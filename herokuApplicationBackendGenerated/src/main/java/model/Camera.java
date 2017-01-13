package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Camera 
 {
		@ObjectId @Id
		private String id;
		private boolean flash;
		private boolean video;
		private String primary;
	public Camera(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public boolean getFlash( ) {
			return this.flash;
		}
		public void setFlash(boolean flash ) {
			this.flash = flash;
		} 
		
		public boolean getVideo( ) {
			return this.video;
		}
		public void setVideo(boolean video ) {
			this.video = video;
		} 
		
		public String getPrimary( ) {
			return this.primary;
		}
		public void setPrimary(String primary ) {
			this.primary = primary;
		} 
		
		
	}

