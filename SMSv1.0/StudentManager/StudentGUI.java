import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;




public class StudentGUI {
    public boolean guiON = true;
    public int selectedRow;
    public Long selectedId;
    DefaultTableModel model;
    JFrame f = new JFrame("SMS v1.0");
    JPanel LeftPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel tabPanel = new JPanel();
    JPanel titlepanel = new JPanel();
    JLabel searchLabel = new JLabel("ID");
    JTextField searchfield = new JTextField();
    JButton searchButton = new JButton("SEARCH");
    JLabel title = new JLabel("STUDENT MANAGER SYSTEM v1.0");
    JLabel leftname = new JLabel("Student fullname: ");
    JTextField leftfieldname = new JTextField();
    JLabel leftdegree = new JLabel("Student degree: ");
    JTextField leftfielddegree = new JTextField();
    JLabel leftage = new JLabel("Student age: ");
    JTextField leftfieldage = new JTextField();
    JButton addButton = new JButton("ADD");
    JButton delButton = new JButton("DELETE");
    JButton quitButton = new JButton("QUIT");
    JButton updateButton = new JButton("UPDATE");
    JButton eraseAll = new JButton("Erase All");
    JLabel signature = new JLabel("SMS v1.0 by OMZO");
    //String rows[][] = {{"test","test","test"},{"test","test","test"}};
    //String columns[] = {"Fullname", "Degree", "Age" };
    //JTable table = new JTable(rows, columns);
    //JScrollPane scrollpane = new JScrollPane(table);

    public StudentGUI(ArrayList<String> namelist, ArrayList<String> degreelist, ArrayList<Integer> agelist, ArrayList<Long> idList, String table_name, driverLoader loader){

        model = new DefaultTableModel();
        model.addColumn("Fullname");
        model.addColumn("Degree");
        model.addColumn("Age");
        model.addColumn("ID");
        for (int i = 0; i < agelist.size(); i++) {
            model.addRow(new Object[]{namelist.get(i), degreelist.get(i), agelist.get(i), idList.get(i)});
        }
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);

        searchPanel.setBackground(Color.gray);
        titlepanel.setBounds(10, 10, 400, 50);
        titlepanel.setBackground(Color.red);
        LeftPanel.setBounds(10, 70, 400, 810);
        LeftPanel.setBackground(Color.gray);
        searchPanel.setBounds(420, 10, 1240, 50);
        tabPanel.setBackground(Color.gray);
        tabPanel.setBounds(420, 70, 1240, 810);
        searchLabel.setBounds(470, 25, 20, 20);
        searchfield.setBounds(550, 15, 500, 40);
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton.setBounds(1060, 15, 120, 40);
        title.setFont(new Font("Copperplat", Font.PLAIN, 16));
        title.setBounds(60, 10, 300, 50);
        //searchButton.setBorder(new RoundedBorder(10));
        leftname.setBounds(20, 90, 300, 20);
        leftfieldname.setBounds(20,120, 350, 20);
        leftdegree.setBounds(20, 170, 300, 20);
        leftfielddegree.setBounds(20, 200, 350, 20);
        leftage.setBounds(20, 250, 300, 20);
        leftfieldage.setBounds(20, 280, 350, 20);
        addButton.setBounds(40, 330, 100, 50);
        delButton.setBounds(250, 330, 100, 50);
        updateButton.setBounds(40, 430, 100, 50);
        quitButton.setBounds(250, 430, 100, 50);
        eraseAll.setBounds(100,530,200,50);
        signature.setBounds(270, 840, 200, 50);
        scrollpane.setBounds(430, 80, 1220, 790);
        scrollpane.setBackground(Color.lightGray);
        table.setBackground(Color.lightGray);
        f.add(scrollpane);
        f.add(signature);
        f.add(eraseAll);
        f.add(addButton);
        f.add(delButton);
        f.add(updateButton);
        f.add(quitButton);
        f.add(leftage);
        f.add(leftfieldage);
        f.add(leftdegree);
        f.add(leftfielddegree);
        f.add(leftfieldname);
        f.add(leftname);
        f.add(title);
        f.add(titlepanel);
        f.add(searchButton);
        f.add(searchLabel);
        f.add(searchfield);
        f.add(searchPanel);
        f.add(LeftPanel);
        f.add(tabPanel);
        f.setSize(1680,1050);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //f.setVisible(false);
                loader.syncDB(table_name);
                loader.SaveDB(namelist, degreelist, agelist, idList, table_name);
                loader.closeConnection();
                f.dispose();
                System.exit(0);
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //code
                String name = leftfieldname.getText();
                String degree = leftfielddegree.getText();
                String age1 = leftfieldage.getText();
                Long IDStudent = IDManager(idList);
                int age = Integer.parseInt(age1);
                leftfieldage.setText("");
                leftfielddegree.setText("");
                leftfieldname.setText("");

                StudentManager.addStudent(namelist, degreelist, agelist, idList, IDStudent, name, degree, age);
                if(StudentManager.addedsuccessful){
                    model.addRow(new Object[]{name, degree, age, IDStudent});
                    StudentManager.addedsuccessful = false;
                }
                
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e){
                
                if (!e.getValueIsAdjusting()) {
                    selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        // Retrieve data from selected row
                        Object firstName = table.getValueAt(selectedRow, 0);
                        Object degree = table.getValueAt(selectedRow, 1);
                        Object age = table.getValueAt(selectedRow, 2);
                        Object ID = table.getValueAt(selectedRow, 3);
                        String n = String.valueOf(firstName);
                        String d = String.valueOf(degree);
                        String a = String.valueOf(age);
                        selectedId = Long.parseLong(String.valueOf(ID));

                        // Setting value of JTextfileds leftfiledname, leftfieldage and leftfileddegree
                        leftfieldage.setText(a);
                        leftfieldname.setText(n);
                        leftfielddegree.setText(d);

                    }
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                StudentManager.delStudent(namelist, degreelist, agelist, idList, selectedId);
                try {
                    model.removeRow(selectedRow);
                } catch (Exception c) {
                    System.out.println("row not found");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String n = leftfieldname.getText();
                String d = leftfielddegree.getText();
                int a = Integer.parseInt(leftfieldage.getText());
                model.setValueAt(n,selectedRow, 0);
                model.setValueAt(d,selectedRow, 1);
                model.setValueAt(a,selectedRow, 2);
                /*
                Object firstName = table.getValueAt(selectedRow, 0);
                Object degree = table.getValueAt(selectedRow, 1);
                Object age = table.getValueAt(selectedRow, 2);
                String n = String.valueOf(firstName);
                String d = String.valueOf(degree);
                int a = Integer.parseInt(String.valueOf(age));
                 */
                StudentManager.updateStud(namelist, degreelist, agelist, idList, n, d, a, selectedId);
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String ID = searchfield.getText();
                Long idLookup = Long.parseLong(String.valueOf(ID));
                StudentManager.searchstud(namelist, degreelist, agelist, idList, idLookup);
            }
        });

        eraseAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loader.eraseAll(table_name);
                model.setRowCount(0);
            }
        });
    
    }
    public static Long IDManager(ArrayList<Long> idlist){

        long x = 300065567;
        int i;
        for(i = 0; i < idlist.size(); i++){
            if(x < idlist.get(i)){
                x = idlist.get(i);
            }
        }
        x ++;
        return x;
    }
}
