package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.ArrayList;
import java.util.List;

// dynamic imports
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
//Generated Class
public class Gadget 
 {
		@ObjectId @Id
		private String id;
		private String additionalFeatures;
		private Android android;
		private String availability;
		private Battery battery;
		private Camera camera;
		private Connectivity connectivity;
		private String description;
		private Display display;
		private Hardware hardware;
private List<Media> images;
		private String name;
		private String height;
		private String width;
		private String lenght;
		private String weight;
		private Storage storage;
		private String snippet;
	public Gadget(){
		
	}
	
	@JsonProperty("_id")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		public String getAdditionalFeatures( ) {
			return this.additionalFeatures;
		}
		public void setAdditionalFeatures(String additionalFeatures ) {
			this.additionalFeatures = additionalFeatures;
		} 
		
		public Android getAndroid( ) {
			return this.android;
		}
		public void setAndroid(Android android ) {
			this.android = android;
		} 
		
		public String getAvailability( ) {
			return this.availability;
		}
		public void setAvailability(String availability ) {
			this.availability = availability;
		} 
		
		public Battery getBattery( ) {
			return this.battery;
		}
		public void setBattery(Battery battery ) {
			this.battery = battery;
		} 
		
		public Camera getCamera( ) {
			return this.camera;
		}
		public void setCamera(Camera camera ) {
			this.camera = camera;
		} 
		
		public Connectivity getConnectivity( ) {
			return this.connectivity;
		}
		public void setConnectivity(Connectivity connectivity ) {
			this.connectivity = connectivity;
		} 
		
		public String getDescription( ) {
			return this.description;
		}
		public void setDescription(String description ) {
			this.description = description;
		} 
		
		public Display getDisplay( ) {
			return this.display;
		}
		public void setDisplay(Display display ) {
			this.display = display;
		} 
		
		public Hardware getHardware( ) {
			return this.hardware;
		}
		public void setHardware(Hardware hardware ) {
			this.hardware = hardware;
		} 
		
    			public List<Media> getImages( ) {
    				return this.images;
    			}
    			
    			public Media getImages(int i){
    				if(images != null){
    					return images.get(i);
    				}else{
    					return null;
    				}
    			}
		public void setImages(List<Media> images ) {
			this.images = images;
		} 
		
		public void addImages(Media images ) {
			if(this.images!=null){
				this.images.add(images);
			}else{
				this.images = new ArrayList();
				this.images.add(images);
			}
		} 
		
		
		public String getName( ) {
			return this.name;
		}
		public void setName(String name ) {
			this.name = name;
		} 
		
		public String getHeight( ) {
			return this.height;
		}
		public void setHeight(String height ) {
			this.height = height;
		} 
		
		public String getWidth( ) {
			return this.width;
		}
		public void setWidth(String width ) {
			this.width = width;
		} 
		
		public String getLenght( ) {
			return this.lenght;
		}
		public void setLenght(String lenght ) {
			this.lenght = lenght;
		} 
		
		public String getWeight( ) {
			return this.weight;
		}
		public void setWeight(String weight ) {
			this.weight = weight;
		} 
		
		public Storage getStorage( ) {
			return this.storage;
		}
		public void setStorage(Storage storage ) {
			this.storage = storage;
		} 
		
		public String getSnippet( ) {
			return this.snippet;
		}
		public void setSnippet(String snippet ) {
			this.snippet = snippet;
		} 
		
		
	}

