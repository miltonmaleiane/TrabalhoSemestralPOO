package estudante;

import java.awt.Image;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import comuns.DataBaseConnection;
import comuns.Notificacoes;
import comuns.NotificacaoDados;
import comuns.TimeUtil;
import disciplina.DadosDisciplina;

/*
 
 */

public class DadosEstudante 
{
	double avg ;
	int n1;
	private int totalstudent=0;
	int teste1, teste2;
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	
	public int addStudent(Estudante s)
	{
		int result=0;
		String query="insert into estudantes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
		
			//Adding notification of new student
			{
				Notificacoes n=new Notificacoes();
				n.setUserProfile("Student");
				n.setCodigoCurso(s.getCodigoCurso());
				n.setSemouano(s.getSemouano());
				n.setTitle("Novo estuante");
				n.setUserId(s.generateUserId());
				n.setMessage(s.getNomecompleto()+" ("+s.getNrestudante()+") Foi Adicionado à tua turma.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificacaoDados().addNotification(n);
				n.setUserProfile("Faculty");
				new NotificacaoDados().addNotification(n);
			}
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, s.getCodigoCurso());
			pr.setInt(2,s.getSemouano());
			pr.setLong(3,s.getNrestudante());
			pr.setString(4, s.getDisciplinaopcional());
			pr.setString(5,s.getPrimeiroNome());
			pr.setString(6, s.getUltimonome());
			pr.setString(7, s.getEmailId());
			pr.setString(8, s.getContactNumber());
			pr.setString(9,s.getBirthDate());
			pr.setString(10, s.getGender());
			pr.setString(11, s.getState());
			pr.setString(12, s.getCity());
			pr.setString(13,s.getNomepai());
			pr.setString(14,s.getOcupacaopai());
			pr.setString(15, s.getNomemae());
			pr.setString(16, s.getOcupacaomae());
			pr.setBytes(17, s.getProfilePicInBytes());
			pr.setInt(18, 0);//sr no 
			pr.setString(19, "");//lastlogin
			pr.setString(20, s.generateUserId());//userid
			pr.setString(21, s.getBirthDate());//password
			pr.setBoolean(22,false);//activestatus
			pr.setString(23,s.gerarDataadmissao() );
			 result=pr.executeUpdate();
		
			pr.close();
			return result;
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	public int deleteMarksData(Estudante s)
	{
		int result=0;
		String query="delete from notas where courcecode='"+s.getCodigoCurso()+"' and semoryear="+s.getSemouano()+" and rollnumber="+s.getNrestudante();
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
	public int deleteAttandanceData(Estudante s)
	{
		int result=0;
		String query="delete from presencas where codigoCurso='"+s.getCodigoCurso()+"' and semouano="+s.getSemouano()+" and numeroEstudante="+s.getNrestudante();
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
	public int deleteUsersHistory(Estudante s)
	{
		int result=0;
		String query="delete from users where userid='"+s.getIdusuario()+"'";
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
	public int deleteChatHistory(Estudante s)
	{
		int result=0;
		String query="delete from chat where touserid='"+s.getIdusuario()+"' or fromuserid='"+s.getIdusuario()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			if(result>0)
			{
				this.reArrangeChatSrNoColumn();
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void reArrangeChatSrNoColumn()
	{
		
		try
		{
			String query="alter table chat drop sr_no;";
			PreparedStatement pr=con.prepareStatement(query);
			pr.executeUpdate();
			query="alter table chat add sr_no int primary key auto_increment first";
			pr=con.prepareStatement(query);
			pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public int deleteNotificationHistory(Estudante s)
	{
		int result=0;
		String query="delete from notification where userid='"+s.getIdusuario()+"'";
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
	public int deleteOldOptionalSubjectMarks(Estudante s)
	{
		int result=0;
		try
		{
			String query="delete from notas where courcecode='"+s.getCodigoCurso()+"' and semoryear="+s.getSemouano()+" and rollnumber="+s.getNrestudante()+" and subjectname='"+s.getDisciplinaopcional()+"'";
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public int updateStudentData(Estudante sold,Estudante s)
	{
		int result=0;
		String query="update estudantes set Courcecode=?,semouano=?,numeroEstudante=?,disciplinaOpcional=?,primeiroNome=?,ultimoNome=?,emailid=?,numeroTelefone=?,dataNascimento=?,genero=?,provincia=?,cidade=?,nomePai=?,ocupacaoPai=?,nomeMae=?,ocupacaoMae=?,profilepic=?,lastlogin=?,activestatus=?,userid=? where courcecode='"+sold.getCodigoCurso()+"' and semouano="+sold.getSemouano()+" and numeroEstudante="+sold.getNrestudante();
	
		//  se o curso o semestre ou o numero de estudante foi alterado
		if(!s.getCodigoCurso().equals(sold.getCodigoCurso()) || s.getSemouano()!=sold.getSemouano() || s.getNrestudante()!=sold.getNrestudante())
		{
			
			
			//Adding notification 
			{
				Notificacoes n=new Notificacoes();
				n.setUserProfile("Student");
				n.setCodigoCurso(s.getCodigoCurso());
				n.setSemouano(s.getSemouano());
				n.setTitle("Novo estudante");
				n.setUserId(s.generateUserId());
				n.setMessage(s.getNomecompleto()+" ("+s.getNrestudante()+") entrou em sua turma.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificacaoDados().addNotification(n);
				n.setUserProfile("Faculty");
				new NotificacaoDados().addNotification(n);
			}
			//deleting all the data of student from this cource
			this.deleteMarksData(sold);
			this.deleteAttandanceData(sold);
			this.deleteUsersHistory(sold);
			this.deleteNotificationHistory(sold);
			this.deleteChatHistory(sold);
		}
		
		if(!s.getDisciplinaopcional().equals(sold.getDisciplinaopcional()))
		{
			this.deleteOldOptionalSubjectMarks(sold);
		}
		try
		{
		
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, s.getCodigoCurso());
			pr.setInt(2,s.getSemouano());
			pr.setLong(3,s.getNrestudante());
			pr.setString(4, s.getDisciplinaopcional());
			pr.setString(5,s.getPrimeiroNome());
			pr.setString(6, s.getUltimonome());
			pr.setString(7, s.getEmailId());
			pr.setString(8, s.getContactNumber());
			pr.setString(9,s.getBirthDate());
			pr.setString(10, s.getGender());
			pr.setString(11, s.getState());
			pr.setString(12, s.getCity());
			pr.setString(13,s.getNomepai());
			pr.setString(14,s.getOcupacaopai());
			pr.setString(15, s.getNomemae());
			pr.setString(16, s.getOcupacaomae());
			pr.setBytes(17, s.getProfilePicInBytes());
			pr.setString(18, s.getLastLogin());
			pr.setBoolean(19, s.getActiveStatus());
			pr.setString(20,s.generateUserId());
			 result=pr.executeUpdate();
		
		
			pr.close();
			return result;
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

	public int getTotalStudentInCource(String Courcecode,int sem)
	{
		int rollnumber=0;
	
		String query="select numeroEstudante from estudantes where courcecode='"+Courcecode+"' and semouano="+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				rollnumber++;
			}
			st.close();
			rs.close();
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public String[] getRollNumber(String Courcecode,int sem)
	{
		String rollnumber[]=null;
		int i=0;
		String query="select numeroEstudante from estudantes where courcecode='"+Courcecode+"' and semouano="+sem+" order by numeroEstudante asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			long num;
			rollnumber=new String[this.getTotalStudentInCource(Courcecode, sem)+1];
			rollnumber[i++]="---Selecione o numero do estudante---";
			while(rs.next())
			{
				num=rs.getLong(1);
				rollnumber[i++]=num+"";
				
			}
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public ResultSet getStudentinfo(String condition)
	{
		ResultSet rs=null;
		String query="select s.courcecode as 'Turma' ,s.numeroEstudante as 'Nr de estudante',concat(s.primeiroNome,' ',s.ultimoNome) as 'Nome',c.courcename as 'Nome Curso',concat(c.semoryear,'-',s.semouano) as 'Sem/Ano' from estudantes s ,cursos c where s.courcecode=c.courcecode "+condition+" order by s.courcecode asc,s.semouano asc,s.numeroEstudante asc";
		
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet searchStudent(String query)
	{

		query+=" order by s.courcecode asc,s.semouano asc,s.numeroEstudante asc";
		ResultSet rs=null;
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public Estudante getStudentDetails(String Courcecode,int sem,long rollnumber)
	{
		Estudante s=new Estudante();
		
		String query=" select * from estudantes where courcecode='"+Courcecode+"' and semouano="+sem+" and numeroEstudante="+rollnumber;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			s.setCodigoCurso(rs.getString(1));
			s.setSemouano(rs.getInt(2));
			s.setNrestudante(rs.getLong(3));
			s.setDisciplinaopcional(rs.getString(4));
			s.setPrimeiroNome(rs.getString(5));
			s.setUltimonome(rs.getString(6));
			s.setEmailId(rs.getString(7));
			s.setContactNumber(rs.getString(8));
			s.setBirthDate(rs.getString(9));
			s.setGender(rs.getString(10));
			s.setState(rs.getString(11));
			s.setCity(rs.getString(12));
			s.setNomepai(rs.getString(13));
			s.setOcupacaopai(rs.getString(14));
			s.setNomemae(rs.getString(15));
			s.setOcupacaomae(rs.getString(16));
			s.setProfilePic(rs.getBytes(17));
			s.setSrNo(rs.getInt(18));
			s.setLastLogin(rs.getString(19));
			s.setIdusuario(rs.getString(20));
			s.setPassword(rs.getString(21));
			s.setActiveStatus(rs.getBoolean(22));
			s.setDataadmissao(rs.getString(23));

			return s;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	public int addStudentTheoryMarks(Notas m)
	{
		
		int result=0;
	String sit = "indefinida";
		try
		{
		
			//String sql="update notas"+
		//" set theorymarks=?, media =? where subjectcode=? and rollnumber=?";
		//	String sql="update notas set theorymarks="+m.getTheoryMarks()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getRollNumber(); // funciona
	//	String sql="update notas set media="+m.getTheoryMarks()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getRollNumber(); //actualizar media
		String qr="update notas"+
	" set media="+m.getMedia()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getNrestudante();
		String sql="update notas"+
				" set theorymarks="+m.getTeste1()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getNrestudante();	
		
		
		//String query="update notas"+ 
			//"set theorymarks= ?"+
			//" where subjectcode="+ m.getSubjectCode()+ "and rollnumber="+m.getNrestudante();
			String query;
		
			PreparedStatement pr=con.prepareStatement(sql);
			PreparedStatement st=con.prepareStatement(qr);
	
			result = pr.executeUpdate();
			result = st.executeUpdate();
			m.setAux(m.getTeste1());
			if(result==0)
			{
				query="insert into notas values(?,?,?,?,?,?,?,?,?)";
				pr=con.prepareStatement(query);
				pr.setString(1, m.getCodigoCurso());
				pr.setInt(2, m.getSemouano());
				pr.setString(3, m.getSubjectCode());
				pr.setString(4, m.getSubjectName());
				pr.setLong(5, m.getNrestudante());
				pr.setInt(6, m.getTeste1());
				
				pr.setInt(7,Types.NULL);
				pr.setDouble(8,m.getTeste1()); // eu
				pr.setString(9,sit);
				result=pr.executeUpdate();
				teste1 = m.getTeste1();
			}
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp);
			
		}
		return result;
		
	}
	public int nota1 (Notas m) {
		//teste1 =   m.getTheoryMarks();
		return teste1;
	}
	Integer x;
	public int addStudentPracticalMarks(Notas m)
	{
		int result=0;


		
       

		teste2 = m.getTeste2();
		
        
		



		
		try
		{


			
			
// buscar nota down
			String nota1;
			Connection coni ;
			Statement stmt;
			ResultSet rs;
		
			stmt = con.createStatement();     // Create a Statement object           1 
			rs = stmt.executeQuery("SELECT theorymarks FROM notas where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+ m.getNrestudante());                  
			                                  // Get the result table from the query
			while (rs.next()) {               // Position the cursor                 3 
				nota1 = rs.getString(1);             // Retrieve only the first column value
			 System.out.println("therorymarks = " + nota1);
			 x = Integer.parseInt(nota1);                         // Print the column value
			}
			rs.close();                       // Close the ResultSet                 4 
			stmt.close();                     // Close the Stat
			
		
			avg= (x+ m.getTeste2() )/2;
			 System.out.println("AVG 4r " + avg);
			 String situacao;
			 if(avg<9.5) {
				 situacao = "excluido";
			 }else {
				 if(avg>=13.5) {
					 situacao = "dispensado";
				 }else {
					 situacao = "admitido";
				 }
			 }
			 System.out.println("situacao " + situacao);
			 /////////
			//String sql = "SELECT theorymarks FROM usuario WHERE subjectcode="+m.getSubjectCode()+" and rollnumber="+m.getRollNumber();//eu
				String query="update notas set practicalmarks="+m.getTeste2()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getNrestudante();
				PreparedStatement pr=con.prepareStatement(query);

				
				result=pr.executeUpdate();
				//inserir media a bd
				String qr="update notas set media= "+avg+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getNrestudante();
				PreparedStatement st=con.prepareStatement(qr);
				result = st.executeUpdate();
				//inserir situacao a bd
				String inst="update notas set situacao=? where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getNrestudante();
				PreparedStatement ex=con.prepareStatement(inst);
				ex.setString(1, situacao);
				result = ex.executeUpdate();
				//****/
			if(result==0)
			{
				query="insert into notas values(?,?,?,?,?,?,?)";
				pr=con.prepareStatement(query);
				pr.setString(1, m.getCodigoCurso());
				pr.setInt(2, m.getSemouano());
				pr.setString(3, m.getSubjectCode());
				pr.setString(4, m.getSubjectName());
				pr.setLong(5, m.getNrestudante());
				pr.setInt(6, Types.NULL);
				pr.setInt(7,m.getTeste2());
				pr.setDouble(8,m.getTeste2()); // eu
				result=pr.executeUpdate();	
			
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	
		return result;
		
		
	}
	public ArrayList<Notas> getStudentTheoryMarksDetails(String Courcecode,int sem,String subjectname)
	{
		ResultSet rs=null;		
		ArrayList<Notas> notas=new ArrayList<Notas>();
		String subjectcode=new DadosDisciplina().getSubjectCode(Courcecode, sem, subjectname);
		String query="select distinct s.primeiroNome,s.numeroEstudante,disciplina.subjectname,disciplina.theorymarks,m.theorymarks from estudantes s left join notas m on s.numeroEstudante=m.rollnumber and m.subjectcode='"+subjectcode+"',disciplina where s.courcecode='"+Courcecode+"' and s.semouano="+sem+" and disciplina.subjectcode='"+subjectcode+"'  order by s.numeroEstudante asc";
		String subjecttype=new DadosDisciplina().checkCoreorOptional(subjectcode);
		if(!subjecttype.equals("nuclear"))
		{
			query="select distinct s.primeiroNome,s.numeroEstudante,disciplina.subjectname,disciplina.theorymarks,m.theorymarks from estudantes s left join notas m on s.numeroEstudante=m.rollnumber and m.subjectcode='"+subjectcode+"',disciplina where s.disciplinaOpcional=disciplina.subjectname  and s.courcecode='"+Courcecode+"' and s.semouano="+sem+" and disciplina.subjectcode='"+subjectcode+"'  order by s.numeroEstudante asc";

		}
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Notas m=new Notas();
				m.setNrestudante(rs.getLong(2));
				m.setNomeestudante(rs.getString(1));
				m.setSubjectName(rs.getString(3));
				m.setMaxTheoryMarks(rs.getInt(4));
				m.setTeste1(rs.getInt(5));
				notas.add(m);
				
			}
			st.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return notas;
	}
	public ArrayList<Notas> getStudentPracticalMarksDetails(String Courcecode,int sem,String subjectname)
	{
		ResultSet rs=null;		
		ArrayList<Notas> notas=new ArrayList<Notas>();
		String subjectcode=new DadosDisciplina().getSubjectCode(Courcecode, sem, subjectname);
		String query="select distinct s.primeiroNome,s.numeroEstudante,disciplina.subjectname,disciplina.practicalmarks,m.practicalmarks, m.media from estudantes s left join notas m on s.numeroEstudante=m.rollnumber and m.subjectcode='"+subjectcode+"',disciplina where s.courcecode='"+Courcecode+"' and s.semouano="+sem+" and disciplina.subjectcode='"+subjectcode+"' order by s.numeroEstudante asc";
		String subjecttype=new DadosDisciplina().checkCoreorOptional(subjectcode);
		if(!subjecttype.equals("nuclear"))
		{
			query="select distinct s.primeiroNome,s.numeroEstudante,disciplina.subjectname,disciplina.practicalmarks,m.practicalmarks, m.media from estudantes s left join notas m on s.numeroEstudante=m.rollnumber and m.subjectcode='"+subjectcode+"',disciplina where s.disciplinaOpcional=disciplina.subjectname  and s.courcecode='"+Courcecode+"' and s.semouano="+sem+" and disciplina.subjectcode='"+subjectcode+"'  order by s.numeroEstudante asc";

		}
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Notas m=new Notas();
				m.setNrestudante(rs.getLong(2));
				m.setNomeestudante(rs.getString(1));
				m.setSubjectName(rs.getString(3));
				m.setMaxPracticalMarks(rs.getInt(4));
				m.setTeste2(rs.getInt(5));
				m.setMedia(rs.getDouble(6));
				notas.add(m);
				
			}
			st.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return notas;
	}
	public Estudante getStudentDetails(int row)
	{
		Estudante s=new Estudante();
		String query="select * from estudantes where sr_no="+row;
		try
		{
		
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			s.setCodigoCurso(rs.getString(1));
			s.setSemouano(rs.getInt(2));
			s.setNrestudante(rs.getLong(3));
			s.setDisciplinaopcional(rs.getString(4));
			s.setPrimeiroNome(rs.getString(5));
			s.setUltimonome(rs.getString(6));
			s.setEmailId(rs.getString(7));
			s.setContactNumber(rs.getString(8));
			s.setBirthDate(rs.getString(9));
			s.setGender(rs.getString(10));
			s.setState(rs.getString(11));
			s.setCity(rs.getString(12));
			s.setNomepai(rs.getString(13));
			s.setOcupacaopai(rs.getString(14));
			s.setNomemae(rs.getString(15));
			s.setOcupacaomae(rs.getString(16));
			s.setProfilePic(rs.getBytes(17));
			s.setSrNo(rs.getInt(18));
			s.setLastLogin(rs.getString(19));
			s.setIdusuario(rs.getString(20));
			s.setPassword(rs.getString(21));
			s.setActiveStatus(rs.getBoolean(22));
			s.setDataadmissao(rs.getString(23));
			return s;
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public Estudante getStudentDetailsByUserId(String userid)
	{
		Estudante s=new Estudante();
		String query="select * from estudantes where userid='"+userid+"'";
		try
		{
		
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
				rs.next();
				s.setCodigoCurso(rs.getString(1));
				s.setSemouano(rs.getInt(2));
				s.setNrestudante(rs.getLong(3));
				s.setDisciplinaopcional(rs.getString(4));
				s.setPrimeiroNome(rs.getString(5));
				s.setUltimonome(rs.getString(6));
				s.setEmailId(rs.getString(7));
				s.setContactNumber(rs.getString(8));
				s.setBirthDate(rs.getString(9));
				s.setGender(rs.getString(10));
				s.setState(rs.getString(11));
				s.setCity(rs.getString(12));
				s.setNomepai(rs.getString(13));
				s.setOcupacaopai(rs.getString(14));
				s.setNomemae(rs.getString(15));
				s.setOcupacaomae(rs.getString(16));
				s.setProfilePic(rs.getBytes(17));
				s.setSrNo(rs.getInt(18));
				s.setLastLogin(rs.getString(19));
				s.setIdusuario(rs.getString(20));
				s.setPassword(rs.getString(21));
				s.setActiveStatus(rs.getBoolean(22));
				s.setDataadmissao(rs.getString(23));
				
			return s;
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public int getTotalStudents()
	{
		int totalstudent=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from estudantes");
			while(rs.next())
			{
				totalstudent++;
			}
			rs.close();
			st.close();
			return totalstudent;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalstudent;
	}

	public Notas getOptionalSubjectMarks(String courcecode,int semoryear,long rollnumber)
	{
		try
		{
			Notas m=new Notas();
			m.setCodigoCurso(courcecode);
			m.setSemouano(semoryear);
			m.setNrestudante(rollnumber);
			String scode=this.getOptionalSubjectCode(courcecode,semoryear,rollnumber);
			if(scode==null)
			{
				return null;
			}
			String query="select su.subjectcode,su.subjectname,su.theorymarks,m.theorymarks,su.practicalmarks,m.practicalmarks from disciplina su left join notas m on m.subjectcode='"+scode+"' and m.rollnumber="+rollnumber+" where su.subjectcode='"+scode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			m.setSubjectCode(rs.getString(1));
			m.setSubjectName(rs.getString(2));
			m.setMaxTheoryMarks(rs.getInt(3));
			m.setTeste1(rs.getInt(4));
			m.setMaxPracticalMarks(rs.getInt(5));
			m.setTeste2(rs.getInt(6));
			m.setNrestudante(rollnumber);
			m.setNomeestudante(getStudentName(courcecode+"-"+semoryear+"-"+rollnumber));
			return m;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public String getOptionalSubjectCode(String courcecode,int semoryear,long rollnumber)
	{
		String osub=null;
		
		try
		{
			String query="select disciplinaOpcional from estudantes where userid='"+courcecode+"-"+semoryear+"-"+rollnumber+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			osub=rs.getString(1);
			osub=new DadosDisciplina().getSubjectCode(courcecode, semoryear, osub);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return osub;
		
	}
	public ArrayList<Notas> getMarkssheetOfStudent(String courcecode,int sem,long rollnumber)
	{
			ArrayList<Notas> list=new ArrayList<Notas>();
			String query="select su.subjectcode,su.subjectname,su.theorymarks,m.theorymarks,su.practicalmarks,m.practicalmarks, m.media, m.situacao from disciplina su left join notas m on su.subjectname=m.subjectname and m.rollnumber="+rollnumber+" and m.semoryear="+sem+" and m.courcecode='"+courcecode+"' where su.courcecode='"+courcecode+"' and su.semoryear="+sem+" and su.subjecttype='nuclear' order by su.subjectcode asc";
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int index=1;
				while(rs.next())
				{
					Notas m=new Notas();
					
					m.setSrNo(index);
					m.setSubjectCode(rs.getString(1));
					m.setSubjectName(rs.getString(2));
					m.setMaxTheoryMarks(rs.getInt(3));
					m.setTeste1(rs.getInt(4));
					m.setMaxPracticalMarks(rs.getInt(5));
					m.setTeste2(rs.getInt(6));
					m.setMedia(rs.getDouble(7));
					m.setSituacao(rs.getString(8));
					m.setNrestudante(rollnumber);
					m.setNomeestudante(getStudentName(courcecode+"-"+sem+"-"+rollnumber));
					index++;
					list.add(m);
				}
				{
					Notas m=getOptionalSubjectMarks(courcecode,sem,rollnumber);
					if(m!=null)
					{
					m.setSrNo(index);
					list.add(m);
					}
				}
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return list;
	}
	public int addStudentAttandance(Presencas a)
	{
		int result=0;
		try
		{
			
			String query="insert into presencas values(?,?,?,?,?,?)";
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, a.getSubjectCode());
			pr.setString(2, a.getAttandanceDate());
			pr.setLong(3, a.getRollNumber());
			pr.setBoolean(4,a.getPresentStatus());
			pr.setString(5,a.getCodigoCurso());
			pr.setInt(6,a.getSemouano());
			result=pr.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Presencas> getStudentsForAttandance(Presencas a)
	{
		ArrayList<Presencas> list=new ArrayList<Presencas>();
		String query="select s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome),s.semouano,a.presenca from estudantes s left join presencas a on"
				+ " s.numeroEstudante=a.numeroEstudante"
				+ " and a.data='"+a.getAttandanceDate()+"'"
				+ " and a.codigoDisciplina='"+a.getSubjectCode()+"'"
				+ " where s.courcecode='"+a.getCodigoCurso()+"'"
				+ " and s.semouano="+a.getSemouano();
		String subjecttype=new DadosDisciplina().checkCoreorOptional(a.getSubjectCode());
		if(!subjecttype.equals("nuclear"))
		{
			query+=" and s.disciplinaOpcional='"+a.getSubjectName()+"'";
		}
		query+=" order by s.numeroEstudante asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Presencas at=new Presencas();
				at.setRollNumber(rs.getLong(1));
				at.setStudentName(rs.getString(2));
				at.setSemouano(rs.getInt(3));
				at.setPresentStatus(rs.getBoolean(4));
				at.setCodigoCurso(a.getCodigoCurso());
				at.setSubjectName(a.getSubjectName());
				list.add(at);
				
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public boolean isSubmitted(String subjectcode,String date)
	{
		try
		{
			String query=" select count(*) from presencas where "
					+ "codigoDisciplina='"+subjectcode
					+ "'"
					+ " and date='"+date
					+ "'";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getInt(1)>0?true:false;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public ArrayList<Presencas> getAttandanceReportBySubject(Presencas a)
	{
		ArrayList<Presencas> list=new ArrayList<Presencas>();
		try
		{
			//JOptionPane.showMessageDialog(null, "Yoooo");
			String query="select distinct s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome) as 'Nome',(select count(*) from presencas where "
					+ "codigoDisciplina='"+a.getSubjectCode()
					+ "' and numeroEstudante=s.numeroEstudante and presenca=1),(select count(*) from presencas where codigoDisciplina='"+a.getSubjectCode()+"' and numeroEstudante=s.numeroEstudante)"
							+ "from estudantes s left join presencas a on s.numeroEstudante=a.numeroEstudante where "
							+ "s.courcecode='"+a.getCodigoCurso()
							+ "' and s.semouano="+a.getSemouano()+" ";
			String subjecttype=new DadosDisciplina().checkCoreorOptional(a.getSubjectCode());
			if(!subjecttype.equals("nuclear"))
			{
				query+=" and s.disciplinaOpcional='"+a.getSubjectName()+"'";
			}
			query+=" order by s.numeroEstudante asc";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Presencas at=new Presencas();
			at.setRollNumber(rs.getLong(1));
			at.setStudentName(rs.getString(2));
			at.setCodigoCurso(a.getCodigoCurso());
			at.setSubjectName(a.getSubjectName());
			at.setAttandance(rs.getInt(3));
			at.setTotalAttandance(rs.getInt(4));
			list.add(at);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Notas> getMarksheetReportBySubject(Notas notas)
	{
		ArrayList<Notas> list=new ArrayList<Notas>();
		try
		{
			String query="select distinct s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome) as 'Nome'"
					+ ",(select media from notas where "
					+ "subjectcode='"+notas.getSubjectCode()
					+ "' and rollnumber=s.numeroEstudante) as 'media'"
					
					+ ",(select situacao from notas where"
					+ " subjectcode='"+notas.getSubjectCode()
					+ "' and rollnumber=s.numeroEstudante) as 'situacao',"
					
					+ "(select theorymarks from disciplina where subjectcode='"+notas.getSubjectCode()
					+ "') as 'Total teste1',(select practicalmarks from disciplina where subjectcode='"+notas.getSubjectCode()
					+ "') as 'Total teste2' from estudantes s left join notas m on s.numeroEstudante=m.rollnumber where s.courcecode='"+notas.getCodigoCurso()
					+ "' and s.semouano="+notas.getSemouano();
					
					//media
				//	+ ",(select media from notas where"
				//	+ " subjectcode='"+notas.getSubjectCode()
				//	+ "' and rollnumber=s.rollnumber) as 'media',";
					//*media
					
			String subjecttype=new DadosDisciplina().checkCoreorOptional(notas.getSubjectCode());
			if(!subjecttype.equals("nuclear"))
			{
				query+=" and s.disciplinaOpcional='"+notas.getSubjectName()+"'";
			}
			query+=" order by s.numeroEstudante asc";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Notas m=new Notas();
				m.setNrestudante(rs.getLong(1));
				m.setNomeestudante(rs.getString(2));
				m.setSubjectName(notas.getSubjectName());
				m.setMedia(rs.getInt(3));
				m.setSituacao(rs.getString(4));
				m.setMaxTheoryMarks(rs.getInt(5));
				m.setMaxPracticalMarks(rs.getInt(6));
				//m.setMedia(rs.getDouble(7));    eu
				list.add(m);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Notas> getMarksheetReportByClass(Notas notas)
	{
		ArrayList<Notas> list=new ArrayList<Notas>();
		try
		{
			String query="select distinct s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome) as 'Nome',(select sum(theorymarks) from notas where courcecode=s.courcecode and semoryear=s.semouano and rollnumber=s.numeroEstudante) as 'Teste1',(select sum(practicalmarks) from notas where courcecode=s.courcecode and semoryear=s.semouano  and rollnumber=s.numeroEstudante) as 'Teste2',(select sum(theorymarks) from disciplina where courcecode=s.courcecode and semoryear=s.semouano and subjecttype='nuclear') as 'Total teste1',(select sum(practicalmarks) from disciplina where courcecode=s.courcecode and semoryear=s.semouano and  subjecttype='nuclear' ) as 'Total teste2' from estudantes s left join notas m on s.numeroEstudante=m.rollnumber where s.courcecode='"+notas.getCodigoCurso()+"' and s.semouano="+notas.getSemouano();	
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Notas m=new Notas();
				String subjectcode=this.getOptionalSubjectCode(notas.getCodigoCurso(), notas.getSemouano(), rs.getLong(1));
				int maxpracticalmarks=new DadosDisciplina().getMaxTheoryMarksOfSubject(subjectcode);
				int maxtheorymarks=new DadosDisciplina().getMaxPracticalMarksOfSubject(subjectcode);
				
				m.setNrestudante(rs.getLong(1));
				m.setNomeestudante(rs.getString(2));
				m.setSubjectName(notas.getSubjectName());
				m.setTeste1(rs.getInt(3));
				m.setTeste2(rs.getInt(4));
				m.setMaxTheoryMarks(rs.getInt(5)+maxtheorymarks);
				m.setMaxPracticalMarks(rs.getInt(6)+maxpracticalmarks);
				
				list.add(m);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Presencas> getAttandanceReportByClass(Presencas a)
	{
		ArrayList<Presencas> list=new ArrayList<Presencas>();
		try
		{
			String query="select distinct s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome) as 'Student Name',(select count(*) from presencas where "
					+ "codigoCurso='"+a.getCodigoCurso()
					+"' and semouano="+a.getSemouano()   //possivel bug
					+ " and numeroEstudante=s.numeroEstudante and presenca=1),(select count(*) from presencas where codigoCurso='"+a.getCodigoCurso()+"' and semouano="+a.getSemouano()+" and  numeroEstudante=s.numeroEstudante)"
							+ " from estudantes s left join presencas a on s.numeroEstudante=a.numeroEstudante where "
							+ "s.courcecode='"+a.getCodigoCurso()
							+ "' and s.semouano="+a.getSemouano()+" order by s.numeroEstudante";	
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Presencas at=new Presencas();
			at.setRollNumber(rs.getLong(1));
			at.setStudentName(rs.getString(2));
			at.setSubjectName("All");
			at.setAttandance(rs.getInt(3));
			at.setTotalAttandance(rs.getInt(4));
			
			list.add(at);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Presencas> getTotalAttandanceReportOfStudent(Presencas a)
	{
		ArrayList<Presencas> list=new ArrayList<Presencas>();
		try
		{
			String query="select distinct s.numeroEstudante,concat(s.primeiroNome,' ',s.ultimoNome) as 'Student Name',(select count(*) from presencas where "
					+ "codigoCurso='"+a.getCodigoCurso()
					+"' and semouano="+a.getSemouano()
					+ " and numeroEstudante=s.numeroEstudante and presenca=1),(select count(*) from presencas where codigoCurso='"+a.getCodigoCurso()+"' and semouano="+a.getSemouano()+" and  numeroEstudante=s.numeroEstudante)"
					+ " from estudantes s left join presencas a on s.numeroEstudante=a.numeroEstudante	 where "
					+ "s.courcecode='"+a.getCodigoCurso()
					+ "' and s.semouano="+a.getSemouano()
					+ " and s.numeroEstudante="+a.getRollNumber()
					
					+" order by s.numeroEstudante";	
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Presencas at=new Presencas();
				at.setRollNumber(rs.getLong(1));
				at.setStudentName(rs.getString(2));
				at.setSubjectName("All");
				at.setAttandance(rs.getInt(3));
				at.setTotalAttandance(rs.getInt(4));
				
				
				list.add(at);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Presencas> getAttandanceReportByStudent(Presencas a)
	{
		ArrayList<Presencas> list=new ArrayList<Presencas>();
		try
		{
			String query="select distinct su.subjectcode,su.subjectname,(select count(*) from presencas where codigoDisciplina=su.subjectcode "
					+ "and numeroEstudante="+a.getRollNumber()+" and presenca=1) as 'presencas',(select count(*) from presencas where"
							+ " codigoDisciplina=su.subjectcode and numeroEstudante="+a.getRollNumber()+") as 'Total presencas' from disciplina su "
									+ "left join presencas a on su.subjectcode=a.codigoDisciplina where su.courcecode='"+a.getCodigoCurso()
									+"' and su.semoryear="+a.getSemouano()+"  order by su.subjectcode asc;";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Presencas at=new Presencas();
			at.setStudentName(this.getStudentName(a.getCodigoCurso()+"-"+a.getSemouano()+"-"+a.getRollNumber()));
			at.setRollNumber(a.getRollNumber());;
			at.setSubjectCode(rs.getString(1));
			at.setSubjectName(rs.getString(2));
			at.setAttandance(rs.getInt(3));
			at.setTotalAttandance(rs.getInt(4));
			String courcetype=new DadosDisciplina().checkCoreorOptional(at.getSubjectCode());
			if(!courcetype.equals("nuclear"))
			{
				if(at.getSubjectCode().equals(this.getOptionalSubjectCode(a.getCodigoCurso(), a.getSemouano(), a.getRollNumber())))
				{
					list.add(at);
				}
			}
			else
			{
			list.add(at);
			}
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public boolean checkPassword(String userid,String password)
	{
		boolean result=false;
		try
		{
			String query="select count(*) from estudantes where userid='"+userid+"' and password='"+password+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			if(rs.getInt(1)>0)
			{
				result=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public ArrayList<Estudante> getStudentsDetails(String condition)
	{
			ArrayList<Estudante> list=new ArrayList<Estudante>();
			String query="select * from estudantes s "+condition+" order by s.courcecode asc,s.semouano asc,s.numeroEstudante asc";
			try
			{
			
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				
				while(rs.next())
				{
					Estudante s=new Estudante();
					s.setCodigoCurso(rs.getString(1));
					s.setSemouano(rs.getInt(2));
					s.setNrestudante(rs.getLong(3));
					s.setDisciplinaopcional(rs.getString(4));
					s.setPrimeiroNome(rs.getString(5));
					s.setUltimonome(rs.getString(6));
					s.setEmailId(rs.getString(7));
					s.setContactNumber(rs.getString(8));
					s.setBirthDate(rs.getString(9));
					s.setGender(rs.getString(10));
					s.setState(rs.getString(11));
					s.setCity(rs.getString(12));
					s.setNomepai(rs.getString(13));
					s.setOcupacaopai(rs.getString(14));
					s.setNomemae(rs.getString(15));
					s.setOcupacaomae(rs.getString(16));
					s.setProfilePic(rs.getBytes(17));
					s.setSrNo(rs.getInt(18));
					s.setLastLogin(rs.getString(19));
					s.setIdusuario(rs.getString(20));
					s.setPassword(rs.getString(21));
					s.setActiveStatus(rs.getBoolean(22));
					s.setDataadmissao(rs.getString(23));
					list.add(s);
				}
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
	}
	public boolean isActive(String userid)
	{
		try
		{
			String query="select activestatus from estudantes where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			boolean active=rs.getBoolean(1);
			st.close();
			rs.close();
			return active;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	
	public String getStudentName(String userid)
	{
		String name="";
		try
		{
			String query="select concat(primeiroNome,' ',ultimoNome) from estudantes where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			name=rs.getString(1);
			
			rs.close();
			st.close();
		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return name;
	}
	public int setActiveStatus(boolean activestatus,String userid)
	{
		int result=0;
		try
		{
			String query="update estudantes set activestatus="+activestatus+" where userid='"+userid+"'";
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
	public String getLastLogin(String userid)
	{
		try
		{
			String query="select lastlogin from estudantes where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return "deleted";
		}
	}
	public Image getProfilePic(String userid)
	{
		Image image=null;
		try
		{
			String query="select profilepic from estudantes where userid='"+userid+"'";
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
			String query="update estudantes set password='"+password+"' where userid='"+userid+"'";
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

