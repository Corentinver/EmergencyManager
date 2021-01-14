package services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dto.internal.*;

@Service
public class ResourceService {

    //@Value("${restUlrApi}")
    private static String restUrlApi = "http://localhost:8080/";
    public RestTemplate restTemplate;
    
    public ResourceService(){
        restTemplate = new RestTemplate();
    }
    
    
    public List<FireStationDTO> getAllFireStation()
    {
		ResponseEntity<FireStationDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allFireStation",FireStationDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    public List<FireFighterDTO> getAllFireFighter()
    {
        ResponseEntity<FireFighterDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allFireFighter",FireFighterDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    public List<VehicleDTO> getAllVehicle()
    {
        ResponseEntity<VehicleDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allVehicle",VehicleDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }
    
    public List<SensorDTO> getAllSensor()
    {
        ResponseEntity<SensorDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allSensor",SensorDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    public List<TypeFireDTO> getAllTypeFire()
    {
        ResponseEntity<TypeFireDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allTypeFire", TypeFireDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    public List<J_TVehicle_TFireDTO> getAllTVehicle_TFire()
    {
        ResponseEntity<J_TVehicle_TFireDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "allTVehicle_TFire", J_TVehicle_TFireDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    public FireStationInfosDTO getFireStationInfo(){
        ResponseEntity<FireStationInfosDTO> responseEntity = restTemplate.getForEntity(restUrlApi + "informationsFireStation", FireStationInfosDTO.class);
        return responseEntity.getBody();
    }
    

    public HashMap<Double, String> getMapFireStationByDistance(PointDTO fireLocation){

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("endLatitude", String.valueOf(fireLocation.latitude));
		headers.add("endLongitude", String.valueOf(fireLocation.longitude));
		HttpEntity<String> request;
		ResponseEntity<HashMap> responseEntity = null;

		request = new HttpEntity<String>(null, headers);
		responseEntity = restTemplate.exchange(restUrlApi + "getMapFireStationByDistance", HttpMethod.GET, request, HashMap.class);
		return responseEntity.getBody();
    }

    public FireStationResourcesDTO getFireStationResourcesAvailable(String idFireStation){

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("idFireStation", idFireStation);
		HttpEntity<String> request;
		ResponseEntity<FireStationResourcesDTO> responseEntity = null;

		request = new HttpEntity<String>(null, headers);
		responseEntity = restTemplate.exchange(restUrlApi + "ressourcesFireStation", HttpMethod.GET, request, FireStationResourcesDTO.class);
		return responseEntity.getBody();
    }
    

    public List<FireDTO> unmanagedFire(){
        ResponseEntity<FireDTO[]> responseEntity = restTemplate.getForEntity(restUrlApi + "unmanagedFire", FireDTO[].class);
        return Arrays.asList(responseEntity.getBody());
    } 

    public double getRouteDuration(double startLatitude, double startLongitude, double endLatitude, double endLongitude)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("startLatitude", String.valueOf(startLatitude));
		headers.add("startLongitude", String.valueOf(startLongitude));
		headers.add("endLatitude", String.valueOf(endLatitude));
		headers.add("endLongitude", String.valueOf(endLongitude));
		HttpEntity<String> request;
		ResponseEntity<Double> responseEntity = null;

		request = new HttpEntity<String>(null, headers);
		responseEntity = restTemplate.exchange("http://localhost:8080/getRouteDuration", HttpMethod.GET, request, Double.class);
		return responseEntity.getBody();
	}

    
}
