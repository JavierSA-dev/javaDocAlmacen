package menuUtilArrayList;

import java.util.Scanner;


public class Menu extends ArrayList<String> {

  private String title;
  private ArrayList<String> options = new ArrayList<String>();
  private Scanner sc = new Scanner(System.in);


  public Menu(String... content) {
    this.title = content[0];
    for (String element : content) {

      this.options.add(element);

    }
    this.options.remove(0);
  }


  @Override
  public void add(String element) {
    this.options.add(element);
  }

  public int choose() {
    
    System.out.println(this);
    
    System.out.print("Escoge una opción: ");
    int choice = sc.nextInt();
    
    if (choice < 0 || choice > options.size()) {
      
      throw new IllegalArgumentException("Parámetro incorrecto.");
     
    }

    return choice;
  }

  @Override
  public String toString() {
    StringBuilder formatOptions = new StringBuilder();

    for (int i = 0; i < options.size(); i++) {

      formatOptions.append(options.get(i));
      formatOptions.append("\n");

    }
    return title + "\n" + "--------" + "\n" + formatOptions;

  }
}


