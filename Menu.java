import java.util.Scanner;

class Menu {
    static int menu(CatalogOfMovies cat) {
        Scanner scan = new Scanner(System.in);
        String strUserChoice;
        System.out.println(
                "Welcome to NoTFLaX, a movie catalog service \n" +
                "Above are our current movie selections \n" +
                "Enter 1 to shuffle a new set of 30 movies \n" +
                "Enter 2 to sort by year \n" +
                "Enter 3 to sort by length \n" +
                "Enter 4 to sort by rating \n" +
                "Enter 5 to sort by name \n" +
                "Enter 6 to search by genre \n" +
                "Enter 7 to search by name \n" +
                "Enter 8 to add a movie \n" +
                "Enter 0 to exit \n");

        int intUserChoice = scan.nextInt();                 //  get option as int
        scan.nextLine();                                    //  clear buffer
        switch (intUserChoice) {
            case 1: cat = new CatalogOfMovies(); break;     //  replace with new catalog of movies
            case 2: cat.sortByYear(); break;
            case 3: cat.sortByRuntime(); break;
            case 4: cat.sortByRating(); break;
            case 5: cat.sortByTitle(); break;
            case 6: {
                System.out.println("\n\nWhat genre do you want to browse?\n");
                strUserChoice = scan.nextLine();    //  read line into string
                cat.searchGenre(strUserChoice);     //  call genre search method
                return 9;
            }
            case 7: {
                System.out.println("\n\nWhat movie do you want to find?\n");
                strUserChoice = scan.nextLine();        //  read line into string
                cat.searchMovieTitle(strUserChoice);    //  call title search method
                System.out.println();                   //  blank line for formatting
                return 9;
            }
            case 8: {
                System.out.println("To add a movie, I'll ask for some details.\n" +
                        "First, what's the movie TITLE?: ");
                String name = scan.nextLine();
                //  read line into string until not blank
                while (name.equalsIgnoreCase("")) {
                    System.out.println("\nA movie needs a name, give me *something* : ");
                    name = scan.nextLine();
                }
                System.out.println("\nWhat YEAR did the movie come out?: ");
                int year = scan.nextInt(); scan.nextLine();
                //  read first int token until within range
                while (year < 1920 || year > 2017) {
                    System.out.println("\nNot a valid year, try again: ");
                    year = scan.nextInt(); scan.nextLine();
                }
                System.out.println("\nHow LONG is the movie?: ");
                //  get int, clear buffer;
                int runtime = scan.nextInt(); scan.nextLine();
                //  read first int token until within range
                while (runtime < 60 || runtime > 200) {
                    System.out.println("\nNot a valid runtime, try again: ");
                    runtime = scan.nextInt(); scan.nextLine();
                }
                System.out.println("\nWhat GENRE is the movie?: ");
                String genre = scan.nextLine();
                //  read line until line matches a genre
                while (!Movie.matchAnyGenre(genre)) {
                    System.out.println("\nNot a valid genre, try again: ");
                    genre = scan.nextLine();
                }
                System.out.println("\nHow do you RATE the movie? (1 to 10): ");
                //  get int, clear buffer
                int rated = scan.nextInt(); scan.nextLine();
                //  read first int token until within range
                while (rated < 1 || rated > 10) {
                    System.out.println("\nNot a valid rating, try again: ");
                    rated = scan.nextInt(); scan.nextLine();
                }
                //  call the add method with the user values
                cat.add(new Movie(name, year, runtime, genre, (double) rated));
                break;
            }
            default:
                break;
        }
        return intUserChoice;
    }
}
