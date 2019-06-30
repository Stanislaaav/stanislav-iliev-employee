package com.sirmasolution.siliev;

import javax.swing.SwingUtilities;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sirmasolution.siliev.view.GUIControl;


/**
 * 
 * Main application class to capture employee data and apply business logic to
 * pair of employees who have worked together on the same projects for the
 * longest period of time.
 * 
 * @author Stan
 *
 */
@Configuration
@ComponentScan
public class MainClass {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUIControl();
			}
		});		
	}
}
