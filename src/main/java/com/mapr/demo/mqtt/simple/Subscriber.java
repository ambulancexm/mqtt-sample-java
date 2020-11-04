package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER ==");
    String userName ="bulleux";
	String password ="bulleux";
	
    MqttClient client=new MqttClient("tcp://176.166.1.64:1883", MqttClient.generateClientId());
    client.setCallback( new SimpleMqttCallBack() );
    MqttConnectOptions option = new MqttConnectOptions();
    option.setPassword(password.toCharArray());
    option.setUserName(userName);
    client.connect();

    client.subscribe("#");

  }

}
