package manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import dto.internal.*;
import services.ResourceService;

@Component
public class ManagerRessources {

    private List<FireDTO> fires;

	private List<FireStationDTO> fireStations;

	private List<FireFighterDTO> fireFighters;

	private List<VehicleDTO> vehicles;

    private List<TypeFireDTO> typeFires;

    private List<J_TVehicle_TFireDTO> tVehicle_TFire;
    
    @Autowired
    private ResourceService resourceService;

    @Autowired
    public JmsTemplate jmsTemplate;
    
    @Autowired
	public Queue queueOperation;

    private ManagerRessources() {
        resourceService = new ResourceService();
        fires = new ArrayList<FireDTO>();
        fireStations = resourceService.getAllFireStation();
        fireFighters = resourceService.getAllFireFighter();
        vehicles = resourceService.getAllVehicle();
        typeFires = resourceService.getAllTypeFire();
        tVehicle_TFire = resourceService.getAllTVehicle_TFire();
    }

    public void receiveNewFire(FireDTO fire){
        Map<Double, String> fireStationMap = new HashMap<Double, String>();
        for(FireStationDTO f : fireStations){

            double distance = PointDTO.getDistance(f.location, fire.location);
            fireStationMap.put(distance, f.id);
        }
        
        int ressourceToConsume = ressourceToConsume(fire);
        List<String> vehicleSend = new ArrayList<>();
        List<String> fireFighterSend = new ArrayList<>();
        List<String> tags = vehicleTagsByTypeFire(fire);
        System.out.println(tags);
        TreeMap<Double, String> sortedDistance = new TreeMap<>();
        sortedDistance.putAll(fireStationMap);
        while(ressourceToConsume != 0){
            Optional<FireStationDTO> station = fireStations.stream().filter(st -> sortedDistance.firstEntry().getValue().equals(st.id)).findAny();
            if(station.isPresent()){
                System.out.println(station.get());
                List<VehicleDTO> filterVehicle = vehicles.stream().filter(ve -> (station.get().id.equals(ve.idFireStation))).collect(Collectors.toList());
                List<FireFighterDTO> filterFireFighter = fireFighters.stream().filter(fe -> (station.get().id.equals(fe.idFireStation))).collect(Collectors.toList());
                System.out.println(filterVehicle);
                System.out.println(ressourceToConsume);
                while(ressourceToConsume != 0 || (filterVehicle.size() == 0 && filterFireFighter.size() == 0)){
                    for(String tag: tags){
                        Optional<VehicleDTO> veh = filterVehicle.stream().filter(ve -> ve.idType.equals(tag)).findFirst();
                        if(veh.isPresent()){
                            vehicleSend.add(veh.get().id);
                            fireFighterSend.add(filterFireFighter.get(0).id);
                            //vehicles.remove(veh.get());
                            //filterVehicle.remove(veh.get());
                            //fireFighters.remove(filterFireFighter.get(0));
                            //filterFireFighter.remove(filterFireFighter.get(0));
                        }
                    }
                    ressourceToConsume--;
                }
            }
        }
        
        OperationDTO op = new OperationDTO();
        op.setIdFire(fire.getId());
        op.setIdFireFighter(fireFighterSend);
        op.setIdVehicle(vehicleSend);
        op.setLocation(fire.getLocation());
        jmsTemplate.convertAndSend(queueOperation, op);
    }

    public int ressourceToConsume(FireDTO fire){
        return (int) (Math.round((fire.size * fire.intensity) / 40) + 1);
    }

    public List<String> vehicleTagsByTypeFire(FireDTO fire){
        List<String> tags = new ArrayList<>();
        TypeFireDTO type = typeFires.stream().filter(typeFire -> typeFire.id.equals(fire.idTypeFire)).findFirst().get();
        for(J_TVehicle_TFireDTO j : tVehicle_TFire){
            if(j.idFireType.equals(type.id)){
                tags.add(j.idVehicleType);
            }
        }

        return tags;
    }
}
