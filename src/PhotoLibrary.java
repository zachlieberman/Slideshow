import java.util.*;

/*
 * Homework 1
 * Zach Lieberman, zl5nrs
 * 
 * Sources: My head.
 */
public class PhotoLibrary extends PhotographContainer{

	
	/*
	 * int - an integer containing the PhotoLibrary's identification number
	 */
	private final int id;

	
	/*
	 * A HashSet of Albums that this user has created. Each album will then contain
	 * photos from this user's photos stream that they have organized into albums.
	 */
	private HashSet<Album> albums = new HashSet<Album>();

	// single constructor(name, id)
	public PhotoLibrary(String name, int i) {
		super(name);
		id = i;

	}

	
	/*
	 * @return id of feed
	 */
	public int getId() {
		return id;
	}

	

	/*
	 * @return albums
	 */
	public HashSet<Album> getAlbums() {
		return albums;
	}

	
	/*
	 * Creates a new Album with name albumName and adds it to the list of albums,
	 * only if an Album with that name does not already exist. Returns true if the
	 * add was successful, false otherwise.
	 */
	public boolean createAlbum(String albumName) {
		Album a = new Album(albumName);

		if (!this.getAlbums().contains(a)) {
			albums.add(a);
			return true;
		}

		return false;
	}

	/*
	 * Removes the Album with name albumName if an Album with that name exists in
	 * the set of albums. Returns true if the remove was successful, false
	 * otherwise.
	 */
	public boolean removeAlbum(String albumName) {
		Album a = new Album(albumName);

		if (this.getAlbums().contains(a)) {
			albums.remove(a);
			return true;
		}

		return false;
	}

	/*
	 * Add the Photograph p to the Album in the set of albums that has name
	 * albumName if and only if it is in the PhotoLibrary's list of photos and it
	 * was not already in that album. Return true if the Photograph was added;
	 * return false if it was not added.
	 */
	public boolean addPhotoToAlbum(Photograph p, String albumName) {

		if (photos.contains(p) && this.getAlbumByName(albumName) != null) {
			getAlbumByName(albumName).addPhoto(p);
			return true;
		}

		return false;
	}

	/*
	 * Remove the Photograph p from the Album in the set of albums that has name
	 * albumName. Return true if the photo was successfully removed. Otherwise
	 * return false.
	 */
	public boolean removePhotoFromAlbum(Photograph p, String albumName) {

		if (photos.contains(p) && this.getAlbumByName(albumName) != null) {
			if (this.getAlbumByName(albumName).hasPhoto(p)) {
				getAlbumByName(albumName).removePhoto(p);
				return true;
			}
		}
		return false;
	}

	/*
	 * This is a private helper method. Given an album name, return the Album with
	 * that name from the set of albums. If an album with that name is not found,
	 * return null.
	 */
	private Album getAlbumByName(String albumName) {

		Album temp = new Album(albumName);

		for (Album i : this.getAlbums()) {
			if (temp.equals(i)) {
				return i;
			}
		}

		return null;
	}

	


	/*
	 * Modify your erasePhoto from homework 2 to remove the Photograph p from the
	 * PhotoLibrary list of photos as well as remove the Photograph from any Albums
	 * in the list of albums. Return true if the photograph was successfully
	 * removed, false otherwise.
	 */
	public boolean removePhoto(Photograph p) {
		// removes from photolist of photos
		boolean result = photos.remove(p);
		if (result) {
			for (Album i : this.getAlbums()) {
				if (i.hasPhoto(p)) {
					i.removePhoto(p);
				}
			}
		}

		// remove from all albums
		

		return result;
	}

	/*
	 * 
	 * @param o A PhotoLibrary that is compared to another PhotoLibrary 
	 * @return A boolean for if the two Id's are equivalent to each other
	 * 
	 */

	public boolean equals(Object o) {

		if (o instanceof PhotoLibrary) {

			PhotoLibrary other = (PhotoLibrary) o;

			if (this.getId() == (other.getId())) {

				return true;

			}

		}

		return false;

	}


	public String toString() {
		return "Name: " + this.getName() + "\nId: " + this.getId() + "\nAlbums: " + this.getAlbums() + "\nFeed: "
				+ this.getPhotos();
	}

	/**
	 * 
	 * @param a First PhotoLibrary being compared
	 * @param b Second PhotoLibrary being compared
	 * @return ArrayList<Photograph> of common photos between the two PhotoLibraries
	 */
	public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
		ArrayList<Photograph> common = new ArrayList<Photograph>(); // common photos

		for (int i = 0; i < a.numPhotographs(); i++) // a is i
		{
			for (int j = 0; j < b.numPhotographs(); j++) // b is j
			{
				if (a.getPhotos().get(i).equals(b.getPhotos().get(j)))// if they both have a photo
				{
					common.add(b.getPhotos().get(j)); // add to common photos
				}
			}
		}

		return common;
	}

	/**
	 * 
	 * @param a First PhotoLibrary being compared
	 * @param b Second PhotoLibrary being compared
	 * @return percentage of similarity between the two PhotoLibraries
	 */
	public static double similarity(PhotoLibrary a, PhotoLibrary b) {
		double percent; // percent similar
		boolean aSmaller;// true if a has less photos than b
		ArrayList<Photograph> common = commonPhotos(a, b); // list of common photos

		if (common.size() == 0) {
			return 0;
		}

		// find smaller feed
		if (a.numPhotographs() <= b.numPhotographs()) {
			aSmaller = true;
		} else {
			aSmaller = false;
		}

		// divide common pictures by smallest feed to get percentage
		if (aSmaller) {
			percent = ((double) (common.size())) / ((double) a.numPhotographs());

		} else {
			percent = (double) (common.size() / (double) b.numPhotographs());
			// System.out.println(b.numPhotographs());
			// System.out.println(common.size());

		}

		return percent;
	}

}
