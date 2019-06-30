package com.sirmasolution.siliev.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sirmasolution.siliev.MainClass;
import com.sirmasolution.siliev.date.parser.DateParser;
import com.sirmasolution.siliev.model.Employee;
import com.sirmasolution.siliev.model.PairedEmployees;
import com.sirmasolution.siliev.services.impl.CSVParserServiceImpl;

/**
 * This class make frame with table and load button.
 * 
 * @author Stan
 *
 */

public class GUIControl{

	
	private final String[] HEADER = {"First Employee Id", "Second Employee Id", "Project"}; 
	private DefaultTableModel model = new DefaultTableModel(null, HEADER);

	private JTable table;
	private JButton buttonLoad;
	private JFrame frame;
    private JPanel buttonPanel;

	public GUIControl() {

		table = new JTable(model);		
		frame = new JFrame();
		frame.setTitle("Longest period work in the same project");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.add(new JScrollPane(table));		
		frame.pack();
				
		buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
		buttonLoad = new JButton("Get File");
		frame.add(buttonLoad, BorderLayout.SOUTH);
		buttonLoad = createButton();
				
	}

	private JButton createButton() {
		JFileChooser fileChooser = new JFileChooser();
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					File file = fileChooser.getSelectedFile();

					DateParser.parseDateIntoNewFile(file);

					File fileOutput = new File("outFile.txt");					
					DefaultTableModel model = createModel(fileOutput);
					table.setModel(model);
				}
			}
		});
		
		return buttonLoad;
	}

	private DefaultTableModel createModel(File file) {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);

		CSVParserServiceImpl csvParserServiceImpl = (CSVParserServiceImpl) applicationContext
				.getBean("CSVParserServiceImpl");

		List<PairedEmployees> employeesWorkedLongestInProjects = null;
		try {

			List<Employee> employeeProjectEngagement = csvParserServiceImpl.parseEmployeeData(file);

			employeesWorkedLongestInProjects = csvParserServiceImpl
					.getListOfPairedEmployeesWorkedLongestInProject(employeeProjectEngagement);

			System.out.println(employeesWorkedLongestInProjects);

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object rowData[] = new Object[3];
		for (int i = 0; i < employeesWorkedLongestInProjects.size(); i++) {
			rowData[0] = employeesWorkedLongestInProjects.get(i).getFirstEmpId();
			rowData[1] = employeesWorkedLongestInProjects.get(i).getSecondEmpId();
			rowData[2] = employeesWorkedLongestInProjects.get(i).getProjectId();
			
			model.addRow(rowData);
		}
		
		((ConfigurableApplicationContext) applicationContext).close();

		return model;
	}
}