package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUIConcreteFactory extends GUIAbstractFactory {

	public Border emptyBorder = BorderFactory.createEmptyBorder();
	public Border paddedBorder0 = new EmptyBorder(0,0,30,0);
	public Border paddedBorder1 = BorderFactory.createEmptyBorder(0, 0, 10, 0);
	public Border paddedBorder2 = BorderFactory.createEmptyBorder(30, 0, 30, 0);
	public Border paddedBorder3 = BorderFactory.createEmptyBorder(10, 0, 10, 0);
	public Border etchedBorder = BorderFactory.createEtchedBorder();
	public Border simpleBorder = new CompoundBorder(new EmptyBorder(0,0,0,0),new LineBorder(Color.BLACK));
	public Border titleBorder = BorderFactory.createTitledBorder(emptyBorder);
	
	@Override
	public JPanel createJPanel(String name, LayoutManager layout,
			Dimension dimension, Border border, float alignment) {
		JPanel panel = new JPanel();
		panel.setName(name);
		panel.setLayout(layout);
		panel.setPreferredSize(dimension);
		panel.setBorder(border);
		panel.setAlignmentX(alignment);
		panel.setVisible(true);
		return panel;
	}

	@Override
	public JLayeredPane createJLayeredPane(String name, LayoutManager layout,
			Dimension dimension, Border border, float alignment) {
		JLayeredPane panel = new JLayeredPane();
		panel.setName(name);
		panel.setLayout(layout);
		panel.setPreferredSize(dimension);
		panel.setBorder(border);
		panel.setAlignmentX(alignment);
		panel.setVisible(true);
		return panel;
	}
	
	@Override
	public JFrame createJFrame(String name) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		return frame;
	}

	@Override
	public JToolBar createJToolBar(String name, Dimension dimension) {
		JToolBar toolBar = new JToolBar();
		toolBar.setName(name);
		toolBar.setFloatable(false);
		toolBar.setPreferredSize(dimension);
		return toolBar;
	}

	
	
	@Override
	public JLayeredPane createBasePanel(String name, LayoutManager layout,
			Dimension dimension, Border border, float alignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createBoardPanel(String name, LayoutManager layout,
			Dimension dimension, Border border, float alignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createStatusPanel(String name, LayoutManager layout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createConsolePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFrame createMainFrame(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JToolBar createMenuBar(String name, LayoutManager layout,
			Dimension dimension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createNewGamePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createPopUpPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JLabel createJLabel(String labelString, String labelName,
			int dimensionX, Border border) {
		JLabel label = new JLabel(labelString);
		label.setName(labelName);
		label.setHorizontalTextPosition(JLabel.LEFT);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setPreferredSize(new Dimension(dimensionX,20));
		label.setBorder(border);
		return label;
	}
	@Override
	public JLabel createJLabel(String labelString, String labelName,
			Dimension dimension, Border border) {
		JLabel label = new JLabel(labelString);
		label.setName(labelName);
		label.setHorizontalTextPosition(JLabel.LEFT);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setPreferredSize(dimension);
		label.setBorder(border);
		return label;
	}

	@Override
	public JTextField createJTextField(String textFieldString,
			String textFieldName, int dimensionX) {
		JTextField textField = new JTextField(textFieldString);
		textField.setName(textFieldName);
		textField.setHorizontalAlignment(JTextField.LEFT);
		textField.setPreferredSize(new Dimension(dimensionX,20));
		return textField;
	}

	@Override
	public JSpinner createJSpinner(String spinnerName, int dimensionX,
			int initialValue, int lowerBound, int upperBound) {
		SpinnerModel sm = new SpinnerNumberModel(initialValue, lowerBound, upperBound, 1);
		JSpinner spinner = new JSpinner(sm);
		spinner.setName(spinnerName);
		spinner.setPreferredSize(new Dimension(dimensionX,20));
		return spinner;
	}

	@Override
	public JButton createJButton(String text, String name, Border border,
			boolean actionListener, boolean mouseListener,
			boolean mouseMotionListener,
			eventHandling.Subject eventHandler) {
		
		final eventHandling.Subject controllerEventHandler = eventHandler;
		JButton button = new JButton(text);
		
		button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					controllerEventHandler.setEvent("mouseclick");
					//BoardView.getInstance().getControllerEventHandler().setEvent("mouseclick");
				}
         });
		button.addMouseListener(new MouseListener(){
           	@Override 
         	public void mouseClicked(MouseEvent e) {
           		if (e.getButton() == MouseEvent.BUTTON1){
						controllerEventHandler.setEvent(e.getComponent().getName());
            	}
         	}
         	@Override
         	public void mouseEntered(MouseEvent arg0) {}
         	@Override
         	public void mouseExited(MouseEvent arg0) {}
         	@Override
         	public void mousePressed(MouseEvent arg0) {}
         	@Override
         	public void mouseReleased(MouseEvent arg0) {}
         });
         
		button.addMouseMotionListener(new MouseMotionListener(){
				@Override
				public void mouseDragged(MouseEvent arg0) {}
				@Override
				public void mouseMoved(MouseEvent arg0) {}
         });
		return button;
	}

	@Override
	public JRadioButton createJRadioButton(String text, String name,
			Border border, boolean enabled, ActionListener actionListener, String actionCommand) {
		JRadioButton radioButton = new JRadioButton(text);
		radioButton.setName(name);
		radioButton.setSelected(enabled);
		radioButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		radioButton.setPreferredSize(new Dimension(220,20));
		radioButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		radioButton.setActionCommand(actionCommand);
		if (actionListener != null){
			radioButton.addActionListener(actionListener);
		} else {
			radioButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(arg0.getActionCommand());
			}});
		}
		
		return radioButton;
	}

	@Override
	public JComboBox<String> createJComboBox(String[] textList, String name,
			Dimension dimension, Border border, boolean actionListener, String actionCommand) {
		JComboBox<String> comboBox = new JComboBox<String>(textList);
		comboBox.setName(name);
		comboBox.setPreferredSize(dimension);
		comboBox.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		comboBox.setBorder(border);
		
		comboBox.setActionCommand(actionCommand);
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(arg0.getActionCommand());
			}});
		
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				//System.out.println(arg0.getItem().toString() + " selected");
			}});
		return comboBox;
	}
	
	@Override
	public JComboBox<String> createJComboBox(Vector<String> textList, String name,
			Dimension dimension, Border border, boolean actionListener, String actionCommand) {
		JComboBox<String> comboBox = new JComboBox<String>(textList);
		comboBox.setName(name);
		comboBox.setPreferredSize(dimension);
		comboBox.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		comboBox.setBorder(border);
		
		comboBox.setActionCommand(actionCommand);
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(arg0.getActionCommand());
			}});
		return comboBox;
	}

	@Override
	public ButtonGroup createButtonGroup(String name, Dimension dimension, Border border) {
		ButtonGroup buttonGroup = new ButtonGroup();
		return buttonGroup;
	}

	@Override
	public JCheckBox createJCheckBox(String text, String name,
			Dimension dimension, Border border, boolean enabled,
			boolean selected, ItemListener itemListener, String actionCommand) {
		JCheckBox checkBox = new JCheckBox(text);
		checkBox.setPreferredSize(dimension);
		checkBox.setEnabled(enabled);
		checkBox.setSelected(selected);
		
		checkBox.setActionCommand(actionCommand);
		
		if (itemListener != null){
			checkBox.addItemListener(itemListener);
		} else {
			checkBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent arg0) {

			}});
		}
		return checkBox;
	}



}
