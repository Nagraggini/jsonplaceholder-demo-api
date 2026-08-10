package albumsPOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Only considers the fields that are defined
//as member variables of the class.
@JsonIgnoreProperties(ignoreUnknown=true)
public class Album {
	
	// Variables
	private int userId;
	private int id;
	private String title;
	
	// Contructors
	public Album(int userId, int id, String title) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
	}
	
	// Getter and setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
