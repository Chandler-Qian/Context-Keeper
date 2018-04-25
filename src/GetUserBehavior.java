import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collections;  



public class GetUserBehavior {
	
	public static ArrayList<File> PFResultList;
	public static ArrayList<File> recentResultList;
	public static ArrayList<File> ResultList;
	
	public static ArrayList<File> MusicResultList;
	public static ArrayList<File> WordResultList;
	public static ArrayList<File> PicResultList;
	public static ArrayList<File> ExeResultList;
	public static ArrayList<File> VideoResultList;
	public static ArrayList<File> OtherResultList;
	
	public static String Username;

	public static Map<String, String> ComputerStatusMap = System.getenv();

	public static void getUserBehavior(){
		//定义数据结构,使用Map来定义单个行为，用ArrayList来整合
		//Map:Type,Time,FileName,Size.
		Username = ComputerStatusMap.get("USERNAME");	
		
		//遍历PF文件夹

		PFSearchFile("C://Windows//Prefetch", "pf");
//		//将PF文件改名
//		for(File file:PFResultList){
//			String fileName = file.getName();
//			int index = fileName.indexOf(".exe");
//			String newName = fileName.substring(0,index);
//			file.renameTo(new File("C://Windows//Prefetch"+"//newName"+".exe"));
//		}
//		
		//遍历Recent文件夹
		RecentSearchFile("C://Users//Recent", ".");
		
		//整合数据
		ResultList =(ArrayList<File>) PFResultList.clone();
		ResultList.addAll(recentResultList);
		MainUI.isResultListGot = true;
		
		//时间排序
		ComparatorDate comparatorDate = new ComparatorDate();
		comparatorDate.ListSort(ResultList);
		
		//分类
		MusicResultList = new ArrayList<>();
		PicResultList = new ArrayList<>();
		VideoResultList = new ArrayList<>();
		ExeResultList = new ArrayList<>();
		WordResultList = new ArrayList<>();
		OtherResultList = new ArrayList<>();
		
		for(File file:ResultList){
			if (file.getName().contains(".EXE")) {
				ExeResultList.add(file);
			}
			else if (file.getName().contains(".doc")||file.getName().contains(".wps")||file.getName().contains(".txt")||file.getName().contains(".ppt")||file.getName().contains(".xls")||file.getName().contains(".htm")) {
				WordResultList.add(file);
			}
			else if (file.getName().contains(".mp4")||file.getName().contains(".avi")||file.getName().contains(".mpeg")||file.getName().contains(".mov")||file.getName().contains(".rmvb")) {
				VideoResultList.add(file);
			}
			else if (file.getName().contains(".mp3")||file.getName().contains(".wav")||file.getName().contains(".wma")) {
				MusicResultList.add(file);
			}
			else if (file.getName().contains(".jpg")||file.getName().contains(".png")||file.getName().contains(".bmp")||file.getName().contains(".gif")||file.getName().contains(".pic")||file.getName().contains(".jpeg")) {
				PicResultList.add(file);
			}else{
				OtherResultList.add(file);
			}
		}
		for(File file:ExeResultList){
			System.out.println(file.getName());
		}
	
	}
	public static void main(String args[]){
		
		getUserBehavior();
		
	}
	
	 public static void PFSearchFile (String location, String keyword){
			//初始化
			PFResultList = new ArrayList<File>();
				    	
		    LinkedList list = new LinkedList();//搜索要用的linkedlist
		    File dir = new File(location);  
		    File[] file = dir.listFiles();//存放的是一级目录下的文件以及文件夹  

		    for (int i = 0; i < file.length; i++) {
		    	if (file[i].isDirectory()) {
		    		PFResultList.add(file[i]);//如果是文件夹就加到list中  
		    	}
		    	else{
		        		if (file[i].getName().indexOf(keyword) != -1){
		        			PFResultList.add(file[i]);
		        			}
		        		}
		       }
		       File tmp; 
		       while (!list.isEmpty()) {//遍历list中的文件夹  
		        	tmp = (File) list.removeFirst();//移除并返回此列表的第一个元素  
		        	if (tmp.isDirectory()) {  
		        		file = tmp.listFiles();//存放的是二级目录下的文件以及文件夹  
		        		if (file == null)
		        			continue;//如果文件夹为空就跳出，进入下一个文件夹的遍历  
		        		for (int i = 0; i < file.length; i++) {//遍历二级目录下的文件夹  
		        			if (file[i].isDirectory()) {
		        				PFResultList.add(file[i]);//如果是文件夹就加入到list中，会在下次循环中继续调用文件夹下的文件或者文件夹  
		        			}else{
			                        if(file[i].getName().indexOf(keyword) != -1){
			                        	PFResultList.add(file[i]);
			                        }
			        			
		        				
		        			}
		        		}
		        	} 
		        	else {
		                  if(tmp.getName().indexOf(keyword) != -1){
		                	  PFResultList.add(tmp);
		                  }
		        	}  

		        }
		       
	   }
	 
	 public static void RecentSearchFile (String location, String keyword){
			//初始化
		 recentResultList = new ArrayList<File>();
				    	
		    LinkedList list = new LinkedList();//搜索要用的linkedlist
		    File dir = new File(location);  
		    File[] file = dir.listFiles();//存放的是一级目录下的文件以及文件夹  

		    for (int i = 0; i < file.length; i++) {
		    	if (file[i].isDirectory()) {
		    		 recentResultList.add(file[i]);//如果是文件夹就加到list中  
		    	}
		    	else{
		        		if (file[i].getName().indexOf(keyword) != -1){
		        			recentResultList.add(file[i]);
		        			}
		        		}
		       }
		       File tmp; 
		       while (!list.isEmpty()) {//遍历list中的文件夹  
		        	tmp = (File) list.removeFirst();//移除并返回此列表的第一个元素  
		        	if (tmp.isDirectory()) {  
		        		file = tmp.listFiles();//存放的是二级目录下的文件以及文件夹  
		        		if (file == null)
		        			continue;//如果文件夹为空就跳出，进入下一个文件夹的遍历  
		        		for (int i = 0; i < file.length; i++) {//遍历二级目录下的文件夹  
		        			if (file[i].isDirectory()) {
		        				recentResultList.add(file[i]);//如果是文件夹就加入到list中，会在下次循环中继续调用文件夹下的文件或者文件夹  
		        			}else{
			                        if(file[i].getName().indexOf(keyword) != -1){
			                        	recentResultList.add(file[i]);
			                        }
			        			
		        				
		        			}
		        		}
		        	} 
		        	else {
		                  if(tmp.getName().indexOf(keyword) != -1){
		                	  recentResultList.add(tmp);
		                  }
		        	}  

		        }
		       
	   }
	
}
