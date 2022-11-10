package curso;

import javax.swing.Jpainel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import javax.swing.JTextField;

/*

 */

@SuppressWarnings("serial")
public class PainelGeradorNumeroEstudante extends Jpainel {

	/**
	 * 
	 * 
	 */
	Jpainel painel[];
	JLabel codCursoLabel[];
	JLabel semouanolb[];
	JTextField textField[];
	String codCurso;
	int sem=8;

	int start=1;
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(605,this.getHeight());
	}
	
	public PainelGeradorNumeroEstudante(String strcodCurso,int intsem) {
		setBackground(Color.WHITE);
		this.codCurso=strcodCurso;
		this.sem=intsem;
		this.setSize(605, 71);
		setLayout(null);
		painel=new Jpainel[sem];
		codCursoLabel=new JLabel[sem];
		semouanolb=new JLabel[sem];
		textField=new JTextField[sem];
		
		JLabel lblcodCurso = new JLabel("Id do Curso");
		lblcodCurso.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblcodCurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblcodCurso.setOpaque(true);
		lblcodCurso.setForeground(new Color(255, 255, 255));
		lblcodCurso.setBackground(new Color(32, 178, 170));
		lblcodCurso.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblcodCurso.setBounds(10, 10, 166, 50);
		add(lblcodCurso);
		
		JLabel lblSem = new JLabel("Sem/Ano");
		lblSem.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblSem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSem.setOpaque(true);
		lblSem.setForeground(Color.WHITE);
		lblSem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSem.setBackground(new Color(32, 178, 170));
		lblSem.setBounds(176, 10, 158, 50);
		add(lblSem);
		
		JLabel lblRollNumber = new JLabel("Master Roll Number");
		lblRollNumber.setOpaque(true);
		lblRollNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRollNumber.setForeground(Color.WHITE);
		lblRollNumber.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRollNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblRollNumber.setBackground(new Color(32, 178, 170));
		lblRollNumber.setBounds(333, 10, 262, 50);
		add(lblRollNumber);
		int y=60;
		for(int i=0; i<intsem; i++)
		{
				this.setSize(this.getWidth(),this.getHeight()+50);
				painel[i]= new Jpainel();
				painel[i].setBackground(Color.WHITE);
				painel[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				painel[i].setBounds(10, y, 585, 50);
				add(painel[i]);
				painel[i].setLayout(null);
				
				codCursoLabel[i] = new JLabel(codCurso);
				codCursoLabel[i].setOpaque(true);
				codCursoLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				codCursoLabel[i].setForeground(Color.DARK_GRAY);
				codCursoLabel[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				codCursoLabel[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				codCursoLabel[i].setBackground(new Color(255, 255, 255));
				codCursoLabel[i].setBounds(0, 0, 166, 50);
				painel[i].add(codCursoLabel[i]);
				
				semouanolb[i] = new JLabel(start+"");
				semouanolb[i].setOpaque(true);
				semouanolb[i].setHorizontalAlignment(SwingConstants.CENTER);
				semouanolb[i].setForeground(Color.DARK_GRAY);
				semouanolb[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				semouanolb[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				semouanolb[i].setBackground(Color.WHITE);
				semouanolb[i].setBounds(166, 0, 158, 50);
				painel[i].add(semouanolb[i]);
				
				textField[i] = new JTextField();
				textField[i].setBounds(333, 5, 242, 40);
				textField[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				painel[i].add(textField[i]);
				textField[i].setColumns(10);
				y+=50;
				start++;
				if(i<intsem-1)
				{
					changeFocusable(i);
				}
		}

	}
	public void changeFocusable(int n)
	{
		textField[n].addKeyListener(new KeyAdapter()
		{
			
			
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				textField[n].setFocusable(false);
				textField[n+1].setFocusable(true);
				}
				
			}
		});
		textField[n].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField[n].setFocusable(true);
			}
		});
	}

}
