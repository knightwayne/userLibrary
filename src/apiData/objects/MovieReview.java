package apiData.objects;

public class MovieReview extends Media {
    public String movieTitle;
    public String movieCritic;
    public String movieSummary;

    public String getMovieTitle(){
        return this.movieTitle;
    }
    public String getMovieCritic(){
        return this.movieCritic;
    }
    public String getMovieSummary(){
        return this.movieSummary;
    }
    
    public void setMovieTitle(String movieTitle){
        this.movieTitle=movieTitle;
    }
    public void setMovieCritic(String movieCritic){
        this.movieCritic=movieCritic;
    }
    public void setMovieSummary(String movieSummary){
        this.movieSummary=movieSummary;
    }
}
