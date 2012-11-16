package Vista;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;

import java.util.*;
import java.text.*;



@SuppressWarnings("serial")
public class AdmIN extends JFrame implements ActionListener, ItemListener
{
	private static JDesktopPane escritorio = new JDesktopPane ();
	@SuppressWarnings("unused")
	private JInternalFrame Duenio, Inmueble, Locatario, Alquiler;
	private JMenuBar barra;
	
	private JMenu 
		mnuAdministracion, 
		mnuAyuda, 
		mnuSalir;
	
	private	JMenuItem 
		inmuebles,
		alquileres,
		locadores,
		locatarios;
	
	private	JMenuItem 
		ayudaContenido, 
		ayudaAtajos, 
		acercaDe;
	
	private	JMenuItem 
		cerrarAplicacion;
	
	private JPanel barraDeEstado = new JPanel ();
	private JLabel fechaPie;
	private JLabel propiedadIntelectual;
	private java.util.Date fechaActual= new java.util.Date ();
	private SimpleDateFormat fechaFormateada = new SimpleDateFormat ("dd MMMM yyyy", Locale.getDefault());
	private String fecha = fechaFormateada.format (fechaActual);
	

	public AdmIN() 
	{
		super ("AdmIN v1.0 :: Administrador para Inmobiliaria \"El Roble\"");
		escritorio.setBackground(new Color(119, 136, 153));
		UIManager.addPropertyChangeListener (new UISwitchListener ((JComponent)getRootPane()));
		barra = new JMenuBar ();
		setIconImage (getToolkit().getImage ("Imagenes/icono.png"));
		setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setJMenuBar (barra);
		
		for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels())
		{
	        if("Windows".equals(laf.getName()))
	            try 
	        	{
	            	UIManager.setLookAndFeel(laf.getClassName());
	        	} 
	        	catch (Exception ex) 
	        	{
	        		
	        	}
	    }
		addWindowListener 
		(
				new WindowAdapter () 
				{
					public void windowClosing (WindowEvent we) 
					{
						salirDelPrograma();
					}
				}
		);

