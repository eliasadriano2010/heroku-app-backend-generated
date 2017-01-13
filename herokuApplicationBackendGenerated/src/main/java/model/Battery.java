package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Battery 
 {
		@ObjectId @Id
		private String id;
		private String standbyTime;
		private String talkTime;
		private String batteryType;
	public Battery(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getStandbyTime( ) {
			return this.standbyTime;
		}
		public void setStandbyTime(String standbyTime ) {
			this.standbyTime = standbyTime;
		} 
		
		public String getTalkTime( ) {
			return this.talkTime;
		}
		public void setTalkTime(String talkTime ) {
			this.talkTime = talkTime;
		} 
		
		public String getBatteryType( ) {
			return this.batteryType;
		}
		public void setBatteryType(String batteryType ) {
			this.batteryType = batteryType;
		} 
		
		
	}

