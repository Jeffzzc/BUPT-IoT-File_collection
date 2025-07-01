/**
 * Title      : GradePageView.java
 * Description: This class represents the Grade Page in the GUI.
 * It allows users to query, filter, and search for their own grade results.
 * Also, users can see their GPA and their ranking in their major.
 * Copyright  : Copyright (c) 2023/5/20
 * @author Tangxun Wu
 * @version 3.1
 */
package main.GUI;

import main.Control.ButtonControl;
import main.Entity.GPA;
import main.Entity.Grade;
import main.Entity.Module;
import main.Read.GradeReader;
import main.Read.ModuleReader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GradePageView extends BaseFrame2{

    public static JFrame frameGrade;
    private JLabel semester,category,rank,gpa;
    private JButton question;
    private JComboBox cmbSemester,cmbCategory,cmbRank,cmbGPA,cmbGrade;
    private JTable gradeGraph;
    private JPanel grade;
    public ArrayList<Grade> studentGradeView;//specfic grade collection through studentID
    public ArrayList<Module> studentModuleView;//specfic module collection through studentID
    public int serialNum = 0; //the number of modules after filtering and selected which are displayed on screen
    public String stuID = ButtonControl.currentStudent;
    public String getItem = "All";
    public String[][] tableData;
    Object[] tableColumn = {"Serial number", "Course start time", "Course ID", "Course name", "Course category", "Course credit", "Course grade"};
    public boolean hasChoice1 = false; //indicate whether users filter the module year
    public boolean hasChoice2 = false; //indicate whether users filter the module category
    public ArrayList<Grade> studentGradeViewModified1;
    public ArrayList<Module> studentModuleViewModified1;
    public ArrayList<Grade> studentGradeViewModified2;
    public ArrayList<Module> studentModuleViewModified2;
    public ArrayList<Grade> studentGradeViewModified3;
    public ArrayList<Module> studentModuleViewModified3;
    public int initialize;
    public int sort = 1;
    public String input = "";
    public int rankNum; //store the ranking number

    /**
     * Description: Read and match data from the database by student ID.
     * When initializing and clicking "ALL" in all filter button, this method will be used.
     */
    public void readData(){
        studentGradeView.clear(); //do initialization
        studentModuleView.clear(); //do initialization
        for (Grade grade : GradeReader.grades) {
            if ((grade.studentID).equals(stuID)) {
                studentGradeView.add(grade);
            }
        }
        if(sort == 1){ //sort=1 means sort by grade
            Collections.sort(studentGradeView, new Comparator<Grade>(){
                @Override
                /*By default, grade are ranked and displayed from highest to lowest */
                public int compare(Grade o1, Grade o2) {
                    return Integer.parseInt(o2.grade) - Integer.parseInt(o1.grade); //降序
                }
            });
            for (Grade grade : studentGradeView) {
                for(Module module : ModuleReader.modules){
                    if((module.moduleID).equals(grade.moduleID))
                    {
                        studentModuleView.add(module);
                    }
                }
            }
        }
        if(sort == 0){ //sort=0 means sort by module credit
            for (Grade grade : studentGradeView) {
                for(Module module : ModuleReader.modules){
                    if((module.moduleID).equals(grade.moduleID))
                    {
                        studentModuleView.add(module);
                    }
                }
            }
            Collections.sort(studentModuleView, new Comparator<Module>() {
                @Override
                /*By default, module are ranked and displayed via credits from highest to lowest */
                public int compare(Module o1, Module o2) {
                    return Integer.parseInt(o2.credit) - Integer.parseInt(o1.credit);
                }
            });
            studentGradeView.clear();
            for (Module module : studentModuleView) {
                for(Grade grade : GradeReader.grades){
                    if((grade.studentID).equals(stuID)){
                        if((grade.moduleID).equals(module.moduleID))
                        {
                            studentGradeView.add(grade);
                        }
                    }
                }
            }
        }
        serialNum = studentGradeView.size(); //update serial number
        initialize = serialNum;
        studentGradeViewModified3 = (ArrayList<Grade>)studentGradeView.clone();//cache current data
        studentModuleViewModified3 = (ArrayList<Module>)studentModuleView.clone(); //cache current data
    }

    /**
     * Description: Create and initial table through data collected by student ID
     * When users see this page, this method will be used automatically.
     */
    public void createTable(){
        tableData = new String[serialNum][7];
        //Data content: serial number, course time, course ID, course name, course type, course credit, course score
        for(int i = 0; i < serialNum; i++){
            tableData[i][0] = "" + (1+i);
            tableData[i][1] = "Y"+studentModuleView.get(i).year+"S"+studentModuleView.get(i).semester;
            tableData[i][2] = studentModuleView.get(i).moduleID;
            tableData[i][3] = studentModuleView.get(i).moduleName;
            tableData[i][4] = studentModuleView.get(i).type;
            tableData[i][5] = studentModuleView.get(i).credit;
            tableData[i][6] = studentGradeView.get(i).grade;
        }
        gradeGraph = new JTable(tableData,tableColumn);
        gradeGraph.setBounds(20, 240, 1200, 500);
        setColumnColor(gradeGraph);
    }

    /**
     * Description: update table through new data modified after users clicking some button to do filtering
     * When users do some filtering or search a certain course, this method will be used automatically.
     */
    public void updateTable(){
        String[][] data = new String[serialNum][7];
        //Data content: serial number, course time, course ID, course name, course type, course credit, course score
        for(int i = 0; i < serialNum; i++){
            data[i][0] = "" + (1+i);
            data[i][1] = "Y"+studentModuleView.get(i).year+"S"+studentModuleView.get(i).semester;
            data[i][2] = studentModuleView.get(i).moduleID;
            data[i][3] = studentModuleView.get(i).moduleName;
            data[i][4] = studentModuleView.get(i).type;
            data[i][5] = studentModuleView.get(i).credit;
            data[i][6] = studentGradeView.get(i).grade;
        }
        DefaultTableModel tabModel = new DefaultTableModel(data,tableColumn);
        gradeGraph.setModel(tabModel);
        gradeGraph.setEnabled(true);
        setColumnColor(gradeGraph);
    }

    /**
     * Description: Design the style of the table.
     * This style is the same as the table on other pages.
     */
    public void designTable(){
        JTableHeader graphHeader = gradeGraph.getTableHeader();
        graphHeader.setFont(new Font("Times New Roman", Font.BOLD, 16));
        graphHeader.setForeground(new Color(0, 0, 0));
        gradeGraph.setRowHeight(50);
        gradeGraph.setFont(new Font("Times New Roman", Font.BOLD, 16));
        gradeGraph.setPreferredScrollableViewportSize(new Dimension(1,1));
        JScrollPane scrollPane = new JScrollPane(gradeGraph);
        grade.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(grade);
    }

    public GradePageView() throws AWTException{
        super();

        studentGradeView = new ArrayList<Grade>();
        studentModuleView = new ArrayList<Module>();

        //Data reading
        GradeReader.initialize();
        ModuleReader.initialize();
        int j = 0;

        //The data is initially matched
        readData();
        frameGrade=new JFrame("Grade");
        frameGrade.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameGrade.setBounds(0,0,1368,864);
        frameGrade.setVisible(false);

        title.setText("Grade Query");
        back.setIcon(new ImageIcon("img/back.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        //Add a filter for school semesters
        semester= new JLabel("Semester");
        semester.setBounds(20, 120, 100, 40);
        semester.setFont(new Font("Times New Roman", Font.BOLD, 25));
        contentPane.add(semester);
        cmbSemester = new JComboBox<>();
        cmbSemester.setBounds(140, 120, 150, 40);
        cmbSemester.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        cmbSemester.addItem("All");
        cmbSemester.addItem("Y1S1");
        cmbSemester.addItem("Y1S2");
        cmbSemester.addItem("Y2S1");
        cmbSemester.addItem("Y2S2");
        cmbSemester.addItem("Y3S1");
        cmbSemester.addItem("Y3S2");
        cmbSemester.addItem("Y4S1");
        cmbSemester.addItem("Y4S2");
        cmbSemester.addItemListener(new ItemListener() {//Listen for the user to press this button
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.SELECTED == e.getStateChange()) {
                    getItem = String.valueOf(cmbSemester.getSelectedItem());
                    if(getItem.equals("All")) {
                        if(!hasChoice2){//no category filtered currently
                            readData();
                            serialNum = initialize;
                        }
                        else{
                            studentGradeView = (ArrayList<Grade>)studentGradeViewModified2.clone();
                            studentModuleView = (ArrayList<Module>)studentModuleViewModified2.clone();
                            serialNum = studentModuleView.size();
                        }
                        hasChoice1 = false;//Indicates that the semester is no longer filtered
                    }
                    else{
                        if(!hasChoice2){ //no category filtered currently
                            readData();
                            serialNum = initialize;
                        }
                        else{
                            studentGradeView = (ArrayList<Grade>)studentGradeViewModified2.clone();
                            studentModuleView = (ArrayList<Module>)studentModuleViewModified2.clone();
                            serialNum = studentModuleView.size();
                        }
                        for(int i = serialNum - 1; i>=0; i--){
                            if(!(("Y"+studentModuleView.get(i).year+"S"+studentModuleView.get(i).semester).equals(getItem))){
                                studentModuleView.remove(studentModuleView.get(i));
                                //remove some data from the screen if these data don't belong to the filted semester
                            }
                        }
                        for(int j = serialNum - 1; j>=0; j--){
                            int time = 0;
                            for(int i = studentModuleView.size()-1; i>=0; i--){
                                if(!studentModuleView.get(i).moduleID.equals(studentGradeView.get(j).moduleID)){
                                    time++;
                                    if(time == studentModuleView.size()){
                                        studentGradeView.remove(studentGradeView.get(j));
                                    }
                                }
                            }
                        }
                        hasChoice1 = true;
                        serialNum = studentModuleView.size();
                    }
                    studentGradeViewModified1 = (ArrayList<Grade>)studentGradeView.clone();
                    studentModuleViewModified1 = (ArrayList<Module>)studentModuleView.clone();
                    studentGradeViewModified3 = (ArrayList<Grade>)studentGradeView.clone();
                    studentModuleViewModified3 = (ArrayList<Module>)studentModuleView.clone();
                    updateTable();
                }
            }
        });
        cmbSemester.setSelectedIndex(0);//Set the filters that are displayed by default
        contentPane.add(cmbSemester);

        //Add a filter for school course categories
        category= new JLabel("Course Category");
        category.setBounds(320, 120, 200, 40);
        category.setFont(new Font("Times New Roman", Font.BOLD, 25));
        contentPane.add(category);
        cmbCategory = new JComboBox<>();
        cmbCategory.setBounds(540, 120, 180, 40);
        cmbCategory.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        cmbCategory.addItem("All");
        cmbCategory.addItem("compulsary");
        cmbCategory.addItem("optional");
        cmbCategory.addItemListener(new ItemListener() {//Listen for the user to press this button
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.SELECTED == e.getStateChange()) {
                    getItem = String.valueOf(cmbCategory.getSelectedItem());
                    if(getItem.equals("All")) {
                        if(!hasChoice1){ //no semester filtered currently
                            readData();
                            serialNum = initialize;
                        }
                        else{
                            studentGradeView = (ArrayList<Grade>)studentGradeViewModified1.clone();
                            studentModuleView = (ArrayList<Module>)studentModuleViewModified1.clone();
                            serialNum = studentModuleView.size();
                        }
                        hasChoice2 = false; //Indicates that the category is no longer filtered
                    }
                    else{
                        if(!hasChoice1){ //no semester filtered currently
                            readData();
                            serialNum = initialize;
                        }
                        else{
                            studentGradeView = (ArrayList<Grade>)studentGradeViewModified1.clone();
                            studentModuleView = (ArrayList<Module>)studentModuleViewModified1.clone();
                            serialNum = studentModuleView.size();
                        }
                        for(int i = serialNum - 1; i>=0; i--){
                            if(!((studentModuleView.get(i).type).equals(getItem))){
                                studentModuleView.remove(studentModuleView.get(i));
                                //remove some data from the screen if these data don't belong to the filted module category
                            }
                        }
                        for(int j = serialNum - 1; j>=0; j--){
                            int time = 0;
                            for(int i = studentModuleView.size()-1; i>=0; i--){
                                if(!studentModuleView.get(i).moduleID.equals(studentGradeView.get(j).moduleID)){
                                    time++;
                                    if(time == studentModuleView.size()){
                                        studentGradeView.remove(studentGradeView.get(j));
                                    }
                                }
                            }
                        }
                        hasChoice2 = true;
                        serialNum = studentModuleView.size();
                    }
                    studentGradeViewModified2 = (ArrayList<Grade>)studentGradeView.clone();
                    studentModuleViewModified2 = (ArrayList<Module>)studentModuleView.clone();
                    studentGradeViewModified3 = (ArrayList<Grade>)studentGradeView.clone();
                    studentModuleViewModified3 = (ArrayList<Module>)studentModuleView.clone();
                    updateTable();
                }
            }
        });
        contentPane.add(cmbCategory);

        //Add a choosing button for sorting
        cmbGrade = new JComboBox<>();
        cmbGrade.setBounds(800, 120, 190, 40);
        cmbGrade.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        cmbGrade.addItem("Sort by grade");
        cmbGrade.addItem("Sort by credit");
        cmbGrade.addItemListener(new ItemListener() { //Listen for the user to press this button
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.SELECTED == e.getStateChange()) {
                    getItem = String.valueOf(cmbGrade.getSelectedItem());
                    if(getItem.equals("Sort by grade")) { //sort through grade
                        sort = 1;
                        Collections.sort(studentGradeView, new Comparator<Grade>() {
                            @Override
                            public int compare(Grade o1, Grade o2) {
                                return Integer.parseInt(o2.grade) - Integer.parseInt(o1.grade); //降序
                            }
                        });
                        studentModuleView.clear();
                        for (Grade grade : studentGradeView) {
                            for(Module module : ModuleReader.modules){
                                if((module.moduleID).equals(grade.moduleID))
                                {
                                    studentModuleView.add(module);
                                }
                            }
                        }
                        updateTable();
                    }
                    else{ //sort through module credit
                        sort = 0;
                        Collections.sort(studentModuleView, new Comparator<Module>() {
                            @Override
                            public int compare(Module o1, Module o2) {
                                return Integer.parseInt(o2.credit) - Integer.parseInt(o1.credit); //降序
                            }
                        });
                        studentGradeView.clear();
                        for (Module module : studentModuleView) {
                            for(Grade grade : GradeReader.grades){
                                if((grade.studentID).equals(stuID)){
                                    if((grade.moduleID).equals(module.moduleID))
                                    {
                                        studentGradeView.add(grade);
                                    }
                                }
                            }
                        }
                        updateTable();
                    }
                }
            }
        });
        contentPane.add(cmbGrade);

        //Add a search bar for users to select course
        jtfSearch.setBounds(1020, 120, 180, 40);
        contentPane.add(jtfSearch);
        jtfSearch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent a)
            {
                a.getSource();
                JTextField textField = (JTextField) a.getSource();
                input = textField.getText();//see the source code ,it will return a String
            }
        });

        query.setFont(new Font("Times New Roman", Font.BOLD, 25));
        query.setBounds(1230, 120, 89, 40);
        contentPane.add(query);
        query.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                studentGradeView = (ArrayList<Grade>)studentGradeViewModified3.clone();
                studentModuleView = (ArrayList<Module>)studentModuleViewModified3.clone();
                serialNum = studentModuleView.size();
                if(!input.equals("")){
                    for(int i = serialNum - 1; i>=0; i--){
                        if(!((studentModuleView.get(i).moduleName).equals(input))){
                            studentModuleView.remove(studentModuleView.get(i));
                            //only when the user input is equal to the course name, can the course be maintained on the screen
                        }
                    }
                    for(int j = serialNum - 1; j>=0; j--){
                        int time = 0;
                        for(int i = studentModuleView.size()-1; i>=0; i--){
                            if(!studentModuleView.get(i).moduleID.equals(studentGradeView.get(j).moduleID)){
                                time++;
                                if(time == studentModuleView.size()){
                                    studentGradeView.remove(studentGradeView.get(j));
                                }
                            }
                        }
                    }
                    serialNum = studentModuleView.size();
                }
                updateTable();
            }
        });

        //Add ranking, which is different from different student
        cmbRank = new JComboBox<>();
        cmbRank.setBounds(20, 180, 200, 40);
        cmbRank.setFont(new Font("Times New Roman", Font.BOLD, 25));
        cmbRank.addItem("  Ranking(All)");
        contentPane.add(cmbRank);

        //Add GPA checking, which is different from different student
        GPAView gpaView = new GPAView();
        for(GPA ranking : GPAView.studentGPAView){
            if(ranking.studentID.equals(stuID)){
                rankNum = GPAView.studentGPAView.indexOf(ranking) + 1;
            }
        }
        rank = new JLabel(": " + rankNum + "/" + GPAView.studentNum);
        rank.setBounds(230, 180, 100, 40);
        rank.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        contentPane.add(rank);

        cmbGPA = new JComboBox<>();
        cmbGPA.setBounds(350, 180, 200, 40);
        cmbGPA.setFont(new Font("Times New Roman", Font.BOLD, 25));
        cmbGPA.addItem("  BUPT:GPA");
        cmbGPA.addItem("  QMUL:GPA");
        contentPane.add(cmbGPA);
        DecimalFormat df = new DecimalFormat("#.00");
        for(GPA gpa0 : GPAView.studentGPAView){
            if(stuID.equals(gpa0.studentID)){
                gpa= new JLabel(": "+df.format(gpa0.gpa1)+"/100.00");
            }
        }
        gpa.setBounds(560, 180, 150, 40);
        gpa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        cmbGPA.addItemListener(new ItemListener() {//Listen for the user to press this button
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.SELECTED == e.getStateChange()) {
                    getItem = String.valueOf(cmbGPA.getSelectedItem());
                    if(getItem.equals("  BUPT:GPA")) {
                        for(GPA gpa0 : GPAView.studentGPAView){
                            if(stuID.equals(gpa0.studentID)){
                                gpa.setText(": "+df.format(gpa0.gpa1)+"/100.00");
                            }
                        }
                    }
                    if(getItem.equals("  QMUL:GPA")) {
                        for(GPA gpa0 : GPAView.studentGPAView){
                            if(stuID.equals(gpa0.studentID)){
                                gpa.setText(": "+df.format(gpa0.gpa2)+"/4.00");
                            }
                        }
                    }
                }
            }
        });
        cmbGPA.setSelectedIndex(0);
        contentPane.add(gpa);

        //this button is used to demonstrate the GPA algorithms for users
        question = new JButton(new ImageIcon("img/question2.jpg"));
        question.setBorderPainted(false);
        question.setBounds(720, 180, 36, 40);
        contentPane.add(question);
        question.addActionListener(new ActionListener(){
            // Click the button to execute the method
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("GPA guide");
                // Set position on the screen
                frame1.setLocation(600,280);
                frame1.setSize(300,800);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setLayout(new BoxLayout(frame1.getContentPane(), BoxLayout.Y_AXIS));
                String text1 = "BUPT's GPA: A weighted average score is calculated out of 100 points.";
                String text2 = "It is calculated by multiplying the course grade by the number of credits";
                String text3 = " in the corresponding course and dividing by the total number of credits.";
                String text4 = "QMUL's GPA: A weighted average score is calculated out of 4 points.";
                String text5 = "                                                                   ";
                JLabel label1= new JLabel(text1);
                JLabel label2= new JLabel(text2);
                JLabel label3= new JLabel(text3);
                JLabel label4= new JLabel(text4);
                JLabel label5= new JLabel(text2);
                JLabel label6= new JLabel(text3);
                JLabel label7= new JLabel(text5);
                frame1.getContentPane().add(label1);
                frame1.getContentPane().add(label2);
                frame1.getContentPane().add(label3);
                frame1.getContentPane().add(label7);
                frame1.getContentPane().add(label4);
                frame1.getContentPane().add(label5);
                frame1.getContentPane().add(label6);
                frame1.pack();
                frame1.setVisible(true);
            }
        });

        grade = new JPanel(new BorderLayout());
        grade.setBounds(20, 240, 1300, 500);

        // Initialize the table to display all contents of the table
        createTable();
        designTable();

        // Create a cell renderer
        MyTableCellRenderer renderer = new MyTableCellRenderer();

        // Walk through each column of the table, giving each column a separate cell renderer
        for (int i = 0; i < tableColumn.length; i++) {
            // Gets a table column by column name
            TableColumn column = gradeGraph.getColumn(tableColumn[i]);
            // Sets the cell renderer for the table column
            column.setCellRenderer(renderer);
        }

        //add print choice which will call a method in the control class
        print = new JButton(new ImageIcon("img/GradePrint.jpg"));
        print.setFont(new Font("Times New Roman", Font.BOLD, 10));
        print.setBounds(800, 180, 84, 40);
        contentPane.add(print);
        print.addActionListener(new ButtonControl(print.getIcon()));

        //add download choice which will call a method in the control class
        download = new JButton(new ImageIcon("img/GradeDownload.jpg"));
        download.setFont(new Font("Times New Roman", Font.BOLD, 10));
        download.setBounds(920, 180, 122, 40);
        contentPane.add(download);
        download.addActionListener(new ButtonControl(download.getIcon()));

        background.setIcon(new ImageIcon("img/background1.jpg"));
        contentPane.add(background);
        frameGrade.add(contentPane);
    }

    /**
     * This method is used to add cell render when initializingt the table
     */
    public static class MyTableCellRenderer extends DefaultTableCellRenderer {
        /**
         * Return the default form cell renderer
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (row % 2 == 0) {
                setBackground(LIGHTBLUE);
            } else {
                setBackground(GREY);
            }

            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(Integer.parseInt((String) table.getValueAt(row, 6))<60)//设置变色的单元格
            {
                cell.setForeground(Color.RED);
            } else {
                cell.setForeground(Color.BLACK);
            }

            // Align the contents of all columns horizontally
            setHorizontalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    /**
     * This method is used to add cell render when updating the table
     */
    public static void setColumnColor(JTable table) {
        try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
                private static final long serialVersionUID = 1L;
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                    if(row%2 == 0) {
                        setBackground(LIGHTBLUE);// Set the base color for odd rows
                    } else {
                        setBackground(GREY);// Set the base color for even rows
                    }

                    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if(Integer.parseInt((String) table.getValueAt(row, 6))<60)// Sets the cell to change color
                    {
                        cell.setForeground(Color.RED); //when the grade is less than 60, the row will turn red.
                    } else {
                        cell.setForeground(Color.BLACK);
                    }

                    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
                }
            };
            for(int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
            tcr.setHorizontalAlignment(JLabel.CENTER);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
