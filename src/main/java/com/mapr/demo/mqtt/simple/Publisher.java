package com.mapr.demo.mqtt.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Publisher {

	static String userName ="bulleux";
	static String password ="bulleux";
	static String messageString = "Hello World from Java!";
	static MqttConnectOptions option = new MqttConnectOptions();
	static MqttClient client; 
	private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class.getName());
	static InputStream resourcesInputStream = Subscriber.class.getClassLoader().getResourceAsStream("application.properties");
	static Properties properties = new Properties();
	
	
	
  public static void main(String[] args) throws MqttException {
	
      try {
          properties.load(resourcesInputStream);
      } catch (IOException e) {
          LOGGER.warn("Cannot read property ", e);
      }
      
    System.out.println("== START SUBSCRIBER ==");
    String userName = properties.getProperty("mqtt_user_name");
	String password = properties.getProperty("mqtt_password");
	  

    if (args.length == 2 ) {
      messageString = args[1];
    }


    System.out.println("== START PUBLISHER ==");

    client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    option.setPassword(password.toCharArray());
    option.setUserName(userName);
    MqttMessage message = new MqttMessage();
    message.setPayload(messageString.getBytes());
    
    client.connect();


    client.disconnect();

    System.out.println("== END PUBLISHER ==");

  }
  
  public static void send(String topic, String payload) {
	  
	  try {
          properties.load(resourcesInputStream);
      } catch (IOException e) {
          LOGGER.warn("Cannot read property ", e);
      }
	  String userName = properties.getProperty("mqtt_user_name");
	  String password = properties.getProperty("mqtt_password");
	  String mqttbrokerUrl = properties.getProperty("mqttbroker_url");
	  try {
		client = new MqttClient(mqttbrokerUrl, MqttClient.generateClientId());
	    option.setPassword(password.toCharArray());
	    option.setUserName(userName);
	    
	    MqttMessage message = new MqttMessage();
	    message.setPayload(payload.getBytes());
	    
	    client.connect();
	    client.publish(topic, message);
	    
	    client.disconnect();

	    System.out.println("== END PUBLISHER ==");

	} catch (MqttException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
