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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.text.Utilities;

import org.omg.CORBA.PUBLIC_MEMBER;


public class MainUI{
	
    //�����ʾ���ĳ���
    static int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    //�趨���ڳ���
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
    static JTextField searchbar=new JTextField("�������ѯ�ؼ��֣��ļ�������չ����"); 
    static JButton Button_search = new JButton();
    static JButton Button_funtion1 = new JButton("ʱ���߲���",icon);
    static JButton Button_funtion2 = new JButton("��������",iconnull);
    static JButton Button_funtion3 = new JButton("��ȷ����",iconnull);
    
    static Thread searchThread = new Thread();
    static Thread thread1,thread2,thread3;
    static JLabel usericon = new JLabel();
    static JLabel loadingicon;
    static JLabel loadingmessage;

    static JComboBox comboBox=new JComboBox();
    
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
          
        frame.setLayout(null);  //���ɲ���  
        frame.setBounds((ScreenWidth - windowsWidth) / 2, (ScreenHeight - windowsHeight) / 2, windowsWidth, windowsHeight);  //�ߴ�С  
        frame.setResizable(false);  
        frame.setTitle("Project DEMO");  
         
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
        
        //�����ӵ�������  
        frame.add(contentpanel); 
        frame.add(tabpanel);
        frame.add(tabblue);
        frame.add(userpanel);
 
