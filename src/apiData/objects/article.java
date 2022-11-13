package apiData.objects;

public class Article extends Media {
    public String articleHeadline;
    public String articleAbstract;

    public String getarticleHeadline(){
        return this.articleHeadline;
    }
    public String getarticleAbstract(){
        return this.articleAbstract;
    }

    public void setarticleHeadline(String articleHeadline){
        this.articleHeadline=articleHeadline;
    }
    public void setarticleAbstract(String articleAbStract){
        this.articleAbstract=articleAbStract;
    }



}
