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
		//初始化
		currentItem = "";
		resultlist = new ArrayList<>();
		volume = 0;
		getVolumeTime = 0;
		//定义支持的文件名后缀
		String suffix[] = {"doc","docx","jpg","rar","zip","html","htm","wps","pdf","bmp","gif","pic","png","tif","wav","mp3","mp4",
				 "wma","avi","mov","exe","com","iso","ppt","xls","xml","pptx","txt","xlsx"};
		List<String> suffixList = Arrays.asList(suffix);
		 
		//记录开始时间
		long startVolumeTime = System.nanoTime();
			    	
	    LinkedList list = new LinkedList();//搜索要用的linkedlist
	    File dir = new File(location);  
	    File[] file = dir.listFiles();//存放的是一级目录下的文件以及文件夹  
	    System.out.println("_______Destination_file_length"+file.length);
	    for (int i = 0; i < file.length; i++) {
	    	if (file[i].isDirectory()) {  
	    		currentItem = file[i].getAbsolutePath();
	            System.out.println("----------------------------------------------------"+currentItem);
	        	list.add(file[i]);//如果是文件夹就加到list中  
	    	}
	    	else{
	    		volume = volume + file[i].length();
	        	currentItem = file[i].getAbsolutePath();
	        	System.out.println("----------------------------------------------------"+currentItem);
	        	if (suffixList.contains(file[i].toString().substring(file[i].toString().lastIndexOf(".")+1)))
	        	{
	        		System.out.println(file[i].getAbsolutePath());//如果是文件就输出绝对路径  
	        		if (file[i].getName().indexOf(keyword) != -1){
	        			resultlist.add(file[i]);
	        			}
	        		}
	        	}
	    	
	       }
	       File tmp; 
	       while (!list.isEmpty()) {//遍历list中的文件夹  
	        	tmp = (File) list.removeFirst();//移除并返回此列表的第一个元素  
	        	if (tmp.isDirectory()) {  
	        		currentItem = tmp.getAbsolutePath();
	        		System.out.println("----------------------------------------------------"+currentItem);
	        		file = tmp.listFiles();//存放的是二级目录下的文件以及文件夹  
	        		if (file == null)
	        			continue;//如果文件夹为空就跳出，进入下一个文件夹的遍历  
	        		for (int i = 0; i < file.length; i++) {//遍历二级目录下的文件夹  
	        			if (file[i].isDirectory()) { 
	        				currentItem = file[i].getAbsolutePath();
	        				System.out.println("----------------------------------------------------"+currentItem);
	        				list.add(file[i]);//如果是文件夹就加入到list中，会在下次循环中继续调用文件夹下的文件或者文件夹  
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
