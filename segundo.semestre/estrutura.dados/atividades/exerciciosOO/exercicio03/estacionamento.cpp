#include <iostream>
using namespace std;

class Estacionamento {
public:
    int dia, horaEntrada, horaSaida;

    void dados(){
        cout<<"Digite o dia, entrada e saida";
        cin>>dia, horaEntrada, horaSaida;
    }

    int valorFinal(){
        return ((horaSaida - horaEntrada) * 5)* dia;
    }
};
int main() {
    Estacionamento obj;
    obj.dia = 3;
    obj.horaEntrada = 10;
    obj.horaSaida = 18;
    cout << "O valor total a ser pago e: R$"<< obj.valorFinal();
    return 0;
}
