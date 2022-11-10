package estudante;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.AdminPrincipal;
import comuns.DicaTextField;
import curso.DadosCurso;
import curso.DadosNumeroEstudante;
import disciplina.DadosDisciplina;


@SuppressWarnings("serial")
public class AddEstudante extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField rollnumberfield;
	private JTextField firstnamefield;
	private JTextField lastnamefield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private JTextField statefield;
	private JTextField cityfield;
	private JTextField fathernamefield;
	private JTextField fatheroccupationfield;
	private JTextField mothernamefield;
	private JTextField motheroccupationfield;
	private JLabel lblPhoto;
	private JLabel filename;
	private JComboBox<String> courcenamecombo, semoryearcombo, optionalsubjectcombo, gendercombo;
	private JSpinner birthdatespinner;
	private JButton choosefilebutton, addstudentbutton;
	private File file ;
	private String imagepath = null;
	private JLabel filesize;
	private AdminPrincipal am;
	private JLabel profilepiclabel, filesizenote;
	private JLabel Errorlabel;
	private static AddEstudante dialog;
	private PainelEstudante sp;
	private JLabel headerlabel;
	private Estudante student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// If translucent windows aren't supported, exit.

		JFrame.setDefaultLookAndFeelDecorated(true);

		try {
			dialog = new AddEstudante();

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 */
	public AddEstudante() {

		super(new JFrame(), true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setSize(850, 700);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		headerlabel = new JLabel("Cadastro de Estudante");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 834, 40);
		getContentPane().add(headerlabel);

		headerlabel.setBackground(new Color(47, 173, 102));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));

		courcenamecombo = new JComboBox<String>(new DadosCurso().getCourceName());
		courcenamecombo.setForeground(Color.DARK_GRAY);
		courcenamecombo.setToolTipText("Cource");
		courcenamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		courcenamecombo.addActionListener(this);
		courcenamecombo.setBackground(new Color(255, 255, 255));
		courcenamecombo.setBounds(10, 51, 400, 40);
		courcenamecombo.setFocusable(false);
		getContentPane().add(courcenamecombo);

		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setPrototypeDisplayValue("--select prototype--");
		semoryearcombo.setToolTipText("Semestre/Ano");
		semoryearcombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setFocusable(false);
		semoryearcombo.setBounds(424, 51, 400, 40);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		getContentPane().add(semoryearcombo);

		rollnumberfield = new DicaTextField("");
		rollnumberfield.setToolTipText("Número de Rol");
		rollnumberfield.setFocusable(false);
		rollnumberfield.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		rollnumberfield.setBounds(160, 116, 250, 40);
		getContentPane().add(rollnumberfield);
		rollnumberfield.setColumns(10);

		JLabel lblRollNo = new JLabel("Id do Esdutante");
		lblRollNo.setForeground(Color.DARK_GRAY);
		lblRollNo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		lblRollNo.setFocusable(true);
		lblRollNo.setBounds(12, 116, 140, 40);
		getContentPane().add(lblRollNo);

		optionalsubjectcombo = new JComboBox<String>();
		optionalsubjectcombo.setToolTipText("Disciplina Opcional");
		optionalsubjectcombo.setFocusable(false);
		optionalsubjectcombo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					optionalsubjectcombo.setFocusable(false);
					firstnamefield.setFocusable(true);
				}
			}
		});
		optionalsubjectcombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		optionalsubjectcombo.setBackground(Color.WHITE);
		optionalsubjectcombo.setBounds(424, 116, 400, 40);
		optionalsubjectcombo.addActionListener(this);
		getContentPane().add(optionalsubjectcombo);

		firstnamefield = new DicaTextField("Nome");
		firstnamefield.setToolTipText("Nome sem Apelido");
		firstnamefield.setForeground(Color.DARK_GRAY);
		firstnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		firstnamefield.addActionListener(this);
		firstnamefield.setColumns(10);
		firstnamefield.setBounds(10, 177, 400, 40);
		getContentPane().add(firstnamefield);

		lastnamefield = new DicaTextField("Apelido");
		lastnamefield.setToolTipText("Apelido");
		lastnamefield.setForeground(Color.DARK_GRAY);
		lastnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		lastnamefield.setColumns(10);
		lastnamefield.setBounds(424, 177, 400, 40);
		getContentPane().add(lastnamefield);

		emailidfield = new DicaTextField(" E-mail ");
		emailidfield.setToolTipText("Exemplo:keilbambo@gmail.com ");
		emailidfield.setForeground(Color.DARK_GRAY);
		emailidfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		emailidfield.setColumns(10);
		emailidfield.setBounds(10, 240, 400, 40);
		getContentPane().add(emailidfield);

		contactnumberfield = new DicaTextField(" Contacto");
		contactnumberfield.setToolTipText("Contacto");
		contactnumberfield.setForeground(Color.DARK_GRAY);
		contactnumberfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		contactnumberfield.setColumns(10);
		contactnumberfield.setBounds(424, 240, 400, 40);
		getContentPane().add(contactnumberfield);

		JLabel lblDateOfBirth = new JLabel("Data de Nasc.:");
		lblDateOfBirth.setForeground(Color.DARK_GRAY);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(10, 302, 114, 40);
		getContentPane().add(lblDateOfBirth);

		birthdatespinner = new JSpinner();
		birthdatespinner.setToolTipText("Data de Nascimento");
		birthdatespinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					birthdatespinner.setFocusable(false);
				}
			}
		});
		birthdatespinner.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		SimpleDateFormat model = new SimpleDateFormat("dd-MM-yyyy");
		birthdatespinner.setModel(new SpinnerDateModel());
		birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner, model.toPattern()));
		birthdatespinner.setBounds(134, 302, 276, 42);
		getContentPane().add(birthdatespinner);

		gendercombo = new JComboBox<String>();
		gendercombo.setToolTipText("Género");
		gendercombo.setModel(new DefaultComboBoxModel<String>(new String[] { "---Selecionar Género---", "Masculino", "Femenino" }));
		gendercombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		gendercombo.setBackground(Color.WHITE);
		gendercombo.addActionListener(this);
		gendercombo.setBounds(424, 303, 400, 40);
		gendercombo.setFocusable(false);
		getContentPane().add(gendercombo);

		statefield = new DicaTextField("Província");
		statefield.setToolTipText("State");
		statefield.setForeground(Color.DARK_GRAY);
		statefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		statefield.setColumns(10);
		statefield.setBounds(10, 363, 400, 40);
		getContentPane().add(statefield);

		cityfield = new DicaTextField(" Bairro");
		cityfield.setToolTipText("Bairro");
		cityfield.setForeground(Color.DARK_GRAY);
		cityfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		cityfield.setColumns(10);
		cityfield.setBounds(424, 363, 400, 40);
		getContentPane().add(cityfield);

		fathernamefield = new DicaTextField(" Nome do pai");
		fathernamefield.setToolTipText("Nome da Mãe");
		fathernamefield.setForeground(Color.DARK_GRAY);
		fathernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		fathernamefield.setColumns(10);
		fathernamefield.setBounds(10, 424, 400, 40);
		getContentPane().add(fathernamefield);

		fatheroccupationfield = new DicaTextField(" Profissão do Pai");
		fatheroccupationfield.setToolTipText("Ocupação do Pai");
		fatheroccupationfield.setForeground(Color.DARK_GRAY);
		fatheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		fatheroccupationfield.setColumns(10);
		fatheroccupationfield.setBounds(424, 424, 400, 40);
		getContentPane().add(fatheroccupationfield);

		mothernamefield = new DicaTextField(" Nome Mãe");
		mothernamefield.setToolTipText("Nome Mãe");
		mothernamefield.setForeground(Color.DARK_GRAY);
		mothernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		mothernamefield.setColumns(10);
		mothernamefield.setBounds(10, 485, 400, 40);
		getContentPane().add(mothernamefield);

		motheroccupationfield = new DicaTextField(" Profissão da Mãe");
		motheroccupationfield.setToolTipText("Profissão da Mãe");
		motheroccupationfield.setForeground(Color.DARK_GRAY);
		motheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		motheroccupationfield.setColumns(10);
		motheroccupationfield.setBounds(424, 485, 400, 40);
		getContentPane().add(motheroccupationfield);

		filesizenote = new JLabel("T.imagem <  1024 KB");
		filesizenote.setToolTipText("Tamanho da imagem");
		filesizenote.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filesizenote.setBounds(134, 624, 545, 32);
		getContentPane().add(filesizenote);

		filesize = new JLabel("");
		filesize.setToolTipText("Tamanho da Imagem");
		filesize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filesize.setBounds(200, 544, 566, 32);
		getContentPane().add(filesize);

		profilepiclabel = new JLabel();
		profilepiclabel.setToolTipText("Foto de Perfil");
		profilepiclabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilepiclabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		profilepiclabel.setBounds(10, 536, 100, 120);
		getContentPane().add(profilepiclabel);
		profilepiclabel.setIcon(new ImageIcon("./assets/profilepicicon.jpg"));

		choosefilebutton = new JButton("Escolher Ficheiro");
		choosefilebutton.addActionListener(this);
		choosefilebutton.setFocusable(false);
		choosefilebutton.setBackground(new Color(245, 245, 245));
		choosefilebutton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		choosefilebutton.setBounds(134, 582, 114, 32);
		choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(choosefilebutton);

		lblPhoto = new JLabel("Foto");
		lblPhoto.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		lblPhoto.setBounds(136, 548, 73, 21);
		getContentPane().add(lblPhoto);

		filename = new JLabel("Nenhum Ficheiro escolhido");
		filename.setToolTipText("File Name");
		filename.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filename.setBounds(258, 582, 566, 32);
		getContentPane().add(filename);

		addstudentbutton = new JButton("Adicionar Estudante");
		addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addstudentbutton.setForeground(new Color(255, 255, 255));
		addstudentbutton.setBackground(new Color(12, 69, 86));
		addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addstudentbutton.addActionListener(this);
		addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addstudentbutton.setBounds(670, 613, 150, 37);
		addstudentbutton.setFocusable(false);
		getContentPane().add(addstudentbutton);

		Errorlabel = new JLabel("Preenchimento obrigatorio !");
		Errorlabel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		Errorlabel.setHorizontalAlignment(SwingConstants.LEFT);
		Errorlabel.setForeground(new Color(255, 69, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setVisible(false);
		Errorlabel.setBounds(10, 90, 400, 26);
		getContentPane().add(Errorlabel);

	}

	public AddEstudante(AdminPrincipal am, Estudante s) {
		this();
		this.am = am;
		this.student = s;
		courcenamecombo.setSelectedItem(s.getNomecurso());
		semoryearcombo.setSelectedIndex(s.getSemouano());
		rollnumberfield.setText(s.getNrestudante() + "");
		rollnumberfield.setEditable(false);
		optionalsubjectcombo.setSelectedItem(s.getDisciplinaopcional());
		firstnamefield.setText(s.getPrimeiroNome());
		lastnamefield.setText(s.getUltimonome());
		emailidfield.setText(s.getEmailId());
		contactnumberfield.setText(s.getContactNumber());
		birthdatespinner.setValue(s.getBirthDateInDateFormat());

		gendercombo.setSelectedItem(s.getGender()+"");
		statefield.setText(s.getState());
		cityfield.setText(s.getCity());
		fathernamefield.setText(s.getNomepai());
		fatheroccupationfield.setText(s.getOcupacaopai());
		mothernamefield.setText(s.getNomemae());
		motheroccupationfield.setText(s.getOcupacaomae());
		profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(100, 120)));
		headerlabel.setText("Editar Detalhes do estudante");
		addstudentbutton.setText("Actualizar Estudante");
		courcenamecombo.setEnabled(false);
		semoryearcombo.setEnabled(false);
		rollnumberfield.setEditable(false);
