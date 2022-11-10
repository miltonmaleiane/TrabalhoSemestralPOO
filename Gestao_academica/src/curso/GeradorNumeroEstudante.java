package curso;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/*
 
 */

@SuppressWarnings("serial")
public class GeradorNumeroEstudante extends JDialog implements ActionListener
{

	JComboBox <String>courcenamecombo;
	JScrollPane scrollPane;
	JTable table;
	JPanel panel;
	JButton btnSave;
	JTextField tf[];
	JLabel lbErro;
	PainelGeradorNumeroEstudante rp=null;
	JLabel lblSelecionarCurso,lblNomeCurso;
	JScrollPane scrollNrEst;
	private int sem;
	private String codCurso="";
	private static GeradorNumeroEstudante dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new GeradorNumeroEstudante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocation(400, 100);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param courcenamecombo 
	 */
	public GeradorNumeroEstudante() {
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		this.setLocation(400,100);
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setSize(620, 282);
		getContentPane().setLayout(null);
		
		JLabel headingLabel = new JLabel("Gerar nr roll ");
		headingLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		headingLabel.setOpaque(true);
		headingLabel.setBounds(0, 0, this.getWidth(), 44);
		headingLabel.setBackground(new Color (47, 173, 102));
		headingLabel.setForeground(new Color(255, 255, 255));
		headingLabel.setFont(new Font("Arial", Font.BOLD, 23));
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(headingLabel);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, this.getHeight()-82, 599, 53);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		 btnSave = new JButton("Salvar");
		 btnSave.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSave.setFocusable(false);
		btnSave.setBackground(new Color(12, 69, 86));
		btnSave.setForeground(Color.WHITE);
		btnSave.addActionListener(this);
		btnSave.setFont(new Font("Arial", Font.BOLD, 17));
		btnSave.setBounds(450, 11, 139, 33);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnSave);
		
		 lbErro = new JLabel("Erro! somente numeros permitidos");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 11, 396, 29);
		lblError.setVisible(false);
		panel.add(lblError);
		
		 courcenamecombo = new JComboBox<String>(new DadosCurso().getRollCourceName());
		courcenamecombo.setForeground(Color.DARK_GRAY);
		courcenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		courcenamecombo.setFocusable(false);
		courcenamecombo.addActionListener(this);
		courcenamecombo.setBackground(Color.WHITE);
		courcenamecombo.setBounds(182, 93, 407, 37);
		getContentPane().add(courcenamecombo);
		
		
		 lblSelecionarCurso = new JLabel("Selecionar Curso :");
		 lblSelecionarCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelecionarCurso.setForeground(new Color(0, 0, 0));
		lblSelecionarCurso.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSelecionarCurso.setBounds(10, 93, 162, 37);
		getContentPane().add(lblSelecionarCurso);
		
		scrollNrEst=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollNrEst);
		this.setSize(620,262);
		panel.setBounds(0, this.getHeight()-82, this.getWidth()-6, 53);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
		
			if(e.getSource()==courcenamecombo&&courcenamecombo.getSelectedIndex()>0)
			{
				if(scrollNrEst!=null)
				{
					scrollNrEst.setVisible(false);
					
				}
				
				{
					sem=new DadosCurso().getTotalsemoryear(""+courcenamecombo.getSelectedItem());
					courcecode=new DadosCurso().getCourcecode(""+courcenamecombo.getSelectedItem());
					
					rp=new PainelGeradorNumeroEstudante(courcecode,sem);
					rp.setLocation(0,0);
					rp.setVisible(true);
					scrollNrEst.setViewportView(rp);
					
					scrollNrEst.setLocation(0, 150);
					scrollNrEst.getVerticalScrollBar().setUnitIncrement(80);
					scrollNrEst.setVisible(true);
					scrollNrEst.setSize(620,sem<5?rp.getHeight()+5:4*62);
					this.setSize(620,scrollNrEst.getHeight()+262);
					panel.setBounds(0, this.getHeight()-82, this.getWidth()-6, 53);
				}
			}
			else if(e.getSource()==courcenamecombo&&courcenamecombo.getSelectedIndex()==0)
			{
				if(scrollNrEst!=null)
				{
					scrollNrEst.setSize(620,0);
					this.setSize(620,scrollNrEst.getHeight()+262);
					panel.setBounds(0, this.getHeight()-82, this.getWidth()-6, 53);
					scrollNrEst.setVisible(false);
				}
			}
		
		if(e.getSource()==btnSave&&!CheckError() && courcenamecombo.getSelectedIndex()!=0)
		{
				String courcecode=new DadosCurso().getCourcecode(""+courcenamecombo.getSelectedItem());
				this.adddatatotable(courcecode, sem);
				this.dispose();
		}
		
	}
	public boolean CheckError()
	{
		int limit=sem;
		for(int i=0; i<limit; i++)
		{
			if(rp.textField[i].getText().isEmpty())
			{
				rp.textField[i].setBorder(new LineBorder(Color.RED,1));
				lblError.setVisible(true);
				lblError.setText("Erro introduza sem ou numero"+(i+1));
				return true;
			}
			else
				
			{
				try
				{
				Long.parseLong(rp.textField[i].getText());
				}
				catch(NumberFormatException exp)
				{
					rp.textField[i].setBorder(new LineBorder(Color.RED,1));
					lblError.setVisible(true);
					lblError.setText("Deve ser um Número !");
					return true;
					
				}
				lblError.setVisible(false);
				rp.textField[i].setBorder(new LineBorder(Color.gray,1));
			}
		}
		return false;
		
	}

	public void adddatatotable(String courcecode,int limit)
	{
		for(int i=1; i<=limit; i++)
		{
			long roll=Long.parseLong(rp.textField[i-1].getText());
			new DadosNumeroEstudante().adddata(courcecode, i, roll);
			
			
			
		}
	}
}