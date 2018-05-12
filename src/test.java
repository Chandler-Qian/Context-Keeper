import javax.swing.filechooser.FileSystemView;
import java.io.File; 

public class test {
  public static void main(String args[]){
	  FileSystemView fsv = FileSystemView.getFileSystemView();
      File com=fsv.getHomeDirectory();
      System.out.println(com.getPath());
  }
}
