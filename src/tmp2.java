import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class tmp2 {
	static String currentItem = "";
	static ArrayList<File> resultlist = new ArrayList<>();
	static long volume = 0;
	static long getVolumeTime = 0;
	
	public static void main(String args[]){
	   SearchFile("C://Users//administrator//Desktop", "doc");
       
   }
   public static void SearchFile (String location, String keyword){
		//��ʼ��
		currentItem = "";
		resultlist = new ArrayList<>();
		volume = 0;
		getVolumeTime = 0;
		//����֧�ֵ��ļ�����׺
		String suffix[] = {"doc","docx","jpg","rar","zip","html","htm","wps","pdf","bmp","gif","pic","png","tif","wav","mp3","mp4",
				 "wma","avi","mov","exe","com","iso","ppt","xls","xml","pptx","txt","xlsx"};
		List<String> suffixList = Arrays.asList(suffix);
		 
		//��¼��ʼʱ��
		long startVolumeTime = System.nanoTime();
			    	
	    LinkedList list = new LinkedList();//����Ҫ�õ�linkedlist
	    File dir = new File(location);  
	    File[] file = dir.listFiles();//��ŵ���һ��Ŀ¼�µ��ļ��Լ��ļ���  
	    System.out.println("_______Destination_file_length"+file.length);
	    for (int i = 0; i < file.length; i++) {
	    	if (file[i].isDirectory()) {  
	    		currentItem = file[i].getAbsolutePath();
	            System.out.println("----------------------------------------------------"+currentItem);
	        	list.add(file[i]);//������ļ��оͼӵ�list��  
	    	}
	    	else{
	    		volume = volume + file[i].length();
	        	currentItem = file[i].getAbsolutePath();
	        	System.out.println("----------------------------------------------------"+currentItem);
	        	if (suffixList.contains(file[i].toString().substring(file[i].toString().lastIndexOf(".")+1)))
	        	{
	        		System.out.println(file[i].getAbsolutePath());//������ļ����������·��  
	        		if (file[i].getName().indexOf(keyword) != -1){
	        			resultlist.add(file[i]);
	        			}
	        		}
	        	}
	    	
	       }
	       File tmp; 
	       while (!list.isEmpty()) {//����list�е��ļ���  
	        	tmp = (File) list.removeFirst();//�Ƴ������ش��б�ĵ�һ��Ԫ��  
	        	if (tmp.isDirectory()) {  
	        		currentItem = tmp.getAbsolutePath();
	        		System.out.println("----------------------------------------------------"+currentItem);
	        		file = tmp.listFiles();//��ŵ��Ƕ���Ŀ¼�µ��ļ��Լ��ļ���  
	        		if (file == null)
	        			continue;//����ļ���Ϊ�վ�������������һ���ļ��еı���  
	        		for (int i = 0; i < file.length; i++) {//��������Ŀ¼�µ��ļ���  
	        			if (file[i].isDirectory()) { 
	        				currentItem = file[i].getAbsolutePath();
	        				System.out.println("----------------------------------------------------"+currentItem);
	        				list.add(file[i]);//������ļ��оͼ��뵽list�У������´�ѭ���м��������ļ����µ��ļ������ļ���  
	        			}else{
	        				currentItem = file[i].getAbsolutePath();
	        				volume = volume + file[i].length();
	        				System.out.println("----------------------------------------------------"+currentItem);
	        				if(suffixList.contains(file[i].toString().substring(file[i].toString().lastIndexOf(".")+1))){
		        				System.out.println(file[i].getAbsolutePath());  
		                        if(file[i].getName().indexOf(keyword) != -1){
		                        	resultlist.add(file[i]);
		                        }
		        			}
	        				
	        			}
	        		}
	        	} 
	        	else {
	        		System.out.println(tmp.getAbsolutePath()); 
	        		volume = volume + tmp.length();
	                  if(tmp.getName().indexOf(keyword) != -1){
	                	  resultlist.add(tmp);
	                  }
	        	}  

	        }
	       if(list.isEmpty()){
	    	   long endVolumeTime = System.nanoTime();
	    	   MainUI.isVolumeGot = true;
	    	   MainUI.searchVolume = volume/1024.0;
	    	   MainUI.searchVolumeTime = (endVolumeTime - startVolumeTime) / 1.0e9;
	    	   System.out.println("Done");
	    	   System.out.println(resultlist.size());
	    	   System.out.println(MainUI.searchVolume);
	    	   System.out.println(MainUI.searchVolumeTime);
	       }
   }
 
}
