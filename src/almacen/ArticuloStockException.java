package almacen;
/**
 * Se lanza si el stock de seguridad supera al máximo
 * @author Javier Sánchez López
 *
 */

public class ArticuloStockException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  /**
   * Usa la super clase para mostrar una cadena
   * @param string
   */
  public ArticuloStockException(String string) {
    super(string);
  }

}
