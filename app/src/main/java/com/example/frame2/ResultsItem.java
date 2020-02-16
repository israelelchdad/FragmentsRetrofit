package com.example.frame2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class ResultsItem implements Parcelable {
	public ResultsItem(String site, int size, String iso31661, String name, @NonNull String id, String type, String iso6391, String key) {
		this.site = site;
		this.size = size;
		this.iso31661 = iso31661;
		this.name = name;
		this.id = id;
		this.type = type;
		this.iso6391 = iso6391;
		this.key = key;
	}

	@SerializedName("site")
	private String site;

	@SerializedName("size")
	private int size;

	@SerializedName("iso_3166_1")
	private String iso31661;

	@SerializedName("name")
	private String name;

	@SerializedName("id")

	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("iso_639_1")
	private String iso6391;

	@SerializedName("key")
	private String key;

	@PrimaryKey
	@NonNull
	private int movieid;


	protected ResultsItem(Parcel in) {
		site = in.readString();
		size = in.readInt();
		iso31661 = in.readString();
		name = in.readString();
		id = in.readString();
		type = in.readString();
		iso6391 = in.readString();
		key = in.readString();
		movieid = in.readInt();
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"site = '" + site + '\'' + 
			",size = '" + size + '\'' + 
			",iso_3166_1 = '" + iso31661 + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(site);
		dest.writeInt(size);
		dest.writeString(iso31661);
		dest.writeString(name);
		dest.writeString(id);
		dest.writeString(type);
		dest.writeString(iso6391);
		dest.writeString(key);
		dest.writeInt(movieid);
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getIso31661() {
		return iso31661;
	}

	public void setIso31661(String iso31661) {
		this.iso31661 = iso31661;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	public String getId() {
		return id;
	}

	public void setId(@NonNull String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIso6391() {
		return iso6391;
	}

	public void setIso6391(String iso6391) {
		this.iso6391 = iso6391;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
}