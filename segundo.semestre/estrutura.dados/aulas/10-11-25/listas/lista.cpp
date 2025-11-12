#include <cstdlib>
#include <iostream>

using namespace std;

class ListaDeArray
{
private:
    int *VET;
    int ProximaPosicaoLivre;
    int MAX;

public:
    ListaDeArray(int qtde)
    {
        MAX = qtde;
        VET = new int[MAX];
        ProximaPosicaoLivre = 0;
    }

    void InserirInicio(int n)
    {
        if (ProximaPosicaoLivre < MAX)
        {
            for (int indice = ProximaPosicaoLivre - 1; indice >= 0; indice--)
            {
                VET[indice + 1] = VET[indice];
            }
            VET[0] = n;
            ProximaPosicaoLivre++;
        }
    }

    void InserirFim(int n)
    {
        if (ProximaPosicaoLivre < MAX)
            VET[ProximaPosicaoLivre++] = n;
    }

    int SaidaInicio()
    {
        int auxiliar = VET[0];
        if (ProximaPosicaoLivre > 0)
        {

            for (int indice = 0; indice < ProximaPosicaoLivre - 1; indice++)
            {

                VET[indice] = VET[indice + 1];
            }

            ProximaPosicaoLivre--;
            return auxiliar;
        }
        else
            return -1;
    }

    int SaidaFim()
    {
        if (ProximaPosicaoLivre > 0)
            return VET[--ProximaPosicaoLivre];
        else
            return -1;
    }

    void MostrarListaDeArray()
    {
        for (int i = 0; i < ProximaPosicaoLivre; i++)
        {
            cout << VET[i] << "\n";
        }
    }
};

int main(int argc, char *argv[])
{
    ListaDeArray lista(50);
    lista.InserirFim(2);
    lista.InserirFim(3);
    lista.InserirFim(4);
    lista.InserirFim(5);
    lista.InserirInicio(1);
    lista.InserirInicio(666);
    lista.InserirFim(999);
    lista.MostrarListaDeArray();
    cout << "\nSaida " << lista.SaidaInicio() << ".\n\n";
    cout << "\nSaida " << lista.SaidaFim() << ".\n\n";
    lista.MostrarListaDeArray();
    system("PAUSE");
    return EXIT_SUCCESS;
}
