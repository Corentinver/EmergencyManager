package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

    private Map<String,FireDTO> fires;

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
        System.out.println("loaded");
        resourceService = new ResourceService();
        //fires = resourceService.unmanagedFire();
        fires = new HashMap();
        fireStations = resourceService.getAllFireStation();
        typeFires = resourceService.getAllTypeFire();
        tVehicle_TFire = resourceService.getAllTVehicle_TFire();
    }

    public void receiveNewFire(FireDTO fire) throws IOException {
        
        fires.put(fire.id, fire);
        int ressourceToConsume = ressourceToConsume(fire);
        List<String> vehicleSend = new ArrayList<>();
        List<String> fireFighterSend = new ArrayList<>();
        List<String> tags = vehicleTagsByTypeFire(fire);

        HashMap<Double, String> hashMap = new HashMap<Double, String>();
        hashMap = resourceService.getMapFireStationByDistance(fire.location);
        List<Double> sortedKeys = new ArrayList<>(hashMap.keySet());
        /*for(Set<Double> key : hashMap.keySet()){
            sortedKeys.add(key.);
        }*/

        
        Collections.sort(sortedKeys);
        //System.out.println(sortedKeys);
        while(ressourceToConsume != 0){
            FireStationResourcesDTO fireStationResources = resourceService.getFireStationResourcesAvailable(hashMap.get(sortedKeys.get(0)));
            if(fireStationResources.hasRessources()){
                while(fireStationResources.hasRessources() && ressourceToConsume != 0){
                    for(String tag: tags){
                        Optional<VehicleDTO> veh = fireStationResources.vehicles.stream().filter(ve -> ve.idType.equals(tag)).findFirst();
                        if(!fireStationResources.hasRessources()){
                            hashMap.remove(hashMap.get(sortedKeys.get(0)));
                            fireStationResources = resourceService.getFireStationResourcesAvailable(hashMap.get(sortedKeys.get(0)));
                            veh = fireStationResources.vehicles.stream().filter(ve -> ve.idType.equals(tag)).findFirst();
                        }
                        if(veh.isPresent()){
                            vehicleSend.add(veh.get().id);
                            //System.out.println("IdFireStation : " + fireStationResources.id);
                            //System.out.println("IdFireFighter : " + fireStationResources.idFireFighters);
                            fireFighterSend.add(fireStationResources.idFireFighters.get(0));
                            fireFighterSend.add(fireStationResources.idFireFighters.get(1));
                            fireStationResources.vehicles.remove(veh.get());
                            fireStationResources.idFireFighters.remove(fireStationResources.idFireFighters.get(0));
                            fireStationResources.idFireFighters.remove(fireStationResources.idFireFighters.get(0));
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

    public void receiveUpdateFire(FireDTO fire){

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
