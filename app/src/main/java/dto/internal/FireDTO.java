package dto.internal;

public class FireDTO{
	
	public String id;
	public int intensity;
	public String idTypeFire;
	public PointDTO location;
	public int size;
	public static enum stateFire { Initialize, InOperation, Completed };
	public stateFire state;
	
	public FireDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public String getTypeFire() {
		return idTypeFire;
	}

	public void setTypeFire(String idTypeFire) {
		this.idTypeFire = idTypeFire;
	}

	public PointDTO getLocation() {
		return location;
	}

	public void setLocation(PointDTO location) {
		this.location = location;
	}

	public int getSize(){
		return size;
	}

	public void setSize(int size){
		this.size = size;
	}

	public stateFire getState(){
		return state;
	}

	public void setState(stateFire state){
		this.state = state;
	}
	
}
