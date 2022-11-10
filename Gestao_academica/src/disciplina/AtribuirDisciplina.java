package disciplina;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.AdminPrincipal;
import curso.DadosCurso;
import docente.Docente;
import docente.DadosDocente;
import docente.PainelVerDocente;


@SuppressWarnings("serial")
public class AtribuirDisciplina extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	Docente f=null;
	static AtribuirDisciplina dialog;
	private JComboBox<String> cbNomecurso,semoryearcombo,subjectnamecombo,positioncombo;
	private JButton assignsubjectbutton;
	private AdminPrincipal am;
	JLabel Errorlabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AtribuirDisciplina(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param f 
	 * @wbp.parser.constructor
	 */
	private AtribuirDisciplina(Docente f) {
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		this.setLocation(450, 100);
		
		getContentPane().setBackground(Color.WHITE);
		this.f=f;
		setSize(521, 580);
		getContentPane().setLayout(null);
		
		JLabel headerlabel = new JLabel("Atribuir Disciplina");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBackground(new Color (47, 173, 102));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		headerlabel.setBounds(0, 0, 505, 39);
		getContentPane().add(headerlabel);
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(34, 50, 98, 111);

		lblImage.setIcon(new ImageIcon(f.getProfilePic(lblImage.getWidth(), lblImage.getHeight())));
		lblImage.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		lblImage.setOpaque(true);
		lblImage.setBackground(new Color(240, 255, 255));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblImage);
		
		JLabel lblFacultyName = new JLabel("Nome do Docente  :  "+f.getFacultyName());
		lblFacultyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacultyName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblFacultyName.setBounds(156, 77, 293, 29);
		getContentPane().add(lblFacultyName);
		
		JLabel lblDegree = new JLabel("Qualificação :  "+f.getQualification());
		lblDegree.setHorizontalAlignment(SwingConstants.LEFT);
		lblDegree.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDegree.setBounds(156, 108, 293, 26);
		getContentPane().add(lblDegree);
		
		JLabel lblExperience = new JLabel("Experiencia  :  "+f.getExperience());
		lblExperience.setHorizontalAlignment(SwingConstants.LEFT);
		lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblExperience.setBounds(156, 138, 293, 26);
		getContentPane().add(lblExperience);
		
		JLabel lblFacultyId = new JLabel("ID Docente  : "+f.getFacultyId());
		lblFacultyId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacultyId.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblFacultyId.setBounds(156, 50, 323, 22);
		getContentPane().add(lblFacultyId);
		
		cbNomecurso = new JComboBox<String>(new DadosCurso().getCourceName());
		cbNomecurso.setFocusable(false);
		cbNomecurso.addActionListener(this);
		cbNomecurso.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		cbNomecurso.setBackground(new Color(255, 255, 255));
		cbNomecurso.setBounds(156, 199, 338, 39);
		getContentPane().add(cbNomecurso);
		
		JLabel lblCourceName = new JLabel("Nome do Curso :");
		lblCourceName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCourceName.setFont(new Font("Candara", Font.PLAIN, 18));
		lblCourceName.setBounds(10, 199, 138, 39);
		getContentPane().add(lblCourceName);
		
		JLabel lblSelectSemyear = new JLabel("Semstre/Ano  :");
		lblSelectSemyear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectSemyear.setFont(new Font("Candara", Font.PLAIN, 18));
		lblSelectSemyear.setBounds(10, 265, 138, 37);
		getContentPane().add(lblSelectSemyear);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFocusable(false);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setBounds(156, 265, 338, 39);
		getContentPane().add(semoryearcombo);
		
		JLabel lblSubject = new JLabel("Disciplina :");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubject.setFont(new Font("Candara", Font.PLAIN, 18));
		lblSubject.setBounds(10, 332, 138, 37);
		getContentPane().add(lblSubject);
		
		subjectnamecombo = new JComboBox<String>();
		subjectnamecombo.addActionListener(this);
		subjectnamecombo.setFocusable(false);
		subjectnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		subjectnamecombo.setBackground(Color.WHITE);
		subjectnamecombo.setBounds(156, 332, 338, 39);
		getContentPane().add(subjectnamecombo);
		
		JLabel lblPosition = new JLabel("Tipo de Docente  :");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Candara", Font.PLAIN, 18));
		lblPosition.setBounds(10, 397, 138, 37);
		getContentPane().add(lblPosition);
		
		positioncombo = new JComboBox<String>();
		positioncombo.setFocusable(false);
		positioncombo.addActionListener(this);
		positioncombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Selecionar---", "Regente", "Assistente", "Monitor"}));
		positioncombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		positioncombo.setBackground(Color.WHITE);
		positioncombo.setBounds(156, 397, 338, 39);
		getContentPane().add(positioncombo);
		
		assignsubjectbutton = new JButton("Atribuir Disciplina");
		assignsubjectbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		assignsubjectbutton.setFocusable(false);
		assignsubjectbutton.addActionListener(this);
		assignsubjectbutton.setBackground(new Color(12, 69, 86));
		assignsubjectbutton.setForeground(new Color(255, 255, 255));
		assignsubjectbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		assignsubjectbutton.setBounds(356, 485, 139, 37);
		assignsubjectbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(assignsubjectbutton);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 462, 505, 8);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label_1.setBounds(0, 172, 505, 8);
		getContentPane().add(label_1);
		
		Errorlabel = new JLabel("Preenchimento obrigatorio  !");
		Errorlabel.setVisible(false);
		Errorlabel.setForeground(Color.RED);
		Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
		Errorlabel.setBounds(156, 236, 215, 22);
		getContentPane().add(Errorlabel);
	
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		if(!f.getCodigoCurso().equals("Not Assigned"))
		{
			this.setDataInComboBox();
		}
		
	}

	public AtribuirDisciplina(Docente f, AdminPrincipal am) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.am=am;
	
	}
	public void setDataInComboBox()
	{
		cbNomecurso.setSelectedItem(new DadosCurso().getcourcename(f.getCodigoCurso()));
		 semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DadosCurso().getSemorYear(cbNomecurso.getSelectedItem()+"")));
			String[] totalsub=new DadosDisciplina().getSubjectinCource(f.getCodigoCurso(),f.getSemouano());
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
			semoryearcombo.setSelectedIndex(f.getSemouano());
			subjectnamecombo.setSelectedItem(f.getSubject());
			positioncombo.setSelectedItem(f.getPosition());
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Errorlabel.setVisible(false);
		if(e.getSource()==cbNomecurso)
		{
			cbNomecurso.setFocusable(false);
			
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
			if(cbNomecurso.getSelectedIndex()==0)
			{
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				
			}
			else
			{
			 String cource=(String) cbNomecurso.getSelectedItem();

			 semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DadosCurso().getSemorYear(cource)));
			}
		 
		}
		if(e.getSource()==semoryearcombo && semoryearcombo.getSelectedIndex()>0)
		{
			 String cource=(String) cbNomecurso.getSelectedItem();
			
			String[] totalsub=new DadosDisciplina().getSubjectinCource(new DadosCurso().getCourcecode(cource), semoryearcombo.getSelectedIndex());
			if(totalsub!=null)
			{
				subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
			}
			else
			{
				subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {"No Subject"}));
				
			}
		}
		if(e.getSource()==assignsubjectbutton)
		{
			if(cbNomecurso.getSelectedIndex()==0)
			{
				showerror(cbNomecurso);
			}
			else if(semoryearcombo.getSelectedIndex()==0)
			{
				showerror(semoryearcombo);
			}
			else if(subjectnamecombo.getSelectedIndex()==0)
			{
				showerror(subjectnamecombo);
			}
			else if(positioncombo.getSelectedIndex()==0)
			{
				showerror(positioncombo);
			}
			else
			{
				Docente fnew=new Docente();
			
				fnew.setCodigoCurso(new DadosCurso().getCourcecode(cbNomecurso.getSelectedItem()+""));
				fnew.setPosition(positioncombo.getSelectedItem()+"");
				fnew.setSemouano(semoryearcombo.getSelectedIndex());
				fnew.setSubject(subjectnamecombo.getSelectedItem()+"");
				fnew.setFacultyId(f.getFacultyId());
				fnew.setFacultyName(f.getFacultyName());
				int result=new DadosDocente().assignSubject(f,fnew);
				if(result>0)
				{
					if(am!=null)
					{
						if(am.assignsubjectpanel!=null&&am.assignsubjectpanel.isVisible())
						{
							am.assignsubjectpanel.createtablemodel();
						}
						
						else if(am.viewfacultypanel!=null&&am.viewfacultypanel.isVisible())
						{
							System.out.println("updateing ");
							am.viewfacultypanel.setVisible(false);
							am.viewfacultypanel=new PainelVerDocente(new DadosDocente().getFacultyInfobyId(f.getFacultyId()),am,am.viewfacultypanel.lastpanel);
							am.viewfacultypanel.setVisible(true);
							am.viewfacultypanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewfacultypanel);
						}
					}
					
					this.dispose();
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
