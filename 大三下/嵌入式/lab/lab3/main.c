#include <platform.h>
#include <gpio.h>
#include <stdio.h>
#include <uart.h>
#include <timer.h>
#include <string.h>
#include "delay.h"
#include "lcd.h"

static volatile int changed;
static volatile unsigned int secs;
static volatile unsigned int centiseconds;  
static volatile unsigned int minutes;
/* ---- globals touched by ISR must be volatile ---- */
static volatile unsigned int game_cs;     /* elapsed centiseconds          */
static volatile unsigned char game_run;   /* 1 = round in progress         */
static unsigned int score;                /* points for the current round  */

/* ---- user-tunable parameters ---- */
#define GAME_DURATION_CS   500      /* 5 s ¡Á 100 centiseconds/s        */
#define SYSTICK_PERIOD_US  10000    /* 10 ms ¡ú 100 Hz SysTick interrupt */

/* ---- USER button definition (adapt to your board) ---- */
#define BTN_PORT   GPIOA
#define BTN_PIN    0                /* PA0; active-LOW when pressed     */

void systick_isr(void){
    secs++;
    changed = 1;
}
void new_systick_isr(void) {
        centiseconds++;  // Increment centiseconds (10ms units)
        
        // Handle rollover from centiseconds to seconds
        if (centiseconds >= 100) {
            centiseconds = 0;  // Reset centiseconds
            secs++;  // Increment seconds
            
            // Handle rollover from seconds to minutes
            if (secs >= 60) {
                secs = 0;  // Reset seconds
                minutes++;  // Increment minutes
            }
        }
    
    // Set flag to indicate time has been updated (triggers display refresh)
    changed = 1;
}
void main_sample(void) {
    char s[20];
    // initialise LCD
    lcd_init();	
	lcd_clear();
    lcd_print("EBU5477 Lab 3");

    // set up systick timer
    timer_init(1000000);
    timer_set_callback(systick_isr);
    timer_enable();
    changed = 0;
    secs = 0;
    
    __enable_irq();
    
    while(1) {  
        if (changed){
            changed = 0;
            sprintf(s, "%d", secs);
            lcd_set_cursor(0, 1);
            lcd_print(s); 
        }
    }
}
void main1(void) {
    char s[20];
    
    // Initialize LCD display
    lcd_init();
    lcd_clear();
    lcd_print("EBU5477 Lab 3");  // Display lab title
    
    // Configure SysTick timer for 100Hz (10ms intervals)
    // Timer period = 1000000 / 100 = 10000 ticks (10ms per interrupt)
    timer_init(10000);  
    timer_set_callback(new_systick_isr);
    timer_enable();
    
    // Reset display update flag and time counters
    changed = 0;  
    centiseconds = 0;
    secs = 0;
    minutes = 0;

    __enable_irq();  // Enable global interrupts
    
    while(1) {  
        // Update display when timer interrupt flags a change
        if (changed) {
            changed = 0;  // Reset flag after processing
            // Format time as HH:MM:SS.ss (hours:minutes:seconds.centiseconds)
            sprintf(s, "%02d:%02d.%02d", minutes, secs, centiseconds);
            lcd_set_cursor(0, 1);  // Position cursor on second line
            lcd_print(s);  // Display formatted time
        }
    }
}
void main2(void) {
    char s[20];
    
    // Initialize LCD and UART for input/output
    lcd_init();
    lcd_clear();
    uart_init(9600);  // Set UART baud rate
    uart_enable();
    
    // Target name to be typed (case-sensitive)
    const char* target_name = "zuozecheng";
    int target_len = strlen(target_name);  // Length of target name
    int match_idx = 0;  // Current character index in input
    int timer_running = 0;  // Flag to control timer
    char input[20] = {0};  // Buffer to store user input
    
    // Configure timer for 100Hz (10ms intervals) for timing
    timer_init(10000);
    timer_set_callback(new_systick_isr);
    timer_enable();
    
    // Reset time counters and display flag
    changed = 0;
    centiseconds = 0;
    secs = 0;
    minutes = 0;

    __enable_irq();  // Enable interrupts
    
    // Initial display prompt
    lcd_clear();
    lcd_set_cursor(0, 0);
    lcd_print("Enter my name:");  // Prompt user to type the name
    lcd_set_cursor(0, 1);  // Position cursor on second line for input display
    
    while (1) {
        // Read character from UART (blocking if no input)
        char c = uart_rx();  
        
        // Process valid lowercase letters
        if (c >= 'a' && c <= 'z') {
            // Start timer on first character input
            if (!timer_running) {
                timer_running = 1;
                // Reset time and input state
                centiseconds = 0;
                secs = 0;
                minutes = 0;
                match_idx = 0;
                memset(input, 0, sizeof(input));  // Clear input buffer
                lcd_clear();  // Clear display for new attempt
                lcd_set_cursor(0, 0);  // Reposition to prompt
            }
            
            // Append character to input and display it
            input[match_idx++] = c;
            lcd_set_cursor(match_idx - 1, 0);  // Update cursor position
            char temp[2] = {c, '\0'};  // Convert to C-string
            lcd_print(temp);  // Display the typed character
            
            // Check if input matches target name
            if (match_idx == target_len) {
                input[match_idx] = '\0';  // Null-terminate input string
                
                if (strcmp(input, target_name) == 0) {  // Match success
                    timer_running = 0;  // Stop timer
                    lcd_clear();
                    lcd_set_cursor(0, 0);
                    lcd_print("Success!");  // Display success message
                    // Format and display total time taken
                    sprintf(s, "Time: %02d:%02d.%02d", minutes, secs, centiseconds);
                    lcd_set_cursor(0, 1);
                    lcd_print(s);
                    delay_ms(3000);  // Hold result for 3 seconds
                } else {  // Match failure
                    timer_running = 0;  // Stop timer
                    lcd_clear();
                    lcd_set_cursor(0, 0);
                    lcd_print("Incorrect, try again");  // Display error message
                    delay_ms(1000);  // Hold message for 1 second
                }
                
                // Reset for new attempt
                lcd_clear();
                lcd_set_cursor(0, 0);
                lcd_print("Enter my name:");  // Redisplay prompt
                lcd_set_cursor(0, 1);
                match_idx = 0;
                memset(input, 0, sizeof(input));
            }
        }
        
        // Update timer display while typing is in progress
        if (changed && timer_running) {
            changed = 0;  // Reset flag
            // Format and display elapsed time
            sprintf(s, "Timer: %02d:%02d.%02d", minutes, secs, centiseconds);
            lcd_set_cursor(0, 1);  // Update timer on second line
            lcd_print(s);
        }
    }
}

