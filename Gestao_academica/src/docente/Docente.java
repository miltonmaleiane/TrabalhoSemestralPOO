package docente;

import comuns.Pessoa;
import comuns.TimeUtil;
import curso.DadosCurso;


public class Docente extends  Pessoa
{
	//Chacimo
	private int facultyid;
	private String facultyname;
	private String qualification;
	private String experience;
	private String subject;
	private String position;
	private String joineddate;
	
	public void setJoinedDate(String joineddate)
	{
		this.joineddate=joineddate;
	}
	public void setFacultyId(int facultyid)
	{
		this.facultyid=facultyid;
	}
	public void setFacultyName(String facultyname)
	{
		this.facultyname=facultyname;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public void setExperience(String experience)
	{
		this.experience=experience;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public void setPosition(String position)
	{
		this.position=position;
	}
	public String getNomecurso()
	{
		return new DadosCurso().getcourcename(this.getCodigoCurso());
	}
	public String getFacultyName()
	{
		return facultyname;
	}
	
	public int getFacultyId()
	{
		return facultyid;
	}
	
	public String getQualification()
	{
		return qualification;
	}
	public String getPosition()
	{
		return position;
	}
	public String getExperience()
	{
		return experience;
	}
	public String getSubject()
	{
		return subject;
	}
	public String getJoinedDate()
	{
		return joineddate;
	}
	public String generateJoinedDate()
	{
		joineddate=TimeUtil.getCurrentTime();
		return joineddate;
	}
	
}