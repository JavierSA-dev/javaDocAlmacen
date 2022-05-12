package almacen;

/**
 * Clase Artículo que representa a los artículos del almacén.
 * 
 * Su estado será: código, nombre, marca, precio de compra, precio de venta, número de unidades
 * (nunca negativas), stock de seguridad y stock máximo.
 * 
 * Como comportamiento: Consideramos que el código va a generarse de forma automática en el
 * constructor, así no puede haber dos artículos con el mismo código. Esto implica que no puede
 * modificarse el código de un artículo, sí el resto de las propiedades. Podremos mostrar el
 * artículo, por lo que necesito una representación del artículo en forma de cadena (toString).
 * 
 * 
 *
 */

public class Articulo {
  
  /**
   * Almacena el último código del último artículo existente para averiguar el código siguiente
   */
  private static int ultimoCodigo = 0;
  /**
   * Código único identificador del artículo
   */
  private int codigo;
  /**
   * Nombre del artículo
   */
  private String nombre;
  /**
   * Marca del artículo
   */
  private String marca;
  /**
   * Precio de compra del artículo
   */
  private double precioDeCompra;
  /**
   * Precio de venta del artículo
   */  
  private double precioDeVenta;
  /**
   * Número de unidades del artículo
   */  
  private int numeroDeUnidades;
  /**
   * Stock de seguridad del artículo
   */  
  private int stockDeSeguridad;
  /**
   * Stock máximo del artículo
   */
  private int stockMaximo;

  /**
   * 
   * Crea un artículo 
   * 
   * @param nombre Nombre del artículo
   * @param marca Marca del artículo
   * @param precioDeCompra Precio de compra del artículo
   * @param precioDeVenta Precio de venta del artículo
   * @param numeroDeUnidades Número de unidades del artículo
   * @param stockDeSeguridad Stock de seguridad del artículo
   * @param stockMaximo Stock máximo del artículo
   * @throws ArticuloIllegalErrorArgumentException 
   */
  Articulo(String nombre, String marca, double precioDeCompra, double precioDeVenta,
      int numeroDeUnidades, int stockDeSeguridad, int stockMaximo) throws ArticuloIllegalErrorArgumentException {
    codigo = ++ultimoCodigo;
    setNombre(nombre);
    setMarca(marca);
    setPrecioDeCompra(precioDeCompra);
    setPrecioDeVenta(precioDeVenta);
    setNumeroDeUnidades(numeroDeUnidades);
    setStockDeSeguridad(stockDeSeguridad);
    setStockMaximo(stockMaximo);
  }
  /**
   * 
   * Crea un artículo con el stockMaximo a 0.
   * 
   * @param nombre Nombre del artículo
   * @param marca Marca del artículo
   * @param precioDeCompra Precio de compra del artículo
   * @param precioDeVenta Precio de venta del artículo
   * @param numeroDeUnidades Número de unidades del artículo
   * @param stockDeSeguridad Stock de seguridad del artículo
   * @throws ArticuloIllegalErrorArgumentException 
   */
  Articulo(String nombre, String marca, double precioDeCompra, double precioDeVenta,
      int numeroDeUnidades, int stockDeSeguridad) throws ArticuloIllegalErrorArgumentException { // stockmaximo vale 0
    this(nombre, marca, precioDeCompra, precioDeVenta, numeroDeUnidades, stockDeSeguridad, 0);
  }
  /**
   * Crea un artículo con el stockMaximo a 0 y mínimo a 0.
   * 
   * @param nombre Nombre del artículo
   * @param marca Marca del artículo
   * @param precioDeCompra Precio de compra del artículo
   * @param precioDeVenta Precio de venta del artículo
   * @param numeroDeUnidades Número de unidades del artículo
   * @throws ArticuloIllegalErrorArgumentException 
   */
  Articulo(String nombre, String marca, double precioDeCompra, double precioDeVenta,
      int numeroDeUnidades) throws ArticuloIllegalErrorArgumentException { // stockmaximo y mínimo vale 0
    this(nombre, marca, precioDeCompra, precioDeVenta, numeroDeUnidades, 0, 0);
  }

