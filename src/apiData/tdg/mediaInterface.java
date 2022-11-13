package apiData.tdg;

public interface mediaInterface {

    public void create(int userId, int rating, String query);
    
    public void read(String query);

    public void update(String queryValue, String lhs, String rhs);

    public void delete(String lhs, String rhs);
    
}
