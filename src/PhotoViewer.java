import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class PhotoViewer extends JFrame implements MouseListener{
	
	private PhotographContainer imageLibrary; //collection
	
	/*
	 * Variable Setup
	 */
	JPanel view, currImage, thumbnail, controls, radio, rating; //different panels
	
	JButton exit, previous, next; //buttons
	
	JRadioButton byDate, byRating, byCaption; //sort options at top
	
	JRadioButton[] ratings; //rating buttons at bottom
	
	JLabel imageLabel, title; //big picture, title of collection
	
	JLabel[] tImage = new JLabel[5]; //thumbnail image
	
	JLabel[] tText = new JLabel[5]; //thumbnail text
	
	File imageFile; //image file
	
	File backgroundImageFile = new File("images\\w.jpg");
	
	Image backgroundImage;
	
	
	ImageIcon thisIcon; //big pic icon
	
	PhotoLibrary nats = new PhotoLibrary("Nationals Baseball", 1); //collection of photographs
	
	private int numImage = 0; //which image is showing
	private int typeSort = 0; //0-date 1-rating 2-caption
	
	//layouts
	private BorderLayout border = new BorderLayout();
	private FlowLayout flow = new FlowLayout();
	
	//sets up the GUI
	public static void showPhotoViewer() {
		PhotoViewer frame = new PhotoViewer();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		frame.setSize(width, (height-100));
		frame.setLocationRelativeTo(null);
		frame.setTitle("PhotoViewer");
		//frame.pack();
		frame.setVisible(true);
		frame.addComponentsToPane(frame.getContentPane());
		
		
	}
	
	public PhotographContainer getImageLibrary()
	{
		return imageLibrary;
	}
	
	public void setImageLibrary(PhotographContainer p)
	{
		this.imageLibrary = p;
	}
	
	public static void main(String[] args) {
		
		PhotoViewer myViewer = new PhotoViewer();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myViewer.showPhotoViewer();
			}
			
		});
		
	}
	
	private void addComponentsToPane(Container pane)
	{
		
		
		//Whole panel
		view = new JPanel();
		view.setLayout(border);
		
		
		/*currImage
		 * 
		 */
		
		Photograph scherzer = new Photograph("Maximus Scherzer", 
				"scherzer.jpg", "2019-10-15", 4);
		Photograph dubs = new Photograph("World Series Bound!", 
				"dubs.jpg", "2019-10-15", 5);
		Photograph game = new Photograph("Didn't win but had a great time.", 
				"game.jpg", "2019-10-06", 3);
		Photograph kendrick = new Photograph("Killer Kendrick", 
				"kendrick.jpg", "2019-10-09", 5);
		Photograph soto = new Photograph("SOTO...FOR THE LEADDDDD", 
				"soto.jpg", "2019-10-01", 5);
		
		
		nats.addPhoto(scherzer);
		nats.addPhoto(soto);
		nats.addPhoto(kendrick);
		nats.addPhoto(game);
		nats.addPhoto(dubs);
		
		
		currImage = new JPanel(); //panel for current image
		currImage.setLayout(flow);
		imageLabel = new JLabel();
		String fileName = "scherzer.jpg";
		imageFile = new File("images\\"+nats.getPhotos().get(numImage).getFilename());
		
		
		try {
			BufferedImage photo = ImageIO.read(imageFile);
			thisIcon = new ImageIcon(photo.getScaledInstance(700, 500, Image.SCALE_DEFAULT));
			imageLabel.setIcon(thisIcon);
		}catch(Exception e) {
			imageLabel.setText("Error loading default image or album is empty");
		}
		currImage.add(imageLabel);
		
		view.add(currImage, BorderLayout.EAST);
		
		/*
		 * Controls
		 */
		controls = new JPanel();
		controls.setLayout(flow);
		
		title = new JLabel();
		title.setText(nats.getName()+"    Photo "+(numImage+1)+" of 5");
		controls.add(title);
		
		//exit button
		exit = new JButton("Exit");
		exit.addActionListener(new exitListener());
		controls.add(exit);
		
		//previous button
		previous = new JButton("Previous");
		previous.addActionListener(new previousListener());
		controls.add(previous);
		
		//next button
		next = new JButton("Next");
		next.addActionListener(new nextListener());
		controls.add(next);
		
		//radio buttons: 
		//Source: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html#radiobutton
		byDate = new JRadioButton("Sort By Date");
		byDate.setActionCommand("Sort by Date");
		byDate.setSelected(true);
		
		byCaption = new JRadioButton("Sort By Caption");
		byCaption.setActionCommand("Sort by Caption");
		
		byRating = new JRadioButton("Sort By Rating");
		byRating.setActionCommand("Sort by Rating");
		
		ButtonGroup group = new ButtonGroup();
		group.add(byCaption);
		group.add(byDate);
		group.add(byRating);
		
		byDate.addActionListener(new dateSortListener());
		byRating.addActionListener(new ratingSortListener());
		byCaption.addActionListener(new captionSortListener());
		
		radio = new JPanel(new GridLayout(1,0));
		radio.add(byDate);
		radio.add(byRating);
		radio.add(byCaption);
		
		controls.add(radio);
		view.add(controls, BorderLayout.NORTH);
		
				
		
	/*
	 * thumbnail
	 */
		thumbnail = new JPanel();
		thumbnail.setLayout(new GridLayout(5,2));
		
		
		
		//imageFile = new File("images\\"+nats.getPhotos().get(numImage).getFilename());
		for (int i = 0; i < 5; i++) {
			tImage[i] = new JLabel();
			tText[i] = new JLabel();
			try {
				BufferedImage pic = ImageIO.read(new File("images\\" + nats.getPhotos().get(i).getFilename()));
				ImageIcon thisIcons = new ImageIcon(pic.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
				tImage[i].setIcon(thisIcons);
				thumbnail.add(tImage[i]);
			} catch (Exception e) {
				tImage[i].setText("Error loading default image or album is empty");
			}
			tText[i].setText("<html>"+nats.getPhotos().get(i).getCaption()+
					"<br/>Date: "+nats.getPhotos().get(i).getDateTaken()+
					"<br/>Rating: "+nats.getPhotos().get(i).getRating()+"</html>");
			thumbnail.addMouseListener(this);
			thumbnail.add(tText[i]);
			
		}
		
		
		view.add(thumbnail, BorderLayout.WEST);
		
		
		/*
		 * Rating
		 */
		JPanel rPanel = new JPanel();
		rPanel.setLayout(flow);
		
		JLabel rLabel = new JLabel();
		rLabel.setText("Rating");
		rPanel.add(rLabel);
		
		rating = new JPanel();
		rating.setLayout(new GridLayout(1,0));
		
		ButtonGroup groups = new ButtonGroup();
		ratings = new JRadioButton[5];
		for(int i=0;i<5;i++) {
			ratings[i] = new JRadioButton(""+(i+1));
			ratings[i].setActionCommand(""+(i+1));
			groups.add(ratings[i]);
			ratings[i].addActionListener(new ratingsListener());
			ratings[0].setSelected(true);
			rating.add(ratings[i]);
		}
		
		rPanel.add(rating);
		view.add(rPanel, BorderLayout.SOUTH);
		
		pane.add(view);
	}
	
	/*
	 * Listeners
	 */
	
	private class previousListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(numImage==0)
			{
				numImage=4;
				
			}
			else
			{
				numImage--;
				
			}
			imageFile = new File("images\\"+nats.getPhotos().get(numImage).getFilename());
			try {
				BufferedImage photo = ImageIO.read(imageFile);
				thisIcon = new ImageIcon(photo.getScaledInstance(700, 500, Image.SCALE_DEFAULT));
				imageLabel.setIcon(thisIcon);
			}catch(Exception e) {
				imageLabel.setText("Error loading default image or album is empty");
			}
			title.setText(nats.getName()+"    Photo "+(numImage+1)+" of 5");
			imageLabel.revalidate();
		}
	}
	
	public PhotoViewer() {
		try {
			backgroundImage = ImageIO.read(backgroundImageFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	//exit listener
	private class exitListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			/*System.out.println("NumImage: "+numImage);
			System.out.println(nats.getPhotos());*/
			System.exit(0);
		}
	}
	
	//next listener
	private class nextListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(numImage==4)
			{
				numImage=0;
			}
			else
			{
				numImage++;
			}
			imageFile = new File("images\\"+nats.getPhotos().get(numImage).getFilename());
			try {
				BufferedImage photo = ImageIO.read(imageFile);
				thisIcon = new ImageIcon(photo.getScaledInstance(700, 500, Image.SCALE_DEFAULT));
				imageLabel.setIcon(thisIcon);
			}catch(Exception e) {
				imageLabel.setText("Error loading default image or album is empty");
			}
			title.setText(nats.getName()+"    Photo "+(numImage+1)+" of 5");
			imageLabel.revalidate();
		}
	}

	
	// caption listener
	private class captionSortListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Collections.sort(nats.getPhotos(), new CompareByCaption());
			revalidate();
			for (int i = 0; i < 5; i++) {
				try {
					BufferedImage pic = ImageIO.read(new File("images\\" + nats.getPhotos().get(i).getFilename()));
					ImageIcon thisIcons = new ImageIcon(pic.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
					tImage[i].setIcon(thisIcons);
				} catch (Exception e) {
					tImage[i].setText("Error loading default image or album is empty");
				}
				tText[i].setText("<html>"+nats.getPhotos().get(i).getCaption()+
						"<br/>Date: "+nats.getPhotos().get(i).getDateTaken()+
						"<br/>Rating: "+nats.getPhotos().get(i).getRating()+"</html>");}
		}
	}

	// date listener
	private class dateSortListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Collections.sort(nats.getPhotos());
			for (int i = 0; i < 5; i++) {
				try {
					BufferedImage pic = ImageIO.read(new File("images\\" + nats.getPhotos().get(i).getFilename()));
					ImageIcon thisIcons = new ImageIcon(pic.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
					tImage[i].setIcon(thisIcons);
				} catch (Exception e) {
					tImage[i].setText("Error loading default image or album is empty");
				}
				tText[i].setText("<html>"+nats.getPhotos().get(i).getCaption()+
						"<br/>Date: "+nats.getPhotos().get(i).getDateTaken()+
						"<br/>Rating: "+nats.getPhotos().get(i).getRating()+"</html>");}
			
			
		}
	}

	// sort rating listener
	private class ratingSortListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Collections.sort(nats.getPhotos(), new CompareByRating());
			for (int i = 0; i < 5; i++) {
				try {
					BufferedImage pic = ImageIO.read(new File("images\\" + nats.getPhotos().get(i).getFilename()));
					ImageIcon thisIcons = new ImageIcon(pic.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
					tImage[i].setIcon(thisIcons);
				} catch (Exception e) {
					tImage[i].setText("Error loading default image or album is empty");
				}
				tText[i].setText("<html>"+nats.getPhotos().get(i).getCaption()+
						"<br/>Date: "+nats.getPhotos().get(i).getDateTaken()+
						"<br/>Rating: "+nats.getPhotos().get(i).getRating()+"</html>");}
			
		}
	}
	
	// rating button listener
		private class ratingsListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				int num = Integer.parseInt(arg0.getActionCommand());
				//Need to set photo rating to num
				nats.getPhotos().get(numImage).setRating(num);
				tText[numImage].setText("<html>"+nats.getPhotos().get(numImage).getCaption()+
						"<br/>Date: "+nats.getPhotos().get(numImage).getDateTaken()+
						"<br/>Rating: "+nats.getPhotos().get(numImage).getRating()+"</html>");
			}
		}
		//when clicks a thumbnail
		public void mouseClicked(java.awt.event.MouseEvent a) {
			int x = a.getX();
			int y = a.getY();
			
			
			for(int i=0;i<tImage.length;i++) {
				if((y)<(tImage[i].getY()+102)&&y>tImage[i].getY()) {//if it is in the thumbnail
					numImage = i;//make it the main image
				}
				
			}
			imageFile = new File("images\\"+nats.getPhotos().get(numImage).getFilename());
			try {
				BufferedImage photo = ImageIO.read(imageFile);
				thisIcon = new ImageIcon(photo.getScaledInstance(700, 500, Image.SCALE_DEFAULT));
				imageLabel.setIcon(thisIcon);
			}catch(Exception e) {
				imageLabel.setText("Error loading default image or album is empty");
			}
			title.setText(nats.getName()+"    Photo "+(numImage+1)+" of 5");
			imageLabel.revalidate();
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent arg0) {
			
			
		}
		
	
	
}
