package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import dto.internal.FireDTO;
import manager.ManagerRessources;

@Component
public class Receiver {

  @Autowired
  public ManagerRessources managerRessources;
  
  @JmsListener(destination = "queue.fire")
  public void receiveFire(FireDTO fire) {
    managerRessources.receiveNewFire(fire);
  }

}