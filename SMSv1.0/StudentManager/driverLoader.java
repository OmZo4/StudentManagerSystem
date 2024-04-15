import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;  //added this one for array init
import java.util.ArrayList;

public class driverLoader {

    
    private Connection con; // Declaring Connection object at class level
    
    private ArrayList<String> namelist = new ArrayList<String>();
    private ArrayList<String> degreelist = new ArrayList<String>();
    private ArrayList<Integer> agelist = new ArrayList<Integer>();
    private ArrayList<Long> idlist = new ArrayList<Long>();

    String DBname;
    String ip;
    String table_name;
    //LoginPage LoginPage = new LoginPage();



    // add variable ip, DBname, table_name
    public driverLoader(boolean statusIsON, String ip, String DBname) {
        try {
            // Creating the connection only if statusIsON is true
            if (statusIsON) {
                // Setting up the JDBC to MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");  
                con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+DBname, "OMZO", "OMZO");
                StudentManager.loggedIn = true;
                //  ---------------------------lancer ici fenetre principal
                
                //  take all data and put it in to the arrays ----------to do
            }
        } catch (Exception ex) {
            // Handle the error
            System.out.println("Connnexion error");
        }
    }

    public void list(String table){
        try {
            String query = "SELECT Fullname, degree, age, ID FROM "+table;
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("Fullname");
                String degree = resultSet.getString("degree");
                int age = resultSet.getInt("age");
                Long ID = resultSet.getLong("ID");
                namelist.add(name);
                degreelist.add(degree);
                agelist.add(age);
                idlist.add(ID);
            }
            
        } catch (Exception e) {
            System.out.println("Error in the array creation");
        }
    }

    public ArrayList<Integer> getAgeList(){
        return agelist;
    }
    public ArrayList<String> getnameList(){
        return namelist;
    }
    public ArrayList<Long> getIdList(){
        return idlist;
    }
    public ArrayList<String> getDegreeList(){
        return degreelist;
    }

    public void eraseAll(String table_name){
        try {
            String query = "TRUNCATE TABLE "+ table_name;
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void syncDB(String table_name){
        try {
            String query = "TRUNCATE TABLE "+ table_name;
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Database syncronised succefully! ");
        } catch (Exception e) {
            System.out.println("Error in the delete db process");
        }
    }

    public void closeConnection(){
        try {
            // Closing conection
            con.isClosed();
            
        } catch (Exception e) {
            System.out.println("Error while trying to close!! ");
        }
    }

    //testing only with string and age for now
    public void SaveDB(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist, String table_name){
        try {
            int i;
            for (i = 0; i < namelist.size(); i++){
                String insertQuery = "INSERT INTO "+table_name+" (Fullname, degree, age, ID) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setString(1, namelist.get(i)); // Set the value for the first parameter (Fullname)
                pstmt.setString(2, degreelist.get(i)); // Set the value for the second parameter (Degree)
                pstmt.setInt(3, agelist.get(i)); // Set the value for the third parameter (age)
                pstmt.setLong(4, idlist.get(i)); // Set the value for the fourth parameter (idlist)
                pstmt.executeUpdate();
            }

            // DO THE SAME FOR IDLIST AND DEGREELIST
            System.out.println("Values saved successfully!");


        } catch (Exception e) {
            System.out.println(e);
            //"ERROR IN THE MySQL DATA MANILPULATION !!!"
        }
    }


}


