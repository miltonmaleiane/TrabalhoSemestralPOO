package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import comuns.DataBaseConnection;
import comuns.PainelPrincipal;
import comuns.PainelPesquisa;
import comuns.TimeUtil;
import comuns.PainelUsuario;
import curso.PainelCurso;
import disciplina.PainelAtribuirDisciplina;
import disciplina.PainelDisciplina;
import docente.PainelDocente;
import docente.PainelVerDocente;
import estudante.PainelVerFaltas;
import estudante.PainelIntroduzirNotas;
import estudante.PainelMarcarFaltas;
import estudante.PainelNotas;
import estudante.PainelReportarNotas;
import estudante.PainelEstudante;
import estudante.PainelVerEstudante;
import login.PaginaLogin;
import papo.DadosChat;
import papo.ChatPainelPrincipal;

/*
 
 */
@SuppressWarnings("serial")
public class AdminPrincipal extends JFrame  implements ActionListener
{
//UEM LANDZA
	
	public JPanel contentPane;
	private JLabel nomeEscolalabel;
	private JLabel profilepiclabel;
	private JPanel profilepanel;
	private JButton homebutton;
	private JButton btCurso;
	private JButton btEstudante;
	private JButton btDisciplina;
	private JButton btDocente;
	private JButton btUsuario;
	private JButton btIntroduzirNotas;
	private JButton assignsubjectbutton;
	private JButton markattandancebutton;
	private JButton attandancereportbutton;
	private JButton searchbutton;
	private JButton exitbutton;
	private JButton btn;
	private JButton btPerfilAdmin;
	
	private Color buttonbcolor=Color.DARK_GRAY;
	private Color buttonfcolor=Color.LIGHT_GRAY;
	private Font buttonfont=new Font("Tw Cen MT", Font.PLAIN, 20);
	private PainelCurso painelCurso;
	private PainelDisciplina painelDisciplina;
	private PainelPrincipal painelPrincipal;
	
	public PainelEstudante painelEstudante;
	public PainelVerEstudante viewstudentpanel;
	public PainelNotas marksheetpanel;
	public JScrollPane marksheetpanelscroll;
	public PainelVerDocente viewfacultypanel;
	public PainelAtribuirDisciplina assignsubjectpanel;
	public PainelIntroduzirNotas entermarkspanel;
	public JScrollPane entermarkspanelscroll;
	private PainelMarcarFaltas markattandancepanel;
	private JScrollPane markattandancepanelscroll;
	public PainelVerFaltas attandancereportpanel;
	public JScrollPane attandancereportpanelscroll;
	public PainelReportarNotas marksheetreportpanel;
	public JScrollPane marksheetreportpanelscroll;
	public PainelDocente facultypanel;
	public AdminPainelPerfil adminprofilepanel;
	public PainelPesquisa searchpanel;
	public ChatPainelPrincipal chatmainpanel;
	public PainelUsuario userspanel;
	
	public int panely=-5,panelx=175;
	
