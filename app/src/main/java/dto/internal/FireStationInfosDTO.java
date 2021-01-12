package dto.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireStationInfosDTO {

	public int nbFireFighters;
	public Map<String, Integer> vehiclesByType;
	
	public FireStationInfosDTO() {}

	public int getNbFireFighters() {
		return nbFireFighters;
	}

	public void setNbFireFighters(int nbFireFighters) {
		this.nbFireFighters = nbFireFighters;
	}

	public Map<String, Integer> getVehiclesByType() {
		return vehiclesByType;
	}

	public void setVehiclesByType(Map<String, Integer> vehiclesByType) {
		this.vehiclesByType = vehiclesByType;
	}
	
	public void buildVehiclesByType(List<VehicleDTO> listVehicles) {
		vehiclesByType = new HashMap<String, Integer>();
		for (int i = 0 ; i < listVehicles.size() ; i++ ) {
			VehicleDTO currentVehicle = listVehicles.get(i);
			if (!vehiclesByType.containsKey(currentVehicle.getIdType())) {
				vehiclesByType.put(currentVehicle.getIdType(), 1);
			} else {
				vehiclesByType.put(currentVehicle.getIdType(), vehiclesByType.get(currentVehicle.getIdType()) + 1);
			}
		}
		
	}
	
}
