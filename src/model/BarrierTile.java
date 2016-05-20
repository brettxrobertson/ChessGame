package model;

/**
 * Type of Tile - Barrier
 * 
 * @author Brett Robertson (s3437164)
 * @author Dolly Shah (s3399503)
 */
public class BarrierTile extends Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146365493907715271L;
	final private int points = Constants.POINT_BARRIER;
	
	public int getTileValue() {

		//int totalValue = AbstractPiece.getPointsValue() * piece.getPiecesCount();
		int totalValue = points;
		System.out.println(">>>>>>>>>>>BARRIER tile value = " + totalValue);
		return totalValue;
	}	

	@Override
	public String toString() {
		return "*";
	}
}
