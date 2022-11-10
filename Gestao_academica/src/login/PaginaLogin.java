package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.Admin;
import admin.AdminDados;
import comuns.DataBaseConnection;


@SuppressWarnings("serial")
public class PaginaLogin extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	private JButton btnDocente;
	private JButton btnEstudante;
	private JButton btnadmin;
	private PainelLogin estudanteLoginpanel,docenteLoginpanel,adminLoginpanel;
	private boolean adminchanging=false,studentchanging=false,facultychanging=false;
	private int adminpanelx=-2300,adminpanely=215;
	private int docentepanelx=-900,docentepanely=215;
	private int panelestudantex=600,panelestudantey=215;
	private int underlinelabelx=300,underlinelabelwidth=110;
	public Timer timer;
	private int imagenumber=1;
	private JLabel bgimagelabel;
	private JLabel underlinelabel;
	private JPanel loginbuttonpanel;
	public Timer imagetimer;
	/**	
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(DataBaseConnection.checkconnection())
					{
					PaginaLogin frame = new PaginaLogin();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.show();
					frame.setVisible(true);
					
					frame.setLocation(70, 6);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Inicie a Base de Dados","Erro",JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaginaLogin() {
		timer=new Timer(5,this);
		imagetimer=new Timer(5000,this);
		imagetimer.start();
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100,700);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		contentPane.setBackground(new Color(47, 173, 102));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		Admin ad=new AdminDados().getAdminData();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(12, 69, 86));
		panel.setBounds(0, 0, 550, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSilverOakCollage = new JLabel(ad.getNomeEscola());
		lblSilverOakCollage.setForeground(new Color(12, 69, 86));
		lblSilverOakCollage.setFont(new Font("Times new Roman", Font.BOLD, 38));
		lblSilverOakCollage.setHorizontalAlignment(SwingConstants.LEFT);
		lblSilverOakCollage.setBounds(797, 43, 749, 57);
		contentPane.add(lblSilverOakCollage);
		
		JLabel nome= new JLabel("Sistema de Gestão Escolar");
		nome.setForeground(new Color(12, 69, 86));
		nome.setBounds(755,95,300,20);
		nome.setFont(new Font("Arial",Font.BOLD,14));
		contentPane.add(nome);
		
		JLabel lblLogo = new JLabel("logo");
	    lblLogo.setBounds(900, 18, 140, 140);
		lblLogo.setIcon(new ImageIcon(ad.getRoundedProfilePic(lblLogo.getWidth(), lblLogo.getHeight(), lblLogo.getWidth())));
		contentPane.add(nome);
		
		JLabel lblLogo2 = new JLabel("logo");
	    lblLogo2.setBounds(80, 18, 540, 540);
	    lblLogo2.setBackground(new Color(12, 69, 86));
	    lblLogo2.setForeground(new Color(12, 69, 86));
		lblLogo2.setIcon(new ImageIcon("./assets/vetor-book.png"));
		panel.add(lblLogo2);
		contentPane.add(lblLogo);
		
		JLabel texto = new JLabel("Dinamismo na sua Instituição");
		texto.setBounds(130,450,300,20);
		texto.setFont(new Font("Arial",Font.BOLD,15));
		texto.setForeground(new Color(47, 173, 102)); 
		panel.add(texto);
		
		JLabel texto2 = new JLabel("Organize as suas tarefas académicas de formas simples ");
		texto2.setBounds(85,475,400,20);
		texto2.setFont(new Font("Arial",Font.PLAIN,14));
		texto2.setForeground(new Color(134, 212, 168)); 
		panel.add(texto2);
		
		JLabel texto3 = new JLabel(" e dinâmica onde estiver, via Desktop ou pela web.");
		texto3.setBounds(85,495,400,20);
		texto3.setFont(new Font("Arial",Font.PLAIN,14));
		texto3.setForeground(new Color(134, 212, 168)); 
		panel.add(texto3);
		
		
		
		
		
		
		estudanteLoginpanel=new PainelLogin("Estudante",new ImageIcon("./assets/studentlogin.png"),this);
		estudanteLoginpanel.setVisible(true);
		//estudanteLoginpanel.setForeground(new Color(47, 173, 102));
		estudanteLoginpanel.setLocation(panelestudantex,panelestudantey);
	
		docenteLoginpanel=new PainelLogin("Docente",new ImageIcon("./assets/facultylogin.png"),this);
		docenteLoginpanel.setVisible(true);
		docenteLoginpanel.setLocation(docentepanelx, docentepanely);
		
		adminLoginpanel=new PainelLogin("Admin",new ImageIcon("./assets/adminlogin.png"),this);
		adminLoginpanel.setVisible(true);
		adminLoginpanel.setLocation(adminpanelx, adminpanely);
		
		
		contentPane.add(estudanteLoginpanel);
		contentPane.add(docenteLoginpanel);
		contentPane.add(adminLoginpanel);
			
			
			loginbuttonpanel = new JPanel()
					{
						protected void paintComponent(Graphics g)
					    {
					        g.setColor( getBackground() );
					        g.fillRect(0, 0, getWidth(), getHeight());
					        super.paintComponent(g);
					    }
					};
					loginbuttonpanel.setOpaque(false);
					loginbuttonpanel.setBackground(new Color(47, 173, 102));
			loginbuttonpanel.setBounds(600, 170, 420, 40);
			loginbuttonpanel.setLayout(null);
			
			contentPane.add(loginbuttonpanel);
		
			
		
			btnadmin = new JButton("Admin");
			btnadmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activeButton(btnadmin);
					disableButton(btnDocente);
					disableButton(btnEstudante);
					adminchanging=true;
					studentchanging=false;
					facultychanging=false;
					timer.start();
				}
			});
			this.buttonStyle(btnadmin);
			btnadmin.setBounds(0, 0, 140, 35);
			loginbuttonpanel.add(btnadmin);
			
			
			btnDocente = new JButton("Docente");
			btnDocente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activeButton(btnDocente);
					disableButton(btnEstudante);
					disableButton(btnadmin);
					facultychanging=true;
					adminchanging=false;
					studentchanging=false;
					timer.start();
				}
			});
			this.buttonStyle(btnDocente);
			btnDocente.setBounds(140, 0, 140, 35);
			loginbuttonpanel.add(btnDocente);
			
			btnEstudante= new JButton("Estudante");
			btnEstudante.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activeButton(btnEstudante);
					disableButton(btnDocente);
					disableButton(btnadmin);
					studentchanging=true;
					adminchanging=false;
					facultychanging=false;
					timer.start();
				}
				
			});
			btnEstudante.setBounds(280, 0, 140, 35);
			this.buttonStyle(btnEstudante);
			loginbuttonpanel.add(btnEstudante);
			activeButton(btnEstudante);

			underlinelabel = new JLabel("");
			underlinelabel.setBorder(new MatteBorder(2, 0, 0, 0, (Color)new Color(12, 69, 86)));
			underlinelabel.setBounds(underlinelabelx, 37, underlinelabelwidth, 4);
			loginbuttonpanel.add(underlinelabel);

		
			
			//bgimagelabel = new JLabel("image");
			//bgimagelabel.setBounds(0, 11, 1380, 683);
			//contentPane.add(bgimagelabel);
			//this.setBackgroundImage();
			
		
		
	}
//
//	protected void disposethis() {
//		// TODO Auto-generated method stub
//		this.dispose();
//	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
			if(!adminchanging&&!studentchanging&&!facultychanging)
			{
				
				imagenumber++;
				if(imagenumber>5)
				{
					imagenumber=1;
				
				}
				this.setBackgroundImage();
			}
			
			if(adminchanging)
			{
				if(adminpanelx==600)
				{
					adminchanging=false;
					timer.stop();
				}
				else
				{
					
					adminpanelx+=50;
					panelestudantex+=50;
					docentepanelx+=50;
					underlinelabelx-=5;
				}
			}
			else if(facultychanging)
			{
				if(docentepanelx==600)
				{
					facultychanging=false;
					timer.stop();
				}
				else
				{
					if(docentepanelx>600)
					{
						adminpanelx-=50;
						panelestudantex-=50;
						docentepanelx-=50;	
						underlinelabelx+=5;
					}
					else
					{
						adminpanelx+=50;
						panelestudantex+=50;
						docentepanelx+=50;
						underlinelabelx-=5;
					}
				}
			}
			else if(studentchanging)
			{
				if(panelestudantex==600)
				{
					studentchanging=false;
					timer.stop();
				}
				else
				{
					
						adminpanelx-=50;
						panelestudantex-=50;
						docentepanelx-=50;
						underlinelabelx+=5;
					
				}
			}
			
			estudanteLoginpanel.setLocation(panelestudantex,panelestudantey);
			docenteLoginpanel.setLocation(docentepanelx, docentepanely);
			adminLoginpanel.setLocation(adminpanelx, adminpanely);
			underlinelabel.setLocation(underlinelabelx, underlinelabel.getY());
			this.repaint();
			
		
	}
	public void buttonStyle(JButton button)
	{
		button.setFocusable(true);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 15));
		button.setBorder(new EmptyBorder(0,0,0,0));
		button.setBackground(Color.black);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
	
	}
	public void activeButton(JButton button)
	{
		button.setForeground(new Color(12, 69, 86));
		
	}
	public void disableButton(JButton button)
	{
		button.setForeground(Color.white);
	}
	public void setBackgroundImage()
	{
		//try {
			System.out.println(imagenumber);
			//	Image image=ImageIO.read(new File(".//assets//backgroundimage"+imagenumber+".jpg"));
			//bgimagelabel.setIcon(new ImageIcon(image.getScaledInstance(bgimagelabel.getWidth(), bgimagelabel.getHeight(), Image.SCALE_SMOOTH)));
			
		//} catch (IOException e) {
	
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
}