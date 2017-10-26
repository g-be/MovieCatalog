import java.util.Random;

public class CatalogOfMovies {

    private final int MOVIES = 30;                      //  size of catalog
    private Movie[] movieArray = new Movie[MOVIES];     //  array of movie objects
    private int addCounter = 0;

    //  table (2d array) of set of pre-determined movie properties ( year, genre, x MOVIES )
    private int[][] moviePropertiesTable = new int[movieArray.length][2];


    CatalogOfMovies() {
        //  an array of years ( year = index + 1920)
        boolean[] yearsTrackerArray = new boolean[(2017 - 1920)];

        //  an array of genres ( each genre is an int element,  and gets incremented when selected )
        //  comedy / drama / sci-fi / action / documentary
        int[] genreTrackerArray = new int[]{0,0,0,0,0};

        Random r = new Random();
        //  loop to fill 2d array
        for (int i=0; i<moviePropertiesTable.length; i++) {
            int temp = r.nextInt(2017-1920);                                //  pick rand year
            while (yearsTrackerArray[temp]) temp = r.nextInt(2017-1920);    //  if year is taken, pick again
            moviePropertiesTable[i][0] = temp;                                    //  set year
            yearsTrackerArray[temp] = true;                                       //  update year tracker
            temp = r.nextInt(5);                                            //  pick rand genre
            while (genreTrackerArray[temp]>=10) temp = r.nextInt(5);        //  if genre is over 10, pick again
            moviePropertiesTable[i][1] = temp;                                    //  set genre
            genreTrackerArray[temp]++;                                            //  update genre tracker
        }

        //now that we have our properties, let's make movies
        for (int i=0; i<movieArray.length; i++)
            movieArray[i] = RandomMovie.grab(moviePropertiesTable[i][0]+1920, moviePropertiesTable[i][1]);
    }

    void add(Movie m){
        if ( addCounter<5 ){
            //  new movie array, slightly longer
            Movie[] temp = new Movie[movieArray.length+1];
            //  let the system copy the arrays efficiently
            System.arraycopy(movieArray, 0, temp, 0, movieArray.length);
            //  add that new movie to the end
            temp[temp.length-1] = m;
            //  rewrite the original array to the new longer one
            movieArray = temp;
            //  update the additions counter
            addCounter++;
        } else {
            System.out.println("Can't add any more movies!");
        }
    }

    void sortByTitle() {     //  bubbleSort the array of movies by title, alphabetically
        for (int i=0; i<movieArray.length; i++){        //  outer loop
            for (int j = 0; j<movieArray.length; j++){  //  inner loop
                                                        // if i.title is lex larger than j.title
                if (movieArray[i].getTitle().compareTo(movieArray[j].getTitle()) < 0){
                    Movie temp =movieArray[i];          //  hold the movie i
                    movieArray[i] = movieArray[j];      //  put movie j in place i
                    movieArray[j] = temp;               //  restore movie i
                }
            }
        }
    }

    void sortByYear(){
        //  bubbleSort the array of movies by year, oldest first
        for (int i=0; i<movieArray.length; i++){
            for (int j=0; j<movieArray.length; j++){
                if (movieArray[i].getYear() < movieArray[j].getYear()){
                    Movie temp =movieArray[i];      //  hold the movie i
                    movieArray[i] = movieArray[j];  //  put movie j in place i
                    movieArray[j] = temp;           //  restore movie i
                }
            }
        }
    }

    void sortByRuntime(){
        //  bubbleSort the array of movies by runtime, shortest first
        for (int i=0; i<movieArray.length; i++){
            for (int j=0; j<movieArray.length; j++){
                if (movieArray[i].getRuntime() < movieArray[j].getRuntime()){
                    Movie temp =movieArray[i];      //  hold the movie i
                    movieArray[i] = movieArray[j];  //  put movie j in place i
                    movieArray[j] = temp;           //  restore movie i
                }
            }
        }
    }

    void sortByGenre(){
        //  bubbleSort the array of movies by genre, alphabetically
        //  primary sort is genre, secondary sort is rating
        this.sortByRating();
        for (int i=0; i<movieArray.length; i++){
            for (int j = 0; j<movieArray.length; j++){
                // if i.title is lex larger than j.title
                if (movieArray[i].getGenre().compareTo(movieArray[j].getGenre()) < 0){
                    Movie temp =movieArray[i];      //  hold the movie i
                    movieArray[i] = movieArray[j];  //  put movie j in place i
                    movieArray[j] = temp;           //  restore movie i
                }
            }
        }
    }

    void sortByRating(){
        //  bubbleSort the array of movies by Rating, highest first
        //  primary sort is rating, secondary sort is title
        this.sortByTitle();
        for (int i=0; i<movieArray.length; i++){
            for (int j=0; j<movieArray.length; j++){
                if (movieArray[i].getRating() > movieArray[j].getRating()){
                    Movie temp =movieArray[i];      //  hold the movie i
                    movieArray[i] = movieArray[j];  //  put movie j in place i
                    movieArray[j] = temp;           //  restore movie i
                }
            }
        }
    }

    void searchGenre(String in){
        // filter by genre, only print the movie if found
        boolean aMovieFound = false;
        for (Movie aMovie : movieArray) {
            //  if given string matches exactly
            if ( aMovie.getGenre().equalsIgnoreCase(in) ){
                System.out.println(aMovie.toString());
                aMovieFound = true;
                }
            else
                //  if string matches an alternative spelling of SCI-FI
                if (    in.equalsIgnoreCase("Sci-Fi") ||
                        in.equalsIgnoreCase("SciFi") ||
                        in.equalsIgnoreCase("Science Fiction") ||
                        in.equalsIgnoreCase("Sci Fi") ) {
                    //  if genre matches the alternative spelling
                    if (    aMovie.getGenre().equalsIgnoreCase("Sci-Fi") ||
                            aMovie.getGenre().equalsIgnoreCase("SciFi") ||
                            aMovie.getGenre().equalsIgnoreCase("Science Fiction") ||
                            aMovie.getGenre().equalsIgnoreCase("Sci Fi") ) {
                        //  print movie
                        System.out.println(aMovie.toString());
                        //  flip switch
                        aMovieFound = true;
                    }

                }
            else
                //  if string matches an alternative spelling of DOCUMENTARY
                if (    in.equalsIgnoreCase("Documentary") ||
                        in.equalsIgnoreCase("Doc") ) {
                    //  if genre matches the alternative spelling
                    if (    aMovie.getGenre().equalsIgnoreCase("Documentary") ||
                            aMovie.getGenre().equalsIgnoreCase("Doc")){
                        //  print movie
                        System.out.println(aMovie.toString());
                        //  flip switch
                        aMovieFound = true;
                    }
                }
        }
        //  print not matches with a blank line for formatting
        if (!aMovieFound) System.out.println("No Matches");
        System.out.println();
    }

    void searchMovieTitle(String str){
        //  'movie found' switch
        boolean movieFound = false;

        for (Movie aMovieArray : movieArray) {
            //  if given string matches exactly
            if (aMovieArray.getTitle().equalsIgnoreCase(str)){
                System.out.println(aMovieArray.toString());
                movieFound = true;
            }
        }
        if (!movieFound)
            System.out.println("No Match");
    }

    int length(){
        return movieArray.length;
    }

    void display(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();    //stringBuilder object
        for (Movie aMovieArray : movieArray)
            out.append(aMovieArray.toString()).append('\n');  //  write movie details to stringBuilder

        int[] genres = new int[5];
        for (int[] tableArray:moviePropertiesTable)
            genres[tableArray[1]]++;

        return out.toString();
    }


}
