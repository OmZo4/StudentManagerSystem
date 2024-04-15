import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class StudentManager {

    public static boolean loggedIn = false;
    public static int choi;
    public static boolean addedsuccessful = false;
    public static String table_naame;

    public static void main(String[] args) {
        
        //Taking user choice in count
        boolean running = true;
        

        //saving MySQL DB in the arrays
        //Starting up connection with Remote Computer

        //System.out.println("Welcome to Student Manager 1.0 BY OMZO");
        
        // HERE ask user variable: ip, DBname, table_name

              
        final String DBname;
        final String ip;
        final String table_name;
        LoginPage LoginPage = new LoginPage();

        while (!LoginPage.triggered) {

            try {
                Thread.sleep(100); // Attendez 100 millisecondes avant de vérifier à nouveau
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        ip = LoginPage.getip();//LoginPage.getip();
        table_name = LoginPage.gettable();//LoginPage.gettable();
        table_naame = table_name;
        DBname = LoginPage.getDBname();//LoginPage.getDBname(); 
        
        driverLoader loader = new driverLoader(running, ip, DBname);
        //Load database into arrays
        loader.list(table_name);
        ArrayList<String> namelist = loader.getnameList();
        ArrayList<String> degreelist = loader.getDegreeList();
        ArrayList<Integer> agelist = loader.getAgeList();
        ArrayList<Long> idlist = loader.getIdList();    // Dont ask for user id, Create one starting from 300005600
        //Long IDStudent =  IDManager(idlist);
        
        
        System.out.println("Connecting to MySQL server...");

        try {
            TimeUnit.SECONDS.sleep(5); 
        } catch (Exception e) {
            System.out.println(e);
        }

        new StudentGUI(namelist, degreelist, agelist, idlist, table_name, loader);

    }

    public static void addStudent(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist, Long IDStudent, String name, String degree, int age){
        boolean correct = true;
        while (correct){

            //!!ERROR TO CORRECT!! Input sanitization    ☑
            correct = sanitizeString(name);
            if (!correct) {
                break;
            }

            //!!ERROR TO CORRECT!! Input sanitization   ☑
            correct = sanitizeString(degree);
            if (!correct) {
                break;
            }

            //!!ERROR TO CORRECT!! Input sanitization  
            //agelist.add(age);
            
            // function IDManager idlist.add(max idlist + 1)
            if(correct){
                idlist.add(IDStudent);

                degreelist.add(degree);
                namelist.add(name);
                agelist.add(age);
                addedsuccessful= true;
                correct = false;

            }

        }
    }

    //Displaying all Students function
    //!!MINOR DETAIL!! Show if there is nothing in list print "Nothing Here."   ☑



    public static void showAll(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist){
        int x = 0;
        for(x = 0; x < namelist.size(); x++){
            Student student = new Student(namelist.get(x), degreelist.get(x), agelist.get(x), idlist.get(x));
            System.out.println("----------------------------------");
            student.showinfo();
            System.out.println("----------------------------------");
        }
        if (x == 0) {
            System.out.println("Nothing Here!!");
        }
    }

    public static void delStudent(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist, Long selectedId){
        String tmp;
        int i = 0;
        boolean found = false;

        //!!ERROR TO CORRECT!! Input sanitization   ☑
        for (Long integer : idlist) {
            if (integer.equals(selectedId)) {
                tmp = namelist.get(i);
                namelist.remove(i);
                degreelist.remove(i);
                agelist.remove(i);
                idlist.remove(i);
                System.out.println("Student "+ tmp +" with ID "+ selectedId +" deleted successfully!");
                found = true;
                break;
            }
            i++;
        }
        if(!found){
            System.out.println("student not found");
        }
    }

    public static void updateStud(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist, String newName,String newDegree, int newAge ,Long selectedId){
        int i = 0;

        for (Long long1 : idlist) {

            if (long1.equals(selectedId)){
                namelist.set(i, newName);
                degreelist.set(i, newDegree);
                agelist.set(i, newAge);
                break;
                
            }
            i++;
        }
    }

    public static void searchstud(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idlist, Long id){
       
        
            int i = 0;
            for (Long integer : idlist) {
                if (integer.equals(id)) {
                    //search here
                    new message(namelist.get(i), degreelist.get(i), agelist.get(i), idlist.get(i));
                    break;
                }
                i++;
            }
        
    }
    
    public static boolean sanitizeString(String input){
        String namePattern = "^[A-Za-z]+(\\s[A-Za-z]+)?$"; // Regular expression pattern for names
        // Check if the input matches the name pattern
        if (input.matches(namePattern)) {
            return true;
        } else {
            System.out.println("Invalid name. Please enter a valid name.");
            return false;
        }
    }
}


