#include<DHT.h>
#include <HCSR04.h>//调用HCSR04库
#include <Wire.h> //调用wire库
#include <LiquidCrystal_I2C.h> //调用LiquidCrystal_I2C库
#include <TimerOne.h>//颜色信号库

//RGB探测器引脚
#define OUTPin 2
#define S0Pin 3
#define S1Pin 4
#define S2Pin 5
#define S3Pin 6

//定义类型：DHT11及引脚
#define DHTTYPE DHT11 
#define DHTPIN 22
DHT dht(DHTPIN, DHTTYPE);  //创建一个dht类型的变量

//定义火焰传感器、光敏模块与蜂鸣器引脚
#define DigitalInPin 26    //DO 引脚： digital 26
#define BuzzerPin 27       //buzzer 引脚： digital 27
#define idrpin    A5

//定义光敏模块数据范围
#define low=0;
#define high=1023;

//定义颜色识别模组变量
#define MIN_THR 100
#define WHITE_THR 280
#define MAX_DELTA 200
int g_count = 0; // count the frequency
int g_array[3];  // store the RGB value
int g_flag = 0;  // filter of RGB queue
float g_SF[3];   // save the RGB scale factor
int compare[3];
int numMin;
int numMax;
int delta;
int H;

//设置LCD液晶屏幕以及超声波探测模块引脚
LiquidCrystal_I2C lcd(0x27,16,2); //设置LCD1_602A设备地址
UltraSonicDistanceSensor distanceSensor(24, 25);  // 初始化引脚，第一个参数为Trig引脚，第二个参数为Echo引脚。
int block_count = 0;



void setup()
{
  Serial.begin(9600);                      // 打开串口监视器
  Color_set();
  lcd.init();                            // 初始化LCD_1602A
  lcd.backlight();                       //设置LCD背景等亮
  dht.begin();                            //DHT开始工作
  
  Bee_set();
}
 
void loop()
{
  Guangmin_loop();
  Fire_loop();  
  Color_loop();
  Ultrasound();
  TandH();
}

void Bee_set()
{
  pinMode(DigitalInPin,INPUT);       //set the pins state
  pinMode(BuzzerPin,OUTPUT);
}

void Bee()
{
  digitalWrite(BuzzerPin,HIGH);
  pinMode (BuzzerPin,OUTPUT) ;
  tone(BuzzerPin,320,200);
}

void NoBee()
{
  digitalWrite(BuzzerPin, LOW);
  pinMode (BuzzerPin,INPUT) ;
  noTone(BuzzerPin);
}

void Guangmin_loop()
{
  int guangmin = analogRead(idrpin);   //读入光敏电阻产生的模拟信号（范围：0~1023）
  /*
  Serial.print("value= ");
  Serial.print(guangmin);
  */
  guangmin=map(guangmin,0,1023,255,0);  //map函数 将0~1023范围映射到255~0
  /*
  Serial.print("--");
  Serial.println(guangmin);
  */
  if(guangmin>=200)
    {
      Bee(); //判断有强光
    }
  else
    {
      NoBee();  //判断无事发生
    }
  delay(750);
}

void Fire_loop()
{
  boolean stat = digitalRead(DigitalInPin);
  if(stat == HIGH){
    NoBee();  //判断无事发生
    //Serial.println("Peace");
  }
  if(stat == LOW){
    Bee();  //判断有火灾
    //Serial.println("Fire");
  }
}

void Ultrasound()
{
  //Serial.println(distanceSensor.measureDistanceCm());//打印超声波测量距离，单位为厘米
  lcd.setCursor(0,0);            //第一行显示
  lcd.print(distanceSensor.measureDistanceCm());       //输出字符
  lcd.setCursor(5,0);
  lcd.print("cm");
  if(distanceSensor.measureDistanceCm() <= 10)
  {
    block_count++;  //距离判断后根据位置计数 仓库数会随着行进累加
    lcd.setCursor(15,0);
    lcd.print(block_count);
    if(block_count>=4){ //当仓库数大于4的时候，默认循迹完成，仓库数清零
      block_count=0;
    }
  }
}

void TandH()
{
  lcd.setCursor(0,1);            //第二行显示
  lcd.print(dht.readHumidity());       //输出字符 表示湿度
  lcd.setCursor(5,1);
  lcd.print("%");
  
  lcd.setCursor(7,1);
  lcd.print(dht.readTemperature());       //输出字符 表示温度
  lcd.setCursor(12,1);
  lcd.print(char(0xdf));
  lcd.setCursor(13,1);
  lcd.print("C");
}


void TSC_Init()
        {
        pinMode(S0Pin, OUTPUT);
        pinMode(S1Pin, OUTPUT);
        pinMode(S2Pin, OUTPUT);
        pinMode(S3Pin, OUTPUT);
        pinMode(OUTPin,INPUT);
        //OUTPUT Frequency Scaling 2%
        digitalWrite(S0Pin,LOW);
        digitalWrite(S1Pin,HIGH);
        }

//Select the filter color
void TSC_FilterColor(int Level01, int Level02)
{
  if(Level01 != 0)
  Level01= HIGH;
  if(Level02 != 0)
  Level02= HIGH;
  digitalWrite(S2Pin,Level01);
  digitalWrite(S3Pin,Level02);
}

//interrupt function, calculate TCS3200 output signal of pulse number
void TSC_Count(){g_count ++;}

