package estudante;

import disciplina.Disciplina;



public class Notas extends Disciplina
{
	public String situacao;
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int sr_no;
	public String nomeestudante;
	private long nrestudante;
	int teste1=0;
	int teste2=0;
	double media = 0;
	private int aux;
	public int getAux() {
		return aux;
	}
	public void setAux(int aux) {
		this.aux = aux;
	}
	public void setSrNo(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setTeste1(int teste1)
	{
		this.teste1=teste1;
	}

	public void setTeste2(int teste2)
	{
		this.teste2=teste2;
	}
	public void setNomeestudante(String nomeestudante)
	{
		this.nomeestudante=nomeestudante;
	}
	public void setNrestudante(long nrestudante)
	{
		this.nrestudante=nrestudante;
	}
	public String getStudentName()
	{
		return nomeestudante;
	}
	public long getNrestudante()
	{
		return nrestudante;
	}
	public int getTeste1()
	{
		return  teste1;
	}
	public int getTeste2()
	{
		return teste2;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media ;
	}
	
}
