package comuns;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import admin.Admin;
import curso.DadosCurso;
import disciplina.DadosDisciplina;
import docente.Docente;
import docente.DadosDocente;
import estudante.Estudante;
import estudante.DadosEstudante;

/*

 */

@SuppressWarnings("serial")
public class PainelPrincipal extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private JPanel painelCab;
	private JLabel totalEstudantes, lbTotalDoc, lbTotalCursp, totallectureslabel;
	public JLabel lbUltimoLogin;
	private JLabel lbdiffTempo;
	private JLabel lbBemVindo;
	private JLabel lbNotif;
	private JPanel pnlnotifciacoes;
	private JPanel painelCursos;
	private JPanel painelDocentes;
	private JPanel painelEstudantes;
	int pos[]= {20,294,569,837};
	private JPanel painelDisciplina;
	/**
	 * Create the panel.
	 */
	private PainelPrincipal() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		
		
				

		pnlnotifciacoes = new JPanel();
		pnlnotifciacoes.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		pnlnotifciacoes.setBounds(20, 244, 253, 247);
		add(pnlnotifciacoes);
		pnlnotifciacoes.setBackground(new Color(255, 255, 255));
		pnlnotifciacoes.setLayout(null);
		pnlnotifciacoes.setVisible(false);

		lbNotif = new JLabel("0");
		lbNotif.setForeground(new Color(128, 128, 128));
		lbNotif.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbNotif.setHorizontalAlignment(SwingConstants.CENTER);
		lbNotif.setBounds(10, 174, 233, 35);
		pnlnotifciacoes.add(lbNotif);

		JLabel lblNotification = new JLabel("Notificações");
		lblNotification.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNotification.setForeground(new Color(128, 128, 128));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setHorizontalTextPosition(JLabel.CENTER);
		lblNotification.setVerticalTextPosition(JLabel.BOTTOM);
		lblNotification.setBounds(10, 31, 233, 142);
		pnlnotifciacoes.add(lblNotification);
		lblNotification.setIcon(new ImageIcon(".//assets//notificationhomepage.png"));
		
		painelCursos = new JPanel();
		painelCursos.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		painelCursos.setBounds(20, 244, 253, 247);
		add(painelCursos);
		painelCursos.setBackground(new Color(255, 255, 255));
		painelCursos.setLayout(null);
		
		lbTotalCursp = new JLabel("0");
		lbTotalCursp.setForeground(new Color(128, 128, 128));
		lbTotalCursp.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbTotalCursp.setHorizontalAlignment(SwingConstants.CENTER);
		lbTotalCursp.setBounds(10, 174, 233, 35);
		painelCursos.add(lbTotalCursp);
		
		JLabel lblCources = new JLabel("Cursos");
		lblCources.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblCources.setForeground(new Color(128, 128, 128));
		lblCources.setHorizontalAlignment(SwingConstants.CENTER);
		lblCources.setHorizontalTextPosition(JLabel.CENTER);
		lblCources.setVerticalTextPosition(JLabel.BOTTOM);
		lblCources.setBounds(10, 31, 233, 142);
		painelCursos.add(lblCources);
		lblCources.setIcon(new ImageIcon(".//assets//courceshomepage.png"));

		painelEstudantes = new JPanel();
		painelEstudantes.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		painelEstudantes.setLayout(null);
		painelEstudantes.setBackground(Color.WHITE);
		painelEstudantes.setBounds(294, 244, 253, 247);
		add(painelEstudantes);

		totalEstudantes = new JLabel("0");
		totalEstudantes.setText(new DadosEstudante().getTotalStudents() + "");
		totalEstudantes.setHorizontalAlignment(SwingConstants.CENTER);
		totalEstudantes.setForeground(Color.GRAY);

		totalEstudantes.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalEstudantes.setBounds(10, 174, 233, 35);
		painelEstudantes.add(totalEstudantes);

		JLabel lblStudents = new JLabel("Estudantes");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setForeground(Color.GRAY);
		lblStudents.setIcon(null);
		lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudents.setBounds(10, 32, 233, 144);
		lblStudents.setHorizontalTextPosition(JLabel.CENTER);
		lblStudents.setVerticalTextPosition(JLabel.BOTTOM);
		painelEstudantes.add(lblStudents);
		lblStudents.setIcon(new ImageIcon(".//assets//studenthomepage.png"));
		
		painelDocentes = new JPanel();
		painelDocentes.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		painelDocentes.setLayout(null);
		painelDocentes.setBackground(Color.WHITE);
		painelDocentes.setBounds(565, 244, 253, 247);
		add(painelDocentes);

		lbTotalDoc = new JLabel("0");
		lbTotalDoc.setBackground(Color.WHITE);
		lbTotalDoc.setHorizontalAlignment(SwingConstants.CENTER);
		lbTotalDoc.setForeground(Color.GRAY);
		lbTotalDoc.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbTotalDoc.setBounds(10, 174, 233, 35);
		painelDocentes.add(lbTotalDoc);

		JLabel lblFaculities = new JLabel("Docentes");
		lblFaculities.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaculities.setForeground(Color.GRAY);
		lblFaculities.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblFaculities.setBounds(10, 34, 233, 140);
		lblFaculities.setHorizontalTextPosition(JLabel.CENTER);
		lblFaculities.setVerticalTextPosition(JLabel.BOTTOM);
		painelDocentes.add(lblFaculities);
		lblFaculities.setIcon(new ImageIcon(".//assets//facultyhomepage.png"));
		
		painelDisciplina = new JPanel();
		painelDisciplina.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		painelDisciplina.setLayout(null);
		painelDisciplina.setBackground(Color.WHITE);
		painelDisciplina.setBounds(831, 244, 253, 247);
		add(painelDisciplina);

		totallectureslabel = new JLabel("0");

		totallectureslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totallectureslabel.setForeground(Color.GRAY);
		totallectureslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totallectureslabel.setBounds(10, 174, 233, 35);
		painelDisciplina.add(totallectureslabel);

		JLabel lblLectures = new JLabel("Disciplinas");
		lblLectures.setHorizontalAlignment(SwingConstants.CENTER);
		lblLectures.setForeground(Color.GRAY);
		lblLectures.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblLectures.setBounds(10, 40, 225, 141);
		lblLectures.setIconTextGap(10);
		lblLectures.setHorizontalTextPosition(JLabel.CENTER);
		lblLectures.setVerticalTextPosition(JLabel.BOTTOM);
		painelDisciplina.add(lblLectures);
		try {
			Image image=ImageIO.read(new File(".//assets//subjects2.png"));
			lblLectures.setIcon(new ImageIcon(image.getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();         
		}

		painelCab = new JPanel();
		painelCab.setBorder(new EmptyBorder(0, 0, 0, 0));
		painelCab.setBackground(new Color (47, 173, 102));
		painelCab.setLayout(null);
		painelCab.setBounds(10, 0, 1096, 279);
		add(painelCab);

		lbBemVindo = new JLabel("Bem-Vindo");
		lbBemVindo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lbBemVindo.setForeground(Color.WHITE);
		lbBemVindo.setBounds(10, 11, 1076, 45);
		painelCab.add(lbBemVindo);

		JLabel lblHome = new JLabel("Estatísticas");
		lblHome.setIcon(null);
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblHome.setBounds(10, 97, 377, 45);
		painelCab.add(lblHome);

		lbUltimoLogin = new JLabel("Último Acesso : Primeiro Acesso");
		lbUltimoLogin.setBackground(Color.WHITE);
		lbUltimoLogin.setForeground(Color.WHITE);
		lbUltimoLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbUltimoLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUltimoLogin.setBounds(20, 47, 900, 30);
		painelCab.add(lbUltimoLogin);

		lbdiffTempo = new JLabel("");
		lbdiffTempo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbdiffTempo.setForeground(Color.WHITE);
		lbdiffTempo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbdiffTempo.setBackground(Color.WHITE);
		lbdiffTempo.setBounds(599, 75, 486, 19);
		painelCab.add(lbdiffTempo);

	}

	public PainelPrincipal(Admin a) {
		this();
		lbTotalDoc.setText(new DadosDocente().getTotalFaculaty() + "");
		totalEstudantes.setText(new DadosEstudante().getTotalStudents() + "");
		lbTotalCursp.setText(new DadosCurso().getTotalCource() + "");
		lbBemVindo.setText("Bem vindo Adminstrador");
		totallectureslabel.setText(new DadosDisciplina().getTotalSubject() + "");
	}

	public PainelPrincipal(Docente f) {
		this();
		lbTotalDoc.setText(new DadosDocente().getFaculaty(f.getCodigoCurso(), f.getSemouano()) + "");
		totalEstudantes.setText(new DadosEstudante().getTotalStudentInCource(f.getCodigoCurso(), f.getSemouano()) + "");
		lbNotif.setText(""+new NotificacaoDados().getUnreadNotification(f.getFacultyId()+"", "Faculty", f.getCodigoCurso(), f.getSemouano(),f.getJoinedDate()));
		painelCursos.setVisible(false);
		pnlnotifciacoes.setVisible(true);
		lbBemVindo.setText("Bem vindo " + f.getFacultyName());
		totallectureslabel.setText(new DadosDisciplina().getTotalSubjectinCource(f.getCodigoCurso(), f.getSemouano()) + "");

		painelEstudantes.setLocation(pos[0], painelEstudantes.getY());
		painelDocentes.setLocation(pos[1], painelDocentes.getY());
		painelDisciplina.setLocation(pos[2], painelDisciplina.getY());
		pnlnotifciacoes.setLocation(pos[3], pnlnotifciacoes.getY());
		
	}

	public PainelPrincipal(Estudante s) {
		this();
		lbTotalDoc.setText(new DadosDocente().getFaculaty(s.getCodigoCurso(), s.getSemouano()) + "");
		totalEstudantes.setText(new DadosEstudante().getTotalStudentInCource(s.getCodigoCurso(), s.getSemouano()) + "");
		
		lbNotif.setText(""+new NotificacaoDados().getUnreadNotification(s.getIdusuario()+"", "Student", s.getCodigoCurso(), s.getSemouano(),s.getDataadmissao()));
		painelCursos.setVisible(false);
		pnlnotifciacoes.setVisible(true);
		lbBemVindo.setText("Bem vindo " +s.getNomecompleto());
		totallectureslabel.setText(new DadosDisciplina().getTotalSubjectinCource(s.getCodigoCurso(), s.getSemouano()) + "");
		painelEstudantes.setLocation(pos[0], painelEstudantes.getY());
		painelDocentes.setLocation(pos[1], painelDocentes.getY());
		painelDisciplina.setLocation(pos[2], painelDisciplina.getY());
		pnlnotifciacoes.setLocation(pos[3], pnlnotifciacoes.getY());
	}

	public void setultimologin(String ultimologin) {
		if (ultimologin == null || ultimologin.isEmpty()) {
			this.lbUltimoLogin.setText("ultimo login");
		} else {
			this.lbUltimoLogin.setText("ultimo login : " + ultimologin);
//		this.lbdiffTempo.setText(TimeUtil.getDateDifference(ultimologin));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
