import java.util.Arrays;
import java.util.Random;

/**
 * ATAD 2026/27 - Laboratório 2
 *
 * Código base para a análise de complexidade.
 *
 * Regras:
 *  - Não alterar a lógica dos algoritmos.
 *  - Podes acrescentar contadores (ver README).
 *  - Não alterar os utilitários de construção de arrays.
 */
public class Analise {

    static long ops = 0;

    // ---------------------------------------------------------------
    // Algoritmos a analisar
    // ---------------------------------------------------------------

    /** Soma de todos os elementos de um array. */
    public static int soma(int[] a) {
        int s = 0;
        for (int i = 0; i < a.length; i++) {
            ops++;
            s = s + a[i];
        }
        return s;
    }

    /** Conta quantos elementos são pares. */
    public static int contarPares(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                c++;
            }
        }
        return c;
    }

    /** Maior elemento de um array não vazio. */
    public static int maximo(int[] a) {
        int m = a[0];
        for (int i = 1; i < a.length; i++) {
            ops++;
            if (a[i] > m) {
                m = a[i];
            }
        }
        return m;
    }

    /** Indica se existe algum elemento negativo (termina no primeiro). */
    public static boolean temNegativo(int[] a) {
        for (int i = 0; i < a.length; i++) {
            ops++;
            if (a[i] < 0) {
                return true;
            }
        }
        return false;
    }

    /** Conta os pares de posições (i, j), i < j, com valores iguais. */
    public static int contaParesIguais(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                ops++;
                if (a[i] == a[j]) {
                    c++;
                }
            }
        }
        return c;
    }

    // ---------------------------------------------------------------
    // Utilitários (não alterar)
    // ---------------------------------------------------------------

    /** Array [0, 1, 2, ..., n-1]. */
    public static int[] crescente(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    /** Array de n valores pseudo-aleatórios em [-500, 500]. */
    public static int[] aleatorio(int n) {
        return aleatorio(n, -500, 500);
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

    /** Array [0, 1, ..., n-1] com o primeiro elemento negativo. */
    public static int[] comNegativoNoInicio(int n) {
        int[] a = crescente(n);
        a[0] = -1;
        return a;
    }

    /** Array [0, 1, ..., n-1] com o último elemento negativo. */
    public static int[] comNegativoNoFim(int n) {
        int[] a = crescente(n);
        a[n - 1] = -1;
        return a;
    }

    /** Array [0, 1, ..., n-1] sem elementos negativos. */
    public static int[] semNegativos(int n) {
        return crescente(n);
    }

    // ---------------------------------------------------------------
    // Ponto de entrada (podes alterar)
    // ---------------------------------------------------------------

    public static void main(String[] args) {
        /*
        int[] a = aleatorio(10, -5, 5);
        System.out.println(Arrays.toString(a));

        System.out.println("soma        = " + soma(a));
        System.out.println("contarPares = " + contarPares(a));
        System.out.println("maximo      = " + maximo(a));
        System.out.println("temNegativo = " + temNegativo(a));
        System.out.println("contaParesIguais = " + contaParesIguais(a));

         */


        System.out.println("SOMA");
        System.out.println("n\tops\tops/n");
        for (int n = 100; n <= 800; n *= 2) {
            int[] a = aleatorio(n);

            ops = 0;
            soma(a);
            System.out.println(n + "\t" + ops + "\t" + (double) ops / n);
        }

        System.out.println("MAXIMO");
        System.out.println("n\tops\tops/n");
        for (int n = 100; n <= 800; n *= 2) {
            int[] a = aleatorio(n);

            ops = 0;
            maximo(a);
            System.out.println(n + "\t" + ops + "\t" + (double) ops / n);
        }

        System.out.println("CONTA PARES IGUAIS");
        System.out.println("n\tops\tops/n²");
        for (int n = 100; n <= 800; n *= 2) {
            int[] a = aleatorio(n);

            ops = 0;
            contaParesIguais(a);
            System.out.println(n + "\t" + ops + "\t" + (double) ops / (n*n));
        }

        System.out.println("TEM NEGATIVO");
        ops = 0;
        temNegativo(comNegativoNoInicio(1000));
        System.out.println("com negativo no inicio | ops: " + ops);

        ops = 0;
        temNegativo(comNegativoNoFim(1000));
        System.out.println("com negativo no fim | ops: " + ops);

        ops = 0;
        temNegativo(semNegativos(1000));
        System.out.println("sem negativos | ops: " + ops);

    }
}
