package disciplina;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Jtabela;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.tabela.DefaulttabelaCellRenderer;

import net.proteanit.sql.DbUtils;



@SuppressWarnings("serial")
public class AddDisciplina extends JDialog implements ActionListener
{

	private final JPanel painelConteudo = new JPanel();
	private JTextField codDisccp;
	private JTextField cpNomeDisc;
	private JTextField cpteste1;
	private JTextField cpteste2;
	private JButton addDisc;
	private JComboBox<String> cbTipoCurso;
	private String CodCurso;
	private int semouano;
	private Jtabela tabela;
	private JLabel lblErro;
	private static AddDisciplina dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 dialog = new AddDisciplina("IT",1,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDisciplina(String CodCurso,int sem,Jtabela datatabela) {
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(0, 128, 128));
		this.tabela=datatabela;
		this.semouano=sem;
		this.CodCurso=CodCurso;
		setResizable(false);
		setSize(518, 488);
		getContentPane().setLayout(new BorderLayout());
		painelConteudo.setBackground(new Color(255, 255, 255));
		painelConteudo.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painelConteudo, BorderLayout.CENTER);
		painelConteudo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cod da Disciplina");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(30, 71, 152, 35);
		painelConteudo.add(lblNewLabel);
		
		JLabel lblSubjectName = new JLabel("Nome da Disciplina");
		lblSubjectName.setForeground(Color.DARK_GRAY);
		lblSubjectName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSubjectName.setBounds(30, 134, 152, 35);
		painelConteudo.add(lblSubjectName);
		
		JLabel lblCourceType = new JLabel("Tipo");
		lblCourceType.setForeground(Color.DARK_GRAY);
		lblCourceType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCourceType.setBounds(30, 198, 152, 35);
		painelConteudo.add(lblCourceType);
		
		JLabel lblTheoryMarks = new JLabel("Aulas Teóricas");
		lblTheoryMarks.setForeground(Color.DARK_GRAY);
		lblTheoryMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTheoryMarks.setBounds(30, 265, 152, 35);
		painelConteudo.add(lblTheoryMarks);
		
		JLabel lblPracticalMarks = new JLabel("Aulas Prácticas");
		lblPracticalMarks.setForeground(Color.DARK_GRAY);
		lblPracticalMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPracticalMarks.setBounds(30, 332, 152, 35);
		painelConteudo.add(lblPracticalMarks);
		
		codDisccp = new JTextField();
		codDisccp.setEditabela(false);
		codDisccp.setText(new DadosDisciplina().createSubjectcode(CodCurso, sem));
		
		codDisccp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		codDisccp.setBounds(180, 70, 300, 40);
		painelConteudo.add(codDisccp);
		codDisccp.setColumns(10);
		
