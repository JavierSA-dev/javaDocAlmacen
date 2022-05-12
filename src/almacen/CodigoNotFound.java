package almacen;
/**
 * Se lanza cuando el código pasado no corresponde a ningún artículo
 * @author Javier Sánchez López
 *
 */
public class CodigoNotFound extends Exception {

  private static final long serialVersionUID = 1L;
  /**
   * Usa la super clase para mostrar una cadena
   * @param string
   */
  public CodigoNotFound(String string) {
    super(string);
  }

}
