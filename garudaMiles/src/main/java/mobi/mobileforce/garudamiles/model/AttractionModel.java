package mobi.mobileforce.garudamiles.model;

public class AttractionModel {
	String Title;
	String Category;
	String reviews;
	int rating;
	int resource;
	int id;
	int photos;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhotos() {
		return photos;
	}

	public void setPhotos(int photos) {
		this.photos = photos;
	}
}
