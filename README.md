# projet de reception/envoi de message avec gestion des messages par retour
## Prerequisite

* Maven 3.3.x
* Mosquitto [Mosquitto](https://mosquitto.org/)
* Java 8
* Mariadb
* Arduino IDE
* NodeMCU
    
**1- Build the project with Apache Maven:**

This project is a simple Java application that runs a publisher and subscriber using the [Eclipse Paho library](https://eclipse.org/paho/).

```
$ mvn clean package
```

For convenience, the example programs project is set up so that the maven package target produces a single executable, 
`/mqtt-sample `, that includes all of the example programs and dependencies.

**2- Pourquoi ce projet:**

c'est un projet pour l'utilisation de mqtt en communication pour ne pas utilisé d'API
c'est aussi un projet de recherche 

***le principe de conception***

les IOT envoie à interval donnée un message comprennant 
*le type (sensor,request,output)
*le nom du projet qui est convert par IOT
*l'adresse mac de IOT
*le type de capteur 
*la payload (la valeur du capteur)

```
sensor/campingCar/1/EC:FA:BC:BC:C8:B6/tempPanneauSolaire 19.5 
```
chaque capteur va envoyer un topic différent après plusieurs essais 
c'est plus simple pour logger les messages directement

enfin à chaque reception de message on loggue en incluant les données sur 
une base de donnée sql (mariadb)


fork du projet  [tgrall/mqtt-sample-java](https://github.com/tgrall/mqtt-sample-java)
