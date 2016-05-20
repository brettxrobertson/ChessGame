/**
 * 
 */
package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The decorator/image/icon contruction class. Takes a source image
 * and sub-images the required parts for icons.  
 * 
 * @author keltie
 *
 */
public class PieceVisualDecorator {
	private static class InstanceHolder {
		private static PieceVisualDecorator instance = new PieceVisualDecorator(boardSize, chessboardPreferredSize); 
	}

	private static int boardSize;// = 6;
	private static int chessboardPreferredSize;// = 768;
	private static Image[][] pieceVisuals = new Image[4][12];
	private static String imageAddress = ""; 
	private static File imageName = new File(imageAddress + Constants.ICON_SET);
	private static BufferedImage sourceVisual = new BufferedImage(2048, 512, BufferedImage.TYPE_INT_ARGB_PRE);
	
	static final int[] imageSorter = {Constants.NONE, Constants.KING, Constants.QUEEN, 
		Constants.BISHOP, Constants.KNIGHT, Constants.ROOK, Constants.PAWN};
	
	//private static ArrayList<Image> imageSortList = new ArrayList<Image>();
	
	//private void initImageSortList(){
	//	for (int i = 0; i < 10; i++){
	//	imageSortList.add(new BufferedImage((chessboardPreferredSize / boardSize), 
	//			(chessboardPreferredSize / boardSize), BufferedImage.TYPE_INT_ARGB));
	//	}
	//}

	
	PieceVisualDecorator(int boardSize, int chessboardPreferredSize){
		PieceVisualDecorator.boardSize = boardSize;
		PieceVisualDecorator.chessboardPreferredSize = chessboardPreferredSize;

		createPieceVisuals(boardSize, chessboardPreferredSize);
		//initImageSortList();
	}
	
	public static PieceVisualDecorator getInstance(int boardSize, int chessboardPreferredSize){
		PieceVisualDecorator.boardSize = boardSize;
		PieceVisualDecorator.chessboardPreferredSize = chessboardPreferredSize;

		createPieceVisuals(boardSize, chessboardPreferredSize);
		return InstanceHolder.instance;
	}
		
	public static void createPieceVisuals(int boardSize, int chessboardPreferredSize){
		try {
			sourceVisual = ImageIO.read(imageName);
		} catch (IOException e) {
			System.out.println("PieceVisual image read failed. Is the source image: " + imageName + " in the correct directory? ");
			e.printStackTrace();
		}
		
		for (int i = 0; i < pieceVisuals.length; i++){
			for (int j = 0; j < pieceVisuals[i].length; j++){
				pieceVisuals[i][j] = sourceVisual.getSubimage(
						j * 128, i * 128, 128, 128).getScaledInstance(
								(chessboardPreferredSize / boardSize), 
								(chessboardPreferredSize / boardSize), 
								Image.SCALE_SMOOTH);
			}
		}
	}


	
	/**
	 * @param boardSize the boardSize to set
	 */
	public static void setBoardSize(int boardSize) {
		PieceVisualDecorator.boardSize = boardSize;
	}

	/**
	 * @param chessboardPreferredSize the chessboardPreferredSize to set
	 */
	public static void setChessboardPreferredSize(int chessboardPreferredSize) {
		PieceVisualDecorator.chessboardPreferredSize = chessboardPreferredSize;
	}	

	public static Image getPieceVisual(int COLOUR, int PIECE){
		return pieceVisuals[PIECE][COLOUR];
	}
	
	public static BufferedImage getPieceVisual(final ArrayList<model.pieces.AbstractPiece> pieces){
		//TODO order the subimages to suit source for composite effect 
		// eg pawn to front, kingy and queeny to the rear..
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);  		  
		BufferedImage compositeImage = new BufferedImage((chessboardPreferredSize / boardSize), 
				(chessboardPreferredSize / boardSize), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2DcompositeImage = compositeImage.createGraphics();
		
		ArrayList<model.pieces.AbstractPiece> imageSortList = new ArrayList<model.pieces.AbstractPiece>();
		//for (int i = 0; i < pieces.size(); i++){
		//	imageSortList.add(compositeImage);
		//}
		
		int compositeLookupOffset = 0;
		if (pieces.size() > 1) {
			compositeLookupOffset = 2;
		} else {
			g2DcompositeImage.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		// Add the images to a temporary list to sort for draw order.
		for (int i = 0; i < pieces.size(); i++){
			//System.out.println("imageSorter index: " + imageSorter[pieces.get(i).getType()] 
			//		+ ", piecetype: " + pieces.get(i).getType() + ", piece size: " + pieces.size()
			//		+ ", imageSortList size: " + imageSortList.size());

			if (pieces.get(i).getType() == 5){ //king
				imageSortList.add(pieces.get(i));
			}
			if (pieces.get(i).getType() == 4){ //queen
				imageSortList.add(pieces.get(i));
			}
			if (pieces.get(i).getType() == 2){ //bishop
				imageSortList.add(pieces.get(i));
			}
			if (pieces.get(i).getType() == 3){ //knight
				imageSortList.add(pieces.get(i));
			}
			if (pieces.get(i).getType() == 1){ //rook
				imageSortList.add(pieces.get(i));
			}
			if (pieces.get(i).getType() == 6){ //pawn
				imageSortList.add(pieces.get(i));
			}

			
			//imageSortList.set(imageSorter[pieces.get(i).getType()], 
			//		pieceVisuals[(pieces.get(i).getPlayerId() - 1 + compositeLookupOffset)][pieces.get(i).getType()]);
		}	
		
		for (int i = 0; i < imageSortList.size(); i++){
			g2DcompositeImage.drawImage(pieceVisuals[(imageSortList.get(i).getPlayerId() - 1 + compositeLookupOffset)][imageSortList.get(i).getType()], 0, 0, null);				
			g2DcompositeImage.setComposite(ac); 
		}	
		
		//for (int i = 0; i < pieces.size(); i++){
		//	g2DcompositeImage.drawImage(pieceVisuals[(pieces.get(i).getPlayerId() - 1 + compositeLookupOffset)][pieces.get(i).getType()],
		//					0, 0, null);				
		//	g2DcompositeImage.setComposite(ac); 
		//}	
		g2DcompositeImage.dispose();
		return compositeImage;
	}
}

