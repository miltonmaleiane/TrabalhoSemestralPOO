package docente;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.AdminPrincipal;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@SuppressWarnings("serial")
public class AdicionarDocente extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JTextField facultyidfield;
	private JTextField facultynamefield;
	private JComboBox<String> statefield;
	private JTextField cityfield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private JTextField qualificationfield;
	private JTextField experiencefield;
	private static AdicionarDocente dialog;
	private String defaultpicpath="./assets/profilepicicon.jpg";
	private JButton choosefilebutton,addfacultybutton;
	private File file;
	private JLabel filesizenote,filenamelabel,filesize;
	private JLabel profilepiclabel;
	private JLabel Errorlabel;
	private JSpinner birthdatespinner;
	private JComboBox<String> gendercombo;
	PainelDocente fp;
	private AdminPrincipal am;
	private Docente faculty;
	private JLabel headerlabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AdicionarDocente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarDocente() {	
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 20, 711, 680);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		headerlabel = new JLabel("Cadastrar Docente");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 695, 42);
		getContentPane().add(headerlabel);
		
		headerlabel.setBackground(new Color (47, 173, 102));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		
		JLabel faculityidlabel = new JLabel("ID do Docente");
		faculityidlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		faculityidlabel.setBounds(21, 53, 134, 29);
		getContentPane().add(faculityidlabel);
		
		facultyidfield = new JTextField(new DadosDocente().createFacultyID()+"");
		facultyidfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		facultyidfield.setEditable(false);
		facultyidfield.setBounds(20, 85, 323, 42);
		getContentPane().add(facultyidfield);
		facultyidfield.setColumns(10);
		
		JLabel lblFaculityName = new JLabel("Nome");
		lblFaculityName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFaculityName.setBounds(362, 53, 166, 29);
		getContentPane().add(lblFaculityName);
		
		facultynamefield = new JTextField();
		facultynamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				facultynamefield.setFocusable(true);
			}
		});
		facultynamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					facultynamefield.setFocusable(false);
				}
				
			}
		});
		facultynamefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		facultynamefield.setColumns(10);
		facultynamefield.setBounds(362, 85, 323, 42);
		getContentPane().add(facultynamefield);
		
		JLabel lblState = new JLabel("Província");
		lblState.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblState.setBounds(21, 145, 134, 21);
		getContentPane().add(lblState);
		
		statefield = new JComboBox<String>();
		statefield.setFocusable(false);
		
		statefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		statefield.setModel(new DefaultComboBoxModel<String>(new String[] {"---Selecionar província---",
				"Cabo-Delgado","Gaza","Inhambane","Maputo Cidade","Maputo Pronvicia","Manica",
				"Nampula","Niassa","Sofala","Tete","Zambézia"}));
		statefield.setBackground(Color.WHITE);
		//stat.setBounds(362, 442, 323, 42);
		//gendercombo.addActionListener(this);
		//getContentPane().add(gendercombo);
		
		
		//statefield = new JTextField();
		//statefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		//statefield.setColumns(10);
		statefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					statefield.setFocusable(false);
				}
				
			}
		});
		statefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				statefield.setFocusable(true);
			}
		});
		statefield.setBounds(21, 173, 322, 42);
		getContentPane().add(statefield);
		
		JLabel lblCity = new JLabel("Districto");
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCity.setBounds(362, 141, 143, 29);
		getContentPane().add(lblCity);
		
		cityfield = new JTextField();
		cityfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		cityfield.setColumns(10);
		cityfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					cityfield.setFocusable(false);
				}
				
			}
		});
		cityfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cityfield.setFocusable(true);
			}
		});
		cityfield.setBounds(362, 173, 323, 42);
		getContentPane().add(cityfield);
		
		JLabel lblEmailId = new JLabel("E-mail ");
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmailId.setBounds(21, 231, 134, 29);
		getContentPane().add(lblEmailId);
		
		emailidfield = new JTextField();
		emailidfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		emailidfield.setColumns(10);
		emailidfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					emailidfield.setFocusable(false);
				}
				
			}
		});
		emailidfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				emailidfield.setFocusable(true);
			}
		});
		emailidfield.setBounds(21, 260, 322, 42);
		getContentPane().add(emailidfield);
		
		JLabel lblPhoneNumber = new JLabel("Contacto");
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(362, 226, 185, 29);
		getContentPane().add(lblPhoneNumber);
		
		contactnumberfield = new JTextField();
		contactnumberfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		contactnumberfield.setColumns(10);
		contactnumberfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					contactnumberfield.setFocusable(false);
				}
				
			}
		});
		contactnumberfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contactnumberfield.setFocusable(true);
			}
		});
		contactnumberfield.setBounds(362, 260, 323, 42);
		getContentPane().add(contactnumberfield);
		
		JLabel lblQualification = new JLabel("Qualificação");
		lblQualification.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblQualification.setBounds(21, 326, 134, 29);
		getContentPane().add(lblQualification);
		
		qualificationfield = new JTextField();
		qualificationfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		qualificationfield.setColumns(10);
		qualificationfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					qualificationfield.setFocusable(false);
				}
				
			}
		});
		qualificationfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				qualificationfield.setFocusable(true);
			}
		});
		qualificationfield.setBounds(21, 355, 322, 42);
		getContentPane().add(qualificationfield);
		
		JLabel lblExperience = new JLabel("Ramo de Ensino");
		lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblExperience.setBounds(362, 326, 134, 29);
		getContentPane().add(lblExperience);
		
		experiencefield = new JTextField();
		experiencefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		experiencefield.setColumns(10);
		experiencefield.setBounds(362, 355, 322, 42);
		experiencefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					experiencefield.setFocusable(false);
				}
				
			}
		});
		experiencefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				experiencefield.setFocusable(true);
			}
		});
		getContentPane().add(experiencefield);
		
		JLabel doblabel = new JLabel("Data de Nascimento");
		doblabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		doblabel.setBounds(21, 414, 164, 29);
		getContentPane().add(doblabel);
		
		birthdatespinner = new JSpinner();
		birthdatespinner.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		birthdatespinner.setModel(new SpinnerDateModel());
		SimpleDateFormat model=new SimpleDateFormat("dd-MM-yyyy");
		birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner,model.toPattern()));
		birthdatespinner.setForeground(Color.WHITE);
		birthdatespinner.setBounds(21, 442, 282, 42);
		getContentPane().add(birthdatespinner);
		
		JLabel lblGender = new JLabel("Género");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGender.setBounds(362, 414, 134, 29);
		getContentPane().add(lblGender);
		
		gendercombo = new JComboBox<String>();
		gendercombo.setFocusable(false);
		
		gendercombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gendercombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Selecionar Género---", "Masculino", "Femenino",}));
		gendercombo.setBackground(Color.WHITE);
		gendercombo.setBounds(362, 442, 323, 42);
		gendercombo.addActionListener(this);
		getContentPane().add(gendercombo);
		
		 profilepiclabel = new JLabel("");
		profilepiclabel.setBorder(new LineBorder(Color.GRAY));
		profilepiclabel.setIcon(new ImageIcon(defaultpicpath));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilepiclabel.setBounds(21, 507, 100, 120);
		getContentPane().add(profilepiclabel);
		
		JLabel lblPhoto = new JLabel("Foto");
		lblPhoto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblPhoto.setBounds(163, 507, 54, 29);
		getContentPane().add(lblPhoto);
		
		 filesize = new JLabel("");
		filesize.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		filesize.setBounds(227, 507, 399, 29);
		getContentPane().add(filesize);
		
		 choosefilebutton = new JButton("Selecionar");
		choosefilebutton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		choosefilebutton.setForeground(Color.BLACK);
		choosefilebutton.addActionListener(this);
		choosefilebutton.setFocusable(false);
		choosefilebutton.setBackground(new Color(245, 245, 245));
		choosefilebutton.setBounds(161, 547, 114, 35);
		choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(choosefilebutton);
		
		 filenamelabel = new JLabel("Nenhum fich selecionado");
		filenamelabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		filenamelabel.setBounds(285, 547, 400, 29);
		getContentPane().add(filenamelabel);
		
		 filesizenote = new JLabel("Imagesize < 1024 KB");
		filesizenote.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		filesizenote.setBounds(163, 593, 373, 29);
		getContentPane().add(filesizenote);
		
		addfacultybutton = new JButton("Cadastrar");
		addfacultybutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addfacultybutton.setForeground(new Color(255, 255, 255));
		addfacultybutton.setBackground(new Color(12, 69, 86));
		addfacultybutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		addfacultybutton.setFocusable(false);
		addfacultybutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addfacultybutton.addActionListener(this);
		addfacultybutton.setBounds(530, 605, 155, 37);
		getContentPane().add(addfacultybutton);
		
		Errorlabel = new JLabel("Preenchimento obrigatório !");
		Errorlabel.setForeground(new Color(255, 0, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setBounds(21, 127, 322, 17);
		Errorlabel.setVisible(false);
		getContentPane().add(Errorlabel);
		
	}
	public AdicionarDocente(PainelDocente facultypanel)
	{
		
		this();
		this.fp=facultypanel;
	}
	public AdicionarDocente(AdminPrincipal am,Docente f)
	{
		this();
		this.faculty=f;
		this.am=am;
		facultyidfield.setText(f.getFacultyId()+"");
		facultynamefield.setText(f.getFacultyName());
		statefield.setSelectedItem(f.getState());
		cityfield.setText(f.getCity());
		emailidfield.setText(f.getEmailId());
		contactnumberfield.setText(f.getContactNumber());
		birthdatespinner.setValue(f.getBirthDateInDateFormat());
		gendercombo.setSelectedItem(f.getGender()+"");
		experiencefield.setText(f.getExperience());
		qualificationfield.setText(f.getQualification());
		profilepiclabel.setIcon(new ImageIcon(f.getProfilePic(100, 120)));
		headerlabel.setText("Edit Faculty Details");
		addfacultybutton.setText("Update Faculty");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Errorlabel.setVisible(false);
		if(e.getSource()==choosefilebutton)
		{
			FileDialog fd=new FileDialog(this,"Choose a File",FileDialog.LOAD);
			
			fd.setDirectory(".\\Faculities Profile pic\\");
			fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");	
			fd.setVisible(true);
			fd.setLocationRelativeTo(this);
			String filename=fd.getFile();
			String path=fd.getDirectory();
			
			if(filename!=null)
			{
 				
				file=new File(path+filename);
				long bytes=file.length();
				if(bytes<10482376)
				{
					try
					{
						 filesizenote.setForeground(new Color(46,139,27));
						filesizenote.setText("Image size < 1024 KB");
						Image image = ImageIO.read(file).getScaledInstance(100, 120, Image.SCALE_SMOOTH);
						profilepiclabel.setIcon(new ImageIcon(image));
						filesize.setText(file.length()/1024+" KB");
						filenamelabel.setText(file.getName());	
					}
					catch(Exception exp)
					{
						file = null;
	            		filenamelabel.setText("No file Choosen");
	            		filesize.setText("");
	            	   filesizenote.setForeground(Color.red);
	            	   filesizenote.setText("Image Not supported");
	            	   exp.printStackTrace();
					}
				}
				else
				{
					file = null;
					filesizenote.setForeground(Color.red);
					filesizenote.setText("Image size greater than 1024 KB");
					filesize.setText("");
					filenamelabel.setText("No File Choosen");
				}
			
			}
		// TODO Auto-generated method stub
		}
		if(e.getSource()==addfacultybutton)
		{
			if(facultynamefield.getText().isEmpty())
			{
				showerror(facultynamefield);
			}
			else if(statefield.getSelectedIndex()==0)
			{
				showerror(statefield);
			}
			else if(cityfield.getText().isEmpty())
			{
				showerror(cityfield);
			}
			else if(emailidfield.getText().isEmpty())
			{
				showerror(emailidfield);
			}
			else if(contactnumberfield.getText().isEmpty())
			{
				showerror(contactnumberfield);
			}

			else if(qualificationfield.getText().isEmpty())
			{
				showerror(qualificationfield);
				
			}
			else if(experiencefield.getText().isEmpty())
			{
				showerror(experiencefield);
			}
			
			else if(gendercombo.getSelectedIndex()==0)
			{
				showerror(gendercombo);
			}
			else
			{
				try
				{
					Docente f=new Docente();
					f.setFacultyId(Integer.parseInt(facultyidfield.getText()));
					f.setFacultyName(facultynamefield.getText());
					f.setState(statefield.getSelectedItem()+"");
					f.setCity(cityfield.getText());
					f.setEmailId(emailidfield.getText());
					f.setContactNumber(contactnumberfield.getText());
					f.setExperience(experiencefield.getText());
					f.setQualification(qualificationfield.getText());
					Date date=(Date) birthdatespinner.getValue();
					f.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
					f.setGender(gendercombo.getSelectedItem()+"");
					
					if(file!=null) {
						f.setProfilePic(ImageIO.read(file));
					}
					else if(faculty!=null)
					{
						f.setProfilePic(faculty.getProfilePic());
					}
					else 
					{
					
						file = new File(defaultpicpath);
						f.setProfilePic(ImageIO.read(file));
					}
					int result=0;
					if(fp!=null)
					{
					result=new DadosDocente().addFacultyData(f);
					}
					else if(am!=null && faculty!=null)
					{
						result=new DadosDocente().updateFacultyData(faculty, f);
					}
					if(result>0)
					{
						if(fp!=null)
						{
							if(fp.photoviewscrollpane!=null && fp.photoviewscrollpane.isVisible())
							{
								fp.createtablemodel();
								fp.createphotoviewpanel();
							}
							else
							{
								fp.createtablemodel();
							}
						}
						else if(am!=null && faculty!=null)
						{
//							Student su=new StudentData().getStudentDetails(s.courcecode, s.sem, s.rollnumber);
							am.viewfacultypanel.setVisible(false);
							am.viewfacultypanel=new PainelVerDocente(new DadosDocente().getFacultyInfobyId(f.getFacultyId()),am,am.viewfacultypanel.lastpanel);
							am.viewfacultypanel.setVisible(true);
							am.viewfacultypanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewfacultypanel);
					
							
							
						}
						Properties props = new Properties();
					    /** Parâmetros de conexão com servidor Gmail */
					    props.put("mail.smtp.host", "smtp.gmail.com");
					    props.put("mail.smtp.socketFactory.port", "465");
					    props.put("mail.smtp.socketFactory.class",
					    "javax.net.ssl.SSLSocketFactory");
					    props.put("mail.smtp.auth", "true");
					    props.put("mail.smtp.port", "465");

					    Session session = Session.getDefaultInstance(props,
					      new javax.mail.Authenticator() {
					           protected PasswordAuthentication getPasswordAuthentication()
					           {
					                 return new PasswordAuthentication("uembiblioteca2022@gmail.com",
					                 "hbqnnqmxgzkzuhin");
					           }
					      });

					    /** Ativa Debug para sessão */
					    session.setDebug(true);

					    try {

					      Message message = new MimeMessage(session);
					      message.setFrom(new InternetAddress("uembiblioteca2022@gmail.com"));
					      //Remetente
					      String destinatario = emailidfield.getText();
					      Address[] toUser = InternetAddress //Destinatário(s)
					                 .parse(destinatario);
					                 

					      message.setRecipients(Message.RecipientType.TO, toUser);
					      message.setSubject("Email de boas vindas ");//Assunto
					      message.setText("Viva caro(a) Docente "+""+facultynamefield.getText()+""+" Podera aceder ao nosso sistema usando os seguintes dados Username:"+" "+f.getFacultyId()+" "+"Password:"
					      		+ " "+(new SimpleDateFormat("dd-MM-yyyy").format(date))+"\r\n"+"Melhores Cumprimentos"+"\r\n"+"Equipe academica");
					   
					      /**Método para enviar a mensagem criada*/
					      Transport.send(message);

					      System.out.println("Feito!!!");

					     } catch (MessagingException e1) {
					        throw new RuntimeException(e1);
					    }
						this.dispose();
						
					}
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
	}

	public void showerror(JComponent tf)
	{
		Errorlabel.setVisible(true);
		Errorlabel.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
}
