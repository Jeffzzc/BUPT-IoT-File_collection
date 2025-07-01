//三合一 (循迹 红外避障 超声波避障)多功能程序

#include <Servo.h>

//***********************定义电机腳位*************************

int MotorLeft1 = 11;
int MotorLeft2 = 6;
int MotorRight1 = 3;
int MotorRight2 = 5;
int counter = 0;

const int servo=9; //舵机引脚

char val;

//*************************定义寻线模块脚位************************************
const int SensorLeft = 7;      //左传感器
const int SensorMiddle = 4 ;   //中传感器
const int SensorRight = 8;     //右传感器
int SL;    //左传感器状态
int SM;    //中传感器状态
int SR;    //右传感器状态

const int barrier_left = A5; //左红外避障
const int barrier_right = A4; //右红外避障

//*************************超声波脚位******************************
int inputPin = 13 ; // 超声波接收脚 E
int outputPin = 12; // 超声波发射脚 T
int Fspeedd = 0; // 前方距离
int Rspeedd = 0; // 右方距离
int Lspeedd = 0; // 左方距离
int directionn = 0; // 前=8 后=2 左=4 右=6
Servo myservo; // 舵机对象
int delay_time = 250; // 舵机转向后的稳定时间
int Fgo = 8; // 前进
int Rgo = 6; // 右转
int Lgo = 4; // 左转
int Bgo = 2; // 后退

