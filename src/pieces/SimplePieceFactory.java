package pieces;

import chess.Main;

public class SimplePieceFactory extends PieceFactory{

	@Override
	public Piece create(String id, String path, int color, int type) {
		
		if(type == PieceFactory.TYPE_KING){
			int x = color == Main.WHITE_COLOUR?7:0;
			int y = 3;
			return new KingPiece(id, path, color, x, y);
		}
		
		Piece piece = new SimplePiece(id, path, color);
		switch(type){
		case TYPE_BISHOP:
			piece = new DiagonalMover(piece);
			return piece;
		case TYPE_KNIGHT:
			piece = new Knight(piece);
			return piece;
		
		case TYPE_PAWN:
			piece = new Pawn(piece);
			return piece;
		case TYPE_QUEEN:
			piece = new DiagonalMover(piece);
			piece = new StraightMover(piece);
			return piece;
		case TYPE_ROOK:
			piece = new StraightMover(piece);
			return piece;
		default:
			throw new IllegalArgumentException("Invalid type value");
		}
	}

}
