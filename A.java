
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author heiderarellano
 */
public class A {

    public static void main(String[] args) {
        
try (Scanner input = new Scanner(System.in)) {

            System.out.println("Por favor ingrese las coordenadas (x,y,z) del submarino numero 1 separadas por 1 espacio");
            String coordenadasSubmarinoUno = input.nextLine();

            String[] valuesSubmarinoUno = coordenadasSubmarinoUno.split(" ");
            Float x = Float.valueOf(valuesSubmarinoUno[0]);
            Float y = Float.valueOf(valuesSubmarinoUno[1]);
            Integer z = Integer.valueOf(valuesSubmarinoUno[2]);

            validarValores(x, y, z);
            
            System.out.println("Por favor ingrese las coordenadas (x,y,z) del submarino numero 1 separadas por 1 espacio");
            String coordenadasSubmarinoDos = input.nextLine();
            String[] valuesSubmarinoDos = coordenadasSubmarinoDos.split(" ");
            Float x2 = Float.valueOf(valuesSubmarinoDos[0]);
            Float y2 = Float.valueOf(valuesSubmarinoDos[1]);
            Integer z2 = Integer.valueOf(valuesSubmarinoDos[2]);
            
            validarValores(x2, y2, z2);
            
            boolean intersectan = intersectan(x, y, z, x2, y2, z2);
            if (intersectan) {
                System.err.println("SI");
            }else{
                System.out.println("NO");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validarValores(Float x, Float y, Integer z) {
        if ((x < -100000 || x > 100000)) {
            throw new InputMismatchException("El valor de x esta fuera de los limites permitidos");
        }

        if (y < -100000 || y > 100000) {
            throw new InputMismatchException("El valor de x esta fuera de los limites permitidos");
        }

        if (z < 0 || z > 10000) {
            throw new InputMismatchException("El valor de z esta fuera de los limites permitidos");
        }
    }

    private static boolean intersectan(double x1, double y1, Integer z1, double x2, double y2, Integer z2) {
        double distanciaCentros = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double sumaRadios = z1 + z2;
        return distanciaCentros <= sumaRadios;
    }
}
