import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {

	
	public int compare(Photograph a, Photograph b) {
		if(a.getCaption().compareTo(b.getCaption())<0)//less than
		{
			return -1;
		}
		else if(a.getCaption().compareTo(b.getCaption())>0)
		{
			return 1;
		}
		else {
			if(b.getCaption().compareTo(a.getCaption())<0) 
			{
				return -1;
			}
			else if(b.getCaption().compareTo(a.getCaption())>0)
			{
				return 1;
			}
			else
			{
				if(a.getRating()-b.getRating()<0)//less than
				{
					return 1;
				}
				else if(a.getRating()-b.getRating()>0)
				{
					return -1;
				}
				else {
					return 0;
				}
			}
		}
		
		
	}

}
