package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import dto.internal.FireDTO;
import manager.ManagerOperation;

@Component
public class Receiver {

  @Autowired
  public ManagerOperation managerOperation;
  
  @JmsListener(destination = "queue.fire")
  public void receiveFire(FireDTO fire) {
    managerOperation.receiveNewFire(fire);
  }

}