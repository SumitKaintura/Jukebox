
public class PodcastData {

	String podcastId;
	String Celebrity;
	String Genere;
	String ShowName;
	String Duration;
	
	
	public PodcastData(String podcastId, String celebrity, String genere, String showName, String duration) {
		super();
		this.podcastId = podcastId;
		Celebrity = celebrity;
		Genere = genere;
		ShowName = showName;
		Duration = duration;
	}


	public String getPodcastId() {
		return podcastId;
	}


	public void setPodcastId(String podcastId) {
		this.podcastId = podcastId;
	}


	public String getCelebrity() {
		return Celebrity;
	}


	public void setCelebrity(String celebrity) {
		Celebrity = celebrity;
	}


	public String getGenere() {
		return Genere;
	}


	public void setGenere(String genere) {
		Genere = genere;
	}


	public String getShowName() {
		return ShowName;
	}


	public void setShowName(String showName) {
		ShowName = showName;
	}


	public String getDuration() {
		return Duration;
	}


	public void setDuration(String duration) {
		Duration = duration;
	}

	
	
	
}
