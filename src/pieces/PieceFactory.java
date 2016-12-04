package pieces;

public abstract class PieceFactory {

	
	
	public static final int TYPE_KING = 1;
	public static final int TYPE_KNIGHT = 2;
	public static final int TYPE_PAWN = 3;
	public static final int TYPE_ROOK = 4;
	public static final int TYPE_BISHOP = 5;
	public static final int TYPE_QUEEN = 6;
	
	public abstract Piece create(String id, String path, int color, int type);
}