//********************************************************************(SETUP)
void setup()
{
  Serial.begin(9600);
  pinMode(MotorRight1, OUTPUT);
  pinMode(MotorRight2, OUTPUT);
  pinMode(MotorLeft1,  OUTPUT);
  pinMode(MotorLeft2,  OUTPUT);

  pinMode(SensorLeft, INPUT);
  pinMode(SensorMiddle, INPUT);
  pinMode(SensorRight, INPUT);
  
  pinMode(barrier_left, INPUT);
  pinMode(barrier_right, INPUT);
  
  pinMode(inputPin, INPUT);
  pinMode(outputPin, OUTPUT);
  myservo.attach(servo); // 启动舵机库

  Serial.println("-----------------");
}
//******************************************************************(Void)
void advance(int a) // 前进
{
  Serial.println("advance");
  digitalWrite(MotorRight1, LOW);
  digitalWrite(MotorRight2, HIGH);
  digitalWrite(MotorLeft1, LOW);
  digitalWrite(MotorLeft2, HIGH);
  delay(a * 100);
}
void right(int b) //右转 单轮
{
  Serial.println("right");
  digitalWrite(MotorLeft1, LOW);
  digitalWrite(MotorLeft2, HIGH);
  digitalWrite(MotorRight1, LOW);
  digitalWrite(MotorRight2, LOW);
  delay(b * 100);
}
void left(int c) //左转 轮
{
  Serial.println("left");
  digitalWrite(MotorRight1, LOW);
  digitalWrite(MotorRight2, HIGH);
  digitalWrite(MotorLeft1, LOW);
  digitalWrite(MotorLeft2, LOW);
  delay(c * 100);
}
void turnR(int d) //右转 双轮
{
  Serial.println("turnR");
  digitalWrite(MotorRight1, HIGH);
  digitalWrite(MotorRight2, LOW);
  digitalWrite(MotorLeft1, LOW);
  digitalWrite(MotorLeft2, HIGH);
  delay(d * 100);
}
void turnL(int e) //左转 双轮
{
  Serial.println("turnL");
  digitalWrite(MotorRight1, LOW);
  digitalWrite(MotorRight2, HIGH);
  digitalWrite(MotorLeft1, HIGH);
  digitalWrite(MotorLeft2, LOW);
  delay(e * 100);
}
void stopp(int f) //停止
{
  Serial.println("stopp");
  digitalWrite(MotorRight1, LOW);
  digitalWrite(MotorRight2, LOW);
  digitalWrite(MotorLeft1, LOW);
  digitalWrite(MotorLeft2, LOW);
  delay(f * 100);
}
void back(int g) //后退
{
  Serial.println("back");
  digitalWrite(MotorRight1, HIGH);
  digitalWrite(MotorRight2, LOW);
  digitalWrite(MotorLeft1, HIGH);
  digitalWrite(MotorLeft2, LOW);;
  delay(g * 100);
}
void detection() //测量三个角度(前.左.右)
{
  int delay_time = 250; // 舵机转向后的稳定时间
  ask_pin_F(); // 读前方距离

  if (Fspeedd < 10) // 距离小于10公分
  {
    stopp(1); // 
    back(2); // 后退 0.2秒
  }
  if (Fspeedd < 25) // 假如前方距离小于25公分
  {
    stopp(1); // 
    ask_pin_L(); // 读取左方距离
    delay(delay_time); // 等待舵机稳定
    ask_pin_R(); // 读取右方距离
    delay(delay_time); // 等待舵机稳定

    if (Lspeedd > Rspeedd) //假如 左距离大于右距离
    {
      directionn = Lgo; //向左走
    }

    if (Lspeedd <= Rspeedd) //假如 左距离小于等于右距离
    {
      directionn = Rgo; //向右走
    }

    if (Lspeedd < 15 && Rspeedd < 15) //假如 左右距离 都小于10公分
    {
      directionn = Bgo; //后退
    }
  }
  else //加前方大于25公分
  {
    directionn = Fgo; //向前走
  }
}
//*********************************************************************************
void ask_pin_F() // 量出前方距离
{
  myservo.write(90);
  digitalWrite(outputPin, LOW); // 
  delayMicroseconds(2);
  digitalWrite(outputPin, HIGH); //超声波发送一个不小于10us的高脉冲
  delayMicroseconds(10);
  digitalWrite(outputPin, LOW); // 
  float Fdistance = pulseIn(inputPin, HIGH); // 读取返回脉冲宽度
  Fdistance = Fdistance / 5.8 / 10; // 时间转成距离 （单位：公分）
  Serial.print("F distance:"); //输出距离
  Serial.println(Fdistance); //
  Fspeedd = Fdistance; // 
}
//********************************************************************************
void ask_pin_L() // 量出左边距离 
{
  myservo.write(177);
  delay(delay_time);
  digitalWrite(outputPin, LOW); //  
  delayMicroseconds(2);
  digitalWrite(outputPin, HIGH); //  
  delayMicroseconds(10);
  digitalWrite(outputPin, LOW); //  
  float Ldistance = pulseIn(inputPin, HIGH); //  
  Ldistance = Ldistance / 5.8 / 10; //  
  Serial.print("L distance:"); // 
  Serial.println(Ldistance); // 
  Lspeedd = Ldistance; //  
}
//******************************************************************************
void ask_pin_R() // 量出右边距离
{
  myservo.write(5);
  delay(delay_time);
  digitalWrite(outputPin, LOW);  
  delayMicroseconds(2);
  digitalWrite(outputPin, HIGH); //  
  delayMicroseconds(10);
  digitalWrite(outputPin, LOW); //  
  float Rdistance = pulseIn(inputPin, HIGH); //  
  Rdistance = Rdistance / 5.8 / 10; //  
  Serial.print("R distance:"); // 
  Serial.println(Rdistance); //
  Rspeedd = Rdistance; //  
}

