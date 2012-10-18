package vista_Controlador;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.EOFException;
import java.io.LineNumberReader;
import java.io.StringReader;

import javax.swing.*;

import Modelo.ConexionDB;

import java.util.*;
import java.text.*;

@SuppressWarnings("serial")
public class Magesti extends JFrame implements ActionListener, ItemListener,
		Config {
	private static JDesktopPane escritorio = new JDesktopPane ();
	
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
		clientes,
		Proveedor,
		Calidad,
		Formato,
		Variante;
	private	JMenuItem 
		mostrarCalendario,
		reporte;
	
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
	private JMenuItem Stock;
	

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
		clientes.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
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
		
		reporte = new JMenuItem ("Reporte  ", new ImageIcon ("Imagenes/imprimir.png"));
		reporte.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		reporte.setMnemonic ((int)'R');
		reporte.addActionListener (this);
		mnuAdministracion.add (reporte);
		mnuAdministracion.addSeparator ();

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
		mnuAdministracion.add (Proveedor);
		
		Stock = new JMenuItem("Registro de Stock");
		Stock.setIcon(new ImageIcon("C:\\Documents and Settings\\Agustina\\Escritorio\\Magesti\\maGESTIc\\Magesti\\Imagenes\\clientes.png"));
		Stock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnuAdministracion.add(Stock);
		Stock.addActionListener (this);
		
		JMenu mnAdministracionAtributosPapel = new JMenu("Administracion Atributos de Papel");
		mnuAdministracion.add(mnAdministracionAtributosPapel);
		
		Calidad= new JMenuItem ("Administracion Calidad  ", new ImageIcon ("Imagenes/clientes.png"));
		mnAdministracionAtributosPapel.add(Calidad);
		Calidad.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		Calidad.setMnemonic ((int)'C');
		
		Formato= new JMenuItem ("Administracion Formato  ", new ImageIcon ("Imagenes/clientes.png"));
		mnAdministracionAtributosPapel.add(Formato);
		Formato.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		Formato.setMnemonic ((int)'F');
		
		Variante= new JMenuItem ("Administracion Variante  ", new ImageIcon ("Imagenes/clientes.png"));
		mnAdministracionAtributosPapel.add(Variante);
		Variante.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		Variante.setMnemonic ((int)'G');
		Variante.addActionListener (this);
		Formato.addActionListener (this);
		Calidad.addActionListener (this);
		

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
		btnNuevoRegistroDePedido.setToolTipText ("Registrar Recepción de Pedido");
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
		
		propiedadIntelectual = new JLabel (" " + "para CMYK S.A. por ©2012 - De Napoli, Godoy, Jiménez y asociados.", Label.LEFT);
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
		
		TablaDeBusqueda_Top5 tablaTop5 = new TablaDeBusqueda_Top5();
		escritorio.add (tablaTop5);
		tablaTop5.show ();
		try {
			tablaTop5.setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actionPerformed (ActionEvent ae) 
	{
		Object obj = ae.getSource();
		if (obj == ingresarOrdenDeTrabajo || obj == btnNuevaOrdenDeTrabajo) 
		{
			boolean b = Metodos.openChildWindow ("Nueva Orden de Trabajo");
			if (b == false) 
			{
				OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
				escritorio.add (nuevaOT);
				nuevaOT.show ();
			}
		}
		else if (obj == consultarOrdenDeTrabajo)
		{

				boolean b = Metodos.openChildWindow ("Buscador");
				if (b == false) 
				{
					TablaDeBusqueda_Top5 tablaConsultar = new TablaDeBusqueda_Top5();
					escritorio.add (tablaConsultar);
					tablaConsultar.show ();
					try {
						tablaConsultar.setMaximum(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		}
		/*else if (obj == btnBuscarSolicitudDeCompra) 
		{

				boolean b = openChildWindow ("Buscador");
				if (b == false) 
				{
					TablaDeBusqueda tablaB = new TablaDeBusqueda("Buscar Solicitud de Compra",false);
					escritorio.add (tablaB);
					tablaB.show ();
				}

		}*/
		else if (obj == cerrarAplicacion || obj== btnSalir) 
		{
			salirDelPrograma();
		}
		else if (obj == ingresarSolicitudDeCompra || obj == btnNuevaSolicitudDeCompra) 
		{
			/*
			 * Código para ingresar la Solicitud de Compra
			 */
			{
				boolean b = Metodos.openChildWindow ("SC");
				if (b == false) 
				{
				SolicitudDeCompra nSC = new SolicitudDeCompra(false);
				escritorio.add (nSC);
				nSC.show ();
				}
				}
		}
		else if (obj == btnBuscarOrdenDeTrabajo) 
		{

				boolean b = Metodos.openChildWindow ("Buscador");
				if (b == false) 
				{
					TablaDeBusqueda tablaB = new TablaDeBusqueda();
					escritorio.add (tablaB);
					tablaB.show ();
					try {
						tablaB.setMaximum(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		}
		else if (obj == consultarSolicitudDeCompra || obj==btnBuscarSolicitudDeCompra) 
		{		
			boolean b = Metodos.openChildWindow ("Buscador");
			if (b == false) 
			{
				TablaDeBusqueda_SC tablaB = new TablaDeBusqueda_SC("Consultar Solicitud de Compra");
				escritorio.add (tablaB);
				tablaB.show ();
				try {
					tablaB.setMaximum(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if (obj == btnNuevoRegistroDePedido  ||  obj == registrarRecepcionDePedido)  
		{
			/*
			 * Código para ingresar un Registro De Pedido
			 */
			boolean b = Metodos.openChildWindow ("SC");
			if (b == false) 
			{
			SolicitudDeCompra nSC = new SolicitudDeCompra(true);
			escritorio.add (nSC);
			nSC.show ();
			}
				
		}
		else if (obj == reporte) 
		{
		boolean b = Metodos.openChildWindow ("Reporte");
		if (b == false) 
		{
		Metodos.imprimir (Metodos.fabricaReporte(1020), this);
		}
		}
		
		
		else if (obj == btnCalendario || obj==mostrarCalendario)  
		{
			boolean b = Metodos.openChildWindow ("Calendario");
			if (b == false) 
			{
				Calendario cal = new Calendario();
				escritorio.add (cal);
				cal.show ();
			}
		}
		
		else if (obj == clientes) 
		{
			
			boolean b = Metodos.openChildWindow ("Registro de Clientes");
			if (b == false) 
			{
				Adm_Cliente admCli= new Adm_Cliente();
				escritorio.add (admCli);
				try {
					admCli.setMaximum(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				admCli.show ();
			}
			
			
		}
		
		else if (obj == Proveedor) 
		{
			
			boolean b = Metodos.openChildWindow ("Registro de Proveedor");
			if (b == false) 
			{
				Adm_Proveedor admProv= new Adm_Proveedor();
				escritorio.add (admProv);
				admProv.show ();
				try 
				{
					admProv.setMaximum(true);
				} 
				catch (PropertyVetoException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		else if (obj == Calidad) 
		{
			
			boolean b = Metodos.openChildWindow ("Registro de Calidad");
			if (b == false) 
			{
				Adm_Calidad admCal= new Adm_Calidad();
				escritorio.add (admCal);
				admCal.show ();
			}
		}
		
		else if (obj == Formato) 
		{
			
			boolean b = Metodos.openChildWindow ("Registro de Formato");
			if (b == false) 
			{
				Adm_Formato admFor= new Adm_Formato();
				escritorio.add (admFor);
				admFor.show ();
			}
		}
		
		
		else if (obj == Variante) 
		{
			
			boolean b = Metodos.openChildWindow ("Registro de Variante");
			if (b == false) 
			{
				Adm_Variante admVar= new Adm_Variante();
				escritorio.add (admVar);
				admVar.show ();
			}
		}
		
		else if (obj == Stock)
		{
			boolean b = Metodos.openChildWindow ("Registro de Stock");
			if (b == false) 
			{
				Adm_Stock admStock= new Adm_Stock();
				escritorio.add (admStock);
				try 
				{
					admStock.setMaximum(true);
				} 
				catch (PropertyVetoException e) 
				{
					e.printStackTrace();
				}
				admStock.show ();
			}
		}
		
		else if (obj == ayudaContenido || obj == btnAyuda) 
		{
			boolean b = Metodos.openChildWindow ("Ayuda Magesti");
			if (b == false) 
			{
				MagestiHelp hlpMagesti = new MagestiHelp ("Contenido de Ayuda", "Ayuda/Magesti.htm");
				escritorio.add (hlpMagesti);
				hlpMagesti.show ();
			}
		}
		else if (obj == ayudaAtajos) 
		{
			boolean b = Metodos.openChildWindow ("Atajos Magesti");
			if (b == false) 
			{
				MagestiHelp hlpAtajos = new MagestiHelp ("Atajos / Combinaciones de teclas", "Ayuda/Atajos.htm");
				escritorio.add (hlpAtajos);
				hlpAtajos.show ();
			}

		}
		else if (obj == acercaDe) 
		{
			String msg = qTITULO + "\n\n" + "Creado y diseñado por:\n" + 
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
		    	qTITULO + " - Salir", 
		    	JOptionPane.YES_NO_OPTION, 
		    	JOptionPane.WARNING_MESSAGE
		    );

			if (reply == JOptionPane.YES_OPTION) 
			{
				setVisible (false);
				ConexionDB.getbaseDatos().cerrar();
				dispose();
				System.out.println ("Gracias por utilizar MAGESTI\n©2012 - De Napoli, Godoy, Jiménez y asociados.");
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
}






































