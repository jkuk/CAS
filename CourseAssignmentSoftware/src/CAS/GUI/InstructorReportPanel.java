/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.GUI;

import CAS.CourseAssignment;
import CAS.Data.Course;
import CAS.Data.Instructor;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Dailton Rabelo
 */
public class InstructorReportPanel extends JPanel {
    private JList list;
    private JLabel label1;
    private DefaultListModel listModel;
    private JScrollPane listScroller;
    private HashMap<String, Instructor> instructors;
    private HashMap<String, Course> courses;
    private CourseAssignment courseAssignment;

    
    public InstructorReportPanel(CourseAssignment courseAssignment) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        setBackground(Color.LIGHT_GRAY);
        
        this.courseAssignment = courseAssignment;
        instructors = courseAssignment.getInstructors();
        courses = courseAssignment.getCourses();
        
        label1 = new JLabel("InstructorPanel");
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.05;
        add(label1, c);
        
        listModel = new DefaultListModel();
        //Adds instructors to the list
          for (Instructor instructor : instructors.values()) {
            listModel.addElement(instructor.getName());
            //If there is a course under that instructor, it adds to the list also.
            //Also If I add a new tab before courses, it will be harder to search for the course
            //because you will have to concatenate the course. Should I do that?
            for(Course course : instructor.getCourses())
              listModel.addElement("\t" + course.getClassCode() + course.getSection());
        }

        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        listScroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 0.5;
        
        add(listScroller, c);
        setVisible(true);
        
    }
    /*
     * Returns the selected item using the getSelectedValue method that is
     * in JList
     */
    public Object getSelected(){
      String selected = ((String)list.getSelectedValue()).replace("\t", "");
      Instructor i = instructors.get(selected);
      Course c = courses.get(selected);
      if(!i.equals(null)) {
          return i;
      }
      else {
          return c;
      }
    }

}