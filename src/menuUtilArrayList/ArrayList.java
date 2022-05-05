package menuUtilArrayList;

import java.util.Arrays;

public class ArrayList<T> implements Cloneable{

  protected static final int DEFAULT_SIZE = 10;

  @SuppressWarnings("unchecked")
  private T[] content = (T[]) new Object[DEFAULT_SIZE]; // array donde almacenamos los elementos de la lista
  private int contentSize = 0;  // tama�o de la lista (n� de elementos almacenados)

  /**
   * Crea una lista con los valores que llegan como par�metro.
   * @param content lista de enteros que conforman la lista.
   * @return 
   */
  public ArrayList(@SuppressWarnings("unchecked") T ... content) {
    for (T element : content) {  // metemos los valores llegados como par�metros en el array
      add(element);
    }
  }

  /**
   * A�ade un elemento al final de la lista. 
   * @param element elemento a a�adir
   */
  public void add(T element) {
    if (isFull()) {
      expand();
    }
    content[contentSize++] = element;
  }
  
  /**
   * A�ade un elemento en la posici�n indicada de la lista.
   * @param element elemento a a�adir
   * @param index �ndice donde hay que a�adir element
   */
  public void add(int index, T element) {  
    throwExceptionIfIndexOutOfBounds(index);
    scrollListToRightFrom(index);
    content[index] = element;
  }
  
 

  /**
   * Desplaza la lista una posici�n a la derecha a partir de la posici�n indicada
   * y aumenta su tama�o en uno.
   * @param index posici�n a partir de la cual hay que desplazar la lista.
   */
  private void scrollListToRightFrom(int index) {
    if (isFull()) {
      expand();
    }
    for (int i = contentSize-1; i >= index; i--) {
      content[i+1] = content[i];
    }
    contentSize++;
  }
  
  /**
   * Ampl�a el tama�o del vector que almacena la lista porque est� llena. 
   */
  private void expand() {
    content = Arrays.copyOf(content, content.length + DEFAULT_SIZE);
  }
  
  /**
   * Devuelve el elemento de la lista que est� en la posici�n pasada como par�metro.
   * @param index
   * @return elemento de la lista en la posici�n index
   */
  public T get(int index) {
    throwExceptionIfIndexOutOfBounds(index);
    return content[index];
  }
  
  /**
   * Reemplaza el elemento que est� en la posici�n index por element.
   * @param index
   * @param element
   * @return elemento sustituido.
   */
  public T set(int index, T element) {
    T elementOld = get(index);
    content[index] = element;
    return elementOld;
  }
  
  /**
   * Borra el elemento que est� en la posici�n index.
   * @param index
   * @return elemento borrado.
   */
  public T remove(int index) {
    T elementDeleted = get(index);
    scrollListToLeftFrom(index + 1);
    return elementDeleted;
  }
  
  /**
   * Desplaza la lista una posici�n a la izquierda a partir de la posici�n indicada
   * y disminuye su tama�o en uno.
   * @param index posici�n a partir de la cual hay que desplazar la lista.
   */  
  private void scrollListToLeftFrom(int index) {
    for (int i = index; i < contentSize; i++) {
      content[i-1] = content[i];
    }
    contentSize--;
  }
  
  /**
   * Devuelve la posici�n de la lista donde se encuentra element.
   * @param element
   * @return posici�n de element o -1 si no est�.
   */
  public int indexOf(int element) {
    for (int index = 0; index < contentSize; index++) {
      if (content[index].equals(element)) {
        return index;
      }
    }
    return -1;
  }
  
  public boolean contains(int element) {
    return indexOf(element) != -1;
  }
  
  /**
   * Borra la primera ocurrencia de un elemento en la lista.
   * @param element
   * @return �xito de la operaci�n.
   */
  public boolean removeInt(int element) {
    int indexElement = indexOf(element);
    if (indexElement == -1) {
      return false;
    }
    remove(indexElement);
    return true;
  }
 
  /**
   * Vac�a la lista.
   */
  public void clear() {
    contentSize = 0;
  }
  
  /**
   * @return si el array de la lista est� lleno.
   */
  private boolean isFull() {
    return content.length == contentSize;
  }
  
  /**
   * @return si la lista est� vac�a.
   */
  public boolean isEmpty() {
    return contentSize == 0;
  }
    
  /**
   * @return número de elementos que hay en la lista.
   */
  public int size() {
    return contentSize;
  }
  
  /**
   * Ordena la lista.
   */
  public void sort() {
    T[] list = Arrays.copyOf(content, contentSize);
    Arrays.sort(list);
    content = Arrays.copyOf(list, content.length);
  }

  /**
   * Si index est� fuera de los l�mites del array lanza una excepci�n.
   * @param index
   * @throws IndexOutOfBoundsException
   */
  private void throwExceptionIfIndexOutOfBounds(int index) {
    if (index < 0 || index >= contentSize) {
      throw new IndexOutOfBoundsException("Posici�n fuera de los l�mites de la lista.");
    }
  }
  
  @Override
  public String toString() {
    T[] list = Arrays.copyOf(content, contentSize);
    return Arrays.toString(list);
  }
  
  @Override
  public ArrayList<T> clone() {
    return new ArrayList<T>(Arrays.copyOf(content, contentSize));
  }

  @Override
  public int hashCode() { // ha sido necesario modificar lo generado por eclipse
    final int prime = 31;
    int result = 1;
    T[] list = Arrays.copyOf(content, contentSize);
    result = prime * result + Arrays.hashCode(list);
    result = prime * result + contentSize;
    return result;
  }





  /*@Override
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    IntArrayList other = (IntArrayList) obj;
    if (contentSize != other.contentSize)
      return false;
    int[] listThis = Arrays.copyOf(content, contentSize);
    int[] listOther = Arrays.copyOf(other.content, other.contentSize);
    if (!Arrays.equals(listThis, listOther))
      return false;
    return true;
  }*/
  
}
