import java.awt.Desktop;
import java.io.File;  
import java.io.IOException;  
import java.util.LinkedList;

import javax.swing.JFrame;  
  
  
public class test {  
 public static void main(String[] args) throws IOException {     

  
     long a = System.currentTimeMillis();//��¼��ʼʱ��  
       
     LinkedList list = new LinkedList();  
     File dir = new File("C:/Users/Administrator/Desktop/");  
     File[] file = dir.listFiles();//��ŵ���һ��Ŀ¼�µ��ļ��Լ��ļ���  
       
     for (int i = 0; i < file.length; i++) {  
         if (file[i].isDirectory())  
             list.add(file[i]);//������ļ��оͼӵ�list��  
         else  
             System.out.println(file[i].getAbsolutePath());//������ļ����������·��  
     }  
     File tmp;  
     while (!list.isEmpty()) {//����list�е��ļ���  
         tmp = (File) list.removeFirst();//�Ƴ������ش��б�ĵ�һ��Ԫ��  
         if (tmp.isDirectory()) {  
             file = tmp.listFiles();//��ŵ��Ƕ���Ŀ¼�µ��ļ��Լ��ļ���  
             if (file == null)  
                 continue;//����ļ���Ϊ�վ�������������һ���ļ��еı���  
             for (int i = 0; i < file.length; i++) {//��������Ŀ¼�µ��ļ���  
                 if (file[i].isDirectory())  
                     list.add(file[i]);//������ļ��оͼ��뵽list�У������´�ѭ���м��������ļ����µ��ļ������ļ���  
                 else  
                     System.out.println(file[i].getAbsolutePath());  
             }  
         } else {  
             System.out.println(tmp.getAbsolutePath());  
         }  
     }  
     useAWTDesktop();
     System.out.println(System.currentTimeMillis() - a);//��ӡ������ʱ��  
}  
 private static void useAWTDesktop() throws IOException{

//	    Desktop.getDesktop().open(new File("C:/Users/Administrator/Desktop/Personal Statement Brown.docx"));

	    }
} 