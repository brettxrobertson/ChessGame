public class Board {

	private int size;
	private Tile tiles;

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSize() {
		throw new UnsupportedOperationException();
	}

	public void createTiles() {
		throw new UnsupportedOperationException();
	}

	public void createPieces() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pieces
	 * @param playerId
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void movePiece(int pieces, int playerId, int x1, int y1, int x2, int y2) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pieces
	 * @param x
	 * @param y
	 */
	public void getValidMoves(int pieces, int x, int y) {
		throw new UnsupportedOperationException();
	}

}