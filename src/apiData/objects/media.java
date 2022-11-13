package apiData.objects;

public class Media {
    public int id;
    public String mediaURL;
    public String mediaDate;
    public String mediaRating;
    public int userId;

    public int getId(){
        return this.id;
    }
    public String getMediaURL(){
        return this.mediaURL;
    }
    public String getMediaDate(){
        return this.mediaDate;
    }
    public String getMediaRating(){
        return this.mediaRating;
    }
    public int getUserId(){
        return this.userId;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setMediaURL(String mediaURL){
        this.mediaURL=mediaURL;
    }
    public void setMediaDate(String mediaDate){
        this.mediaDate=mediaDate;
    }
    public void setMediaRating(String mediaRating){
        this.mediaRating=mediaRating;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }

    
}
