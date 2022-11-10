package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.AdminDados;
import admin.AdminPrincipal;
import comuns.DicaPassword;
import comuns.DicaTextField;
import comuns.DadosUsuario;
import docente.Docente;
import docente.DadosDocente;
import docente.DocenteMain;
import estudante.Estudante;
import estudante.DadosEstudante;
import estudante.EstudanteMain;

@SuppressWarnings("serial")
public class PainelLogin extends JPanel implements ActionListener
{

	public DicaTextField fieldUsuarioId;
	public JPasswordField passwordfield;
	public JButton loginbutton;
	String perfirLogin;
	private PaginaLogin loginpageframe;

	/**
	 * 
	 */
	public PainelLogin(String perfirLogin,ImageIcon imageicon,PaginaLogin lpf) {
		
		this.perfirLogin=perfirLogin;
		this.loginpageframe=lpf;
		setBorder(new LineBorder(new Color(47, 173, 102)));
		setBackground(new Color(47, 173, 102));
		setBounds(490, 206, 420, 434);
		setLayout(null);
		
		/**JLabel lblPassword = new JLabel("");
		lblPassword.setOpaque(false);
		lblPassword.setBackground(new Color(12, 69, 86));
		lblPassword.setIcon(new ImageIcon(".\\assets\\password1.png"));
		lblPassword.setBounds(24, 272, 35, 35);
		add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		*/
		fieldUsuarioId = new DicaTextField("");
		
		//fieldUsuarioId.setBorder(new EmptyBorder(0,0,0,0));
		fieldUsuarioId.setBorder(null);
		fieldUsuarioId.setOpaque(false);
		fieldUsuarioId.requestDefaultFocus();
		fieldUsuarioId.setToolTipText("Prencha usando o Id ");
		fieldUsuarioId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		fieldUsuarioId.setBounds(80, 196, 323, 44);
		fieldUsuarioId.setForeground(Color.DARK_GRAY);
		add(fieldUsuarioId);
		fieldUsuarioId.setColumns(10);
		JLabel usuario= new JLabel("Usuário");
		usuario.setForeground(new Color(12, 69, 86));
		usuario.setBounds(80,183,70,20);
		usuario.setFont(new Font("Times New Roman",Font.BOLD,16));
		add(usuario);
		  
		JLabel underlinelabel2 = new JLabel("");
		underlinelabel2.setBorder(new MatteBorder(2, 0, 0, 0, (Color)new Color(12, 69, 86)));
		underlinelabel2.setBounds(80, 230, 280, 4);
		add(underlinelabel2);
		
		
		
		/**JLabel lblEmail = new JLabel("");
		 lblEmail.setOpaque(true);
		 lblEmail.setFocusable(true);
		 lblEmail.setBackground(new Color(12, 69, 86));
		 lblEmail.setIcon(new ImageIcon(".\\assets\\userid.png"));
		 lblEmail.setBounds(20, 196, 30, 40);
		add(lblEmail);
		 lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		 lblEmail.setBorder(new LineBorder(new Color(192, 192, 192)));
		 lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		*/
		loginbutton = new JButton("Login");
		
		loginbutton.setToolTipText("Clique aqui para aceder ao sistema");
		loginbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		loginbutton.setForeground(new Color(255, 255, 255));
		loginbutton.addActionListener(this);
		loginbutton.setBackground(new Color(12, 69, 86));
		loginbutton.setBounds(160, 370, 120, 36);
		loginbutton.setFocusable(false);
		loginbutton.setBorderPainted(false);
		add(loginbutton);
		
		JLabel lblLoginestudante = new JLabel("Login "+perfirLogin);
		lblLoginestudante.setForeground(new Color(12, 69, 86));
		lblLoginestudante.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblLoginestudante.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginestudante.setBounds(10, 100, 390, 34);
		add(lblLoginestudante);
		
		JLabel labelPerfilusuario = new JLabel();
		labelPerfilusuario.setIcon(imageicon);
		labelPerfilusuario.setBounds(169, 14, 100, 94);
		add(labelPerfilusuario);
		
		passwordfield = new DicaPassword("");
		passwordfield.setOpaque(false);
		passwordfield.setBorder(fieldUsuarioId.getBorder());
		passwordfield.setToolTipText("Introduza a sua senha");
		passwordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordfield.setBounds(80, 272, 261, 44);
		add(passwordfield);
		
		JLabel senha= new JLabel("Senha");
		senha.setForeground(new Color(12, 69, 86));
		senha.setBounds(80,255,70,20);
		senha.setFont(new Font("Times New Roman",Font.BOLD,17));
		add(senha);
		
		
		JLabel underlinelabel3 = new JLabel("");
		underlinelabel3.setBorder(new MatteBorder(2, 0, 0, 0, (Color)new Color(12, 69, 86)));
		underlinelabel3.setBounds(80, 305, 280, 4);
		add(underlinelabel3);
		
		JLabel recsenha= new JLabel("Esqueceu  senha ?");
		recsenha.setToolTipText("Clique aqui para recuperar a senha");
		recsenha.setForeground(Color.white);
		recsenha.setBounds(260,307,100,20);
		recsenha.setFont(new Font("Times New Roman",Font.ITALIC,12));
		add(recsenha);
		
		
		JButton btnmostrar_Ocultar = new JButton("Mostrar");
		btnmostrar_Ocultar.setForeground(new Color(255, 255, 255));
		btnmostrar_Ocultar.setBounds(361, 277, 45, 30);
		btnmostrar_Ocultar.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnmostrar_Ocultar.setToolTipText("Mostrar/ocultar senha");
		btnmostrar_Ocultar.setFocusable(false);
		btnmostrar_Ocultar.setFocusPainted(false);
		btnmostrar_Ocultar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnmostrar_Ocultar.setBackground(new Color(12, 69, 86));
		btnmostrar_Ocultar.setOpaque(false);
		btnmostrar_Ocultar.setBorderPainted(false);
		btnmostrar_Ocultar.addActionListener(e->
		    {
		    	if(btnmostrar_Ocultar.getText().equals("Mostrar"))
		    	{
		    		passwordfield.setEchoChar('\u0000');
		    		btnmostrar_Ocultar.setText("Ocultar");
		    	}
		    	else
		    	{
		    		passwordfield.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
		    		btnmostrar_Ocultar.setText("Mostrar");
		    	}
		    });
		add(btnmostrar_Ocultar);

	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		if(perfirLogin.equals("Admin"))
		{
			boolean result=new AdminDados().checkPassword(fieldUsuarioId.getText(), passwordfield.getText());
			if(result==true)
			{
				
				AdminPrincipal am=new AdminPrincipal();
				
				am.setVisible(true);
				am.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				System.out.println("Timer running "+loginpageframe.timer.isRunning());

				loginpageframe.dispose();
				
				
				
			}
			
		}
		else if(perfirLogin.equals("Docente"))
		{
			boolean result=new DadosDocente().checkPassword(fieldUsuarioId.getText(), passwordfield.getText());
			if(result==true)
			{
				Docente f=new DadosDocente().getFacultyInfobyUserId(fieldUsuarioId.getText());
				if(!f.getCodigoCurso().equals("Not Assigned"))
				{
				
					new DadosUsuario().addFacultyLoginTime(f);
					DocenteMain fm=new DocenteMain(f);
					fm.setVisible(true);
					fm.setLocationRelativeTo(null);
					loginpageframe.timer.stop();
					loginpageframe.imagetimer.stop();
					loginpageframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Conta não activada.","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(perfirLogin.equals("Estudante"))
		{
			boolean result=new DadosEstudante().checkPassword(fieldUsuarioId.getText(), passwordfield.getText());
			if(result==true)
			{
				Estudante s=new DadosEstudante().getStudentDetailsByUserId(fieldUsuarioId.getText());
				new DadosUsuario().addStudentLoginTime(s);
				EstudanteMain sm=new EstudanteMain(s);
				sm.setVisible(true);
				sm.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				loginpageframe.dispose();
				
			}
		}
	}
}
