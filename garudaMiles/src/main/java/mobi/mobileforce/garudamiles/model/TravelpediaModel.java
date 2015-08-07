package mobi.mobileforce.garudamiles.model;

public class TravelpediaModel {

	String id;
	int default_drawable;
	String type;
	String username;
	String status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDefault_drawable() {
		return default_drawable;
	}

	public void setDefault_drawable(int default_drawable) {
		this.default_drawable = default_drawable;
	}

}