void TSC_Callback()
{
  switch(g_flag)
  {
    case 0:
    Serial.println("->WB Start");
    TSC_WB(LOW, LOW);   //选择让红色光线通过滤波器的模式
    break;
    
    case 1:
    Serial.print("->Frequency R=");
    Serial.println(g_count);   //打印1s内的红光通过滤波器时，TCS3200输出的脉冲个数
    g_array[0] = g_count;    //存储1s内的红光通过滤波器时，TCS3200输出的脉冲个数
    TSC_WB(HIGH, HIGH);  //选择让绿色光线通过滤波器的模式
    break;
    
    case 2:
    Serial.print("->Frequency G=");
    Serial.println(g_count);   //打印1s内的绿光通过滤波器时，TCS3200输出的脉冲个数
    g_array[1] = g_count;    //存储1s内的绿光通过滤波器时，TCS3200输出的脉冲个数
    TSC_WB(LOW, HIGH);  //选择让蓝色光线通过滤波器的模式
    break;

    case 3:
    Serial.print("->Frequency B=");
    Serial.println(g_count);   //打印1s内的蓝光通过滤波器时，TCS3200输出的脉冲个数
    Serial.println("->WB End");
    g_array[2] = g_count;     //存储1s内的蓝光通过滤波器时，TCS3200输出的脉冲个数
    TSC_WB(HIGH, LOW);   //选择无滤波器的模式  
    break;

    default:
    g_count = 0;     //计数值清零
    break;
  }
}
//设置反射光中红、绿、蓝三色光分别通过滤波器时如何处理数据的标志

//该函数被TSC_Callback( )调用
void TSC_WB(int Level0, int Level1)
{
  g_count = 0;   //计数值清零
  g_flag ++;     //输出信号计数标志
  TSC_FilterColor(Level0, Level1); //滤波器模式
  Timer1.setPeriod(1000000);     //设置输出信号脉冲计数时长1s
}

void Color_set()
{
  TSC_Init();
  Timer1.initialize();   // defaulte is 1s
  Timer1.attachInterrupt(TSC_Callback); //设置定时器1的中断，中断调用函数为TSC_Callback()
  //设置TCS3200输出信号的上跳沿触发中断，中断调用函数为TSC_Count()
  attachInterrupt(0, TSC_Count, RISING);
  delay(4000); //延时4s，以等待被测物体红、绿、蓝三色在1s内的TCS3200输出信号脉冲计数
  //通过白平衡测试，计算得到白色物体RGB值255与1s内三色光脉冲数的RGB比例因子
  g_SF[0] = 255.0/ g_array[0];     //红色光比例因子
  g_SF[1] = 255.0/ g_array[1] ;    //绿色光比例因子
  g_SF[2] = 255.0/ g_array[2] ;    //蓝色光比例因子
  //打印白平衡后的红、绿、蓝三色的RGB比例因子
  Serial.println(g_SF[0],5);
  Serial.println(g_SF[1],5);
  Serial.println(g_SF[2],5);//红、绿、蓝三色光分别对应的1s内TCS3200输出脉冲数乘以相应的比例因子就是RGB标准值。打印被测物体的RGB值
  
  for(int i=0; i<3; i++)
    Serial.println(int(g_array[i] * g_SF[i]));
}

void Color_loop()
{
  g_flag = 0;
  //每获得一次被测物体RGB颜色值需时4s
  delay(4000);
  //打印出被测物体RGB颜色值
  for(int i=0; i<3; i++){
    Serial.println(int(g_array[i] * g_SF[i]));
    compare[i]=int(g_array[i] * g_SF[i]);
  }
  
  if(compare[0]>compare[1])
  {
    numMax=compare[0];
  }
  else
  {
    numMax=compare[1];
  }
  if(compare[2]>numMax)
  {
    numMax = compare[2];
  }

  if(compare[0]<compare[1])
  {
    numMin=compare[0];
  }
  else
  {
    numMin=compare[1];
  }
  if(compare[2]<numMin)
  {
    numMin=compare[2];
  }

  if(numMin<=110&&numMax<70)  //小车自动认定为未检测到货物，并且不进行后续颜色判断
  {
    Serial.println("Don't have any object being detected");//修改none判定
    lcd.setCursor(8,0);
    lcd.print("None   ");
  }
  else
  {
    delta=numMax-numMin;
    if(numMax==compare[0])
    {
      H=(compare[1]-compare[2])*60/delta;
    }
    else if(numMax==compare[1])
    {
      H = 120 + (compare[2]-compare[0])*60/delta;
    }
    else
    {
      H = 240 + (compare[0]-compare[1])*60/delta;
    }
  
    if(H<0)
      H+=360;

    if(H<30||H>300)
    {
      Serial.println("Red");  //检测为红色
      lcd.setCursor(8,0);
      lcd.print("Red    ");
    }
    else if(H>=30 && H<=80)
    {
      Serial.println("Yellow");  //检测为黄色
      lcd.setCursor(8,0);
      lcd.print("Yellow ");
    }
    else if(H>80 && H<=180)
    {
      Serial.println("Green");  //检测为绿色
      lcd.setCursor(8,0);
      lcd.print("Green  ");
    }
    else if(H>=180 && H<=280)
    {
      Serial.println("Blue");  //检测为蓝色
      lcd.setCursor(8,0);
      lcd.print("Blue   ");
    }
  
    Serial.println(H);
  }
}
