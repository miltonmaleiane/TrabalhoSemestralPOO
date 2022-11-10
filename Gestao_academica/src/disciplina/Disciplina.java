package disciplina;

import curso.Curso;

public class Disciplina extends Curso
{

	public String nomeDisciplina;
	private String codDisciplina;
	public int maxTeste1=0;
	public int maxTeste2=0;
	private String tipoDisc;
	
	
	public void settipoDisc(String tipoDisc)
	{
		this.tipoDisc=tipoDisc;
	}
	public void setmaxTeste1(int maxTeste1)
	{
		this.maxTeste1=maxTeste1;
	}
	public void setmaxTeste2(int maxTeste2)
	{
		this.maxTeste2=maxTeste2;
	}
	public void setnomeDisciplina(String subject)
	{
		this.nomeDisciplina=subject;
	}
	public void setcodDisciplina(String codDisciplina)
	{
		this.codDisciplina=codDisciplina;
	}
	public void setSemouano(int semouano)
	{
		super.setSemouano(semouano);
	}
	public String getcodDisciplina()
	{
		return codDisciplina;
	}
	public String getnomeDisciplina()
	{
		return nomeDisciplina;
	}
	public int getmaxTeste1()
	{
		return maxTeste1;
	}
	public int getmaxTeste2()
	{
		return maxTeste2;
	}
	public String gettipoDisc()
	{
		return tipoDisc;
	}
}
