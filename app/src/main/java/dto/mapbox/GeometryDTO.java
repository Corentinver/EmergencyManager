package dto.mapbox;

import java.util.List;

public class GeometryDTO {

	public List<List<Double>> coordinates;
	public String type;
	
	public GeometryDTO() {}

	public List<List<Double>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<List<Double>> coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
