
// MotorB
int motorBPin3 = 27; 
int motorBPin4 = 26; 
int enable1PinB = 14;

// Setting PWM properties
const int freq = 30000;
const int pwmChannel = 0;
const int resolution = 8;
int dutyCycle = 200;

void setup() {
  // put your setup code here, to run once:
   pinMode(motorBPin3, OUTPUT);
  pinMode(motorBPin4, OUTPUT);
  pinMode(enable1PinB, OUTPUT);
 
  // configure LED PWM functionalitites
  ledcSetup(pwmChannel, freq, resolution);

  // attach the channel to the GPIO to be controlled
  ledcAttachPin(enable1PinB, pwmChannel);

  Serial.begin(115200);

   //Testting
   Serial.print("Testing DC Motor...");



}

void loop() {
  // put your main code here, to run repeatedly:
   // Move the DC motor forward at maximum speed
  Serial.println("Moving Forward");
  digitalWrite(motorBPin3, LOW);
  digitalWrite(motorBPin4, HIGH); 
  delay(2000);

  // Stop the DC motor
  Serial.println("Motor stopped");
  digitalWrite(motorBPin3, LOW);
  digitalWrite(motorBPin4, LOW);
  delay(1000);

  // Move DC motor backwards at maximum speed
  Serial.println("Moving Backwards");
  digitalWrite(motorBPin3, HIGH);
  digitalWrite(motorBPin4, LOW); 
  delay(2000);

   // Stop the DC motor
  Serial.println("Motor stopped");
  digitalWrite(motorBPin3, LOW);
  digitalWrite(motorBPin4, LOW);
  delay(1000);

  // Move DC motor forward with increasing speed
  digitalWrite(motorBPin3, HIGH);
  digitalWrite(motorBPin4, LOW);
  while (dutyCycle <= 255){
    ledcWrite(pwmChannel, dutyCycle);   
    Serial.print("Forward with duty cycle: ");
    Serial.println(dutyCycle);
    dutyCycle = dutyCycle + 5;
    delay(500);
  }
  dutyCycle = 200;

}
