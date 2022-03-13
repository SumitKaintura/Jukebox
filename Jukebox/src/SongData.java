

public class SongData {

	String songId;
	String artist;
	String genere;
	String album;
	String songname;
	String time;
	
	
	
	@Override
	public String toString() {
		return songId +"  "+"  "+ artist +"  "+genere+"  "+ album+"  "+songname+"  "+time;
	}

	public SongData(String songId, String artist, String genere, String album, String songname, String time) {
		super();
		this.songId = songId;
		this.artist = artist;
		this.genere = genere;
		this.album = album;
		this.songname = songname;
		this.time = time;
	}
	
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
