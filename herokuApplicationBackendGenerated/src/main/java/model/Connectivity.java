package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Connectivity 
 {
		@ObjectId @Id
		private String id;
		private String bluetooth;
		private String cell;
		private boolean gps;
		private boolean infrared;
		private String wifi;
	public Connectivity(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getBluetooth( ) {
			return this.bluetooth;
		}
		public void setBluetooth(String bluetooth ) {
			this.bluetooth = bluetooth;
		} 
		
		public String getCell( ) {
			return this.cell;
		}
		public void setCell(String cell ) {
			this.cell = cell;
		} 
		
		public boolean getGps( ) {
			return this.gps;
		}
		public void setGps(boolean gps ) {
			this.gps = gps;
		} 
		
		public boolean getInfrared( ) {
			return this.infrared;
		}
		public void setInfrared(boolean infrared ) {
			this.infrared = infrared;
		} 
		
		public String getWifi( ) {
			return this.wifi;
		}
		public void setWifi(String wifi ) {
			this.wifi = wifi;
		} 
		
		
	}

