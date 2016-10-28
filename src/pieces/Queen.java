package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece{
	
	public Queen(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		
		//Checking possible moves in vertical direction
		checkW(state, x, y);
		checkE(state, x, y);
		
		
		//Checking possible moves in horizontal Direction
		checkS(state, x, y);
		checkN(state, x, y);
		
		//Checking for possible moves in diagonal direction
		checkSE(state, x, y);
		checkNW(state, x, y);
		checkSW(state, x, y);
		checkNE(state, x, y);
		return possiblemoves;
	}

	private void checkSE(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x+1;tempy=y-1;
		while(tempx<8&&tempy>=0)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy--;
		}
	}

	private boolean noPieceAtCell(Cell[][] state, int tempx, int tempy) {
		return state[tempx][tempy].getpiece()==null;
	}

	private void checkNW(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<8)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}
	}

	private void checkSW(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}
	}

	private boolean ourPieceAtCell(Cell[][] state, int tempx, int tempy) {
		return state[tempx][tempy].getpiece().getcolor()==this.getcolor();
	}

	private void checkNE(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x+1;tempy=y+1;
		while(tempx<8&&tempy<8)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
	}

	private void checkN(Cell[][] state, int x, int y) {
		int tempy;
		tempy=y+1;
		while(tempy<8)
		{
			if(noPieceAtCell(state, x, tempy))
				possiblemoves.add(state[x][tempy]);
			else if(ourPieceAtCell(state, x, tempy))
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy++;
		}
	}

	private void checkS(Cell[][] state, int x, int y) {
		int tempy=y-1;
		while(tempy>=0)
		{
			if(noPieceAtCell(state, x, tempy))
				possiblemoves.add(state[x][tempy]);
			else if(ourPieceAtCell(state, x, tempy))
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy--;
		}
	}

	private void checkE(Cell[][] state, int x, int y) {
		int tempx;
		tempx=x+1;
		while(tempx<8)
		{
			if(noPieceAtCell(state, tempx, y))
				possiblemoves.add(state[tempx][y]);
			else if(ourPieceAtCell(state, tempx, y))
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx++;
		}
	}

	private void checkW(Cell[][] state, int x, int y) {
		int tempx=x-1;
		while(tempx>=0)
		{
			if(noPieceAtCell(state, tempx, y))
				possiblemoves.add(state[tempx][y]);
			else if(ourPieceAtCell(state, tempx, y))
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx--;
		}
	}
}