from flask import Flask, request
import json
import sqlite3
import bcrypt
import uuid

app = Flask(__name__)

conn = sqlite3.connect('api.db')

user_tokens = {}


@app.route("/events/post", methods=['POST'])
def events():
    data = request.get_json()
    credentials = data['auth']
    event = data['model']
    print(event)
    c = conn.cursor()
    c.execute('''
        INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?)
    ''', (event["title"], event["description"], event["sportKind"], event["dateTime"], event["duration"], event["location"], event["imageUrl"]))
    conn.commit()
    return '{"result": "success"}'


@app.route("/events/get", methods=['POST'])
def post_event():
    user = {
        "name": "Tom Cruise",
        "avatarUrl": "http://a5.files.biography.com/image/upload/c_fit,cs_srgb,dpr_1.0,h_1200,q_80,w_1200/MTE5NDg0MDU0OTM2NTg1NzQz.jpg"
    }
    data = request.get_json()
    credentials = data['auth']
    query = data['model']
    c = conn.cursor()
    if query:
        c.execute("select * from events "
                  "  where title like '%' || ? || '%'"
                  "  and description like '%' || ? || '%'"
                  "  and location like '%' || ? || '%'"
                  "  and sportKind like '%' || ? || '%'", query)
    else:
        c.execute('select * from events')
    res = list(map(lambda t: {
        "title": t[0],
        "description": t[1],
        "creator": user,
        "sportKind": t[2],
        "dateTime": t[3],
        "duration": t[4],
        "location": t[5],
        "imageUrl": "http://www.livelappeenranta.fi/sites/default/files/lut1.jpg"
    }, c.fetchall()))
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
    user_id = token_data['id']
    user_token = token_data['token']
    actual_token = user_tokens.get(user_id)
    if (user_token == actual_token):
        return json.dumps({'result': 'success'})
    else:
        return json.dumps({'result': 'fail'})


if __name__ == "__main__":
    app.run()
