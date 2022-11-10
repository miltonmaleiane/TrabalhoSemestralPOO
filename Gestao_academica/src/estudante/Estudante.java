package estudante;

import comuns.Pessoa;
import comuns.TimeUtil;
import curso.DadosCurso;

public class Estudante extends Pessoa{
	
		private long nrestudante;
		private String disciplinaopcional;
		private String primeironome;
		private String ultimonome;
		private String nomepai;
		private String ocupacaopai;
		private String nomemae;
		private String ocupacaomae;
		private String idusuario;
		private String dataadmissao; 
		
		
		public void setDataadmissao(String dataadmissao)
		{
			this.dataadmissao=dataadmissao;
		}
		public void setNrestudante(long nrestudante )
		{
			this.nrestudante=nrestudante;
		}
		public void setDisciplinaopcional(String disciplinaopcional)
		{
			this.disciplinaopcional=disciplinaopcional;
		}
		public void setPrimeiroNome(String primeironome)
		{
			this.primeironome=primeironome;
		}
		public void setUltimonome(String ultimonome)
		{
			this.ultimonome=ultimonome;
		}
		public void setNomepai(String nomepai)
		{
			this.nomepai=nomepai;
		}
		public void setOcupacaopai(String ocupacaopai)
		{
			this.ocupacaopai=ocupacaopai;
		}
		public void setNomemae(String nomemae)
		{
			this.nomemae=nomemae;
		}
		public void setOcupacaomae(String ocupacaomae)
		{
			this.ocupacaomae=ocupacaomae;
		}
		public void setIdusuario(String idusuario)
		{
			this.idusuario=idusuario;
		}
		public String getNomecurso()
		{
			return new DadosCurso().getcourcename(this.getCodigoCurso());
		}
		public String getNomecompleto()
		{
			return primeironome+" "+ultimonome;
		}
		public String getPrimeiroNome()
		{
			return primeironome;
		}
		public String getUltimonome()
		{
			return ultimonome;
		}
		public long getNrestudante()
		{
			return nrestudante;
		}
		public String getDisciplinaopcional()
		{
			return disciplinaopcional;
		}
		public String getDataadmissao()
		{
			return dataadmissao;
			
		}
		public String gerarDataadmissao()
		{
			dataadmissao=TimeUtil.getCurrentTime();
			return dataadmissao;
		}
		public String getNomepai()
		{
			return nomepai;
		}
		public String getNomemae()
		{
			return nomemae;
		}
		public String getOcupacaopai()
		{
			return ocupacaopai;
		}
		public String getOcupacaomae()
		{
			return ocupacaomae;
		}
		public String getIdusuario()
		{
			return idusuario;
		}
		public String generateUserId() 
		{
			// TODO Auto-generated method stub
			idusuario=getCodigoCurso()+"-"+getSemouano()+"-"+nrestudante;
			return idusuario;
		}
		
}
