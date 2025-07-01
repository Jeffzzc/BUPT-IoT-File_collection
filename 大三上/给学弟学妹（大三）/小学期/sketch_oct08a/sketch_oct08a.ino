//Bluetooth define
#include <SoftwareSerial.h>
#define START 5
int control = 0;

//track trace define
#define STOP      0 //stop
#define RUN       1 //forward
#define BACK      2 //backword
#define LEFT      3 //turn left
#define RIGHT     4 //turn right
#define a1 A5 //left motor 1
#define a2 A6 //left motor 2
#define b1 A7 //right motor 1
#define b2 A8 //right motor 2
#define sensor1 A4  //sensor
#define sensor2 A3
#define sensor3 A2
#define sensor4 A1
#define sensor5 A0
#define leftPWM 9 //L298N left EN
#define rightPWM 10 //L298N right EN
int d[5] = {0, 0, 0, 0, 0};
int count = 0;// count location 

//colour define
#include <TimerOne.h> 
#define S0 6 // the combination of S0 and S1 determine the factor of output signal
#define S1 5 
#define S2 4 // the combination of S2 and S3 determine which colour of light can path the filter
#define S3 3
#define OUT 2 
#define MIN_THR 100
#define WHITE_THR 240
#define MAX_DELTA 200
float g_scale_factor[3];
int g_count = 0; 
int g_pulse_number_array[3]; 
int g_flag = 0; 
int compare[3];
int numMin;
int numMax;
int delta;
int H;

//LCD define
#include <Wire.h>
#include <LiquidCrystal_I2C.h> 
LiquidCrystal_I2C lcd(0x27, 16, 2);//set the location of LCD1602

//define the temperature and humidity 
#include <DHT.h>
#define DHTPIN 11
#define DHTTYPE DHT11//type define
DHT dht(DHTPIN, DHTTYPE); //initialize

//bluetooth data
char val;

void Track_Init()
{
  //initialize the track module
  pinMode(sensor1, INPUT);
  pinMode(sensor2, INPUT);
  pinMode(sensor3, INPUT);
  pinMode(sensor4, INPUT);
  pinMode(sensor5, INPUT);
}

void Motor_Init()
{
  //initialize the motor module
  pinMode(a1, OUTPUT);
  pinMode(a2, OUTPUT);
  pinMode(b1, OUTPUT);
  pinMode(b2, OUTPUT);
  pinMode(leftPWM, OUTPUT);
  pinMode(rightPWM, OUTPUT);
}

void TSC_Init()
{
  // initialize TSC3200
  pinMode(S0, OUTPUT);
  pinMode(S1, OUTPUT);
  pinMode(S2, OUTPUT);
  pinMode(S3, OUTPUT);
  pinMode(OUT, INPUT);
  // set TCS3002D the factor as 2%
  digitalWrite(S0, LOW);
  digitalWrite(S1, HIGH);
}

// determine which colour of light can pass through the filter
void TSC_FilterColor(int Level01, int Level02)
{
  if (Level01 != 0)
    Level01 = HIGH;
  if (Level02 != 0)
    Level02 = HIGH;
  digitalWrite(S2, Level01);
  digitalWrite(S3, Level02);
}

// determine the flag
void TSC_WB(int Level0, int Level1)
{
  g_count = 0; // initialize
  g_flag ++; // count the flag
  TSC_FilterColor(Level0, Level1); // set the mode
  Timer1.setPeriod(1000000); // set the impulse 
}

// define the interrput function
void TSC_Callback()
{
  switch (g_flag)
  {
    case 0:
      Serial.println("--->WB Start Filter");
      Serial3.println("--->WB Start Filter");
      TSC_WB(LOW, LOW); //Red light pass the filter
      break;
    case 1:
      Serial.print("->Frequency R=");
      Serial3.print("->Frequency R=");
      Serial.println(g_count);
      Serial3.println(g_count);
      g_pulse_number_array[0] = g_count;
      TSC_WB(HIGH, HIGH); // Green light pass the filter
      break;
    case 2:
      Serial.print("->Frequency G=");
      Serial3.print("->Frequency G=");
      Serial.println(g_count); 
      Serial3.println(g_count); 
      g_pulse_number_array[1] = g_count;
      TSC_WB(LOW, HIGH); // Blue light pass the filter
      break;
    case 3:
      Serial.print("->Frequency B=");
      Serial3.print("->Frequency B=");
      Serial.println(g_count);
      Serial3.println(g_count);
      Serial.println("--->WB End Filter");
      Serial3.println("--->WB End Filter");
      g_pulse_number_array[2] = g_count;
      TSC_WB(HIGH, LOW); // no filter
      break;
    default:
      g_count = 0; //initialize
      break;
  }
}

