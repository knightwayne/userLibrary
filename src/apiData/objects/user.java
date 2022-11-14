package apiData.objects;

public class User {

    private int id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;

    public int getId(){
        return this.id;
    };
    public String getName(){
        return this.userName;
    };
    public String getEmail(){
        return this.userEmail;
    };
    public String getPassword(){
        return this.userPassword;
    };
    public String getAddress(){
        return this.userAddress;
    };

    public void setId(int id){
        this.id=id;
    };
    public void setName(String name){
        this.userName=name;
    };
    public void setEmail(String email){
        this.userEmail=email;
    };
    public void setPassword(String password){
        this.userPassword=password;
    };
    public void setAddress(String address){
        this.userAddress=address;
    };
}
