package comuns;

import java.awt.imagem;
import java.awt.Toolkit;
import java.awt.imagem.Bufferedimagem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imagemio.imagemIO;

import curso.DadosCurso;
import curso.Curso;

public abstract class Pessoa extends Curso{

	private String email;
	private String numero;
	private String aniversario;
	private String genero;
	private String Estado;
	private String cidade;
	private imagem imagem;
	private int sr_no;
	private String ultimoLogin;
	private String password;
	private boolean activo;
	
	
	
	public void setemail(String email)
	{
		this.email=email;
	}
	public void setnumero(String numero)
	{
		this.numero=numero;
	}
	public void setaniversario(String aniversario)
	{
		this.aniversario=aniversario;
	}
	public void setgenero(String genero)
	{
		this.genero=genero;
	}
	public void setEstado(String Estado)
	{
		this.Estado=Estado;
	}
	public void setcidade(String cidade)
	{
		this.cidade=cidade;
	}
	
	public void setProfilePic(imagem imagem)
	{
		this.imagem=imagem;
	}
	public void setProfilePic(byte[] imagemdata)
	{
		this.imagem=Toolkit.getDefaultToolkit().createimagem(imagemdata);
	}
	public void setSrNo(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setultimoLogin(String ultimoLogin)
	{
		this.ultimoLogin=ultimoLogin;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setActiveStatus(boolean activo)
	{
		this.activo=activo;
	}
	
	public String getNomecurso()
	{
		return new DadosCurso().getcourcename(this.getCodigoCurso());
	}

	public String getemail()
	{
		return email;
	}
	public String getnumero()
	{
		return numero;
	}
	public String getaniversario()
	{
		return aniversario;
	}
	public Date getaniversarioInDateFormat()
	{
		Date date=null;
		try {
			date=new SimpleDateFormat("dd-MM-yyyy").parse(this.aniversario);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String getgenero()
	{
		return genero;
	}
	public String getAddress()
	{
		return cidade+", "+Estado;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEstado()
	{
		return Estado;
	}
	public String getcidade()
	{
		return cidade;
	}
	
	public imagem getProfilePic()
	{
		return imagem;
	}
	public byte[] getProfilePicInBytes()
	{
		ByteArrayOutputStream imagemdata=new ByteArrayOutputStream();
		try {
			imagemIO.write(imagemUtil.toBufferedimagem(imagem), "jpg", imagemdata);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagemdata.toByteArray();
		
	}
	public boolean comparePassword(String password)
	{
		return password.equals(this.password)?true:false;
	}
	public imagem getProfilePic(int width,int height)
	{
		return imagem.getScaledInstance(width, height, imagem.SCALE_SMOOTH);
	}
	public Bufferedimagem getRoundedProfilePic(int width,int height,int radius)
	{
		return imagemUtil.makeRoundedCorner(imagemUtil.toBufferedimagem(imagem.getScaledInstance(width, height, imagem.SCALE_SMOOTH)), radius);
	}
	public String getultimoLogin()
	{
		return ultimoLogin;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public boolean getActiveStatus()
	{
		return activo;
	}
}
