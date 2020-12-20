package com.mapr.demo.mqtt.simple.database;

import java.util.List;

public interface Crudable {

	boolean create();	
	String find();
	String find(String mac);
	List <String> findAll();
	boolean delete();
		
}
