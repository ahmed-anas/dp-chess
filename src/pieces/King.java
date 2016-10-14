package pieces;

import java.util.ArrayList;

import chess.Cell;

public class King extends Piece{
	
	private int x,y; //Extra variables for King class to keep a track of king's position
	
	//King Constructor
	public King(String id,String path,int color,int x,int y)
	{
		setx(x);
		sety(y);
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	//general value access functions
	public void setx(int x)
	{
		this.x=x;
	}
	public void sety(int y)
	{
		this.y=y;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	//Move Function for King Overridden from Pieces
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//King can move only one step. So all the adjacent 8 cells have been considered.
		possiblemoves.clear();
		int posx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
		int posy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getpiece()==null||state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor()))
					possiblemoves.add(state[posx[i]][posy[i]]);
		return possiblemoves;
	}
	
	
	
	//Function to check if king is under threat
	//It checks whether there is any piece of opposite color that can attack king for a given board state
	public boolean isindanger(Cell state[][])
    {
		return isAttackedFromStraightLine(state) || isAttackedFromDiagonal(state) || isAttackedByKnight(state) || isAttackedByPawn(state) || isAttackedByKing(state);
    	
    }
	
	private boolean locationIsEmpty(Cell[][] state, int x, int y){
		return state[x][y].getpiece()==null;
	}
	

	private boolean isAttackedFromStraightLine(Cell[][] state, int x, int y) {
		return ((state[x][y].getpiece() instanceof Rook) || (state[x][y].getpiece() instanceof Queen)) && state[x][y].getpiece().getcolor()!=this.getcolor();
	}


	private boolean isAttackedFromStraightLine(Cell[][] state) {
		for(int i=0;i<8;i++)
    	{
    		if(locationIsEmpty(state, i, y))
    			continue;
    		else if (isAttackedFromStraightLine(state, i, y))
    			return true;
    		else 
    			break;
    		
    	}
   
    	for(int i=0;i<8;i++)
    	{
    		if(locationIsEmpty(state, x, i))
    			continue;
    		else if (isAttackedFromStraightLine(state, x, i))
    			return true;
    		else 
    			break;
    
    	}
    	return false;
	}
	
	private boolean isAttackedByKnight(Cell[][] state, int x, int y) {
		return state[x][y].getpiece()!=null && state[x][y].getpiece().getcolor()!=this.getcolor() && (state[x][y].getpiece() instanceof Knight);
	}
	private boolean isValidPosition(int x, int y)
	{
		return x>=0&&x<8&&y>=0&&y<8;
	}
	
	private boolean isAttackedByKnight(Cell[][] state) {
		//Checking for attack from the Knight of opposite color
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
		{
			if(isValidPosition(posx[i], posy[i]) && isAttackedByKnight(state, posx[i], posy[i]))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isAttackedByKing(Cell[][] state, int x, int y)
	{
		return state[x][y].getpiece()!=null && state[x][y].getpiece().getcolor()!=this.getcolor() && (state[x][y].getpiece() instanceof King);
	}
	
	private boolean isAttackedByKing(Cell[][] state)
	{
		int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
		int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
	
		for(int i=0;i<8;i++)
		{
			if(isValidPosition(pox[i], poy[i]) && isAttackedByKing(state, pox[i], poy[i]))
			{
				return true;
			}
		}
	
		return false;
	}

	private boolean isEnemeyPawn(Cell[][] state, int x, int y){
		return isValidPosition(x, y) && state[x][y].getpiece()!=null&&state[x][y].getpiece().getcolor()!=this.getcolor()&&(state[x][y].getpiece() instanceof Pawn);
	}
	private boolean isAttackedByPawn(Cell[][] state){
	
		if(getcolor()==0)
		{
			if(isEnemeyPawn(state, x-1, y-1) || isEnemeyPawn(state, x-1, y+1))
				return true;
		}
		else
		{
			if(isEnemeyPawn(state, x+1, y-1) || isEnemeyPawn(state, x+1, y+1))
				return true;
		}
		return false;
	}
	
	

	private boolean isAttackedFromDiagonal(Cell[][] state, int tempx, int tempy) {
		return state[tempx][tempy].getpiece().getcolor()!=this.getcolor() && state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen;
	}
	private boolean isAttackedFromDiagonal(Cell[][] state) {
		
		for(int x = 0, y = 7; x < 8 && y >=0; x++, y--)
		{
			if(locationIsEmpty(state, x, y))
			{
				continue;
			}
			else if(isAttackedFromDiagonal(state, x, y))
				return true;
			else
			{
				break;
			}
		}
		
		for(int x = 0, y = 0; x < 8 && y < 8; x++, y++)
		{
			if(locationIsEmpty(state, x, y))
			{
				continue;
			}
			else if(isAttackedFromDiagonal(state, x, y))
				return true;
			else
			{
				break;
			}
		}
		
		return false;
	}

}