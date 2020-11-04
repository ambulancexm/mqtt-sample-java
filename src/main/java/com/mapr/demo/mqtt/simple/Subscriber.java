package com.mapr.demo.mqtt.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Subscriber {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class.getName());
	
  public static void main(String[] args) throws MqttException {
		  
	  InputStream resourcesInputStream = Subscriber.class.getClassLoader().getResourceAsStream("application.properties");
      Properties properties = new Properties();
      try {
          properties.load(resourcesInputStream);
      } catch (IOException e) {
          LOGGER.warn("Cannot read property ", e);
      }
      
    System.out.println("== START SUBSCRIBER ==");
    String userName = properties.getProperty("mqtt_user_name");
	String password = properties.getProperty("mqtt_password");
	String brokerUrl = properties.getProperty("mqttbroker_url");
	String topic = properties.getProperty("topic");
	
    MqttClient client=new MqttClient(brokerUrl, MqttClient.generateClientId());
    client.setCallback( new SimpleMqttCallBack() );
    MqttConnectOptions option = new MqttConnectOptions();
    option.setPassword(password.toCharArray());
    option.setUserName(userName);
    client.connect();

    client.subscribe(topic);
//String[] recievedValuesAsSrings = new String(payload).split("/");
  }

}
