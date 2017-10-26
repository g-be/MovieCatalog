public class Movie {
    private String title;
    private int year;       // 1920 to 2017, inclusive
    private int runtime;    // 60 to 200, inclusive
    private String genre;   // comedy, drama, sci-fi, action, documentary
    private double rating;  // 0.1 to 10.0, inclusive

    Movie() {
        this.title = "witty Movie name here";
        this.year = 2000;
        this.runtime = 180;
        this.rating = 5;
    }

    Movie(String title) {
        this.title = title;
        this.year = 2000;
        this.runtime = 180;
        this.rating = 5;
    }

    Movie(String title, int year, int runtime, String genre, double rating) {

        this.title = title;
        if ( year >= 1920 && year <= 2017 )
            this.setYear(year);
        if ( runtime >= 60 && runtime <= 200 )
            this.runtime = runtime;
        if (matchAnyGenre(genre))
            this.genre = genre;
        if (rating >= 0 && rating <=10)
            this.rating = rating;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        if (title.length() <= 200)
            this.title = title;
    }

    int getYear() {
        return year;
    }

    void setYear(int year) {
        if (year >= 1920 && year <= 2017)
            this.year = year;
        else
            System.out.println("year must be between 1920 and 2017");
    }

    int getRuntime() {
        return runtime;
    }

    void setRuntime(int runtime) {
        if (runtime >= 60 && runtime <= 200)
            this.runtime = runtime;
        else
            System.out.println("runtime must be between 60 and 200");
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        if (matchAnyGenre(genre))
            this.genre = genre;
        else
            System.out.println("not a genre");
    }

    static boolean matchAnyGenre(String genre){
        return genre.equalsIgnoreCase("Comedy") ||
                genre.equalsIgnoreCase("Drama") ||
                genre.equalsIgnoreCase("Sci-Fi") ||
                genre.equalsIgnoreCase("SciFi") ||
                genre.equalsIgnoreCase("Sci Fi") ||
                genre.equalsIgnoreCase("Science Fiction") ||
                genre.equalsIgnoreCase("Science") ||
                genre.equalsIgnoreCase("Action") ||
                genre.equalsIgnoreCase("Doc") ||
                genre.equalsIgnoreCase("Documentary");
    }

    double getRating() {
        return rating;
    }

    void setRating(double rating) {
        if(rating > 0 && rating <= 10.0)
            this.rating = rating;
        else
            System.out.println("not a valid rating "+rating);
    }

    @Override
    public String toString() {
        return String.format(year + ", " + "%3d" + " minutes,\t" + "rating = " + "%4.1f " + "%7s" + " \t " + title, runtime, rating, genre);
    }
}