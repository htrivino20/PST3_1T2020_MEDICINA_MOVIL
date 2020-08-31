
//MOTOR A
int ENA =10;
int IN1=9;
int IN2=8;

// MOTOR B

int ENB =5;
int IN3=6;
int IN4=7;

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


void ATRAS(int tiempo_ejecucion){
//MOTOR A
 digitalWrite(IN1,HIGH);
 digitalWrite(IN2,LOW);
 analogWrite(ENA,255);

//MOTOR B
 
 digitalWrite(IN3,HIGH);
 digitalWrite(IN4,LOW);
 analogWrite(ENB,255);
 delay(tiempo_ejecucion);}


void ADELANTE(int tiempo_ejecucion){
  //MOTOR A
 digitalWrite(IN1,LOW);
 digitalWrite(IN2,HIGH);
 analogWrite(ENA,255); // rueda derecha

//MOTOR B
 
 digitalWrite(IN3,LOW);
 digitalWrite(IN4,HIGH);
 analogWrite(ENB,255);
 delay(tiempo_ejecucion);}// rueda izquierda



void IZQUIERDA(int tiempo_ejecucion){
 //MOTOR A
 digitalWrite(IN1,LOW);
 digitalWrite(IN2,HIGH);
 analogWrite(ENA,255); //velocidad del motor es decir va a girar a 130 

//MOTOR B
 
 digitalWrite(IN3,LOW);
 digitalWrite(IN4,HIGH);
 analogWrite(ENB,0);
 delay(tiempo_ejecucion);}


 void DERECHA(int tiempo_ejecucion){
//MOTOR A
 digitalWrite(IN1,LOW);
 digitalWrite(IN2,HIGH);
 analogWrite(ENA,0); //velocidad del motor es decir va a girar a 130 

//MOTOR B
 
 digitalWrite(IN3,LOW);
 digitalWrite(IN4,HIGH);
 analogWrite(ENB,255);
 delay(tiempo_ejecucion);}
  


void PARAR(int tiempo_ejecucion){
  //MOTOR A
 digitalWrite(IN1,LOW);
 digitalWrite(IN2,LOW);
 analogWrite(ENA,0); //velocidad del motor es decir va a girar a 130 

//MOTOR B
 
 digitalWrite(IN3,LOW);
 digitalWrite(IN4,LOW);
 analogWrite(ENB,0);
 delay(tiempo_ejecucion);}

void VOLVER_2_4(int tiempo_ejecucion){ // giro invertido
 
  //MOTOR A
 digitalWrite(IN1,HIGH);
 digitalWrite(IN2,LOW);
 analogWrite(ENA,255); //velocidad del motor es decir va a girar a 130 

//MOTOR B
 
 digitalWrite(IN3,LOW);
 digitalWrite(IN4,HIGH);
 analogWrite(ENB,0);
 delay(tiempo_ejecucion);}
 
void VOLVER_1_3(int tiempo_ejecucion){ // giro invertido 
 
  //MOTOR A
 digitalWrite(IN1,HIGH);
 digitalWrite(IN2,LOW);
 analogWrite(ENA,0); //velocidad del motor es decir va a girar a 130 

//MOTOR B
 
 digitalWrite(IN3,HIGH);
 digitalWrite(IN4,LOW);
 analogWrite(ENB,255);
 delay(tiempo_ejecucion);}

 /////////////////////////////////////////////////////////////////////// RUTA 2,4 /////////////////////////////////////////////////////////////////////////////

 void Ruta_ida_2_4(int t1, int t2, int t3){
        //ida
      ADELANTE(t1);
      PARAR(1000);
      DERECHA(t2);
      PARAR(1000);
      ADELANTE(t3);
      PARAR(1000);
    
    }
    
void Ruta_regreso_2_4(int t4, int t5, int t6){
   //regreso
      ATRAS(t4);
      PARAR(1000);
      VOLVER_2_4(t5);
      PARAR(1000);
      ADELANTE(t6);
      PARAR(1000);
  
  }

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////// RUTA 1,3 /////////////////////////////////////////////////////////////////////////////

 void Ruta_ida_1_3(int t1, int t2, int t3){
        //ida
      ADELANTE(t1);
      PARAR(1000);
      IZQUIERDA(t2);
      PARAR(1000);
      ADELANTE(t3);
      PARAR(1000);
    
    }
    
void Ruta_regreso_1_3(int t4, int t5, int t6){
   //regreso
      ATRAS(t4);
      PARAR(1000);
      VOLVER_1_3(t5);
      PARAR(1000);
      ADELANTE(t6);
      PARAR(1000);
  
  }

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void loop() {
  delay(4000);
if ( a==0){
Ruta_ida_2_4(2500,400,250);
delay(3000);
Ruta_regreso_2_4(250,400,2500);
   a=1;
  
  }
}
