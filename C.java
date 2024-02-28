
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author heiderarellano
 */
public class C {

    public static void main(String[] args) {

       try (Scanner input = new Scanner(System.in)) {
            System.out.println("Digite la cantidad valores a evaluar ");
            int cantidadValores = input.nextInt();

            if (cantidadValores < 0) {
                throw new InputMismatchException("debe ingresar un valor positivo");
            }

            List<Long> totales = new ArrayList<>();

            for (int i = 0; i < cantidadValores; i++) {
                System.out.println("Digite la cantidad del producto ");
                int cantidad = input.nextInt();
                System.out.println("Digite el precio del producto ");
                long precio = input.nextLong();
                totales.add(cantidad * precio);
            }
            
            for (Long total : totales) {
                 if (total >= 10000 && total < 100000) {
                System.out.println("NO");
            } else {
                System.out.println("SI");
            }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
