package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BoardInitialiseBoardButtonsDecorator extends BoardAbstractDecorator {

	private int boardSize = 2;
	private int chessboardPreferredSize = 768;
	private eventHandling.Subject controllerEventHandler;
	
	BoardInitialiseBoardButtonsDecorator(DnDJLabel[][] chessBoardSquares) {
		super(chessBoardSquares);
		
		if (GameView.getNewGameData() != null){
			this.boardSize = GameView.getNewGameData().boardSize;
			this.chessboardPreferredSize = GameView.getNewGameData().chessboardPreferredSize;
		}
		controllerEventHandler = BoardView.getInstance().getControllerEventHandler();
	}

    
	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
		
		chessBoardSquares = new DnDJLabel[boardSize][boardSize];
		GUIConcreteFactory factory = new GUIConcreteFactory();
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				
				chessBoardSquares[i][j] = new DnDJLabel();  //empty label/text as it will render in the button
				////set the object name to contain the tile address in chess algebra or Cartesian
				////chessBoardSquares[i][j].setName(alphabetChar.charAt(j) + "" + (chessBoardSquares[i].length - i));
				
				chessBoardSquares[i][j].setName(i + "," + j);
				chessBoardSquares[i][j].setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1),new LineBorder(Color.BLACK)));
				
				//apply a proxy image (transparent) that we can replace with a piece visual/
                ImageIcon pieceProxyIcon = new ImageIcon(new BufferedImage(
                		(chessboardPreferredSize / boardSize),
                		(chessboardPreferredSize / boardSize), 
                		BufferedImage.TYPE_INT_ARGB));
                chessBoardSquares[i][j].setIcon(pieceProxyIcon);
                chessBoardSquares[i][j].setOpaque(true);
 
                chessBoardSquares[i][j].addMouseListener(new MouseAdapter(){
                  	@Override 
                	public void mouseClicked(MouseEvent e) {
                  			//System.out.println(e.paramString().split(",")[0] + e.getComponent().getName());
    						//controllerEventHandler.setEvent(e.getComponent().getName());
                 	}
                	@Override
                	public void mouseEntered(MouseEvent e) {
			        	//System.out.println(e.paramString().split(",")[0] + e.getComponent().getName());
                	}
                	public void mousePressed(MouseEvent e) {
			        	//System.out.println(e.paramString().split(",")[0] + " InitBoard: " + e.getComponent().getName());
                		if (e.getComponent().isEnabled()){ //filter out messages to the observer from non-valid-tiles
                			controllerEventHandler.setEvent(e.getComponent().getName());
                		}
                	}
                	@Override
                	public void mouseReleased(MouseEvent e) {
                       	//System.out.println(e.paramString().split(",")[0] + " InitBoard: " + e.getComponent().getName());
                   		//controllerEventHandler.setEvent(e.getComponent().getName());
                 	}
                });
 			}
		}
		return chessBoardSquares;
	}

}
