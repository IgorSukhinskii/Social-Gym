from flask import Flask, request
import json
import sqlite3

app = Flask(__name__)

conn = sqlite3.connect('api.db')

@app.route("/events", methods=['GET', 'POST'])
def events():
    if request.method == 'POST':
        event = request.get_json()
        print(event)
        c = conn.cursor()
        c.execute('''
            INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?)
        ''', (event["title"], event["description"], event["sportKind"], event["dateTime"], event["duration"], event["location"], event["imageUrl"]))
        conn.commit()
        return '{"result": "success"}'
    else:
        user = {
            "name": "Tom Cruise",
            "avatarUrl": "http://a5.files.biography.com/image/upload/c_fit,cs_srgb,dpr_1.0,h_1200,q_80,w_1200/MTE5NDg0MDU0OTM2NTg1NzQz.jpg"
        }

        query = request.args.get('q')
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


if __name__ == "__main__":
    app.run()