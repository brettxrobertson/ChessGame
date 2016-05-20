package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


/**
 * The popup composite piece selection panel. 
 * Offers an interactive selection based upon the possible composite options presented to it.
 * Returns the selected set of options back to the requester. 
 * 
 * @author keltie
 *
 */
public class PieceVisualSelector {
	private static class InstanceHolder {
		private static PieceVisualSelector instance = new PieceVisualSelector(); 
	}
	
	private static eventHandling.Subject controllerEventHandler;
	private static ArrayList<model.pieces.AbstractPiece> piecesOut;
	
	private static JPanel dialoguePanel; //= new JPanel();
	private static JPanel checkBoxPanel; // = new JPanel(); 
	private static JLabel iconPanel; //= new JLabel();
	
	private static JCheckBox[] compositeOptions;
	private static JToggleButton toggleButton = new JToggleButton("Select All");
	
	PieceVisualSelector(){
		setControllerEventHandler(controllerEventHandler);
	}

	public static PieceVisualSelector getInstance(){
		return InstanceHolder.instance;
	}	
	
	public static ArrayList<model.pieces.AbstractPiece> launchDialogue(Component component, final ArrayList<model.pieces.AbstractPiece> pieces){
		piecesOut = new ArrayList<model.pieces.AbstractPiece>();
		compositeOptions = new JCheckBox[pieces.size()];
	
		createPieceVisualSelectorDialogue(pieces);

		int SelectorOptionDialogue = JOptionPane.showConfirmDialog(component, 
				dialoguePanel, "Compose a piece", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if (SelectorOptionDialogue == 0 ){
			for (int i = 0; i < compositeOptions.length; i++){
				if (compositeOptions[i].isSelected()){
					piecesOut.add(pieces.get(i));
				}
			}
			controllerEventHandler.setEvent("comp");
			
		} else {
			// return null for piecesOut
			controllerEventHandler.setEvent("cancel selection");
		}
		//reset elements in the (static) dialogue for next time.
		toggleButton.setText("Select All");
		toggleButton.setSelected(false);
		checkBoxPanel.removeAll();
		dialoguePanel.removeAll();
		compositeOptions = null;
		
		return piecesOut;
	}
	
	private static void createPieceVisualSelectorDialogue(final ArrayList<model.pieces.AbstractPiece> pieces){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		
		iconPanel = factory.createJLabel("", "IconPanel", 60, factory.emptyBorder);
		iconPanel.setIcon(new ImageIcon(
				PieceVisualDecorator.getPieceVisual(Constants.BARRIER,
						(Constants.WHITE))));
		
		checkBoxPanel = factory.createJPanel("CheckBoxPanel", new GridLayout(0,1), 
				new Dimension(100,150), factory.emptyBorder, JComponent.LEFT_ALIGNMENT);
		//BoxLayout requires the panel to be already defined!? differing from other layout managers 
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		checkBoxPanel.add(toggleButton);

		final ArrayList<model.pieces.AbstractPiece> piecesIcon = new ArrayList<model.pieces.AbstractPiece>();
		
		ItemListener itemListenerToggleButton = new ItemListener(){
			@Override 
			public void itemStateChanged(ItemEvent e) {
				if (e.getItemSelectable() == toggleButton){
					if (toggleButton.isSelected()){
						toggleButton.setText("Clear Selection");
						
						for (int i = 0; i < compositeOptions.length; i++){
							compositeOptions[i].setSelected(true);
						}				
					} else {
						toggleButton.setText("Select All");
						
						for (int i = 0; i < compositeOptions.length; i++){
							compositeOptions[i].setSelected(false);
						}
					}
				}
			}
		};
		
		ItemListener itemListenerCheckBox = new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					for (int i = 0; i < compositeOptions.length; i++){
						if (compositeOptions[i].isSelected()){
							piecesIcon.add(pieces.get(i));
						} else {
							piecesIcon.remove(pieces.get(i));
						}
					}
					if (piecesIcon.size() == 0){
						toggleButton.setSelected(false);
						toggleButton.setText("Select All");
						iconPanel.setIcon(new ImageIcon(
								PieceVisualDecorator.getPieceVisual(Constants.BARRIER,
										(Constants.WHITE))));
					} else if (piecesIcon.size() == compositeOptions.length){
						toggleButton.setSelected(true);
						toggleButton.setText("Clear Selection");
						iconPanel.setIcon(new ImageIcon(
								PieceVisualDecorator.getPieceVisual(piecesIcon)));
					} else {
						iconPanel.setIcon(new ImageIcon(
								PieceVisualDecorator.getPieceVisual(piecesIcon)));
					}
					
					piecesIcon.clear();
					
				} catch (IndexOutOfBoundsException ex){
					System.out.println(ex.getCause().toString() + ", " + ex.getMessage());
				}
			}
		};
		

		for (int i = 0; i < pieces.size(); i++){
			checkBoxPanel.add(compositeOptions[i] = factory.createJCheckBox(Constants.pieceName[pieces.get(i).getType()], 
					Constants.pieceName[pieces.get(i).getType()], 
					new Dimension(0,0), factory.emptyBorder, true, false, itemListenerCheckBox, ""));
		}
		
		toggleButton.addItemListener(itemListenerToggleButton);

		dialoguePanel = factory.createJPanel("Composite Selection Panel", new BorderLayout(), 
				new Dimension(160,150), factory.emptyBorder, JComponent.LEFT_ALIGNMENT);
		dialoguePanel.add(checkBoxPanel, BorderLayout.LINE_START);
		dialoguePanel.add(iconPanel, BorderLayout.CENTER);
	}
	
	public static ArrayList<model.pieces.AbstractPiece> getCompositePieces(){
		return piecesOut;
	}
	/**
	 * @param controllerEventHandler the controllerEventHandler to set
	 */
	public static void setControllerEventHandler(eventHandling.Subject controllerEventHandler) {
		PieceVisualSelector.controllerEventHandler = controllerEventHandler;
	}
}
