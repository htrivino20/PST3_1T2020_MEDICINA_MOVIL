
#include"LibreriaSergio.h"


//Bandera
int a=0;

void setup() {
  // put your setup code here, to run once:
pinMode(ENA,OUTPUT);
pinMode(IN4,OUTPUT);
pinMode(IN3,OUTPUT);
pinMode(IN2,OUTPUT);
pinMode(IN1,OUTPUT);
pinMode(ENB,OUTPUT);
}

void loop() {
  delay(4000);
if ( a==0){
Ruta_ida_2_4(2500,400,250);
delay(3000);
Ruta_regreso_2_4(250,400,2500);
   a=1;
  
  }
}
