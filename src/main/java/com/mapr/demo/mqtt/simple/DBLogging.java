package com.mapr.demo.mqtt.simple;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBLogging {
    private DBLogging(){}
    private static final String QUERY = "INSERT INTO sensorTest(projet, line, mac, name, val) values(?,?,?,?,?)";  
    private static final Logger LOGGER = LoggerFactory.getLogger(DBLogging.class.getName());

    public static void writeToDb(String topic, MqttMessage mqttMessage ) {
        InputStream resourcesInputStream = DBLogging.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();

     	String[] recievedTopicAsSrings = topic.split("/");
	    //String[] recievedValuesAsSrings = new String(new String(mqttMessage.getPayload())).split("/");
	    String project = recievedTopicAsSrings[0];
	    int line = Integer.parseInt(recievedTopicAsSrings[1]);
	    String mac = recievedTopicAsSrings[2];
	    String name = recievedTopicAsSrings[3];
	    double value = Double.parseDouble(new String(mqttMessage.getPayload()));
        
        
        try {
            properties.load(resourcesInputStream);
        } catch (IOException e) {
            LOGGER.warn("Cannot read property ", e);
        }
        String dataBaseName = properties.getProperty("db.name");
        String dataBaseURL = properties.getProperty("db.url");
        String dataUserName = properties.getProperty("db.username");
        String dataUserPass = properties.getProperty("db.password");
        String dataBaseConnParam = properties.getProperty("db.connparams");
        String dbUrl = dataBaseURL + dataBaseName + dataBaseConnParam;

        try(Connection connection = DriverManager.getConnection(dbUrl, dataUserName, dataUserPass);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        ) {
            preparedStatement.setString(1, project);
//            preparedStatement.setDate(2, new java.sql.Date(timestamp));
            preparedStatement.setInt(2,line);
            preparedStatement.setString(3, mac);
            preparedStatement.setString(3, name);
            preparedStatement.setDouble(5, value);
            preparedStatement.execute();
        } catch (SQLException s) {
                LOGGER.warn("statement was not executed",s);
        }
        }
}

