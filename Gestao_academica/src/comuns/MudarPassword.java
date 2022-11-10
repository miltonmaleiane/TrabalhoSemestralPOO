package comuns;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import docente.Docente;
import docente.DadosDocente;
import estudante.Estudante;
import estudante.DadosEstudante;

import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MudarPassword extends JDialog {

	private final JPanel painelConteudo = new JPanel();
	private JPasswordField campoPassAntiga;
	private JPasswordField campoNovPass;
	private JPasswordField campoNovPass2;
	private JLabel lblCab;
	private JButton btMudPass;
	private JLabel lblErro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MudarPassword dialog = new MudarPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public MudarPassword(Estudante s)
	{
		this();
		btMudPass.addActionListener(e->
		{
			System.out.println(s.getPassword());
			if(campoPassAntiga.getText().isEmpty())
			{
				showerror(campoPassAntiga);
			}
			else if(!s.comparePassword(campoPassAntiga.getText()))
			{
				showerror(campoPassAntiga);
				lblErro.setText("A senha antiga não corresponde!.");
			}
			else if(campoNovPass.getText().isEmpty())
			{
				showerror(campoNovPass);
			}
			else if(campoNovPass2.getText().isEmpty())
			{
				showerror(campoNovPass2);
			}
			else if(!campoNovPass.getText().equals(campoNovPass2.getText()))
			{
				showerror(campoNovPass2);
				lblErro.setText("A Senha não corresponde!");
			}
			else 
			{
				int result=new DadosEstudante().changePassword(s.getIdusuario(), campoNovPass.getText());
				if(result>0)
				{
				JOptionPane.showMessageDialog(null, "Senha Actualizada","Informação",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}
				
			}
		});
		
	}
	@SuppressWarnings("deprecation")
	public MudarPassword(Docente f)
	{
		this();
		btMudPass.addActionListener(e->
		{
			if(campoPassAntiga.getText().isEmpty())
			{
				showerror(campoPassAntiga);
			}
			else if(!f.comparePassword(campoPassAntiga.getText()))
			{
				showerror(campoPassAntiga);
				lblErro.setText("A senha antiga não corresponde!");
			}
			else if(campoNovPass.getText().isEmpty())
			{
				showerror(campoNovPass);
			}
			else if(campoNovPass2.getText().isEmpty())
			{
				showerror(campoNovPass2);
			}
			else if(!campoNovPass.getText().equals(campoNovPass2.getText()))
			{
				showerror(campoNovPass2);
				lblErro.setText("A senha não corresponde!");
			}
			else 
			{
				int result=new DadosDocente().changePassword(f.getFacultyId()+"", campoNovPass.getText());
				if(result>0)
				{
				JOptionPane.showMessageDialog(null, "Senha Actualizada","Informação",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}
				
			}
		});
		
	}
	public MudarPassword() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 545, 354);
		getContentPane().setLayout(null);
		
		lblCab = new JLabel("Mudar Senha");
		lblCab.setBackground(new Color (47, 173, 102));
		lblCab.setOpaque(true);
		lblCab.setFocusable(true);
		lblCab.setForeground(Color.WHITE);
		lblCab.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCab.setHorizontalAlignment(SwingConstants.CENTER);
		lblCab.setBounds(0, 0, 545, 51);
		getContentPane().add(lblCab);
		
		JLabel label1 = new JLabel("Senha Antiga");
		label1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label1.setBounds(10, 76, 173, 33);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Nova senha");
		label2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label2.setBounds(10, 139, 173, 33);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Confirme a nova senha");
		label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label3.setBounds(10, 194, 173, 40);
		getContentPane().add(label3);
		
		campoPassAntiga = new JPasswordField();
		campoPassAntiga.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoPassAntiga.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		campoPassAntiga.setBounds(193, 75, 220, 38);
		getContentPane().add(campoPassAntiga);
		
		campoNovPass = new JPasswordField();
		campoNovPass.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoNovPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		campoNovPass.setBounds(193, 135, 220, 38);
		getContentPane().add(campoNovPass);
		
		campoNovPass2 = new JPasswordField();
		campoNovPass2.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoNovPass2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		campoNovPass2.setBounds(193, 197, 220, 38);
		getContentPane().add(campoNovPass2);
		
		
		
		btMudPass = new JButton("Mudar");
		btMudPass.setFocusable(false);
		btMudPass.setBorder(new EmptyBorder(0, 0, 0, 0));
		btMudPass.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btMudPass.setForeground(new Color(255, 255, 255));
		btMudPass.setBackground(new Color(12, 69, 86));
		btMudPass.setBounds(377, 281, 137, 33);
		getContentPane().add(btMudPass);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		lblNewLabel_1.setBounds(0, 256, 523, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton oldshowbutton = new JButton("Mostrar");
		oldshowbutton.setOpaque(false);
		oldshowbutton.setForeground(new Color(255, 255, 255));
		oldshowbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		oldshowbutton.setBackground(new Color(12, 69, 86));
		oldshowbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		oldshowbutton.setBounds(445, 74, 70, 40);
		oldshowbutton.setFocusable(false);
		oldshowbutton.addActionListener(e->
				{
					if(oldshowbutton.getText().equals("Mostrar"))
					{
						campoPassAntiga.setEchoChar('\u0000');
						oldshowbutton.setText("Ocultar");
					}
					else if(oldshowbutton.getText().equals("Ocultar"))
					{
						campoPassAntiga.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
						oldshowbutton.setText("Mostrar");
					}
				}
				);
		getContentPane().add(oldshowbutton);
		
		JButton newshowbutton = new JButton("Mostar");
		newshowbutton.setForeground(Color.WHITE);
		newshowbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		newshowbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		newshowbutton.setBackground(new Color(12, 69, 86));
		newshowbutton.setBounds(445, 135, 70, 40);
		newshowbutton.setFocusable(false);
		newshowbutton.addActionListener(e->
					{
						if(newshowbutton.getText().equals("Mostar"))
						{
							campoNovPass.setEchoChar('\u0000');
							newshowbutton.setText("Ocultar");
						}
						else if(newshowbutton.getText().equals("Ocultar"))
						{
							campoNovPass.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
							newshowbutton.setText("Mostrar");
						}
					}
				);
		getContentPane().add(newshowbutton);
		
		JButton new2showbutton = new JButton("Mostrar");
		new2showbutton.setForeground(Color.WHITE);
		new2showbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		new2showbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		new2showbutton.setBackground(new Color(12, 69, 86));
		new2showbutton.setBounds(445, 197, 70, 40);
		new2showbutton.setFocusable(false);
		new2showbutton.addActionListener(e->
		{
			if(new2showbutton.getText().equals("Mostar"))
			{
				campoNovPass2.setEchoChar('\u0000');
				new2showbutton.setText("Ocultar");
			}
			else if(new2showbutton.getText().equals("Ocultar"))
			{
				campoNovPass2.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				newshowbutton.setText("Mostar");
			}
		});
		getContentPane().add(new2showbutton);
		painelConteudo.setLayout(new FlowLayout());
		painelConteudo.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblErro=new JLabel("Preenchimento obrigatorio!");
		lblErro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setFont(new Font("Candara", Font.PLAIN, 15));
		lblErro.setVisible(false);
		lblErro.setBounds(211,134,355,21);
		getContentPane().add(lblErro);
	}
	public void showerror(JComponent tf)
	{
		lblErro.setVisible(true);
		lblErro.setText("Preenchimento obrigatorio !");
		lblErro.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
}
