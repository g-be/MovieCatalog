public class Main {
    public static void main(String[] args){
        //  make CatalogOfMovies object and input scanner
        CatalogOfMovies cat = new CatalogOfMovies();

        // start user option interface
        cat.display();
        int choice = Menu.menu(cat);
        while ( choice != 0){
            if (choice != 9)
                cat.display();
            choice = Menu.menu(cat);
        }
    }
}
