package disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import comuns.DataBaseConnection;
import comuns.Notificacoes;
import comuns.NotificacaoDados;
import comuns.TimeUtil;



public class DadosDisciplina 
{

	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public String checkCoreorOptional(String subjectcode)
	{
		String type="nuclear";
		try
		{
			String query="select subjecttype from disciplina where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			
			type=rs.getString(1);
		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return type;
	}
	public boolean isExist(String courcecode,int semoryear,String subjectname)
	{
		try
		{
			String query="select subjectname from disciplina where courcecode='"+courcecode+"' and semoryear="+semoryear+" and subjectname='"+subjectname+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.first())
			{
				return true;
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public int getMaxTheoryMarksOfSubject(String subjectcode)
	{
		int marks=0;
		try
		{
			String query="select theorymarks from disciplina where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			marks=rs.getInt(1);
			st.close();
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return marks;
	}
	public int getMaxPracticalMarksOfSubject(String subjectcode)
	{
		int marks=0;
		try
		{
			String query="select practicalmarks from disciplina where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			marks=rs.getInt(1);
			st.close();
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return marks;
	}
	public int getTotalSubject()
	{
		int totalsubject=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from disciplina");
			while(rs.next())
			{
				totalsubject++;
			}
			rs.close();
			st.close();
			return totalsubject;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalsubject;
	}
	public String createSubjectcode(String Courcecode,int sem)
	{
		int code=101;
		String subjectcode=Courcecode+sem+code;
		try
		{
			String query="select courcecode,semoryear from disciplina where courcecode='"+Courcecode+"' and semoryear="+sem;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				code++;
			}
			subjectcode=Courcecode+sem+code;
			rs.close();
			st.close();
			return subjectcode;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return subjectcode;
	}
	public int addSubject(Disciplina su)
	{
		String query="insert into disciplina values(?,?,?,?,?,?,?)";
		int result=0;
		try
		{
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, su.getSubjectCode());
			pr.setString(2, su.getSubjectName());
			pr.setString(3, su.getCodigoCurso());
			pr.setInt(4, su.getSemouano());
			pr.setString(5, su.getSubjectType());
			pr.setInt(6, su.getMaxTheoryMarks());
			pr.setInt(7,su.getMaxPracticalMarks());
			result=pr.executeUpdate();
			
			//Adding notification of new subject
			{
				Notificacoes n=new Notificacoes();
				n.setUserProfile("Student");
				n.setCodigoCurso(su.getCodigoCurso());
				n.setUserId("Admin");
				n.setSemouano(su.getSemouano());
				n.setTitle("Nova disciplina");
				n.setMessage(su.getSubjectName()+" ("+su.getSubjectCode()+") E' sua nova disciplina.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificacaoDados().addNotification(n);
				n.setMessage(su.getSubjectName()+" ("+su.getSubjectCode()+") E'  nova disciplina na sua turma");
				n.setUserProfile("Faculty");
				new NotificacaoDados().addNotification(n);
			}
			
			pr.close();
			
			return result;
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	public String getSubjectName(String subjectcode)
	{
		String subjectname=null;
		try
		{
			String query="select subjectname from disciplina where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			subjectname=rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return subjectname;
	}
	public  ResultSet getSubjectinfo(String courcecode,int sem)
	{
	
		ResultSet st=null;
		try
		{
			String query="select subjectcode as 'Cod. Disc.',subjectname as 'Nome',semoryear as 'Sem/Ano',subjecttype as 'Tipo',theorymarks as 'Teste1',practicalmarks as 'Teste2' from disciplina where courcecode='"+courcecode+"' and semoryear="+sem;
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
	public String[] getOptionalSubject(String courcecode,int sem)
	{
		int totaloptionalsubject=this.gettotalOptionalSubject(courcecode, sem);
		if(totaloptionalsubject>0)
		{
		String[] subject=new String[totaloptionalsubject+1];
		subject[0]="---Selecione a disciplina opcional---";
		String query="select subjectname from disciplina where courcecode='"+courcecode+"' and semoryear="+sem+" and subjecttype='opcional'";
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int i=1;
				while(rs.next())
				{
					subject[i++]=rs.getString(1);
				}
				rs.close();
				st.close();
				return subject;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			
		}
		return null;
	}
	public int gettotalOptionalSubject(String courcecode,int sem)
	{
		int totalopsubject=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select subjectname from disciplina where courcecode='"+courcecode+"' and semoryear="+sem+" and subjecttype='opcional'");
			while(rs.next())
			{
				totalopsubject++;
			}
			rs.close();
			st.close();
			return totalopsubject;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalopsubject;
	}
	public String[] getSubjectinCource(String courcecode,int sem)
	{
		int totalsubjectincource=this.getTotalSubjectinCource(courcecode, sem);
		if(totalsubjectincource>0)
		{
		String[] subject=new String[totalsubjectincource+1];
		subject[0]="---Selecione a disciplina---";
		String query="select subjectname from disciplina where courcecode='"+courcecode+"' and semoryear="+sem;
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int i=1;
				while(rs.next())
				{
					subject[i++]=rs.getString(1);
				}
				rs.close();
				st.close();
				return subject;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			
		}
		return null;
	}
	public int getTotalSubjectinCource(String courcecode,int sem)
	{
		int totalsubjectincource=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select subjectname from disciplina where courcecode='"+courcecode+"' and semoryear="+sem);
			while(rs.next())
			{
				totalsubjectincource++;
			}
			
			rs.close();
			st.close();
			return totalsubjectincource;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalsubjectincource;
	}
	public String getSubjectCode(String courcecode,int sem,String subjectname)
	{
		String subcode=null;
		String query="select subjectcode from disciplina where courcecode='"+courcecode+"' and semoryear="+sem+" and subjectname='"+subjectname+"'";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			subcode=rs.getString(1);
			
		}
		catch(Exception exp)
		{
			return null;
		}
		
		return subcode;
		
	}
}
