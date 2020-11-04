package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMqttCallBack implements MqttCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
	
  public void connectionLost(Throwable throwable) {
    System.out.println("Connection to MQTT broker lost!");
  }

  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
//    System.out.println("topic => " + topic.toString()+ " \t\t ||"+ new String(mqttMessage.getPayload()) ); 
	  String[] recievedTopicAsSrings = topic.split("/");
//    String[] recievedValuesAsSrings = new String(new String(mqttMessage.getPayload())).split("/");
	  String project = recievedTopicAsSrings[0];
	  int line = Integer.parseInt(recievedTopicAsSrings[1]);
	  String mac = recievedTopicAsSrings[2];
	  String name = recievedTopicAsSrings[3];
	  double value = Double.parseDouble(new String(mqttMessage.getPayload()));
	  
    LOGGER.debug("retour",project,line, mac,name,value);
    
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
  }
}
