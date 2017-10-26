class RandomMovie {

    static Movie grab() {
        java.util.Random rand = new java.util.Random();
        Movie m = new Movie();

        m.setTitle(GenerateName.generate());
        m.setYear(rand.nextInt(2017-1920)+1920);
        m.setRuntime(rand.nextInt(200-60)+60);
        double rating = rand.nextDouble()*10+.1;
        m.setRating(rating-(rating%0.1));
        int r = rand.nextInt(4);
        switch (r){
            case 0: m.setGenre("Comedy"); break;
            case 1: m.setGenre("Drama"); break;
            case 2: m.setGenre("SciFi"); break;
            case 3: m.setGenre("Action"); break;
            case 4: m.setGenre("Doc"); break;
            default: System.out.println("error at switch statement");
        }
        return m;
    }

    static Movie grab(int year, int genreInt) {
        //uses above method to generate a basic random movie,
        // then modifies with the given values if they are within range
        Movie m = grab();
        if (year>1920 && year<2017)
            m.setYear(year);
        if (genreInt>=0 && genreInt<=4){
            switch (genreInt){
                case 0: m.setGenre("Comedy"); break;
                case 1: m.setGenre("Drama"); break;
                case 2: m.setGenre("SciFi"); break;
                case 3: m.setGenre("Action"); break;
                case 4: m.setGenre("Doc"); break;
                default: System.out.println("improper genre");
            }
        }
        return m;
    }
}
