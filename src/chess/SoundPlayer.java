package chess;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer implements Runnable {

	public SoundPlayer(String soundName)
	{
		this.soundName=soundName;
	}
	private String soundName;
	@Override
	public void run() {
		 try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(soundName));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		      }
		}
	
	public static void playBeep2(){
		Thread soundThread=new Thread(new SoundPlayer("B:/OneDrive/lums/design patterns/project/eclipse_workspace/Chess/beep.wav"));
		soundThread.start();
	}
	public static synchronized void playBeep() {
		final String url = "B:/OneDrive/lums/design patterns/project/eclipse_workspace/Chess/beep.wav";
		
		final File f = new File(url);
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        //AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(url));
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(f.toURI().toURL());
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}

}
