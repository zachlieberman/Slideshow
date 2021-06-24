import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/*
 *  Zach Lieberman	Homework 3
 *  zl5nrs
 *  Sources: My head
 */

public class PhotographContainerTest {
	
	
	//tests to see if the photo is removed from the feed
	@Test
	public void erasePhotoTest() {
		PhotoLibrary zach = new PhotoLibrary("Zach", 1234567809);

		// Photograph(String cap, String file, String date: "YYYY-MM-DD", int r)

		Photograph a = new Photograph("a", "a.jpg", "2001-05-06", 2);
		Photograph b = new Photograph("b", "b.jpg", "2001-05-09", 2);
		Photograph c = new Photograph("c", "c.jpg", "2001-06-08", 2);
		Photograph d = new Photograph("d", "d.jpg", "2001-05-04", 2);
		Photograph e = new Photograph("e", "e.jpg", "2002-05-06", 2);
		// inputs
		zach.addPhoto(a);
		zach.addPhoto(b);
		zach.addPhoto(c);
		zach.addPhoto(d);
		zach.addPhoto(e);

		a.setRating(2);
		c.setRating(3);
		
		assertTrue("The photo was not removed", zach.removePhoto(e));
		assertFalse("The photo was not removed from the feed", zach.getPhotos().contains(e));
		
	}
	//Tests to see if the photo is removed from the feed and the album
	@Test
	public void erasePhotoTest2() {
		PhotoLibrary zach = new PhotoLibrary("Zach", 1234567809);

		// Photograph(String cap, String file, String date: "YYYY-MM-DD", int r)

		Photograph a = new Photograph("a", "a.jpg", "2001-05-06", 2);
		Photograph b = new Photograph("b", "b.jpg", "2001-05-09", 2);
		Photograph c = new Photograph("c", "c.jpg", "2001-06-08", 2);
		Photograph d = new Photograph("d", "d.jpg", "2001-05-04", 2);
		Photograph e = new Photograph("e", "e.jpg", "2002-05-06", 2);
		// inputs
		zach.addPhoto(a);
		zach.addPhoto(b);
		zach.addPhoto(c);
		zach.addPhoto(d);
		zach.addPhoto(e);

		a.setRating(2);
		c.setRating(3);

		assertTrue("An album was not created.",zach.createAlbum("lit"));
		assertTrue("An album was not created.",zach.createAlbum("not lit"));

		assertTrue("Photograph was not added to album.",zach.addPhotoToAlbum(a, "lit"));
		assertTrue("Photograph was not added to album.",zach.addPhotoToAlbum(b, "not lit"));
		assertTrue("Photograph was not added to album.",zach.addPhotoToAlbum(c, "lit"));
		assertTrue("Photograph was not added to album.",zach.addPhotoToAlbum(d, "not lit"));
		
		assertTrue("The photo was not removed.", zach.removePhoto(b));
		assertFalse("RemovePhoto failed (something wasn't removed).", zach.getPhotos().contains(b));
		
		// expected
		ArrayList<Photograph> pics1 = zach.getPhotos();
		//System.out.println(pics1.contains(b));
		zach.removePhoto(b);
		Boolean expected = false;
		// expected.add(c);

		// actual
		ArrayList<Photograph> pics = zach.getPhotos();

		Boolean actual = true;

		if (pics.contains(b) == false) {
			for (Album i : zach.getAlbums()) {
				if (i.hasPhoto(b)) {
					actual = true;

				}

			}
			actual = false;
		}

		assertEquals("The method erasePhoto does not work with pictures in albums", actual, expected);
	}
	
	//Tests CompareTo Method for all different dates
	@Test
	public void testCompareToDifferent() {
		
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		list.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		list.add(new Photograph("d", "d.jpg", "2001-05-04", 2));
		list.add(new Photograph("e", "e.jpg", "2002-05-06", 2));
		Collections.sort(list);
		
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();
		expected.add(new Photograph("d", "d.jpg", "2001-05-04", 2));
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		expected.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		expected.add(new Photograph("e", "e.jpg", "2002-05-06", 2));
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareTo method does not put in the right date order"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
	}
	
	@Test
	public void testCompareToSame() {
		
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		list.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		list.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		list.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		Collections.sort(list);
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();
		
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		expected.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		expected.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		expected.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareByCaption method does not put in the right caption order"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
		
	}
	
	@Test
	public void testCompareByCaptionSame() {
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		list.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		list.add(new Photograph("a", "c.jpg", "2001-06-08", 5));
		list.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		Collections.sort(list, new CompareByCaption());
		System.out.println(list);
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();
		expected.add(new Photograph("a", "c.jpg", "2001-06-08", 5));
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		expected.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		expected.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareByCaption method does not put in the right caption order or in ascending rating if same"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
		
		
	}
	
	@Test
	public void testCompareByCaptionDifferent() {
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		list.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		list.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		list.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		Collections.sort(list, new CompareByCaption());
		System.out.println(list);
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();		
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 2));
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 2));
		expected.add(new Photograph("c", "c.jpg", "2001-06-08", 2));
		expected.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		expected.add(new Photograph("e", "e.jpg", "2002-05-09", 2));
		
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareTo method does not put in the right date order"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
		
		
	}
	
	@Test
	public void testCompareByRatingSame() {
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 4));
		list.add(new Photograph("d", "d.jpg", "2001-05-09", 1));
		list.add(new Photograph("c", "c.jpg", "2001-06-08", 1));
		list.add(new Photograph("e", "e.jpg", "2002-05-09", 3));
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 5));
		Collections.sort(list, new CompareByRating());
		System.out.println(list);
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();		
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 5));
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 4));
		expected.add(new Photograph("e", "e.jpg", "2002-05-09", 3));
		expected.add(new Photograph("c", "c.jpg", "2001-06-08", 1));
		expected.add(new Photograph("d", "d.jpg", "2001-05-09", 1));
		
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareByRating method does not put in the right rating order and if same goes by caption"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
		
		
	}
	
	@Test
	public void testCompareByRatingDifferent() {
		ArrayList<Photograph> list = new ArrayList<Photograph>();
		list.add(new Photograph("b", "b.jpg", "2001-05-09", 4));
		list.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		list.add(new Photograph("c", "c.jpg", "2001-06-08", 1));
		list.add(new Photograph("e", "e.jpg", "2002-05-09", 3));
		list.add(new Photograph("a", "a.jpg", "2004-05-06", 5));
		Collections.sort(list, new CompareByRating());
		System.out.println(list);
		
		ArrayList<Photograph> expected = new ArrayList<Photograph>();		
		expected.add(new Photograph("a", "a.jpg", "2004-05-06", 5));
		expected.add(new Photograph("b", "b.jpg", "2001-05-09", 4));
		expected.add(new Photograph("e", "e.jpg", "2002-05-09", 3));
		expected.add(new Photograph("d", "d.jpg", "2001-05-09", 2));
		expected.add(new Photograph("c", "c.jpg", "2001-06-08", 1));
		
		
		for(int i=0;i<expected.size();i++)
		{
			assertEquals("CompareByRating method does not put in the right rating order"
					,expected.get(i).getDateTaken(),list.get(i).getDateTaken());
		}
		
		
	}
	
	

}
