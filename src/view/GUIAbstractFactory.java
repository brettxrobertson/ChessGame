package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public abstract class GUIAbstractFactory {

	   public abstract JPanel createJPanel(String name, LayoutManager layout, 
			   Dimension dimension, Border border, float alignment);
	   
	   public abstract JLayeredPane createJLayeredPane(String name, LayoutManager layout, 
			   Dimension dimension, Border border, float alignment);
	   
	   public abstract JFrame createJFrame(String name);
	   
	   public abstract JToolBar createJToolBar(String name, Dimension dimension);
	   
	   public abstract JLabel createJLabel(String labelString, String labelName, 
			   int dimensionX, Border border);
	   public abstract JLabel createJLabel(String labelString, String labelName, 
			   Dimension dimension, Border border);
	   
	   public abstract JTextField createJTextField(String textFieldString, String textFieldName, 
			   int dimensionX);
	   
	   public abstract JSpinner createJSpinner(String spinnerName, int dimensionX, 
			   int initialValue, int lowerBound, int upperBound);
	   
	   public abstract JButton createJButton(String text, String name, Border border,
			   boolean actionListener, boolean mouseListener,
			   boolean mouseMotionListener,
			   eventHandling.Subject eventHandler);
	   
	   public abstract JRadioButton createJRadioButton(String text, String name, 
			   Border border,
			   boolean enabled, ActionListener actionListener, String actionCommand);
	   
	   public abstract JCheckBox createJCheckBox(String text, String name, Dimension dimension,
			   Border border, boolean enabled, boolean selected, 
			   ItemListener itemListener, String actionCommand);
	   
	   public abstract JComboBox<?> createJComboBox(String[] textList, String name, Dimension dimension, 
			   Border border, boolean actionListener, String actionCommand);
	   public abstract JComboBox<String> createJComboBox(Vector<String> textList, String name, Dimension dimension, 
			   Border border, boolean actionListener, String actionCommand);
	   
	   public abstract ButtonGroup createButtonGroup(String name, Dimension dimension,	Border border);
	   
	   
	   
	   public abstract  JLayeredPane createBasePanel(String name, LayoutManager layout, 
			   Dimension dimension, Border border, float alignment);
	   public abstract JPanel createBoardPanel(String name, LayoutManager layout, 
			   Dimension dimension, Border border, float alignment);
	   public abstract JPanel createStatusPanel(String name, LayoutManager layout);
	   public abstract JPanel createConsolePanel();
	   public abstract JFrame createMainFrame(String name);
	   public abstract JToolBar createMenuBar(String name, LayoutManager layout, 
			   Dimension dimension);
	   
	   public abstract JPanel createNewGamePanel();
	   
	   public abstract JPanel createPopUpPanel();
}
