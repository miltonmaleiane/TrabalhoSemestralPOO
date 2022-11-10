package comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import curso.Curso;
import docente.DadosDocente;
import estudante.DadosEstudante;


/*

 */


class Usuario extends Curso
{
	
	
	private String dataLogin;
	private String tempoLogin;
	private String idusuario;
	private String perfilUsuario;
	
	public void settempoLogin(String tempoLogin)
	{
		SimpleDateFormat timeformatter=new SimpleDateFormat("hh:mm aaa");
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date;
		try {
			date = formater.parse(tempoLogin);
			this.tempoLogin=timeformatter.format(date);
			this.dataLogin=dateformatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setidusuario(String idusuario)
	{
		this.idusuario=idusuario;
	}
	public void  setperfilUsuario(String perfilUsuario)
	{
		this.perfilUsuario=perfilUsuario;
	}
	public String gettempoLogin()
	{
		return tempoLogin;
	}
	public String getdataLogin()
	{
		if(getCurrentDate().equals(dataLogin))
		{
			return "hoje";
		}
		
		return dataLogin;
	}
	public String getCurrentDate()
	{
		Date date=new Date();
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		String time=dateformatter.format(date);
		return time;
	}
	public String getidusuario()
	{
		return idusuario;
	}
	public String getperfilUsuario() 
	{
		return perfilUsuario;
	}
	
	public String getName() 
	{
		if(getperfilUsuario().equals("Estudante"))
		{
			return new DadosEstudante().getEtudentNome(idusuario);
		}
		else if(getperfilUsuario().equals("Docente"))
		{
				return new DadosDocente().getDocenteName(idusuario);
		}
		return "-";
	}

}
