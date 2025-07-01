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
    
    while (1) {
        char c = uart_rx();
        
        if (c == '.') {
            lcd_clear();  // Clear LCD if '.' is received
        } 
        else if (c == ',') {
            lcd_set_cursor(0, 0);  // Move cursor to the top if ',' is received
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
    
    while (1) {
        // Check if the RIGHT button (connected to PA_0) is pressed
        if (gpio_get(PA_0) == 1) {
            uart_print("Your Name in Pinyin");  // Print the name on the terminal
            delay_ms(200);  // Delay to debounce the button press
        }
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
    main3();  // Run main3() for the secret password challenge
    return 0;
}

