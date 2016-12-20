from flask import Flask, request
import json
import sqlite3
import bcrypt
import uuid

app = Flask(__name__)

conn = sqlite3.connect('api.db')

user_tokens = {}


def check_credentials(credentials):
    token = user_tokens.get(credentials.get('id'))
    if (token == credentials.get('token')):
        return credentials['id']
    else:
        return None


def results_to_events(fetchall):
    return list(map(lambda t: {
        "title": t[0],
        "description": t[1],
        "sportKind": t[2],
        "dateTime": t[3],
        "duration": t[4],
        "location": t[5],
        "imageUrl": t[6],
        "userId": t[7],
        "id": t[8]
    }, fetchall))


@app.route("/events/post", methods=['POST'])
def post_event():
    data = request.get_json()
    credentials = data['auth']
    user_id = check_credentials(credentials)
    if (user_id is None):
        return json.dumps({'result': 'fail'})
    event = data['model']
    print(event)
    c = conn.cursor()
    c.execute('''
        INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    ''', (event["title"], event["description"], event["sportKind"], event["dateTime"], event["duration"], event["location"], event["imageUrl"], user_id))
    conn.commit()
    return '{"result": "success"}'


@app.route("/events/get", methods=['POST'])
def events():
    data = request.get_json()
    credentials = data['auth']
    user_id = check_credentials(credentials)
    if (user_id is None):
        return json.dumps({'result': 'fail'})
    query = data['model']
    c = conn.cursor()
    if query and query != "":
        c.execute("select *, ROWID from events"
                  "  where title like '%' || ? || '%'"
                  "  and description like '%' || ? || '%'"
                  "  and location like '%' || ? || '%'"
                  "  and sportKind like '%' || ? || '%'", query)
    else:
        c.execute('select *, ROWID from events')
    res = results_to_events(c.fetchall())
    return json.dumps(res)


@app.route("/login", methods=['POST'])
def login():
    login_data = request.get_json()
    c = conn.cursor()
    c.execute("select password, id FROM users WHERE login = ?", (login_data["email"],))
    password, id = c.fetchone()
    if bcrypt.checkpw(login_data["password"].encode('utf-8'), password):
        token = uuid.uuid4().hex
        user_tokens[id] = token
        return json.dumps({
            'result': 'success',
            'token': token,
            'id': id
        })
    else:
        return json.dumps({'result': 'fail'})


@app.route("/register", methods=['POST'])
def register():
    register_data = request.get_json()
    password = bcrypt.hashpw(register_data['password'].encode('utf-8'), bcrypt.gensalt())
    c = conn.cursor()
    c.execute("INSERT INTO users (name, login, password) VALUES (?,?,?)",
              (register_data['name'],
               register_data['email'],
               password))
    conn.commit()
    return json.dumps({'result': 'success'})


@app.route("/check", methods=['POST'])
def check_token():
    token_data = request.get_json()
    user_id = check_credentials(token_data)
    if (user_id is not None):
        return json.dumps({'result': 'success'})
    else:
        return json.dumps({'result': 'fail'})


@app.route("/interested", methods=['POST'])
def interested():
    data = request.get_json()
    credentials = data['auth']
    user_id = check_credentials(credentials)
    if (user_id is None):
        return json.dumps({'result': 'fail'})
    event_id = data['model']
    c = conn.cursor()
    c.execute("INSERT INTO users_events (user_id, event_id, type) VALUES (?, ?, ?)",
              (user_id, event_id, "interested"))
    conn.commit()
    return json.dumps({'result': 'success'})


@app.route("/going", methods=['POST'])
def going():
    data = request.get_json()
    credentials = data['auth']
    user_id = check_credentials(credentials)
    if (user_id is None):
        return json.dumps({'result': 'fail'})
    event_id = data['model']
    c = conn.cursor()
    c.execute("INSERT INTO users_events (user_id, event_id, type) VALUES (?, ?, ?)",
              (user_id, event_id, "going"))
    conn.commit()
    return json.dumps({'result': 'success'})


@app.route("/events/my", methods=['POST'])
def my_events():
    data = request.get_json()
    credentials = data['auth']
    user_id = check_credentials(credentials)
    if (user_id is None):
        return json.dumps({'result': 'fail'})
    c = conn.cursor()
    c.execute("SELECT e.*, e.ROWID as eid from events e"
              " JOIN users_events ue ON ue.event_id=e.ROWID"
              " WHERE ue.user_id=?"
              " AND ue.type='interested'", (user_id,))
    interested = results_to_events(c.fetchall())
    c.execute("SELECT e.*, e.ROWID as eid from events e"
              " JOIN users_events ue ON ue.event_id=e.ROWID"
              " WHERE ue.user_id=?"
              " AND ue.type='going'",(user_id,))
    going = results_to_events(c.fetchall())
    c.execute("SELECT e.*, e.ROWID as eid from events e"
              " WHERE e.creator_id=?", (user_id,))
    my_events = results_to_events(c.fetchall())
    return json.dumps({'interested': interested, 'going': going, 'myEvents': my_events})

if __name__ == "__main__":
    app.run()