//count the impulse signal output of TCS3200
void TSC_Count()
{
  g_count ++ ;
}

void lcd_Init(){
  lcd.init();//inilialize LCD
  lcd.backlight(); // set background for LCD
}

void setup_Color()
{
  Serial.println("Colour setup start--");
  Serial3.println("Colour setup start--");
  delay(2000);
  TSC_Init();
  Timer1.initialize(); // defaulte is 1s
  Timer1.attachInterrupt(TSC_Callback); // set the interrupt of the clock 

  attachInterrupt(0, TSC_Count, RISING);
  delay(4000); 
  //count the factor
  g_scale_factor[0] = 255.0 / g_pulse_number_array[0]; // red factor
  g_scale_factor[1] = 255.0 / g_pulse_number_array[1] ; // green factor
  g_scale_factor[2] = 255.0 / g_pulse_number_array[2] ; // blue factor
  Serial.println("after white banlance:");
  Serial3.println("after white banlance:");
  Serial.println(g_scale_factor[0], DEC);
  Serial3.println(g_scale_factor[0], DEC);
  Serial.println(g_scale_factor[1], DEC);
  Serial3.println(g_scale_factor[1], DEC);
  Serial.println(g_scale_factor[2], DEC);
  Serial3.println(g_scale_factor[2], DEC);
}

void tracing()
{
  d[0] = digitalRead(sensor1);
  d[1] = digitalRead(sensor2);
  d[2] = digitalRead(sensor3);
  d[3] = digitalRead(sensor4);
  d[4] = digitalRead(sensor5);
  if (d [0]  == 0  && d [1] == 0 && d [3] == 0 && d [4] == 0) //左右都没有检测到黑线
  {
    Work(RUN, 50, 50);
  }
  else if (d [0]  == 0  && d [1] == 0 && d [3] == 1 && d [4] == 0) //小右转
  {
    Work(RUN, 90, 40);
  }
  else if (d [0]  == 0  && d [1] == 1 && d [3] == 0 && d [4] == 0) //小左转
  {
    Work(RUN, 40, 90);
  }
  else if (d [0]  == 0  && d [1] == 0 && d [3] == 0 && d [4] == 1) //大右转
  {
    Work(RIGHT, 150, 80);
  }
  else if (d [0]  == 1  && d [1] == 0 && d [3] == 0 && d [4] == 0) //大左转
  {
    Work(LEFT, 80, 150);
  }
  else if (d [0]  == 0  && d [3] == 1 && d [4] == 1) //右急转弯
  {
    Work(RIGHT, 200, 180);
  }
  else if (d [0]  == 1  && d [1] == 1 && d [4] == 0) //左急转弯
  {
    Work(LEFT, 180, 200);
  }
  else if (d [0]  == 1  && d [1] == 1 && d [3] == 1 && d [4] == 1) //左右都检测到黑线是停止
  {
    if (count == 0) {
      Serial.println(count);
      Serial3.println(count);
      count++;
      Temperature();
      delay(1000);
      Work(RUN, 70, 70);
      delay(400);
    }
    else if (count > 0 && count <= 4) {
      Work(STOP, 0, 0);  //如果到达横线处，就停车
      Serial.println(count);
      Serial3.println(count);
      Temperature();
      Color();
      count++;
      Work(RUN, 70, 70);
      delay(400);
    }
    else if ( count == 5) {
      Work(STOP, 0, 0);  //如果到达重点横线处，就停车
      delay(5000);
      count = 0;
      Serial.println(count);
      Serial3.println(count);
    }
  }
  else  //除了左右转，就只剩下直走
    Work(RUN, 50, 50);
}