        //���ùرմ���ʱ�˳�Ӧ�ó���  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  //��ʾ����  
	}
	
    public static void main(String[] args) throws FileNotFoundException, IOException{
		
		//��ʼ��UI
		initUI();
		initButton();
		initTimeLineUI();
		initSingleFileUI();
		initSearchUI();
	}
	
	private static void initTimeLineUI() {
		// TODO Auto-generated method stub
        timeLinePanel.setLayout(null);
        timeLinePanel.setBounds(0,155,1200,645);
        
        frame.add(timeLinePanel);

	}

	private static void initSingleFileUI() {
		// TODO Auto-generated method stub
		 singleFilePanel.setLayout(null);
	     singleFilePanel.setBounds(0,155,1200,645);
	        
	     frame.add(singleFilePanel);
	}

	

	private static void initButton() {
		// TODO Auto-generated method stub
        //����Tab:������ȥ������
        Button_funtion1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Button_funtion3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Button_funtion1.setBorderPainted(false);
        Button_funtion1.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion1.setBackground(null);
        Button_funtion1.setForeground(Color.white);
        Button_funtion1.setFont(new Font("����", Font.BOLD, 13));

        Button_funtion2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        Button_funtion2.setForeground(new Color(38,82,179));
        Button_funtion2.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion2.setBackground(null);
        Button_funtion2.setBorderPainted(false);
       
        Button_funtion3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        Button_funtion3.setHorizontalTextPosition(SwingConstants.CENTER);
        Button_funtion3.setForeground(new Color(38,82,179));
        Button_funtion3.setBackground(null);
        Button_funtion3.setBorderPainted(false);
        
        tabpanel.add(Button_funtion1);
        tabpanel.add(Button_funtion2);
        tabpanel.add(Button_funtion3);
        
		//���������������ʾ
		searchbar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			 searchbar.setText("");}
	    });
		
		//�س���������
		searchbar.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				if(e.getKeyCode() == KeyEvent.VK_ENTER) //�жϰ��µļ��Ƿ��ǻس���
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
                if (keyword.isEmpty()||keyword.equals("�������ѯ�ؼ��֣��ļ�������չ����")||keyword.equals("��ʾ��δ����ؼ���")){
                	System.out.println("Search Key Word Empty!");
                	searchbar.setText("��ʾ��δ����ؼ���");
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
				//��Button�ķ�ʽʵ�ֵ��Ч��
				System.out.println("Function 1");
				Button_funtion1.setIcon(icon);
		        Button_funtion1.setForeground(Color.white);
		        Button_funtion1.setFont(new Font("����", Font.BOLD, 13));
		        
		        Button_funtion2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
 		        Button_funtion2.setIcon(iconnull);
 		        Button_funtion2.setForeground(new Color(38,82,179));
 		        
		        Button_funtion3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
 		        Button_funtion3.setIcon(iconnull);
		        Button_funtion3.setForeground(new Color(38,82,179));
		        

 		        //��Tab1���ر�����Tab
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
 		        Button_funtion2.setFont(new Font("����", Font.BOLD, 15));
 		        
 		        Button_funtion1.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
 		        Button_funtion3.setForeground(new Color(38,82,179));	        
 		        Button_funtion3.setIcon(iconnull);
 		        
 		        //��Tab1���ر�����Tab
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
 		        Button_funtion3.setFont(new Font("����", Font.BOLD, 15));
 		        
 		        Button_funtion1.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                Button_funtion1.setForeground(new Color(38,82,179));
 		        Button_funtion1.setIcon(iconnull);

 		        Button_funtion2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
 		        Button_funtion2.setForeground(new Color(38,82,179));        
 		        Button_funtion2.setIcon(iconnull);
 		        
 		        //��Tab1���ر�����Tab
 		        timeLinePanel.setVisible(false);
 		        singleFilePanel.setVisible(false);
 		        searchpanel.setVisible(true);
 			}
 		});
        
        
	}

	//����ͷ���ı�����ɫ
	public static class BackgroundPanel extends JPanel {  
		
		private static final long serialVersionUID = -6352788025440244338L; 
        private Image image = null;  
  
        public BackgroundPanel(Image image) {  
                 this.image = image;  
        }  
           
             // �̶�����ͼƬ���������JPanel������ͼƬ������������  
        protected void paintComponent(Graphics g) {  
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
        }  
	}
	
	//����ͷ���̱����
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
	
	//�������
	public static Font getFont(float size) {  
        String pathString = "bin/��������DIN1451.ttf";
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
	
	//��ʼ����ȷ����UI
	private static void initSearchUI(){
		//��ȡFont
		
		JPanel searchprogress = new BackgroundPanel(image);
		searchinfo = new JPanel();
		JPanel resultPanel = new BackgroundPanel(resultbackground);
		JPanel resultTitle = new BackgroundPanel(resulttitle);
		JPanel resultItem = new JPanel();
		JPanel resultCategory = new JPanel();
		JPanel resultListDisplay = new JPanel();
		JPanel pathPanel = new BackgroundPanel(image);
		JScrollPane resultScroll = new JScrollPane(resultListDisplay);
		JProgressBar progressBar = new JProgressBar();
		JLabel searching = new JLabel("��������������ؼ��֣���ѡ��·���������ʼ��ť����...");
		JLabel searchitem = new JLabel("��ǰ������");
		loadingicon = new JLabel();
		loadingmessage = new JLabel("����Ѱ����Դ������Ҫ����һ��ʱ��...");
		JLabel resultlist = new JLabel("��ѯ���");
		JLabel noResult = new JLabel("���޽��...");
		JLabel start_label = new JLabel("��ʼ/����",SwingConstants.CENTER);
		JLabel pause_label = new JLabel("��ͣ",SwingConstants.CENTER);
		JLabel stop_label = new JLabel("ֹͣ",SwingConstants.CENTER);
		JLabel key_label = new JLabel("��ǰ�ؼ���",SwingConstants.LEFT);
		JLabel path_label = new JLabel("��ǰ·��",SwingConstants.LEFT);
		JButton pause_button = new JButton(new ImageIcon("bin/pause_button.png"));
		JButton start_button = new JButton(new ImageIcon("bin/start_button.png"));
		JButton stop_button = new JButton(new ImageIcon("bin/stop_button.png"));
		JButton cate_Num = new JButton("���");
		JButton cate_Name = new JButton("��Դ����");
		JButton cate_Suffix = new JButton("��չ��");
		JButton cate_Path = new JButton("·��");
		JButton openfile = new JButton("...",new ImageIcon("bin/filechooser.png"));
		JButton deleteAll = new JButton("���");
		JButton searchNow = new JButton("��ʼ����");
		JFileChooser fileChooser = new JFileChooser();
		JTextField keyField = new JTextField("������ؼ���");
		JTextField pathField = new JTextField("��ȷ·��");
		searching.setFont(new Font("΢���ź�", Font.BOLD, 15));
		searching.setBounds(20,10,500,20);
	
		searchinfo.setVisible(false);
		
		deleteAll.setBounds(10, 65, 180, 25);
		searchNow.setBounds(195, 65, 180, 25);
		
		pathPanel.setLayout(null);
		pathPanel.setBounds(800, 110, 385, 95);

		key_label.setBounds(10, 10, 80, 20);
		key_label.setFont(new Font("΢���ź�", Font.BOLD, 13));
		path_label.setBounds(10, 35, 80, 20);
		path_label.setFont(new Font("΢���ź�", Font.BOLD, 13));
		
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
        searchinfo.setBounds(800, 210, 385, 100);
        searchinfo.setBackground(new Color(255, 255, 255));
        loadingicon.setIcon(new ImageIcon("bin/loading.gif"));
        loadingicon.setBounds(5,5,80,80);
        loadingmessage.setBounds(100,40,300,20);
        loadingmessage.setFont(new Font("΢���ź�", Font.BOLD, 13));
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

        start_label.setFont(new Font("΢���ź�", Font.BOLD, 12));
        pause_label.setFont(new Font("΢���ź�", Font.BOLD, 12));
        stop_label.setFont(new Font("΢���ź�", Font.BOLD, 12));
        
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
        
        //ѡ����ȷλ�õ�pathPanel
		openfile.setBounds(335,35,40,20);
		openfile.setFont(new Font("΢���ź�", Font.BOLD, 10));
		
		pathPanel.add(openfile);
		pathPanel.add(pathField);
		pathPanel.add(keyField);
		pathPanel.add(key_label);
		pathPanel.add(path_label);
		pathPanel.add(deleteAll);
		pathPanel.add(searchNow);

		//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		
        //��ʾ��������Ľ���
        resultPanel.setLayout(null);
        resultPanel.setBounds(5, 110, 785, 500);
        resultTitle.setLayout(null);
        resultlist.setForeground(new Color(255, 255, 255));
        resultlist.setFont(new Font("΢���ź�", Font.PLAIN, 13));
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
        //��Ϣ����������
        cate_Name.setFont(new Font("����",Font.PLAIN,13));
        cate_Num.setFont(new Font("����",Font.PLAIN,13));
        cate_Path.setFont(new Font("����",Font.PLAIN,13));
        cate_Suffix.setFont(new Font("����",Font.PLAIN,13));
        
        
        
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
        searchpanel.add(pathPanel);
        searchpanel.add(searchinfo);
        searchpanel.add(resultPanel);
        
        frame.add(searchpanel);

        searchpanel.setVisible(false);
        
        keyField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			 if(keyField.getText().equals("������ؼ���")){
				 keyField.setText("");
			 }}
	    });
        
        //����ļ��жԻ���ļ���
        openfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("��ѡ��·��");
				fileChooser.showDialog(new JLabel(), "ѡ��");
				File file = fileChooser.getSelectedFile();
				pathField.setText(file.getAbsolutePath());
				
			}
		});
        
        //�����ť�ļ���
        deleteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				keyField.setText("");
				pathField.setText("");
			}
		});
        
      
        
        //����searchitem���߳�
        Thread searchItemThread = new Thread(new Runnable(){
        	
            @Override
            public void run() {   
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
                                    //���²���ͨ���¼��ɷ��߳���ɣ�һ��ʵ��Runnable()�ӿڣ�  
                                	searchitem.setText(BasicSearch.currentItem); 
                                	
                                }  
                            }); 
                			}
                		
                }
            }   	
        });
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
                                         //���²���ͨ���¼��ɷ��߳���ɣ�һ��ʵ��Runnable()�ӿڣ�  
                                     	searchitem.setText(BasicSearch.currentItem); 
                                     	
                                     }  
                                 }); 
                     			}
                     		
                     }
        		}
               
            }
        }