	private Admin a;
	private String lastlogin;
	private JButton chatbutton;
	private int row=0;
	private JButton logoutbutton;
	private Timer timer;
	private JButton marksheetreportbutton;
	private JLabel totalnewchatmessage;
	private Image messagecount;
	private int chat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					if(DataBaseConnection.checkconnection())
					{
						AdminPrincipal frame = new AdminPrincipal();
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						frame.show();
						

					
					    frame.setVisible(true);
					    
					    
					}
					else
					{
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JOptionPane.showMessageDialog(null, "Conecte a Base de Dados","Erro",JOptionPane.ERROR_MESSAGE);
					}
					new Thread().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPrincipal() {
	
		
		 a=new AdminDados().getAdminData();
		 ActionListener setActive=new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					int result=new AdminDados().setActiveStatus(a.getActiveStatus());
					
					if(result>0)
					{
						chat=new DadosChat().getUndreadMessageCountAdmin();
						if(chat>0)
						{
							totalnewchatmessage.setText(chat>999?"999+":chat+"");
							totalnewchatmessage.setVisible(true);
							totalnewchatmessage.setIcon(new ImageIcon(messagecount.getScaledInstance(26+totalnewchatmessage.getText().length(), 26, Image.SCALE_SMOOTH)));
						}
						else 
						{
							totalnewchatmessage.setVisible(false);
						}
					}
					
				}
				
			};
			timer=new Timer(2000,setActive);
			timer.start();
			
		Color bgColor =new Color(32,178,170);
		Color frColor=Color.white;

		UIManager.put("ComboBoxUI", "com.sun.java.swing.plaf.windows.WindowsComboBoxUI");
	    UIManager.put("ComboBox.selectionBackground", new ColorUIResource(bgColor));
	    UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
	    UIManager.put("ComboBox.foreground", new ColorUIResource(Color.DARK_GRAY));
	    UIManager.put("ComboBox.selectionForeground", new ColorUIResource(frColor));
	    UIManager.put("ScrollBarUI", "com.sun.java.swing.plaf.windows.WindowsScrollBarUI");
	  
	    try
		{
			messagecount=ImageIO.read(new File("./assets/messagecount.png"));
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
		this.setResizable(false);
		setTitle("Sistema de Gestão Academica");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(12, 69, 86));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setBounds(0,0,1370,733);

		 profilepanel = new JPanel();
		 profilepanel.setBounds(5, 7, 170, 63);
		 contentPane.add(profilepanel);
		 profilepanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		 profilepanel.setBackground(new Color(12, 69, 86));
		 profilepanel.setLayout(null);
		 
		 
		 nomeEscolalabel = new JLabel();
		 nomeEscolalabel.setForeground(Color.WHITE);
		 nomeEscolalabel.setHorizontalAlignment(SwingConstants.LEFT);
		 nomeEscolalabel.setFont(new Font("Tw Cen MT", Font.BOLD, 21));
		 nomeEscolalabel.setBackground(new Color(12, 69, 86) );
		 nomeEscolalabel.setText("Faculdade");
		 nomeEscolalabel.setOpaque(true);
		 nomeEscolalabel.setBounds(60, 5, 140, 36);
		 profilepanel.add(nomeEscolalabel);
		 
		 profilepiclabel = new JLabel();
		 profilepiclabel.setBounds(5, 0, 50, 50);
		 profilepanel.add(profilepiclabel);
		 profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		 profilepiclabel.setBackground(new Color(12, 69, 86));
		 profilepiclabel.setBorder(new LineBorder(Color.black, 0));
		 profilepiclabel.setOpaque(true);
		 
		 createHomepanel();
		
		
		//creating side bar panel
		
		JPanel sidebarpanel = new JPanel();
		sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(64, 64, 64)));
		sidebarpanel.setBackground (new Color (12, 69, 86));
		sidebarpanel.setBounds(0, 72, 170, 820);
		contentPane.add(sidebarpanel);
		sidebarpanel.setLayout(null);
		
		//Adding buttons to sidebar
		homebutton =createButton("Inicio");
		sidebarpanel.add(homebutton);
		btn=homebutton;
		
		btCurso = createButton("Cursos");
		sidebarpanel.add(btCurso);
		
		btEstudante =createButton("Estudantes");
		sidebarpanel.add(btEstudante);
		
		btDisciplina = createButton("Disciplinas");
		sidebarpanel.add(btDisciplina);
		
		btDocente = createButton("Docentes");
		sidebarpanel.add(btDocente);
		
		assignsubjectbutton = createButton("D-Disciplina");
		sidebarpanel.add(assignsubjectbutton);
		
		btIntroduzirNotas = createButton("Lançar Notas");
		sidebarpanel.add(btIntroduzirNotas);
		
		marksheetreportbutton = createButton("Ver Notas");
		sidebarpanel.add(marksheetreportbutton);
		
		markattandancebutton = createButton("Marcar Presenças");
		sidebarpanel.add(markattandancebutton);
		
		attandancereportbutton =createButton("Ver Presenças");
		sidebarpanel.add(attandancereportbutton);
		
		//chatbutton = createButton("Chat");
		//chatbutton.setLayout(new BorderLayout());
		//sidebarpanel.add(chatbutton);
		//chat=new ChatData().getUndreadMessageCountAdmin();
		//totalnewchatmessage=new JLabel();
		//totalnewchatmessage.setSize(60,30);
		//totalnewchatmessage.setFont(new Font("Arial",Font.BOLD,12));
		//totalnewchatmessage.setForeground(Color.white);
		//totalnewchatmessage.setHorizontalTextPosition(JLabel.CENTER);
	    //totalnewchatmessage.setVerticalTextPosition(JLabel.CENTER);
		//chatbutton.add(totalnewchatmessage,BorderLayout.LINE_END);
		//if(chat>0)
		{
			//totalnewchatmessage.setText(chat>999?"999+":chat+"");
		//	totalnewchatmessage.setVisible(true);
			//totalnewchatmessage.setIcon(new ImageIcon(messagecount.getScaledInstance(26+totalnewchatmessage.getText().length(), 26, Image.SCALE_SMOOTH)));
		}