/* ---------- 10 ms SysTick ISR ---------- */
static void game_systick_isr(void)
{
    if (game_run) {
        if (++game_cs >= GAME_DURATION_CS) {
            game_run = 0;           /* time¡¯s up ¨C stop the round */
        }
    }
}
/* ---------- simple button poll ---------- */
static inline int button_pressed(void)
{
    /* Replace gpio_get() with your BSP¡¯s call if different      */
    /* Active-LOW logic: 0 = pressed, 1 = released               */
    return (gpio_get(BTN_PORT, BTN_PIN) == 0);
}

void main3()
{
    /* -- basic peripheral setup -- */
    lcd_init();
    lcd_clear();
    lcd_print("Press Btn to start");

    gpio_set_mode(BTN_PORT, BTN_PIN, GPIO_MODE_INPUT);
    gpio_set_pull(BTN_PORT, BTN_PIN, GPIO_PULLUP);

    timer_init(SYSTICK_PERIOD_US);          /* 10 ms SysTick  */
    timer_set_callback(game_systick_isr);
    timer_enable();
    __enable_irq();

    /* -- polling loop with a tiny state machine -- */
    int prev_state = 1;     /* previous physical button level (1 = released) */
    int game_over  = 0;     /* 0 = waiting, 1 = score just shown             */

    while (1) {
        int cur_state = button_pressed();

        /* detect falling edge (release ¡ú press) */
        if (cur_state && !prev_state) {
            if (!game_run && !game_over) {          /* start a new round   */
                score   = 0;
                game_cs = 0;
                game_run = 1;
                lcd_clear();
                lcd_print("Go! Tap button");
            } else if (game_run) {                  /* count a tap         */
                ++score;
            } else if (game_over) {                 /* prepare next round  */
                lcd_clear();
                lcd_print("Press Btn to start");
                game_over = 0;
            }
        }
        prev_state = cur_state;

        /* once the round ends, display the score exactly once  */
        if (!game_run && !game_over && game_cs >= GAME_DURATION_CS) {
            char buf[17];
            lcd_clear();
            sprintf(buf, "Score: %u", score);
            lcd_print(buf);
            game_over = 1;
        }
    }
}

int main(void){
    //main_sample();
    main1();
    //main2();
    //main3();
}

// ****************************** ARM University Program Copyright ? ARM Ltd 2016 ************************************   
// ****************************** Queen Mary University of London Copyright Matthew Tang 2024 ************************

