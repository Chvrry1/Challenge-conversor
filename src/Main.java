import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {


        Scanner entrada = new Scanner(System.in);
        ServicioConversion servConversion = new ServicioConversion();
        String opcionP = "";
        String tipoInicial, tipoFinal;
        double montoE;
        ArrayList<String> monedas;
        ArrayList<Conversion> historial = new ArrayList<>();
        monedas = servConversion.consultarMonedas();
        int monTable = (int) ceil(sqrt(monedas.size()));
        System.out.println("++++++++++Bienvenido al convertidor de Monedas++++++++++");
        do {
            System.out.println("Opciones: Consultar monedas(m) - Convertir(c) - Consultar historial(h) - Salir(s)");
            opcionP = entrada.next();
            switch (opcionP.toLowerCase()) {
                case "m": {
                    for (int i = 0; i < monTable*7; i++) {
                        System.out.print("_");
                    }
                    for (int i = 0; i < monTable; i++) {
                        System.out.print("\n");
                        for (int j = 0; j < monTable; j++) {
                            if (monedas.size()>monTable*i+j){
                                System.out.print("| " + monedas.get(monTable*i+j) + " |");
                            }else System.out.print("|     |");
                        }
                        System.out.print("\n");
                        for (int j = 0; j < monTable*7; j++) {
                            System.out.print("_");
                        }
                    }
                    System.out.print("\n");
                }break;
                case "c": {
                    double montoF;
                    Conversion c;
                    System.out.print("Ingrese moneda a convertir: ");
                    tipoInicial = entrada.next();
                    System.out.print("Ingrese moneda a la que se va a convertir: ");
                    tipoFinal = entrada.next();
                    System.out.print("Ingrese el monto: ");
                    montoE = entrada.nextDouble();
                    try {
                        montoF = servConversion.consultarConversiones(tipoInicial,tipoFinal,montoE);
                        c = new Conversion(tipoInicial, tipoFinal, montoE, montoF);
                        System.out.println(c);
                        historial.add(c);
                    }catch (Exception e) {
                        System.out.println("Ingresa los datos correctamente");
                    }
                }break;
                case "h": {
                    for (Conversion c: historial){
                        System.out.println(c);
                    }
                    try (BufferedReader reader = new BufferedReader(new FileReader("src/historial.txt"))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            System.out.println(linea);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }break;
                case "s": break;
                default: System.out.println("Opción no válida!!- \""+opcionP+"\""); break;
            }
        } while (!opcionP.equalsIgnoreCase("s"));

        try (FileWriter writer = new FileWriter("src/historial.txt",true)) {
            for (Conversion c: historial){
                writer.write(c.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
