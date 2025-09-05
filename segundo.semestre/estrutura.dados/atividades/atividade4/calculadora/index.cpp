#include <iostream>
#include "../consoleAcento.h"
using namespace std;

int multiplicar(int n1, int n2){
    return n1 * n2;
}
int somar(int n1, int n2){
    return n1 + n2;
}
int subtrair(int n1, int n2){
    return n1 - n2;
}
int dividir(int n1, int n2){
    return n1 / n2;
}

int main()
{
    consoleAcento();
    bool sair = false;
    do {
        int digitado, n1, n2;
        cout << "Qual operacao voce deseja fazer?\n";
        cout << "1 - Multiplicacao\n";
        cout << "2 - Soma\n";
        cout << "3 - Divisao\n";
        cout << "4 - Subtracao\n";
        cout << "99 - Sair\n";

        cin >> digitado;

        switch(digitado){
            case 1: // multiplicar
                cout << "Digite o n1: ";
                cin >> n1;
                cout << "Digite o n2: ";
                cin >> n2;
                cout << "Resultado: " << multiplicar(n1, n2) << "\n";
                break;

            case 2: // soma
                cout << "Digite o n1: ";
                cin >> n1;
                cout << "Digite o n2: ";
                cin >> n2;
                cout << "Resultado: " << somar(n1, n2) << "\n";
                break;

            case 3: // divisão
                cout << "Digite o n1: ";
                cin >> n1;
                cout << "Digite o n2: ";
                cin >> n2;
                if (n2 == 0) {
                    cout << "Erro: divisao por zero!\n";
                } else {
                    cout << "Resultado: " << dividir(n1, n2) << "\n";
                }
                break;

            case 4: // subtração
                cout << "Digite o n1: ";
                cin >> n1;
                cout << "Digite o n2: ";
                cin >> n2;
                cout << "Resultado: " << subtrair(n1, n2) << "\n";
                break;

            case 99: // sair
                cout << "Tchau!\n";
                sair = true;
                break;

            default:
                cout << "Opcao invalida, tente de novo!\n";
        }

    } while(!sair);

    return 0;
}
