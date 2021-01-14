package dto.internal;

import java.util.List;

public class FireStationResourcesDTO {
    public String id;
    public List<VehicleDTO> vehicles;
    public List<String> idFireFighters; 

    public FireStationResourcesDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<VehicleDTO> getVehicles() {
		return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
		this.vehicles = vehicles;
    }
    
    public List<String> getIdFireFighters() {
		return idFireFighters;
    }
    
    public void setIdFireFighters(List<String> idFireFighters) {
		this.idFireFighters = idFireFighters;
	}

	public boolean hasRessources(){
		return vehicles.size() != 0 && idFireFighters.size() != 0;
	}
}
