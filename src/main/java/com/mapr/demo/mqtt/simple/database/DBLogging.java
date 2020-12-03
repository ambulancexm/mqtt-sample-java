package com.mapr.demo.mqtt.simple.database;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBLogging {
    
    private static final String QUERY = "INSERT INTO sensorTest(projet, line, mac, name, val,date,time) values(?,?,?,?,?,?,?)";  
    private static final String SELECT = "SELECT * FROM sensorTest";
    private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());
    private static InputStream resourcesInputStream = DBLogging.class.getClassLoader().getResourceAsStream("application.properties");
	private static Properties properties = new Properties();
	private static MqttMessage mq = new MqttMessage();
	private static byte[] payload = {0x00,0x00,0x00,0x00,0x00,0x00,0x00};
	
    
    public static void main(String[] args) {
    	
//    	try {
//            properties.load(resourcesInputStream);
//        } catch (IOException e) {
//            LOGGER.warn("Cannot read property ", e);
//        }
//    	String JdbcURL = properties.getProperty("db.url")+properties.getProperty("db.name");
//        String Username = properties.getProperty("db.username");
//        String password = properties.getProperty("db.password");
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rst = null;
//        
//            try
//            {
//                con = DriverManager.getConnection(JdbcURL , Username, password);
//                if(!con.isClosed()) {
//                	System.out.println("c'est connecté");           	
//                }
//            pstmt = con.prepareStatement(SELECT);
//            rst = pstmt.executeQuery();
//            System.out.println("Id\t\tName\t\tAge\n");
//            while(rst.next()) {
//               System.out.print(rst.getInt(1));
//               System.out.print("\t\t"+rst.getString(2));
//               System.out.print("\t\t"+rst.getInt(3));
//               System.out.println();
//            }
//         } catch(Exception exec) {
//            exec.printStackTrace();
//         }
//    	MqttMessage mq = new MqttMessage();
//    	byte[] payload = {0x00};
//    	mq.setPayload(payload);
    	mq.setPayload(payload);
    	DBLogging.writeToDb("test/1/00:00:00:00:00:00/temp", "2");
	}
    
    

    public static void writeToDb(String topic, String payload ) {
        
    	// prepare connexion
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
        
//        prépare create into
     	String[] recievedTopicAsSrings = topic.split("/");
	    //String[] recievedValuesAsSrings = new String(new String(mqttMessage.getPayload())).split("/");
	    String project = recievedTopicAsSrings[0];
	    int line = Integer.parseInt(recievedTopicAsSrings[1]);
	    String mac = recievedTopicAsSrings[2];
	    String name = recievedTopicAsSrings[3];
//	    double value = Double.parseDouble(new String(mqttMessage.getPayload()));
	    double value = Double.parseDouble(payload);
      
        try{
        	con = DriverManager.getConnection(JdbcURL , Username, password);
        	if(con.isClosed()) {
            	LOGGER.debug("-----PAS CONNECTÉ-------");
            }else {
            	LOGGER.debug("-----c'est connecté-----");
            	        	
            }
        	pstmt = con.prepareStatement(QUERY);
        	
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
        } catch (SQLException s) {
                LOGGER.debug("retour de querry "+ s.getMessage());
        }
        }
}

