# System Monitoring for Mushroom based on NodeMCU and Android

This is documentation for a project internship in PT. Mitra Tani Damarayu. 
<br>Project by <b>Riyad Febrian and Duwi Hariyanto</b>
<br>

<p align="center">
  <img width="460" height="300" src="https://github.com/riyadfebrian/system-monitoring-for-mushroom-based-on-nodemcu-android/blob/master/image/system.jpg">
</p>

<p align="center">
  <img width="360" height="640" src="https://github.com/riyadfebrian/system-monitoring-for-mushroom-based-on-nodemcu-android/blob/master/image/screenshot.jpg">
</p>


## Getting Started

Our monitoring system based on microcontoller NodeMCU for acquisition sensor data. we use DHT22 for temperature & humidity and MQ135 for CO2 ppm (this sensor not recommended, but we use it to build a lowcost system). Follow these instruction to use this code :

### Prerequisites

What things you need :

```
1. NodeMCU (Install on board manager arduino IDE)
2. DHT22 
3. MQ135 
4. Thingspeak Channel
5. Android Smartphone
```

### Installing

For NodeMCU

```
Change <Channel-ID> and <API-KEY> in the code. Upload code .ino to NodeMCU
```

For Android

```
Change <Channel-ID> and <API-KEY> in the code. Build .APK file from the anroid project source
```

Enjoy :)

## Future Works

for the next feature, i'll add some actuator like water spray, fan, etc to make an autonomous mushroom cultivation 

## Thanks to
1. [Macroyau Thingspeak Library](https://github.com/MacroYau/ThingSpeakAndroid)
2. [HelloCharts Android Library](https://github.com/lecho/hellocharts-android)
3. [ESP8266 Library](https://github.com/esp8266/Arduino/tree/master/libraries/ESP8266WiFi)
4. [Knolleary PubSubClient](https://github.com/knolleary/pubsubclient)
5. [Adafruit DHT Library](https://github.com/adafruit/DHT-sensor-library)
6. [GeorgK MQ135 Library](https://github.com/GeorgK/MQ135)
