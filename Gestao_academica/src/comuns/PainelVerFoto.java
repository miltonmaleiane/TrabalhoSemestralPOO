package comuns;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import curso.DadosCurso;
import docente.Docente;
import docente.DadosDocente;
import docente.PainelDocente;
import docente.PainelVerDocente;
import estudante.Estudante;
import estudante.DadosEstudante;
import estudante.PainelEstudante;
import estudante.PainelVerEstudante;


/*

 */
@SuppressWarnings("serial")
public class PainelVerFoto extends JPanel {
	int xpos[];
	JPanel panel[][];
	JLabel lbPerfilFoto[][];
	JLabel lbNome[][];
	JLabel lbCurso[][];
	int totalDocente=-1;
	int totalEstudentes=-1;
	int maxfotosnarow=3;
	int incrementarx=0;
	int incrementary=0;
	PainelDocente fp;
	PainelEstudante sp;
	/**
	 * Create the panel.
	 * 
	 */
	 
	@Override
	public Dimension getPreferredSize()
	{
		int n=0;
		if(totalDocente!=-1)
		{
		  n=totalDocente;
		}
		if(totalEstudentes!=-1)
		{
			n=totalEstudentes;
		}
		 int row=n%maxfotosnarow==0?n/maxfotosnarow:(n/maxfotosnarow)+1;
		 if(row==1)
		 {
			 return new Dimension(xpos[maxfotosnarow-1]+xpos[1]-xpos[0],incrementary+20);
		 }
		 
	    return new Dimension( 1116,row*(incrementary));
	}
	public PainelVerFoto(PainelDocente facultyPanel,int maxphoto) {
		
		this.maxfotosnarow=maxphoto;
		this.setFocusable(true);
		this.fp=facultyPanel;
		xpos=new int[maxfotosnarow];
		incrementarx=(4*270)/maxfotosnarow;
		int start=20;
		
		for(int i=0; i<maxfotosnarow; i++)
		{
			xpos[i]=start;
			start+=(incrementarx);
		}
		 incrementary=incrementarx+50;
		totalDocente=fp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalDocente;
		  int row=n%maxfotosnarow==0?n/maxfotosnarow:(n/maxfotosnarow)+1;
		  int lastcolumn=n%maxfotosnarow==0?maxfotosnarow:n%maxfotosnarow;
	
		  panel=new JPanel[row][maxfotosnarow];
		  lbPerfilFoto=new JLabel[row][maxfotosnarow];
		  lbNome=new JLabel[row][maxfotosnarow];
		  lbCurso=new JLabel[row][maxfotosnarow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxfotosnarow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
					int fid=Integer.parseInt(fp.table.getValueAt(index-1, 0)+"");
					Docente f=new DadosDocente().getFacultyInfobyId(fid);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementarx-20, incrementary-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(f.getFacultyName());
				  add(panel[i][j]);
				  panel[i][j].setLayout(null);
				  panel[i][j].setName(f.getFacultyId()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
				  {
					  @Override
			  			public void mousePressed(MouseEvent e)
			  			{
						  	if(e.getButton()==MouseEvent.BUTTON1)
						  	{
			  					
			  					Docente f=new DadosDocente().getFacultyInfobyId(Integer.parseInt(e.getComponent().getName()));
								if(fp.am!=null)
								{
									fp.am.viewfacultypanel = new PainelVerDocente(f, fp.am, fp);
									fp.am.viewfacultypanel.setVisible(true);
									fp.am.facultypanel.setVisible(false);
									fp.am.viewfacultypanel.setLocation(fp.am.panelx, fp.am.panely);
									fp.am.viewfacultypanel.setVisible(true);
									fp.am.viewfacultypanel.setFocusable(true);
									fp.am.contentPane.add(fp.am.viewfacultypanel);
								}
								else if(fp.fm!=null)
								{
									fp.fm.viewfacultypanel = new PainelVerDocente(f, fp.fm, fp);
									fp.fm.viewfacultypanel.setVisible(true);
									fp.fm.facultypanel.setVisible(false);
									fp.fm.viewfacultypanel.setLocation(fp.fm.panelx, fp.fm.panely);
									fp.fm.viewfacultypanel.setVisible(true);
									fp.fm.viewfacultypanel.setFocusable(true);
									fp.fm.contentPane.add(fp.fm.viewfacultypanel);
								}
								else if(fp.sm!=null)
								{
									fp.sm.viewfacultypanel = new PainelVerDocente(f, fp.sm, fp);
									fp.sm.viewfacultypanel.setVisible(true);
									fp.sm.facultypanel.setVisible(false);
									fp.sm.viewfacultypanel.setLocation(fp.sm.panelx, fp.sm.panely);
									fp.sm.viewfacultypanel.setVisible(true);
									fp.sm.viewfacultypanel.setFocusable(true);
									fp.sm.contentPane.add(fp.sm.viewfacultypanel);
								}
			  				}
			  			}
				  }
				  );
				
				  
				  lbPerfilFoto[i][j]=new JLabel();
				  lbPerfilFoto[i][j].setBounds(0, 0, panel[i][j].getWidth()-10,panel[i][j].getHeight()-60);
				  lbPerfilFoto[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  lbPerfilFoto[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbPerfilFoto[i][j].setText("image");
				  lbPerfilFoto[i][j].setIcon(new ImageIcon(f.getProfilePic(lbPerfilFoto[i][j].getWidth()+((maxfotosnarow*10)/4+1),lbPerfilFoto[i][j].getHeight())));
				  
				
				  panel[i][j].add(lbPerfilFoto[i][j]);

				  lbNome[i][j]=new JLabel();
				  lbNome[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbNome[i][j].setText(f.getFacultyName());
				  lbNome[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  lbNome[i][j].setBounds(0, lbPerfilFoto[i][j].getHeight()+5,panel[i][j].getWidth(), 22);
				  panel[i][j].add(lbNome[i][j]);
				  lbCurso[i][j]=new JLabel();
				  lbCurso[i][j].setVerticalAlignment(SwingConstants.TOP);
				  if(f.getPosition().equals("Not Assigned"))
				  {
				  lbCurso[i][j].setText("");
				  }
				  else
				  {
					  lbCurso[i][j].setText(f.getPosition());
				  }
				  lbCurso[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbCurso[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  lbCurso[i][j].setBounds(0, lbPerfilFoto[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(lbCurso[i][j]);
				  index++;
				  if(index>totalDocente)
				  {
					  break;
				  }
			  }
			  column+=incrementary;
		  }

	}
	public PainelVerFoto(PainelEstudante sp,int maxphoto)
	{
		this.maxfotosnarow=maxphoto;
		this.sp=sp;
		xpos=new int[maxfotosnarow];
		 incrementarx=(4*270)/maxfotosnarow;
		int start=20;
		
		for(int i=0; i<maxfotosnarow; i++)
		{
			xpos[i]=start;
			start+=(incrementarx);
		}
		incrementary=incrementarx+50;
		totalEstudentes=sp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalEstudentes;
		  int row=n%maxfotosnarow==0?n/maxfotosnarow:(n/maxfotosnarow)+1;
		  int lastcolumn=n%maxfotosnarow==0?maxfotosnarow:n%maxfotosnarow;
	
		  panel=new JPanel[row][maxfotosnarow];
		  lbPerfilFoto=new JLabel[row][maxfotosnarow];
		  lbNome=new JLabel[row][maxfotosnarow];
		  lbCurso=new JLabel[row][maxfotosnarow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxfotosnarow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
				  
					String courcecode=sp.table.getValueAt(index-1,0)+"";
					String  strsem=sp.table.getValueAt(index-1, 4)+"";
					int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
					String strroll=sp.table.getValueAt(index-1, 1)+"";
					long rollnumber=Long.parseLong(strroll);
					Estudante s=new DadosEstudante().getStudentDetails(courcecode,sem,rollnumber);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementarx-20, incrementary-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(s.getNomecompleto());
				  add(panel[i][j]);
				  panel[i][j].setName(s.getSrNo()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
						  {
					  			@Override
					  			public void mousePressed(MouseEvent e)
					  			{
					  				if (e.getButton() == MouseEvent.BUTTON1)
					  				{
					  					Estudante s=new DadosEstudante().getStudentDetails(Integer.parseInt(e.getComponent().getName()));
					  					
					  					if(sp.am!=null)
					  					{
						  					sp.am.viewstudentpanel=new PainelVerEstudante(s,sp.am,sp);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.painelEstudante.setVisible(false);
						  					sp.am.viewstudentpanel.setLocation(sp.am.panelx,sp.am.panely);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.viewstudentpanel.setFocusable(true);
						  					sp.am.contentPane.add(sp.am.viewstudentpanel);
					  					}
					  					else  if(sp.fm!=null)
					  					{
					  						sp.fm.viewstudentpanel=new PainelVerEstudante(s,sp.fm,sp);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.studentpanel.setVisible(false);
						  					sp.fm.viewstudentpanel.setLocation(sp.fm.panelx,sp.fm.panely);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.viewstudentpanel.setFocusable(true);
						  					sp.fm.contentPane.add(sp.fm.viewstudentpanel);
					  					}
					  					else if(sp.sm!=null)
					  					{
					  						sp.sm.viewstudentpanel=new PainelVerEstudante(s,sp.sm,sp);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.studentpanel.setVisible(false);
						  					sp.sm.viewstudentpanel.setLocation(sp.sm.panelx,sp.sm.panely);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.viewstudentpanel.setFocusable(true);
						  					sp.sm.contentPane.add(sp.sm.viewstudentpanel);
					  					}
					  					
					  				}
					  			}
						  }
						  );
				  panel[i][j].setLayout(null);
				
				  
				  lbPerfilFoto[i][j]=new JLabel();
				  lbPerfilFoto[i][j].setBounds(0, 0, panel[i][j].getWidth(),panel[i][j].getHeight()-60);
				  lbPerfilFoto[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  lbPerfilFoto[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbPerfilFoto[i][j].setText("image");
				  lbPerfilFoto[i][j].setIcon(new ImageIcon(s.getProfilePic(lbPerfilFoto[i][j].getWidth()+((maxfotosnarow*10)/4+1),lbPerfilFoto[i][j].getHeight())));
				  
				
				  panel[i][j].add(lbPerfilFoto[i][j]);

				  lbNome[i][j]=new JLabel();
				  lbNome[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbNome[i][j].setText(s.getNomecompleto());
				
				  lbNome[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  lbNome[i][j].setBounds(0, lbPerfilFoto[i][j].getHeight()+3,panel[i][j].getWidth(), 22);
				  panel[i][j].add(lbNome[i][j]);
				  lbCurso[i][j]=new JLabel();
				  lbCurso[i][j].setVerticalAlignment(SwingConstants.TOP);
				  lbCurso[i][j].setText(new DadosCurso().getsemoryear(s.getCodigoCurso())+"-"+s.getSemouano()+" "+" ("+s.getCodigoCurso()+")");
				  lbCurso[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  lbCurso[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  lbCurso[i][j].setBounds(0, lbPerfilFoto[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(lbCurso[i][j]);
				  index++;
				  if(index>totalEstudentes)
				  {
					  break;
				  }
			  }
			  column+=incrementary;
		  }
		  

	}
	public int changeNameFont()
	  {
		return maxfotosnarow<4?22:maxfotosnarow<8?17:maxfotosnarow<12?13:10;
	  }
	public int changeDegreeFont()
	  {
		return maxfotosnarow<4?18:maxfotosnarow<8?14:maxfotosnarow<12?13:10;
	  }
	
	


}
