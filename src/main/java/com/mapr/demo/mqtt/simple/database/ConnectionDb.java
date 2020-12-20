package com.mapr.demo.mqtt.simple.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionDb  implements Crudable{

	private static InputStream resourcesInputStream = DBLogging.class.getClassLoader().getResourceAsStream("application.properties");
	private static Properties properties = new Properties();
	private static MqttMessage mq = new MqttMessage();
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
	private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rst = null;
    
    private final String insert = "INSERT INTO sensorTest(projet, line, mac, name, val,date,time) values(?,?,?,?,?,?,?)";
    private final String distinct = "SELECT DISTINCT mac from sensorTest";
	
	public ConnectionDb() {
		try {
            properties.load(resourcesInputStream);
        } catch (IOException e) {
            LOGGER.warn("Cannot read property ", e);
        }
    	String JdbcURL = properties.getProperty("db.url")+properties.getProperty("db.name");
        String Username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        
        
//     
      
        try{
        	con = DriverManager.getConnection(JdbcURL , Username, password);
        	if(con.isClosed()) {
            	LOGGER.warn("pas connecté a la base de données ");
            }
            	        	
            
        	
        	
        	
	        
        } catch (SQLException s) {
                LOGGER.warn("retour de querry "+ s.getMessage());
        }
        }
	
	public void closeDb() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean create() {
//		String query =  "INSERT INTO sensorTest(projet, line, mac, name, val,date,time) "
//				+ 		"values(sensor,999,AA:AA:AA:AA:AA:AA,fake,99,?,?)";
		String query ="INSERT INTO `fake` (`name`, `age`) VALUES (?,?)";
		try {
			java.util.Date date=new java.util.Date();
			
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, "thomas2");
			pstmt.setInt(2, 99);	
//			pstmt.setDate(1,sqlDate);
//	        pstmt.setTimestamp(2, sqlTime);
	        
	        pstmt.execute();
	        con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean CreateIotMqtt(String topic, String payload) {
		
		String[] recievedTopicAsSrings = topic.split("/",4);
	    String project = recievedTopicAsSrings[0];
	    int line = Integer.parseInt(recievedTopicAsSrings[1]);
	    String mac = recievedTopicAsSrings[2];
	    String name = recievedTopicAsSrings[3];
	    double value = Double.parseDouble(payload);
	    int cpt =0;
	    for (String item : recievedTopicAsSrings) {
			System.out.println("["+ cpt +"]" +item);
			cpt++;
		}
		
		try {
			pstmt = con.prepareStatement(insert);
			java.util.Date date=new java.util.Date();
			
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
        
        	pstmt.setString(1, project);
	        pstmt.setInt(2,line);
	        pstmt.setString(3, mac);
	        pstmt.setString(4, name);
	        pstmt.setDouble(5, value);
	        pstmt.setDate(6,sqlDate);
	        pstmt.setTimestamp(7, sqlTime);
	        
	        pstmt.execute();
	        
	        con.close();
	        return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<String> findIot(){
		List<String> listIot = new ArrayList<>();
		try {
			Statement stat = con.createStatement();
			rst = stat.executeQuery(distinct);
			
			while(rst.next()) {
				String mac = rst.getString("mac"); 
				
				listIot.add(mac);
				
			}
			
	        con.close();
	        LOGGER.debug("find "+ listIot);
	        return listIot;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
		
		

	@Override
	public String find(String mac) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String find() {
		// TODO Auto-generated method stub
		return null;
	}
	
		
	}
	

