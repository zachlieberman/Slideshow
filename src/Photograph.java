import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/*
 * Homework 3
 * Zach Lieberman, zl5nrs
 * 
 * Sources: My head.
 */

public class Photograph implements Comparable{

	/*
	 * Caption for the picture
	 */
	private String caption;

	/*
	 * Filename for the picture
	 */
	private String filename;

	/*
	 * A String containing the date the photograph was taken. Dates are given in the
	 * format “YYYY-MM-DD” such as “2019-02-13” (February 13, 2019)
	 */
	private String dateTaken;

	/*
	 * the rating of the photograph on a scale from 0 to 5. No other values are
	 * allowed.
	 */
	private int rating;
	
	/*
	 * @return image data for the object
	 */
	protected BufferedImage imageData;

	/*
	 * Basic constructor for the photograph
	 */
	public Photograph(String cap, String file) {
		caption = cap;
		filename = file;
		dateTaken = "1901-01-01";
		rating = 0;

	}

	/*
	 * Additional constructor with more user input
	 */
	public Photograph(String cap, String file, String date, int r) {
		caption = cap;
		filename = file;
		this.setDateTaken(date);
		this.setRating(r);
	}

	/*
	 * @return the caption for the photograph
	 */
	public String getCaption() {
		return caption;
	}

	/*
	 * @param cap A String that becomes the new caption of the Photograph
	 */
	public void setCaption(String cap) {
		this.caption = cap;
	}

	/*
	 * @return the filename for the photograph
	 */
	public String getFilename() {
		return filename;
	}

	/*
	 * @param file A String that becomes the new filename of the Photograph
	 */
	public void setFilename(String file) {
		this.filename = file;
	}

	/*
	 * @return the date taken of the photograph
	 */
	public String getDateTaken() {
		return dateTaken;
	}
	
	/*
	 * @param String date being checked
	 * @return if the date is properly formatted
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
	 * @param cap A String that becomes the new date taken for the Photograph
	 */
	public void setDateTaken(String date) {
		// “YYYY-MM-DD”
		this.dateTaken ="1901-01-01";
		if (correctDateFormat(date)) {
			this.dateTaken = date;
			
		} 
		
	}

	/*
	 * @return the rating for the photograph
	 */
	public int getRating() {
		return rating;
	}

	/*
	 * @param cap A String that becomes the new rating of the Photograph
	 */
	public void setRating(int r) {
		if (r >= 0 && r <= 5) {
			this.rating = r;
		} else {
			this.rating = 0;
		}
	}

	/*
	 * @param o A photograph that is compared to another photograph
	 * 
	 * @return A boolean for if the two photos are equivalent to each other
	 */
	public boolean equals(Object o) {
		if (o instanceof Photograph) {
			Photograph other = (Photograph) o;
			if (this.getCaption().equals(other.getCaption())) {
				if (this.getFilename().equals(other.getFilename())) {
					return true;
				}
			}
		} else {
			return false;
		}

		return false;

	}
	
	public BufferedImage getImageData()
	{
		return imageData;
	}
	
	public void setImageData(BufferedImage image) {
		this.imageData = image;
	}
	
	public boolean loadImageData(String filename) {
		try {
			this.filename = filename;
			BufferedImage img = ImageIO.read(new File(filename));
			this.imageData = img;
			return true;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
			return false;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public String toString() {
		return this.getCaption() + "\n" + "Filename: " + this.getFilename();
	}

	public int hashCode() {
		return filename.hashCode();
	}

	
	public int compareTo(Object o) {
		Photograph n = (Photograph)o;
		
		if(this.getDateTaken().compareTo(n.getDateTaken())<0)//less than
		{
			return -1;
		}
		else if(this.getDateTaken().compareTo(n.getDateTaken())>0)
		{
			return 1;
		}
		else {
			if(n.getDateTaken().compareTo(this.getDateTaken())<0) 
			{
				return -1;
			}
			else if(n.getDateTaken().compareTo(this.getDateTaken())>0)
			{
				return 1;
			}
			else
			{
				if(this.getCaption().compareTo(n.getCaption())<0)//less than
				{
					return -1;
				}
				else if(this.getCaption().compareTo(n.getCaption())>0)
				{
					return 1;
				}
				else {
					if(n.getCaption().compareTo(this.getCaption())<0) 
					{
						return -1;
					}
					else if(n.getCaption().compareTo(this.getCaption())>0)
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
			}
		}
	}

}
