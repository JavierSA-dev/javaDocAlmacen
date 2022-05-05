package almacen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * 
 * Clase Almacén realiza el alta, baja, modificación de artículos, incremento de unidades y
 * decrementación de las mismas
 * 
 * <p>
 * El estado es un Arraylist de la clase {@link Articulo Artículo}
 * </p>
 * 
 * <p>
 * Comportamiento:
 * <ul>
 * <li>Añadir artículos</li>
 * <li>Eliminar artículos</li>
 * <li>Incrementar las existencias de un artículos</li>
 * <li>Decrementar las existencias de un artículo</li>
 * <li>Devolver un artículo</li>
 * <li>Mostrar Almacén (toString)</li>
 * </ul>
 * </p>
 * 
 * @author Javier Sánchez López
 * 
 */


public class Almacen {

  private List<Articulo> almacen = new ArrayList<>();

  /**
   * 
   * Añade artículos al arraylist
   * 
   * @param nombre
   * @param marca
   * @param precioDeCompra
   * @param precioDeVenta
   * @param numeroDeUnidades
   * @param stockDeSeguridad
   * @param stockMaximo
   * @throws AlmacenNombreMarcaException
   * @throws ArticuloIllegalErrorArgumentException
   */
  void add(String nombre, String marca, double precioDeCompra, double precioDeVenta,
      int numeroDeUnidades, int stockDeSeguridad, int stockMaximo)
      throws AlmacenNombreMarcaException, ArticuloIllegalErrorArgumentException {

    lanzaExcepcionSiNombreYMarca2ProductosSonIguales(nombre, marca);
    almacen.add(new Articulo(nombre, marca, precioDeCompra, precioDeVenta, numeroDeUnidades,
        stockDeSeguridad, stockMaximo));

  }


  /**
   * 
   * Lanza una excepción si a la hora de añadir un artículo el nombre y la marca son iguales.
   * 
   * @param nombre
   * @param marca
   * @throws AlmacenNombreMarcaException
   */
  private void lanzaExcepcionSiNombreYMarca2ProductosSonIguales(String nombre, String marca)
      throws AlmacenNombreMarcaException {
    for (Articulo art : almacen) {
      if (nombre.equals(art.getNombre()) && marca.equals(art.getMarca())) {
        throw new AlmacenNombreMarcaException("El nombre y la marca no pueden ser iguales.");
      }
    }
  }

  /**
   * 
   * Borra un artículo que existe en el almacén, si no existe se lanza excepción.
   * 
   * @param codigo
   * @throws CodigoNotFound
   */
  public void delete(int codigo) throws CodigoNotFound {
    if (!containsCod(codigo)) {
      throw new CodigoNotFound("El código " + codigo + " no existe en el almacén.");
    }
    almacen.removeIf(art -> art.getCodigo() == codigo);
  }

  /**
   *
   * Comprueba que el codigo pasado hace referencia a un artículo que existe en el almacén.
   * 
   * @param codigo
   * @return
   */
  private boolean containsCod(int codigo) {
    for (Articulo art : almacen) {
      if (art.getCodigo() == codigo) {
        return true;
      }
    }
    return false;
  }


  /**
   * 
   * Incrementa las unidades de un artículo que exista en el almacén.
   * 
   * @param codigo
   * @param unidades
   * @throws CodigoNotFound
   */
  public void addUnidades(int codigo, int unidades) throws CodigoNotFound {
    for (Articulo art : almacen) {
      if (codigo == art.getCodigo()) {
        art.addUnidades(unidades);
        return;
      }
    }
    throw new CodigoNotFound("El código " + codigo + " no existe en el almacén.");
  }

  /**
   * 
   * Decrementa las unidades de un artículo que exista en el almacén.
   * 
   * @param codigo
   * @param unidades
   * @throws CodigoNotFound
   * @throws ArticuloStockException
   */
  public void removeUnidades(int codigo, int unidades)
      throws CodigoNotFound, ArticuloStockException {
    for (Articulo art : almacen) {
      if (codigo == art.getCodigo()) {
        art.eliminarUnidades(unidades);
        return;
      }
    }
    throw new CodigoNotFound("El código " + codigo + " no existe en el almacén.");
  }


  /**
   * 
   * Modifica cualquier parámetro de un artículo existente en el almacén.
   * 
   * @param codigo
   * @param nombre
   * @param marca
   * @param precioDeCompra
   * @param precioDeVenta
   * @param numeroDeUnidades
   * @param stockDeSeguridad
   * @param StockMaximo
   * @throws AlmacenNombreMarcaException
   * @throws CodigoNotFound
   * @throws ArticuloIllegalErrorArgumentException
   */
  public void modificarArticulo(int codigo, String nombre, String marca, double precioDeCompra,
      double precioDeVenta, int numeroDeUnidades, int stockDeSeguridad, int StockMaximo)
      throws AlmacenNombreMarcaException, CodigoNotFound, ArticuloIllegalErrorArgumentException {

    for (Articulo art : almacen) {
      if (codigo == art.getCodigo()) {
        if (art.getNombre().equals(nombre) || art.getMarca().equals(marca)) {
          throw new AlmacenNombreMarcaException("El artículo ya existe en el almacén");
        }
        art.set(nombre, marca, precioDeCompra, precioDeVenta, numeroDeUnidades, StockMaximo,
            stockDeSeguridad);
        return;
      }
    }
    throw new CodigoNotFound("El código " + codigo + " no existe en el almacén.");
  }
  