void xunji_loop()
{
    SL = digitalRead(SensorLeft);
    SM = digitalRead(SensorMiddle);
    SR = digitalRead(SensorRight);
        Serial.print(SL);
        Serial.print("-");
        Serial.print(SM);
        Serial.print("-");
        Serial.println(SR);
        if (SM == HIGH)//中间传感器检测到黑线  黑线为1 白为0
    {
        if (SL == LOW & SR ==  HIGH) //左白右黑, 向右转弯
        {
            Serial.println("BL");
            analogWrite(MotorRight1, 0); //右转
            analogWrite(MotorRight2, 80);
            digitalWrite(MotorLeft1, LOW);
            digitalWrite(MotorLeft2, HIGH);
        }
        else if ( SL ==  HIGH & SR == LOW) // 左黑右白, 向左转弯
        {
            Serial.println("BR");

            digitalWrite(MotorRight1, LOW);
            digitalWrite(MotorRight2, HIGH);
            analogWrite(MotorLeft1, 0);
            analogWrite(MotorLeft2, 80);
        }
        else if ( SL == LOW & SR == LOW)  // 两侧为白色，前进
        {
            Serial.println("Bw");
            digitalWrite(MotorRight1, LOW);
            digitalWrite(MotorRight2, HIGH);
            digitalWrite(MotorLeft1, LOW);
            digitalWrite(MotorLeft2, HIGH);
            /*
              analogWrite(MotorLeft1,0);
              analogWrite(MotorLeft2,150);
              analogWrite(MotorRight1,0);
              analogWrite(MotorRight2,150);
            */
        }
        else if ( SL == HIGH & SR == HIGH)
        {
            Serial.println("Bb");
            advance(1);
            stopp(20);


            digitalWrite(MotorRight1, LOW);
            digitalWrite(MotorRight2, HIGH);
            digitalWrite(MotorLeft1, LOW);
            digitalWrite(MotorLeft2, HIGH);

        }
    }
        else // 中间传感器在白色区域
    {
        if (SL == LOW & SR == HIGH ) // 左白右黑, 快速右转
        {
            Serial.println("WL");

            digitalWrite(MotorRight1, LOW);
            digitalWrite(MotorRight2, LOW);
            digitalWrite(MotorLeft1, LOW);
            digitalWrite(MotorLeft2, HIGH);
        }
        else if ( SL == HIGH & SR == LOW ) // 左黑右白, 快速左转
        {
            Serial.println("WR");

            digitalWrite(MotorRight1, LOW);
            digitalWrite(MotorRight2, HIGH);
            digitalWrite(MotorLeft1, LOW);
            digitalWrite(MotorLeft2, LOW);
        }
        else // 都是白色, 保持不变
        {
            Serial.println("ww");
            /*
                         analogWrite(MotorLeft1,255);
                         analogWrite(MotorLeft2,125);
                         analogWrite(MotorRight1,255);
                         analogWrite(MotorRight2,125);
            */
        }
    }
}

void hongwaibizhang_loop()
{
  if ((digitalRead(barrier_left)==LOW) && (digitalRead(barrier_right)==LOW))
        {
          back(5);//后退
        }
        else if (digitalRead(barrier_left)==LOW)
        {
          right(6); // 右转;
        }
        else if (digitalRead(barrier_right)==LOW)
        {
          left(6); // 左转;
        }
        else
          advance(0);//前进
}
      
void duoji_loop()
{
        myservo.write(90); //舵机回中
        detection(); //测量距离
        if (directionn == 8) //假如directionn(方向) = 8(前进)
        {
          
          advance(1); // 正常前进
          Serial.print(" Advance "); //前进
          Serial.print(" ");
        }
        if (directionn == 2) //假如directionn(方向) = 2(后退)
        {
          
          back(8); // 后退
          turnL(3); //向左偏一点，防止死在里边
          Serial.print(" Reverse "); //
        }
        if (directionn == 6) //假如directionn(方向) = 6(右转)
        {
          
          back(1);
          turnR(6); // 右转
          Serial.print(" Right "); //
        }
        if (directionn == 4) //假如directionn(方向) = 4左转
        {
          
          back(1);
          turnL(6); // 左转
          Serial.print(" Left "); //
        }
        //舵机回中
}

void loop()
{
  xunji_loop();
  //hongwaibizhang_loop();
  //duoji_loop();
}






  
