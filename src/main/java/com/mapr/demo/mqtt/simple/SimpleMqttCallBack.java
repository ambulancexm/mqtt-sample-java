package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapr.demo.mqtt.simple.database.ConnectionDb;
import com.mapr.demo.mqtt.simple.database.DBLogging;
import com.mapr.demo.mqtt.simple.database.DBRequest;



public class SimpleMqttCallBack implements MqttCallback {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
	private DBRequest dbRequest;
	private ConnectionDb db; 
	
	Thread thread = new Thread();
	long sleepTime = 2000;
	
	public void connectionLost(Throwable throwable) {
    System.out.println("Connection to MQTT broker lost!");
  }
/**
 * cette fonction sert de dispacheur 
 * sensor : pour la reception des données à mettre en base
 * request : pour questionner la base de donnée
 * 
 * 
 * 
 * 
 */
  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
	  System.out.println("reception : " + topic.toString() +" : "+ mqttMessage.toString());
	  db = new ConnectionDb();
	  try {
		  if(topic.contains("request")) {
			  LOGGER.debug("on est dans request");
//			  dbRequest = new DBRequest(mqttMessage.toString());
			  if(mqttMessage.equals("update"))
			  
			  db.findIot();
		  }
		  if(topic.contains("sensor")) {
			  LOGGER.debug("on est dans sensor");
			  db.CreateIotMqtt(topic, new String(mqttMessage.getPayload()));
//			  db.create();
		  }
		  if(topic.contains("update")) {
			  
		  }
		  else {
//			  DBLogging.writeToDb(topic, new String(mqttMessage.getPayload())); 
//			  db.CreateIotMqtt(topic, new String(mqttMessage.getPayload()));
		  }
		
	} catch (Exception e) {
		LOGGER.debug(e.getMessage()+ " on ne bloque pas ");
	}
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
  }
}
