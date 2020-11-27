package com.mapr.demo.createFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class FileCreator {
	private static String EXTENSION = ".ttb";
	private String file;
	private String dossier;
	
	public static void main(String[] args) {
		
		FileCreator test = new FileCreator("thothomas", "/tmp/");
		test.addObject();
		

		
	}
	
	public FileCreator(String file, String dossier) {
		this.file = file;
		this.dossier = dossier;
	}
	
	private void addObject() {
		
		Date aujourdhui = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyyMMddHHmmss");
		File file = new File(this.getDossier() + 
				this.getFile() + 
				"_" + 
				formater.format(aujourdhui) +
				EXTENSION);
		
		if(file.exists()) {
			System.out.println("on ne fait rien le fichier existe déja");
		}else {
			try {
				file.createNewFile();
				System.out. println("et là on crée le fichier");
			}catch (Exception e) {
				System.out.println("message catch " + e.getMessage());
			}
			
		}
	}
	
	public String getFile() {
		return this.file;
	}
	
	public String getDossier() {
		return this.dossier;
	}

}
