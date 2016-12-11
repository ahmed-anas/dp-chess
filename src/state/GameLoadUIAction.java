package state;

import java.awt.event.ActionListener;
import java.io.IOException;

public class GameLoadUIAction extends GameUIAction implements ActionListener{
	

	public GameLoadUIAction(StateLogger gameStateLogger){
		this.gameStateLogger = gameStateLogger;
		actionType = "Load";
	 }
	

	
	private class GameLoadDo extends GameUIAction.GameDoer{
		
		private GameLoadDo(int slot){
			super(slot, "Load");
		}
	
		@Override
		void onAction() throws IOException, ClassNotFoundException, InterruptedException {
			gameStateLogger.load(slot);
		}
		
		@Override
		protected String onErrorMessage(Exception e) {
			return "Game does not exist";
		}
		
	}

	@Override
	protected ActionListener getActionListener(int index) {
		// TODO Auto-generated method stub
		return new GameLoadDo(index);
	}
}
