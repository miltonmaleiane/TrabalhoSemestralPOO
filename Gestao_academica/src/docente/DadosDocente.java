package docente;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import comuns.DataBaseConnection;
import comuns.Notificacoes;
import comuns.NotificacaoDados;
import comuns.TimeUtil;


public class DadosDocente 
{
	//IOkm
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getTotalFaculaty()
	{
		int totalf=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from docentes");
			rs.next();
			totalf=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalf;
	}
	public int getTotalFaculaty(String courcecode,int sem)
	{
		int totalf=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from docentes where courcecode='"+courcecode+"' and semoryear="+sem);
			rs.next();
			totalf=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalf;
	}
	public int getFaculaty(String courcecode,int sem)
	{
		int f=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from docentes where courcecode='"+courcecode+"' and semoryear="+sem);
			rs.next();
			f=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	public int createFacultyID()
	{
		int id=101;
		try
		{
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from docentes");
			rs.next();
			id=id+rs.getInt(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return id;
		
	}
	public ResultSet getFacultyInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select facultyid as 'ID',facultyname as 'Nome',emailid as 'Email',qualification as 'Qualificacao',experience as 'Experiencia' from docentes  "+condition+" order by facultyid";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public ResultSet searchFaculty(String query)
	{
		ResultSet rs=null;
		query+=" order by facultyid";
		try
		{
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean isActive(String facultyid)
	{
		try
		{
			String query="select activestatus from docentes where facultyid="+facultyid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getBoolean(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public String getFacultyName(String facultyid)
	{
		try
		{
			String query="select facultyname from docentes where facultyid="+facultyid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return "";
	}
	public int addFacultyData(Docente f)
	{
		int result=0;
		String query="insert into docentes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, f.getFacultyId());
			pr.setString(2,f.getFacultyName());
			pr.setString(3,f.getState());
			pr.setString(4,f.getCity());
			pr.setString(5,f.getEmailId());
			pr.setString(6,f.getContactNumber());
			pr.setString(7,f.getQualification());
			pr.setString(8,f.getExperience());
			pr.setString(9,f.getBirthDate());
			pr.setString(10,f.getGender());
			pr.setBytes(11,f.getProfilePicInBytes());
			pr.setString(12, "Not Assigned");
			pr.setInt(13, 0);
			pr.setString(14, "Not Assigned");
			pr.setString(15, "Not Assigned");
			pr.setInt(16, 0);
			pr.setString(17, null);
			pr.setString(18, f.getBirthDate());
			pr.setBoolean(19, f.getActiveStatus());
			pr.setString(20, f.generateJoinedDate());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public int updateFacultyData(Docente fold,Docente f)
	{
		int result=0;
		String query="update docentes set facultyid=? , facultyname=? ,state=? , city=? , emailid=? , contactnumber=? , qualification=? , experience=? , birthdate=? , gender=? , profilepic=?,lastlogin=?,activestatus=? where facultyid="+fold.getFacultyId();
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, f.getFacultyId());
			pr.setString(2,f.getFacultyName());
			pr.setString(3,f.getState());
			pr.setString(4,f.getCity());
			pr.setString(5,f.getEmailId());
			pr.setString(6,f.getContactNumber());
			pr.setString(7,f.getQualification());
			pr.setString(8,f.getExperience());
			pr.setString(9,f.getBirthDate());
			pr.setString(10,f.getGender());
			pr.setBytes(11,f.getProfilePicInBytes());
			pr.setString(12, f.getLastLogin());
			pr.setBoolean(13, f.getActiveStatus());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public Docente getFacultyInfo(int row)
	{
		Docente f=new Docente();
		String query="select * from docentes where sr_no="+row;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setFacultyId(rs.getInt(1));
			f.setFacultyName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCodigoCurso(rs.getString(12));
			f.setSemouano(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			st.close();
		
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	public Docente getFacultyInfobyId(int facultyid)
	{
		Docente f=new Docente();
		String query="select * from docentes where facultyid="+facultyid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setFacultyId(rs.getInt(1));
			f.setFacultyName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCodigoCurso(rs.getString(12));
			f.setSemouano(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			
			st.close();
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	public ArrayList<Docente> getTotalFaculty(String condition)
	{
		ArrayList<Docente> list=new ArrayList<Docente>();
		
		String query="select * from docentes"+condition+" order by facultyid asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Docente f=new Docente();
				f.setFacultyId(rs.getInt(1));
				f.setFacultyName(rs.getString(2));
				f.setState(rs.getString(3));
				f.setCity(rs.getString(4));
				f.setEmailId(rs.getString(5));
				f.setContactNumber(rs.getString(6));
				f.setQualification(rs.getString(7));
				f.setExperience(rs.getString(8));
				f.setBirthDate(rs.getString(9));
				f.setGender(rs.getString(10));
				f.setProfilePic(rs.getBytes(11));
				f.setCodigoCurso(rs.getString(12));
				f.setSemouano(rs.getInt(13));
				f.setSubject(rs.getString(14));
				f.setPosition(rs.getString(15));
				f.setLastLogin(rs.getString(17));
				f.setPassword(rs.getString(18));
				f.setActiveStatus(rs.getBoolean(19));
				f.setJoinedDate(rs.getString(20));
				list.add(f);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	
	public Docente getFacultyInfobyUserId(String facultyid)
	{
		Docente f=new Docente();
		facultyid=facultyid.replaceAll("\\s", "");
		String query="select * from docentes where facultyid="+facultyid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setFacultyId(rs.getInt(1));
			f.setFacultyName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCodigoCurso(rs.getString(12));
			f.setSemouano(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			st.close();
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	
	public int assignSubject(Docente fold,Docente f)
	{
		int result=0;
		try
		{
			
			if(!fold.getSubject().equals(f.getSubject())||!fold.getCodigoCurso().equals(f.getCodigoCurso())||fold.getSemouano()!=f.getSemouano()||!fold.getPosition().equals(f.getPosition()))
			{
				this.deleteNotificationHistory(f);
				Notificacoes n=new Notificacoes();
				n.setUserProfile("Student");
				n.setCodigoCurso(f.getCodigoCurso());
				n.setSemouano(f.getSemouano());
				n.setTitle("Docent da cadeira");
				n.setUserId(f.getFacultyId()+"");
				n.setMessage(f.getFacultyName()+" E' "+f.getSubject()+" O "+f.getPosition()+"da cadeira");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificacaoDados().addNotification(n);
				n.setMessage(f.getFacultyName()+" E' "+f.getPosition()+" da "+f.getSubject()+" cadeira.");
				n.setUserProfile("Faculty");
				new NotificacaoDados().addNotification(n);
				
			}
			
			String query="update docentes set courcecode='"+f.getCodigoCurso()+"',semoryear="+f.getSemouano()+",subject='"+f.getSubject()+"',position='"+f.getPosition()+"' where facultyid="+f.getFacultyId();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
		exp.printStackTrace();	
		}
		return result;
	}
	public int deleteNotificationHistory(Docente f)
	{
		int result=0;
		String query="delete from notificacao where userid='"+f.getFacultyId()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public ResultSet getFacultySubjectInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select facultyid as ' ID',facultyname as ' Nome',courcecode as 'Turma',semoryear as 'Sem/Anp',subject as 'Disciplina',position as 'Cargo' from docentes "+condition+" order by facultyid asc";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean checkPassword(String facultyid,String password)
	{
		boolean result=false;
		try
		{
			
			if(facultyid.isEmpty()||facultyid.equalsIgnoreCase(" Enter faculty user-id"))
			{
				JOptionPane.showMessageDialog(null, "Usuario ou password incorrectos","Erro",JOptionPane.ERROR_MESSAGE);
				result=false;
			}
			else
			{
				String query="select count(*) from docentes where facultyid="+facultyid+" and password='"+password+"'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				rs.next();
				if(rs.getInt(1)>0)
				{
					result=true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuario ou password incorrectos","Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	public void setActiveStatus(boolean activestatus,int facultyid)
	{
		try
		{
			String query="update docentes set activestatus="+activestatus+" where facultyid="+facultyid;
			PreparedStatement pr=con.prepareStatement(query);
			pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public String getLastLogin(String userid)
	{
		try
		{
			String query="select lastlogin from docentes where facultyid="+userid+"";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public Image getProfilePic(String userid)
	{
		Image image=null;
		try
		{
			String query="select profilepic from docentes where facultyid="+userid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			byte[] imagedata=rs.getBytes(1);
			image=Toolkit.getDefaultToolkit().createImage(imagedata);
			rs.close();
			st.close();
		}
		catch(Exception exp)
		{
		exp.printStackTrace();	
		}
		return image;
	}
	public int changePassword(String userid,String password)
	{
		try
		{
			String query="update docentes set password='"+password+"' where facultyid="+userid;
			PreparedStatement pr=con.prepareStatement(query);
			return pr.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return 0;
	}
	
}

