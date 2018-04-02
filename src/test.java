import java.awt.Desktop;
import java.io.File;  
import java.io.IOException;  
import java.util.LinkedList;

import javax.swing.JFrame;  
  
  
public class test {  
 public static void main(String[] args) throws IOException {     

  
     long a = System.currentTimeMillis();//记录开始时间  
       
     LinkedList list = new LinkedList();  
     File dir = new File("C:/Users/Administrator/Desktop/");  
     File[] file = dir.listFiles();//存放的是一级目录下的文件以及文件夹  
       
     for (int i = 0; i < file.length; i++) {  
         if (file[i].isDirectory())  
             list.add(file[i]);//如果是文件夹就加到list中  
         else  
             System.out.println(file[i].getAbsolutePath());//如果是文件就输出绝对路径  
     }  
     File tmp;  
     while (!list.isEmpty()) {//遍历list中的文件夹  
         tmp = (File) list.removeFirst();//移除并返回此列表的第一个元素  
         if (tmp.isDirectory()) {  
             file = tmp.listFiles();//存放的是二级目录下的文件以及文件夹  
             if (file == null)  
                 continue;//如果文件夹为空就跳出，进入下一个文件夹的遍历  
             for (int i = 0; i < file.length; i++) {//遍历二级目录下的文件夹  
                 if (file[i].isDirectory())  
                     list.add(file[i]);//如果是文件夹就加入到list中，会在下次循环中继续调用文件夹下的文件或者文件夹  
                 else  
                     System.out.println(file[i].getAbsolutePath());  
             }  
         } else {  
             System.out.println(tmp.getAbsolutePath());  
         }  
     }  
     useAWTDesktop();
     System.out.println(System.currentTimeMillis() - a);//打印遍历的时间  
}  
 private static void useAWTDesktop() throws IOException{

//	    Desktop.getDesktop().open(new File("C:/Users/Administrator/Desktop/Personal Statement Brown.docx"));

	    }
} 