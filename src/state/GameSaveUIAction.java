package state;

import java.awt.event.ActionListener;
import java.io.IOException;


public class GameSaveUIAction extends GameUIAction implements ActionListener{
	
	private StateLogger gameStateLogger;
	public GameSaveUIAction(StateLogger gameStateLogger){
		 this.gameStateLogger = gameStateLogger;
		 actionType = "Save";
	 }
	


	@Override
	protected ActionListener getActionListener(int index) {
		// TODO Auto-generated method stub
		return new GameSaveDo(index);
	}
	
	private class GameSaveDo extends GameUIAction.GameDoer{
		
		private GameSaveDo(int slot){
			super(slot, "Save");
		}
	
		@Override
		void onAction() throws IOException {
			gameStateLogger.save(slot);
		}
		
	}


}
