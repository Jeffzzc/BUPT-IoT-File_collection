/**
 * Title      : SchedulePageView.java
 * Description: This class represents the Schedule Page View in the GUI.
 *              It displays the course schedule and provides filtering options based on week and semester.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Rongxunan Zhu
 * @version     3.2
 */
package main.GUI;

import main.Control.ButtonControl;
import main.Entity.Module;
import main.Entity.Student;
import main.Read.ModuleReader;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SchedulePageView extends BaseFrame2 {
    // LoginPageView.currentStudent;
    public String stuID = "1";
    Student currentStudent;
    public String Year;
    public String Semester;
    public String major;

    // Number of weeks and semesters selected for screening
    public String chooseWeek, chooseSemester;

    // Retrieve filled data
    public static ArrayList<Module> modules;
    public static JFrame frameSchedule;
    private JLabel week, semester;
    private JComboBox<String> cmbWeek, cmbSemester;
    JScrollPane scrollPane1, scrollPane2;
    private JTable table1, table2;

    // Schedule data for Year 1, Semester 1
    Object[][] data1 = ModuleReader.object("1", "1");
    // Schedule data for Year 1, Semester 1
    Object[][] data2 = ModuleReader.object("1", "2");

    /**
     * Constructs a SchedulePageView object.
     * Initializes the GUI components and sets up the event listeners.
     */
    public SchedulePageView() throws AWTException {
        super();
        title.setText("Course Schedule");

        /* back */
        back.setIcon(new ImageIcon("img/back.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        /* Set up the frame */
        frameSchedule = new JFrame("Learning Journey");
        frameSchedule.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameSchedule.setBounds(0, 0, 1368, 864);
        frameSchedule.setVisible(false);

        /* Add week filter */
        week = new JLabel("Week");
        week.setBounds(20, 140, 80, 40);
        week.setFont(new Font("Times New Roman", Font.BOLD, 25));
        contentPane.add(week);
        cmbWeek = new JComboBox<>();
        cmbWeek.setBounds(120, 140, 150, 40);
        cmbWeek.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        String[] cmbWeeks = { "       All   ", "    Week1   ", "    Week2   ", "    Week3   ", "    Week4   ",
                "    Week5   ", "    Week6   ", "    Week7   ", "    Week8   ", "    Week9   ", "    Week10   ",
                "    Week11   ", "    Week12   ", "    Week13   ", "    Week14   ", "    Week15   ", "    Week16   " };
        for (String CmbWeek : cmbWeeks) {
            cmbWeek.addItem(CmbWeek);
        }
        cmbWeek.setSelectedIndex(0);
        contentPane.add(cmbWeek);

        /* Add semester filter */
        semester = new JLabel("Semester");
        semester.setBounds(320, 140, 100, 40);
        semester.setFont(new Font("Times New Roman", Font.BOLD, 25));
        contentPane.add(semester);
        cmbSemester = new JComboBox<>();
        cmbSemester.setBounds(440, 140, 180, 40);
        cmbSemester.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        String[] semesters = { "  2022-2023-2", "  2022-2023-1", "  2021-2022-2",
                "  2021-2022-1", "  2020-2021-2",
                "  2020-2021-1", "  2019-2020-2", "  2019-2020-1" };
        for (String Semester : semesters) {
            cmbSemester.addItem(Semester);
        }
        cmbSemester.setSelectedIndex(0);
        contentPane.add(cmbSemester);

        /* Week option action accordingly */
        cmbWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWeek = cmbWeek.getSelectedItem().toString();
                chooseWeek = selectedWeek.trim();
                Filter();
            }
        });

        /* Semester option action accordingly */
        cmbSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSemester = cmbSemester.getSelectedItem().toString();
                chooseSemester = selectedSemester.trim();
                Filter();
            }
        });

        /* Setting the course content */
        table(data1);
        table(data2);
        initializeData1();
        initializeData2();
        contentPane.add(scrollPane1);
        contentPane.add(scrollPane2);
        // Set to false for the form to disappear
        scrollPane1.setVisible(false);
        scrollPane2.setVisible(false);

        /* Create a background */
        background.setIcon(new ImageIcon("img/background1.jpg"));
        contentPane.add(background);
        frameSchedule.add(contentPane);

        /* Hover box to suggest page content */
        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Schedule Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel("1. show the course schedule.");
                panel.add(title);
                panel.add(conetent_1);
                panel.add(conetent_2);

                dialog.add(panel);
                dialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dialog.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

        });

    }

    /**
     * Initializes the data1 for the first semester in the schedule page.
     * This method sets up the table structure, including column names, cell
     * renderers, and border styles.
     */
    public void initializeData1() {

        // columnNames is define as the class schedule header week
        String[] columnNames = { "   ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        // Initialise the table model with the specified data1 and columnNames.
        DefaultTableModel model = new DefaultTableModel(data1, columnNames);
        table1 = new JTable(model) {
            @Override
            /* Set the background colour and border thickness in the class list */
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                ((JComponent) component).setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));// Set border thickness and colour
                ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER); // Centered alignment
                ((JComponent) component).setFont(new Font("Times New Roman", Font.BOLD, 16));// Set font and font size, and bold
                /* Set different background colours for odd and even rows */
                if (row % 2 == 0) {
                    component.setBackground(GREY);
                } else {
                    component.setBackground(LIGHTBLUE);
                }
                return component;
            }
        };
        /* Adjusting the overall position and size of the table */
        // Set line height
        table1.setRowHeight(50);
        scrollPane1 = new JScrollPane(table1);
        // Set total position and size
        scrollPane1.setBounds(80, 200, 1200, 530);
        /* Adjustment of the outermost border */
        // Set the grid line colour
        table1.setGridColor(Color.BLACK);
        ((JComponent) table1).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        /* Set the table header border and the outermost border */
        JTableHeader header = table1.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // 设置居中对齐
                ((JComponent) c).setFont(new Font("Times New Roman", Font.BOLD, 16));// 设置字体，大小及加粗
                ((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 1, 0, 1, Color.BLACK));
                ((JComponent) c).setBackground(new Color(204, 204, 204));
                return c;
            }
        });
    }

    /**
     * Initializes the data2 for the second semester in the schedule page.
     * This method sets up the table structure, including column names, cell
     * renderers, and border styles.
     * just same as initializeData1(), but not same semester.
     */
    public void initializeData2() {
        /* Write the top of the page */
        String[] columnNames = { "   ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        DefaultTableModel model = new DefaultTableModel(data2, columnNames);
        table2 = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                ((JComponent) component).setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
                ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
                ((JComponent) component).setFont(new Font("Times New Roman", Font.BOLD, 16));
                if (row % 2 == 0) {
                    component.setBackground(GREY);
                } else {
                    component.setBackground(LIGHTBLUE);
                }
                return component;
            }
        };
        table2.setRowHeight(50);
        scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBounds(80, 200, 1200, 530);
        table2.setGridColor(Color.BLACK);
        ((JComponent) table2).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        JTableHeader header = table2.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                ((JComponent) c).setFont(new Font("Times New Roman", Font.BOLD, 16));
                ((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 1, 0, 1, Color.BLACK));
                ((JComponent) c).setBackground(new Color(204, 204, 204));
                return c;
            }
        });
    }

    /**
     * Adds two-dimensional string arrays and returns the result.
     *
     * @param array1 The first two-dimensional string array.
     * @param array2 The second two-dimensional string array.
     * @return The result of adding the arrays.
     */

    public static String[][] addArrays(String[][] array1, String[][] array2) {
        int rows = array1.length;
        int columns = array1[0].length;
        String[][] result = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (array1[i][j] == null) {
                    result[i][j] = array2[i][j];
                }
                if (array2[i][j] == null) {
                    result[i][j] = array1[i][j];
                }
            }
        }

        return result;
    }

    /**
     * Filters the schedule based on the selected week and semester.
     *
     */
    public void Filter() {
        switch (chooseWeek) {
            case "All":
                switch (chooseSemester) {
                    case "2022-2023-2":
                        scrollPane2.setVisible(true);
                        scrollPane1.setVisible(false);
                        break;
                    case "2022-2023-1":
                        scrollPane1.setVisible(true);
                        scrollPane2.setVisible(false);
                        break;
                    default:
                        scrollPane1.setVisible(false);
                        scrollPane2.setVisible(false);
                        break;
                }
                break;
            default:
                switch (chooseSemester) {
                    case "2022-2023-2":
                        scrollPane2.setVisible(true);
                        scrollPane1.setVisible(false);
                        break;
                    case "2022-2023-1":
                        scrollPane1.setVisible(true);
                        scrollPane2.setVisible(false);
                        break;
                    default:
                        scrollPane1.setVisible(false);
                        scrollPane2.setVisible(false);
                        break;
                }
                break;
        }

    }

    /**
     * Sets the table data with formatted time slots.
     *
     * @param data The two-dimensional array representing the table data.
     */
    public void table(Object data[][]) {
        data[0][0] = "<html><div style='text-align:center;'>1<br/>8:00-8:45</div></html>";
        data[1][0] = "<html><div style='text-align:center;'>2<br/>8:50-9:35</div></html>";
        data[2][0] = "<html><div style='text-align:center;'>3<br/>9:50-10:35</div></html>";
        data[3][0] = "<html><div style='text-align:center;'>4<br/>10:40-11:25</div></html>";
        data[4][0] = "<html><div style='text-align:center;'>5<br/>11:30-12:15</div></html>";
        data[5][0] = "<html><div style='text-align:center;'>6<br/>13:00-13:45</div></html>";
        data[6][0] = "<html><div style='text-align:center;'>7<br/>13:50-14:35</div></html>";
        data[7][0] = "<html><div style='text-align:center;'>8<br/>14:45-15:30</div></html>";
        data[8][0] = "<html><div style='text-align:center;'>9<br/>15:40-16:25</div></html>";
        data[9][0] = "<html><div style='text-align:center;'>10<br/>16:35-17:20</div></html>";
        data[10][0] = "<html><div style='text-align:center;'>11<br/>17:25-18:10</div></html>";
        data[11][0] = "<html><div style='text-align:center;'>12<br/>18:30-19:15</div></html>";
        data[12][0] = "<html><div style='text-align:center;'>13<br/>19:20-20:05</div></html>";
        data[13][0] = "<html><div style='text-align:center;'>14<br/>20:05-20:55</div></html>";
    }
}
