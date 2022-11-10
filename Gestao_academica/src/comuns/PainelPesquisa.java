package comuns;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Jtabela;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tabela.DefaulttabelaCellRenderer;

import admin.AdminPrincipal;
import curso.DadosCurso;
import docente.Docente;
import docente.DadosDocente;
import docente.DocenteMain;
import docente.PainelVerDocente;
import estudante.Estudante;
import estudante.DadosEstudante;
import estudante.EstudanteMain;
import estudante.PainelVerEstudante;
import net.proteanit.sql.DbUtils;


/*

 */


@SuppressWarnings("serial")
public class PainelPesquisa extends JPanel implements ActionListener {

	private Jtabela tabela;
	private JScrollPane tabelaviewscroll;
	private JTextField campopesquisa;
	private JComboBox<String> cbNomeCurso;
	private JComboBox<String> cbsemouano;
	private JComboBox<String> EstudanteDocente;

	private JButton searchbutton;
	/**
	 * Create the panel.
	 */
	public PainelPesquisa(AdminPrincipal am)
	{
		this();
		tabela.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
					if(EstudanteDocente.getSelectedIndex()==0)
					{
						Jtabela t=(Jtabela) e.getSource();
						int row=t.getSelectedRow();
						String courcecode=tabela.getValueAt(row,0)+"";
						String  strsem=tabela.getValueAt(row, 4)+"";
						int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
						String strroll=tabela.getValueAt(row, 1)+"";
						long rollnumber=Long.parseLong(strroll);
						Estudante s=new DadosEstudante().getStudentDetails(courcecode,sem,rollnumber);
						
						am.viewstudentpanel=new PainelVerEstudante(s,am,am.searchpanel);
						am.viewstudentpanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
					else 
					{
						Jtabela t=(Jtabela) e.getSource();
						int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
						Docente f=new DadosDocente().getFacultyInfobyId(fid);
						
						am.viewfacultypanel=new PainelVerDocente(f,am,am.searchpanel);
						am.viewfacultypanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewfacultypanel.setLocation(am.panelx,am.panely);
						am.viewfacultypanel.setVisible(true);
						am.viewfacultypanel.setFocusable(true);
						am.contentPane.add(am.viewfacultypanel);
					}
				}
			}
		});
		
		
	}
	public PainelPesquisa(DocenteMain fm)
	{
		this();
		cbNomeCurso.setSelectedItem(new DadosCurso().getcourcename(fm.f.getCodigoCurso()));
		cbNomeCurso.setEnabled(false);
		 cbNomeCurso.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 cbsemouano.setSelectedIndex(fm.f.getSemouano());
		 cbsemouano.setEnabled(false);
		 cbsemouano.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 this.createtabelamodel();
		 tabela.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
						if(EstudanteDocente.getSelectedIndex()==0)
						{
							Jtabela t=(Jtabela) e.getSource();
							int row=t.getSelectedRow();
							String courcecode=tabela.getValueAt(row,0)+"";
							String  strsem=tabela.getValueAt(row, 4)+"";
							int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
							String strroll=tabela.getValueAt(row, 1)+"";
							long rollnumber=Long.parseLong(strroll);
							Estudante s=new DadosEstudante().getStudentDetails(courcecode,sem,rollnumber);
							
							fm.viewstudentpanel=new PainelVerEstudante(s,fm,fm.searchpanel);
							fm.viewstudentpanel.setVisible(true);
							fm.searchpanel.setVisible(false);
							fm.viewstudentpanel.setLocation(fm.panelx,0);
							fm.viewstudentpanel.setVisible(true);
							fm.viewstudentpanel.setFocusable(true);
							fm.contentPane.add(fm.viewstudentpanel);
						}
						else 
						{
							Jtabela t=(Jtabela) e.getSource();
							int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
							Docente f=new DadosDocente().getFacultyInfobyId(fid);
							
							fm.viewfacultypanel=new PainelVerDocente(f,fm,fm.searchpanel);
							fm.viewfacultypanel.setVisible(true);
							fm.searchpanel.setVisible(false);
							fm.viewfacultypanel.setLocation(fm.panelx,fm.panely);
							fm.viewfacultypanel.setVisible(true);
							fm.viewfacultypanel.setFocusable(true);
							fm.contentPane.add(fm.viewfacultypanel);
						}
					}
				}
			});
			
	}
	public PainelPesquisa(EstudanteMain sm)
	{
		this();
		cbNomeCurso.setSelectedItem(new DadosCurso().getcourcename(sm.s.getCodigoCurso()));
		cbNomeCurso.setEnabled(false);
		 cbNomeCurso.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 cbsemouano.setSelectedIndex(sm.s.getSemouano());
		 cbsemouano.setEnabled(false);
		 cbsemouano.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 this.createtabelamodel();
		 tabela.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
						if(EstudanteDocente.getSelectedIndex()==0)
						{
							Jtabela t=(Jtabela) e.getSource();
							int row=t.getSelectedRow();
							String courcecode=tabela.getValueAt(row,0)+"";
							String  strsem=tabela.getValueAt(row, 4)+"";
							int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
							String strroll=tabela.getValueAt(row, 1)+"";
							long rollnumber=Long.parseLong(strroll);
							Estudante s=new DadosEstudante().getStudentDetails(courcecode,sem,rollnumber);
							
							sm.viewstudentpanel=new PainelVerEstudante(s,sm,sm.searchpanel);
							sm.viewstudentpanel.setVisible(true);
							sm.searchpanel.setVisible(false);
							sm.viewstudentpanel.setLocation(sm.panelx,0);
							sm.viewstudentpanel.setVisible(true);
							sm.viewstudentpanel.setFocusable(true);
							sm.contentPane.add(sm.viewstudentpanel);
						}
						else 
						{
							Jtabela t=(Jtabela) e.getSource();
							int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
							Docente f=new DadosDocente().getFacultyInfobyId(fid);
							
							sm.viewfacultypanel=new PainelVerDocente(f,sm,sm.searchpanel);
							sm.viewfacultypanel.setVisible(true);
							sm.searchpanel.setVisible(false);
							sm.viewfacultypanel.setLocation(sm.panelx,sm.panely);
							sm.viewfacultypanel.setVisible(true);
							sm.viewfacultypanel.setFocusable(true);
							sm.contentPane.add(sm.viewfacultypanel);
						}
					}
				}
			});
			
	}
	public PainelPesquisa() {
		this.setName("Search Panel");
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		tabelaviewscroll = new JScrollPane();
		tabelaviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabelaviewscroll.setBounds(10, 194, 1096, 500);
		for(Component c : tabelaviewscroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(tabelaviewscroll);
		
		
		
		tabela = new Jtabela();
		
		tabela.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
		tabela.gettabelaHeader().setBackground(new Color(47, 173, 102));
		tabela.gettabelaHeader().setForeground(Color.white);
		tabela.setSelectionBackground(new Color(240, 255, 255));
		tabela.gettabelaHeader().setFont(new Font("Arial",Font.BOLD,20));
		tabela.setFont(new Font("Segoe UI",Font.PLAIN,20));
		tabela.setModel(DbUtils.resultSetTotabelaModel(new DadosEstudante().getStudentinfo("")));
		tabela.gettabelaHeader().setPreferredSize(new Dimension(50,40));
		tabela.setFocusable(false);
		tabela.setDragEnabled(false);
		tabela.setRowHeight(40);
		tabela.setDefaultEditor(Object.class, null);
		tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tabela.setGridColor(Color.LIGHT_GRAY);
		tabela.gettabelaHeader().setReorderingAllowed(false);	
		tabelaviewscroll.setViewportView(tabela);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color (47, 173, 102));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		JLabel lblStudentManagement = new JLabel("Pesquisar");
		lblStudentManagement.setIcon(null);
		lblStudentManagement.setBounds(10, 38, 185, 44);
		panel.add(lblStudentManagement);
		lblStudentManagement.setBackground(new Color (47, 173, 102));
		lblStudentManagement.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentManagement.setForeground(Color.WHITE);
		lblStudentManagement.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentManagement.setOpaque(true);
		
		EstudanteDocente = new JComboBox<String>();
		EstudanteDocente.setModel(new DefaultComboBoxModel<String>(new String[] {"Estudantes", "Docentes"}));
		this.arrangeStudenttabela();
		EstudanteDocente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		EstudanteDocente.setBounds(10, 128, 185, 40);
		EstudanteDocente.addActionListener(this);
		panel.add(EstudanteDocente);
		
		String courcename[]=new DadosCurso().getCourceName();
		courcename[0]="Todos Cursos";
		cbNomeCurso = new JComboBox<String>(courcename);
		
		cbNomeCurso.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbNomeCurso.setBounds(215, 128, 255, 40);
		cbNomeCurso.addActionListener(this);
		
		panel.add(cbNomeCurso);
		
		cbsemouano = new JComboBox<String>();
		cbsemouano.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbsemouano.setBounds(490, 128, 214, 40);
		cbsemouano.addActionListener(this);
		panel.add(cbsemouano);
		
		campopesquisa = new DicaTextField("Pesquisar");
		campopesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		campopesquisa.setForeground(Color.DARK_GRAY);
		campopesquisa.setBounds(714, 129, 185, 40);
		panel.add(campopesquisa);
		campopesquisa.setColumns(10);
		
		searchbutton = new JButton();
		searchbutton.setForeground(new Color(0, 139, 139));
		searchbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		searchbutton.setText("Pesquisar");
		searchbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchbutton.setBackground(new Color(255, 255, 255));
		searchbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchbutton.setIcon(new ImageIcon("./assets/search.png"));
		searchbutton.setBounds(930, 129, 114, 40);
		searchbutton.addActionListener(this);
		searchbutton.setFocusable(false);
		panel.add(searchbutton);
		   

	}

	
	public void arrangeStudenttabela()
	{
		tabela.getColumnModel().getColumn(0).setMaxWidth(150);
		tabela.getColumnModel().getColumn(1).setMaxWidth(200);
		tabela.getColumnModel().getColumn(2).setMaxWidth(300);
		tabela.getColumnModel().getColumn(3).setMaxWidth(300);
		tabela.getColumnModel().getColumn(4).setMaxWidth(150);
		tabela.setAutoResizeMode(Jtabela.AUTO_RESIZE_LAST_COLUMN);
	}
	public void arrangeFacultytabela()
	{
		tabela.getColumnModel().getColumn(0).setMaxWidth(200);
		tabela.getColumnModel().getColumn(1).setMaxWidth(300);
		tabela.getColumnModel().getColumn(2).setMaxWidth(500);
		tabela.getColumnModel().getColumn(3).setMaxWidth(250);
		tabela.getColumnModel().getColumn(4).setMaxWidth(250);
		tabela.setAutoResizeMode(Jtabela.AUTO_RESIZE_LAST_COLUMN);
		DefaulttabelaCellRenderer cellrenderer=new DefaulttabelaCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		tabela.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
			
		if(e.getSource()==cbNomeCurso)
		{
			
			
			if(cbNomeCurso.getSelectedIndex()==0)
			{
				cbsemouano.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				
			}
			
			else
			{
			 String cource=(String) cbNomeCurso.getSelectedItem();
			 String semoryear[]=new DadosCurso().getSemorYear(cource);
			 semoryear[0]="All "+semoryear[1].substring(0,semoryear[1].indexOf(' ')); 
			 cbsemouano.setModel(new DefaultComboBoxModel<String>(semoryear));
			}
		 
		}
		if(e.getSource()==searchbutton)
		{
			
			createtabelamodel();
		}
	
	}
	public void createtabelamodel()
	{
		String searchtext=campopesquisa.getText().trim();
		if(EstudanteDocente.getSelectedIndex()==0)
		{
			String defaultquery="select s.courcecode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',c.courcename as 'Cource Name',concat(c.semoryear,'-',s.semoryear) as 'Sem/Year' from estudantes s left join cursos c on s.courcecode=c.courcecode ";
			String query=defaultquery;
			if(cbNomeCurso.getSelectedIndex()>0)
			{
				String courcecode=new DadosCurso().getCourcecode(cbNomeCurso.getSelectedItem()+"");
				query+=" where s.courcecode='"+courcecode+"'";
				if(cbsemouano.getSelectedIndex()>0)
				{
					query+=" and s.semoryear="+cbsemouano.getSelectedIndex();
				}
				
			}
			
			if(!searchtext.isEmpty())
			{
				String searchquery="s.firstname like '"+searchtext+"%' or s.lastname like '"+searchtext+"%' or s.rollnumber like '"+searchtext+"%' ";
				if(!query.contains("where"))
				{
					query+="where "+searchquery;
				}
				else
				{
					query+=" and ( "+searchquery+" ) ";
				}
			
			}
			tabela.setModel(DbUtils.resultSetTotabelaModel(new DadosEstudante().searchStudent(query)));
			this.arrangeStudenttabela();
		}
		else if(EstudanteDocente.getSelectedIndex()==1)
		{
			String defaultquery="select facultyid as 'ID',facultyname as 'Nome',emailid as 'Email',qualification as 'Qualificacao',experience as 'Experiencia' from docentes f ";
			String query=defaultquery;
			if(cbNomeCurso.getSelectedIndex()>0)
			{
				String courcecode=new DadosCurso().getCourcecode(cbNomeCurso.getSelectedItem()+"");
				query+=" where f.courcecode='"+courcecode+"'";
				if(cbsemouano.getSelectedIndex()>0)
				{
					query+=" and f.semoryear="+cbsemouano.getSelectedIndex();
				}
				
			}
			if(!searchtext.equals("Search")&&!searchtext.isEmpty())
			{
				String searchquery=" f.facultyname like '"+searchtext+"%' or f.facultyid like '"+searchtext+"%' ";
				if(!query.contains("where"))
				{
					query+="where "+searchquery;
				}
				else
				{
					query+=" and ( "+searchquery+" ) ";
				}
			
			}
			tabela.setModel(DbUtils.resultSetTotabelaModel(new DadosDocente().searchFaculty(query)));
			this.arrangeFacultytabela();
		}
	}
}
