//variables para el sensor
#define Pecho 6
#define Ptrig 7
long duracion, distancia;  

//variables para el parlante
 int altavoz = 2;
  float dom=554.37;
  float re=587.33;
  float mi=659.26;
  float fa=698.46;
  float sol=783.99;
  float la=880;
  float si=987.77;
  float doM=1046.50;
  int UnSegundo = 1000;
void setup() {
  Serial.begin(9600);
  pinMode(2, OUTPUT);
  pinMode(Pecho, INPUT);     // define el pin 6 como entrada (echo)
  pinMode(Ptrig, OUTPUT);    // define el pin 7 como salida  (triger)
  pinMode(13, 1);            // Define el pin 13 como salida
  pinMode(4, 1); 
}

void loop() {
 
  digitalWrite(Ptrig, LOW);
  delayMicroseconds(2);
  digitalWrite(Ptrig, HIGH);   // genera el pulso de triger por 10ms
  delayMicroseconds(10);
  digitalWrite(Ptrig, LOW);
  
  duracion = pulseIn(Pecho, HIGH);
  distancia = (duracion/2) / 29;            // calcula la distancia en centimetros
   
  if(distancia >= 1 && distancia <= 8){
    Serial.println(distancia);           // envia el valor de la distancia por el puerto serial 
    digitalWrite(13, 1);                     // en alto el pin 13 si la distancia es menor a 10cm
    tonoAlarma(75);
    digitalWrite(13, 0);  

    digitalWrite(4, 0); 
 
  }else if(distancia >= 1 && distancia <= 20){
    Serial.println(distancia);           // envia el valor de la distancia por el puerto serial 
    digitalWrite(13, 1);  
    tonoAlarma(150);
    digitalWrite(13, 0);  
    //digitalWrite(13, 1);                     // en alto el pin 13 si la distancia es menor a 10cm
    digitalWrite(4, 0); 
     
  }else if(distancia >= 1 && distancia <= 30){
    Serial.println(distancia);           // envia el valor de la distancia por el puerto serial 
    tonoAlarma(300);

    //digitalWrite(13, 1);                     // en alto el pin 13 si la distancia es menor a 10cm
    digitalWrite(4, 0); 
     
  }else if (distancia > 0 && distancia <= 100){  // si la distancia es mayor a 500cm o menor a 0cm 
    Serial.println(distancia);           // envia el valor de la distancia por el puerto serial 
    //digitalWrite(13, 0);               // en bajo el pin 13
    digitalWrite(4, 0);                 // no mide nada
  }
  delay(400);                                // espera 400ms para que se logre ver la distancia en la consola
}


void tonoAlarma(int tiempo){
  tone(altavoz,dom,tiempo);
  delay(tiempo);
  tone(altavoz,fa,tiempo);
  delay(tiempo);
  tone(altavoz,mi,tiempo);
  delay(tiempo);
}
