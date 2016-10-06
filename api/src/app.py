from flask import Flask
import json

app = Flask(__name__)


@app.route("/events")
def events():
    user = {
        "name": "Tom Cruise",
        "avatarUrl": "http://a5.files.biography.com/image/upload/c_fit,cs_srgb,dpr_1.0,h_1200,q_80,w_1200/MTE5NDg0MDU0OTM2NTg1NzQz.jpg"
    }
    result = [
        {
            "title": "Jogging with friends!",
            "description": "We are going to get together and jog-jog-jog! Don't be late.💩",
            "creator": user,
            "sportKind": "JOGGING",
            "dateTime": "2016-10-07T10:50:00Z",
            "duration": "PT6600.0S"
        } for i in range(15)
    ]
    return json.dumps(result)


if __name__ == "__main__":
    app.run()