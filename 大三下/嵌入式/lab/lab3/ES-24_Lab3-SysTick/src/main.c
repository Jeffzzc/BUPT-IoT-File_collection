/*
 * main.c - Lab3 exercises (SysTick, Stopwatch, Mini games)
 * Author: <Your Name>
 * Student ID: <ID>
 * Date: 2025-05-16
 */

#include "stm32f4xx.h"
#include <stdint.h>
#include <stdio.h>
#include <string.h>

#include "lcd.h"
#include "uart.h"  // Custom UART driver provided in project files

/* ------------------------------------------------------------------ */
/*                       Forward declarations                         */
/* ------------------------------------------------------------------ */
void delay_ms(uint32_t ms);
void button_init(void);
uint8_t button_read(void);
void rng_init(void);
uint32_t rng_get(void);

/* ------------------------------------------------------------------ */
volatile uint32_t tick10ms = 0;

/* ------------------------------------------------------------------ */
void SysTick_Handler(void)
{
    tick10ms++;
}

/* ------------------------------------------------------------------ */
static void main1(void)
{
    lcd_init();
    SysTick_Config(SystemCoreClock / 100);
    uint32_t last = 0;

    while (1)
    {
        if (tick10ms != last)
        {
            last = tick10ms;

            uint32_t total = tick10ms;
            uint32_t cs   =  total % 100;
            uint32_t sec  = (total / 100) % 60;
            uint32_t min  =  total / 6000;

            char buf[12];
            sprintf(buf, "%02lu:%02lu.%02lu", min, sec, cs);

            lcd_set_cursor(0, 0);
            lcd_print(buf);
        }
    }
}

/* ------------------------------------------------------------------ */
static void main2(void)
{
    const char *name = "wangwu";
    const uint32_t len = strlen(name);

    lcd_init();
    uart_init();                               // Use your uart.c driver
    SysTick_Config(SystemCoreClock / 100);

    uint32_t pos = 0;
    uint8_t  started = 0;

    lcd_set_cursor(0, 0);
    lcd_print("Type your name:");

    while (1)
    {
        char ch = uart_getc();                // Blocking receive from uart.c

        if (!started)
        {
            started = 1;
            tick10ms = 0;
            pos = 0;
        }

        if (ch == name[pos])
        {
            pos++;
            if (pos == len)
            {
                uint32_t t = tick10ms;

                uint32_t cs   =  t % 100;
                uint32_t sec  = (t / 100) % 60;
                uint32_t min  =  t / 6000;

                char buf[16];
                sprintf(buf, "%02lu:%02lu.%02lu", min, sec, cs);

                lcd_clear();
                lcd_set_cursor(0, 0);
                lcd_print("Your time:");
                lcd_set_cursor(1, 0);
                lcd_print(buf);

                started = 0;
                pos     = 0;
                continue;
            }
        }
        else
        {
            pos = 0;
            tick10ms = 0;
        }
    }
}

/* ------------------------------------------------------------------ */
static void main3(void)
{
    lcd_init();
    button_init();
    rng_init();
    SysTick_Config(SystemCoreClock / 100);

    while (1)
    {
        while (button_read()) ;

        lcd_clear();
        lcd_print("Get ready...");
        delay_ms(500);

        uint32_t rnd = rng_get();
        uint32_t wait_ms = 1000 + (rnd % 2000);
        delay_ms(wait_ms);

        lcd_clear();
        lcd_print("GO!");
        tick10ms = 0;

        while (!button_read()) ;

        uint32_t t = tick10ms;

        uint32_t cs   =  t % 100;
        uint32_t sec  = (t / 100) % 60;
        uint32_t min  =  t / 6000;

        char buf[16];
        sprintf(buf, "%02lu:%02lu.%02lu", min, sec, cs);

        lcd_clear();
        lcd_print("Reaction:");
        lcd_set_cursor(1, 0);
        lcd_print(buf);

        while (button_read()) ;
        delay_ms(300);
    }
}

/* ------------------------------------------------------------------ */
int main(void)
{
    // main1();    // Stopwatch
    // main2();    // UART typing game
    main3();       // Reaction timer game

    while (1) {}
}

