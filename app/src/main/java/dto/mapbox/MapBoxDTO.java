package dto.mapbox;

import java.util.List;

public class MapBoxDTO {

	public List<RoutesDTO> routes;
	public Object waypoints;
	public String code;
	public Object uuid;
	
	public MapBoxDTO() {}

	public List<RoutesDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RoutesDTO> routes) {
		this.routes = routes;
	}

	public Object getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Object waypoints) {
		this.waypoints = waypoints;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getUuid() {
		return uuid;
	}

	public void setUuid(Object uuid) {
		this.uuid = uuid;
	}
	
	
}
