package pieces;

import java.util.ArrayList;

import javax.management.BadAttributeValueExpException;
import javax.naming.directory.InvalidAttributeValueException;

import chess.Cell;

public class SimplePiece extends PieceType{
	
	
	public SimplePiece(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}

	@Override
	public int getPieceType() {
		return Piece.SIMPLE_TYPE;
	}


	

}
