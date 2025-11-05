#include <iostream>
using namespace std;

class Calendario
{
private:
    int mes;
    int ano;

public:
    // Construtor correto
    Calendario(int mes, int ano)
    {
        this->mes = mes;
        this->ano = ano;
    }

    // Verifica se o ano é bissexto
    bool bissexto()
    {
        return ((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0);
    }

    // Calcula o dia da semana de uma data (simplificado)
    int diaSemana(int dia)
    {
        int a = ano; // variável auxiliar para não alterar o atributo
        int f = a + dia + 3 * (mes - 1) - 1;
        if (mes < 3)
            a--;
        else
            f -= int(0.4 * mes + 2.3);
        f += int(a / 4) - int((a / 100 + 1) * 0.75);
        f %= 7;
        return f + 1;
    }

    // Imprime o calendário do mês
    void calendario()
    {
        cout << "DOM\tSEG\tTER\tQUA\tQUI\tSEX\tSAB\n\n";
        short TamanhoDoMes[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (bissexto())
            TamanhoDoMes[1] = 29;

        for (int j = 1; j < diaSemana(1); j++)
            cout << '\t';

        for (int dia = 1; dia <= TamanhoDoMes[mes - 1]; dia++)
        {
            if (dia < 10)
                cout << '0' << dia << '\t';
            else
                cout << dia << '\t';

            if (diaSemana(dia) == 7)
                cout << '\n';
        }
        cout << "\n";
    }
};

int main()
{
    int ano, mes;

    cout << "Digite o ano: ";
    cin >> ano;

    cout << "Digite o mes (1 a 12): ";
    cin >> mes;

    Calendario cal(mes, ano); // ✅ cria o objeto corretamente
    cal.calendario();

    return 0;
}
