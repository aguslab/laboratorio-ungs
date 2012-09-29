package vista_Controlador;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import java.util.*;
import java.text.*;

@SuppressWarnings("serial")
public class Magesti 
extends JFrame 
implements 
	ActionListener, 
	ItemListener, 
	Config 
{
	private JDesktopPane escritorio = new JDesktopPane ();
	
	private JMenuBar barra;
	
	private JMenu 
		mnuOrdenDeTrabajo, 
		mnuSolicitudDeCompra, 
		mnuRecepcionDePedido, 
		mnuAdministracion, 
		mnuCalendario, 
		mnuAyuda, 
		mnuSalir;
	
	private JMenuItem 
		ingresarOrdenDeTrabajo, 
		consultarOrdenDeTrabajo;
	
	private	JMenuItem 
		ingresarSolicitudDeCompra, 
		consultarSolicitudDeCompra; 
	
	private	JMenuItem 
		registrarRecepcionDePedido;
	
	private	JMenuItem 
		clientes;
	private	JMenuItem 
		mostrarCalendario;
	
	private	JMenuItem 
		ayudaContenido, 
		ayudaAtajos, 
		acercaDe;
	
	private	JMenuItem 
		cerrarAplicacion;
	
	private	JToolBar barraHerramientas;
	
	private	JButton 
		btnNuevaOrdenDeTrabajo, 
		btnBuscarOrdenDeTrabajo, 
		btnNuevaSolicitudDeCompra, 
		btnBuscarSolicitudDeCompra, 
		btnNuevoRegistroDePedido, 
		btnCalendario, 
		btnAdministracion,
		btnAyuda, 
		btnSalir;

	private JPanel barraDeEstado = new JPanel ();

	private JLabel fechaPie;
	private JLabel propiedadIntelectual;

	private java.util.Date fechaActual= new java.util.Date ();
	
	private SimpleDateFormat fechaFormateada = new SimpleDateFormat ("dd MMMM yyyy", Locale.getDefault());
	
	private String fecha = fechaFormateada.format (fechaActual);

	public Magesti() 
	{
		
		super (qTITULO +"  -  Mantenimiento y Gesti�n de Imprentas");
		escritorio.setBackground(new Color(83, 130, 161));
		UIManager.addPropertyChangeListener (new UISwitchListener ((JComponent)getRootPane()));
		barra = new JMenuBar ();
		setIconImage (getToolkit().getImage ("Imagenes/icono.png"));
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setJMenuBar (barra);

		for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels())
		{
	        if("Nimbus".equals(laf.getName()))
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

		mnuOrdenDeTrabajo = new JMenu ("Orden de Trabajo  ");
		mnuOrdenDeTrabajo.setMnemonic ((int)'O');
		
		mnuSolicitudDeCompra = new JMenu ("Solicitud de Compra  ");
		mnuSolicitudDeCompra.setMnemonic ((int)'S');
		
		mnuRecepcionDePedido = new JMenu ("Recepci�n de Pedido  ");
		mnuRecepcionDePedido.setMnemonic ((int)'R');
		
		mnuAdministracion = new JMenu ("Administraci�n  ");
		mnuAdministracion.setMnemonic ((int)'A');
		
		mnuCalendario = new JMenu ("Calendario  ");
		mnuCalendario.setMnemonic ((int)'C');
		
		mnuAyuda = new JMenu ("Ayuda  ");
		mnuAyuda.setMnemonic ((int)'Y');
		
		mnuSalir = new JMenu ("Quitar  ");
		mnuSalir.setMnemonic ((int)'Q');
		mnuSalir.addActionListener(this);

		ingresarOrdenDeTrabajo = new JMenuItem ("Ingresar  ", new ImageIcon ("Imagenes/ingresar.png"));
		ingresarOrdenDeTrabajo.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		ingresarOrdenDeTrabajo.setMnemonic ((int)'I');
		ingresarOrdenDeTrabajo.addActionListener (this);
		
		consultarOrdenDeTrabajo = new JMenuItem ("Consultar  ", new ImageIcon ("Imagenes/buscar.png"));
		consultarOrdenDeTrabajo.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		consultarOrdenDeTrabajo.setMnemonic ((int)'C');
		consultarOrdenDeTrabajo.addActionListener (this);
		
		ingresarSolicitudDeCompra = new JMenuItem ("Ingresar  ", new ImageIcon ("Imagenes/ingresar.png"));
		ingresarSolicitudDeCompra.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		ingresarSolicitudDeCompra.setMnemonic ((int)'N');
		ingresarSolicitudDeCompra.addActionListener (this);
		
		consultarSolicitudDeCompra = new JMenuItem ("Consultar  ", new ImageIcon ("Imagenes/buscar.png"));
		consultarSolicitudDeCompra.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		consultarSolicitudDeCompra.setMnemonic ((int)'O');	
		consultarSolicitudDeCompra.addActionListener (this);
		
		registrarRecepcionDePedido = new JMenuItem ("Registrar  ", new ImageIcon ("Imagenes/registrar.png"));
		registrarRecepcionDePedido.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		registrarRecepcionDePedido.setMnemonic ((int)'R');	
		registrarRecepcionDePedido.addActionListener (this);

		clientes = new JMenuItem ("Registro de Clientes  ", new ImageIcon ("Imagenes/clientes.png"));
		clientes.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		clientes.setMnemonic ((int)'G');
		clientes.addActionListener (this);
		
		mostrarCalendario = new JMenuItem ("Ver  ", new ImageIcon ("Imagenes/calendario2.png"));
		mostrarCalendario.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
		mostrarCalendario.setMnemonic ((int)'V');
		mostrarCalendario.addActionListener (this);

		ayudaContenido = new JMenuItem ("Contenido de la Ayuda  ", new ImageIcon ("Imagenes/ayuda1.png"));
		ayudaContenido.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
		ayudaContenido.setMnemonic ((int)'Y');
		ayudaContenido.addActionListener (this);
		
		ayudaAtajos = new JMenuItem ("Atajos...  ", new ImageIcon ("Imagenes/ayuda3.png"));
		ayudaAtajos.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_J, Event.CTRL_MASK));
		ayudaAtajos.setMnemonic ((int)'J');
		ayudaAtajos.addActionListener (this);
		
		acercaDe = new JMenuItem ("Acerca de Magesti...  "	, new ImageIcon ("Imagenes/ayuda2.png"));
		acercaDe.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));
		acercaDe.setMnemonic ((int)'M');
		acercaDe.addActionListener (this);
		
		cerrarAplicacion = new JMenuItem ("Salir del sistema  ", new ImageIcon ("Imagenes/cerrar.png"));
		cerrarAplicacion.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		cerrarAplicacion.setMnemonic ((int)'S');	
		cerrarAplicacion.addActionListener (this);

		mnuOrdenDeTrabajo.add (ingresarOrdenDeTrabajo);
		mnuOrdenDeTrabajo.addSeparator ();
		mnuOrdenDeTrabajo.add (consultarOrdenDeTrabajo);
		
		mnuCalendario.add (mostrarCalendario);

		mnuSolicitudDeCompra.add (ingresarSolicitudDeCompra);
		mnuSolicitudDeCompra.addSeparator ();
		mnuSolicitudDeCompra.add (consultarSolicitudDeCompra);

		mnuRecepcionDePedido.add (registrarRecepcionDePedido);
		
		mnuAdministracion.add (clientes);

		mnuAyuda.add (ayudaContenido);
		mnuAyuda.addSeparator ();
		mnuAyuda.add (ayudaAtajos);
		mnuAyuda.addSeparator ();
		mnuAyuda.add (acercaDe);
		
		mnuSalir.add (cerrarAplicacion);

		barra.add (mnuOrdenDeTrabajo);
		barra.add (mnuSolicitudDeCompra);
		barra.add (mnuRecepcionDePedido);
		barra.add (mnuAdministracion);
		barra.add (mnuCalendario);
		barra.add (mnuAyuda);
		barra.add (mnuSalir);

		btnNuevaOrdenDeTrabajo = new JButton (new ImageIcon ("Imagenes/nuevaOT.png"));
		btnNuevaOrdenDeTrabajo.setToolTipText ("Ingresar nueva Orden de Trabajo");
		btnNuevaOrdenDeTrabajo.addActionListener (this);
		
		btnBuscarOrdenDeTrabajo = new JButton (new ImageIcon ("Imagenes/buscarOT.png"));
		btnBuscarOrdenDeTrabajo.setToolTipText ("Buscar una Orden de Trabajo");
		btnBuscarOrdenDeTrabajo.addActionListener (this);
		
		btnNuevaSolicitudDeCompra = new JButton (new ImageIcon ("Imagenes/nuevaSC.png"));
		btnNuevaSolicitudDeCompra.setToolTipText ("Ingresar nueva Solicitud de Compra");
		btnNuevaSolicitudDeCompra.addActionListener (this);
		
		btnBuscarSolicitudDeCompra = new JButton (new ImageIcon ("Imagenes/buscarSC.png"));
		btnBuscarSolicitudDeCompra.setToolTipText ("Buscar una Solicitud de Compra");
		btnBuscarSolicitudDeCompra.addActionListener (this);
		
		btnNuevoRegistroDePedido = new JButton (new ImageIcon ("Imagenes/nuevaRP.png"));
		btnNuevoRegistroDePedido.setToolTipText ("Registrar Recepci�n de Pedido");
		btnNuevoRegistroDePedido.addActionListener (this);
		
		btnCalendario = new JButton (new ImageIcon ("Imagenes/calendario.png"));
		btnCalendario.setToolTipText ("Ver calendario");
		btnCalendario.addActionListener (this);
		
		btnAdministracion = new JButton (new ImageIcon ("Imagenes/administracion.png"));
		btnAdministracion.setToolTipText ("Registrar nuevos datos en la Base");
		btnAdministracion.addActionListener (this);
		
		btnAyuda = new JButton (new ImageIcon ("Imagenes/ayuda.png"));
		btnAyuda.setToolTipText ("Ayuda");
		btnAyuda.addActionListener (this);
		
		btnSalir = new JButton (new ImageIcon ("Imagenes/salir.png"));
		btnSalir.setToolTipText ("Salir de la aplicaci�n");
		btnSalir.addActionListener (this);

		barraHerramientas = new JToolBar ();
		barraHerramientas.add (btnNuevaOrdenDeTrabajo);
		barraHerramientas.add (btnBuscarOrdenDeTrabajo);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnNuevaSolicitudDeCompra);
		barraHerramientas.add (btnBuscarSolicitudDeCompra);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnNuevoRegistroDePedido);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnCalendario);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnAdministracion);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnAyuda);
		barraHerramientas.addSeparator ();
		barraHerramientas.add (btnSalir);
		barraHerramientas.addSeparator ();
		
		propiedadIntelectual = new JLabel (" " + "para CMYK S.A. por �2012 - De Napoli, Godoy, Jim�nez y asociados.", Label.LEFT);
		propiedadIntelectual.setForeground (Color.black);
		propiedadIntelectual.setToolTipText ("Todos los derechos reservados");
		
		fechaPie = new JLabel ("" + fecha + " ", JLabel.RIGHT);
		fechaPie.setForeground (Color.black);
		fechaPie.setToolTipText ("Fecha actual del sistema");
		
		barraDeEstado.setLayout (new BorderLayout());
		barraDeEstado.add (propiedadIntelectual, BorderLayout.WEST);
		barraDeEstado.add (fechaPie, BorderLayout.EAST);

		escritorio.putClientProperty ("JDesktopPane.dragMode", "outline");

		getContentPane().add (barraHerramientas, BorderLayout.NORTH);
		getContentPane().add (escritorio, BorderLayout.CENTER);
		getContentPane().add (barraDeEstado, BorderLayout.SOUTH);

		setVisible (true);
	}

	public void actionPerformed (ActionEvent ae) 
	{
		Object obj = ae.getSource();
		if (obj == ingresarOrdenDeTrabajo || obj == btnNuevaOrdenDeTrabajo) 
		{
			/*
			 * C�digo para ingresar la Orden de Trabajo
			 */
		}
		else if (obj == consultarOrdenDeTrabajo || obj == btnBuscarSolicitudDeCompra) 
		{
			/*
			 * C�digo para consultar la Orden de Trabajo
			 */
		}
		else if (obj == cerrarAplicacion || obj== btnSalir) 
		{
			salirDelPrograma();
		}
		else if (obj == ingresarSolicitudDeCompra || obj == btnBuscarOrdenDeTrabajo) 
		{
			/*
			 * C�digo para ingresar la Solicitud de Compra
			 */
		}
		else if (obj == consultarSolicitudDeCompra || obj == btnNuevaSolicitudDeCompra) 
		{		
			/*
			 * C�digo para consultar la Solicitud de Compra
			 */
		}
		else if (obj == btnNuevoRegistroDePedido  ||  obj == registrarRecepcionDePedido)  
		{
			/*
			 * C�digo para ingresar un Registro De Pedido
			 */
		}
		else if (obj == btnCalendario) 
		{
			/*
			 * C�digo para mostrar el Calendario
			 */
		}
		
		else if (obj == clientes) 
		{
			/*
			 * C�digo para registrar Clientes
			 */
		}
		
		else if (obj == ayudaContenido || obj == btnAyuda) 
		{
			/*
			 * C�digo para mostrar Contenido de Ayuda
			 */
		}
		else if (obj == ayudaAtajos || obj == btnSalir) 
		{
			/*
			 * C�digo para mostrar las teclas de Atajos
			 */
		}
		else if (obj == acercaDe) 
		{
			/*
			 * C�digo para mostrar la Ventana "Acerca de..."
			 */
		}
	}

	private void salirDelPrograma() 
	{
		try 
		{

		    int reply = JOptionPane.showConfirmDialog 
		    (
		    	this,
		    	"�Est� seguro que desea cerrar\nesta aplicaci�n?",
		    	qTITULO + " - Salir", 
		    	JOptionPane.YES_NO_OPTION, 
		    	JOptionPane.WARNING_MESSAGE
		    );

			if (reply == JOptionPane.YES_OPTION) 
			{
				setVisible (false);
				dispose();
				System.out.println ("Gracias por utilizar MAGESTI\n�2012 - De Napoli, Godoy, Jim�nez y asociados.");
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
	@Override
	public void itemStateChanged(ItemEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

}





































