using System;

class SistemaClassificacaoNotas

{

    static void BubbleSort(double[] notas, int tamanho)

    {

        for (int c = 0; c < tamanho - 1; c++)

        {

            for (int j = 0; j < tamanho - c - 1; j++)

            {

                if (notas[j] < notas[j + 1])

                {

                    double temp = notas[j];

                    notas[j] = notas[j + 1];

                    notas[j + 1] = temp;

                }

            }

        }

    }

    static void ExibirClassificacao(double[] notas, string[] nomes, int quantidade)

    {

        Console.WriteLine("\n=== CLASSIFICAÇÃO FINAL ===");

        Console.WriteLine("Posição | Aluno              | Nota  | Status");

        Console.WriteLine("--------|-------------------|-------|----------");

        for (int i = 0; i < quantidade; i++)

        {

            string status = notas[i] >= 7.0 ? "Aprovado" :

                           notas[i] >= 5.0 ? "Recuperação" : "Reprovado";

            Console.WriteLine($"{i + 1,7} | {nomes[i],-17} | {notas[i],5:F1} | {status}");

        }

    }

    static void OrdenarAlunosPorNota(double[] notas, string[] nomes, int quantidade)

    {

        for (int c = 0; c < quantidade - 1; c++)

        {

            for (int j = 0; j < quantidade - c - 1; j++)

            {

                if (notas[j] < notas[j + 1])

                {

                    double tempNota = notas[j];

                    notas[j] = notas[j + 1];

                    notas[j + 1] = tempNota;

                    string tempNome = nomes[j];

                    nomes[j] = nomes[j + 1];

                    nomes[j + 1] = tempNome;

                }

            }

        }

    }

    static void Main()

    {

        Console.WriteLine("=== SISTEMA DE CLASSIFICAÇÃO DE NOTAS ===\n");

        string[] nomes = {

            "Ana Silva", "João Santos", "Maria Oliveira", "Pedro Costa",

            "Julia Ferreira", "Carlos Lima", "Beatriz Alves", "Lucas Pereira",

            "Camila Rocha", "Rafael Martins"

        };

        double[] notas = { 7.8, 6.2, 9.1, 4.5, 8.7, 5.9, 9.5, 3.2, 7.1, 8.3 };

        int totalAlunos = nomes.Length;

        Console.WriteLine("Notas antes da classificação:");

        for (int i = 0; i < totalAlunos; i++)

        {

            Console.WriteLine($"{nomes[i]}: {notas[i]:F1}");

        }

        OrdenarAlunosPorNota(notas, nomes, totalAlunos);

        ExibirClassificacao(notas, nomes, totalAlunos);

        Console.WriteLine("\n=== ESTATÍSTICAS DA TURMA ===");

        double soma = 0;

        int aprovados = 0, recuperacao = 0, reprovados = 0;

        for (int i = 0; i < totalAlunos; i++)

        {

            soma += notas[i];

            if (notas[i] >= 7.0) aprovados++;

            else if (notas[i] >= 5.0) recuperacao++;

            else reprovados++;

        }

        double media = soma / totalAlunos;

        Console.WriteLine($"Média da turma: {media:F2}");

        Console.WriteLine($"Maior nota: {notas[0]:F1} ({nomes[0]})");

        Console.WriteLine($"Menor nota: {notas[totalAlunos - 1]:F1} ({nomes[totalAlunos - 1]})");

        Console.WriteLine($"\nAprovados: {aprovados} alunos");

        Console.WriteLine($"Em recuperação: {recuperacao} alunos");

        Console.WriteLine($"Reprovados: {reprovados} alunos");

        Console.WriteLine("\nPressione qualquer tecla para sair...");

        Console.ReadKey();

    }

}
