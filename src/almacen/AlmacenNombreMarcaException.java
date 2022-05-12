package almacen;

/**
 * Se lanza si el nombre y la marca de un artículo son iguales
 */

public class AlmacenNombreMarcaException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Usa la super clase para mostrar una cadena
   * @param string
   */
  public AlmacenNombreMarcaException(String string) {
    super(string);
  }

}
