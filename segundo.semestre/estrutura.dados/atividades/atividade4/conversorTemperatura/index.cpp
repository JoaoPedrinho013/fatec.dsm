#include <iostream>
#include "../consoleAcento.h"
using namespace std;

int fahrenheit(double celsius){
    return (celsius * 9 / 5) + 32;
}
int celsius(double fahrenheit){
    return (fahrenheit - 32) * 5 / 9;
}
int main(){
    consoleAcento();
    int ConverterFahrenheit = fahrenheit(10);
    int ConverterCelsius = celsius(10);
    cout << "Convertendo 10°C para Farenheit fica " << ConverterFahrenheit << "°F\n";
    cout << "Convertendo 10°F para Celsius fica " << ConverterCelsius<< "°C";
    return 0;
}