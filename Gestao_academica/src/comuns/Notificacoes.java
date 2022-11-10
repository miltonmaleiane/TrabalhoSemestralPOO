package comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import curso.Curso;

public class Notificacoes extends Curso
{
	
	private String tempo;
	private String title;
	private String messagem;
	private String lido;
	private int sr_no;
	private String userid;
	private String perfilUser;
	
	
	public void setperfilUser(String perfilUser)
	{
		this.perfilUser=perfilUser;
	}
	public void setSrNo(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setUserId(String userid)
	{
		this.userid=userid;
	}
	public void settempo(String tempo)
	{
		this.tempo=tempo;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public void setmessagem(String messagem)
	{
		this.messagem=messagem;
	}
	public void setlido(String lido)
	{
		this.lido=lido;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public String getUserId()
	{
		return userid;
	}
	public String getTitle()
	{
		return title;
	}
	public	String getmessagem()
	{
		return messagem;
	}
	public String getlido()
	{
		return lido;
	}
	public String getNotificationDate()
	{
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date=null;
			try {
				date = formater.parse(tempo);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return dateformatter.format(date);
		
	}
	public String gettempo()
	{
		return tempo;
	}
	public String getNotificationtempo()
	{
		SimpleDateFormat tempoformatter=new SimpleDateFormat("hh:mm aaa");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date=null;
			try {
				date = formater.parse(tempo);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return tempoformatter.format(date);
	}
	public String getperfilUser()
	{
		return perfilUser;
	}
}