  /**
   * 
   * 
   * @param code Código identificador único
   * @param nombre Nombre del artículo
   * @param marca Marca del artículo
   * @param precioDeCompra Precio de compra del artículo
   * @param precioDeVenta Precio de venta del artículo
   * @param units Número de unidades del artículo
   * @param stockMinimo Stock de seguridad del artículo
   * @param stockMaximo Stock máximo del artículo
   * @throws ArticuloIllegalErrorArgumentException 
   */
  Articulo(int code, String nombre, String marca, double precioDeCompra,
      double precioDeVenta, int units, int stockMinimo, int stockMaximo) throws ArticuloIllegalErrorArgumentException {
    this(nombre, marca, precioDeCompra, precioDeVenta, units);
  }

  /**
   * 
   * @return Devuelve el código del artículo
   */
  public int getCodigo() {
    return codigo;
  }
  /**
   * 
   * @return Devuelve el nombre del artículo
   */
  public String getNombre() {
    return nombre;
  }
  /**
   * 
   * @return Devuelve el marca del artículo
   */
  public String getMarca() {
    return marca;
  }
  /**
   * 
   * @return Devuelve el precio de compra del artículo
   */
  public double getPrecioDeCompra() {
    return precioDeCompra;
  }
  /**
   * 
   * @return Devuelve el precio de venta del artículo
   */
  public double getPrecioDeVenta() {
    return precioDeVenta;
  }
  /**
   * 
   * @return Devuelve el numero de unidades del artículo
   */
  public int getNumeroDeUnidades() {
    return numeroDeUnidades;
  }
  /**
   * 
   * @return Devuelve el stock de seguridad del artículo
   */
  public int getStockDeSeguridad() {
    return stockDeSeguridad;
  }
  /**
   * 
   * @return Devuelve el stock máximo del artículo
   */
  public int getStockMaximo() {
    return stockMaximo;
  }
  
  /**
   * Cambia el nombre del artículo
   * 
   * @param nombre Nombre que va a sustituir al nombre anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el nombre está en blanco
   */
  void setNombre(String nombre) throws ArticuloIllegalErrorArgumentException {
    if (nombre == null || nombre.isBlank()) {
      throw new ArticuloIllegalErrorArgumentException("El nombre del artículo no puede estar vacío.");
    }
    this.nombre = nombre;
  }
  /**
   * Cambia la marca del artículo
   * 
   * @param marca marca que va a sustituir al nombre anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si la marca está en blanco
   */
  void setMarca(String marca) throws ArticuloIllegalErrorArgumentException {
    if (marca == null || marca.isBlank()) {
      throw new ArticuloIllegalErrorArgumentException("La marca no puede estar vacía.");
    } 
    this.marca = marca;
  }
  /**
   * Cambia el precio de compra
   * 
   * @param precioDeCompra Precio de compra que va a sustituir al precio de compra anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el precio de compra es menor a 0
   */
  void setPrecioDeCompra(double precioDeCompra) throws ArticuloIllegalErrorArgumentException {
    if (precioDeCompra < 0) {
      throw new ArticuloIllegalErrorArgumentException("El precio de compra no puede ser menor a 0.");
    }
    this.precioDeCompra = precioDeCompra;
  }
  /**
   * Cambia el precio de venta
   * 
   * @param precioDeVenta precio de venta que va a sustituir al precio de venta anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el precio de venta es menor a 0
   */
  void setPrecioDeVenta(double precioDeVenta) throws ArticuloIllegalErrorArgumentException {
    if (precioDeVenta < 0) {
      throw new ArticuloIllegalErrorArgumentException("El precio de venta no puede ser menor a 0.");
    }
    this.precioDeVenta = precioDeVenta;
  }
  /**
   * Cambia el número de unidades
   * 
   * @param numeroDeUnidades numeroDeUnidades que va a sustituir al numero de unidades anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el precio de venta es menor a 0
   */
  void setNumeroDeUnidades(int numeroDeUnidades) throws ArticuloIllegalErrorArgumentException {
    lanzaExcepcionSiUnidadesNegativas(numeroDeUnidades);
    this.numeroDeUnidades = numeroDeUnidades;
  }
  /**
   * Cambia el stock de seguridad
   * 
   * @param stockDeSeguridad stock de seguridad que va a sustituir al stock de seguridad anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el stock de seguridad es menor a 0 o mayor que el stock máximo
   */
  void setStockDeSeguridad(int stockDeSeguridad) throws ArticuloIllegalErrorArgumentException {
    if (stockDeSeguridad < 0) {
      throw new ArticuloIllegalErrorArgumentException("El stock de seguridad no puede ser menor a 0.");
    }

    if (this.stockMaximo != 0 && this.stockMaximo < stockDeSeguridad) {
      throw new ArticuloIllegalErrorArgumentException(
          "El stock de seguridad no puede ser mayor al stockMáximo.");
    }
    this.stockDeSeguridad = stockDeSeguridad;
  }
  /**
   * Cambia el stock de maximo
   * 
   * @param stockMaximo stock maximo que va a sustituir al stock maximo anterior
   * @throws ArticuloIllegalErrorArgumentException Se lanza si el stock máximo es menor a 0 o menor que el stock de seguridad
   */
  void setStockMaximo(int stockMaximo) throws ArticuloIllegalErrorArgumentException {
    if (stockMaximo < 0) {
      throw new ArticuloIllegalErrorArgumentException("El stock máximo no puede ser menor a 0.");
    }

    if (this.stockDeSeguridad != 0 && this.stockDeSeguridad > stockMaximo) {
      throw new ArticuloIllegalErrorArgumentException(
          "El stock máximo no puede ser menor al stockDeSeguridad.");
    }
    this.stockMaximo = stockMaximo;
  }

