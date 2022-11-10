package curso;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import comuns.DataBaseConnection;
import comuns.Notificacoes;
import comuns.NotificacaoDados;
import comuns.TimeUtil;


/*
 
 */

public class DadosCurso
{
	
	static Connection con=DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public int addCource(String courcecode,String courcename,String semoryear,int totalyearorsem)
	{
		int result=0;
		try 
		{
		String query="insert into cursos values(?,?,?,?,?)";
		PreparedStatement pr=con.prepareStatement(query);
		pr.setInt(1,0);
		pr.setString(2, courcecode.toUpperCase());
		pr.setString(3, courcename);
		pr.setString(4, semoryear);
		pr.setInt(5, totalyearorsem);
		result=pr.executeUpdate();
			
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public  ResultSet getCourceinfo()
	{
	
		ResultSet st=null;
		try
		{
			String query="select c.sr_no as 'Index nr.',c.courcecode as 'Codigo curso' ,c.courcename as 'Nome do curso',(select count(*) from disciplina where disciplina.courcecode=c.courcecode) as 'Disciplinas' ,(select count(*) from estudantes where estudantes.courcecode=c.courcecode) as 'Estudantes',concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Ano' from cursos c;";
			PreparedStatement pr=con.prepareStatement(query);
			
			st=pr.executeQuery();
			return st;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return st;
	}
	public int getTotalCource()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select * from cursos");
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
		
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	
	
	
	public String[] getCourceName()
	{
				String courcename[];
				int i=0;
				courcename=new String[getTotalCource()+1];
				courcename[i++]="---Selecionar Curso---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select courcename from cursos");
					
					
					while(st.next())
					{
						courcename[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return courcename;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return courcename;
		
	}
	public int getRollTotalCource()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select courcename from cursos where courcecode Not IN(select distinct courcecode from rollgenerator)");             
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
			st.close();
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	public String[] getRollCourceName()
	{
				String courcename[];
				int i=0;
				courcename=new String[getRollTotalCource()+1];
				courcename[i++]="---select---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select courcename from cursos where courcecode NOT IN(select distinct courcecode from rollgenerator)");
					
					
					while(st.next())
					{
						courcename[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return courcename;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return courcename;
		
	}
	public String[] getSemorYear(String Courcename)
	{
		String query="select semoryear, totalsemoryear from cursos where courcename='"+Courcename+"'";
		String totalsem[]=new String[1];
		totalsem[0]="---Selecione Sem/Ano---";
		if(!Courcename.contains("--selecione--"))
		{
			try
			{
				Statement pr=con.createStatement();
				ResultSet st=pr.executeQuery(query);
				st.next();
				String semoryear=st.getString(1);
				int totalsemoryear=st.getInt(2);	
				
				totalsem=new String[totalsemoryear+1];
				if(semoryear.contains("sem"))
				{
					semoryear="Semestre";
				}
				else
				{
					semoryear="Ano";
				}
				totalsem[0]="---Selecione "+semoryear+"---";
				for(int i=1; i<=totalsemoryear; i++)
				{
					totalsem[i]=semoryear+" "+i;
				}
				pr.close();
				st.close();
				return totalsem;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		return totalsem;
		
	}
	public String[] getCourcecode()
	{
		String courcecode[]=new String[this.getTotalCource()];
		String query="select courcecode from cursos";
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			int i=0;
			while(st.next())
			{
				courcecode[i++]=st.getString(1);
			}
			pr.close();
			st.close();
			
			return courcecode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcecode;
		
		
	}
	public String getCourcecode(String courcename)
	{
		String query="select courcecode from cursos where courcename='"+courcename+"'";
		String courcecode=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				courcecode=st.getString(1);
			
				pr.close();
				st.close();
			return courcecode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcecode;
	}
	public String getsemoryear(String courcecode)
	{
		String query="select semoryear from cursos where courcecode='"+courcecode+"'";
		String semoryear=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				semoryear=st.getString(1);
			
				pr.close();
				st.close();
			return semoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return semoryear;
	}
	public String getcourcename(String courcecode)
	{
		String query="select courcename from cursos where courcecode='"+courcecode+"'";
		String courcename=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				courcename=st.getString(1);
			
				pr.close();
				st.close();
			return courcename;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcename;
	}
	public int getTotalsemoryear(String courcename)
	{
		String query="select totalsemoryear from cursos where courcename='"+courcename+"'";
		int totalsemoryear=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			while(st.next())
			{
			totalsemoryear=st.getInt(1);
			}
			pr.close();
			st.close();
			
			return totalsemoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalsemoryear;
	}
	public boolean isCourceCodeExist(String courcecode)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from cursos where courcecode='"+courcecode+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isCourceNameExist(String courcename)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from cursos where courcename='"+courcename+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDeclared(String courcecode,int semoryear)
	{
		boolean isdeclared=false;
		try
		{
			String query="select isdeclared from result where courcecode='"+courcecode+"' and semoryear="+semoryear;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
			isdeclared=rs.getBoolean(1);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return isdeclared;
	}
	public ArrayList<Curso> getCourcesForDeclareResult(String courcename)
	{
		ArrayList<Curso> list=new ArrayList<Curso>();
		try
		{
			String query="select courcename,courcecode,totalsemoryear from cursos where courcename='"+courcename+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				int totalsem=rs.getInt(3);
				for(int i=0; i<totalsem; i++)
				{
					Curso cource=new Curso();
					cource.setNomeCurso(rs.getString(1));
					cource.setCodigoCurso(rs.getString(2));
					cource.setSemouano((i+1));
					cource.setIsDeclared(isDeclared(rs.getString(2),(i+1)));
					list.add(cource);
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public int updateResult(Curso c)
	{
		int result=0;
		try
		{
			String query="update result set isdeclared="+c.getIsDeclared()+" where courcecode='"+c.getCodigoCurso()+"' and semoryear="+c.getSemouano();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp) 
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void declareResult(Curso c)
	{
		try
		{		if(c.getIsDeclared())
				{
					Notificacoes n=new Notificacoes();
					n.setUserProfile("Estudante");
					n.setCodigoCurso(c.getCodigoCurso());
					n.setSemouano(c.getSemouano());
					n.setTitle("Result");
					n.setUserId("Admin");
					n.setMessage("Resultado declarado.");
					n.setTime(TimeUtil.getCurrentTime());
					new NotificacaoDados().addNotification(n);
					//ver coigo abaixo
					n.setMessage( c.getCodigoCurso()+" "+getsemoryear(c.getCodigoCurso())+"-"+c.getSemouano()+" Resultado declarado");
					n.setUserProfile("Docente");
					new NotificacaoDados().addNotification(n);
				}
				if(updateResult(c)==0)
				{
				String query="insert into result values(?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setString(1,c.getCodigoCurso());
				pr.setInt(2, c.getSemouano());
				pr.setBoolean(3, c.getIsDeclared());
				pr.executeUpdate();
				}
				
				
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
}

