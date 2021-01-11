package dto.internal;

import java.util.List;

public class OperationDTO {

	public String id;
	
	public List<String> idFireFighter;
	
	public List<String> idVehicle;
	
	public String idFire;
	
	public PointDTO location;
	
	public OperationDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getIdFireFighter() {
		return idFireFighter;
	}

	public void setIdFireFighter(List<String> idFireFighter) {
		this.idFireFighter = idFireFighter;
	}

	public List<String> getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(List<String> idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getIdFire() {
		return idFire;
	}

	public void setIdFire(String idFire) {
		this.idFire = idFire;
	}

	public PointDTO getLocation() {
		return location;
	}

	public void setLocation(PointDTO location) {
		this.location = location;
	}
	
}
