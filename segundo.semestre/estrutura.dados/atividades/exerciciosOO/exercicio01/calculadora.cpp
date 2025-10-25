#include <iostream>
using namespace std;

class Matematica{
    public:

    int num1, num2;

    int adicao() {
        return num1 + num2;
    }
    int subtracao() {
        return num1 - num2;
    }
    int multiplicacao() {
        return num1 * num2;
    }
    int divisao() {

        if(num1 < num2){
            return num2 / num1;
        }else {
            return num1 / num2;
        }
    }

};

int main (){
    Matematica calculadora;
    calculadora.num1 = 5;
    calculadora.num2 = 5;

    cout<<"Soma: "<<calculadora.adicao()<<"\n";
    cout<<"Subtracao: "<<calculadora.subtracao()<<"\n";
    cout<<"Multiplicacao: "<<calculadora.multiplicacao()<<"\n";
    cout<<"Divisao: "<<calculadora.divisao()<<"\n";

    return 0;
}