		cpNomeDisc = new JTextField();
		cpNomeDisc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					cpNomeDisc.setFocusable(false);
					cpteste1.setFocusable(true);
				}
			}
		});
		cpNomeDisc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cpNomeDisc.setFocusable(true);
			}
		});
		cpNomeDisc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cpNomeDisc.setColumns(10);
		cpNomeDisc.setBounds(180, 136, 300, 40);
		painelConteudo.add(cpNomeDisc);
		
		cpteste1 = new JTextField();
		cpteste1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				cpteste1.setFocusable(false);
				cpteste2.setFocusable(true);
				}
			}
		});
		cpteste1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cpteste1.setFocusable(true);
			}
		});
		cpteste1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cpteste1.setColumns(10);
		
		cpteste1.setBounds(180, 267, 300, 40);
		painelConteudo.add(cpteste1);
		
		cpteste2 = new JTextField();
		cpteste2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cpteste2.setFocusable(true);
			}
		});
		cpteste2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cpteste2.setColumns(10);
		cpteste2.setBounds(180, 334, 300, 40);
		painelConteudo.add(cpteste2);
		
		 cbTipoCurso = new JComboBox<String>();
		cbTipoCurso.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbTipoCurso.setModel(new DefaultComboBoxModel<String>(new String[] {"---selecionar---", "nuclear", "opcional"}));//nao mexer por enquanto
		cbTipoCurso.setFocusable(false);
		cbTipoCurso.setBackground(Color.WHITE);
		cbTipoCurso.setBounds(180, 199, 300, 40);
		painelConteudo.add(cbTipoCurso);
		
		JLabel headerlabel = new JLabel("  Adicionar Disciplina");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBackground(new Color(47, 173, 102));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBounds(0, 0, 512, 44);
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		painelConteudo.add(headerlabel);
		
		 addDisc = new JButton("Adicionar");
		 addDisc.setBorder(new EmptyBorder(0, 0, 0, 0));
		addDisc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				HandCursor();
				
			}
			public void mouseExited(MouseEvent e)
			{
				DefaultCursor();
			}
		});
		
		addDisc.setBackground(new Color(12, 69, 86));
		addDisc.setForeground(Color.WHITE);
		addDisc.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addDisc.setBounds(363, 413, 139, 33);
		addDisc.addActionListener(this);
		addDisc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addDisc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addDisc.setFocusable(false);
		
		painelConteudo.add(addDisc);
		
		JLabel borderlabel = new JLabel("");
		borderlabel.setBounds(0, 388, 512, 14);
		painelConteudo.add(borderlabel);
		borderlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		
		 lblErro = new JLabel("Preenchimento obrigatorio !");
		 lblErro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblErro.setForeground(Color.RED);
		lblErro.setFont(new Font("Candara", Font.PLAIN, 17));
		lblErro.setBounds(172, 107, 331, 30);
		lblErro.setVisible(false);
		painelConteudo.add(lblErro);
		
	}
	public void HandCursor()
	{
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public void DefaultCursor()
	{
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		lblErro.setVisible(false);
		lblErro.setText("This is required question..!=-");
		 if(cpNomeDisc.getText().isEmpty())
		{
			lblErro.setVisible(true);
			lblErro.setBounds(cpNomeDisc.getX(), cpNomeDisc.getY()+cpNomeDisc.getHeight()-5, 331, 30);
		}
		else if(cbTipoCurso.getSelectedIndex()==0)
		{
			lblErro.setVisible(true);
			lblErro.setBounds(cbTipoCurso.getX(), cbTipoCurso.getY()+cbTipoCurso.getHeight()-5, 331, 30);
		}
	
		else if(cpteste1.getText().isEmpty()||cpNomeDisc.getText().isEmpty())
		{
			lblErro.setVisible(true);
			lblErro.setBounds(cpteste1.getX(), cpteste1.getY()+cpteste1.getHeight()-5, 331, 30);
		}
		else if(cpteste2.getText().isEmpty())
		{
			lblErro.setVisible(true);
			lblErro.setBounds(cpteste2.getX(), cpteste2.getY()+cpteste2.getHeight()-5, 331, 30);
		}
		else if(new DadosDisciplina().isExist(CodCurso,semouano,cpNomeDisc.getText()))
		{
			lblErro.setVisible(true);
			lblErro.setBounds(cpNomeDisc.getX(), cpNomeDisc.getY()+cpNomeDisc.getHeight()-5, 331, 30);
			lblErro.setText("Nome da cadeira ja existe..!");
		}
		else
		{
			if(e.getSource()==addDisc)
			{
				String numbererror="";
				try
				{
				String subjectcode=codDisccp.getText();
				String subjectname=cpNomeDisc.getText();
			
				String subjecttype=(String) cbTipoCurso.getSelectedItem();
				numbererror="theorymarks";
				int theorymarks=Integer.parseInt(cpteste1.getText());
				numbererror="practicalmarks";
				int practicalmarks=Integer.parseInt(cpteste2.getText());
				Disciplina su=new Disciplina();
				su.setSubjectName(subjectname);
				su.setSubjectCode(subjectcode);
				su.setMaxPracticalMarks(practicalmarks);
				su.setCodigoCurso(CodCurso);
				su.setSemouano(semouano);
				su.setSubjectType(subjecttype);
				su.setMaxTheoryMarks(theorymarks);
				int result=new DadosDisciplina().addDisc(su);
				if(result==1)
				{
					ResultSet st=new DadosDisciplina().getSubjectinfo(CodCurso,semouano);
					tabela.setModel(DbUtils.resultSetTotabelaModel(st));
					DefaulttabelaCellRenderer centerRenderer = new DefaulttabelaCellRenderer();
					 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
					 tabela.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
					 tabela.getColumnModel().getColumn(0).setMaxWidth(200);
					
						tabela.getColumnModel().getColumn(1).setMaxWidth(400);
						tabela.getColumnModel().getColumn(2).setMaxWidth(200);
						tabela.getColumnModel().getColumn(3).setMaxWidth(200);
						tabela.getColumnModel().getColumn(4).setMaxWidth(200);
						tabela.getColumnModel().getColumn(5).setMaxWidth(200);
					this.dispose();
				}
				}
				catch(NumberFormatException exp)
				{
					if(numbererror.equals("teste1"))
					{
						lblErro.setBounds(cpteste1.getX(), cpteste1.getY()+cpteste1.getHeight(), 331, 30);
					}
					if(numbererror.equals("teste2"))
					{
						lblErro.setBounds(cpteste2.getX(), cpteste2.getY()+cpteste2.getHeight(), 331, 30);
					}
					lblErro.setVisible(true);
					lblErro.setText("Caracteres nao sao permitidos !");
				}
			}
		}
		
	
		
	}


}