//		optionalsubjectcombo.setEnabled(false);
		 courcenamecombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 semoryearcombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });

	}

	public AddEstudante(JTable table, PainelEstudante studentpanel) {
		this();
		this.sp = studentpanel;
	}
	String uid ;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Errorlabel.setVisible(false);
		Errorlabel.setText("Preenchimento Obrigatorio !");
		if (e.getSource() == choosefilebutton)
		{

			FileDialog fd = new FileDialog(this, "Escolha uma foto de perfil", FileDialog.LOAD);
			fd.setDirectory(".\\Students Profile pic");
			fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");
			fd.setLocationRelativeTo(null);
			fd.setVisible(true);
			String strfilename = fd.getFile();
			imagepath = fd.getDirectory() + strfilename;
		

			if(fd.getFile()!=null) 
			{
				file = new File(imagepath);
				long bytes = file.length();
				if (bytes < 1048576) {

					try 
					{
						filesize.setText(bytes / 1024 + " KB");
						filesizenote.setForeground(new Color(46, 139, 27));
						filesizenote.setText("Image size < 1024 KB");
						Image image = ImageIO.read(file).getScaledInstance(100, 120, Image.SCALE_SMOOTH);
						profilepiclabel.setIcon(new ImageIcon(image));
						filename.setText(file.getName());	

					} 
					catch (IOException ex) {
						file = null;
						// TODO Auto-generated catch block
						filename.setText("Nenhum ficheito escolhido");
						filesize.setText("");
						filesizenote.setForeground(Color.red);
						filesizenote.setText("Image Not supported");
						ex.printStackTrace();
					}
				} 
				else {
					file = null;
					filename.setText("Nenhum ficheito escolhido");
					filesize.setText("");
					filesizenote.setForeground(Color.red);
					filesizenote.setText("tamanho da imagem>1 MB");
				}

			}
		}

		if (e.getSource() == courcenamecombo)
		{
			courcenamecombo.setFocusable(false);
			rollnumberfield.setText("");
			rollnumberfield.setEditable(true);
			optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
			rollnumberfield.setText("");
			if (courcenamecombo.getSelectedIndex() == 0) 
			{
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));

			}
			else 
			{
				String cource = (String) courcenamecombo.getSelectedItem();
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DadosCurso().getSemorYear(cource)));
			}

		}
		if (e.getSource() == semoryearcombo && semoryearcombo.getSelectedIndex() > 0) 
		{
			String courcecode = new DadosCurso().getCourcecode(courcenamecombo.getSelectedItem() + "");
			int sem = semoryearcombo.getSelectedIndex();
			long rollnumber = 0;
			if (student != null && courcecode.equals(student.getCodigoCurso()) && sem == student.getSemouano()) 
			{
				rollnumber = student.getNrestudante();

			} 
			else 
			{
				rollnumber = new DadosNumeroEstudante().getRollNumber(courcecode, sem);
			}
			if (rollnumber == 0) 
			{
				rollnumberfield.setText("");
				rollnumberfield.setEditable(true);

			} 
			else
			{
				rollnumberfield.setText(rollnumber + "");
				rollnumberfield.setEditable(false);

			}
			String[] totalopsub = new DadosDisciplina().getOptionalSubject(courcecode, sem);
			if (totalopsub != null) 
			{
				optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(totalopsub));
			} 
			else 
			{
				optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Sem disciplinas opcionais" }));

			}
		}

		if (e.getSource() == addstudentbutton)
		{

			if (courcenamecombo.getSelectedIndex() == 0) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(courcenamecombo.getX(), courcenamecombo.getY() + courcenamecombo.getHeight(), 400,
						26);
			} 
			else if (semoryearcombo.getSelectedIndex() == 0)
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(semoryearcombo.getX(), semoryearcombo.getY() + semoryearcombo.getHeight(), 400,
						26);
			}
			else if (rollnumberfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(), 400,
						26);
			}

			else if (optionalsubjectcombo.getSelectedIndex() == 0
					&& !optionalsubjectcombo.getSelectedItem().toString().equals("No Optional Subject")) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(optionalsubjectcombo.getX(),
						optionalsubjectcombo.getY() + optionalsubjectcombo.getHeight(), 400, 26);
			} 
			else if (firstnamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(firstnamefield.getX(), firstnamefield.getY() + firstnamefield.getHeight(), 400,
						26);
			} 
			else if (lastnamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(lastnamefield.getX(), lastnamefield.getY() + lastnamefield.getHeight(), 400, 26);
			} 
			else if (emailidfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(emailidfield.getX(), emailidfield.getY() + emailidfield.getHeight(), 400, 26);
			} 
			else if (contactnumberfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(contactnumberfield.getX(),contactnumberfield.getY() + contactnumberfield.getHeight(), 400, 26);
			} 
			else if (gendercombo.getSelectedIndex() == 0)
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(gendercombo.getX(), gendercombo.getY() + gendercombo.getHeight(), 400, 26);
			}
			else if (statefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(statefield.getX(), statefield.getY() + statefield.getHeight(), 400, 26);
			} 
			else if (cityfield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(cityfield.getX(), cityfield.getY() + cityfield.getHeight(), 400, 26);
			} 
			else if (fathernamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(fathernamefield.getX(), fathernamefield.getY() + fathernamefield.getHeight(), 400,26);
			}
			else if (fatheroccupationfield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(fatheroccupationfield.getX(),fatheroccupationfield.getY() + fatheroccupationfield.getHeight(), 400, 26);
			} 
			else if (mothernamefield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(mothernamefield.getX() + 120, mothernamefield.getY() + mothernamefield.getHeight(),400, 26);
			} 
			else if (motheroccupationfield.getText().isEmpty()|| motheroccupationfield.getText().equals(" Mother Occupation")) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(motheroccupationfield.getX(),motheroccupationfield.getY() + motheroccupationfield.getHeight(), 400, 26);
			}
			else {
				try 
				{
					Estudante s = new Estudante();

					s.setCodigoCurso(new DadosCurso().getCourcecode(courcenamecombo.getSelectedItem() + ""));
					s.setSemouano(semoryearcombo.getSelectedIndex());
					s.setNrestudante(Long.parseLong(rollnumberfield.getText()));
					int rollnumberexist = new DadosNumeroEstudante().isExist(s.getCodigoCurso(), s.getSemouano(), s.getNrestudante());
					if (rollnumberexist > 0)
					{
						if (!(student != null && student.getNrestudante()==s.getNrestudante())) 
						{
							throw new RollNumberAvailableException();
						}

					}
					s.setDisciplinaopcional(optionalsubjectcombo.getSelectedItem().toString());
					s.setPrimeiroNome(firstnamefield.getText());
					s.setUltimonome(lastnamefield.getText());
					s.setEmailId(emailidfield.getText());
					s.setContactNumber(contactnumberfield.getText());

					s.setGender(gendercombo.getSelectedItem() + "");
					Date date = (Date) birthdatespinner.getValue();
					s.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
					s.setState(statefield.getText());
					s.setCity(cityfield.getText());
					s.setNomepai(fathernamefield.getText());
					s.setNomemae(mothernamefield.getText());
					s.setOcupacaopai(fatheroccupationfield.getText());
					s.setOcupacaomae(motheroccupationfield.getText());
					s.gerarDataadmissao();
					s.generateUserId();
					 uid = 	s.generateUserId();
					if(student!=null)
					{
						s.setPassword(student.getPassword());
						s.setDataadmissao(student.getDataadmissao());
						s.setLastLogin(student.getLastLogin());
					}
					if (file!=null) {
						s.setProfilePic(ImageIO.read(file));
					} 
					else if(student!=null){
						s.setProfilePic(student.getProfilePic());
					} else {
						 file = new File("./assets/profilepicicon.jpg");
							s.setProfilePic(ImageIO.read(file));
					}
					int result = 0;
					if (sp != null) 
					{
						result = new DadosEstudante().addStudent(s);
					} 
					else if (am != null && student != null) 
					{
						result = new DadosEstudante().updateStudentData(student, s);
					}
					if (result > 0) {

						
						if (sp != null) 
						{
							if (sp.photoviewscrollpane != null && sp.photoviewscrollpane.isVisible()) 
							{
								sp.createtablemodel();
								sp.createphotopanel();
							}
							else 
							{
								sp.createtablemodel();
							}

						} 
						else if (am != null && student != null) {

							am.viewstudentpanel.setVisible(false);
							am.viewstudentpanel = new PainelVerEstudante(s, am, am.viewstudentpanel.lastpanel);
							am.viewstudentpanel.setVisible(true);
							am.viewstudentpanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewstudentpanel);

						}
						// eviar email para o estudante
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
					      message.setSubject("Confirmação de Registro");//Assunto
					      message.setText(" Caro(a) estudante "+""+firstnamefield.getText()+""+" Podera aceder ao nosso sistema usando os seguintes dados Username:"+" "+uid+" "+"Password:"
					      		+ " "+(new SimpleDateFormat("dd-MM-yyyy").format(date))+"\r\n"+"Melhores Cumprimentos"+"\r\n"+"Equipe academica"+"\r\n"+" ");
					   
					      /**Método para enviar a mensagem criada*/
					      Transport.send(message);

					      System.out.println("Feito!!!");

					     } catch (MessagingException e1) {
					        throw new RuntimeException(e1);
					    }
						this.dispose();
					}

				} catch (NumberFormatException exp) {
					Errorlabel.setVisible(true);
					Errorlabel.setText("Characters are not allowed!");
					Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
							400, 26);
				} catch (RollNumberAvailableException exp) {
					Errorlabel.setVisible(true);
					Errorlabel.setText("RollNumber already Exist...!");
					Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
							400, 26);
					exp.printStackTrace();

				
				} catch (Exception e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();
				}
				
				
			}
		}

	}

}

@SuppressWarnings("serial")
class RollNumberAvailableException extends Exception {
	public RollNumberAvailableException() {
		super("Roll number already available");
	}
}