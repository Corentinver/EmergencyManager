package dto.internal;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("J_TVehicle_TFire")
public class J_TVehicle_TFireDTO {

	public String idVehicleType;
	public String idFireType;
	
	public J_TVehicle_TFireDTO() {}

	public String getTypeVehicle() {
		return idVehicleType;
	}

	public void setTypeVehicle(String idVehicleType) {
		this.idVehicleType = idVehicleType;
	}

	public String getTypeFire() {
		return idFireType;
	}

	public void setTypeFire(String idFireType) {
		this.idFireType = idFireType;
	}

	@Override
	public String toString() {
		return this.idFireType + " " + this.idVehicleType;
	}

}
