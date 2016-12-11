package pieces;



class SimplePiece extends PieceType{
	
	
	SimplePiece(String id,String path,int color)
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
