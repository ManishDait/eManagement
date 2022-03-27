import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import javax.swing.table.DefaultTableModel;

class App extends JFrame {
    String[] columnNames = {"Emp_Id","Name","Company","Skill","Age","Date of Birth","Date of Joining","Salary"};
    String[][] data= new String[0][columnNames.length];
    String sname, scompany, sskill, sage, sdob, sdoj, ssalary, sempid;
    Connection conn = null;
    Statement statement = null;
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
    JTextArea ta;
    JLabel title, name, company, age, skill, dob, doj, salary, ialary, lbackup;
    JTextField ntext, ctext, atext, jtext, btext, ttext, stext,itext;
    JButton submit, edit, delect, backup, search;
    JTable table;
    JPanel nJPanel, cJPanel, aJPanel, sJPanel, bJPanel, jPanel, sPanel, button_bar, iPanel;
    
    App() {

        
        
        

        title = new JLabel("Employee Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(0, 10, 670, 50);

        cJPanel = new JPanel();
        cJPanel.setLayout(null);
        cJPanel.setBounds(0, 80, 670, 50);

        company = new JLabel("Name Of Company: ");
        company.setFont(new Font("Arial", Font.PLAIN, 15));
        company.setBounds(10, -5, 200, 40);
        cJPanel.add(company);

        ctext = new JTextField();
        ctext.setFont(new Font("Arial", Font.BOLD, 15));
        ctext.setBounds(360, 0, 300, 30);
        cJPanel.add(ctext);

        nJPanel = new JPanel();
        nJPanel.setLayout(null);
        nJPanel.setBounds(0, 140, 670, 50);

        name = new JLabel("Name Of Employee: ");
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setBounds(10, 0, 200, 40);
        nJPanel.add(name);

        ntext = new JTextField();
        ntext.setFont(new Font("Arial", Font.BOLD, 15));
        ntext.setBounds(360, 0, 300, 30);
        nJPanel.add(ntext);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 200, 670, 50);

        doj = new JLabel("Date Of Joining: ");
        doj.setFont(new Font("Arial", Font.PLAIN, 15));
        doj.setBounds(10, 0, 200, 40);
        jPanel.add(doj);

        jtext = new JTextField();
        jtext.setBounds(360, 0, 300, 30);
        jtext.setFont(new Font("Arial", Font.BOLD, 15));
        jPanel.add(jtext);

        aJPanel = new JPanel();
        aJPanel.setLayout(null);
        aJPanel.setBounds(0, 260, 670, 50);

        age = new JLabel("Age: ");
        age.setFont(new Font("Arial", Font.PLAIN, 15));
        age.setBounds(10, 0, 200, 40);
        aJPanel.add(age);

        atext = new JTextField();
        atext.setBounds(360, 0, 300, 30);
        atext.setFont(new Font("Arial", Font.BOLD, 15));
        aJPanel.add(atext);

        sJPanel = new JPanel();
        sJPanel.setLayout(null);
        sJPanel.setBounds(0, 320, 670, 50);

        skill = new JLabel("Skill: ");
        skill.setFont(new Font("Arial", Font.PLAIN, 15));
        skill.setBounds(10, 0, 200, 40);
        sJPanel.add(skill);

        ttext = new JTextField();
        ttext.setBounds(360, 0, 300, 30);
        ttext.setFont(new Font("Arial", Font.BOLD, 15));
        sJPanel.add(ttext);

        sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.setBounds(0, 380, 670, 50);

        salary = new JLabel("Salary: ");
        salary.setFont(new Font("Arial", Font.PLAIN, 15));
        salary.setBounds(10, 0, 200, 40);
        sPanel.add(salary);

        stext = new JTextField();
        stext.setBounds(360, 0, 300, 30);
        stext.setFont(new Font("Arial", Font.BOLD, 15));
        sPanel.add(stext);

        bJPanel = new JPanel();
        bJPanel.setLayout(null);
        bJPanel.setBounds(0, 440, 670, 50);

        dob = new JLabel("Date Of Birth: ");
        dob.setFont(new Font("Arial", Font.PLAIN, 15));
        dob.setBounds(10, 0, 200, 40);
        bJPanel.add(dob);

        btext = new JTextField();
        btext.setBounds(360, 0, 300, 30);
        btext.setFont(new Font("Arial", Font.BOLD, 15));
        bJPanel.add(btext);

        iPanel = new JPanel();
        iPanel.setLayout(null);
        iPanel.setBounds(0, 500, 670, 50);

        ialary = new JLabel("Emp_ID: ");
        ialary.setFont(new Font("Arial", Font.PLAIN, 15));
        ialary.setBounds(10, 0, 200, 40);
        iPanel.add(ialary);

        itext = new JTextField();
        itext.setBounds(360, 0, 300, 30);
        itext.setFont(new Font("Arial", Font.BOLD, 15));
        iPanel.add(itext);

        button_bar = new JPanel();
        button_bar.setLayout(null);
        button_bar.setBounds(0, 580, 1200, 50);

        submit = new JButton();
        submit.setText("Submit");
        submit.setBounds(285, 0, 100, 30);
        button_bar.add(submit);

        edit = new JButton();
        edit.setText("Edit");
        edit.setBounds(10, 0, 100, 30);
        button_bar.add(edit);

        delect = new JButton();
        delect.setText("Delect");
        delect.setBounds(565, 0, 100, 30);
        button_bar.add(delect);

        backup = new JButton();
        backup.setText("Backup");
        backup.setBounds(788, 0, 100, 30);
        button_bar.add(backup);

        search = new JButton();
        search.setText("Search");
        search.setBounds(1030, 0, 100, 30);
        button_bar.add(search);

        lbackup = new JLabel("bACKUP");
        lbackup.setFont(new Font("Arial", Font.BOLD, 12));
        lbackup.setBounds(928, 640, 300, 30);
        
        

        table = new JTable(model);
        model.setColumnIdentifiers(columnNames);

        JScrollPane js=new JScrollPane(table);
        js.setBounds(680, 80, 560, 250);
        js.setVisible(true);
        add(js,BorderLayout.CENTER);

        ta = new JTextArea();
        ta.setBounds(680, 350, 560,210);
        ta.setFont(new Font("Arial", Font.BOLD, 12));
        ta.setEditable(false);
        ta.setText("\nSearched EMPLOYEE Details:");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?" + "user=root&password=password");
            statement = (Statement) conn.createStatement();
            System.out.println("Connected");
            String sql;
            sql = "select *from Test.Employee";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                sname = rs.getString(2);
                scompany= rs.getString(1);
                sskill = rs.getString(3);
                sage = rs.getString(4);
                sdob = rs.getString(5);
                sdoj = rs.getString(6);
                ssalary = rs.getString(7);
                sempid = rs.getString(8);
                String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                model.addRow(row);
                
            }
            
            

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try {
            File myfile = new File("emp_backup.txt");
            if (myfile.createNewFile()) {
              System.out.println("File created: " + myfile.getName());
              lbackup.setText("No Backup Done yet.");
            } else {
            long time = myfile.lastModified();
              System.out.println("File already exists.");
              lbackup.setText("Last Backup on "+sdf.format(time));
            }
          } catch (IOException E) {
            System.out.println("An error occurred.");
            E.printStackTrace();
          }

        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sname = ntext.getText().toString();
                scompany = ctext.getText().toString();
                sskill = ttext.getText().toString();
                sage = atext.getText().toString();
                sdob = btext.getText().toString();
                sdoj = jtext.getText().toString();
                ssalary = stext.getText().toString();
                sempid = itext.getText().toString();