//        searchItemThread.start();
        
        
        
//        //�������������Ϣ�����߳�
//        Thread resultPanelThread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if(isVolumeGot || isStop ||isPause){
//	
//                	searchinfo.remove(loadingicon);
//		    		searchinfo.remove(loadingmessage);
//		    		
//		    		JLabel icon_volume = new JLabel();
//		    		JLabel icon_time = new JLabel();
//		    		JLabel infoTime = new JLabel();
//		    		JLabel volumeUnit = new JLabel();
//		    		JLabel infoVolume = new JLabel();
//		    		JLabel second = new JLabel("S");
//		    		JLabel volumeTitle = new JLabel("��ѯ��Ŀ���ļ�����");
//		    		JLabel timeTitle = new JLabel("������ʱ");
//		    		System.out.println("____________"+searchVolume);
//		    		System.out.println(searchVolumeTime);
//		    		//������������ʱ�䲢���ж�������λ
//		    		
//		    		//����С��1024KB=1MB
//		    		if(searchVolume < 1024.0){
//		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
//		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
//		        		infoVolume.setText(String.valueOf(newVolume));
//		        		volumeUnit.setText("KB");
//		    		}
//		    		//����С��1024MB=1GB
//		    		if(searchVolume >= 1024.0 && searchVolume < 1024.0*1024){
//		    			searchVolume = searchVolume/1024.0;
//		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
//		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
//		        		infoVolume.setText(String.valueOf(newVolume));
//		        		volumeUnit.setText("MB");
//		    		}
//		    		//��������1GB
//		    		if(searchVolume >= 1024.0*1024){
//		    			searchVolume = searchVolume/(1024.0*1024);
//		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
//		    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
//		        		infoVolume.setText(String.valueOf(newVolume));
//		        		volumeUnit.setText("GB");
//		    		}
//		    		volumeTitle.setBounds(65, 15, 150, 20);
//		    		volumeTitle.setFont(new Font("΢���ź�", Font.BOLD, 13));
//		    		timeTitle.setBounds(265, 15, 150, 20);
//		    		timeTitle.setFont(new Font("΢���ź�", Font.BOLD, 13));
//		    		
//		    		if(isVolumeGot){
//		    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolumeTime);
//		    			double newVolumeTime = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
//		    			infoTime.setText(String.valueOf(newVolumeTime));
//		    			infoTime.setHorizontalAlignment(SwingConstants.RIGHT);
//		    			infoTime.setFont(getFont(25.0f));
//		    			infoTime.setForeground(new Color(38,82,179));
//		    		}
//		    		if(isStop||isPause){
//			    		java.math.BigDecimal bigDecimal = new java.math.BigDecimal(timeUsed);
//						double newVolumeTime = bigDecimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
//			    		infoTime.setText(String.valueOf(newVolumeTime));
//			    		infoTime.setHorizontalAlignment(SwingConstants.RIGHT);
//			    		infoTime.setFont(getFont(25.0f));
//			    		infoTime.setForeground(new Color(38,82,179));
//			    	}
//
//
//		    		volumeUnit.setFont(getFont(15.0f));
//		    		second.setFont(getFont(15.0f));
//		    		second.setForeground(new Color(38,82,179));
//		    		volumeUnit.setForeground(new Color(38,82,179));
//
//		    		infoVolume.setFont(getFont(25.0f));
//		    		infoVolume.setForeground(new Color(38,82,179));
//		    		infoVolume.setHorizontalAlignment(SwingConstants.RIGHT);
//		    		icon_time.setIcon(new ImageIcon("bin/timeicon.png"));
//		    		icon_volume.setIcon(new ImageIcon("bin/volumeicon.png"));
//		    		icon_volume.setBounds(20,5,50,50);
//		    		icon_time.setBounds(220, 5, 50, 50);
//		    		infoVolume.setBounds(60,20 , 100, 80);
//		    		volumeUnit.setBounds(170, 40, 60, 50);
//		    		infoTime.setBounds(265, 20, 80, 80);
//		    		second.setBounds(355,40,60,50);
//		    		
//		    		searchinfo.add(icon_volume);
//		    		searchinfo.add(icon_time);
//		    		searchinfo.add(infoTime);
//		    		searchinfo.add(volumeUnit);
//		    		searchinfo.add(infoVolume);
//		    		searchinfo.add(second);
//		    		searchinfo.add(volumeTitle);
//		    		searchinfo.add(timeTitle);
//		    		
//		    	    searchpanel.repaint();
//		    	    
//		    	   
//                }
//		
//				
//			}
//			
//		});
       
        
        //����progressbar��������������ʱ����߳�
        Thread progressBarThread = new Thread(new Runnable(){
        	
            @Override
            public void run() {
            

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
                    
            	}while(isVolumeGot){
                	progressBar.setValue(100);
                	searching.setText("��ѯ�����");
                	updateResultPanel();
                	try {
						Thread.sleep(Long.MAX_VALUE);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                }
                
                }	
               	
        });
