package admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*

 */

@SuppressWarnings("serial")
public class AdminPainelPerfil extends JPanel {

	private JPanel panel;
	private Admin a;
	private JLabel cabecalho;

	/**
	 * Create the panel.
	 */
	
	
	public AdminPainelPerfil(AdminPrincipal am)
	{
		this();
		cabecalho.setText("Perfil do Admin");
		JButton editardadosBtn = new JButton("Editar Informação");
		editardadosBtn.setFocusPainted(false);
		editardadosBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		editardadosBtn.setForeground(new Color(0, 139, 139));
		editardadosBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editardadosBtn.setBackground(Color.WHITE);
		editardadosBtn.setBounds(855, 139, 160, 33);
		editardadosBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editardadosBtn.addActionListener(e->
		{
				EditarAdminDetalhes caad=new EditarAdminDetalhes(a,am);
				caad.setLocationRelativeTo(null);
				caad.setVisible(true);
		});
		panel.add(editardadosBtn);
		
		
		JButton btneditarlinks = new JButton("Editar links");
		btneditarlinks.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btneditarlinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditarAdminLinks ed=new EditarAdminLinks(a,am);
				ed.setLocationRelativeTo(null);
				ed.setVisible(true);
			}
		});
		btneditarlinks.setForeground(new Color(0, 139, 139));
		btneditarlinks.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btneditarlinks.setFocusPainted(false);
		btneditarlinks.setBorder(new EmptyBorder(0, 0, 0, 0));
		btneditarlinks.setBackground(Color.WHITE);
		btneditarlinks.setBounds(690, 139, 161, 33);
		panel.add(btneditarlinks);
	}
	public AdminPainelPerfil() {
		
		a=new AdminDados().getAdminData();
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(47, 173, 102));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
	
		 cabecalho= new JLabel("Sobre Nós");
		 cabecalho.setIcon(null);
		 cabecalho.setBounds(10, 65, 272, 44);
		panel.add(cabecalho);
		cabecalho.setBackground(new Color (47, 173, 102));
		cabecalho.setHorizontalAlignment(SwingConstants.LEFT);
		cabecalho.setForeground(Color.WHITE);
		cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 30));
		cabecalho.setOpaque(true);
		
		
		
		JLabel instLogolabel = new JLabel();
		instLogolabel .setHorizontalAlignment(SwingConstants.CENTER);
		instLogolabel .setHorizontalTextPosition(SwingConstants.CENTER);
		instLogolabel .setIcon(new ImageIcon(a.getProfilePic(200, 180)));
		instLogolabel .setBorder(new LineBorder(new Color(0, 0, 0)));
		instLogolabel .setBounds(24, 213, 200, 180);
		add(instLogolabel );
		
		JLabel lblNomeInst = new JLabel("Instituição  :  ");
		lblNomeInst.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeInst.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNomeInst.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblNomeInst.setBounds(233, 213, 191, 48);
		add(lblNomeInst);
		
		JLabel labelNome = new JLabel("  "+a.getNomeEscola());
		labelNome.setToolTipText(a.getNomeEscola());
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		labelNome.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		labelNome.setBorder(new LineBorder(Color.LIGHT_GRAY));
		labelNome.setBounds(423, 213, 672, 48);
		add(labelNome);
		
		JLabel lblEmail = new JLabel("Email   :  ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmail.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblEmail.setBounds(233, 260, 191, 48);
		add(lblEmail);
		
		JLabel emailLabel = new JLabel("  "+a.getEmailId());
		emailLabel.setToolTipText(a.getEmailId());
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		emailLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		emailLabel.setBounds(423, 260, 672, 48);
		add(emailLabel);
		
		JLabel lblContacto = new JLabel("Contacto  :  ");
		lblContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContacto.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblContacto.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblContacto.setBounds(233, 307, 191, 48);
		add(lblContacto);
		
		JLabel labelContacto = new JLabel("  "+a.getNumero());
		labelContacto.setToolTipText(a.getNumero());
		labelContacto.setHorizontalAlignment(SwingConstants.LEFT);
		labelContacto.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		labelContacto.setBorder(new LineBorder(Color.LIGHT_GRAY));
		labelContacto.setBounds(423, 307, 672, 48);
		add(labelContacto);
		
		JLabel lblWebsite = new JLabel("Website  :  ");
		lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWebsite.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblWebsite.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblWebsite.setBounds(233, 354, 191, 48);
		add(lblWebsite);
		
		JLabel websitelabel = new JLabel("<html>&nbsp <u>"+a.getWebsite()+"</u></html>");
		websitelabel.setToolTipText(a.getWebsite());
		websitelabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		websitelabel.setForeground(new Color(65, 105, 225));
		websitelabel.setHorizontalAlignment(SwingConstants.LEFT);
		websitelabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		websitelabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		websitelabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				websitelabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				abrirLink(a.getWebsite());
				websitelabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		websitelabel.setBounds(423, 354, 672, 48);
		add(websitelabel);
		
		JLabel lblEndereco = new JLabel("Endereço  :  ");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEndereco.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblEndereco.setBounds(23, 401, 211, 48);
		add(lblEndereco);
		
		JLabel enderecolabel = new JLabel("  "+a.getEndereco());
		enderecolabel.setToolTipText(a.getEndereco());
		enderecolabel.setHorizontalAlignment(SwingConstants.LEFT);
		enderecolabel.setForeground(Color.BLACK);
		enderecolabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		enderecolabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		enderecolabel.setBounds(233, 401, 862, 48);
		add(enderecolabel);
		
		JLabel lblFaceBook = new JLabel("FaceBook  :  ");
		
		lblFaceBook.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFaceBook.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFaceBook.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFaceBook.setBounds(24, 460, 211, 48);
		add(lblFaceBook);
		
		JLabel facebooklabel = new JLabel("<html>&nbsp <u>"+a.getFacebookLink()+"</u></html>");
		facebooklabel.setToolTipText(a.getFacebookLink());
		facebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		facebooklabel.setHorizontalAlignment(SwingConstants.LEFT);
		facebooklabel.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						facebooklabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						abrirLink(a.getFacebookLink());
						facebooklabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}	
				}
				);
		
		facebooklabel.setForeground(new Color(65, 105, 225));
		facebooklabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		facebooklabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		facebooklabel.setBounds(234, 460, 862, 48);
		add(facebooklabel);
		
		JLabel lblTwitter = new JLabel("Instagram  :  ");
		lblTwitter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTwitter.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTwitter.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblTwitter.setBounds(24, 507, 211, 48);
		add(lblTwitter);
		
		JLabel instagramlabel = new JLabel("<html>&nbsp <u>"+a.getInstagramLink()+"</u></html>");
		instagramlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		instagramlabel.setHorizontalAlignment(SwingConstants.LEFT);
		instagramlabel.setForeground(new Color(65, 105, 225));
		instagramlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		instagramlabel.setToolTipText(a.getInstagramLink());
		instagramlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		instagramlabel.setBounds(234, 507, 862, 48);
		instagramlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				instagramlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				abrirLink(a.getInstagramLink());
				instagramlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(instagramlabel);
		
		JLabel label_1 = new JLabel("Twitter  :  ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		label_1.setBounds(24, 554, 211, 48);
		add(label_1);
		
		JLabel twitterlabel = new JLabel("<html>&nbsp <u>"+a.getTwitterLink()+"</u></html>");
		twitterlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		twitterlabel.setHorizontalAlignment(SwingConstants.LEFT);
		twitterlabel.setForeground(new Color(65, 105, 225));
		twitterlabel.setToolTipText(a.getTwitterLink());
		twitterlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		twitterlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		twitterlabel.setBounds(234, 554, 862, 48);
		twitterlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				twitterlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				abrirLink(a.getTwitterLink());
				twitterlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(twitterlabel);
		
		JLabel lblLinkedin = new JLabel("LinkedIn  :  ");
		lblLinkedin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinkedin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLinkedin.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblLinkedin.setBounds(24, 601, 211, 48);
		add(lblLinkedin);
		
		JLabel linkedinlabel = new JLabel("<html>&nbsp <u>"+a.getLinkedinLink()+"</u></html>");
		linkedinlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkedinlabel.setHorizontalAlignment(SwingConstants.LEFT);
		linkedinlabel.setForeground(new Color(65, 105, 225));
		linkedinlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		linkedinlabel.setToolTipText(a.getLinkedinLink());
		linkedinlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		linkedinlabel.setBounds(234, 601, 862, 48);
		linkedinlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				linkedinlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				abrirLink(a.getLinkedinLink());
				linkedinlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(linkedinlabel);
	}
	
	public void abrirLink(String link)
	{
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		Desktop desktop=Desktop.getDesktop();
		try {
			desktop.browse(URI.create(link));
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
	}
}