  /**
   * 
   * Devuelve un artículo pasándole el código si no existe da error
   * 
   * @param codigo
   * @return
   * @throws CodigoNotFound
   */
  public Articulo get(int codigo) throws CodigoNotFound {
    for (Articulo articulo : almacen) {
      if (articulo.getCodigo() == codigo) {
        return articulo;
      }
    }
    throw new CodigoNotFound("El código " + codigo + " no existe en el almacén.");
  }

  /**
   * 
   * Guarda el contenido del almacén en un archivo json
   * 
   * @param filename
   * @throws IOException
   */
  public void save(String filename) throws IOException {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String stringGson = gson.toJson(this);
    BufferedWriter bw = new BufferedWriter(new FileWriter("src/almacen/" + filename + ".json"));

    bw.write(stringGson);
    bw.close();

  }


  /**
   * 
   * Carga el contenido de un archivo json al almacén.
   * 
   * @param filename
   * @throws IOException
   */
  public void load(String filename) throws IOException {

    BufferedReader lector = new BufferedReader(new FileReader("src/almacen/" + filename + ".json"));
    StringBuilder cadena = new StringBuilder();
    String line = null;

    while ((line = lector.readLine()) != null) {
      cadena.append(line);

    }
    lector.close();

    Almacen data = new Gson().fromJson(cadena.toString(), Almacen.class);
    this.almacen = data.almacen;


  }

  /**
   * 
   * Crea un archivo XML con el contenido del almacén
   * 
   * @param archive
   * @throws ParserConfigurationException 
   * @throws TransformerFactoryConfigurationError 
   * @throws TransformerException 
   */
  void changeFormatToXml(String archive) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
  
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation implementation = builder.getDOMImplementation();

      Document documento = implementation.createDocument(null, "almacen", null);
      documento.setXmlVersion("1.0");

      Element stock = documento.createElement("Almacen");

      for (Articulo art : almacen) {
        Element articulo = documento.createElement("Articulo");

        Element nombre = documento.createElement("Nombre");
        Text textNombre = documento.createTextNode(art.getNombre());
        nombre.appendChild(textNombre);
        articulo.appendChild(nombre);

        Element marca = documento.createElement("Marca");
        Text textMarca = documento.createTextNode(art.getMarca());
        marca.appendChild(textMarca);
        articulo.appendChild(marca);

        Element precioDeCompra = documento.createElement("PrecioDeCompra");
        Text textPrecioDeCompra = documento.createTextNode("" + art.getPrecioDeCompra());
        precioDeCompra.appendChild(textPrecioDeCompra);
        articulo.appendChild(precioDeCompra);

        Element precioDeVenta = documento.createElement("PrecioDeVenta");
        Text textPrecioDeVenta = documento.createTextNode("" + art.getPrecioDeVenta());
        precioDeVenta.appendChild(textPrecioDeVenta);
        articulo.appendChild(precioDeVenta);

        Element numUnidades = documento.createElement("NumeroDeUnidades");
        Text textNumUnidades = documento.createTextNode("" + art.getNumeroDeUnidades());
        numUnidades.appendChild(textNumUnidades);
        articulo.appendChild(numUnidades);

        Element stockSeguridad = documento.createElement("StockDeSeguridad");
        Text textStockSeguridad = documento.createTextNode("" + art.getStockDeSeguridad());
        stockSeguridad.appendChild(textStockSeguridad);
        articulo.appendChild(stockSeguridad);

        Element stockMaximo = documento.createElement("StockMaximo");
        Text textStockMaximo = documento.createTextNode("" + art.getStockMaximo());
        stockMaximo.appendChild(textStockMaximo);
        articulo.appendChild(stockMaximo);

        stock.appendChild(articulo);

      }
      documento.getDocumentElement().appendChild(stock);

      Source source = new DOMSource(documento);
      Result result = new StreamResult(new File(archive));

      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.transform(source, result);
    
  }
  /**
   * 
   * Guarda el contenido del almacén en un archivo XML
   * 
   * @param filename
   * @throws IOException
   */
  public void loadFromXml(String filename) throws IOException {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document documento = builder.parse(new File(filename));

      documento.getDocumentElement().normalize();

      NodeList nodes = documento.getElementsByTagName("Articulo");

      for (int i = 0; i < nodes.getLength(); i++) {
        Node node = nodes.item(i);
        Element articulo = (Element) node;

        String nombre = articulo.getElementsByTagName("Nombre").item(0).getTextContent();
        String marca = articulo.getElementsByTagName("Marca").item(0).getTextContent();
        double precioDeCompra = Double
            .parseDouble(articulo.getElementsByTagName("PrecioDeCompra").item(0).getTextContent());
        double precioDeVenta = Double
            .parseDouble(articulo.getElementsByTagName("PrecioDeVenta").item(0).getTextContent());
        int numUnidades = Integer
            .parseInt(articulo.getElementsByTagName("NumeroDeUnidades").item(0).getTextContent());
        int stockSeguridad = Integer
            .parseInt(articulo.getElementsByTagName("StockDeSeguridad").item(0).getTextContent());
        int stockMaximo =
            Integer.parseInt(articulo.getElementsByTagName("StockMaximo").item(0).getTextContent());

        almacen.add(new Articulo(nombre, marca, precioDeCompra, precioDeVenta, numUnidades,
            stockSeguridad, stockMaximo));
      }

    } catch (ParserConfigurationException | SAXException
        | ArticuloIllegalErrorArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Almacen [almacen=" + almacen + "]";
  }



}