              String sql = "INSERT INTO Employee VALUES ('"+scompany+"', '"+sname+"', '"+sskill+"', "+sage+",'"+sdob+"','"+sdoj+"',"+ssalary+","+sempid+")";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }

            
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                    model.addRow(row);
                    
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Data Added");
            
            }
        });

        backup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
            try {
                    FileWriter myWriter = new FileWriter("emp_backup.txt");
                    String sql;
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    myWriter.write("{\n{\nEmp_id:"+sempid+"\nEmp_name:"+sname+"\nCompany:"+scompany+"\nSkill:"+sskill+"\nSalary:"+ssalary+"\nAge:"+sage+"\nDOB:"+sdob+"\nDOJ:"+sdoj+"\n}\n}\n\n");
                    
                    
                }
                myWriter.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
                    System.out.println("Successfully wrote to the file.");
                  } catch (IOException exp) {
                    System.out.println("An error occurred.");
                    exp.printStackTrace();
                  }
                  try {
                    File myfile = new File("emp_backup.txt");
                    if (myfile.createNewFile()) {
                      System.out.println("File created: " + myfile.getName());
                      lbackup.setText("No Backup Done yet.");
                    } else {
                    long time = myfile.lastModified();
                      System.out.println("File already exists.");
                      lbackup.setText("Last Backup on "+sdf.format(time));
                    }
                  } catch (IOException E) {
                    System.out.println("An error occurred.");
                    E.printStackTrace();
                  }
        
                
            JOptionPane.showMessageDialog(null, "Data Backup Done");
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String result = (String)JOptionPane.showInputDialog(
                  table,
                  "Search by Employee Id", 
                  "Search",            
                  JOptionPane.PLAIN_MESSAGE,
                  null,            
                  null, 
                  ""
               );
               if(result != null && result.length() > 0){
                String sql = "SELECT * FROM Employee WHERE e_id="+result+"";
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while(rs.next()){
                        sname = rs.getString(2);
                        scompany= rs.getString(1);
                        sskill = rs.getString(3);
                        sage = rs.getString(4);
                        sdob = rs.getString(5);
                        sdoj = rs.getString(6);
                        ssalary = rs.getString(7);
                        sempid = rs.getString(8);
                        ta.setText("\nSearched EMPLOYEE Details:\n\nEmployeeId: " + sempid +"\nEmployeeName: " + sname+"\nSkills: " + sskill+"\nSalary: " + ssalary+"\nAge: " + sage+"\nDate of Joining: " + sdoj+"\nDate of Birth: " + sdob);
                        itext.setText(sempid);
                        ntext.setText(sname);
                        ctext.setText(scompany);
                        ttext.setText(sskill);
                        atext.setText(sage);
                        btext.setText(sdob);
                        jtext.setText(sdoj);
                        stext.setText(ssalary);
                        
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
               }else {
                  System.out.println("Not Selected");
               }
            }
         });

        delect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int rowi = table.getSelectedRow();
                String key = (String) table.getModel().getValueAt(rowi, 0);

              String sql = "DELETE FROM Employee WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }

            
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                    model.addRow(row);
                    
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Data Removed");
            }
        });

        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String key = itext.getText().toString();
                 

            String sql = "UPDATE Employee SET e_name='"+ntext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            
            sql = "UPDATE Employee SET e_name='"+ntext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET comapany='"+ctext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET skill='"+ttext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET dob='"+btext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET doj='"+jtext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET age='"+atext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET salary='"+stext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }

            
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                    model.addRow(row);
                    
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            }
        });


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 1) {     // to detect doble click events
                  JTable target = (JTable)me.getSource();
                  
                  int row = target.getSelectedRow(); // select a row
                  itext.setText(table.getValueAt(row, 0).toString());
                  ntext.setText(table.getValueAt(row, 1).toString());
                  ctext.setText(table.getValueAt(row, 2).toString());
                  ttext.setText(table.getValueAt(row, 3).toString());
                  atext.setText(table.getValueAt(row, 4).toString());
                  btext.setText(table.getValueAt(row, 5).toString());
                  jtext.setText(table.getValueAt(row, 6).toString());
                  stext.setText(table.getValueAt(row, 7).toString());
               }
            }
         });

        table.setModel(model);

        

       
        
        

        this.setTitle("Employee Management System");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1275, 720);
        this.setVisible(true);
        this.setResizable(false);
        this.add(title);
        this.add(cJPanel);
        this.add(nJPanel);
        this.add(jPanel);
        this.add(aJPanel);
        this.add(sJPanel);
        this.add(sPanel);
        this.add(bJPanel);
        this.add(iPanel);
        this.add(button_bar);
        this.add(lbackup);
        this.add(ta);
        
    }

    
    public static void main(String[] args) {
        new App();
    }
}