#include <platform.h>
#include <gpio.h>
#include <stdio.h>
#include "delay.h"
#include "lcd.h"


void main_sample(void) {

    lcd_init();
    lcd_clear();

    while (1) {      
        lcd_clear();
        lcd_print("QMUL-BUPT JP/JEI\nWelcome!");
        delay_ms(1500);

        lcd_clear();
        lcd_print("EBU5477\nEmbedded Systems");
        delay_ms(1500);
    }
}


void main1(void) {
    lcd_init();  
    lcd_clear(); 

    while (1) {      
        lcd_clear();
        lcd_print("Zecheng\nZuo");           // name 
        delay_ms(3000);                      // delay 3 seconds
        
        
        lcd_clear();
        lcd_print("2022213434\n221169081");  // My student id
        delay_ms(3000);                      // delay 3 seconds
    }
}

void main2(void) {
    int count = 0;  
    char count_str[20];  

    
    lcd_init();
    lcd_clear();
    gpio_set_mode(PA_0, PullUp);  

   
    lcd_print("Press Count: 0");             // first show the number

    while (1) {
        
        if (gpio_get(PA_0) == 0) {           // click once 
            count++;  
            sprintf(count_str, "Press Count: %d", count);  

            lcd_clear();
            lcd_print(count_str);  

            delay_ms(200); 
            while (gpio_get(PA_0) == 0);     // continue
        }
    }
}


void main3(void) {
    int toggle = 0; 
    lcd_init(); 
    lcd_clear();
    
   
    lcd_print("Zecheng\nZuo");           // output name

    while (1) {
        if (gpio_get(PA_0) == 0) {       // click reset
            toggle = !toggle;            // click right

            lcd_clear();
            if (toggle) { 
                lcd_print("2022213434\n221169081");
            } else {
                lcd_print("Zecheng\nZuo");
            }

            delay_ms(200);               // wait for 0.2 second
            while (gpio_get(PA_0) == 0);  
        }
    }
}

int main(void) {

    //main_sample();
    main1();
    //main2();
    //main3();
}

