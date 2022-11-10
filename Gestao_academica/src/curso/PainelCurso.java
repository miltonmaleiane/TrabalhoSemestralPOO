package curso;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JpainelScroll;
import javax.swing.Jtabela;
import javax.swing.border.LineBorder;

import comuns.painelScrollUtil;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;

/*

 */
@SuppressWarnings("serial")
public class PainelCurso extends JPanel implements ActionListener {
	private JpainelScroll painelScroll;
	private JButton addcurso, gerarNrEst;

	private Jtabela tabela;

	/**
	 * Create the panel.
	 */
	public PainelCurso() {

		setForeground(Color.GRAY);
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);

		painelScroll = new JpainelScroll();
		painelScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		painelScroll.setBounds(10, 200, 1096, 494);
		for (Component c : painelScroll.getComponents()) {
			c.setBackground(Color.white);
		}
		add(painelScroll);

		tabela = new Jtabela();
		tabela.setBorder(new LineBorder(new Color(192, 192, 192)));
		tabela.setForeground(Color.DARK_GRAY);
		tabela.setRowHeight(40);

		tabela.gettabelaHeader().setBackground(new Color(47, 173, 102));

		tabela.gettabelaHeader().setForeground(Color.white);
		tabela.gettabelaHeader().setFont(new Font("Arial", Font.BOLD, 20));
		tabela.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		tabela.gettabelaHeader().setPreferredSize(new Dimension(50, 40));
		tabela.setDragEnabled(false);

		tabela.setGridColor(Color.LIGHT_GRAY);
		tabela.gettabelaHeader().setReorderingAllowed(false);
		this.updatetabelaData();
		tabela.setEnabled(false);
		painelScroll.setViewportView(tabela);
		

		JPanel panel = new JPanel();
		panel.setBackground(new Color (47, 173, 102));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);

		JLabel allCourceslbl = new JLabel("Todos Cursos");
		allCourceslbl.setForeground(new Color(255, 255, 255));
		allCourceslbl.setFont(new Font("Segoe UI", Font.BOLD, 30));
		allCourceslbl.setBounds(10, 65, 321, 34);
		panel.add(allCourceslbl);

		gerarNrEst = new JButton("Gerar roll");
		gerarNrEst.setBounds(702, 135, 147, 33);
		panel.add(gerarNrEst);
		gerarNrEst.setBorder(new EmptyBorder(0, 0, 0, 0));
		gerarNrEst.setForeground(new Color(0, 139, 139));
		gerarNrEst.setFocusable(false);
		gerarNrEst.setFont(new Font("Segoe UI", Font.BOLD, 15));
		gerarNrEst.setBackground(new Color(255, 255, 255));
		gerarNrEst.setVisible(true);
		gerarNrEst.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gerarNrEst.addActionListener(this);

		addcurso = new JButton("Adicionar Curso");
		addcurso.setBounds(870, 135, 147, 33);
		panel.add(addcurso);
		addcurso.setBorder(new EmptyBorder(0, 0, 0, 0));
		addcurso.setForeground(new Color(0, 139, 139));
		addcurso.setBackground(new Color(255, 255, 255));
		addcurso.setFocusable(false);
		addcurso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addcurso.addActionListener(this);
		addcurso.setFont(new Font("Segoe UI", Font.BOLD, 15));
		

	}

	public void updatetabelaData() {
		
		DadosCurso c = new DadosCurso();
		ResultSet st = c.getCourceinfo();
		tabela.setModel(DbUtils.resultSetTotabelaModel(st));
		tabela.getColumnModel().getColumn(0).setMaxWidth(200);
		tabela.getColumnModel().getColumn(1).setMaxWidth(250);
		tabela.getColumnModel().getColumn(2).setMaxWidth(400);
		tabela.getColumnModel().getColumn(3).setMaxWidth(200);
		tabela.getColumnModel().getColumn(4).setMaxWidth(250);
		tabela.getColumnModel().getColumn(5).setMaxWidth(250);
		tabela.setAutoResizeMode(Jtabela.AUTO_RESIZE_LAST_COLUMN);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == gerarNrEst) {
			GeradorNumeroEstudante r = new GeradorNumeroEstudante();
			r.setLocation(400, 100);
			r.setVisible(true);

		}
		if (e.getSource() == addcurso) {
			AdicionarCurso ac = new AdicionarCurso(this);
			ac.setLocationRelativeTo(null);
			ac.setVisible(true);
			painelScrollUtil.scrollToBottom(painelScroll);
		}

	}
}
