
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include "DHT.h"
#include "MQ135.h"


#define DHTPIN 4
#define DHTTYPE DHT22  

#define ANALOGPIN A0 
MQ135 gasSensor = MQ135(ANALOGPIN);

const char* ssid = "ibadurrahman";
const char* password = "sukabangetamakucing"; //cause i like a cat :v
const char* mqtt_server = "mqtt.thingspeak.com";
char* topic = "channels/<Channel-ID>/publish/<Write-API-Key>";


DHT dht(DHTPIN, DHTTYPE);
WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
char msg[50];
int value = 0;

void setup_wifi() {

  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");


}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Create a random client ID
    String clientId = "ESP8266Client-";
    clientId += String(random(0xffff), HEX);
    // Attempt to connect
    if (client.connect(clientId.c_str())) {
      Serial.println("connected");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void setup() {
  pinMode(BUILTIN_LED, OUTPUT);     // Initialize the BUILTIN_LED pin as an output
  Serial.begin(115200);
  dht.begin();
  float rzero = gasSensor.getRZero();
  delay(3000);
  Serial.print("MQ135 RZERO Calibration Value : ");
  Serial.println(rzero);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void loop() {

  static int counter = 0;

float  h = dht.readHumidity();
  // Read temperature as Celsius (the default)
float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
float f = dht.readTemperature(true);

    if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  String payload="field1=";
  payload+=h;
  payload+="&field2=";
  payload+=t;
  payload+="&field3=";
  gasSensor.getCorrectionFactor(t,h);
  payload+=gasSensor.getPPM();
  payload+="&status=MQTTPUBLISH";

  if (client.connected()) {

    Serial.print("Sending payload: ");
    Serial.println(payload);
    
    if (client.publish(topic, (char*) payload.c_str())) {
      Serial.println("Publish ok");
    }
    else {
      Serial.println("Publish failed");
    }
  
  } else {
      reconnect();
    }
  ++counter;
  delay(5000);
}