  // metodos entrada, salida de mercancias y set para modificar
  // metodo que será llamado por otro método en la clase almacén
  /**
   * Añade unidades al Artículo
   * 
   * @param unidades Numero de unidades a incrementar
   * @throws ArticuloIllegalErrorArgumentException Lanza excepción si el número de unidades no es válido
   */
  public void addUnidades(int unidades) throws ArticuloIllegalErrorArgumentException {
    lanzaExcepcionSiUnidadesNegativas(unidades);
    this.numeroDeUnidades += unidades;
  }
  /**
   * 
   * Decrementa unidades al artículo, si intentas decrementar más unidades de las que tiene se lanza una excepción
   * 
   * @param unidades Numero de unidades a decrementar
   * @throws ArticuloIllegalErrorArgumentException  
   */
  public void eliminarUnidades(int unidades) throws  ArticuloIllegalErrorArgumentException {
    lanzaExcepcionSiUnidadesNegativas(unidades);
    if (this.numeroDeUnidades - unidades < 0) {
      throw new ArticuloStockException( 
          "No hay unidades suficientes para decrementar " + unidades + " unidades."); //excepcion marcada
    }
    this.numeroDeUnidades -= unidades;
  }
  /**
   * 
   * Se usa para modificar el valor de los atributos de un artículo
   * 
   * @param nombre Nombre del artículo
   * @param marca Marca del artículo
   * @param precioDeCompra Precio de compra del artículo
   * @param precioDeVenta Precio de venta del artículo
   * @param numeroDeUnidades Número de unidades del artículo
   * @param stockDeSeguridad Stock de seguridad del artículo
   * @param stockMaximo Stock máximo del artículo
   * @throws ArticuloIllegalErrorArgumentException 
   */
  public void set(String nombre, String marca, double precioDeCompra, double precioDeVenta,
      int numeroDeUnidades, int stockMaximo, int stockDeSeguridad) throws ArticuloIllegalErrorArgumentException {
    setNombre(nombre);
    setMarca(marca);
    setPrecioDeCompra(precioDeCompra);
    setPrecioDeVenta(precioDeVenta);
    setNumeroDeUnidades(numeroDeUnidades);
    setStockMaximo(stockMaximo); 
    setStockDeSeguridad(stockDeSeguridad);
  }

  
  /**
   * 
   * Lanza excepción si pones como atributo de unidades menor que cero
   * 
   * @param numeroDeUnidades Numero de unidades incrementar
   * @throws ArticuloIllegalErrorArgumentException Se lanza si las unidades a incrementar son menores de 0
   */
  private void lanzaExcepcionSiUnidadesNegativas(int numeroDeUnidades) throws ArticuloIllegalErrorArgumentException {
    if (numeroDeUnidades < 0) {
      throw new ArticuloIllegalErrorArgumentException("Las unidades no pueden ser menores a 0.");
    }
  }

  @Override
  public String toString() {
    return " \nArticulo [codigo=" + codigo + ", nombre=" + nombre + ", marca=" + marca
        + ", precioDeCompra=" + precioDeCompra + ", precioDeVenta=" + precioDeVenta
        + ", numeroDeUnidades=" + numeroDeUnidades + ", stockDeSeguridad=" + stockDeSeguridad
        + ", stockMaximo=" + stockMaximo + "]";
  }


}
