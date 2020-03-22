package base;

import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author Daniel Migales
 */
public class Main {
    
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        
        Gestor gestion = new Gestor();
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        boolean salir = true;
        
        do {            
            System.out.println("\n*********MENU PRINCIPAL*********\n");
            System.out.println("1. Realizar scrapping de peliculas e imprimir el resultado en pantalla.)");
            System.out.println("2. Salir del programa.");
            System.out.print("\nIntroduzca su seleccion : ");
             seleccion = teclado.nextInt();
            
            switch (seleccion) {
                case 1:
                    System.out.println("\nLISTADO DE PELICULAS ENCONTRADAS: ");
                    gestion.scrappingHtml();
                    System.out.println("\nSE HA GENERADO UN ARCHIVO XML CON EL RESULTADO.");
                    gestion.crearXML();
                    break;
                case 2:  
                    salir = false;
                    break;
            }
        } while (salir);
        
    }
}
