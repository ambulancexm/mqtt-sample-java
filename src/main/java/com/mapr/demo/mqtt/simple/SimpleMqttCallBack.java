package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback {

	Thread thread = new Thread();
	long sleepTime = 2000;
	
	public void connectionLost(Throwable throwable) {
    System.out.println("Connection to MQTT broker lost!");
  }

  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
	  System.out.println("reception : " + topic.toString() +" : "+ mqttMessage.toString());
//	  Thread.sleep(sleepTime);
		  DBLogging.writeToDb(topic, new String(mqttMessage.getPayload()));  
//	  System.out.println("****test***");
    
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
  }
}
