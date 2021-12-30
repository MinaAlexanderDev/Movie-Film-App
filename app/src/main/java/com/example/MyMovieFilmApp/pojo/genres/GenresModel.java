package com.example.MyMovieFilmApp.pojo.genres;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class GenresModel implements Parcelable {
    public static final Creator<GenresModel> CREATOR = new Creator<GenresModel>() {
        @Override
        public GenresModel createFromParcel(Parcel in) {
            return new GenresModel(in);
        }

        @Override
        public GenresModel[] newArray(int size) {
            return new GenresModel[size];
        }
    };
    private Integer id;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public GenresModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    protected GenresModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
