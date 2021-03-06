package pieces;

import java.util.ArrayList;
import chess.Cell;

public class KingPiece extends PieceType{
	private int x,y; //Extra variables for King class to keep a track of king's position
	

	KingPiece(String id,String path,int color,int x,int y)
	{
		setx(x);
		sety(y);
		setId(id);
		setPath(path);
		setColor(color);
	}
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
		public ArrayList<Cell> move(Cell state[][],Coordinates position)
		{
			//King can move only one step. So all the adjacent 8 cells have been considered.
			possiblemoves.clear();
			
			Coordinates[] positions = {
				new Coordinates(position.getX()    , position.getY() - 1),
				new Coordinates(position.getX()    , position.getY() + 1),
				new Coordinates(position.getX() + 1, position.getY() - 1),
				new Coordinates(position.getX() + 1, position.getY()),
				new Coordinates(position.getX() + 1, position.getY() + 1),
				new Coordinates(position.getX() - 1, position.getY() - 1),
				new Coordinates(position.getX() - 1, position.getY()),
				new Coordinates(position.getX() - 1, position.getY() + 1)
				
			};
			
			for(int i = 0; i < 8; i ++){
				if(positions[i].isValid() && (state[positions[i].getX()][positions[i].getY()].getpiece()==null||state[positions[i].getX()][positions[i].getY()].getpiece().getcolor()!=this.getcolor()))
					possiblemoves.add(state[positions[i].getX()][positions[i].getY()]);
			}
			return possiblemoves;
		}
		
		
		private boolean locationIsEmpty(Cell[][] state, int x, int y){
			return state[x][y].getpiece()==null;
		}
		

		private boolean isAttackedFromStraightLine(Cell[][] state, int x, int y) {
			return ((state[x][y].getpiece().hasInstance(StraightMover.class))) && state[x][y].getpiece().getcolor()!=this.getcolor();
		}


		private boolean isAttackedFromStraightLine(Cell[][] state) {
			for(int i=x+1;i<8;i++)
	    	{
	    		if(locationIsEmpty(state, i, y))
	    			continue;
	    		else if (isAttackedFromStraightLine(state, i, y))
	    			return true;
	    		else 
	    			break;
	    		
	    	}
			for(int i=x-1;i>=0;i--)
	    	{
	    		if(locationIsEmpty(state, i, y))
	    			continue;
	    		else if (isAttackedFromStraightLine(state, i, y))
	    			return true;
	    		else 
	    			break;
	    		
	    	}
	   
			for(int i=y+1;i<8;i++)
	    	{
	    		if(locationIsEmpty(state, x, i))
	    			continue;
	    		else if (isAttackedFromStraightLine(state, x, i))
	    			return true;
	    		else 
	    			break;
	    
	    	}
			   
			for(int i=y-1;i>=0;i--)
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
		
		private boolean isLongRangeDiagonalAttacker(Cell cell)
		{
			return cell.getpiece().getcolor()!=this.getcolor() && (cell.getpiece().hasInstance(DiagonalMover.class)); 
		}
		
		private boolean isAttackedFromDiagonal(Cell state[][]){
			//checking for attack from diagonal direction
	    	int tempx=x+1,tempy=y-1;
			while(tempx<8&&tempy>=0)
			{
				if(locationIsEmpty(state, tempx, tempy))
				{
					tempx++;
					tempy--;
				}
				else if(isLongRangeDiagonalAttacker(state[tempx][tempy]))
					return true;
				else
				{
					break;
				}
			}
			tempx=x-1;tempy=y+1;
			while(tempx>=0&&tempy<8)
			{
				if(locationIsEmpty(state, tempx, tempy))
				{
					tempx--;
					tempy++;
				}
				else if(isLongRangeDiagonalAttacker(state[tempx][tempy]))
					return true;
				else
				{
					break;
				}
			}
			tempx=x-1;tempy=y-1;
			while(tempx>=0&&tempy>=0)
			{
				if(locationIsEmpty(state, tempx, tempy))
				{
					tempx--;
					tempy--;
				}
				else if(isLongRangeDiagonalAttacker(state[tempx][tempy]))
					return true;
				else
				{
					break;
				}
			}
			tempx=x+1;tempy=y+1;
			while(tempx<8&&tempy<8)
			{
				if(locationIsEmpty(state, tempx, tempy))
				{
					tempx++;
					tempy++;
				}
				else if(isLongRangeDiagonalAttacker(state[tempx][tempy]))
					return true;
				else
				{
					break;
				}
			}
			return false;
		}
		
		private boolean isAttackedByKnight(Cell[][] state){
			//Checking for attack from the Knight of opposite color
			int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
			int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
			for(int i=0;i<8;i++)
				if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
					if(state[posx[i]][posy[i]].getpiece()!=null && state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor() && (state[posx[i]][posy[i]].getpiece() instanceof Knight))
					{
						return true;
					}
			return false;
		}
		
		private boolean isKingAttacker(Cell state[][], int x, int y)
		{
			return isValidPosition(x, y) && state[x][y].getpiece()!=null && state[x][y].getpiece().getcolor()!=this.getcolor() && (state[x][y].getpiece().getRootPiece() instanceof KingPiece);
		}
		
		public int[][] getSurroundingPoints(){
			int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
			int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
			int[][] allPoints = new int[8][2];
			
			for(int i = 0; i < 8; i++){
				allPoints[i][0] = pox[i];
				allPoints[i][1] = poy[i];
			}
			return allPoints;
			
		}
		private boolean isAttackedByKing(Cell state[][])
		{
			int surroundingPoints[][] = getSurroundingPoints();
			
			for(int i = 0; i < surroundingPoints.length;i++){
				if(isKingAttacker(state, surroundingPoints[i][0], surroundingPoints[i][1]))
					return true;
			}
			
			return false;
		}

		private boolean isValidPosition(int x, int y)
		{
			return x>=0&&x<8&&y>=0&&y<8;
		}
		private boolean isPawnAttacker(Cell cell){
			return cell.getpiece()!=null&&cell.getpiece().getcolor()!=this.getcolor()&&(cell.getpiece() instanceof Pawn);
		}
		private boolean isPawnAttacker(Cell state[][], int x, int y){
			return isValidPosition(x, y) && isPawnAttacker(state[x][y]);
		}
		private boolean isAttackedByPawn(Cell state[][])
		{
			int attackerRow = getcolor()==0?x-1:x+1;
			
			return isPawnAttacker(state, attackerRow, y-1) || isPawnAttacker(state, attackerRow, y+1);
		}
		
		
		//Function to check if king is under threat
		//It checks whether there is any piece of opposite color that can attack king for a given board state
		public boolean isindanger(Cell state[][])
	    {
			
			if(isAttackedFromStraightLine(state))
				return true;
			if(isAttackedFromDiagonal(state))
				return true;
			
			if(isAttackedByKnight(state))
				return true;
			if(isAttackedByPawn(state))
				return true;
			
			if(isAttackedByKing(state))
				return true;
			
			return false;
	    }
	@Override
	public int getPieceType() {
		return Piece.KING_TYPE;
	}
}
