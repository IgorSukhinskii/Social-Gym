package com.github.si1en7ium.socialgym.util.datetime;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

public class ParcelableDateTime implements Parcelable {
    public final ReadableDateTime val;

    public ParcelableDateTime(@NonNull ReadableDateTime dateTime) {
        val = dateTime;
    }

    //region Parcelable
    public ParcelableDateTime(Parcel in) {
        String serializedDateTime = in.readString();
        val = DateTime.parse(serializedDateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(val.toString());
    }

    public static final Parcelable.Creator<ParcelableDateTime> CREATOR =
            new Parcelable.Creator<ParcelableDateTime>() {
        @Override
        public ParcelableDateTime createFromParcel(Parcel in) {
            return new ParcelableDateTime(in);
        }

        @Override
        public ParcelableDateTime[] newArray(int size) {
            return new ParcelableDateTime[size];
        }
    };
    //endregion
}
