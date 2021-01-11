package dto.internal;

public class PointDTO {
	
	public double latitude;
	public double longitude;
	
	public PointDTO() {}
	
	public PointDTO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "{ latitude :  " + this.latitude + ", longitude " + this.longitude + "}";
	}

	public static double getDistance(PointDTO p1, PointDTO p2){

		double ac = Math.abs(p2.longitude - p1.longitude); 
		double cb = Math.abs(p2.latitude - p1.latitude);

		return Math.hypot(ac, cb);
	}

}
