
import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Podcast {
    static Scanner sc = new Scanner(System.in);
	static long cliptime=0L;
	public static void podcastplay(String podcast)
	{
		
		
		try
		{
		System.out.println("playing the podcast");
		//String url = "E:\\niit\\course\\week7\\songswav\\Dua Lipa - Don't Start Now.wav";
		String path = "E:\\niit\\course\\week7\\podcastwav\\";
		String ext=".wav";
		String url=path+podcast+ext;
		File f = new File(url);
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(inputStream);
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
		Song.play(inputStream,clip);
		 while (true)
         {
             System.out.println("1. pause");
             System.out.println("2. resume");
             System.out.println("3. restart");
             System.out.println("4. stop");
             int c = sc.nextInt();
             Song.gotoChoice(c,inputStream,clip);
             if (c == 4)
             break;
         }
		
		
	   }
		catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
          }
	}
		
		 public static void gotoChoice(int c,AudioInputStream inputStream,Clip clip)throws Exception
		    {
		        switch (c) 
		        {
		            case 1:
		                pause(inputStream,clip);
		                break;
		            case 2:
		                resumeAudio(inputStream,clip);
		                break;
		            case 3:
		                restart(inputStream,clip);
		                break;
		            case 4:
		                stop(inputStream,clip);
		                break;
		      
		        }
		      
		    }
		 
		 public static void play(AudioInputStream inputStream,Clip clip) 
		    {
		        //start the clip
		        clip.start();
		       // while(true) {}
		         
		    }
		 
		 public static void pause(AudioInputStream inputStream,Clip clip) 
		    {
		        
		        cliptime = clip.getMicrosecondPosition();
		        clip.stop();
		    }
		 
		 public static void resumeAudio(AudioInputStream inputStream,Clip clip) 
         {
             //clip.close();
             //resetAudioStream();
             clip.setMicrosecondPosition(cliptime);
             clip.start();
         }
		 public static void restart(AudioInputStream inputStream,Clip clip)
		 {
			//reset();
			clip.setMicrosecondPosition(0);
			clip.start(); 
		 }
		 public static void stop(AudioInputStream inputStream,Clip clip)
		 {
			clip.stop();
			
		 }
		 
		 
}
