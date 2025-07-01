#include <platform.h>
#include <gpio.h>
#include <adc.h>
#include <stdio.h>
#include "delay.h"
#include "lcd.h"

#define VREF (3.3)
#define ADC_RESOLUTION (4096.0)

void main_sample(void){
    // initialisation
    lcd_init();	
	lcd_clear();
    lcd_print("EBU5477 Lab 4");
    
    adc_init(PA_0);
    // start looping 
    while (1) {
        volatile int res = (int)adc_read(PA_0);
        lcd_set_cursor(0, 1);
        if (res > 700 && res < 800)
            lcd_print("UP pressed");
        else
            lcd_print("          ");
        delay_ms(500);
    }
}

void main1(void) {
    // Initialize LCD and ADC
    lcd_init();    
    lcd_clear();
    lcd_print("EBU5477 Lab 4");
    
    adc_init(PA_0);
    
    while (1) {

        volatile int res = (int)adc_read(PA_0);
        lcd_set_cursor(0, 1);

        if (res > 700 && res < 800)
            lcd_print("UP      ");
        else if (res > 1700 && res < 2000)
            lcd_print("DOWN    ");
        else if (res > 2700 && res < 3000)
            lcd_print("LEFT    ");
        else if (res < 50)
            lcd_print("RIGHT   ");
        else if (res > 4000)
            lcd_print("SELECT  ");
        else
            lcd_print("NONE    ");
            
        delay_ms(100); 
    }
}

void main2(void) {

    lcd_init();
    lcd_clear();
    lcd_print("Battery Voltage");
    
    char voltage_str[16];  
    adc_init(PA_1);       
    
    while (1) {

        volatile int adc_value = (int)adc_read(PA_1);
        
        float voltage = adc_value * VREF / ADC_RESOLUTION;
        

        sprintf(voltage_str, "V = %.2fV", voltage);
        

        lcd_set_cursor(0, 0);
        lcd_print("Battery Voltage");
        lcd_set_cursor(0, 1);
        lcd_print(voltage_str);
        
        delay_ms(500); 
    }
}

int main(void) {
    main_sample();
    //main1();
    //main2();
    
    return 0;
}

// ****************************** ARM University Program Copyright © ARM Ltd 2016 ************************************   
// ****************************** Queen Mary University of London Copyright Matthew Tang 2024 ************************
