#include <iostream>
using namespace std;

void orndenar(int vetor[], int tamanho){
    for (int i = 0; i < tamanho-1; i++) {
        for (int j = 0; j < tamanho-1-i; j++) {
            if (vetor[j] > vetor[j+1]) {
                swap(vetor[j], vetor[j+1]);
            }
        }
    }
}

int buscar(int vetor[], int tamanho, int valor){
    int inicio = 0;
    int fim = tamanho -1;

    while(inicio <= fim){
        int meio = (inicio + fim) / 2;
        if(vetor[meio] == valor){
            return meio;
        }
        else if(vetor[meio] < valor){
            inicio = meio + 1;
        }
        else{
            fim = meio -1;
        }

    }


    return -1;
}

int main (){
    int notas[10] = {3, 5, 2, 1, 9, 4, 7, 10, 8, 6};
    
    cout << "Array desordenado: ";
    for (int i = 0; i < 10; i++) {
        cout << notas[i] << " ";
    }
    cout << "\n";
    
    orndenar(notas, 10);
    
    cout << "Array ordenado: ";
    for (int i = 0; i < 10; i++) {
        cout << notas[i] << " ";
    }
    cout << "\n";
    int indice = buscar(notas, 10, 7);

    if (indice != -1)
        cout << "Valor encontrado no indice: " << indice << endl;
    else
        cout << "Valor nao encontrado." << endl;


    return 0;
}