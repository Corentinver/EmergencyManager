package dto.mapbox;

import java.util.List;

public class RoutesDTO {

	public String weight_name;
	public double weight;
	public double duration;
	public double distance;
	public List<Object> legs;
	public GeometryDTO geometry;
	
	public RoutesDTO() {}

	public String getWeight_name() {
		return weight_name;
	}

	public void setWeight_name(String weight_name) {
		this.weight_name = weight_name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<Object> getLegs() {
		return legs;
	}

	public void setLegs(List<Object> legs) {
		this.legs = legs;
	}

	public GeometryDTO getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryDTO geometry) {
		this.geometry = geometry;
	}
	
	
}
