
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author heiderarellano
 */
public class B {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Digite el ancho de la matriz ");
            int tamañoMatriz = input.nextInt();

            if (tamañoMatriz < 0 || tamañoMatriz > 100000) {
                throw new InputMismatchException("El valor ingresado esta fuera de los limites permitidos");
            }

            long[][] matriz = new long[tamañoMatriz][tamañoMatriz];
            long sumaDiagonal = 0;

            for (int i = 0; i < tamañoMatriz; i++) {
                for (int j = 0; j < tamañoMatriz; j++) {
                    if (j < tamañoMatriz - 1 - i) {
                        matriz[i][j] = 1;
                    } else if (j > tamañoMatriz - 1 - i) {
                        matriz[i][j] = 0;
                    } else {
                        long valor = tamañoMatriz - i;
                        matriz[i][j] = valor;
                        sumaDiagonal += valor;
                    }
                }
            }

            imprimirDatos(tamañoMatriz, matriz, sumaDiagonal);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void imprimirDatos(int tamañoMatriz, long[][] matriz, long sumaDiagonal) {
        for (int i = 0; i < tamañoMatriz; i++) {
            for (int j = 0; j < tamañoMatriz; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(sumaDiagonal);
    }

}
