#include <iostream>
using namespace std;

class Triangulo {
public:
    double lado1, lado2, lado3;

    bool verificarTriangulo() {
        if (lado1 < lado2 + lado3 &&
            lado2 < lado1 + lado3 &&
            lado3 < lado1 + lado2) {
            return true;
        }
        else {
            return false;
        }
    }

    string tipoTriangulo() {
        if (verificarTriangulo()) {
            if (lado1 == lado2 && lado2 == lado3)
                return "Equilatero";
            else if (lado1 == lado2 || lado2 == lado3 || lado3 == lado1)
                return "Isosceles";
            else
                return "Escaleno";
        }
        else {
            return "Nao e um triangulo";
        }
    }
};

int main() {
    Triangulo obj;
    obj.lado1 = 2;
    obj.lado2 = 4;
    obj.lado3 = 5;

    cout << "O Triangulo e: " << obj.tipoTriangulo() << endl;
    return 0;
}
