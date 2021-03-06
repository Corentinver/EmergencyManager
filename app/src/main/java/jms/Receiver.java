package jms;

import java.io.IOException;

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
  public void receiveFire(FireDTO fire) throws IOException {
    managerRessources.receiveNewFire(fire);
  }

  @JmsListener(destination = "queue.Updatefire")
  public void receiveUpdateFire(FireDTO fire) throws IOException {
    managerRessources.receiveUpdateFire(fire);
  }

}