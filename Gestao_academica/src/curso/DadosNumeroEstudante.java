package curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comuns.DataBaseConnection;

/*
 
 */

public class DadosNumeroEstudante
{	
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	
	public void adddata(String codCurso,int sem,long nrEst)
	{
		String query="insert into Rollgenerator values(?,?,?)";
		try
		{
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, codCurso);
			pr.setInt(2, sem);
			pr.setLong(3, nrEst);
			pr.executeUpdate();
			pr.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
	public long getEStudentNr(String codCurso,int sem)
	{
		long nrEst=-1;
		String query="select nrEst from rollgenerator where codCurso='"+codCurso+"' and semoryear=+"+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			try
			{
			nrEst=rs.getLong(1);
			}
			catch(Exception exp)
			{
				nrEst=-1;
			}
			return nrEst;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return nrEst;
	}
	public long getnrEst(String codCurso,int sem)
	{
		
		long nrEst=this.getEStudentNr(codCurso, sem)+1;
		if(nrEst==0)
		{
			return 0;
		}
		
		String query="select nrEst from students where codCurso='"+codCurso+"' and semoryear=+"+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				if(isExist(codCurso,sem,nrEst)==0)
				{
					break;
				}
				nrEst++;
				
			}
		
			return nrEst;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return nrEst;
	}
	public int isExist(String codCurso,int sem,long nrEst)
	{
		int result=0;
		String query="select nrEst from students where codCurso='"+codCurso+"' and semoryear=+"+sem+" and nrEst="+nrEst;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				result++;
			}
			
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	
}
