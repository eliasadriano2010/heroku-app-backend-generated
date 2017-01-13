package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Android 
 {
		@ObjectId @Id
		private String id;
		private String os;
		private String ui;
	public Android(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getOs( ) {
			return this.os;
		}
		public void setOs(String os ) {
			this.os = os;
		} 
		
		public String getUi( ) {
			return this.ui;
		}
		public void setUi(String ui ) {
			this.ui = ui;
		} 
		
		
	}