//		ActionListener refreshchat=e->
//		{
//		
//		};
//		Timer activechattimer=new Timer(2000,refreshchat);
//		activechattimer.start();
//		
		searchbutton = createButton("Pesquisar");
		sidebarpanel.add(searchbutton);
		
		btUsuario = createButton("Usuários");
		sidebarpanel.add(btUsuario);
		btUsuario.setToolTipText("Todos Usários do Sistema");
		
		btPerfilAdmin = createButton("Sobre Nós","Profile");
		sidebarpanel.add(btPerfilAdmin);

		logoutbutton = createButton("Terminar sessão");
		sidebarpanel.add(logoutbutton);
		logoutbutton.setToolTipText("Encera todas actvidades nesta conta e volta para o Login");
		
		exitbutton = createButton("Sair");
		sidebarpanel.add(exitbutton);
		exitbutton.setToolTipText("Encerar todas actividades e fecha a aplicação");
		activeButton(homebutton);
		painelPrincipal.setVisible(true);
		
		this.setCollageDetails();
		lastlogin=a.getLastLogin();
		painelPrincipal.setLastLogin(lastlogin);
		a.setLastLogin(TimeUtil.getCurrentTime());
		a.setActiveStatus(true);
		new AdminDados().updateAdminDetails(a);
		
		
	        this.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(final WindowEvent windowenent) {
	            	openPanel(exitbutton);
	            	
	            }
	        });
	        
	        
		
	}
	public void createHomepanel()
	{
		painelPrincipal=new PainelPrincipal(a);
		painelPrincipal.setLocation(panelx,panely);
		painelPrincipal.setFocusable(true);
		painelPrincipal.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.add(painelPrincipal);
	}
	
	public void activeButton(JButton button)
	{
		btn.setBackground(buttonbcolor);
		btn.setForeground(buttonfcolor);
		btn.setFont(buttonfont);
		btn.setDisabledIcon(new ImageIcon(""));
		btn.setIcon(new ImageIcon("./assets/"+btn.getName()+"dac.png"));
		btn=button;
		btn.setForeground(Color.white);
		btn.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btn.setIcon(new ImageIcon("./assets/"+btn.getName()+"ac.png"));
		disablepanel();
	}
	
	public JButton createButton(String text,String name)
	{
		JButton button=createButton(text);
		button.setName(name);
		button.setIcon(new ImageIcon("./assets/"+button.getName()+"dac.png"));
		return button;
	}
	public JButton createButton(String text)
	{
		JButton button=new JButton();
		button.setForeground(buttonfcolor);
		button.setFont(buttonfont);
		button.setBackground(buttonbcolor);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(new EmptyBorder(0,0,0,0));
		button.setText(text);
		button.setName(text);
		button.setIcon(new ImageIcon("./assets/"+button.getName()+"dac.png"));
		button.addActionListener(this);
		button.setIconTextGap(10);
		button.setLocation(0, row);
		button.setSize(234, 40);
		row+=39;
		return button;
	}

	public void disablepanel()
	{
		if(painelPrincipal!=null && painelPrincipal.isVisible())
		{
			painelPrincipal.setVisible(false);
		}
		else if(painelCurso!=null&&painelCurso.isVisible())
		{
			painelCurso.setVisible(false);
		}
		else if(painelDisciplina!=null&&painelDisciplina.isVisible())
		{
			painelDisciplina.setVisible(false);
		}
		else if(painelEstudante!=null&&painelEstudante.isVisible())
		{
			painelEstudante.setVisible(false);
		}
		else if(viewstudentpanel!=null && viewstudentpanel.isVisible())
		{
		
			viewstudentpanel.setVisible(false);
		}
		else if(facultypanel!=null && facultypanel.isVisible())
		{
			facultypanel.setVisible(false);
		}
		else if(viewfacultypanel!=null&&viewfacultypanel.isVisible())
		{
			viewfacultypanel.setVisible(false);
		}
		else if(assignsubjectpanel!=null &&assignsubjectpanel.isVisible())
		{
			assignsubjectpanel.setVisible(false);
		}
		else if(entermarkspanelscroll!=null && entermarkspanelscroll.isVisible())
		{
			entermarkspanelscroll.setVisible(false);
		}
		else if(marksheetpanelscroll!=null&& marksheetpanelscroll.isVisible())
		{
			marksheetpanelscroll.setVisible(false);
		}
		else if(markattandancepanelscroll!=null && markattandancepanelscroll.isVisible())
		{
			markattandancepanelscroll.setVisible(false);
		}		
		else if(attandancereportpanelscroll!=null && attandancereportpanelscroll.isVisible())
		{
			attandancereportpanelscroll.setVisible(false);
		}
		else if(marksheetreportpanelscroll!=null &&  marksheetreportpanelscroll.isVisible())
		{
			marksheetreportpanelscroll.setVisible(false);
		}
		else if(adminprofilepanel!=null && adminprofilepanel.isVisible())
		{
			adminprofilepanel.setVisible(false);
		}
		else if(searchpanel!=null && searchpanel.isVisible())
		{
			searchpanel.setVisible(false);
		}
		else if(chatmainpanel!=null && chatmainpanel.isVisible())
		{
			
			try {
				
				if(chatmainpanel.chatpanel.subchatpanel!=null&&chatmainpanel.chatpanel.subchatpanel.socket!=null&&!chatmainpanel.chatpanel.subchatpanel.socket.isClosed())
				{
					chatmainpanel.chatpanel.subchatpanel.socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chatmainpanel.setVisible(false);
		}
		else if(userspanel!=null && userspanel.isVisible())
		{
			userspanel.setVisible(false);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		openPanel(e.getSource());
	}
	public void openPanel(Object source) 
	{
		if(source==homebutton)
		{
			activeButton(homebutton);
			painelPrincipal=new PainelPrincipal(a);
			painelPrincipal.setLocation(panelx, panely);
			painelPrincipal.setFocusable(true);
			contentPane.add(painelPrincipal);
			painelPrincipal.setVisible(true);
			painelPrincipal.setLastLogin(lastlogin);
		}
		else if(source==btCurso)
		{
			activeButton(btCurso);
			painelCurso=new PainelCurso();
			painelCurso.setLocation(panelx,panely);
			painelCurso.setFocusable(true);
			contentPane.add(painelCurso);
			painelCurso.setVisible(true);
		}
		else if(source==btDisciplina)
		{
			activeButton(btDisciplina);
			painelDisciplina=new PainelDisciplina(this);
			painelDisciplina.setLocation(panelx, panely);
			painelDisciplina.setFocusable(true);
			contentPane.add(painelDisciplina);
			painelDisciplina.setVisible(true);
		}
		else if(source==btEstudante)
		{
			activeButton(btEstudante);
			painelEstudante = new PainelEstudante(this);
			painelEstudante.setLocation(panelx, panely);
			painelEstudante.setVisible(true);
			painelEstudante.setFocusable(true);
			contentPane.add(painelEstudante);
			
		}
		else if(source==btDocente)
		{
			activeButton(btDocente);
			facultypanel=new PainelDocente(this);
			facultypanel.setLocation(panelx,panely);
			facultypanel.setVisible(true);
			facultypanel.setFocusable(true);
			contentPane.add(facultypanel);
			
		}
		else if(source==assignsubjectbutton)
		{
			activeButton(assignsubjectbutton);
			assignsubjectpanel=new PainelAtribuirDisciplina(this);
			assignsubjectpanel.setLocation(panelx,panely);
			assignsubjectpanel.setVisible(true);
			assignsubjectpanel.setFocusable(true);
			contentPane.add(assignsubjectpanel);
			
		}
		else if(source==btIntroduzirNotas)
		{
			activeButton(btIntroduzirNotas);
			entermarkspanel=new PainelIntroduzirNotas();
			entermarkspanel.setVisible(true);
			entermarkspanelscroll=new JScrollPane(entermarkspanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			entermarkspanelscroll.setBounds(panelx,panely,1116,705);
			entermarkspanelscroll.setVisible(true);
			entermarkspanelscroll.getVerticalScrollBar().setUnitIncrement(80);
			contentPane.add(entermarkspanelscroll);
			for(Component c:entermarkspanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
			
		}
		else if(source==markattandancebutton)
		{
			activeButton(markattandancebutton);
			markattandancepanel=new PainelMarcarFaltas(this);
			markattandancepanel.setVisible(true);
			markattandancepanelscroll=new JScrollPane(markattandancepanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			markattandancepanelscroll.setBounds(panelx, panely, 1116, 705);
			markattandancepanelscroll.setVisible(true);
			markattandancepanelscroll.getVerticalScrollBar().setUnitIncrement(80);
			contentPane.add(markattandancepanelscroll);
			for(Component c:markattandancepanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}
		else if(source==attandancereportbutton)
		{
			activeButton(attandancereportbutton);
			attandancereportpanel=new PainelVerFaltas(this);
			attandancereportpanel.setVisible(true);
			attandancereportpanelscroll=new JScrollPane(attandancereportpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			attandancereportpanelscroll.setBounds(panelx, panely, 1116, 705);
			attandancereportpanelscroll.setVisible(true);
			attandancereportpanelscroll.setName("Attadance Report Panel Scroll");
			attandancereportpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
			contentPane.add(attandancereportpanelscroll);
			for(Component c:attandancereportpanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}
		else if(source==marksheetreportbutton)
		{
			activeButton(marksheetreportbutton);
			marksheetreportpanel=new PainelReportarNotas(this);
			marksheetreportpanel.setVisible(true);
			marksheetreportpanelscroll=new JScrollPane(marksheetreportpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			marksheetreportpanelscroll.setBounds(panelx, panely, 1116, 705);
			marksheetreportpanelscroll.setVisible(true);
			marksheetreportpanelscroll.setName("Marksheet Report Panel Scroll");
			marksheetreportpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
			contentPane.add(marksheetreportpanelscroll);
			for(Component c:marksheetreportpanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}
		else if(source==btUsuario)
		{
			activeButton(btUsuario);
			userspanel=new PainelUsuario(this);
			userspanel.setVisible(true);
			userspanel.setLocation(this.panelx, this.panely);
			contentPane.add(userspanel);
		}
		else if(source==chatbutton)
		{
			
			activeButton(chatbutton);
			chatmainpanel=new ChatPainelPrincipal(this);
			chatmainpanel.setLocation(this.panelx, this.panely);
			chatmainpanel.setVisible(true);
			contentPane.add(chatmainpanel);
			
		}
		else if(source==searchbutton)
		{
			activeButton(searchbutton);
			searchpanel=new PainelPesquisa(this);
			searchpanel.setLocation(this.panelx, this.panely);
			searchpanel.setVisible(true);
			contentPane.add(searchpanel);
			
		}
		else if(source==btPerfilAdmin)
		{
			activeButton(btPerfilAdmin);
			adminprofilepanel=new AdminPainelPerfil(this);
			adminprofilepanel.setLocation(panelx,panely);
			adminprofilepanel.setVisible(true);
			adminprofilepanel.setFocusable(true);
			contentPane.add(adminprofilepanel);
		}
		else if(source==exitbutton)
		{
			int result=JOptionPane.showConfirmDialog(null,"Tem certeza que deseja fechar ?","SAIR",JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				a.setActiveStatus(false);
				timer.stop();
        		new AdminDados().setActiveStatus(false);
				this.disablepanel();
				DataBaseConnection.closeConnection();
				System.exit(0);
			}
		}
		else if(source==logoutbutton)
		{
			int result=JOptionPane.showConfirmDialog(null,"Encerar seccção ?","Terminar secção",JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				a.setActiveStatus(false);
				timer.stop();
        		new AdminDados().setActiveStatus(false);
				PaginaLogin loginpageframe=new PaginaLogin();
				loginpageframe.setVisible(true);
				loginpageframe.setLocationRelativeTo(null);
				this.disablepanel();
				this.dispose();
			}
		}
		
	}
	
	
	
	public void setCollageDetails()
	{
		 a=new AdminDados().getAdminData();
		 profilepiclabel.setIcon(new ImageIcon(a.getRoundedProfilePic(50, 50, 50)));
	}
}
