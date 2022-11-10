
package comuns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import docente.Docente;
import estudante.Estudante;


/*

 */


public class DadosUsuario {
	Connection con=DataBaseConnection.getConnection();
			
			public int addStudentLoginTime(Estudante s)
			{
				int result=0;
				try
				{
				String query="insert into users values(?,?,?,?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setInt(1, 0);
				pr.setString(2, s.getCodigoCurso());
				pr.setInt(3,s.getSemouano());
				pr.setString(4,s.getIdusuario());
				pr.setString(5,TimeUtil.getCurrentTime());
				pr.setString(6, "Student");
				result=pr.executeUpdate();
				
				}
				catch(Exception exp) {
					exp.printStackTrace();
				}
			return result;	
			}
			public int addFacultyLoginTime(Docente s)
			{
				int result=0;
				try
				{
				String query="insert into users values(?,?,?,?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setInt(1, 0);
				pr.setString(2, s.getCodigoCurso());
				pr.setInt(3,s.getSemouano());
				pr.setString(4,s.getFacultyId()+"");
				pr.setString(5,TimeUtil.getCurrentTime());
				pr.setString(6, "Faculty");
				result=pr.executeUpdate();
				
				}
				catch(Exception exp) {
					exp.printStackTrace();
				}
			return result;	
			}
			public ArrayList<Usuario> getUserInfo(String condition)
			{
				ArrayList<Usuario> list=new ArrayList<Usuario>();
				try
				{
					String query="select courcecode as 'curso',semoryear as 'Sem/ano',userid as 'Userid',datalogin as 'datalogin',perfilusuario as 'perfil usuario' from users "+condition+" order by sr_no desc";
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						Usuario user=new Usuario();
						user.setCodigoCurso(rs.getString(1));
						user.setSemouano(rs.getInt(2));
						user.setUserId(rs.getString(3));
						user.setLoginTime(rs.getString(4));
						user.setUserProfile(rs.getString(5));
						list.add(user);
						
					}
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
				return list;
			}

}

