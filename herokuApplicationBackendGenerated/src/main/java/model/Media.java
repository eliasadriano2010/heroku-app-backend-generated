package model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

public class Media {

	@ObjectId
	@Id
	private String id;
	private String mediaType;
	private String mediaName;
	private String mediaData;
	
	public Media() {
		super();
	}

	public Media(String id, String mediaType, String mediaName, String mediaData) {
		super();
		this.id = id;
		this.mediaType = mediaType;
		this.mediaName = mediaName;
		this.mediaData = mediaData;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaData() {
		return mediaData;
	}

	public void setMediaData(String mediaData) {
		this.mediaData = mediaData;
	}

	@Override
	public String toString() {
		return "Media [id=" + id + ",mediaType=" + mediaType + ",mediaName="
				+ mediaName + ", mediaData=" + mediaData + "]";
	}
}	
