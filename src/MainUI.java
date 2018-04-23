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
import java.awt.Panel;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.text.Utilities;

import org.omg.CORBA.PUBLIC_MEMBER;


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
    static JPanel timeLinePanel = new BackgroundPanel(mainbackground);
    static JPanel singleFilePanel = new BackgroundPanel(mainbackground);
    static JPanel panel1 = new TitlePanel();
    static JPanel panel2 = new JPanel();
    static JPanel tabblue = new JPanel();
    static JPanel tabpanel = new JPanel();
    static JPanel userpanel = new JPanel();
    static JPanel searchpanel = new BackgroundPanel(mainbackground);
    static JPanel searchinfo;
    static JTextField searchbar=new JTextField("请输入查询关键字（文件名或拓展名）"); 
    static JButton Button_search = new JButton();
    static JButton Button_funtion1 = new JButton("时间线查找",icon);
    static JButton Button_funtion2 = new JButton("关联查找",iconnull);
    static JButton Button_funtion3 = new JButton("精确查找",iconnull);
    
    static Thread searchThread = new Thread();
    static Thread thread1,thread2,thread3,threadControl;
    static JLabel usericon = new JLabel();
    static JLabel loadingicon;
    static JLabel loadingmessage;

    static JComboBox comboBox = new JComboBox();
    
    static JLabel icon_volume;
	static JLabel icon_time;
	static JLabel infoTime;
	static JLabel volumeUnit;
	static JLabel infoVolume;
	static JLabel second;
	static JLabel volumeTitle;
	static JLabel timeTitle;
	
    static int value = 0;
    static String keyword;
    static long startVolumeTime;
    static long timeUsed;
    static Boolean isVolumeGot = false;
    static Boolean isPause = false;
    static Boolean isStop = false;
    static double searchVolume;
    static double searchVolumeTime;
    
    private static void initUI() {
		// TODO Auto-generated method stub
          
        frame.setLayout(null);  //自由布局  
        frame.setBounds((ScreenWidth - windowsWidth) / 2, (ScreenHeight - windowsHeight) / 2, windowsWidth, windowsHeight);  //边大小  
        frame.setResizable(false);  
        frame.setTitle("CONTEXT|KEEPER 文件可视化系统");  
         
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
        searchbar.setEditable(true);
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
        tabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tabpanel.setBackground(new Color(204, 204, 204));
        
        contentpanel.add(panel1);
        contentpanel.add(panel2);
       
        searchpanel.setLayout(null);
        searchpanel.setBounds(0,155,1200,645);
        
        //将面板加到窗体上  
        frame.add(contentpanel); 
        frame.add(tabpanel);
        frame.add(tabblue);
        frame.add(userpanel);
 
        //设置关闭窗口时退出应用程序  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  //显示窗体  
	}
	
    public static void main(String[] args) throws FileNotFoundException, IOException{
		
		//加载Nimbus风格
    	String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    	try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//初始化
		initUI();
		initButton();
		initTimeLineUI();
		initSingleFileUI();
		initSearchUI();
	}
	
	

	private static void initSingleFileUI() {
		// TODO Auto-generated method stub
		 singleFilePanel.setLayout(null);
	     singleFilePanel.setBounds(0,155,1200,645);
	        
	     frame.add(singleFilePanel);
	}

	

	private static void initButton() {
		// TODO Auto-generated method stub
        //设置Tab:鼠标放上去变手形
        Button_funtion1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Button_funtion1.setBorderPainted(false);
        Button_funtion1.setFocusable(false);
        Button_funtion1.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion1.setBackground(null);
        Button_funtion1.setForeground(Color.white);
        Button_funtion1.setFont(new Font("黑体", Font.BOLD, 13));

        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        Button_funtion2.setFocusable(false);
        Button_funtion2.setForeground(new Color(38,82,179));
        Button_funtion2.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion2.setBackground(null);
        Button_funtion2.setBorderPainted(false);
       
        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        Button_funtion3.setFocusable(false);
        Button_funtion3.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion3.setForeground(new Color(38,82,179));
        Button_funtion3.setBackground(null);
        Button_funtion3.setBorderPainted(false);
        
        tabpanel.add(Button_funtion1);
        tabpanel.add(Button_funtion2);
        tabpanel.add(Button_funtion3);
        
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
                keyword = searchbar.getText();
                if (keyword.isEmpty()||keyword.equals("请输入查询关键字（文件名或拓展名）")||keyword.equals("提示：未输入关键字")){
                	System.out.println("Search Key Word Empty!");
                	searchbar.setText("提示：未输入关键字");
                }else{
                	initSearchUI();
                    searchThread = new Thread(new Runnable(){
                    	@Override
                    	public void run() {
                    		startVolumeTime = System.nanoTime();
							BasicSearch.SearchFile("C://Users//", keyword);
                    	}
                    });
                    searchThread.start();
                }

			}
		});
        
        Button_funtion1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//以Button的方式实现点击效果
				System.out.println("Function 1");
				Button_funtion1.setIcon(icon);
		        Button_funtion1.setForeground(Color.white);
		        Button_funtion1.setFont(new Font("黑体", Font.BOLD, 13));
		        
		        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion2.setIcon(iconnull);
 		        Button_funtion2.setForeground(new Color(38,82,179));
 		        
		        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion3.setIcon(iconnull);
		        Button_funtion3.setForeground(new Color(38,82,179));
		        

 		        //打开Tab1，关闭其他Tab
 		        timeLinePanel.setVisible(true);
 		        singleFilePanel.setVisible(false);
 		        searchpanel.setVisible(false);
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
 		        
 		        Button_funtion1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion3.setForeground(new Color(38,82,179));	        
 		        Button_funtion3.setIcon(iconnull);
 		        
 		        //打开Tab1，关闭其他Tab
 		        timeLinePanel.setVisible(false);
 		        singleFilePanel.setVisible(true);
 		        searchpanel.setVisible(false);
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
 		        
 		        Button_funtion1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
 		        Button_funtion2.setForeground(new Color(38,82,179));        
 		        Button_funtion2.setIcon(iconnull);
 		        
 		        //打开Tab1，关闭其他Tab
 		        timeLinePanel.setVisible(false);
 		        singleFilePanel.setVisible(false);
 		        searchpanel.setVisible(true);
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
		searchinfo = new JPanel();
		JPanel resultPanel = new BackgroundPanel(resultbackground);
		JPanel resultTitle = new BackgroundPanel(resulttitle);
		JPanel resultItem = new JPanel();
		JPanel resultCategory = new JPanel();
		JPanel resultListDisplay = new JPanel();
		JPanel pathPanel = new BackgroundPanel(image);
		
		JPanel detailPanel = new BackgroundPanel(resultbackground);
		JPanel detailTitlePanel = new BackgroundPanel(resulttitle);
		JTextArea detailName = new JTextArea("文件名称");
		JLabel fileLabel = new JLabel("文件",SwingConstants.LEFT);
		JLabel detailSize = new JLabel("文件大小");
		JLabel detailTime = new JLabel("上次修改时间");
		JTextArea detailPath = new JTextArea("文件路径");
		JLabel nameLabel = new JLabel("文件名称",SwingConstants.CENTER);
		JLabel sizeLabel = new JLabel("文件大小",SwingConstants.CENTER);
		JLabel timeLabel = new JLabel("上次修改",SwingConstants.CENTER);
		JLabel pathLabel = new JLabel("文件路径",SwingConstants.CENTER);
		JLabel detailIcon = new JLabel();
		JButton openFileButton = new JButton("打开文件");
		JButton singleFileAccess = new JButton("查找关联文件");

		JScrollPane resultScroll = new JScrollPane(resultListDisplay);
		JProgressBar progressBar = new JProgressBar();
		JLabel searching = new JLabel("请在搜索框输入关键字（并选择路径）点击开始按钮查找...");
		JLabel searchitem = new JLabel("当前过滤项");
		loadingicon = new JLabel();
		loadingmessage = new JLabel("正在寻找资源，可能要花费一段时间...");
		 
		JLabel resultlist = new JLabel("查询结果");
		JLabel noResult = new JLabel("暂无结果...");
		JLabel start_label = new JLabel("开始/继续",SwingConstants.CENTER);
		JLabel pause_label = new JLabel("暂停",SwingConstants.CENTER);
		JLabel stop_label = new JLabel("停止",SwingConstants.CENTER);
		JLabel key_label = new JLabel("当前关键字",SwingConstants.LEFT);
		JLabel path_label = new JLabel("当前路径",SwingConstants.LEFT);
		JButton pause_button = new JButton(new ImageIcon("bin/pause_button.png"));
		JButton start_button = new JButton(new ImageIcon("bin/start_button.png"));
		JButton stop_button = new JButton(new ImageIcon("bin/stop_button.png"));
		JButton cate_Num = new JButton("序号");
		JButton cate_Name = new JButton("资源名称");
		JButton cate_Suffix = new JButton("拓展名");
		JButton cate_Path = new JButton("路径");
		JButton openfile = new JButton("...",new ImageIcon("bin/filechooser.png"));
		JButton deleteAll = new JButton("清除");
		JButton searchNow = new JButton("开始查找");
		JFileChooser fileChooser = new JFileChooser();
		JTextField keyField = new JTextField("请输入关键字");
		JTextField pathField = new JTextField("精确路径");
		searching.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searching.setBounds(20,10,500,20);
	
		searchinfo.setVisible(false);
		
		deleteAll.setBounds(10, 65, 180, 25);
		searchNow.setBounds(195, 65, 180, 25);
		
		pathPanel.setLayout(null);
		pathPanel.setBounds(800, 110, 385, 95);

		key_label.setBounds(10, 10, 80, 20);
		key_label.setFont(new Font("微软雅黑", Font.BOLD, 13));
		path_label.setBounds(10, 35, 80, 20);
		path_label.setFont(new Font("微软雅黑", Font.BOLD, 13));
		
        pathField.setBounds(85,35 , 245, 20);
        pathField.setEditable(true);
        pathField.setEditable(false);
        pathField.setForeground(Color.gray);
        pathField.setLayout(null);
        pathField.setColumns(20); 
        
        keyField.setBounds(85,10 , 290, 20);
        keyField.setEditable(true);
        keyField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        keyField.setForeground(Color.gray);
        keyField.setLayout(null);
        keyField.setColumns(20); 
        
		searchprogress.setLayout(null);
        searchprogress.setBounds(5,5,1185,100);
        searchinfo.setLayout(null);
        searchinfo.setBounds(800, 210, 385, 90);
        searchinfo.setBackground(new Color(255, 255, 255));
        loadingicon.setIcon(new ImageIcon("bin/loading.gif"));
        loadingicon.setBounds(5,5,80,70);
        loadingmessage.setBounds(100,35,300,20);
        loadingmessage.setFont(new Font("微软雅黑", Font.BOLD, 13));
        progressBar.setBounds(20, 60, 750, 20);
        searchitem.setBounds(20, 35, 750,20);

        progressBar.setMinimum(0);
        progressBar.setMaximum(99);
        progressBar.setStringPainted(true);
        
        start_button.setBounds(875, 20, 40, 40);
        start_button.setBorderPainted(false);
        start_button.setContentAreaFilled(false);
        start_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pause_button.setBounds(975, 20, 40, 40);
        pause_button.setBorderPainted(false);
        pause_button.setContentAreaFilled(false);
        pause_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        stop_button.setBounds(1075, 20, 40, 40);
        stop_button.setContentAreaFilled(false);
        stop_button.setBorderPainted(false);
        stop_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        start_label.setFont(new Font("微软雅黑", Font.BOLD, 12));
        pause_label.setFont(new Font("微软雅黑", Font.BOLD, 12));
        stop_label.setFont(new Font("微软雅黑", Font.BOLD, 12));
        
        start_label.setBounds(865, 70, 60, 20);
        pause_label.setBounds(975, 70, 40, 20);
        stop_label.setBounds(1075, 70, 40, 20);
               
        searchprogress.add(searchitem);
        searchprogress.add(progressBar);
        searchprogress.add(searching);
        searchprogress.add(pause_button);
        searchprogress.add(start_button);
        searchprogress.add(stop_button);
        searchprogress.add(start_label);
        searchprogress.add(pause_label);
        searchprogress.add(stop_label);
        searchprogress.add(openfile);
        searchinfo.add(loadingicon);
        searchinfo.add(loadingmessage);
        
        //选定精确位置的pathPanel
		openfile.setBounds(335,35,40,20);
		openfile.setFont(new Font("微软雅黑", Font.BOLD, 10));
		
		pathPanel.add(openfile);
		pathPanel.add(pathField);
		pathPanel.add(keyField);
		pathPanel.add(key_label);
		pathPanel.add(path_label);
		pathPanel.add(deleteAll);
		pathPanel.add(searchNow);

		detailPanel.setBounds(800, 305, 385, 305);
		
		detailPanel.setLayout(null);
		detailTitlePanel.add(fileLabel);
		detailTitlePanel.setBounds(5,5,375,25);
		detailTitlePanel.setLayout(null);
		openFileButton.setBounds(170, 55, 150, 20);
		singleFileAccess.setBounds(170, 85, 150, 20);
		detailIcon.setBounds(70, 45, 50, 70);
		detailIcon.setIcon(new ImageIcon("bin/icon_doc.png"));
		fileLabel.setForeground(new Color(255, 255, 255));
		fileLabel.setBounds(10,0,30,25);
		
        
        detailName.setBounds(90, 130, 270, 40);
        detailName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        detailSize.setBounds(90, 175, 270, 20);
        detailSize.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        detailTime.setBounds(90, 210, 270, 20);
        detailTime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        detailPath.setBounds(90, 245, 270, 60);
        detailPath.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        
        nameLabel.setBounds(10, 130, 70, 20);
        sizeLabel.setBounds(10, 175, 70, 20);
        timeLabel.setBounds(10, 210, 70, 20);
        pathLabel.setBounds(10, 245, 70, 20);
        
        fileLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        sizeLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        pathLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        detailPath.setBackground(null);
        detailName.setBackground(null);
        
        detailPath.setLineWrap(true);
        detailName.setLineWrap(true);
        detailPath.setWrapStyleWord(true);
        detailName.setWrapStyleWord(true);
		detailPanel.add(detailTitlePanel);
		detailPanel.add(detailIcon);
		detailPanel.add(openFileButton);
		detailPanel.add(singleFileAccess);
		detailPanel.add(detailName);
		detailPanel.add(detailSize);
		detailPanel.add(detailTime);
		detailPanel.add(detailPath);
		detailPanel.add(nameLabel);
		detailPanel.add(sizeLabel);
		detailPanel.add(timeLabel);
		detailPanel.add(pathLabel);
		detailPanel.setVisible(false);
		
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
        
        searchpanel.add(detailPanel);
        searchpanel.add(searchprogress);
        searchpanel.add(pathPanel);
        searchpanel.add(searchinfo);
        searchpanel.add(resultPanel);
        
        frame.add(searchpanel);

        searchpanel.setVisible(false);
        
        keyField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			 if(keyField.getText().equals("请输入关键字")){
				 keyField.setText("");
			 }}
	    });
        
        //浏览文件夹对话框的监听
        openfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("请选择路径");
				fileChooser.showDialog(new JLabel(), "选择");
				File file = fileChooser.getSelectedFile();
				pathField.setText(file.getAbsolutePath());
				
			}
		});
        openFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					System.out.println("Open"+detailPath.getText());
					
					Desktop.getDesktop().open(new File(detailPath.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        //清除按钮的监听
        deleteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				keyField.setText("");
				pathField.setText("");
			}
		});
        
      
        
        //更新searchitem的线程
        class searchItemThread extends Thread{
        	private boolean stop = false;

            public void stopTask() {
                stop = true;
            }
        	@Override
            public void run() {
                // TODO Auto-generated method stub
        		while(!stop){
        			 super.run();
                     long count = 0;
                 	while(!isVolumeGot){
                     	while (count < Long.MAX_VALUE) {
                     		
                     		
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
                     		
                     }
        		}
               
            }
        }

        //更新progressbar和搜索量、搜索时间的线程
        class progressBarThread extends Thread{
        	
        	private boolean stop = false;
        	public void stopTask(){
        		stop = true;
        	}
        	
        	@Override
            public void run() {
                // TODO Auto-generated method stub
        		while(!stop){
        			super.run();
                    
        			while (!isVolumeGot ) {
                		if(value < 99){
                       	progressBar.setValue(value);
                    	value++;
                		}
                        try {
                        	
                        	int max=1000;
                            int min=700;
                            Random random = new Random();
                            int s = random.nextInt(max)%(max-min+1) + min;
     
                        	Thread.sleep(s); 
                        	}
                        	
                        	
                         catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        } 
                        
                	}
                	while(isVolumeGot){
                    	progressBar.setValue(100);
                    	searching.setText("查询已完成");
                    	updateResultPanel();
                    	try {
    						Thread.sleep(Long.MAX_VALUE);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
                    }
        		}
                
            }
        }
      
        //更新搜索结果面板的线程
        class DisplayItemThread extends Thread{
        	private boolean stop = false;

            public void stopTask() {
                stop = true;
            }
        	@Override
            public void run() {
                // TODO Auto-generated method stub
        		while(!stop){
        			 super.run();
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
//                                             resultScroll.validate();
//                                            	resultScroll.repaint();
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
//                                    		 		fileSequence.setFont(new Font("微软雅黑", Font.PLAIN, 13));
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
     											        
//     													
     													detailName.setText(BasicSearch.resultlist.get(j).getName());
     													double size = BasicSearch.resultlist.get(j).length()/1024.0;
     													if(size < 1024.0){
     														java.math.BigDecimal bigDecimal = new java.math.BigDecimal(size);
     										    			double newSize = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
     														detailSize.setText(newSize+" KB");
     													}
     													else if(size > 1024.0 && size < 1024.0*1024.0){
     														size = size/1024.0;
     														java.math.BigDecimal bigDecimal = new java.math.BigDecimal(size);
     										    			double newSize = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
     														detailSize.setText(newSize+" MB");
     													}else{
     														size = size/(1024.0*1024.0);
     														java.math.BigDecimal bigDecimal = new java.math.BigDecimal(size);
     										    			double newSize = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
     														detailSize.setText(newSize+" GB");
     													}
     													detailPath.setText(BasicSearch.resultlist.get(j).getAbsolutePath());
     													java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     													String dateTime = df.format(BasicSearch.resultlist.get(j).lastModified());
     													detailTime.setText(dateTime);
     													detailPanel.setVisible(true);
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
               
               
            }
        }
        
      threadControl = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
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
                        	 if(isVolumeGot){
                        		 System.out.println("threadControl Working");
                 	        	if(searchThread.isAlive()){
                 					searchThread.stop();
                 				}
                 				
                 				if(thread1.isAlive()){
                 					thread1.stop();
                 					}
                 				
                 				if(thread3.isAlive()){
                 				thread3.stop();
                 				}
                 				System.out.println(thread1.getState());
                 				System.out.println(thread2.getState());
                 				System.out.println(thread3.getState());
                 				System.out.println(searchThread.getState());
                        	 }
                         }
                         });
				
	        }
		}
	});
      threadControl.start();
      

        
        //开始查找按钮的监听
        searchNow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(keyField.getText().equals("") ||keyField.getText().equals("请输入关键字")){
					JOptionPane.showMessageDialog(frame, "您未输入搜索关键字！", "无法查找", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(pathField.getText().equals("") ||pathField.getText().equals("精确路径")){
					int res = JOptionPane.showConfirmDialog(frame, "您未指定查找路径，是否继续？", "是否继续", JOptionPane.OK_CANCEL_OPTION);
					if(res == JOptionPane.YES_OPTION){
						BasicSearch.SearchFile(pathField.getText(), keyField.getText());
					}
				}else{
					searching.setText("当前正在过滤文件...");
					value = 0;
					searchinfo.setVisible(true);
                    detailPanel.setVisible(false);
					resultListDisplay.removeAll();
					resultListDisplay.add(resultCategory);
					resultScroll.repaint();
					thread1 = new searchItemThread();
					thread2 = new progressBarThread();
					thread3 = new DisplayItemThread();
					searchThread = new Thread(new Runnable(){
                    	@Override
                    	public void run() {
                    		startVolumeTime = System.nanoTime();
							BasicSearch.SearchFile(pathField.getText(), keyField.getText());
                    	}
                    });
					freshSearchUI();
					thread1.start();
					thread2.start();
					thread3.start();
                    searchThread.start();
				}
				
			}

			private void freshSearchUI() {
				// TODO Auto-generated method stub
				if(searchThread.isAlive()){
					searchThread.destroy();
				}
				
				if(thread1.isAlive()){
					thread1.destroy();;
					}
				if(thread2.isAlive()){
				thread2.destroy();;
				}
				if(thread3.isAlive()){
				thread3.destroy();;
				}
				
				value  = 0;
				
				if(isVolumeGot){
				 searchinfo.remove(icon_time);
			        searchinfo.remove(icon_volume);
			        searchinfo.remove(infoTime);
			        searchinfo.remove(volumeUnit);
			        searchinfo.remove(infoVolume);
			        searchinfo.remove(second);
			        searchinfo.remove(volumeTitle);
			        searchinfo.remove(timeTitle);
					loadingicon.setVisible(true);
					loadingmessage.setVisible(true);
				}
				isVolumeGot = false;
			}

			
		});
        
        //设置开始按钮监听
        start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isPause){
					
					searchThread.resume();
					thread2.resume();
					searching.setText("当前正在过滤文件...");
					
					isPause = false;
					resumeSearch();
				}
				if(isStop){
					resultListDisplay.removeAll();
					searchNow.doClick();
					searching.setText("当前正在过滤文件...");
					isStop = false;
					resumeSearch();
				}
			}
		});
        
        //设置暂停按钮监听
        pause_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("----------------------------------"+searchThread.getState());
				if(searchThread.getState().toString() == "RUNNABLE"){
					timeUsed = (long) ((System.nanoTime()-startVolumeTime)/ 1.0e9);
					searchThread.suspend();
					thread2.suspend();	
					searching.setText("已暂停...");
					
					isPause = true;
					updateResultPanel();
				}
				
			}
		});
        
        //设置停止按钮监听
        stop_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(searchThread.getState().toString() == "RUNNABLE"){
					timeUsed = (long) ((System.nanoTime()-startVolumeTime)/ 1.0e9);
					searchThread.stop();
					thread1.stop();
					thread2.stop();	
					searchitem.setText("当前已经停止过滤文件");
					
					value = 0;
					searching.setText("已停止...");
					isStop = true;
					updateResultPanel();
				
				}
			}
		});
	}

	//更新搜索信息板块
	protected static void updateResultPanel() {
		// TODO Auto-generated method stub
		if(isVolumeGot || isStop ||isPause){
			
//        	searchinfo.remove(loadingicon);
//    		searchinfo.remove(loadingmessage);

    		loadingicon.setVisible(false);
    		loadingmessage.setVisible(false);
    		
    		icon_volume = new JLabel();
    		icon_time = new JLabel();
    		infoTime = new JLabel();
    		volumeUnit = new JLabel();
    		infoVolume = new JLabel();
    		second = new JLabel("S");
    		volumeTitle = new JLabel("过滤的目标文件总量");
    		timeTitle = new JLabel("搜索耗时");
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
    		
    		if(isVolumeGot){
    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolumeTime);
    			double newVolumeTime = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    			infoTime.setText(String.valueOf(newVolumeTime));
    			infoTime.setHorizontalAlignment(SwingConstants.RIGHT);
    			infoTime.setFont(getFont(25.0f));
    			infoTime.setForeground(new Color(38,82,179));
    		}
    		if(isStop||isPause){
	    		java.math.BigDecimal bigDecimal = new java.math.BigDecimal(timeUsed);
				double newVolumeTime = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	    		infoTime.setText(String.valueOf(newVolumeTime));
	    		infoTime.setHorizontalAlignment(SwingConstants.RIGHT);
	    		infoTime.setFont(getFont(25.0f));
	    		infoTime.setForeground(new Color(38,82,179));
	    	}


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
    	    
    	   
	}

	}
	
	//从暂停、停止到继续搜索的信息板块界面更新
	protected static void resumeSearch() {
		// TODO Auto-generated method stub
        searchinfo.remove(icon_time);
        searchinfo.remove(icon_volume);
        searchinfo.remove(infoTime);
        searchinfo.remove(volumeUnit);
        searchinfo.remove(infoVolume);
        searchinfo.remove(second);
        searchinfo.remove(volumeTitle);
        searchinfo.remove(timeTitle);
		loadingicon.setVisible(true);
		loadingmessage.setVisible(true);
	}
	
	//初始化时间线UI
	private static void initTimeLineUI() {
		// TODO Auto-generated method stub
        timeLinePanel.setLayout(null);
        timeLinePanel.setBounds(0,155,1200,645);
        Date date = new Date();
        Calendar c = Calendar.getInstance(); 
        Calendar c1 = Calendar.getInstance();
        int day=c.get(Calendar.DATE);
        int day1 = c1.get(Calendar.DATE);
        
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd");
        String presentDate = dateFormat.format(date);
        
        
        JPanel timeLineSearchPanel = new BackgroundPanel(image);
        JPanel fileRrepresentationPanel = new BackgroundPanel(new ImageIcon("bin/timelineBackground.png").getImage());
        JPanel otherFilePanel = new BackgroundPanel(new ImageIcon("bin/timelineBackground.png").getImage());
        JPanel detailPanel = new BackgroundPanel(resultbackground);
        JPanel fileRepresentTitle = new BackgroundPanel(resulttitle);
        JPanel otherFileTitle = new BackgroundPanel(resulttitle);
        JPanel musicTypeBackground = new BackgroundPanel(new ImageIcon("bin/typeBackground.png").getImage());
        JPanel wordTypeBackground = new BackgroundPanel(new ImageIcon("bin/typeBackground.png").getImage());
        JPanel videoTypeBackground = new BackgroundPanel(new ImageIcon("bin/typeBackground.png").getImage());
        JPanel picTypeBackground = new BackgroundPanel(new ImageIcon("bin/typeBackground.png").getImage());
        JPanel exeTypeBackground = new BackgroundPanel(new ImageIcon("bin/typeBackground.png").getImage());
        
        JPanel musicResultPanel = new JPanel();
        JPanel exeResultPanel = new JPanel();
        JPanel videoResultPanel = new JPanel();
        JPanel picResultPanel = new JPanel();
        JPanel wordResultPanel = new JPanel();
        
        JScrollPane musicScrollPane = new JScrollPane(musicResultPanel);
        JScrollPane exeScrollPane = new JScrollPane(exeResultPanel);
        JScrollPane picScrollPane = new JScrollPane(picResultPanel);
        JScrollPane wordScrollPane = new JScrollPane(wordResultPanel);
        JScrollPane videoScrollPane = new JScrollPane(videoResultPanel);
        
        
        musicTypeBackground.add(musicScrollPane);
        videoTypeBackground.add(videoScrollPane);
        exeTypeBackground.add(exeScrollPane);
        wordTypeBackground.add(wordScrollPane);
        picTypeBackground.add(picScrollPane);
        
        JSlider timeSlider = new JSlider(0,42,42);  
        JLabel timeLineTitleIcon = new JLabel();
        JLabel timeLineTitleText = new JLabel();
        JLabel otherFileTitleText = new JLabel("其他");
        JLabel presentText = new JLabel();
        JLabel choosenTime = new JLabel("选择时间 ",SwingConstants.CENTER);
        JLabel fileText = new JLabel("您要找的是...");
        JLabel word_icon = new JLabel();
        JLabel music_icon = new JLabel();
        JLabel pic_icon = new JLabel();
        JLabel selectType = new JLabel("请选择文件类型");
        JLabel video_icon = new JLabel();
        JLabel exe_icon = new JLabel();
        JComboBox<String> timeScaleBox = new JComboBox<String>();
        JComboBox<String> selectTypeBox = new JComboBox<String>();
        
        musicTypeBackground.setBounds(10, 150, 140, 100);
        picTypeBackground.setBounds(170, 150, 140, 100);
        wordTypeBackground.setBounds(330, 150, 140, 100);
        videoTypeBackground.setBounds(490, 150, 140, 100);
        exeTypeBackground.setBounds(650, 150, 140, 100);
        
        selectTypeBox.setFont(new Font("微软雅黑",Font.BOLD , 13));
        selectTypeBox.setBounds(160, 30, 200, 30);
        
        word_icon.setIcon(new ImageIcon("bin/word_icon.png"));
        word_icon.setBounds(360, 50, 80, 80);
        music_icon.setIcon(new ImageIcon("bin/music_icon.png"));
        music_icon.setBounds(40, 50, 80, 80);
        pic_icon.setIcon(new ImageIcon("bin/pic_icon.png"));
        pic_icon.setBounds(200, 50, 80, 80);
        video_icon.setIcon(new ImageIcon("bin/video_icon.png"));
        video_icon.setBounds(520, 50, 80, 80);
        exe_icon.setIcon(new ImageIcon("bin/exe_icon.png"));
        exe_icon.setBounds(680, 50, 80, 80);
       
        selectType.setBounds(30, 30, 150, 30);
        selectType.setFont(new Font("微软雅黑",Font.BOLD , 15));
        fileRepresentTitle.setLayout(null);
        otherFileTitle.setLayout(null);
        
        otherFileTitle.setLayout(null);
        otherFileTitle.setBounds(5, 5, 370, 20);
        otherFileTitleText.setBounds(10,0,30,20);
        otherFileTitleText.setFont(new Font("微软雅黑",Font.BOLD , 13));
        otherFileTitleText.setForeground(Color.white);
        fileText.setBounds(5, 0, 100, 20);
        fileText.setForeground(Color.white);
        fileText.setFont(new Font("微软雅黑",Font.BOLD , 13));
        
        choosenTime.setBounds(150, 35, 500, 30);
        choosenTime.setFont(new Font("微软雅黑",Font.BOLD , 15));
        
        timeLineTitleIcon.setBounds(10, 10, 30, 30);
        timeLineTitleIcon.setIcon(new ImageIcon("bin/clock.png"));
        
        timeLineTitleText.setBounds(40, 10, 200, 40);
        timeLineTitleText.setFont(new Font("微软雅黑",Font.BOLD , 15));
        timeLineTitleText.setText("请确定时间范围");
        
        presentText.setBounds(660, 60, 150, 20);
        presentText.setFont(new Font("微软雅黑",Font.BOLD , 15));
        presentText.setText("今天("+presentDate+")");
        
        timeScaleBox.addItem("过去一周");
        timeScaleBox.addItem("过去两周");
        timeScaleBox.addItem("过去一个月");
        timeScaleBox.addItem("过去三个月");
        timeScaleBox.addItem("过去半年");
        timeScaleBox.addItem("过去一年");
        timeScaleBox.addItem("过去三年");
        timeScaleBox.setFont(new Font("微软雅黑",Font.BOLD , 13));
        timeScaleBox.setFocusable(false);
        timeScaleBox.setBounds(30, 55, 100, 30);
        
        timeScaleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = timeScaleBox.getSelectedIndex();
				switch (index) {
				case 0:
					timeSlider.setMajorTickSpacing(6);
					choosenTime.setText("选择时间");
					break;
				case 1:
					timeSlider.setMajorTickSpacing(42);
					choosenTime.setText("选择时间");
					break;
				case 2:
					timeSlider.setMajorTickSpacing(21);
					choosenTime.setText("选择时间");
					break;
				case 3:
					timeSlider.setMajorTickSpacing(21);
					choosenTime.setText("选择时间");
					break;
				case 4:
					timeSlider.setMajorTickSpacing(42);
					choosenTime.setText("选择时间");
					break;
				case 5:
					timeSlider.setMajorTickSpacing(42);
					choosenTime.setText("选择时间");
					break;
				case 6:
					timeSlider.setMajorTickSpacing(21);
					choosenTime.setText("选择时间");
					break;

				default:
					break;
				}
			}
		});
        
        fileRrepresentationPanel.setLayout(null);
        fileRrepresentationPanel.setBounds(5,110,800,500);
        
        timeLineSearchPanel.setLayout(null);
        timeLineSearchPanel.setBounds(5,5,800,100);
        
        otherFilePanel.setLayout(null);
        otherFilePanel.setBackground(Color.WHITE);
        otherFilePanel.setBounds(810,5,380,300);
        detailPanel.setLayout(null);
        detailPanel.setBounds(810,310,380,300);
        
        timeSlider.setBounds(150,60,500,30);
        timeSlider.setFocusable(false);
        timeSlider.setPaintTicks(true);
        timeSlider.setMajorTickSpacing(6);
        timeSlider.setSnapToTicks(true);
        timeSlider.setOpaque(false);
        timeLineSearchPanel.add(timeSlider);
        
        fileRepresentTitle.setBounds(10,5,780, 20);

        otherFileTitle.add(otherFileTitleText);
        fileRepresentTitle.add(fileText);
        timeSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(!timeSlider.getValueIsAdjusting()){
					//过去一周的刻度
					if(timeScaleBox.getSelectedIndex() == 0){
						if(timeSlider.getValue() == 42){
							choosenTime.setText("今天 "+presentDate);
						}
						if(timeSlider.getValue() == 36){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-1); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("昨天 "+newDay);
						}
						if(timeSlider.getValue() == 30){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);  
							c.set(Calendar.DATE,day-2); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("前天 "+newDay);
						}
						if(timeSlider.getValue() == 24){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);  
							c.set(Calendar.DATE,day-3); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("大前天 "+newDay);
						}
						if(timeSlider.getValue() == 18){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-4); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("四天前 "+newDay);
						}
						if(timeSlider.getValue() == 12){
						    Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-5); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("五天前 "+newDay);
						}
						if(timeSlider.getValue() == 6){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-6); 
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("六天前 "+newDay);
						}
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-7); 
					        String newDay =new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
					        choosenTime.setText("七天前 "+newDay);
						}
						
					}
					//过去两周
					if(timeScaleBox.getSelectedIndex() == 1){
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-7);
							c1.set(Calendar.DATE,day-14);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("一周到两周前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-7);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("一周以内 "+newDay);
						}
					}
					//过去一个月
					if(timeScaleBox.getSelectedIndex() == 2){
						
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-20);
							c1.set(Calendar.DATE,day-30);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("20到30天前 "+newDay);
						}
						if(timeSlider.getValue() == 21){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-10);
							c1.set(Calendar.DATE,day-20);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("10到20天前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-10);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("10天内 "+newDay);
						}
						
					}
					//过去三个月
					if(timeScaleBox.getSelectedIndex() == 3){
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-60);
							c1.set(Calendar.DATE,day-90);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("2到3个月前 "+newDay);
						}
						if(timeSlider.getValue() == 21){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-30);
							c1.set(Calendar.DATE,day-60);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1到2个月前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-30);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1个月内 "+newDay);
						}
					}
					//过去半年
					if(timeScaleBox.getSelectedIndex() == 4){
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-90);
							c1.set(Calendar.DATE,day-180);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1个到2个季度前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-90);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1季度以内 "+newDay);
						}
					}
					//过去一年
					if(timeScaleBox.getSelectedIndex() == 5){
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-180);
							c1.set(Calendar.DATE,day-365);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("半年到一年前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-180);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("半年以内 "+newDay);
						}
					}
					//三年以内
					if(timeScaleBox.getSelectedIndex() == 6){
						if(timeSlider.getValue() == 0){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-730);
							c1.set(Calendar.DATE,day-1095);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("2年到3年前 "+newDay);
						}
						if(timeSlider.getValue() == 21){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-730);
							c1.set(Calendar.DATE,day-365);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1年到2年前 "+newDay);
						}
						if(timeSlider.getValue() == 42){
							Calendar c = Calendar.getInstance(); 
						    Calendar c1 = Calendar.getInstance();
						    int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day);
							c1.set(Calendar.DATE,day-365);
					        String newDay =new SimpleDateFormat("yyyy/MM/dd").format(c1.getTime())+" - "+new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()); 
					        choosenTime.setText("1年以内 "+newDay);
						}
					}
				}
				
			}
		});
        
        timeLineSearchPanel.add(timeLineTitleIcon);
        timeLineSearchPanel.add(timeScaleBox);
        timeLineSearchPanel.add(timeLineTitleText);
        timeLineSearchPanel.add(presentText);
        timeLineSearchPanel.add(choosenTime);
        
        fileRrepresentationPanel.add(fileRepresentTitle);
        fileRrepresentationPanel.add(word_icon);
        fileRrepresentationPanel.add(music_icon);
        fileRrepresentationPanel.add(pic_icon);
        fileRrepresentationPanel.add(exe_icon);
        fileRrepresentationPanel.add(video_icon);
        fileRrepresentationPanel.add(musicTypeBackground);
        fileRrepresentationPanel.add(videoTypeBackground);
        fileRrepresentationPanel.add(picTypeBackground);
        fileRrepresentationPanel.add(exeTypeBackground);
        fileRrepresentationPanel.add(wordTypeBackground);
        
        otherFilePanel.add(otherFileTitle);
        otherFilePanel.add(selectType);
        otherFilePanel.add(selectTypeBox);
        
        
        timeLinePanel.add(timeLineSearchPanel);
        timeLinePanel.add(fileRrepresentationPanel);
        timeLinePanel.add(otherFilePanel);
        timeLinePanel.add(detailPanel);
        
        
        
        frame.add(timeLinePanel);
	}

}
