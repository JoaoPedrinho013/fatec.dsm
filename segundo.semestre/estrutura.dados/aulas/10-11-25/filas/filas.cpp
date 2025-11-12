#include <cstdlib>
#include <iostream>

using namespace std;

class FilaArray
{
private:
    int *VET;
    int ProximaPosicaoLivre;
    int MAX;

public:
    FilaArray(int qtde)
    {
        MAX = qtde;
        VET = new int[MAX];
        ProximaPosicaoLivre = 0;
    }
    void Entrada(int n)
    {
        if (ProximaPosicaoLivre < MAX)
            VET[ProximaPosicaoLivre++] = n;
    }
    void Mostra()
    {
        for (int i = 0; i < ProximaPosicaoLivre; i++)
        {
            cout << VET[i] << "\n";
        }
    }
    int Saida()
    {
        int auxiliar = VET[0];
        if (ProximaPosicaoLivre > 0)
        {

            for (int indice = 0; indice < MAX; indice++)
            {
                VET[indice] = VET[indice + 1];
            }

            ProximaPosicaoLivre--;
            return auxiliar;
        }
        else return -1;
    }
};

int main(int argc, char *argv[])
{
    FilaArray fila(50);
    fila.Entrada(3);
    fila.Entrada(5);
    fila.Entrada(7);
    fila.Entrada(1);
    fila.Mostra();
    cout << "\nSaida " << fila.Saida() << ".\n\n";
    cout << "\nSaida " << fila.Saida() << ".\n\n";
    fila.Mostra();
    system("PAUSE");
    return EXIT_SUCCESS;
}
