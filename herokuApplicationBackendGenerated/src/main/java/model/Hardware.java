package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Hardware 
 {
		@ObjectId @Id
		private String id;
		private boolean accelerometer;
		private String audioJack;
		private String cpu;
		private boolean fmRadio;
		private boolean physicalKeyboard;
		private String usb;
	public Hardware(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public boolean getAccelerometer( ) {
			return this.accelerometer;
		}
		public void setAccelerometer(boolean accelerometer ) {
			this.accelerometer = accelerometer;
		} 
		
		public String getAudioJack( ) {
			return this.audioJack;
		}
		public void setAudioJack(String audioJack ) {
			this.audioJack = audioJack;
		} 
		
		public String getCpu( ) {
			return this.cpu;
		}
		public void setCpu(String cpu ) {
			this.cpu = cpu;
		} 
		
		public boolean getFmRadio( ) {
			return this.fmRadio;
		}
		public void setFmRadio(boolean fmRadio ) {
			this.fmRadio = fmRadio;
		} 
		
		public boolean getPhysicalKeyboard( ) {
			return this.physicalKeyboard;
		}
		public void setPhysicalKeyboard(boolean physicalKeyboard ) {
			this.physicalKeyboard = physicalKeyboard;
		} 
		
		public String getUsb( ) {
			return this.usb;
		}
		public void setUsb(String usb ) {
			this.usb = usb;
		} 
		
		
	}

