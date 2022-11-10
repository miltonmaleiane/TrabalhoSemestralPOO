package curso;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import comuns.DicaTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


/*

 */

@SuppressWarnings("serial")
public class AdicionarCurso extends JDialog implements ActionListener
{

	private JTextField cpcodcurso;
	private JTextField cpNomecurso;
	private JTextField totalsemouanofield;
	private JComboBox<String> semouanocb;
	private JLabel lblError;
	private PainelCurso cursopainel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarCurso dialog = new AdicionarCurso();
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarCurso(PainelCurso cursopainel)
	{
		this();
		this.cursopainel=cursopainel;
	}
	public AdicionarCurso() {
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 476, 452);
		getContentPane().setLayout(null);
		
		JLabel lblAddNewCource = new JLabel("Cadastro de Curso");
		lblAddNewCource.setForeground(new Color(255, 255, 255));
		lblAddNewCource.setBackground(new Color (47, 173, 102));
		lblAddNewCource.setOpaque(true);
		lblAddNewCource.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewCource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewCource.setBounds(0, 0, 473, 55);
		getContentPane().add(lblAddNewCource);
		
		JLabel lblCourceCode = new JLabel("Id Curso ");
		lblCourceCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCourceCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCourceCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourceCode.setBounds(10, 79, 130, 24);
		lblCourceCode.setFocusable(true);
		getContentPane().add(lblCourceCode);
		
		JLabel lblCourceName = new JLabel("Nome do Curso  ");
		lblCourceName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourceName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCourceName.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCourceName.setBounds(10, 147, 139, 24);
		getContentPane().add(lblCourceName);
		
		JLabel lblSemyear = new JLabel("Sem/Ano");
		lblSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblSemyear.setBounds(10, 218, 139, 24);
		getContentPane().add(lblSemyear);
		
		cpcodcurso = new DicaTextField("");
		cpcodcurso.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		cpcodcurso.setBounds(157, 72, 292, 40);
		getContentPane().add(cpcodcurso);
		cpcodcurso.setColumns(10);
		
		cpNomecurso = new DicaTextField("");
		cpNomecurso.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		cpNomecurso.setColumns(10);
		cpNomecurso.setBounds(159, 141, 292, 40);
		getContentPane().add(cpNomecurso);
		
		totalsemouanofield = new DicaTextField("");
		totalsemouanofield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		totalsemouanofield.setColumns(10);
		totalsemouanofield.setBounds(157, 278, 292, 40);
		getContentPane().add(totalsemouanofield);
		
		semouanocb = new JComboBox<String>();
		semouanocb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		semouanocb.setModel(new DefaultComboBoxModel<String>(new String[] {"--Selecionar Sem/Ano--", "sem", "Ano"}));
		semouanocb.setBounds(159, 210, 292, 40);
		getContentPane().add(semouanocb);
		
		JLabel lblTotalSemyear = new JLabel("Total Sem/Ano");
		lblTotalSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblTotalSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblTotalSemyear.setBounds(10, 284, 139, 24);
		getContentPane().add(lblTotalSemyear);
		
		JButton addcourcebutton = new JButton("Cadastrar");
		addcourcebutton.setToolTipText("Clique aqui para submeter os dados do curso");
		addcourcebutton.setBackground(new Color(12, 69, 86));
		addcourcebutton.setForeground(new Color(255, 255, 255));
		addcourcebutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		addcourcebutton.setBounds(310, 373, 139, 37);
		addcourcebutton.addActionListener(this);
		getContentPane().add(addcourcebutton);
		
		lblError=new JLabel("Preenchimento obrigatorio!");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(157,115,355,21);
		getContentPane().add(lblError);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("Preenchimento obrigatorio !");
		String courcename=cpNomecurso.getText();
		String courcecode=cpcodcurso.getText();
		String semoryear=(String) semouanocb.getSelectedItem();
		String strtotalsemoryear=totalsemouanofield.getText();
	 	if(courcecode.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(cpcodcurso.getX(), cpcodcurso.getY()+cpcodcurso.getHeight(), lblError.getWidth(), 21);
			cpcodcurso.setFocusable(true);
		}
	
		else if(courcename.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(cpNomecurso.getX(), cpNomecurso.getY()+cpNomecurso.getHeight(), lblError.getWidth(), 21);
			cpNomecurso.setFocusable(true);
		}
		else if(semouanocb.getSelectedIndex()==0)
		{
			lblError.setVisible(true);
			lblError.setBounds(semouanocb.getX(), semouanocb.getY()+semouanocb.getHeight(),  lblError.getWidth(), 21);
			
		}
		else if(strtotalsemoryear.isEmpty())
		{
			
			lblError.setVisible(true);
			lblError.setBounds(totalsemouanofield.getX(), totalsemouanofield.getY()+totalsemouanofield.getHeight(),  lblError.getWidth(), 21);
			totalsemouanofield.setFocusable(true);
		}
		
		else
		{
			 
				try
				{
					int totalsemoryear=Integer.parseInt(strtotalsemoryear);
					if(new DadosCurso().isCourceCodeExist(courcecode.toUpperCase()))
					{
						lblError.setVisible(true);
						lblError.setBounds(cpcodcurso.getX(), cpcodcurso.getY()+cpcodcurso.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("O Cod do curso ja existe! !");
					}
					else if(new DadosCurso().isCourceNameExist(courcename))
					{
						lblError.setVisible(true);
						lblError.setBounds(cpNomecurso.getX(), cpNomecurso.getY()+cpNomecurso.getHeight(), lblError.getWidth(), 21);
						cpNomecurso.setFocusable(true);
						lblError.setText("O nome do curso ja existe! !");
					}
					else if(totalsemoryear<1)
					{
						lblError.setVisible(true);
						lblError.setBounds(totalsemouanofield.getX(), totalsemouanofield.getY()+totalsemouanofield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Deve ter no minimo 1 Sem/Ano !");
					}
					else
					{
						DadosCurso c=new DadosCurso();
						int result=c.addCource(courcecode, courcename, semoryear, totalsemoryear);
						if(result>0)
						{
							
							if(cursopainel!=null)
							{
							cursopainel.updatetableData();
							}
							this.dispose();
						}
						
					}
				}
				catch(NumberFormatException nexp)
				{
					lblError.setVisible(true);
					lblError.setBounds(totalsemouanofield.getX(), totalsemouanofield.getY()+totalsemouanofield.getHeight(), lblError.getWidth(), 21);
					lblError.setText("Caracteres não são validos !");
				}
				
		
		}
		
	}
	
}
