import java.util.Arrays;
import java.util.Random;

/**
 * ATAD 2026/27 - Laboratório 3
 *
 * Pesquisa e ordenação.
 *
 * Métodos já implementados: pesquisaLinear, indiceMinimo e os utilitários.
 * Métodos a implementar: pesquisaBinaria, bubbleSort, selectionSort.
 *
 * Os contadores `comparacoes` e `trocas` são repostos pelo chamador.
 */
public class Ordenacao {

    // Contadores globais (repor antes de cada medição)
    static long comparacoes;
    static long trocas;

    // ---------------------------------------------------------------
    // Pesquisa (implementado)
    // ---------------------------------------------------------------

    public static int pesquisaLinear(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            comparacoes++;
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // ---------------------------------------------------------------
    // Pesquisa binária (a implementar)
    // ---------------------------------------------------------------

    // Pré-condição: a está ordenado crescentemente
    public static int pesquisaBinaria(int[] a, int x) {
        int esq = 0;
        int dir = a.length - 1;
        while (esq <= dir) {
            comparacoes++;

            int meio = (esq + dir) / 2;
            if (a[meio] == x) {
                return meio;
            }
            if (a[meio] < x) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return -1;
        // Contar uma comparação por iteração (comparacoes++).
    }

    // ---------------------------------------------------------------
    // Mínimo (implementado)
    // ---------------------------------------------------------------

    // Pré-condição: a tem pelo menos um elemento
    public static int indiceMinimo(int[] a) {
        int indiceMin = 0;
        for (int i = 1; i < a.length; i++) {
            comparacoes++;
            if (a[i] < a[indiceMin]) {
                indiceMin = i;
            }
        }
        return indiceMin;
    }

    // ---------------------------------------------------------------
    // Ordenação (a implementar)
    // ---------------------------------------------------------------

    public static void bubbleSort(int[] a) {
        // TODO: implementar (com flag de paragem antecipada).
        // Contar uma comparação por cada comparação entre elementos
        // e uma troca por cada troca efetuada.
    }

    public static void selectionSort(int[] a) {
        // TODO: implementar.
        // Contar comparações e trocas como no bubbleSort.
    }

    // ---------------------------------------------------------------
    // Utilitários (não alterar)
    // ---------------------------------------------------------------

    public static int[] copia(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    /** Array [0, 1, ..., n-1]. */
    public static int[] crescente(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    /** Array [n-1, n-2, ..., 0]. */
    public static int[] inverso(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - 1 - i;
        }
        return a;
    }

    /** Array de n valores pseudo-aleatórios em [0, 1000]. */
    public static int[] aleatorio(int n) {
        return aleatorio(n, 0, 1000);
    }

    /** Array de n valores pseudo-aleatórios em [min, max].
     *  Pré-condição: min < max
     */
    public static int[] aleatorio(int n, int min, int max) {
        Random r = new Random();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(max - min + 1) + min;
        }

        return a;
    }

    public static boolean estaOrdenado(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] >= a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // ---------------------------------------------------------------
    // Ponto de entrada (podes alterar)
    // ---------------------------------------------------------------

    public static void main(String[] args) {
        int[] a = aleatorio(10);
        System.out.println("antes:       " + Arrays.toString(a));

        int[] b = copia(a);
        bubbleSort(b);
        System.out.println("bubble:      " + Arrays.toString(b) + "  ordenado? " + estaOrdenado(b));

        int[] c = copia(a);
        selectionSort(c);
        System.out.println("selection:   " + Arrays.toString(c) + "  ordenado? " + estaOrdenado(c));

        System.out.println("---");

        int[] ord = crescente(10);
        System.out.println(Arrays.toString(ord));

        comparacoes = 0;
        System.out.println("linear(7)    = " + pesquisaLinear(ord, 7) + "  (" + comparacoes + " comparações)");

        comparacoes = 0;
        System.out.println("binaria(7)   = " + pesquisaBinaria(ord, 7) + "  (" + comparacoes + " comparações)");

        comparacoes = 0;
        System.out.println("indiceMinimo = " + indiceMinimo(a) + "  (" + comparacoes + " comparações)");

        System.out.println("---");

        // TODO
    }
}
