package almacen;

import static menuUtilArrayList.Util.*;


import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import menuUtilArrayList.Menu;

/**
 * 
 * Clase test que prueba la clase almacén y artículo.
 * 
 * @author Javier Sánchez López
 *
 */

public class TestAlmacen {
  
  private static Almacen almacen = new Almacen();
  
  public static void main(String[] args) throws ArticuloIllegalErrorArgumentException {
    
    // rellenar almacén con datos aleatorios
    for (int i = 1; i <= 5; i++) {
      try {
        almacen.add("Artículo" + i, "Marca" + i, randomInt(1, 100), 
            randomInt(10, 100), randomInt(50, 500), 0, 50);
      } 
      catch (AlmacenNombreMarcaException e) {
        e.printStackTrace();
      }
    }
    
    /**
     * Creo un menú para interactuar con el usuario
     */
    Menu menu = new Menu("Menu de opciones", "1. Añadir artículo", "2. Eliminar artículo",
        "3. Modificar artículo", "4. Añadir existencias", "5. Eliminar existencias", "6. Guardar el almacén en fichero JSON.", "7. Cargar almacén desde fichero JSON.",
        "8. Guardar en fichero XML", "9. Restaurar desde archivo XML" , "10. Mostrar almacén", "11. Salir");
    int choice;
    do {

      choice = menu.choose();
      System.out.println();

      switch (choice) {

        case 1 -> addArticle();
        case 2 -> removeArticle();
        case 3 -> modifyArticle();
        case 4 -> addExistences();
        case 5 -> removeExistences();
        case 6 -> saveJSON();
        case 7 -> loadJSON();
        case 8 -> makeXML();
        case 9 -> loadXML();
        case 10 -> System.out.println("maricón");

      }

    } while (choice != 11);
    System.out.println("Adiós :D");
  }

  private static void loadXML() {

      try {
        almacen.loadFromXml(readStr("Introduce el nombre del fichero: "));
      } catch (IOException e) {
        
        e.printStackTrace();
      }
  
  }

  private static void makeXML() {
  
      try {
        almacen.changeFormatToXml(readStr("Introduce el nombre del fichero: "));
      } catch (ParserConfigurationException e) {
        
        e.printStackTrace();
      } catch (TransformerFactoryConfigurationError e) {
        
        e.printStackTrace();
      } catch (TransformerException e) {
        
        e.printStackTrace();
      }

  }

  private static void loadJSON() {
    
    try {
      almacen.load(readStr("Introduce el nombre del fichero: "));
    } catch (IOException e) {
      System.err.println("ERROR: Al crear el fichero.");
    }
    
  }

  private static void saveJSON() {
    
    
      try {
        almacen.save(readStr("Introduce el nombre del fichero: "));
      } catch (IOException e) {
        System.err.println("ERROR: Al crear el fichero.");
      }
   
    
  }

  private static void addArticle() {
    try {
      almacen.add(readStr("Nombre de artículo a dar de alta"), readStr("Marca"), 
         readDouble("Precio compra"), readDouble("Precio venta"), readInt("Unidades"), 
          readInt("Stock de seguridad"), readInt("Stock máximo"));
    }
    catch (AlmacenNombreMarcaException e) {
      System.err.println("ERROR: El artículo que has intentado añadir ya existe en el almacén.");
    }
    catch (ArticuloIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }
  
  private static void removeArticle() {
    try {
      almacen.delete(readInt("Código de artículo a dar de baja"));
    } 
    catch (CodigoNotFound e) {
      System.err.println("Ese código no pertenece a ningún artículo");
    } 
  }
  
  private static void modifyArticle() {
    try {
      Articulo article = almacen.get(readInt("Código de artículo a modificar"));
      System.out.println(article);
      almacen.modificarArticulo(article.getCodigo(), readStr("Nombre"), readStr("Marca"), 
          readDouble("Precio compra"), readDouble("Precio venta"), readInt("Unidades"),
          readInt("Stock de seguridad"), readInt("Stock máximo")); 
    } 
    catch (CodigoNotFound e) {
      System.err.println("Ese código no pertenece a ningún artículo");
    } 
    catch (AlmacenNombreMarcaException e) {
      System.err.println("ERROR: El nombre y marca de artículo ya existen en el almacén.");
    } 
    catch (ArticuloIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }
  
  private static void addExistences() {
    try {
      almacen.addUnidades(readInt("Código del artículo a incrementar stock"), 
          readInt("Unidades"));
    } 
    catch (CodigoNotFound e) {
      System.err.println("Ese código no pertenece a ningún artículo");
    }
    catch (ArticuloIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }
  
  private static void removeExistences() {
    try {
      almacen.removeUnidades(readInt("Código del artículo a incrementar stock"), 
          readInt("Unidades"));
    } 
    catch (CodigoNotFound e) {
      System.err.println("Ese código no pertenece a ningún artículo");
    }
    catch (ArticuloIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }

}

