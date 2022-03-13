
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.lang.Exception;

public class jukeboxImpl 
{
	static int userId=0;
    static Scanner sc = new Scanner(System.in);
	public static void main(String [] args)throws Exception
	{
		//Song song = new Song();
		Menu m = new Menu();
		int value=m.menu();
		User user = new User();
		Scanner sc = new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Alphamale@123");
			//jukeboxImpl.deleteData(conn);
			switch (value)
			{
			case 1:
			  
			  user.initialize();
			  int roweff=jukeboxImpl.insertuser(conn ,user);
			  if(roweff>0)
			  {
					System.out.println("Account successfully created");
					
					
			  }
				   else
				   {
					System.out.println("Error Occured");
				   }
				   // break;
				    
			case 2:	 
				int id=jukeboxImpl.validateUser(conn,sc);
				if(id>0)
				{
					System.out.println("Successfully logged in");
					menuchoices(conn);
					
				}
				else
				{
					throw new customException();
					//System.out.println("Wrong Id or password");
				}
				
					
					
				}
		   }
		
		
        catch(FileNotFoundException fe) {}
		
		catch(SQLException se) {}
		
		catch(ClassNotFoundException ce) {}
		
		catch(NullPointerException ne) {}
		
		catch(customException ce)
		{
		    System.out.println("Wrong ID or Password Exception");
		}
		
		
	}
	
	   public static int insertuser(Connection conn ,User user)throws Exception
	   {
		   
		   Statement stmt = conn.createStatement();
		   String query = "select *from user getLastRecord ORDER BY userId DESC LIMIT 1;";
		   ResultSet rs = stmt.executeQuery(query); 
		  // rs.last();
		   int id=0;
		   while(rs.next()){
			   id=rs.getInt(1); 
		   }
		   int id1=id+1;
		  
		    String insert = "insert into user (userId,username,password) values(?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(insert);
			psmt.setInt(1, id1);
			psmt.setString(2, user.getUserName());
			psmt.setString(3,user.getPassword());
			
			int row_eff=psmt.executeUpdate();
			psmt.close();
			
			return row_eff;
	   }
	   
	   
	   public static void deleteData(Connection conn)throws Exception
		{
		   Statement stmt = conn.createStatement();
		   String query = "select *from user getLastRecord ORDER BY userId DESC LIMIT 1;";
		   ResultSet rs = stmt.executeQuery(query); 
		  // rs.last();
		   int id=0;
		   while(rs.next()){
			   id=rs.getInt(1); 
		   }
		  
		   System.out.println("the id is "+id);
		   
		   
			//PreparedStatement psmt=conn.prepareStatement("delete from user where userId=?");		
			//psmt.setLong(1, 1);
			//psmt.executeUpdate();
			
		}
	   public static int validateUser(Connection conn , Scanner sc)throws Exception
	   {
		   //Scanner sc = new Scanner(System.in);
		   System.out.println("Enter the name");
		   String name = sc.next();
		   System.out.println("Enter user password");
		   String password=sc.next();
		   PreparedStatement psmt=conn.prepareStatement("SELECT * FROM user WHERE username=? And password=?");	
			psmt.setString(1, name);
			psmt.setString(2, password);
			ResultSet rs=psmt.executeQuery();
			int id=0;
			while(rs.next())
			{
				//count=rs.getInt("count");
				id=rs.getInt(1);
				userId=id;
			}
			
		   
			return id;
	   }
	   
	   public static void search(Connection conn)throws Exception
	   {
		  List<SongData> songList=new ArrayList<SongData>();
			
			
			
			Statement statementObj=conn.createStatement();
			ResultSet rs=statementObj.executeQuery("Select * from songs1");
			
			while(rs.next()) 
			{
				
				songList.add(new SongData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			}
			
			System.out.println("Enter 1 to search by name   2.To search by Artist  3.To search by Genere");
			
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
			{
				System.out.println("Enter the Name of Song to search");
				sc.nextLine();
				String Name=sc.nextLine();
				
			  Predicate<SongData> sng=n->n.getSongname().equalsIgnoreCase(Name);
			  for(SongData l:songList) 
			  {
				if (sng.test(l))
				{
					System.out.println(l);
			    }	
		      }
			   break;
			}
			
			case 2:
			{
				System.out.println("Enter the Artist Name to search");
				sc.nextLine();
				String Name1=sc.nextLine();
				Predicate<SongData> song=n->n.getArtist().equalsIgnoreCase(Name1);
				for(SongData l:songList) 
				{
					if (song.test(l))
					{
						System.out.println(l);
				    }	
			     }
				break;
			}
				
			case 3:
			{
				System.out.println("Enter the Genere to search");
				sc.nextLine();
				String Name2=sc.nextLine();
				Predicate<SongData> song1=n->n.getGenere().equalsIgnoreCase(Name2);
				for(SongData l:songList) 
				{
					if (song1.test(l))
					{
						System.out.println(l);
				    }	
			     }
				break;
			}
			
			}
			
			System.out.println("Enter the SongId to play music");
			String songid=sc.next();
			jukeboxImpl.songtoplay(conn,songid);
			
			
			
			/*for(SongData l:songList) 
			{
				if (sng.test(l))
				{
					String songname=l.getSongname();
					Song.songplay(songname);
					
			    }	
		     }*/
			
	     
	   }
	   public static void songtoplay(Connection conn,String songid)throws Exception
	   {
		   String song="";
	       PreparedStatement psmt=conn.prepareStatement("select * from songs1 where songId=?");	
		   psmt.setString(1, songid);
		   ResultSet rs2=psmt.executeQuery();
		   while(rs2.next())
			{
				song=rs2.getString(5);
			}
			Song.songplay(song);
			menuchoices(conn);
	   }
	   
	   public static void podcasttoplay(Connection conn,String podcastid)throws Exception
	   {
		   String podcast="";
	       PreparedStatement psmt=conn.prepareStatement("select * from podcast1 where podcastId=?");	
		   psmt.setString(1, podcastid);
		   ResultSet rs2=psmt.executeQuery();
		   while(rs2.next())
			{
				podcast=rs2.getString(4);
			}
			Podcast.podcastplay(podcast);
			menuchoices(conn); 
	   }
	   
	   public static int addtouserplaylist(Connection conn)throws Exception
	   {
		    System.out.println("Enter the Name to create playlist");
		    String playlistName=sc.nextLine();
		    PreparedStatement psmt=conn.prepareStatement("insert into userPlaylist values(?,?,?)");
		    int theRandomNum =  (int)((Math.random()*100)+10);
		    String Id="PL";   String add=String.valueOf(theRandomNum);  String playlistId=Id+add;
			psmt.setString(1, playlistId);
			psmt.setString(2, playlistName);
			psmt.setInt(3,userId);
			int row_eff=psmt.executeUpdate();
			psmt.close();
			
			return row_eff;
	   }
	   
	   public static int addsongstoplaylist(Connection conn)throws Exception
	   {
		   viewuserPlaylists(conn);
		   System.out.println("Enter the playlistId to add Songs to it");
		   String pid=sc.next();
	       viewallSongs();
	       System.out.println("Enter the songId to add Songs to Playlist");
	       String sid=sc.next();
	       
	       PreparedStatement psmt=conn.prepareStatement("insert into playlist(playlistId,SongId)values(?,?)");		
		   psmt.setString(1, pid);
		   psmt.setString(2, sid);
		   int row_eff=psmt.executeUpdate();
		   //psmt.setString(3, "null");
		  // ResultSet rs1 = psmt.executeQuery();
	       return row_eff;
         }
	   
	   public static int addpodcasttoplaylist(Connection conn)throws Exception
	   {
		   viewuserPlaylists(conn);
		   System.out.println("Enter the playlistId to add Podcast to it");
		   String pid=sc.next();
		   viewallpodcast();
	       System.out.println("Enter the podcastId to add Podcast to Playlist");
	       String sid=sc.next();
	       
	       //PreparedStatement psmt=conn.prepareStatement("update playlist set podcastId=? where playlistId=?");
	       PreparedStatement psmt=conn.prepareStatement("insert into playlist(playlistId,podcastId)values(?,?)");
		   psmt.setString(1, pid);
		   psmt.setString(2, sid);
		   int row_eff=psmt.executeUpdate();
		   //psmt.setString(3, "null");
		  // ResultSet rs1 = psmt.executeQuery();
	       return row_eff;
         }
	   
	   public static List<SongData> viewallSongs()throws Exception
	   {
		   List<SongData> songList=new ArrayList<SongData>();
		   try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Alphamale@123");
				
				   Statement statementObj=conn.createStatement();
				   ResultSet rs=statementObj.executeQuery("select * from songs1");
					
				   
				   while(rs.next()) 
					{
						
						songList.add(new SongData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
					}
				   
				   System.out.format("%-32s %-32s %-32s %-32s %-32s %-32s","SongId", "Artist", "Genre","Album","Song Name","Duration"); 
		           System.out.println("_______________________________________________________________________________________________________________________________________________________________________________");
				   for(SongData l:songList) 
					{
						
							//System.out.println(l);
						System.out.format("%-32s %-32s %-32s %-32s %-32s %-32s",l.getSongId(),l.getArtist(),l.getGenere(),l.getAlbum(),l.getSongname(),l.getTime());
						System.out.println("");
							
				     }
				   
			}
			
			catch(SQLException se) {}
			
			catch(ClassNotFoundException ce) {}
			
			catch(NullPointerException ne) {}
		   
			/*while(rs.next())
			{
				System.out.format("%-32s %-32s %-32s %-32s %-32s %-32s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				//System.out.print(rs.getString(1)+"\t");
				//System.out.print(rs.getString(2)+"\t");
				//System.out.print(rs.getString(3)+"\t");
				//System.out.print(rs.getString(4)+"\t");
				//System.out.print(rs.getString(5)+"\t");
				//System.out.print(rs.getString(6)+"\t");
				System.out.println("");
			}*/
		   
		   return songList;
	   }
	   
	   public static List<PodcastData> viewallpodcast()throws Exception
	   {	
			
			List<PodcastData> podcastList=new ArrayList<PodcastData>();
			   try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Alphamale@123");
					
					   Statement statementObj=conn.createStatement();
					   ResultSet rs=statementObj.executeQuery("select * from podcast1");
						
					   
					   while(rs.next()) 
						{
							
							podcastList.add(new PodcastData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
						}
					   
			      System.out.format("%-32s %-32s %-32s %-50s %-32s","PodcastId", "Celebrity", "Genre","Show Name","Duration"); 
			      System.out.println("___________________________________________________________________________________________________________________________________________________________________________");
			      for(PodcastData l:podcastList) 
				   {
			        System.out.format("%-32s %-32s %-32s %-50s %-32s",l.getPodcastId(),l.getCelebrity(),l.getGenere(),l.getShowName(),l.getDuration());
				    System.out.println("");
								
				   }
					   
				 }
				
				catch(SQLException se) {}
				
				catch(ClassNotFoundException ce) {}
				
				catch(NullPointerException ne) {}
			   
			   
			   return podcastList;
	   }
	   
	   public static void viewuserPlaylists(Connection conn)throws Exception
	   {
		   
		   PreparedStatement psmt=conn.prepareStatement("select * from userPlaylist where userId=?");		
		   psmt.setInt(1, userId);
		   ResultSet rs = psmt.executeQuery();
		   
		   while(rs.next())
			{
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println();
		   //return row_eff;
	         }
	   }
	   
	   public static void menuchoices(Connection conn)throws Exception
	   {
		   Menu m=new Menu();
		   int val;
		   val=m.afterlogging();
		   switch(val)
			{
			case 1:
				
				viewallSongs();
				System.out.println("Enter the SongId to play music");
				String songid=sc.next();
				jukeboxImpl.songtoplay(conn,songid);
				menuchoices(conn);
				break;
				
				
			case 2:
				
				viewallpodcast();
				String podcast="";
				System.out.println("Enter the PodcastId to play podcast");
				String podcastid=sc.next();
				PreparedStatement psmt2=conn.prepareStatement("select * from podcast1 where podcastId=?");		
				psmt2.setString(1, podcastid);
				ResultSet rs3=psmt2.executeQuery();
				while(rs3.next())
				{
					podcast=rs3.getString(4);
				}
				Podcast.podcastplay(podcast);
				menuchoices(conn);
				break;
				
			case 3://search
				jukeboxImpl.search(conn);
				break;
				
			case 4:
				int roweff1=jukeboxImpl.addtouserplaylist(conn);
				if(roweff1>0)
				{
					System.out.println("Playlist Created");
				    menuchoices(conn);
				}
				   else
					System.out.println("Error Occured");
				    break;
				
			case 5:
				int eff=addsongstoplaylist(conn);
				if(eff>0)
				{
					System.out.println("Song Added to Playlist");
				    menuchoices(conn);
				}
				   else
					System.out.println("Error Occured");
				break;
			case 6:
				viewuserPlaylists(conn);
				System.out.println("Enter the the PlaylistId to play songs");
				String playlistid=sc.next();
				playsongPlaylist(conn,playlistid);
				break;
				
			case 7:
				int eff2=addpodcasttoplaylist(conn);
				if(eff2>0)
				{
					System.out.println("Podcast Added to Playlist");
				    menuchoices(conn);
				}
				   else
					System.out.println("Error Occured");
				break;
				
			case 8:
				viewuserPlaylists(conn);
				System.out.println("Enter the the PlaylistId to play podcast");
				String playlistid1=sc.next();
				playpodcastPlaylist(conn,playlistid1);
				break;
				
				
			case 9:
				System.out.println("Stopping the Application...........");
				System.out.println("Stopped");
				break;
	   }
    }
	   
	   public static void playsongPlaylist(Connection conn,String playlistid)throws Exception
	   {
		   PreparedStatement psmt=conn.prepareStatement("select playlist.SongId from playlist join userPlaylist on userPlaylist.PlaylistId=playlist.playlistId where playlist.playlistId=? and userPlaylist.userId=?");		
		   psmt.setString(1, playlistid);
		   psmt.setInt(2, userId);
		   ResultSet rs = psmt.executeQuery();
		   
		   while(rs.next())
			{
			   String songid=rs.getString(1);
			   songtoplay(conn,songid);
				//System.out.print(rs.getString(1)+"\t");
	        }
	   
      }
	   
	   public static void playpodcastPlaylist(Connection conn,String playlistid)throws Exception
	   {
		   PreparedStatement psmt=conn.prepareStatement("select playlist.podcastId from playlist join userPlaylist on userPlaylist.PlaylistId=playlist.playlistId where playlist.playlistId=? and userPlaylist.userId=? and playlist.podcastId IS NOT NULL");		
		   psmt.setString(1, playlistid);
		   psmt.setInt(2, userId);
		   ResultSet rs = psmt.executeQuery();
		   
		   while(rs.next())
			{
			   String podcastid=rs.getString(1);
			  // System.out.print(podcastid);
			   podcasttoplay(conn,podcastid);
				
	        }
	   
      }
}

