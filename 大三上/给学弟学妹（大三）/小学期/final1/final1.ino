#include <IRremote.h>
#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
#include <dht11.h>
#include <SoftwareSerial.h>

//定义接口
#define BT1 0
#define BT2 1
#define DHT11PIN 2
#define recvPin 3
#define windowPin 5
#define doorPin 6
#define PIR 7
#define ledPin 10
#define PIN_SPEAKER 11
#define motorPin 12

dht11 DHT11;//温湿传感器对象
int light;
int lightPrint;
int chk;
int isHome = 1;//1在家，0不在
//红外地址
long KEY_CODE_ZERO = 0xFF9867;
long KEY_CODE_ONE = 0xFFA25D;
long KEY_CODE_TWO = 0xFF629D;
long KEY_CODE_THREE = 0xFFE21D;
long KEY_CODE_FOUR = 0xFF22DD;
long KEY_CODE_FIVE = 0xFF02FD;
long KEY_CODE_LEFT = 0xFF10EF;
long KEY_CODE_RIGHT = 0xFF5AA5;
long KEY_CODE_STAR = 0xFF6897;
long KEY_CODE_SHARP = 0xFFB04F;
//多任务计时器
unsigned long previousMillis = 0;
const long period = 1000;


LiquidCrystal_I2C lcd(0x27,16,2); // set the LCD address to 0x27 for a 16 chars and 2 line display
IRrecv irrecv(recvPin);//红外接收器
decode_results results;//set the remote
SoftwareSerial BTserial(BT1,BT2);//蓝牙串口

