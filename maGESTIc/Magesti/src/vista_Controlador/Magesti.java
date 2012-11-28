package vista_Controlador;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;

import Modelo.ConexionDB;

import java.util.*;
import java.text.*;

@SuppressWarnings("serial")
public class Magesti extends JFrame implements ActionListener, ItemListener,
		Config {
	private static JDesktopPane escritorio = new JDesktopPane ();
	
	private static Calendario Calendario;

	private JInternalFrame OT;

	private JInternalFrame BuscarOT;

	private JInternalFrame SC;

	private JInternalFrame BuscarSC;

	private JInternalFrame TablaTop5;

	private JInternalFrame Clientes;

	private JInternalFrame Proveedores;

	private JInternalFrame AtributosPapel;

	private JInternalFrame ProcesosOT;

	private JInternalFrame Stocks;

	private JInternalFrame Ayuda;

	private JInternalFrame Atajos;
	private JMenuBar barra;
	private int numeroAureo;
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
		clientes,
		Proveedor,
		Stock,
		Atributos,
		Procesos;
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
		
		super (qTITULO +"  -  Mantenimiento y Gestión de Imprentas");
		
		escritorio.setBackground(new Color(83, 130, 161));
		UIManager.addPropertyChangeListener (new UISwitchListener ((JComponent)getRootPane()));
		barra = new JMenuBar ();
		setIconImage (getToolkit().getImage ("Imagenes/icono.png"));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		//setSize(Toolkit.getDefaultToolkit().getScreenSize());
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


		Calendario = new Calendario();
		escritorio.add (Calendario);
		Calendario.setBounds
		(
				0,0,503,216
		);
		Calendario.show ();
		
		TablaTop5 = new TablaDeBusqueda_Top5();
		escritorio.add (TablaTop5);
		TablaTop5.setBounds
		(
				503,0,Toolkit.getDefaultToolkit().getScreenSize().width  - Calendario.getWidth(),216
		);
		TablaTop5.show ();
		
		mnuOrdenDeTrabajo = new JMenu ("Orden de Trabajo  ");
		mnuOrdenDeTrabajo.setMnemonic ((int)'O');
		
		mnuSolicitudDeCompra = new JMenu ("Solicitud de Compra  ");
		mnuSolicitudDeCompra.setMnemonic ((int)'S');
		
		mnuRecepcionDePedido = new JMenu ("Recepción de Pedido  ");
		mnuRecepcionDePedido.setMnemonic ((int)'R');
		
		mnuAdministracion = new JMenu ("Administración  ");
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
		clientes.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		clientes.setMnemonic ((int)'G');
		clientes.addActionListener (this);
		
		Proveedor = new JMenuItem ("Registro de Proveedores  ", new ImageIcon ("Imagenes/clientes.png"));
		Proveedor.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		Proveedor.setMnemonic ((int)'P');
		Proveedor.addActionListener (this);
		
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
		cerrarAplicacion.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		cerrarAplicacion.setMnemonic ((int)'Q');	
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
		mnuAdministracion.add (Proveedor);
		
		Stock = new JMenuItem("Registro de Stock", new ImageIcon ("Imagenes/clientes.png"));
		Stock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnuAdministracion.add(Stock);
		Stock.addActionListener (this);
		
		Atributos = new JMenuItem("Registro de Atributos de papel", new ImageIcon ("Imagenes/clientes.png"));
		Atributos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnuAdministracion.add(Atributos);
		Atributos.addActionListener (this);
		

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
		
		Procesos = new JMenuItem("Registro de Procesos", new ImageIcon ("Imagenes/clientes.png"));
		Procesos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnuAdministracion.add(Procesos);
		Procesos.addActionListener (this);
		
		
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
		btnNuevoRegistroDePedido.setToolTipText ("Registrar Recepción de Pedido");
		btnNuevoRegistroDePedido.addActionListener (this);
		
		btnCalendario = new JButton (new ImageIcon ("Imagenes/calendario.png"));
		btnCalendario.setToolTipText ("Ver calendario");
		btnCalendario.addActionListener (this);
		
		btnAdministracion = new JButton (new ImageIcon ("Imagenes/administracion.png"));
		btnAdministracion.setToolTipText ("Administración de Stock");
		btnAdministracion.addActionListener (this);
		
		btnAyuda = new JButton (new ImageIcon ("Imagenes/ayuda.png"));
		btnAyuda.setToolTipText ("Ayuda");
		btnAyuda.addActionListener (this);
		
		btnSalir = new JButton (new ImageIcon ("Imagenes/salir.png"));
		btnSalir.setToolTipText ("Salir de la aplicación");
		btnSalir.addActionListener (this);

		barraHerramientas = new JToolBar ();
		barraHerramientas.setFloatable(false);
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
		
		propiedadIntelectual = new JLabel (" " + "para CMYK S.A. por ®2012 - De Napoli, Godoy, Jiménez y asociados.", Label.LEFT);
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
			if (OT == null || OT.isClosed() || (!OT.isShowing() && !OT.isIcon())) 
			{
				OT = new OrdenDeTrabajo ();
				escritorio.add (OT);
				OT.show ();
			}
			else
			{
				OT.toFront();
			}
		}
