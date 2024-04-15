import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class message {  
    JFrame f;  
    public message(String name, String degree, int age, Long id){  
        f=new JFrame();  
        JOptionPane.showMessageDialog(f,"name: "+name+"\n"+"Degree: "+degree+"\n"+"Age: "+age+"\n"+"ID: "+id);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }  
}
