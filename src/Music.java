//Author: Mr. Burroughs
//Date:05/12/2020
//Rev:01
//Notes: This class contains the code to make music play in our game 
	import java.io.File;
	import java.io.IOException;
	import javax.sound.sampled.AudioFormat;
	import javax.sound.sampled.AudioInputStream;
	import javax.sound.sampled.AudioSystem;
	import javax.sound.sampled.DataLine;
	import javax.sound.sampled.LineUnavailableException;
	import javax.sound.sampled.SourceDataLine;

	public class Music
	{
	  private SourceDataLine line = null;
	  private byte[] audioBytes;
	  private int numBytes;

	  public Music(String fileName)
	  {
	    File  soundFile = new File(fileName);
	    AudioInputStream audioInputStream = null;
	    try
	    {
	      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
	    }
	    catch (Exception ex)
	    {
	      System.out.println("*** Cannot find " + fileName + " ***");
	      System.exit(1);
	    }

	    AudioFormat audioFormat = audioInputStream.getFormat();
	    DataLine.Info info = new DataLine.Info(SourceDataLine.class,
	                         audioFormat);
	    try
	    {
	      line = (SourceDataLine)AudioSystem.getLine(info);
	      line.open(audioFormat);
	    }
	    catch (LineUnavailableException ex)
	    {
	      System.out.println("*** Audio line unavailable ***");
	      System.exit(1);
	    }

	    line.start();

	    audioBytes = new byte[(int)soundFile.length()];

	    
	    try
	    {
	      numBytes = audioInputStream.read(audioBytes, 0, audioBytes.length);
	    }
	    catch (IOException ex)
	    {
	      System.out.println("*** Cannot read " + fileName + " ***");
	      System.exit(1);
	    }
	  }

	  public void play()
	  {
	    line.write(audioBytes, 0, numBytes);
	  }
	}

