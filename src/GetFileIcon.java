import java.awt.FlowLayout;  
import java.io.File;  
import java.io.FileNotFoundException;  
  
import javax.swing.Icon;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.filechooser.FileSystemView;


  
  
public class GetFileIcon {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String filePath = "C:/Users/Administrator/Desktop/2017申请/CS 4.0.xlsx";  
        File f = new File(filePath);  
        JFrame frm = new JFrame();  
        frm.setSize(300, 200);  
        frm.setLocationRelativeTo(null);  
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frm.setVisible(true);  
        frm.setLayout(new FlowLayout(10,10,FlowLayout.LEADING));  
          
        JLabel sl = new JLabel("小图标");  
        frm.add(sl);  
        JLabel bl = new JLabel("大图标");  
        frm.add(bl);  
          
        sl.setIcon(getSmallIcon(f));  
 
    }  
  
    /** 
     * 获取小图标 
     * @param f 
     * @return 
     */  
    public static Icon getSmallIcon(File f) {  
        if (f != null && f.exists()) {  
            FileSystemView fsv = FileSystemView.getFileSystemView();  
            return fsv.getSystemIcon(f);  
        }  
        return null;  
    }  
    public static Icon getBigIcon(File f) {  
        if (f!=null && f.exists()) {  
            try {  
                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(f);  
                return new ImageIcon(sf.getIcon(true));  
            } catch (FileNotFoundException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    /** 
     * 获取大图标 
     * @param f 
     * @return 
     */  

}