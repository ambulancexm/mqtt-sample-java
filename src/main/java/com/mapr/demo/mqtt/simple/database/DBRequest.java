package com.mapr.demo.mqtt.simple.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBRequest  {
	private static InputStream resourcesInputStream = DBLogging.class.getClassLoader().getResourceAsStream("application.properties");
	private static Properties properties = new Properties();
	private static MqttMessage mq = new MqttMessage();
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
	
	public DBRequest(String messageRequest) {
		
		try {
            properties.load(resourcesInputStream);
        } catch (IOException e) {
            LOGGER.warn("Cannot read property ", e);
        }
    	String JdbcURL = properties.getProperty("db.url")+properties.getProperty("db.name");
        String Username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        
//     
      
        try{
        	con = DriverManager.getConnection(JdbcURL , Username, password);
        	if(con.isClosed()) {
            	LOGGER.debug("-----PAS CONNECTÉ-------");
            }else {
            	LOGGER.debug("-----c'est connecté-----");
            	        	
            }
        	
        	LOGGER.debug("affichage de payload : " + messageRequest );
        	
	        con.close();
        } catch (SQLException s) {
                LOGGER.debug("retour de querry "+ s.getMessage());
        }
        }
	}
	
	
	


// SELECT DISTINCT mac from sensorTest
// select * from sensorTest WHERE mac LIKE "EC:FA:BC:BC:C8:B6" ORDER BY time DESC limit 25