package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
//Generated Class
public class Storage 
 {
		@ObjectId @Id
		private String id;
		private String flash;
		private String ram;
	public Storage(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getFlash( ) {
			return this.flash;
		}
		public void setFlash(String flash ) {
			this.flash = flash;
		} 
		
		public String getRam( ) {
			return this.ram;
		}
		public void setRam(String ram ) {
			this.ram = ram;
		} 
		
		
	}

