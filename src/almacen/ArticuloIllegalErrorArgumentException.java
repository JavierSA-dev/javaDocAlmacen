package almacen;

/**
 * Se lanza si un argumento de manera ilegal
 * @author Javier Sánchez López
 *
 */

public class ArticuloIllegalErrorArgumentException extends Exception  {

  private static final long serialVersionUID = 1L;
  /**
   * Usa la super clase para mostrar una cadena
   * @param message
   */
  public ArticuloIllegalErrorArgumentException(String message) {
    super(message);
    
  }

  

}
