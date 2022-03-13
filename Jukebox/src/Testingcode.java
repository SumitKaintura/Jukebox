import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Testingcode {

	@Test
    public void testSongListSize() throws Exception
    {
        assertEquals(true,jukeboxImpl.viewallSongs().size()==14);
 
    }

    @Test
    public void testPodcastListSize() throws Exception
    {
        assertEquals(true,jukeboxImpl.viewallpodcast().size()==4);
    }
    
    @Test
    public void testPodcastListSizeEmpty() throws Exception
    {
        assertEquals(true,jukeboxImpl.viewallpodcast().size()>0);
    }
    
	
}
