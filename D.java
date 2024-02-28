
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author heiderarellano
 */
public class D {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("digite el valor a evaluar");
            int valor = input.nextInt();

            if (valor < 0 || valor > 1000000) {
                throw new InputMismatchException("valor incorrecto");
            }

            if (esAutomorfico(valor)) {
                System.out.println("SI");
            } else {
                System.out.println("NO");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean esAutomorfico(int n) {
        String cuadrado = String.valueOf(n * n);
        String numeroStr = String.valueOf(n);

        return cuadrado.endsWith(numeroStr);
    }

}