void Color() {
  g_flag = 0;
  delay(5000);
  //Print the RGB Value
  for (int i = 0; i < 3; i++) {
    Serial.println(int(g_pulse_number_array[i] * g_scale_factor[i]));
    Serial3.println(int(g_pulse_number_array[i] * g_scale_factor[i]));
    compare[i] = int(g_pulse_number_array[i] * g_scale_factor[i]);
  }
  if (compare[0] > compare[1])
  {
    numMax = compare[0];
  }
  else
  {
    numMax = compare[1];
  }
  if (compare[2] > numMax)
  {
    numMax = compare[2];
  }

  if (compare[0] < compare[1])
  {
    numMin = compare[0];
  }
  else
  {
    numMin = compare[1];
  }
  if (compare[2] < numMin)
  {
    numMin = compare[2];
  }

  if (numMin <= 110 && numMax < 70) {
    Serial.println("Empty");
    Serial3.println("Empty");
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Empty");
    lcd.print("  ");
    lcd.print(count);

  } else if (numMin >= WHITE_THR) {
    Serial.println("White");
    Serial3.println("White");
    lcd.clear();
    lcd.setCursor(0, 0);            
    lcd.print("White");    
    lcd.print("  ");
    lcd.print(count);

  } else {
    delta = numMax - numMin;
    if (numMax == compare[0]) {
      H = (compare[1] - compare[2]) * 60 / delta;
    } else if (numMax == compare[1]) {
      H = 120 + (compare[2] - compare[0]) * 60 / delta;
    } else {
      H = 240 + (compare[0] - compare[1]) * 60 / delta;
    }
    if (H < 0)
      H += 360;

    if (H < 30 || H > 300) {
      Serial.println("Red");
      Serial3.println("Red");
      lcd.clear();
      lcd.setCursor(0, 0);               
      lcd.print("Red");     
      lcd.print("  ");
      lcd.print(count);
    }

    else if (H >= 30 && H <= 90) {
      Serial.println("Yellow");
      Serial3.println("Yellow");
      lcd.clear();
      lcd.setCursor(0, 0);         
      lcd.print("Yellow");    
      lcd.print("  ");
      lcd.print(count);
    }

    else if (H > 90 && H <= 160) {
      Serial.println("Green");
      Serial3.println("Green");
      lcd.clear();
      lcd.setCursor(0, 0);        
      lcd.print("Green");  
      lcd.print("  ");
      lcd.print(count);
    }

    else if (H >= 200 && H <= 280) {
      Serial.println("Blue");
      Serial3.println("Blue");
      lcd.clear();
      lcd.setCursor(0, 0);       
      lcd.print("Blue");   
      lcd.print("  ");
      lcd.print(count);
    }
  }
}

void Work(int c, int value1, int value2)
{
  analogWrite(leftPWM, value1);  //set speed
  analogWrite(rightPWM, value2);
  switch (c)
  {
    case RUN:
      Serial.println("RUN"); //forward
      digitalWrite(a1, HIGH);
      digitalWrite(a2, LOW);
      digitalWrite(b1, HIGH);
      digitalWrite(b2, LOW);
      break;
    case BACK:
      Serial.println("BACK"); //backword
      digitalWrite(a1, LOW);
      digitalWrite(a2, HIGH);
      digitalWrite(b1, LOW);
      digitalWrite(b2, HIGH);
      break;
    case RIGHT:
      Serial.println("RIGHT"); //turn right
      digitalWrite(a1, HIGH);
      digitalWrite(a2, LOW);
      digitalWrite(b1, LOW);
      digitalWrite(b2, HIGH);
      break;
    case LEFT:
      Serial.println("LEFT"); //turn left
      digitalWrite(a1, LOW);
      digitalWrite(a2, HIGH);
      digitalWrite(b1, HIGH);
      digitalWrite(b2, LOW);
      break;
    default:
      Serial.println("STOP"); //stop
      digitalWrite(a1, LOW);
      digitalWrite(a2, LOW);
      digitalWrite(b1, LOW);
      digitalWrite(b2, LOW);
  }
}

void Temperature() {
  delay(2000);//延时
  float h = dht.readHumidity();//read humidity
  float t = dht.readTemperature();//read temperature
  String myreadout1 = "Humidity ";
  String myreadout2 = "Temperatrue ";
  Serial.print("Humidity：");
  Serial3.print("Humidity：");
  Serial.print(h);
  Serial3.print(h);
  Serial.println("%");
  Serial3.println("%");
  Serial.print("Temperatrue：");
  Serial3.print("Temperatrue：");
  Serial.print(t);
  Serial3.print(t);
  Serial.println("℃");
  Serial3.println("℃");
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print(myreadout1 += h);
  lcd.setCursor(0, 1);
  lcd.print(myreadout2 += t);
}

void setup()
{
  Serial.begin(9600);  //COM9
  Serial3.begin(9600); //COM12
  Track_Init(); //initialize the track module
  Motor_Init(); //initialize the motor module
  setup_Color();
  lcd_Init();
  dht.begin();//DHT start working 
  Temperature();
}


void loop()
{
  tracing();
}