//		else if (obj == consultarOrdenDeTrabajo)
//		{
//			if (TablaTop5 == null || TablaTop5.isClosed() || (!TablaTop5.isShowing() && !TablaTop5.isIcon())) 
//			{
//				TablaTop5 = new TablaDeBusqueda_Top5();
//				escritorio.add (TablaTop5);
//				TablaTop5.show ();
//				try {
//					TablaTop5.setMaximum(true);
//				} catch (PropertyVetoException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		}
		
		else if (obj == cerrarAplicacion || obj== btnSalir) 
		{
			salirDelPrograma();
		}
		else if (obj == ingresarSolicitudDeCompra || obj == btnNuevaSolicitudDeCompra) 
		{
			if (SC == null || SC.isClosed() || (!SC.isShowing() && !SC.isIcon())) 
			{
				SC = new SolicitudDeCompra(false);
				escritorio.add (SC);
				SC.show ();
			}
			else
			{
				SC.toFront();
			}
		}
		else if (obj == btnBuscarOrdenDeTrabajo || obj==consultarOrdenDeTrabajo) 
		{
			if (BuscarOT == null || BuscarOT.isClosed() || (!BuscarOT.isShowing() && !BuscarOT.isIcon())) 
			{
				BuscarOT = new TablaDeBusqueda();
				escritorio.add (BuscarOT);
				BuscarOT.show ();
				try {
					BuscarOT.setMaximum(true);
				} catch (PropertyVetoException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				BuscarOT.toFront();
			}

		}
		else if (obj == consultarSolicitudDeCompra || obj==btnBuscarSolicitudDeCompra || obj == btnNuevoRegistroDePedido  ||  obj == registrarRecepcionDePedido) 
		{		
			if (BuscarSC == null || BuscarSC.isClosed() || (!BuscarSC.isShowing() && !BuscarSC.isIcon())) 
			{
				BuscarSC = new TablaDeBusqueda_SC("Consultar Solicitud de Compra");
				escritorio.add (BuscarSC);
				BuscarSC.show ();
				try 
				{
					BuscarSC.setMaximum(true);
				} 
				catch (PropertyVetoException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				BuscarSC.toFront();
			}
		}
				
		
		else if (obj == btnCalendario || obj==mostrarCalendario)  
		{
			Calendario.toFront();
//			boolean b = Metodos.openChildWindow ("Calendario");
//			if (b == false) 
//			{
//				Calendario cal = new Calendario();
//				escritorio.add (cal);
//				cal.setBoundsf
//				(
//						0,0,503,216
//				);
//			}
		}
		
		else if (obj == clientes) 
		{
			if (Clientes == null || Clientes.isClosed() || (!Clientes.isShowing() && !Clientes.isIcon())) 
			{
				Clientes= new Adm_Cliente();
				escritorio.add (Clientes);
				try 
				{
					Clientes.setMaximum(true);
				} 
				
				catch (PropertyVetoException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Clientes.show ();
			}
			else
			{
				Clientes.toFront();
			}
			
			
		}
		
		else if (obj == Proveedor) 
		{
			if (Proveedores == null || Proveedores.isClosed() || (!Proveedores.isShowing() && !Proveedores.isIcon()))
			{
				Proveedores= new Adm_Proveedor();
				escritorio.add (Proveedores);
				Proveedores.show ();
				try 
				{
					Proveedores.setMaximum(true);
				} 
				catch (PropertyVetoException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				Proveedores.toFront();
			}
			
			
		}
		
		else if (obj == Atributos) 
		{
			if (AtributosPapel == null || AtributosPapel.isClosed() || ( !AtributosPapel.isShowing() && !AtributosPapel.isIcon()))
			{
				AtributosPapel= new Adm_Atributos();
				escritorio.add (AtributosPapel);
				AtributosPapel.setBounds(0, 0, escritorio.getWidth()/2, escritorio.getHeight());
				AtributosPapel.show ();
			}
			else
			{
				AtributosPapel.toFront();
			}
		}
		
		else if (obj == Procesos) 
		{
			if (ProcesosOT == null || ProcesosOT.isClosed() || (!ProcesosOT.isShowing() && !ProcesosOT.isIcon())) 
			{
				ProcesosOT = new Adm_Proceso();
				escritorio.add (ProcesosOT);
				ProcesosOT.setBounds(0, 0, escritorio.getWidth()/2, escritorio.getHeight());
				ProcesosOT.show ();
			}
			else
			{
				ProcesosOT.toFront();
			}
		}
		
		else if (obj == Stock || obj == btnAdministracion)
		{
			if (Stocks == null || Stocks.isClosed() || (!Stocks.isShowing() && !Stocks.isIcon()))
			{
				Stocks = new Adm_Stock();
				escritorio.add (Stocks);
				try 
				{
					Stocks.setMaximum(true);
				} 
				catch (PropertyVetoException e) 
				{
					e.printStackTrace();
				}
				Stocks.show ();
			}
			else
			{
				Stocks.toFront();
			}
		}
		
		else if (obj == ayudaContenido || obj == btnAyuda) 
		{
			if (Ayuda == null || Ayuda.isClosed() || (!Ayuda.isShowing() && !Ayuda.isIcon())) 
			{
				Ayuda = new MagestiHelp ("Contenido de Ayuda", "Ayuda/Magesti.htm");
				escritorio.add (Ayuda);
				Ayuda.show ();
			}
			else
			{
				Ayuda.toFront();
			}
		}
		
		else if (obj == ayudaAtajos) 
		{
			if (Atajos == null || Atajos.isClosed() || (!Atajos.isShowing() && !Atajos.isIcon())) 
			{
				Atajos = new MagestiHelp ("Atajos / Combinaciones de teclas", "Ayuda/Atajos.htm");
				escritorio.add (Atajos);
				Atajos.show ();
			}
			else
			{
				Atajos.toFront();
			}

		}
		else if (obj == acercaDe) 
		{
			String msg = qTITULO + "\n\n" + "Creado y diseñado por:\n" + 
					"®2012 - De Napoli, Godoy, Jiménez y asociados.\n\n"+
					"  -dn.agus@gmail.com\n"+
					"  -j.godoy277@gmail.com\n"+
					"  -carlos.nelson.jimenez@googlemail.com\n";
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
		    	qTITULO + " - Salir", 
		    	JOptionPane.YES_NO_OPTION, 
		    	JOptionPane.WARNING_MESSAGE
		    );

			if (reply == JOptionPane.YES_OPTION) 
			{
				setVisible (false);
				ConexionDB.getbaseDatos().cerrar();
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

	public static JInternalFrame getCalendario() {
		return Calendario;
	}
	
	
	
}
