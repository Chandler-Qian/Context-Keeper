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
		//�������ݽṹ,ʹ��Map�����嵥����Ϊ����ArrayList������
		//Map:Type,Time,FileName,Size.
		Username = ComputerStatusMap.get("USERNAME");	
		
		//����PF�ļ���

		PFSearchFile("C://Windows//Prefetch", "pf");
//		//��PF�ļ�����
//		for(File file:PFResultList){
//			String fileName = file.getName();
//			int index = fileName.indexOf(".exe");
//			String newName = fileName.substring(0,index);
//			file.renameTo(new File("C://Windows//Prefetch"+"//newName"+".exe"));
//		}
//		
		//����Recent�ļ���
		RecentSearchFile("C://Users//Recent", ".");
		
		//��������
		ResultList =(ArrayList<File>) PFResultList.clone();
		ResultList.addAll(recentResultList);
		MainUI.isResultListGot = true;
		
		//ʱ������
		ComparatorDate comparatorDate = new ComparatorDate();
		comparatorDate.ListSort(ResultList);
		
		//����
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
			//��ʼ��
			PFResultList = new ArrayList<File>();
				    	
		    LinkedList list = new LinkedList();//����Ҫ�õ�linkedlist
		    File dir = new File(location);  
		    File[] file = dir.listFiles();//��ŵ���һ��Ŀ¼�µ��ļ��Լ��ļ���  

		    for (int i = 0; i < file.length; i++) {
		    	if (file[i].isDirectory()) {
		    		PFResultList.add(file[i]);//������ļ��оͼӵ�list��  
		    	}
		    	else{
		        		if (file[i].getName().indexOf(keyword) != -1){
		        			PFResultList.add(file[i]);
		        			}
		        		}
		       }
		       File tmp; 
		       while (!list.isEmpty()) {//����list�е��ļ���  
		        	tmp = (File) list.removeFirst();//�Ƴ������ش��б�ĵ�һ��Ԫ��  
		        	if (tmp.isDirectory()) {  
		        		file = tmp.listFiles();//��ŵ��Ƕ���Ŀ¼�µ��ļ��Լ��ļ���  
		        		if (file == null)
		        			continue;//����ļ���Ϊ�վ�������������һ���ļ��еı���  
		        		for (int i = 0; i < file.length; i++) {//��������Ŀ¼�µ��ļ���  
		        			if (file[i].isDirectory()) {
		        				PFResultList.add(file[i]);//������ļ��оͼ��뵽list�У������´�ѭ���м��������ļ����µ��ļ������ļ���  
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
			//��ʼ��
		 recentResultList = new ArrayList<File>();
				    	
		    LinkedList list = new LinkedList();//����Ҫ�õ�linkedlist
		    File dir = new File(location);  
		    File[] file = dir.listFiles();//��ŵ���һ��Ŀ¼�µ��ļ��Լ��ļ���  

		    for (int i = 0; i < file.length; i++) {
		    	if (file[i].isDirectory()) {
		    		 recentResultList.add(file[i]);//������ļ��оͼӵ�list��  
		    	}
		    	else{
		        		if (file[i].getName().indexOf(keyword) != -1){
		        			recentResultList.add(file[i]);
		        			}
		        		}
		       }
		       File tmp; 
		       while (!list.isEmpty()) {//����list�е��ļ���  
		        	tmp = (File) list.removeFirst();//�Ƴ������ش��б�ĵ�һ��Ԫ��  
		        	if (tmp.isDirectory()) {  
		        		file = tmp.listFiles();//��ŵ��Ƕ���Ŀ¼�µ��ļ��Լ��ļ���  
		        		if (file == null)
		        			continue;//����ļ���Ϊ�վ�������������һ���ļ��еı���  
		        		for (int i = 0; i < file.length; i++) {//��������Ŀ¼�µ��ļ���  
		        			if (file[i].isDirectory()) {
		        				recentResultList.add(file[i]);//������ļ��оͼ��뵽list�У������´�ѭ���м��������ļ����µ��ļ������ļ���  
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
