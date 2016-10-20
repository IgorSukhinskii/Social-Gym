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
        result = [
            {
                "title": "Jogging with friends!",
                "description": "We are going to get together and jog-jog-jog-jog-jog-jog-jog! Don't be late.💩",
                "creator": user,
                "sportKind": "RUNNING_JOGGING",
                "dateTime": "2016-10-07T10:50:00Z",
                "duration": "PT6600.0S",
                "location": "Honkakatu 7",
                "imageUrl": "http://edu.glavsprav.ru/_static/_profiles/499/glav.jpg"
            },
            {
                "title": "Running without friends😊",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "creator": user,
                "sportKind": "CYCLING",
                "dateTime": "2016-10-07T12:50:00Z",
                "duration": "PT6600.0S",
                "location": "Laserkatu 8",
                "imageUrl": "http://www.livelappeenranta.fi/sites/default/files/lut1.jpg"
            }
        ]
        c = conn.cursor()
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
        print(res)
        return json.dumps(res)


if __name__ == "__main__":
    app.run()