//        progressBarThread.start(); 
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
                    	searching.setText("��ѯ�����");
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
      
        
        //����������������߳�
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
                                //���²���ͨ���¼��ɷ��߳���ɣ�һ��ʵ��Runnable()�ӿڣ�  
                            		while(BasicSearch.resultlist.size() == 0){
                            			noResult.setBounds(0, 50, 100, 100);
                            			resultListDisplay.add(noResult);
//                                        resultScroll.validate();
//                                       	resultScroll.repaint();
                                       	break;
                            		}
                            		while(BasicSearch.resultlist.size() != 0){
                            			resultListDisplay.removeAll();
                            			resultListDisplay.add(resultCategory);
                            			resultScroll.repaint();
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
//                               		 		fileSequence.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                               		 		fileName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                               		 		fileName.setForeground(new Color(0, 0, 139));
                               		 		fileSuffix.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                               		 		filePath.setFont(new Font("΢���ź�", Font.PLAIN, 13));
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
														fileName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
													}
											
													
												}
												
												@Override
												public void mouseEntered(MouseEvent e) {
													// TODO Auto-generated method stub
										            if(fileName.getForeground() != Color.gray){
														fileName.setForeground(Color.BLUE);
														fileName.setFont(new Font("΢���ź�", Font.PLAIN, 14));
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
//        DisplayItemThread.start();
        
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
                                     //���²���ͨ���¼��ɷ��߳���ɣ�һ��ʵ��Runnable()�ӿڣ�  
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
//                                    		 		fileSequence.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                                    		 		fileName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                                    		 		fileName.setForeground(new Color(0, 0, 139));
                                    		 		fileSuffix.setFont(new Font("΢���ź�", Font.PLAIN, 13));
                                    		 		filePath.setFont(new Font("΢���ź�", Font.PLAIN, 13));
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
     														fileName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
     													}
     											
     													
     												}
     												
     												@Override
     												public void mouseEntered(MouseEvent e) {
     													// TODO Auto-generated method stub
     										            if(fileName.getForeground() != Color.gray){
     														fileName.setForeground(Color.BLUE);
     														fileName.setFont(new Font("΢���ź�", Font.PLAIN, 14));
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
               
               
            }
        }
      //��ʼ���Ұ�ť�ļ���
        searchNow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(keyField.getText().equals("") ||keyField.getText().equals("������ؼ���")){
					JOptionPane.showMessageDialog(frame, "��δ���������ؼ��֣�", "�޷�����", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(pathField.getText().equals("") ||pathField.getText().equals("��ȷ·��")){
					int res = JOptionPane.showConfirmDialog(frame, "��δָ������·�����Ƿ������", "�Ƿ����", JOptionPane.OK_CANCEL_OPTION);
					if(res == JOptionPane.YES_OPTION){
						BasicSearch.SearchFile(pathField.getText(), keyField.getText());
					}
				}else{
					searching.setText("��ǰ���ڹ����ļ�...");
					value = 0;
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
				isVolumeGot = false;

				
			}

			
		});
        
        //���ÿ�ʼ��ť����
        start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isPause){
					
					searchThread.resume();
					thread2.resume();
					searching.setText("��ǰ���ڹ����ļ�...");
					
					isPause = false;
					resumeSearch();
				}
				if(isStop){
					resultListDisplay.removeAll();
					searchNow.doClick();
					searching.setText("��ǰ���ڹ����ļ�...");
					isStop = false;
					resumeSearch();
				}
			}
		});
        //������ͣ��ť����
        pause_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("----------------------------------"+searchThread.getState());
				if(searchThread.getState().toString() == "RUNNABLE"){
					timeUsed = (long) ((System.nanoTime()-startVolumeTime)/ 1.0e9);
					searchThread.suspend();
					thread2.suspend();	
					searching.setText("����ͣ...");
					
					isPause = true;
					updateResultPanel();
				}
				
			}
		});
        //����ֹͣ��ť����
        stop_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(searchThread.getState().toString() == "RUNNABLE"){
					timeUsed = (long) ((System.nanoTime()-startVolumeTime)/ 1.0e9);
					searchThread.stop();
					thread1.stop();
					thread2.stop();	
					searchitem.setText("��ǰ�Ѿ�ֹͣ�����ļ�");
					
					value = 0;
					searching.setText("��ֹͣ...");
					isStop = true;
					updateResultPanel();
				
				}
			}
		});
	}






	protected static void updateResultPanel() {
		// TODO Auto-generated method stub
		if(isVolumeGot || isStop ||isPause){
			
//        	searchinfo.remove(loadingicon);
//    		searchinfo.remove(loadingmessage);

    		loadingicon.setVisible(false);
    		loadingmessage.setVisible(false);
    		
    		JLabel icon_volume = new JLabel();
    		JLabel icon_time = new JLabel();
    		JLabel infoTime = new JLabel();
    		JLabel volumeUnit = new JLabel();
    		JLabel infoVolume = new JLabel();
    		JLabel second = new JLabel("S");
    		JLabel volumeTitle = new JLabel("��ѯ��Ŀ���ļ�����");
    		JLabel timeTitle = new JLabel("������ʱ");
    		System.out.println("____________"+searchVolume);
    		System.out.println(searchVolumeTime);
    		//������������ʱ�䲢���ж�������λ
    		
    		//����С��1024KB=1MB
    		if(searchVolume < 1024.0){
    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        		infoVolume.setText(String.valueOf(newVolume));
        		volumeUnit.setText("KB");
    		}
    		//����С��1024MB=1GB
    		if(searchVolume >= 1024.0 && searchVolume < 1024.0*1024){
    			searchVolume = searchVolume/1024.0;
    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        		infoVolume.setText(String.valueOf(newVolume));
        		volumeUnit.setText("MB");
    		}
    		//��������1GB
    		if(searchVolume >= 1024.0*1024){
    			searchVolume = searchVolume/(1024.0*1024);
    			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(searchVolume);
    			double newVolume = bigDecimal.setScale(1, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        		infoVolume.setText(String.valueOf(newVolume));
        		volumeUnit.setText("GB");
    		}
    		volumeTitle.setBounds(65, 15, 150, 20);
    		volumeTitle.setFont(new Font("΢���ź�", Font.BOLD, 13));
    		timeTitle.setBounds(265, 15, 150, 20);
    		timeTitle.setFont(new Font("΢���ź�", Font.BOLD, 13));
    		
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
	protected static void resumeSearch() {
		// TODO Auto-generated method stub
//		searchinfo.repaint();
		

	}
	
}
