package curso;

public class Curso {

	private String codigoCurso;
	private int semouano;
	private boolean estadeclarado;
	private String nomeCurso;
	public void setNomeCurso(String nomeCurso)
	{
		this.nomeCurso=nomeCurso;
	}
	public void setCodigoCurso(String codigoCurso)
	{
		this.codigoCurso=codigoCurso;
	}
	public void setSemouano(int semouano)
	{
		this.semouano=semouano;
	}
	public void setestadeclarado(boolean estadeclarado)
	{
		this.estadeclarado=estadeclarado;
	}
	public String getNomeCurso()
	{
		return nomeCurso!=null?nomeCurso:new DadosCurso().getcourcename(codigoCurso);
	}
	public String getCodigoCurso()
	{
		return codigoCurso;
	}
	public int getSemouano()
	{
		return semouano;
	}
	public boolean getestadeclarado()
	{
		return estadeclarado;
	}
}
