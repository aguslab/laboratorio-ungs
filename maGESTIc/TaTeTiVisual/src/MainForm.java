import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class MainForm {

	private JFrame frame;
	private TaTeTi elJuego;
	private JLabel labelInfo;
	private JButton boton00;
	private JButton boton01;
	private JButton boton02;
	private JButton boton12;
	private JButton boton11;
	private JButton boton10;
	private JButton boton22;
	private JButton boton21;
	private JButton boton20; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		elJuego = new TaTeTi();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 198, 267);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		boton00 = new JButton("");
		boton00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton00, 0, 0);
			}
		});
		boton00.setBounds(10, 51, 50, 50);
		frame.getContentPane().add(boton00);
		
		boton01 = new JButton("");
		boton01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton01, 0, 1);
			}
		});
		boton01.setBounds(70, 51, 50, 50);
		frame.getContentPane().add(boton01);
		
		boton02 = new JButton("");
		boton02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton02, 0, 2);
			}
		});
		boton02.setBounds(130, 51, 50, 50);
		frame.getContentPane().add(boton02);
		
		boton12 = new JButton("");
		boton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton12, 1, 2);
			}
		});
		boton12.setBounds(130, 112, 50, 50);
		frame.getContentPane().add(boton12);
		
		boton11 = new JButton("");
		boton11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton11, 1, 1);
			}
		});
		boton11.setBounds(70, 112, 50, 50);
		frame.getContentPane().add(boton11);
		
		boton10 = new JButton("");
		boton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton10, 1, 0);
			}
		});
		boton10.setBounds(10, 112, 50, 50);
		frame.getContentPane().add(boton10);
		
		boton22 = new JButton("");
		boton22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton22, 2, 2);
			}
		});
		boton22.setBounds(130, 173, 50, 50);
		frame.getContentPane().add(boton22);
		
		boton21 = new JButton("");
		boton21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton21, 2, 1);
			}
		});
		boton21.setBounds(70, 173, 50, 50);
		frame.getContentPane().add(boton21);
		
		boton20 = new JButton("");
		boton20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apretarBoton(boton20, 2, 0);
			}
		});
		boton20.setBounds(10, 173, 50, 50);
		frame.getContentPane().add(boton20);
		
		labelInfo = new JLabel("");
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
		labelInfo.setBounds(0, 11, 190, 14);
		frame.getContentPane().add(labelInfo);
	}

	private void apretarBoton(JButton boton, int i, int j) {
		char turno = elJuego.ProximoTurno();
		boton.setText(Character.toString(turno));
		elJuego.Jugar(i, j);
		boton.setEnabled(false);
		
		if (elJuego.Terminado()) 
		{
			boton00.setEnabled(false);
			boton01.setEnabled(false);
			boton02.setEnabled(false);
			boton10.setEnabled(false);
			boton11.setEnabled(false);
			boton12.setEnabled(false);
			boton20.setEnabled(false);
			boton21.setEnabled(false);
			boton22.setEnabled(false);
			
			char ganador = elJuego.Ganador();
			if (ganador == ' ')
				labelInfo.setText("¡Empate!");
			else if (ganador == 'x')
				labelInfo.setText("Ganó la X");
			else
				labelInfo.setText("Ganó la O");
		}
		
	}
}
