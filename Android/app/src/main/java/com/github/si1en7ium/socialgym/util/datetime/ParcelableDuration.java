package com.github.si1en7ium.socialgym.util.datetime;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.Duration;
import org.joda.time.ReadableDuration;

public class ParcelableDuration implements Parcelable {
    public final ReadableDuration val;

    public ParcelableDuration(@NonNull ReadableDuration duration) {
        this.val = duration;
    }

    //region Parcelable
    public ParcelableDuration(Parcel in) {
        String serializedDuration = in.readString();
        val = Duration.parse(serializedDuration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(val.toString());
    }

    public static final Parcelable.Creator<ParcelableDuration> CREATOR =
            new Parcelable.Creator<ParcelableDuration>() {
        @Override
        public ParcelableDuration createFromParcel(Parcel in) {
            return new ParcelableDuration(in);
        }

        @Override
        public ParcelableDuration[] newArray(int size) {
            return new ParcelableDuration[size];
        }
    };
    //endregion
}
