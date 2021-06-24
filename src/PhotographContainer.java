import java.util.ArrayList;

/*
 *  Zach Lieberman	Homework 3
 *  zl5nrs
 *  Sources: My head
 */

public abstract class PhotographContainer {
	protected String name; //name of the Container
	protected ArrayList<Photograph> photos; //ArrayList of photos in the Album
	
	//basic constructor with name and photos
	public PhotographContainer(String n)
	{
		name = n;
		photos= new ArrayList<Photograph>();
	}
	
	/*
	 * @return the name of the container
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * @param cap A String that becomes the new name of the Container
	 */
	public void setName(String nam)
	{
		this.name = nam;
	}
	
	/*
	 * @return ArrayList of photos
	 */
	public ArrayList<Photograph> getPhotos() {
		return photos;
	}

	/*
	 * @param photograph
	 * 
	 * @return boolean true if added or else if not checks first to see that photo
	 * is not already in container
	 */
	public boolean addPhoto(Photograph p) {
		//if feed contains the photo, don't allow a duplicate
		if(this.hasPhoto(p)||p==null)
		{
			return false;
		}
		else //otherwise add the photo to the feed and say that it was true
		{
			this.photos.add(p);
			return true;
		}
		
	}
	/*
	 * @param Photograph The photograph that will be checked
	 * @return boolean If the photograph is in the feed, it will return true
	 */
	public boolean hasPhoto(Photograph p) {
		
		return photos.contains(p);
	}
	/*
	 * @param Photograph The photograph that will be checked for deletion
	 * @return boolean If the photograph is in the feed, it will erase
	 * it and return true
	 */
	public boolean removePhoto(Photograph p) {
		return photos.remove(p);
	}
	/*
	 * @return size of feed ArrayList, number of photos in photolibrary
	 */
	public int numPhotographs() {
		return this.getPhotos().size();
	}

	/*
	 * @param o An container that is compared to another container
	 * @return A boolean for if the two names are equivalent to each other
	 */	
	public boolean equals(Object o) {
		if (o instanceof PhotographContainer) {
			PhotographContainer other = (PhotographContainer) o;

			if (this.getName() == (other.getName())) {
				return true;
			}

		}

		return false;

	}

	public String toString() {
		return "Name: "+ this.getName()+"\nPhotograhs: "+this.getPhotos();
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	/*
	 * @return an ArrayList of photos from the photos feed that have a rating
	 * greater than or equal to the given parameter. If the rating is incorrectly
	 * formatted, return null. If there are no photos of that rating or higher,
	 * return an empty ArrayList.
	 */
	public ArrayList<Photograph> getPhotos(int rating) {
		ArrayList<Photograph> pics = new ArrayList<Photograph>();

		for (Photograph i : this.getPhotos()) {
			if (i.getRating() >= rating) {
				pics.add(i);
			}
		}

		return pics;
	}

	/*
	 * Return an ArrayList of photos from the photos feed that were taken in the
	 * year provided. For example, getPhotosInYear(2018) would return a list of
	 * photos that were taken in 2018. If the year is incorrectly formatted, return
	 * null. If there are no photos taken that year, return an empty ArrayList.
	 */
	public ArrayList<Photograph> getPhotosInYear(int year) {
		String ye = "" + year;
		ArrayList<Photograph> pics = new ArrayList<Photograph>();
		if (year > 0 && ye.length() == 4) {
			for (Photograph i : this.getPhotos()) {
				int y = Integer.parseInt(i.getDateTaken().substring(0, 4));
				if (y == year) {
					pics.add(i);
				}
			}
		}
		else
		{
			pics = null;
		}

		return pics;
	}

	/*
	 * Return an ArrayList of photos from the photos feed that were taken in the
	 * month and year provided. For example, getPhotosInMonth(7, 2018) would return
	 * a list of photos that were taken in July 2018. If the month or year are
	 * incorrectly formatted, return null. If there are no photos taken that month,
	 * return an empty ArrayList.
	 */
	public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
		ArrayList<Photograph> pics = new ArrayList<Photograph>();
		String ye = "" + year;
		String mo = "" + month;

		if (year > 0 && ye.length() == 4 && month > 0 && month <= 12 && (mo.length()==1||mo.length()==2)) {
			for (Photograph i : this.getPhotos()) {
				int y = Integer.parseInt(i.getDateTaken().substring(0, 4));
				int m = Integer.parseInt(i.getDateTaken().substring(5, 7));
				if (y == year && m == month) {
					pics.add(i);
				}
			}
		}

		return pics;
	}

	/*
	 * @return boolean if the first date is before or the same as the second date
	 */
	public boolean isBefore(int by, int bm, int bd, int ey, int em, int ed) {
		if (by < ey) {
			return true;
		} else if (by == ey && bm < em) {
			return true;
		} else if (by == ey && bm == em && (bd <= ed)) {
			return true;
		}

		return false;
	}
	
	/*
	 * @return boolean if the date entered is in the correct format
	 */
	public static boolean correctDateFormat(String date) {
		if (date.length() == 10) {
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			String day = date.substring(8, 10);
			String dash = "-";

			int begYear = Integer.parseInt(date.substring(0, 4));
			int begMonth = Integer.parseInt(date.substring(5, 7));
			int begDay = Integer.parseInt(date.substring(8, 10));

			if (year.length() == 4 && month.length() == 2 & day.length() == 2) {
				if (begYear > 0 && begMonth > 0 && begMonth <= 12 && begDay > 0 && begDay <= 31) {
					if (date.substring(4, 5).equals(dash) && date.substring(7, 8).equals(dash)) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/*
	 * Return an ArrayList of photos from the photos feed that were taken between
	 * beginDate and endDate (inclusive). For example,
	 * getPhotosBetween("2019-01-23", "2019-02-13") would return a list of photos
	 * that were taken in between January 23 and February 13 of 2019. If the begin
	 * and end dates are incorrectly formatted, or beginDate is after endDate,
	 * return null. If there are no photos taken during the period, return an empty
	 * ArrayList.
	 */
	public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
		ArrayList<Photograph> pics = new ArrayList<Photograph>();
		
		if(correctDateFormat(beginDate)==false || correctDateFormat(endDate)==false)
		{
			pics = null;
		}

		if (correctDateFormat(beginDate)==true && correctDateFormat(endDate)==true) {

			if (beginDate.length() == endDate.length() && endDate.length() == 10) {// if they are the correct size of
																					// the
																					// date

				// begin Date information
				int begYear = Integer.parseInt(beginDate.substring(0, 4));
				int begMonth = Integer.parseInt(beginDate.substring(5, 7));
				int begDay = Integer.parseInt(beginDate.substring(8, 10));

				// end Date information
				int endYear = Integer.parseInt(endDate.substring(0, 4));
				int endMonth = Integer.parseInt(endDate.substring(5, 7));
				int endDay = Integer.parseInt(endDate.substring(8, 10));

				if (isBefore(begYear, begMonth, begDay, endYear, endMonth, endDay)) {
					for (Photograph i : this.getPhotos()) {
						int y = Integer.parseInt(i.getDateTaken().substring(0, 4));
						int m = Integer.parseInt(i.getDateTaken().substring(5, 7));
						int d = Integer.parseInt(i.getDateTaken().substring(8, 10));
						if (isBefore(begYear, begMonth, begDay, y, m, d)
								&& isBefore(y, m, d, endYear, endMonth, endDay)) {
							pics.add(i);
						}
					}
				}
			}
		}

		return pics;
	}

	
	
	

}
