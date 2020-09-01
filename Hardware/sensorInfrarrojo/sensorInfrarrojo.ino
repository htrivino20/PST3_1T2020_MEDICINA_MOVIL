//1. Include WiFi.h
#include <WiFi.h>
#include <WiFiMulti.h>

//2. Include Firebase ESP32 library (this library)
#include <FirebaseESP32.h>

//3. Declare the Firebase Data object in the global scope
FirebaseData firebaseData;
FirebaseJson json;
WiFiMulti wifiMulti;

#define FIREBASE_HOST "https://medicinamovil-6446c.firebaseio.com/"
#define FIREBASE_AUTH "6P60rzRsYhu3XaPZfIfRWGPtAuWa21vzqLN5KHKz"
#define WIFI_NAME "LAN"
#define WIFI_KEY "PagaTuInternet123"

// MotorB
int motorBPin3 = 27; 
int motorBPin4 = 26; 
int enable1PinB = 14;

// Setting PWM properties
const int freq = 30000;
const int pwmChannel = 0;
const int resolution = 8;
int dutyCycle = 200;

const int sensorPin = 25;
 
void setup() {
 
  Serial.begin(115200);   //iniciar puerto serie
  delay(100);
  Serial.println("Conectando a Wifi");
  wifiMulti.addAP(WIFI_NAME,WIFI_KEY);  //conectarse a la red
  pinMode(sensorPin , INPUT);  //definir pin como entrada
  //pinMode(LED_BUILTIN, OUTPUT);
  while(wifiMulti.run() != WL_CONNECTED){
     Serial.print(".");
  }

  Serial.println();
  Serial.print("Wifi Conectado");
  Serial.println("Direccion IP: ");
  //Serial.println(WiFi.localIP());

  //Manejar FIrebase
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
 
  //Set database read timeout to 1 minute (max 15 minutes)
  Firebase.setReadTimeout(firebaseData, 1000 * 60);
  //tiny, small, medium, large and unlimited.
  //Size and its write timeout e.g. tiny (1s), small (10s), medium (30s) and large (60s).
  Firebase.setwriteSizeLimit(firebaseData, "tiny");
  Serial.println("------------------------------------");
  Serial.println("Connected...");
  
  pinMode(motorBPin3, OUTPUT);
  pinMode(motorBPin4, OUTPUT);
  pinMode(enable1PinB, OUTPUT);
     Serial.print("Testing DC Motor...");

} 

void loop(){
  int value = 0;
  value = digitalRead(sensorPin);  //lectura digital de pin
   
  json.set("/valor", value);
  //updateNote, actualiza el dato en la base de datos
  Firebase.updateNode(firebaseData,"/Esp32/SensorInfrarrojo",json);

  //Serial.println("Informacion del usuario");
  //Serial.println(Firebase.getInt(firebaseData,"Solicitud/valor"));
  Serial.println("Estado del carro");
  Firebase.getInt(firebaseData,"Solicitud/valor");
  Serial.println(firebaseData.intData());  
 
//  Serial.println(solicitud);
  if(value == 0 && firebaseData.intData() == 1){
    Serial.println("Carro en movimiento");
     // Move the DC motor forward at maximum speed
    Serial.println("Moving Forward");
    digitalWrite(motorBPin3, LOW);
    digitalWrite(motorBPin4, HIGH);
      delay(2000);

    
  }else{
    Serial.println("No hay insumo medico,El carro no se mueve");
    Serial.println("Motor stopped");
    digitalWrite(motorBPin3, LOW);
    digitalWrite(motorBPin4, LOW);
  }

 
   delay(1000);    

}
