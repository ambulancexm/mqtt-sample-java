package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapr.demo.mqtt.simple.database.DBLogging;

public class SimpleMqttCallBack implements MqttCallback {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
	
	Thread thread = new Thread();
	long sleepTime = 2000;
	
	public void connectionLost(Throwable throwable) {
    System.out.println("Connection to MQTT broker lost!");
  }

  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
	  System.out.println("reception : " + topic.toString() +" : "+ mqttMessage.toString());
	  try {
		  if(topic.equals("request")) {
			  LOGGER.debug("on est dans request");
		  }else {
			  DBLogging.writeToDb(topic, new String(mqttMessage.getPayload()));  
		  }
		
	} catch (Exception e) {
		LOGGER.debug(e.getMessage()+ " on ne bloque pas ");
	}
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
  }
}
