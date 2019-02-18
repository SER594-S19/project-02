package BCI.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

import BCI.controller.AffectiveController;
import BCI.Core.*;
import java.awt.Font;

public class Affective extends JPanel{

	
	public Affective(DataGenerator dg) {
		setLayout(null);
		
		JLabel lblAffective = new JLabel("Affective: ");
		lblAffective.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
		lblAffective.setEnabled(false);
		lblAffective.setBounds(6, 59, 81, 16);
		add(lblAffective);
		
		JLabel lblFrustration = new JLabel("Frustration:");
		lblFrustration.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFrustration.setBounds(6, 95, 171, 16);
		add(lblFrustration);
		
		JLabel lblEngagement = new JLabel("Engagement:");
		lblEngagement.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEngagement.setBounds(6, 118, 171, 29);
		add(lblEngagement);
		
		JLabel lblMeditation = new JLabel("Meditation:");
		lblMeditation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMeditation.setBounds(6, 151, 171, 16);
		add(lblMeditation);
		
		JLabel lblSTExcitement = new JLabel("Short Term Excitement: ");
		lblSTExcitement.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSTExcitement.setBounds(6, 182, 200, 16);
		add(lblSTExcitement);
		
		JLabel lblLTExcitement = new JLabel("Long Term Excitement:");
		lblLTExcitement.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLTExcitement.setBounds(6, 211, 200, 16);
		add(lblLTExcitement);
		
		JSlider sliderFrustration = new JSlider();
		sliderFrustration.setPaintLabels(true);
		sliderFrustration.setValue(0);
		sliderFrustration.setMinorTickSpacing(1);
		sliderFrustration.setBounds(201, 90, 288, 29);
		add(sliderFrustration);
		
		JSlider sliderEngagement = new JSlider();
		sliderEngagement.setPaintLabels(true);
		sliderEngagement.setValue(0);
		sliderEngagement.setMinorTickSpacing(1);
		sliderEngagement.setBounds(201, 118, 288, 29);
		add(sliderEngagement);
		
		JSlider sliderMeditation = new JSlider();
		sliderMeditation.setPaintLabels(true);
		sliderMeditation.setValue(0);
		sliderMeditation.setMinorTickSpacing(1);
		sliderMeditation.setBounds(201, 146, 288, 29);
		add(sliderMeditation);
		
		JSlider sliderSTEngagement = new JSlider();
		sliderSTEngagement.setPaintLabels(true);
		sliderSTEngagement.setValue(0);
		sliderSTEngagement.setMinorTickSpacing(1);
		sliderSTEngagement.setBounds(201, 177, 286, 29);
		add(sliderSTEngagement);
		
		JSlider sliderLTEngagement = new JSlider();
		sliderLTEngagement.setPaintLabels(true);
		sliderLTEngagement.setMinorTickSpacing(1);
		sliderLTEngagement.setValue(0);
		sliderLTEngagement.setBounds(201, 206, 288, 29);
		add(sliderLTEngagement);
	
		
		new AffectiveController(sliderFrustration,sliderEngagement,sliderMeditation,sliderSTEngagement,sliderLTEngagement,dg);
		
	}
	
	
//	public static void main (String [] args) {
//		
//		JFrame frame = new JFrame();
//		frame.setSize(500, 400);
//		frame.getContentPane().add(new Affective());
//		
//		
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//		      @Override
//		      public void windowClosing(java.awt.event.WindowEvent e) {
//		        System.exit(0);
//		      }
//		    });
//		
//		frame.setVisible(true);
//	}
}
