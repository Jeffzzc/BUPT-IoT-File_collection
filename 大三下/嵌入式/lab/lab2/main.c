#include <platform.h>
#include <gpio.h>
#include <uart.h>
#include "delay.h"
#include "lcd.h"

void main_sample(void) {
    // Initialization
    lcd_init();    
    lcd_clear();
    lcd_print("EBU5477 Lab 2");
    lcd_set_cursor(0, 1);

    uart_init(9600);
    uart_enable();
    
    while (1) {
        char c = uart_rx();
        lcd_put_char(c);
    }
}

void main1(void) {
    // Initialization
    lcd_init();    
    lcd_clear();
    lcd_print("EBU5477 Lab 2");
    lcd_set_cursor(0, 1);

    uart_init(9600);
    uart_enable();
    int current_line = 1;
    while (1) {
        char c = uart_rx();
        
        if (c == '.') {
            lcd_clear();  // Clear LCD if '.' is received
        } 
        else if (c == ',') {
            current_line = 1 - current_line;
            lcd_set_cursor(0, current_line);  // Move cursor to the top if ',' is received
        } 
        else {
            lcd_put_char(c);  // Otherwise, print the character on the LCD
        }
    }
}

void main2(void) {
    // Initialization
    lcd_init();
    lcd_clear();
    lcd_print("Press RIGHT button");
    lcd_set_cursor(0, 1);

    uart_init(9600);
    uart_enable();
    int last_button_state = 0; // ??????????
    int button_state = 0;      // ?????????

    while (1) {
        // ????????
        button_state = gpio_get(PA_0);

        // ???????(???0??1)
        if (button_state == 1 && last_button_state == 0) {
            uart_print("Zuo Zecheng");  // ????
            delay_ms(300);             // ????
        }

        // ??????????
        last_button_state = button_state;

    }
}

void main3(void) {
    // Initialization
    lcd_init();
    lcd_clear();
    lcd_print("Secret Password: ");
    lcd_set_cursor(0, 1);

    uart_init(9600);
    uart_enable();
    
    const char* secret = "cake";  // Secret password
    char input[5];  // Buffer to store the input characters
    int idx = 0;

    while (1) {
        char c = uart_rx();  // Receive character from UART
        
        if (c == secret[idx]) {
            idx++;
        } else {
            idx = 0;  // Reset index if a character doesn't match
        }

        if (idx == 4) {
            uart_print("BINGO!");  // Print "BINGO!" if the secret is matched
            idx = 0;  // Reset the index to start matching again
        }
    }
}

int main(void) {
    //main_sample();
    main1();
    //main2();
    //main3();
    
    return 0;
}

// ****************************** ARM University Program Copyright ? ARM Ltd 2016 ************************************   
// ****************************** Queen Mary University of London Copyright Matthew Tang 2024 ************************