void setup()
{
  pinMode(windowPin,INPUT); 
  pinMode(doorPin,INPUT);
  pinMode(ledPin, OUTPUT);
  pinMode(PIN_SPEAKER, OUTPUT);
  pinMode(PIR, INPUT);
  irrecv.enableIRIn(); // 初始化红外接收器
  lcd.init(); // initialize the lcd 
  lcd.backlight(); //Open the backlight
  Serial.begin(9600);
  BTserial.begin(9600); // HC-06 默认波特率 9600
}
void loop()
{
  unsigned long currentMillis = millis();
  int chk = DHT11.read(DHT11PIN);

  //遥控
  if (irrecv.decode(&results)) {
    Serial.println(results.value, HEX);//以16进制换行输出接收代码
    Serial.println();//为了便于观看输出结果增加一个空行
    irrecv.resume(); // 接收下一个值
  }
  //按键事件
    //1-5调节灯光亮度
  if (results.value == KEY_CODE_ZERO ) {
    light = 0;
    analogWrite(ledPin,light);
  }
  
  if (results.value == KEY_CODE_ONE ) {
    light = 51;
    analogWrite(ledPin,light);
  }

  if (results.value == KEY_CODE_TWO ) {
    light = 102;
    analogWrite(ledPin,light);
  }

  if (results.value == KEY_CODE_THREE ) {
    light = 153;
    analogWrite(ledPin,light);
  }

  if (results.value == KEY_CODE_FOUR ) {
    light = 204;
    analogWrite(ledPin,light);
  }

  if (results.value == KEY_CODE_FIVE ) {
    light = 255;
    analogWrite(ledPin,light);
    
  }
  //左右控制灯的开关
  if (results.value == KEY_CODE_LEFT ) {
    light = 255;
    analogWrite(ledPin,light);
  }

  if (results.value == KEY_CODE_RIGHT ) {
    light = 0;
    analogWrite(ledPin,light);
  }

  //温度过高激活马达
  if ((float)DHT11.temperature >= 31.0) {
    digitalWrite(motorPin, HIGH);
  }
  else {
    digitalWrite(motorPin, LOW);
  }
  //星号井号设置是否在家
  if (results.value == KEY_CODE_STAR ) {
    isHome = 1;
  }
  if (results.value == KEY_CODE_SHARP ) {
    isHome = 0;
  }

  //蓝牙
  if ( BTserial.available()>0 ) {
    char BTserialData =  BTserial.read();
    Serial.println( BTserialData ); 
    if(BTserialData == '1'){
      light = 51;
      analogWrite(ledPin,light);
      Serial.println("light grade: 1");
    }
    else if (BTserialData == '2'){
      light = 102;
      analogWrite(ledPin,light);
      Serial.println("light grade: 2");
    }
    else if (BTserialData == '3'){
      light = 153;
      analogWrite(ledPin,light);
      Serial.println("light grade: 3");
    }
    else if (BTserialData == '4'){
      light = 204;
      analogWrite(ledPin,light);
      Serial.println("light grade: 4");
    }
    else if (BTserialData == '5'){
      light = 255;
      analogWrite(ledPin,light);
      Serial.println("light grade: 5");
    }
    else if (BTserialData == '0'){
      light = 0;
      analogWrite(ledPin,light);
      Serial.println("light grade: 0");
    }
    else if (BTserialData == '6'){
      Serial.print("Light grade: ");
      lightPrint = light / 51;
      Serial.print(lightPrint);
      Serial.println();
      Serial.print("isAtHome: ");
      if(isHome == 1){
        Serial.print("yes");
      }else{
        Serial.print("no");
      }
      Serial.println();
      Serial.print("Humi: ");
      Serial.print((float)DHT11.humidity, 2);
      Serial.print(" %");
      Serial.println();
      Serial.print("Temp: ");
      Serial.print((float)DHT11.temperature, 2);
      Serial.print(" C");
      Serial.println();
      if (digitalRead(windowPin)) {
        Serial.print("window : on");
      }
      else {
        Serial.print("window : off");
      }
      Serial.println();
      if (digitalRead(doorPin)) {
        Serial.print("door :   on");
      }
      else {
        Serial.print("door :   off");
      }
      Serial.println();
    }
    
  }
  
  if(currentMillis-previousMillis >= 3*period){
    previousMillis = currentMillis;
    //亮度，是否在家
    lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("Light:");
    lightPrint = light / 51;
    lcd.print(lightPrint);
    lcd.setCursor(0,1);
    lcd.print("isAtHome: ");
    if(isHome == 1){
      lcd.print("yes");
    }else{
      lcd.print("no");
    }
  }
  else if(currentMillis - previousMillis >= 2*period)
  {
    //门窗检测显示
    lcd.clear();
    if (digitalRead(windowPin)) {
      lcd.print("window : on");
    }
    else {
      lcd.print("window : off");
    }
    if (digitalRead(doorPin)) {
      lcd.setCursor(0,1);
      lcd.print("door :   on");
    }
    else {
      lcd.setCursor(0,1);
      lcd.print("door :   off");
    }
  }
  else if(currentMillis - previousMillis >= period){
    //温湿度检测显示
    lcd.clear();
    lcd.print("Humi: ");
    lcd.print((float)DHT11.humidity, 2);
    lcd.print(" %");
    lcd.setCursor(0,1);
    lcd.print("Temp: ");
    lcd.print((float)DHT11.temperature, 2);
    lcd.print(" C");
  }

  //报警
  if (digitalRead(PIR)&&(isHome == 0)&&((digitalRead(doorPin))||digitalRead(windowPin))) {
    digitalWrite(PIN_SPEAKER,HIGH);
  }
  else {
    digitalWrite(PIN_SPEAKER,LOW);
  }
}

//温度计算
double dewPoint(double celsius, double humidity)
{
        double A0= 373.15/(273.15 + celsius);
        double SUM = -7.90298 * (A0-1);
        SUM += 5.02808 * log10(A0);
        SUM += -1.3816e-7 * (pow(10, (11.344*(1-1/A0)))-1) ;
        SUM += 8.1328e-3 * (pow(10,(-3.49149*(A0-1)))-1) ;
        SUM += log10(1013.246);
        double VP = pow(10, SUM-3) * humidity;
        double T = log(VP/0.61078);   // temp var
        return (241.88 * T) / (17.558-T);
}
