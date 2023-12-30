# EconomyAPI-PNX
EconomyAPI plugin for PowerNukkitX

# Maven
add in your plugin project.
### Repository
```xml
    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
### Depedency
```xml
    <dependency>
	    <groupId>com.github.angga7togk</groupId>
	    <artifactId>EconomyAPi-PNX</artifactId>
	    <version>Tag</version>
	</dependency>
```

# Method
```java
    EconomyDB.myMoney(player);
    EconomyDB.myMoney(playerName);

    EconomyDB.setMoney(player, 1000);
    EconomyDB.setMoney(playerName, 1000);

    EconomyDB.addMoney(player, 1000);
    EconomyDB.addMoney(playerName, 1000);

    EconomyDB.reduceMoney(player, 1000);
    EconomyDB.reduceMoney(playerName, 1000);
```
