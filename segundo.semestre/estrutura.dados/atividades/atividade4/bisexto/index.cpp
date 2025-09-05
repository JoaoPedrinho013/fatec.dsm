#include <iostream>
#include "../consoleAcento.h"
using namespace std;

int isBisexto(int ano)
{
    if((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
        return 1;
    }else {
        return 0;
    }
}

int main()
{
    consoleAcento();
    cout << isBisexto(2000)<< "\n";
    cout << isBisexto(1900)<< "\n";
    cout << isBisexto(2024)<< "\n";
    cout << isBisexto(2100)<< "\n";
    return 0;
}