# slideshow
A Java slideshow application that sorts Photographs by Date, Rating, and Caption.
 ********READ_ME************

From_Instagram_To_Flickr

Album: Child of PhotographContainer class. Collection of Photographs.

CompareByCaption: Comparator class that compares Photographs first by caption,
then by rating.

CompareByRating: Comparator class that compares Photographs first by rating,
then by caption.

Photograph: An object with a caption, filename, date, rating, and Image.

PhotographContainer: Abstract class of container of Photographs. Parent to 
Album and PhotoLibrary. Contains list of Photographs and a name with various
methods to gather information on Photographs.

PhotographContainerTest: JUnit Testing for the project checking for correct
functionality.

PhotoLibrary: Child of PhotographContainer class. Has a specific identification
number and a set of Albums. Also contains methods to check similarity between
Photographs.

PhotoViewer: Contains the GUI for the project. RUN THE DRIVER HERE. Contains
a section on the left of thumbnails of each Photograph in the PhotographContainer.
On the top, there are buttons to change the large image on the right as well
as radio buttons to change the order of the thumbnails by Date, Rating, and
Caption. On the bottom, there are radio buttons to change the rating of the 
large photo that will be reflected immediately on the left thumbnails.

The images are in the images folder.

Go nats! 2019 World Champions!
