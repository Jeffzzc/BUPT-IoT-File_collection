#include "stm32f4xx.h"
#include <stdio.h>

#define ADC_MAX 4095
#define V_REF 3.3f
#define R_KNOWN 10000.0f  // 10kΩ已知电阻

volatile uint16_t adc_val = 0;
volatile float R_unknown = 0.0f;
char lcd_buf[16];

// ADC初始化，采样PA0
void ADC_Init(void) {
    RCC->AHB1ENR |= RCC_AHB1ENR_GPIOAEN; // 使能GPIOA时钟
    GPIOA->MODER |= (3 << (0 * 2)); // PA0模拟模式

    RCC->APB2ENR |= RCC_APB2ENR_ADC1EN; // 使能ADC1时钟
    ADC1->SQR3 = 0; // 通道0 (PA0)
    ADC1->SMPR2 |= ADC_SMPR2_SMP0; // 采样时间设置
    ADC1->CR2 |= ADC_CR2_ADON; // 打开ADC
}

// 读取ADC值
uint16_t ADC_Read(void) {
    ADC1->CR2 |= ADC_CR2_SWSTART;
    while (!(ADC1->SR & ADC_SR_EOC));
    return ADC1->DR;
}

// 计算未知电阻，注意V_ref=3.3V，电压为5V分压后的电压
float Calculate_Resistance(uint16_t adc) {
    float Vout = (float)adc / ADC_MAX * V_REF;
    if(Vout == 0.0f) return 0.0f;
    // 由电压分压公式反推未知电阻
    return R_KNOWN * Vout / (5.0f - Vout);
}

// 假设有LCD驱动函数
void LCD_Display(float resistance) {
    snprintf(lcd_buf, sizeof(lcd_buf), "R=%.1f Ohm", resistance);
    // LCD显示函数，此处调用你的LCD驱动API
    LCD_Print(lcd_buf);
}

int main(void) {
    ADC_Init();
    LCD_Init(); // 自行实现或调用现有函数

    while(1) {
        adc_val = ADC_Read();
        R_unknown = Calculate_Resistance(adc_val);
        LCD_Display(R_unknown);
        for(int i=0; i<1000000; i++); // 简单延时，防止刷屏太快
    }
}
