package hello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Album properties holder
 * Core domain object. The one and only!
 * 
 * @author Jakub_Wierzchoslawsk
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    private String id;

    private String title;
    private String artist;
    private String releaseYear;
    private String genre;
    private int trackCount;
    private String albumId;
    
    public Album(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
    
    
    
}
