import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ComparatorDate {
	
	public void ListSort(List<File> list) {
		         Collections.sort(list, new Comparator<File>() {
		             @Override
		             public int compare(File o1, File o2) {
		                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		                 try {
		                     Date dt1 = formatter.parse(formatter.format(o1.lastModified()));
		                     Date dt2 = formatter.parse(formatter.format(o2.lastModified()));
		                     if (dt1.getTime() < dt2.getTime()) {
		                         return 1;
		                     } else if (dt1.getTime() > dt2.getTime()) {
		                         return -1;
		                     } else {
		                         return 0;
		                     }
		                 } catch (Exception e) {
		                     e.printStackTrace();
		                 }
		                 return 0;
		             }
		         });
		     }
}
