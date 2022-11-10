package estudante;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import comuns.DicaTextField;

@SuppressWarnings("serial")
public class FeedBack extends JPanel implements ActionListener {
	

	private JPanel homeheaderpanel;
	private JLabel reclamacao;
	private JButton submeter;
	private JComboBox<String>reCombobox;
	JComboBox<String> satCombobox;
	private JTextField rtxt;
	
public FeedBack() {
	setBorder(new EmptyBorder(0, 0, 0, 0));
	setBackground(Color.WHITE);
	setForeground(Color.WHITE);
	this.setSize(1116, 705);
	setLayout(null);
	
	
	homeheaderpanel = new JPanel();
	homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
	homeheaderpanel.setBackground(new Color (47, 173, 102));
	homeheaderpanel.setLayout(null);
	homeheaderpanel.setBounds(10, 0, 1085, 230);
	add(homeheaderpanel);


	JLabel lblHome = new JLabel("Adicionar FeedBack");
	lblHome.setIcon(null);
	lblHome.setForeground(Color.WHITE);
	lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
	lblHome.setBounds(10, 97, 377, 40);
	homeheaderpanel.add(lblHome);
	
	 //reclamacao = new JLabel("Contribuição ");
	//reclamacao.setHorizontalAlignment(SwingConstants.LEFT);
	//reclamacao.setForeground(Color.DARK_GRAY);
	//reclamacao.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	//reclamacao.setBounds(100, 270, 300, 40);
	//reclamacao.setVisible(true);
	//add(reclamacao);
	
	reCombobox = new JComboBox<String>();
	reCombobox.setFocusable(false);
	
	reCombobox.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	reCombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Tipo de Contribuição--",
			"Reclamação","Sugestão"}));
	reCombobox.setBackground(Color.WHITE);
	reCombobox.setBounds(80, 270, 220, 40);
	add(reCombobox);
	
	rtxt = new DicaTextField("Contribuição");
	rtxt.setToolTipText("");
	rtxt.setForeground(Color.DARK_GRAY);
	rtxt.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
	rtxt.addActionListener(this);
	rtxt.setColumns(10);
	rtxt.setBounds(360, 270, 380, 50);
	add(rtxt);

	
	
	 reclamacao = new JLabel("Nível de Satisfação ");
		reclamacao.setHorizontalAlignment(SwingConstants.LEFT);
		reclamacao.setForeground(Color.DARK_GRAY);
		reclamacao.setFont(new Font("Segoe UI", Font.PLAIN, 27));
		reclamacao.setBounds(80, 370, 300, 35);
		reclamacao.setVisible(true);
		add(reclamacao);
		
		
		satCombobox = new JComboBox<String>();
		satCombobox.setFocusable(false);
		satCombobox.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		satCombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Muito Satisfeito --",
				"Satisfeito","Insatisfeito","Muito Insatisfeito"}));
		satCombobox.setBackground(Color.WHITE);
		satCombobox.setBounds(400, 370, 220, 40);
		satCombobox.setVisible(true);
		add(satCombobox);

	
	submeter = new JButton("Submeter ");
	submeter.setBorder(new EmptyBorder(0, 0, 0, 0));
	submeter.addActionListener(this);
	submeter.setForeground(new Color(255, 255, 255));
	submeter.setBackground(new Color(32, 178, 170));
	submeter.setFont(new Font("Segoe UI", Font.BOLD, 15));
	submeter.setBounds(800, 650,200, 37);
	submeter.setVisible(true);
	submeter.setFocusable(false);
	submeter.setCursor(new Cursor(Cursor.HAND_CURSOR));
	add(submeter);
	
	
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		// TODO Auto-generated method stub
		
		
	}

}
