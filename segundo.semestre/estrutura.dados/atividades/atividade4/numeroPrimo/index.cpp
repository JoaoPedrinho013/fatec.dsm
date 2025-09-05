#include <iostream>
#include "../consoleAcento.h"
using namespace std;

int isPrimo(int num)
{
    if (num <= 1)
        return 0;
    if (num <= 3)
        return 1;
    if (num % 2 == 0 || num % 3 == 0)
        return 0;

    for (int i = 5; i * i <= num; i += 6)
    {
        if (num % i == 0 || num % (i + 2) == 0)
            return 0;
    }
    return 1;
}

int main()
{
    consoleAcento();
    cout << isPrimo(10) << "\n";
    cout << isPrimo(2) << "\n";
    cout << isPrimo(4) << "\n";
    cout << isPrimo(7) << "\n";
    cout << isPrimo(13) << "\n";
    return 0;
}