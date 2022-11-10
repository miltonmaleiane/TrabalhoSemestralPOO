package disciplina;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JpainelScroll;
import javax.swing.Jtabela;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tabela.DefaulttabelaCellRenderer;

import admin.AdminPrincipal;
import curso.DadosCurso;
import docente.DocenteMain;
import estudante.EstudanteMain;
import net.proteanit.sql.DbUtils;



@SuppressWarnings("serial")
public class PainelDisciplina extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private JComboBox<String> cbNomecurso;
	private JComboBox<String> cbSemouAno;
	private JButton addDisciplina;
	private String CodCurso[];
	private Jtabela tabela;
	private JpainelScroll painelScroll;
	private JLabel lbSelCurso;
	private JLabel lbSelSem;
	private JLabel lbCabecalho;
	private JButton btVoltar;
	
	public PainelDisciplina(AdminPrincipal am)
	{
		this();
	}
	public PainelDisciplina(EstudanteMain sm)
	{
		this();
		cbNomecurso.setVisible(false);
		cbSemouAno.setVisible(false);
		addDisciplina.setVisible(false);
		lbSelSem.setVisible(false);
		lbSelCurso.setVisible(false);
	
		painelScroll.setVisible(true);
		lbCabecalho.setBounds(10, 0, 1096, 183);
		lbCabecalho.setText(" Diciplinas");
		painelScroll.setBounds(10, lbCabecalho.getY()+lbCabecalho.getHeight()+20, 1096, this.getHeight()-lbCabecalho.getHeight()-20);
		lbCabecalho.setHorizontalAlignment(JLabel.LEFT);
		this.createtabelamodel(sm.s.getCodigoCurso(),sm.s.getSemouano() );
		
	}
	public PainelDisciplina(DocenteMain fm)
	{
		this();
		cbNomecurso.setVisible(false);
		cbSemouAno.setVisible(false);
		addDisciplina.setVisible(false);
		lbSelSem.setVisible(false);
		lbSelCurso.setVisible(false);
	
		painelScroll.setVisible(true);
		lbCabecalho.setBounds(10, 0, 1096, 183);
		lbCabecalho.setText(" Minhas Disciplinas");
		painelScroll.setBounds(10, lbCabecalho.getY()+lbCabecalho.getHeight()+20, 1096, this.getHeight()-lbCabecalho.getHeight()-20);
		lbCabecalho.setHorizontalAlignment(JLabel.LEFT);
		this.createtabelamodel(fm.f.getCodigoCurso(),fm.f.getSemouano() );
		
	}
	public PainelDisciplina(DocenteMain fm,JComponent lastpanel)
	{
		this(fm);
		lbCabecalho.setLayout(null);
		btVoltar = new JButton("Voltar");
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorder(new EmptyBorder(0, 0, 0, 0));
		btVoltar.setIcon(new ImageIcon(".\\assets\\back.png"));
		btVoltar.setFocusable(false);
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btVoltar.setBackground(new Color(32, 178, 170));
		btVoltar.setBounds(10,141, 88, 36);
		lbCabecalho.add(btVoltar);
		
		btVoltar.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
	public PainelDisciplina(EstudanteMain sm,JComponent lastpanel)
	{
		this(sm);
		lbCabecalho.setLayout(null);
		btVoltar = new JButton("Voltar");
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorder(new EmptyBorder(0, 0, 0, 0));
		btVoltar.setIcon(new ImageIcon(".\\assets\\back.png"));
		btVoltar.setFocusable(false);
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btVoltar.setBackground(new Color (47, 173, 102));
		btVoltar.setBounds(10, 141, 88, 36);
		lbCabecalho.add(btVoltar);
		
		btVoltar.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
			
	private PainelDisciplina()
	{
		new Timer(100,this);
//		timer.start();
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		lbCabecalho = new JLabel("Gestão de Disciplinas");
		lbCabecalho.setBackground(new Color (47, 173, 102));
		lbCabecalho.setHorizontalAlignment(SwingConstants.CENTER);
		lbCabecalho.setForeground(new Color(255, 255, 255));
		lbCabecalho.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lbCabecalho.setBounds(10, 0, 1096, 66);
		lbCabecalho.setOpaque(true);
		add(lbCabecalho);
		
		lbSelCurso = new JLabel("Selecionar Curso  ");
		lbSelCurso.setHorizontalAlignment(SwingConstants.LEFT);
		lbSelCurso.setForeground(Color.DARK_GRAY);
		lbSelCurso.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lbSelCurso.setBounds(30, 89, 248, 42);
		add(lbSelCurso);
		
		 cbNomecurso = new JComboBox<String>(new DadosCurso().getCourceName());
		cbNomecurso.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbNomecurso.setBackground(Color.WHITE);
		cbNomecurso.setFocusable(false);
		cbNomecurso.addActionListener(this);
		cbNomecurso.setBounds(300, 88, 806, 42);
		add(cbNomecurso);
		
		lbSelSem = new JLabel("Selecionar Semestre/Ano  ");
		lbSelSem.setHorizontalAlignment(SwingConstants.LEFT);
		lbSelSem.setBackground(Color.DARK_GRAY);
		lbSelSem.setForeground(Color.DARK_GRAY);
		lbSelSem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lbSelSem.setBounds(30, 165, 248, 40);
		add(lbSelSem);
		
		 cbSemouAno = new JComboBox<String>();
		 cbSemouAno.setMaximumRowCount(16);
		cbSemouAno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbSemouAno.setBackground(Color.WHITE);
		cbSemouAno.setFocusable(false);
		cbSemouAno.addActionListener(this);
		cbSemouAno.setBounds(300, 165, 806, 42);
		add(cbSemouAno);
		
		CodCurso=new DadosCurso().getCodCurso();
		
		addDisciplina = new JButton("Adicionar nova Disciplina");
		addDisciplina.setBorder(new EmptyBorder(0, 0, 0, 0));
		addDisciplina.addActionListener(this);
		addDisciplina.setForeground(new Color(255, 255, 255));
		addDisciplina.setBackground(new Color(12, 69, 86));
		addDisciplina.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addDisciplina.setBounds(830, 242,200, 37);
		addDisciplina.setVisible(false);
		addDisciplina.setFocusable(false);
		addDisciplina.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(addDisciplina);
		
		painelScroll = new JpainelScroll();
		painelScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		painelScroll.setBounds(10, 311, 1096, 361);
		for(Component c:painelScroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(painelScroll);

		tabela = new Jtabela();
		tabela.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		tabela.setBackground(Color.white);
		tabela.setRowHeight(40);
		
		tabela.gettabelaHeader().setBackground(new Color(47, 173, 102));
		tabela.gettabelaHeader().setForeground(Color.white);
		tabela.gettabelaHeader().setFont(new Font("Arial",Font.BOLD,20));
		tabela.setFont(new Font("Segoe UI",Font.PLAIN,20));
		tabela.gettabelaHeader().setPreferredSize(new Dimension(50,40));
		tabela.setDragEnabled(false);
		
		tabela.setGridColor(Color.LIGHT_GRAY);
		tabela.gettabelaHeader().setReorderingAllowed(false);		
		
		tabela.setEnabled(false);
		painelScroll.setViewportView(tabela);
		painelScroll.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if(cbNomecurso.getSelectedIndex()==0 ||cbSemouAno.getSelectedIndex()==0)
		{
			painelScroll.setVisible(false);
		}
			if(e.getSource()==cbNomecurso)
			{
				
				
				painelScroll.setVisible(false);
				addDisciplina.setVisible(false);
				if(cbNomecurso.getSelectedIndex()==0)
				{
					cbSemouAno.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				}
				else
				{
				 String cource=(String) cbNomecurso.getSelectedItem();

				 cbSemouAno.setModel(new DefaultComboBoxModel<String>(new DadosCurso().getSemorYear(cource)));
				}
			 
			}
			 if(e.getSource()==cbSemouAno)
			 {
				if(cbNomecurso.getSelectedIndex()>0 && cbSemouAno.getSelectedIndex()>0)
				{
				 painelScroll.setVisible(true);
				 int sem=cbSemouAno.getSelectedIndex();
				 int index=cbNomecurso.getSelectedIndex();
				 this.createtabelamodel(CodCurso[index-1], sem);
					
				}
				else if(cbSemouAno.getSelectedIndex()==0)
				{
					painelScroll.setVisible(false);
					addDisciplina.setVisible(false);
				}
				
				else
				{
					painelScroll.setVisible(false);
				}
					
				 
			 }
			 if(cbNomecurso.getSelectedIndex()>0 && cbSemouAno.getSelectedIndex()>0)
			 {
				 addDisciplina.setVisible(true);
			 }

			 if(e.getSource()==addDisciplina)
			 {
				 int sem=cbSemouAno.getSelectedIndex();
				 int index=cbNomecurso.getSelectedIndex();
				 AddDisciplina sd=new AddDisciplina(CodCurso[index-1],sem,tabela);
				 sd.setLocationRelativeTo(null);
				 sd.setVisible(true);
				 
				 painelScroll.setVisible(true);
				
			 }
	}
	public void createtabelamodel(String CodCurso,int sem)
	{
		 ResultSet st=new DadosDisciplina().getSubjectinfo(CodCurso,sem);
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
			
	}

}