		setLocation
		(
				(Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2
		);
		
		mnuAdministracion = new JMenu ("Administración  ");
		mnuAdministracion.setMnemonic ((int)'A');
		
		mnuAyuda = new JMenu ("Ayuda  ");
		mnuAyuda.setMnemonic ((int)'Y');
		
		mnuSalir = new JMenu ("Salir");
		mnuSalir.setMnemonic ((int)'Q');
		mnuSalir.addActionListener(this);

		inmuebles = new JMenuItem ("Inmuebles", new ImageIcon ("Imagenes/inmuebles.png"));
		inmuebles.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		inmuebles.setMnemonic ((int)'I');
		inmuebles.addActionListener (this);
		
		alquileres = new JMenuItem ("Alquileres", new ImageIcon ("Imagenes/inmuebles.png"));
		alquileres.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		alquileres.setMnemonic ((int)'A');
		alquileres.addActionListener (this);
		
		ayudaContenido = new JMenuItem ("Contenido de la Ayuda  ", new ImageIcon ("Imagenes/ayuda1.png"));
		ayudaContenido.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
		ayudaContenido.setMnemonic ((int)'Y');
		ayudaContenido.addActionListener (this);
		
		ayudaAtajos = new JMenuItem ("Atajos...  ", new ImageIcon ("Imagenes/ayuda3.png"));
		ayudaAtajos.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_J, Event.CTRL_MASK));
		ayudaAtajos.setMnemonic ((int)'J');
		ayudaAtajos.addActionListener (this);
		
		acercaDe = new JMenuItem ("Acerca de AdmIN...  "	, new ImageIcon ("Imagenes/ayuda2.png"));
		acercaDe.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		acercaDe.setMnemonic ((int)'R');
		acercaDe.addActionListener (this);
		
		cerrarAplicacion = new JMenuItem ("Salir del sistema  ", new ImageIcon ("Imagenes/cerrar.png"));
		cerrarAplicacion.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		cerrarAplicacion.setMnemonic ((int)'S');	
		cerrarAplicacion.addActionListener (this);
		
		locadores = new JMenuItem("Locadores", new ImageIcon ("Imagenes/inmuebles.png"));
		locadores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		locadores.addActionListener (this);
		
		locatarios = new JMenuItem("Locatarios", new ImageIcon ("Imagenes/inmuebles.png"));
		locatarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		locatarios.addActionListener (this);
		
		mnuAdministracion.add (inmuebles);
		mnuAdministracion.addSeparator ();
		mnuAdministracion.add (alquileres);
		mnuAdministracion.addSeparator ();
		mnuAdministracion.add(locadores);
		mnuAdministracion.addSeparator ();
		mnuAdministracion.add(locatarios);
		
		mnuAyuda.add (ayudaAtajos);
		mnuAyuda.addSeparator ();
		mnuAyuda.add (acercaDe);
		
		mnuSalir.add (cerrarAplicacion);
		
		barra.add (mnuAdministracion);
		barra.add (mnuAyuda);
		barra.add (mnuSalir);

		

		
		
		propiedadIntelectual = new JLabel (" " + "©2012 - De Napoli, Godoy, Jiménez", Label.RIGHT);
		propiedadIntelectual.setForeground (Color.black);
		propiedadIntelectual.setToolTipText ("Todos los derechos reservados");
		
		fechaPie = new JLabel ("" + fecha + " ", JLabel.LEFT);
		fechaPie.setForeground (Color.black);
		fechaPie.setToolTipText ("Fecha actual del sistema");
		
		BorderLayout bl_barraDeEstado = new BorderLayout();
		bl_barraDeEstado.setVgap(25);
		barraDeEstado.setLayout (bl_barraDeEstado);
		barraDeEstado.add (propiedadIntelectual, BorderLayout.EAST);
		barraDeEstado.add (fechaPie, BorderLayout.WEST);

		escritorio.putClientProperty ("JDesktopPane.dragMode", "outline");

		getContentPane().add (escritorio, BorderLayout.CENTER);
		getContentPane().add (barraDeEstado, BorderLayout.SOUTH);

		setVisible (true);
	}

	public void actionPerformed (ActionEvent ae) 
	{
		Object obj = ae.getSource();
		if (obj == inmuebles) 
		{
			boolean b = openChildWindow ("Nuevo Inmueble");
			if (b == false) 
			{
				Inmueble IM = new Inmueble ();
				escritorio.add (IM);
				IM.show ();
				try {
					IM.setMaximum(false);
				} catch (PropertyVetoException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if (obj == alquileres) 
		{

				boolean b = openChildWindow ("Nuevo Alquiler");
				if (b == false) 
				{
					Alquiler AQ = new Alquiler();
					escritorio.add (AQ);
					AQ.show ();
				}

		}
		else if (obj == cerrarAplicacion) 
		{
			salirDelPrograma();
		}
		else if (obj == locatarios) 
		{
			{
				boolean b = openChildWindow ("Locatarios");
				if (b == false) 
				{
					Locatario LO = new Locatario();
					escritorio.add (LO);
					LO.show ();
				}
				
			}
		}
		else if (obj == locadores) 
		{

				boolean b = openChildWindow ("Locadores");
				if (b == false) 
				{
					Duenio LR = new Duenio();
					escritorio.add (LR);
					LR.show ();
					
				}

		}
		else if (obj==locatarios) 
		{		
			boolean b = openChildWindow ("Locatarios");
			if (b == false) 
			{
				Locatario LO = new Locatario();
				escritorio.add (LO);
				LO.show ();
			}
		}
		
		
		else if (obj == ayudaContenido ) 
		{
			
		}
		else if (obj == ayudaAtajos) 
		{
			
		}
		else if (obj == acercaDe) 
		{
			String msg = "Creado y diseñado por:\n" + 
					"©2012 - De Napoli, Godoy, Jiménez y asociados.";
				JOptionPane.showMessageDialog (this, msg, "Acerca de...", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void salirDelPrograma() 
	{
		try 
		{

		    int reply = JOptionPane.showConfirmDialog 
		    (
		    	this,
		    	"¿Está seguro que desea cerrar\nesta aplicación?",
		    	"Salir", 
		    	JOptionPane.YES_NO_OPTION, 
		    	JOptionPane.WARNING_MESSAGE
		    );

			if (reply == JOptionPane.YES_OPTION) 
			{
				setVisible (false);
				dispose();
				System.exit (0);
			}
			else if (reply == JOptionPane.NO_OPTION) 
			{
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		} 
		catch (Exception e) 
		{
			
		}
	}
	
	public static JDesktopPane getEscritorio()
	{
		return escritorio;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	
	static boolean openChildWindow (String title) 
	{

		JInternalFrame[] childs = getEscritorio().getAllFrames ();
		for (int i = 0; i < childs.length; i++) 
		{
			if (childs[i].getTitle().equalsIgnoreCase (title)) 
			{
				childs[i].show ();
				return true;
			}
		}
		return false;

	}
	
	
	
}
