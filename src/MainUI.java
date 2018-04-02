import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class MainUI{
	
    //获得显示器的长宽
    static int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    //设定窗口长宽
    static int windowsWidth = 1200;
    static int windowsHeight = 800;
    
	static Image image = new ImageIcon("bin/content1_bg.png").getImage(); 
	static Image mainbackground = new ImageIcon("bin/mainbackground.png").getImage(); 
	static Image whitebackground = new ImageIcon("bin/whitebackground.png").getImage(); 
	static Image resultbackground = new ImageIcon("bin/resultBackground.png").getImage(); 
	static Image resulttitle = new ImageIcon("bin/line1.png").getImage(); 
	static ImageIcon icon = new ImageIcon("bin/tab.png");  
	static ImageIcon iconnull = new ImageIcon("bin/tabnull.png");  
	
	static JFrame frame = new JFrame();
	static JPanel contentpanel = new BackgroundPanel(image);
    static JPanel mainpanel = new BackgroundPanel(mainbackground);
    static JPanel panel1 = new TitlePanel();
    static JPanel panel2 = new JPanel();
    static JPanel tabblue = new JPanel();
    static JPanel tabpanel = new JPanel();
    static JPanel userpanel = new JPanel();
    static JPanel searchpanel = new BackgroundPanel(mainbackground);
    static JTextField searchbar=new JTextField("请输入查询关键字（文件名或拓展名）"); 
    static JButton Button_search = new JButton();
    static JButton Button_funtion1 = new JButton("您的主页",icon);
    static JButton Button_funtion2 = new JButton("行为查找",iconnull);
    static JButton Button_funtion3 = new JButton("精确查找",iconnull);
    static JButton Button_funtion4 = new JButton("设置",iconnull);
    static JLabel usericon = new JLabel();
    static JComboBox comboBox=new JComboBox();
    
    
    static Boolean isVolumeGot = false;
    static double searchVolume;
    static double searchVolumeTime;
    
    private static void initUI() {
		// TODO Auto-generated method stub
          
        frame.setLayout(null);  //自由布局  
        frame.setBounds((ScreenWidth - windowsWidth) / 2, (ScreenHeight - windowsHeight) / 2, windowsWidth, windowsHeight);  //边大小  
        frame.setResizable(false);  
        frame.setTitle("Project DEMO");  
         
        mainpanel.setLayout(null);
        mainpanel.setBackground(new Color(255, 255, 0));
        mainpanel.setBounds(0,155,1200,645);

        tabblue.setLayout(null);
        tabblue.setBounds(0, 130, 1200, 25);
        tabblue.setBackground(new Color(38,82,179));
        
        contentpanel.setLayout(new GridLayout(1,2,0,0));
        contentpanel.setBounds(0,0,1200,100);  

        panel1.setOpaque(false);
        panel1.setLayout(null);
        panel2.setOpaque(false);
        panel2.setLayout(null);
            
        searchbar.setBounds(200,35 , 300, 30);
        searchbar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        searchbar.setForeground(Color.gray);
        searchbar.setLayout(null);
        searchbar.setColumns(20); 
        panel2.add(searchbar);
               
        Button_search.setLayout(null);
        Button_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_search.setIcon(new ImageIcon("bin/search.jpg"));
        Button_search.setBounds(500, 35, 30, 30);
        panel2.add(Button_search);
            
        usericon.setIcon(new ImageIcon("bin/usericon.png"));
        usericon.setBounds(200, 0, 30, 30);

        comboBox.addItem("User1");
        comboBox.addItem("User2");
        comboBox.addItem("User3");
        
        userpanel.setBounds(800, 100, 400, 30);
        userpanel.setBackground(new Color(204, 204, 204));
        userpanel.setLayout(null);
        comboBox.setBounds(230,5,100,20);
        userpanel.add(comboBox);
        userpanel.add(usericon);
        tabpanel.setBounds(0, 100, 800, 30);
        tabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabpanel.setBackground(new Color(204, 204, 204));
        
        //设置鼠标放上去变手形
        Button_funtion1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Button_funtion1.setBorderPainted(false);
        Button_funtion1.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion1.setBackground(null);
        Button_funtion1.setForeground(Color.white);
        Button_funtion1.setFont(new Font("黑体", Font.BOLD, 15));

        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        Button_funtion2.setForeground(new Color(38,82,179));
        Button_funtion2.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion2.setBackground(null);
        Button_funtion2.setBorderPainted(false);
       
        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        Button_funtion3.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion3.setForeground(new Color(38,82,179));
        Button_funtion3.setBackground(null);
        Button_funtion3.setBorderPainted(false);
        
       
        Button_funtion4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        Button_funtion4.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion4.setForeground(new Color(38,82,179));
        Button_funtion4.setBackground(null);
        Button_funtion4.setBorderPainted(false);
        
        tabpanel.add(Button_funtion1);
        tabpanel.add(Button_funtion2);
        tabpanel.add(Button_funtion3);
        tabpanel.add(Button_funtion4);
        contentpanel.add(panel1);
        contentpanel.add(panel2);
       
        searchpanel.setLayout(null);
        searchpanel.setBounds(0,155,1200,645);
        

        
        //将面板加到窗体上  
        frame.add(contentpanel); 
        frame.add(tabpanel);
        frame.add(mainpanel);
        frame.add(tabblue);
        frame.add(userpanel);
 
        
        //设置关闭窗口时退出应用程序  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  //显示窗体  
	}
	
    public static void main(String[] args) throws FileNotFoundException, IOException{
		
		//初始化UI
		initUI();
		initButton();
	}
	
	

	private static void initButton() {
		// TODO Auto-generated method stub
		//点击搜索框就清楚提示
		searchbar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			 searchbar.setText("");}
	    });
		
		//回车搜索监听
		searchbar.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				 if(e.getKeyCode() == KeyEvent.VK_ENTER) //判断按下的键是否是回车键
				   {  
				    Button_search.doClick();
				   }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
        Button_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Search Button Pressed!");
                String keyword = searchbar.getText();
                if (keyword.isEmpty()||keyword.equals("请输入查询关键字（文件名或拓展名）")||keyword.equals("提示：未输入关键字")){
                	System.out.println("Search Key Word Empty!");
                	searchbar.setText("提示：未输入关键字");
                }else{
                	initSearchUI();
                    new Thread(new Runnable(){
                    	@Override
                    	public void run() {

                    		new BasicSearch();
							BasicSearch.SearchFile("C://Users//骞宇澄//Desktop", keyword);
                    	}
                    }).start();
                }

			}
		});
        
        Button_funtion1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Function 1");
				Button_funtion1.setIcon(icon);
		        Button_funtion1.setForeground(Color.white);
		        Button_funtion1.setFont(new Font("黑体", Font.BOLD, 15));
		        
		        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion2.setIcon(iconnull);
 		        Button_funtion2.setForeground(new Color(38,82,179));
 		        
		        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion3.setIcon(iconnull);
		        Button_funtion3.setForeground(new Color(38,82,179));
		        
		        Button_funtion4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion4.setIcon(iconnull);
 		        Button_funtion4.setForeground(new Color(38,82,179));
			}
		});
        Button_funtion2.addActionListener(new ActionListener() {
			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				// TODO Auto-generated method stub
 				System.out.println("Function 2");
 				Button_funtion2.setIcon(icon);
 		        Button_funtion2.setForeground(Color.white);
 		        Button_funtion2.setFont(new Font("黑体", Font.BOLD, 15));
 		        
 		        Button_funtion1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion3.setForeground(new Color(38,82,179));	        
 		        Button_funtion3.setIcon(iconnull);
 		        
 		        Button_funtion4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion4.setForeground(new Color(38,82,179));
 		        Button_funtion4.setIcon(iconnull);
 			}
 		});
        
        Button_funtion3.addActionListener(new ActionListener() {
			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				// TODO Auto-generated method stub
 				System.out.println("Function 3");
 				Button_funtion3.setIcon(icon);
 		        Button_funtion3.setForeground(Color.white);
 		        Button_funtion3.setFont(new Font("黑体", Font.BOLD, 15));
 		        
 		        Button_funtion1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion2.setForeground(new Color(38,82,179));        
 		        Button_funtion2.setIcon(iconnull);
 		        
 		        Button_funtion4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion4.setForeground(new Color(38,82,179));
 		        Button_funtion4.setIcon(iconnull);

 			}
 		});
        
        Button_funtion4.addActionListener(new ActionListener() {
			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				// TODO Auto-generated method stub
 				System.out.println("Function 4");
 				Button_funtion4.setIcon(icon);
 		        Button_funtion4.setForeground(Color.white);
 		        Button_funtion4.setFont(new Font("黑体", Font.BOLD, 15));
 		        
 		        Button_funtion1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);
 		        
 		        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion3.setForeground(new Color(38,82,179));	        
 		        Button_funtion3.setIcon(iconnull);
 		        
 		        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion2.setForeground(new Color(38,82,179));
 		        Button_funtion2.setIcon(iconnull);
 
 			}
 		});
	}

	//设置头部的背景颜色
	public static class BackgroundPanel extends JPanel {  
		
		private static final long serialVersionUID = -6352788025440244338L; 
        private Image image = null;  
  
        public BackgroundPanel(Image image) {  
                 this.image = image;  
        }  
           
             // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
        protected void paintComponent(Graphics g) {  
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
        }  
	}
	
	//设置头部商标界面
	public static class TitlePanel extends JPanel{
		private static final long serialVersionUID = -6352788025440244338L; 
		 Image image=null;
		 
		    public void paint(Graphics g){
		        try {
		            image=ImageIO.read(new File("bin/title.png"));
		            g.drawImage(image, 0,0,450,100, null);
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		    }
	
	}
	
	//获得字体
	public static Font getFont(float size) {  
        String pathString = "bin/车牌字体DIN1451.ttf";
        try {  
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, new File(pathString));  
            dynamicFont = dynamicFont.deriveFont(size);  
            return dynamicFont;  
        } catch (FontFormatException ex) {  
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);  
        } catch (IOException ex) {  
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        return null;  

	} 
	
	//初始化精确搜索UI
	private static void initSearchUI(){
		//读取Font
		
		JPanel searchprogress = new BackgroundPanel(image);
		JPanel searchinfo = new BackgroundPanel(whitebackground);
		JPanel resultPanel = new BackgroundPanel(resultbackground);
		JPanel resultTitle = new BackgroundPanel(resulttitle);
		JPanel resultItem = new JPanel();
		JPanel resultCategory = new JPanel();
		JPanel resultListDisplay = new JPanel();
		JScrollPane resultScroll = new JScrollPane(resultListDisplay);
		JProgressBar progressBar = new JProgressBar();
		JLabel searching = new JLabel("当前正在过滤文件...");
		JLabel searchitem = new JLabel("Searching items");
		JLabel loadingicon = new JLabel();
		JLabel loadingmessage = new JLabel("正在寻找资源，可能要花费一段时间...");
		JLabel resultlist = new JLabel("查询结果");
		JLabel noResult = new JLabel("暂无结果...");
		JButton cate_Num = new JButton("序号");
		JButton cate_Name = new JButton("资源名称");
		JButton cate_Suffix = new JButton("拓展名");
		JButton cate_Path = new JButton("路径");
		searching.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searching.setBounds(20,10,200,20);
	
		
		
		searchprogress.setLayout(null);
        searchprogress.setBounds(5,5,785,100);
        searchinfo.setLayout(null);
        searchinfo.setBounds(800, 5, 385, 100);
        loadingicon.setIcon(new ImageIcon("bin/loading.gif"));
        loadingicon.setBounds(5,5,80,80);
        loadingmessage.setBounds(100,40,300,20);
        loadingmessage.setFont(new Font("微软雅黑", Font.BOLD, 13));
        progressBar.setBounds(20, 60, 750, 20);
        searchitem.setBounds(20, 35, 750,20);

        progressBar.setMinimum(0);
        progressBar.setMaximum(99);
        progressBar.setStringPainted(true);
        
        searchprogress.add(searchitem);
        searchprogress.add(progressBar);
        searchprogress.add(searching);
        searchinfo.add(loadingicon);
        searchinfo.add(loadingmessage);
        
        //显示搜索结果的界面
        resultPanel.setLayout(null);
        resultPanel.setBounds(5, 110, 785, 500);
        resultTitle.setLayout(null);
        resultlist.setForeground(new Color(255, 255, 255));
        resultlist.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        resultlist.setBounds(17, 0, 70, 20);
        resultTitle.add(resultlist);
        resultTitle.setBounds(10, 5, 770, 20);
        
        resultItem.setLayout(null);
        resultCategory.setLayout(null);
        resultCategory.setBackground(null);
        resultCategory.setBounds(0, 0, 1000, 20);
        resultCategory.add(cate_Num);
        resultCategory.add(cate_Name);
        resultCategory.add(cate_Suffix);
        resultCategory.add(cate_Path);
        //信息栏配置字体
        cate_Name.setFont(new Font("宋体",Font.PLAIN,13));
        cate_Num.setFont(new Font("宋体",Font.PLAIN,13));
        cate_Path.setFont(new Font("宋体",Font.PLAIN,13));
        cate_Suffix.setFont(new Font("宋体",Font.PLAIN,13));
        
        
        
        cate_Num.setBounds(0,0,70,20);
        cate_Name.setBounds(70,0,220,20);
        cate_Suffix.setBounds(290,0,80,20);
        cate_Suffix.setHorizontalTextPosition(SwingConstants.LEFT);
        cate_Path.setBounds(370,0,630,20);

        
        resultListDisplay.add(resultCategory);

        resultListDisplay.setLayout(null);
        resultScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        resultScroll.setBounds(10, 25, 770, 470);
        resultPanel.add(resultScroll);
        resultPanel.add(resultTitle);
        searchpanel.add(searchprogress);
        searchpanel.add(searchinfo);
        searchpanel.add(resultPanel);
        
        frame.add(searchpanel);

        mainpanel.setVisible(false);

        
        //更新searchitem的线程
        Thread searchItemThread = new Thread(new Runnable(){
        	
            @Override
            public void run() {   
            	long count = 0;
                	while (count < Long.MAX_VALUE) {
                		while(!isVolumeGot){
                			try {  
                				Thread.sleep(100);  
                			} catch (InterruptedException e) {  
                				// TODO Auto-generated catch block  
                				e.printStackTrace();  
                			} 
                			count++;  
                            SwingUtilities.invokeLater(new Runnable() {                     
                                @Override  
                                public void run() {  
                                    // TODO Auto-generated method stub  
                                    //更新操作通过事件派发线程完成（一般实现Runnable()接口）  
                                	searchitem.setText(BasicSearch.currentItem);  
                                }  
                            }); 
                		}
                		while(isVolumeGot){
                			try {
								Thread.sleep(Long.MAX_VALUE);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		}
                }
            }   	
        });
        searchItemThread.start();
       
        //更新progressbar和搜索量、搜索时间的线程
        Thread progressBarThread = new Thread(new Runnable(){
            @Override
            public void run() {   
                int value = 0;
            	while (!isVolumeGot) {
                    try {  
                    	int max=1000;
                        int min=700;
                        Random random = new Random();
                        int s = random.nextInt(max)%(max-min+1) + min;
                    	progressBar.setValue(value);
                    	value++;
                    	Thread.sleep(s); 
                    	
                    } catch (InterruptedException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    } 
            	}
                while(isVolumeGot){

                	progressBar.setValue(100);
                	searching.setText("查询已完成");
                	
                	searchinfo.remove(loadingicon);
		    		searchinfo.remove(loadingmessage);
		    		
		    		JLabel icon_volume = new JLabel();
		    		JLabel icon_time = new JLabel();
		    		JLabel infoTime = new JLabel();
		    		JLabel volumeUnit = new JLabel();
		    		JLabel infoVolume = new JLabel();
		    		JLabel second = new JLabel("S");
		    		JLabel volumeTitle = new JLabel("查询的目标文件总量");
		    		JLabel timeTitle = new JLabel("搜索耗时");
		    		System.out.println("____________"+searchVolume);
		    		System.out.println(searchVolumeTime);
		    		//更新搜索整理时间并且判断容量单位
		    		
		    		//容量小于1024KB=1MB
		    		if(searchVolume < 1024.0){
		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		        		infoVolume.setText(String.valueOf(newVolume));
		        		volumeUnit.setText("KB");
		    		}
		    		//容量小于1024MB=1GB
		    		if(searchVolume >= 1024.0 && searchVolume < 1024.0*1024){
		    			searchVolume = searchVolume/1024.0;
		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		        		infoVolume.setText(String.valueOf(newVolume));
		        		volumeUnit.setText("MB");
		    		}
		    		//容量大于1GB
		    		if(searchVolume >= 1024.0*1024){
		    			searchVolume = searchVolume/(1024.0*1024);
		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		        		infoVolume.setText(String.valueOf(newVolume));
		        		volumeUnit.setText("GB");
		    		}
		    		volumeTitle.setBounds(65, 15, 150, 20);
		    		volumeTitle.setFont(new Font("微软雅黑", Font.BOLD, 13));
		    		timeTitle.setBounds(265, 15, 150, 20);
		    		timeTitle.setFont(new Font("微软雅黑", Font.BOLD, 13));
		    		
		    		java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolumeTime);
					double newVolumeTime = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		    		infoTime.setText(String.valueOf(newVolumeTime));
		    		infoTime.setHorizontalAlignment(SwingConstants.RIGHT);
		    		infoTime.setFont(getFont(25.0f));
		    		infoTime.setForeground(new Color(38,82,179));


		    		volumeUnit.setFont(getFont(15.0f));
		    		second.setFont(getFont(15.0f));
		    		second.setForeground(new Color(38,82,179));
		    		volumeUnit.setForeground(new Color(38,82,179));

		    		infoVolume.setFont(getFont(25.0f));
		    		infoVolume.setForeground(new Color(38,82,179));
		    		infoVolume.setHorizontalAlignment(SwingConstants.RIGHT);
		    		icon_time.setIcon(new ImageIcon("bin/timeicon.png"));
		    		icon_volume.setIcon(new ImageIcon("bin/volumeicon.png"));
		    		icon_volume.setBounds(20,5,50,50);
		    		icon_time.setBounds(220, 5, 50, 50);
		    		infoVolume.setBounds(60,20 , 100, 80);
		    		volumeUnit.setBounds(170, 40, 60, 50);
		    		infoTime.setBounds(265, 20, 80, 80);
		    		second.setBounds(355,40,60,50);
		    		
		    		searchinfo.add(icon_volume);
		    		searchinfo.add(icon_time);
		    		searchinfo.add(infoTime);
		    		searchinfo.add(volumeUnit);
		    		searchinfo.add(infoVolume);
		    		searchinfo.add(second);
		    		searchinfo.add(volumeTitle);
		    		searchinfo.add(timeTitle);
		    		
		    	    searchpanel.repaint();
		    	    return;
                }
                }	
               	
        });
        progressBarThread.start();     
        
        //更新搜索结果面板的线程
        Thread DisplayItemThread = new Thread(new Runnable(){
        	
            @Override
            public void run() {   
            	long count = 0;

            	while (count < Long.MAX_VALUE) {
                    try {  
                        Thread.sleep(1000);  
                    } catch (InterruptedException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    } 
                    count = (long) (count + 0.001);  
                        SwingUtilities.invokeLater(new Runnable() {                     
                            @Override  
                            public void run() {  
                                // TODO Auto-generated method stub  
                                //更新操作通过事件派发线程完成（一般实现Runnable()接口）  

                            		while(BasicSearch.resultlist.size() == 0){
                            			noResult.setBounds(0, 50, 100, 100);
                            			resultListDisplay.add(noResult);
//                                        resultScroll.validate();
//                                       	resultScroll.repaint();
                                       	break;
                            		}
                            		while(BasicSearch.resultlist.size() != 0){
                            			resultListDisplay.setPreferredSize(new Dimension(1000, (BasicSearch.resultlist.size()+1)*20));
     
                               		 	for(int i = 0; i < BasicSearch.resultlist.size();i++){
                               		 		int j = i;
                               		 		JPanel eachItem = new JPanel();
                               		 		eachItem.setLayout(null); 
                               		 		eachItem.setBounds(0, 20*(i+1), 1000, 20);
                               		 		JLabel fileSequence = new JLabel(String.valueOf((i+1)));
                               		 		fileSequence.setFont(getFont(13));
                               		 		MyToolTip fileName = new MyToolTip();
                               		 		fileName.setText(BasicSearch.resultlist.get(i).getName());
                               		 		fileName.setToolTipText(BasicSearch.resultlist.get(i).getName());
                               		 		JLabel fileSuffix = new JLabel((BasicSearch.resultlist.get(i).getAbsolutePath().substring(BasicSearch.resultlist.get(i).getAbsolutePath().lastIndexOf(".")+1)));
                               		 		MyToolTip filePath = new MyToolTip();
                               		 		filePath.setText(BasicSearch.resultlist.get(i).getAbsolutePath());
                               		 		filePath.setToolTipText(BasicSearch.resultlist.get(i).getAbsolutePath());
                               		 		fileSequence.setBounds(0,0,70,20);
                               		 		fileSequence.setHorizontalAlignment(SwingConstants.CENTER);
                               		 		fileName.setBounds(70,0,200,20);
                               		 		
                               		 		fileSuffix.setBounds(290,0,80,20);
                               		 		filePath.setBounds(370,0,630,20);
//                               		 		fileSequence.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                               		 		fileName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                               		 		fileName.setForeground(new Color(0, 0, 139));
                               		 		fileSuffix.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                               		 		filePath.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                               		 		fileName.addMouseListener(new MouseListener() {
												
												@Override
												public void mouseReleased(MouseEvent e) {
													// TODO Auto-generated method stub
													fileName.setForeground(Color.gray);
												}
												
												@Override
												public void mousePressed(MouseEvent e) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void mouseExited(MouseEvent e) {
													// TODO Auto-generated method stub
													if(fileName.getForeground() != Color.gray){
														fileName.setForeground(new Color(0, 0, 139));
														fileName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
													}
											
													
												}
												
												@Override
												public void mouseEntered(MouseEvent e) {
													// TODO Auto-generated method stub
										            if(fileName.getForeground() != Color.gray){
														fileName.setForeground(Color.BLUE);
														fileName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
														 fileName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
										            }
												}
												
												@Override
												public void mouseClicked(MouseEvent e) {
													// TODO Auto-generated method stub
													fileName.setForeground(Color.gray);
											        
													try {
														Desktop.getDesktop().open(BasicSearch.resultlist.get(j));
													} catch (IOException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											});
                               		 		eachItem.add(fileSequence);
                               		 		eachItem.add(fileName);
                               		 		eachItem.add(fileSuffix);
                               		 		eachItem.add(filePath);
                               		 		resultListDisplay.add(eachItem);  
                               		 		resultScroll.validate();
                               		 		resultScroll.repaint();
                               		 	}
                               		 	break;
                            		}
                            }  
                        }); 
            	}	
               }    	
        });
        DisplayItemThread.start();
	}
	
}
