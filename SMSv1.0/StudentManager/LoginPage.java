import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class LoginPage implements ActionListener{

    /*Creating componements for loginPage */
    JFrame f = new JFrame("SMS v1.0");
    JLabel welcome = new JLabel("Welcome to SMS v1.0");
    JButton goButton = new JButton("GO");
    JLabel IP = new JLabel("Enter your IP: ");
    JTextField tf1 = new JTextField("");
    JLabel DBname = new JLabel("Enter the database name: ");
    JTextField tf2 = new JTextField("");
    JLabel table_name = new JLabel("Enter the table name: ");
    JTextField tf3 = new JTextField("");
    String ip;
    String dbname;
    String table;
    boolean triggered = false;
    boolean running = true;    
       

    /* These strings are the value inside text filed area: ip, dbname, tablename cin order*/

    public LoginPage(){
        
        //Making Frame for login page
        /* action listener for the goButton */ 
        goButton.addActionListener(this);


        welcome.setBounds(200,50,200,40);
        goButton.setBounds(50,350,100, 40);  
        IP.setBounds(50, 100,200, 40);
        tf1.setBounds(250,100,200,40);
        DBname.setBounds(50, 150,200, 40);
        tf2.setBounds(250,150,200,40);
        table_name.setBounds(50,200,200, 40);
        tf3.setBounds(250,200,200,40);

        f.add(welcome);
        f.add(goButton);
        f.add(IP);
        f.add(DBname);
        f.add(table_name);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        
        f.setSize(600,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);

        }
    

    public void actionPerformed(ActionEvent e) {
        /* If goButton is pressed */
        try {
            triggered = true;
            
            ip = tf1.getText();
            dbname =tf2.getText();
            table = tf3.getText();
            f.dispose();
            //f.setVisible(false);
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    public String getDBname() {
        return dbname;
    }
    public String getip(){
        return ip;
    }
    public String gettable(){
        return table;
    }
    
    public boolean isTriggered(){
        return triggered;
    }
    public void closeLogin(){
        f.setVisible(false);
    }

    
    
}
