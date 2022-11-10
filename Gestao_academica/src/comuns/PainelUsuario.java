package comuns;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Jtabela;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tabela.DefaulttabelaCellRenderer;
import javax.swing.tabela.DefaulttabelaModel;

import admin.AdminPrincipal;
import docente.Docente;
import docente.DadosDocente;
import docente.PainelVerDocente;
import estudante.Estudante;
import estudante.DadosEstudante;
import estudante.PainelVerEstudante;


/*

 */

@SuppressWarnings("serial")
public class PainelUsuario extends JPanel {

	private JLabel lbCab;
	private Jtabela tabela;
	String condition="";

	/**
	 * Create the panel.
	 */

	public PainelUsuario(AdminPrincipal am)
	{
		this();
		createtabelamodel();
		tabela.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1&&e.getButton()==MouseEvent.BUTTON1)
				{
					
					Jtabela t=(Jtabela) e.getSource();
					int row=t.getSelectedRow();
					String userprofile=tabela.getValueAt(row, 0)+"";
					String userid=tabela.getValueAt(row, 2)+"";
					if(userprofile.equals("Estudante"))
					{
						Estudante s=new DadosEstudante().getStudentDetailsByUserId(userid);
						am.viewstudentpanel=new PainelVerEstudante(s,am,am.userspanel);
						am.viewstudentpanel.setVisible(true);
						am.userspanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
					else if(userprofile.equals("Docente"))
					{
						int fid=Integer.parseInt(userid);
						Docente f=new DadosDocente().getFacultyInfobyId(fid);
						am.viewfacultypanel=new PainelVerDocente(f,am,am.userspanel);
						am.viewfacultypanel.setVisible(true);
						am.userspanel.setVisible(false);
						am.viewfacultypanel.setLocation(am.panelx,am.panely);
						am.viewfacultypanel.setVisible(true);
						am.viewfacultypanel.setFocusable(true);
						am.contentPane.add(am.viewfacultypanel);
					}
						
					
				}
				
			}
		});
	}
	public PainelUsuario() {
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		setName("Painel de Usuarios");
		
		lbCab = new JLabel(" Usúarios");
		lbCab.setBackground(new Color (47, 173, 102));
		lbCab.setForeground(new Color(255, 255, 255));
		lbCab.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lbCab.setHorizontalAlignment(JLabel.LEFT);
		lbCab.setBounds(10, 0, 1096, 183);
		lbCab.setOpaque(true);
		add(lbCab);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 194, 1096, 500);
		for(Component c:scrollPane.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(scrollPane);
		
		tabela = new Jtabela();
		tabela.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tabela.gettabelaHeader().setBackground(new Color(47, 173, 102));
		tabela.gettabelaHeader().setForeground(Color.white);
		tabela.setSelectionBackground(new Color(240, 255, 255));
		tabela.gettabelaHeader().setFont(new Font("Arial",Font.BOLD,20));
		tabela.setFont(new Font("Segoe UI",Font.PLAIN,20));
		tabela.gettabelaHeader().setPreferredSize(new Dimension(50,40));
		tabela.setFocusable(false);
		tabela.setDragEnabled(false);
		tabela.setRowHeight(40);
		tabela.setDefaultEditor(Object.class, null);
		tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tabela.setGridColor(Color.LIGHT_GRAY);
		tabela.gettabelaHeader().setReorderingAllowed(false);	
		scrollPane.setViewportView(tabela);

	}
	
	public void createtabelamodel()
	{
		ArrayList<Usuario> list=new DadosUsuario().getUserInfo(condition);
		String Column[]= {"Perfil do usuario","Turma"," ID Usuario","User name","Data login","Tempo Login"};
		DefaulttabelaModel model=new DefaulttabelaModel(Column,0);
		for(int i=0; i<list.size(); i++)
		{
			Usuario user=list.get(i);
			model.addRow(new Object[0]);
			model.setValueAt(user.getUserProfile(), i, 0);
			model.setValueAt(user.getCodigoCurso()+"-"+user.getSemouano(), i, 1);
			model.setValueAt(user.getUserid(), i, 2);
			model.setValueAt(user.getName(), i, 3);
			model.setValueAt(user.getLoginDate(), i, 4);
			model.setValueAt(user.getLoginTime(), i, 5);
			
			
		}
		tabela.setModel(model);
		
		tabela.getColumnModel().getColumn(0).setMaxWidth(200);
		tabela.getColumnModel().getColumn(1).setMaxWidth(200);
		tabela.getColumnModel().getColumn(2).setMaxWidth(200);
		tabela.getColumnModel().getColumn(3).setMaxWidth(200);
		tabela.getColumnModel().getColumn(4).setMaxWidth(200);
		tabela.getColumnModel().getColumn(5).setMaxWidth(200);
		tabela.setAutoResizeMode(Jtabela.AUTO_RESIZE_LAST_COLUMN);
		tabela.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
		tabela.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
		tabela.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
		tabela.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
		tabela.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer());
		tabela.getColumnModel().getColumn(5).setCellRenderer(new CellRenderer());
		
		
	}
	private class CellRenderer extends DefaulttabelaCellRenderer 
	{


		@Override
		public Component gettabelaCellRendererComponent(Jtabela tabela, Object value, boolean isSelected, boolean hasFocus, int row,
		        int column) 
		{
		    super.gettabelaCellRendererComponent(tabela, value, isSelected, hasFocus, row, column);
		    this.setHorizontalAlignment(JLabel.CENTER);
		    return this;
		}
	}
}
