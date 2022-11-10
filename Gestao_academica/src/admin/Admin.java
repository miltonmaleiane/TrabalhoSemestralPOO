package admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import comuns.ImageUtil;


/* OCTOBER
=======
/*
 * 
>>>>>>> branch 'master' of https://github.com/miltonmaleiane/TraalhoPOO.git

 */

public class Admin
{
	private String website;
	private String numero;
	private String emailid;
	private String nomeEscola;
	private String password;
	private Image logoimage;
	private String facebook;
	private String instagram;
	private String twitter;
	private String lastlogin;
	private String linkedin;
	private String endereco;
	private boolean isactive=false;
	public void setWebsite(String website)
	{
		this.website=website;
	}
	
	public void setNumero(String numero)
	{
		this.numero=numero;
	}
	public void setEmailId(String emailid)
	{
		this.emailid=emailid;
	}
	public void setNomeEscola(String nomeEscola)
	{
		this.nomeEscola=nomeEscola;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setProfilePic(byte[] imagedata)
	{
		this.logoimage=Toolkit.getDefaultToolkit().createImage(imagedata);
	}
	public void setProfilePic(Image profilepic)
	{
		this.logoimage=profilepic;
	}
	public void setFaceBookLink(String facebooklink)
	{
		this.facebook=facebooklink;
	}
	public void setInstagramLink(String instagramlink)
	{
		this.instagram=instagramlink;
	}
	public void setLinkedinLink(String linkedinlink)
	{
		this.linkedin=linkedinlink;
	}
	public void setTwitterLink(String twitterlink)
	{
		this.twitter=twitterlink;
	}
	public void setLastLogin(String lastlogin)
	{
		this.lastlogin=lastlogin;
	}
	public void setActiveStatus(boolean isactive)
	{
		this.isactive=isactive;
	}
	public void setEndereco(String endereco)
	{
		this.endereco=endereco;
	}
	public String getWebsite()
	{
		return website;
	}
	public String getNumero()
	{
		return numero;
	}
	public String getEmailId()
	{
		return emailid;
	}
	public String getNomeEscola()
	{
		return nomeEscola;
	}
	public String getPassword()
	{
		return password;
	}
	public boolean getActiveStatus()
	{
		return isactive;
	}
	public String getFacebookLink()
	{
		return facebook;
	}
	public String getInstagramLink()
	{
		return instagram;
	}
	public String getTwitterLink()
	{
		return twitter;
	}
	public String getLinkedinLink()
	{
		return linkedin;
	}
	public Image getProfilePic()
	{
		return logoimage;
	}
	public byte[] getProfilePicInBytes()
	{
		ByteArrayOutputStream imagedata=new ByteArrayOutputStream();
		try {
			ImageIO.write(ImageUtil.toBufferedImage(logoimage), "jpg", imagedata);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagedata.toByteArray();
		
	}
	
	public Image getProfilePic(int width,int height)
	{
		return logoimage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	public BufferedImage getRoundedProfilePic(int width,int height,int radius)
	{
		return ImageUtil.makeRoundedCorner(ImageUtil.toBufferedImage(logoimage.getScaledInstance(width, height, Image.SCALE_SMOOTH)), radius);
	}
	public String getLastLogin()
	{
		return lastlogin;
	}
	public String getEndereco()
	{
		return endereco;
	}

	
}
