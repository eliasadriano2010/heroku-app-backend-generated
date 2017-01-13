package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Display 
 {
		@ObjectId @Id
		private String id;
		private String screenResolution;
		private String screenSize;
		private boolean touchscreen;
	public Display(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getScreenResolution( ) {
			return this.screenResolution;
		}
		public void setScreenResolution(String screenResolution ) {
			this.screenResolution = screenResolution;
		} 
		
		public String getScreenSize( ) {
			return this.screenSize;
		}
		public void setScreenSize(String screenSize ) {
			this.screenSize = screenSize;
		} 
		
		public boolean getTouchscreen( ) {
			return this.touchscreen;
		}
		public void setTouchscreen(boolean touchscreen ) {
			this.touchscreen = touchscreen;
		} 
		
		
	}

