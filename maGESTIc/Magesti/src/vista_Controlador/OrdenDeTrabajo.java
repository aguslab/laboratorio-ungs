package vista_Controlador;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Modelo.Calidad;
import Modelo.Cliente;
import Modelo.ConexionDB;
import Modelo.Elemento;
import Modelo.ElementosMaterialesTmp;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Procesos_x_OT;
import Modelo.Proveedor;
import Modelo.Variante;

@SuppressWarnings("serial")

public class OrdenDeTrabajo extends JInternalFrame implements ActionListener, Config
{
	private ArrayList<String> elementos=new ArrayList<String>();
	private ArrayList<Integer> cantidad=new ArrayList<Integer>();
	private boolean almaceno=false;
	private ArrayList<String[]> FilasElementos=new ArrayList<String[]>();
	//private String[] row_elem;
	private ArrayList<ElementosMaterialesTmp> elem_mat=new ArrayList<ElementosMaterialesTmp>();
	private JPanel jpOrdenDeTrabajo = new JPanel();
	private JTable secMateriales;
	
	private JLabel 
		lbNro, 
		lbCliente, 
		lbFechaC, 
		lbFechaP, 
		lbNombreOT,
		lbEstado,
		lbDescripcion,
		lbAncho,
		lbAlto,
		lbTipoDeProducto,
		lbCantidadAEntregar,
		lbPreimpresion,
		lbCantidadDeHojasUtilizadas;
	
	private JTextField 
		txtNro, 
		txtNombreOT,
		txtDescripcion,
		txtAncho,
		txtAlto,
		txtCantidadAEntregar,
		txtPreimpresion,
		txtCantidadDeHojasUtilizadas;
	
	private JComboBox 
		cboCliente,
		cboMes, 
		cboDia, 
		cboAnio,
		cboMes2, 
		cboDia2, 
		cboAnio2,
		cboEstado;
	private JComboBox cboEstado_1;
	
	private JButton
		btnLimpiarOT,
		btnGuardar, 
		btnCancelar;
	
	private JCheckBox
		chbApaisado;
	
	
	
	private JTabbedPane
		tabSecciones;
	
	
	private DefaultTableModel dtmMateriales;
	
	
	//ArrayList<String> Clientes= Cliente.getClientes();
	
	String Clientes[] = Cliente.getClientes(); 
	
	/*{ PLAN B 
		"Cosméticos Avon S.A.C.I.", 
		"Garbarino S.A.", 
		"Fravega S.A.C.I.", 
		"Cablevisión S.A.", 
	};*/
	
	String Meses[] = 
	{
		"Enero", 
		"Febrero", 
		"Marzo", 
		"Abril", 
		"Mayo", 
		"Junio",
		"Julio", 
		"Agosto", 
		"Septiembre", 
		"Octubre", 
		"Noviembre", 
		"Diciembre"
	};
	
	String Estados[] = 
	{
		"Pendiente", 
		"En proceso", 
		"Cerrado" 
	};
	
	/*String TipoDeProducto[] = Tipo_producto.getTiposProd();
	//{ // PLAN B
		"Block de facturas", 
		"Revista", 
		"Calendario",
		"Cuaderno"
	};*/
	private JTextField txtTipoProducto;
	private JTable tablaElementos;
	private JPanel panMateriales;
	private JScrollPane spMateriales;
	private JTable tablaMateriales;
	private JScrollPane spOrdenEjecucion;
	private JTable tablaOrdenDeEjecucion;

	OrdenDeTrabajo()
	{	
		super ("Orden de Trabajo (OT)", false, true, false, true);
		
		setSize (680, 680);
		
		jpOrdenDeTrabajo.setBounds (0, 0, 500, 115);

		lbNro = new JLabel ("Número:");
		lbNro.setBounds(15, 20, 80, 25);
		lbNro.setForeground (Color.black);
		
	    lbCliente = new JLabel ("Cliente:");
	    lbCliente.setBounds(355, 20, 80, 25);
		lbCliente.setForeground (Color.black);
	    
		lbFechaC = new JLabel ("Fecha confec.:");
		lbFechaC.setBounds(15, 55, 80, 25);
		lbFechaC.setForeground (Color.black);
		
		lbFechaP = new JLabel ("Fecha prom.:");
		lbFechaP.setBounds(355, 55, 80, 25);
		lbFechaP.setForeground (Color.black);
		
		lbNombreOT = new JLabel ("Nombre OT:");
		lbNombreOT.setBounds(15, 90, 80, 25);
		lbNombreOT.setForeground (Color.black);
		
		lbEstado = new JLabel ("Estado:");
		lbEstado.setBounds(355, 90, 80, 25);
		lbEstado.setForeground (Color.black);
		
		lbDescripcion = new JLabel ("Descripcion:");
		lbDescripcion.setBounds(15, 125, 80, 25);
		lbDescripcion.setForeground (Color.black);

		String maxIdOT=Orden_Trabajo.getUltOT()+"";
		
		txtNro = new JTextField (maxIdOT);
		txtNro.setEditable(false);
		txtNro.setForeground(Color.RED);
		txtNro.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNro.setFocusable(false);
		txtNro.setBounds(105, 20, 210, 25);
		txtNro.setHorizontalAlignment (JTextField.LEFT);
		
		cboCliente = new JComboBox (Clientes);
		cboCliente.setBounds(445, 20, 210, 25);
		
		txtNombreOT = new JTextField ();
		txtNombreOT.setBounds(105, 90, 210, 25);
		txtNombreOT.setHorizontalAlignment (JTextField.LEFT);
		
		txtDescripcion = new JTextField ();
		txtDescripcion.setBounds(105, 125, 550, 25);
		txtDescripcion.setHorizontalAlignment (JTextField.LEFT);
		
		lbAncho = new JLabel ("Ancho:");
		lbAncho.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAncho.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbAncho.setBounds(105, 161, 80, 25);
		lbAncho.setForeground (Color.black);
		
		txtAncho = new JTextField ("0");
		txtAncho.setBounds(202, 161, 100, 25);
		txtAncho.setHorizontalAlignment (JTextField.LEFT);
		
		lbAlto = new JLabel ("Alto:");
		lbAlto.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAlto.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbAlto.setBounds(312, 161, 80, 25);
		lbAlto.setForeground (Color.black);
		
		txtAlto = new JTextField ("0");
		txtAlto.setBounds(402, 161, 100, 25);
		txtAlto.setHorizontalAlignment (JTextField.LEFT);
		
		chbApaisado = new JCheckBox ("Apaisado");
		chbApaisado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chbApaisado.setBounds(580, 160, 80, 25);
		
		lbTipoDeProducto = new JLabel ("Tipo de Prod.:");
		lbTipoDeProducto.setBounds(15, 195, 80, 25);
		lbTipoDeProducto.setForeground (Color.black);
		
		lbCantidadAEntregar = new JLabel ("Cant. a Entr.:");
		lbCantidadAEntregar.setBounds(355, 195, 80, 25);
		lbCantidadAEntregar.setForeground (Color.black);
		
		txtCantidadAEntregar = new JTextField ("1");
		txtCantidadAEntregar.setBounds(445, 195, 210, 25);
		txtCantidadAEntregar.setHorizontalAlignment (JTextField.LEFT);
		
		lbPreimpresion = new JLabel ("Preimpresión:");
		lbPreimpresion.setBounds(15, 230, 80, 25);
		lbPreimpresion.setForeground (Color.black);
		
		txtPreimpresion = new JTextField ("0");
		txtPreimpresion.setBounds(105, 230, 210, 25);
		txtPreimpresion.setHorizontalAlignment (JTextField.LEFT);
		
		lbCantidadDeHojasUtilizadas = new JLabel ("Hojas utilizadas:");
		lbCantidadDeHojasUtilizadas.setBounds(15, 563, 100, 25);
		lbCantidadDeHojasUtilizadas.setForeground (Color.black);
		
		txtCantidadDeHojasUtilizadas = new JTextField ("0");
		txtCantidadDeHojasUtilizadas.setEnabled(false);
		txtCantidadDeHojasUtilizadas.setBounds(105,563, 100, 25);
		txtCantidadDeHojasUtilizadas.setHorizontalAlignment (JTextField.LEFT);
		
		tabSecciones = new JTabbedPane();
		tabSecciones.setBounds(15, 265, 640, 290);

		//Restriccion para que el usuario solo ingrese número.
		txtAncho.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtAlto.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtCantidadAEntregar.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtPreimpresion.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);

		cboMes = new JComboBox (Meses); //Comentar esta línea si quieren utilizar el WB
		//cboMes = new JComboBox<String> ();	//Descomentar esta línea para utilizar el WB
		cboMes.setBounds(105, 55, 97, 25);
		
		cboDia = new JComboBox ();
		cboDia.setBounds(202, 55, 48, 25);
		
		cboAnio = new JComboBox ();
		cboAnio.setBounds(250, 55, 65, 25);
		
		cboEstado = new JComboBox (Estados);	//Comentar esta línea si quieren utilizar el WB
		cboEstado_1 = new JComboBox ();
		cboEstado_1.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "En Proceso", "Cerrada"}));
		cboEstado_1.setToolTipText("Estado de la orden de trabajo");
		cboEstado_1.setBounds(445, 90, 210, 25);
		cboEstado_1.setEnabled(false);
		
		for (int i = 1; i <= 31; i++) 
		{
			String dias = "" + i;
			cboDia.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio.addItem (anios);
		}
		
		cboMes2 = new JComboBox (Meses);
		//cboMes2 = new JComboBox<String> ();
		
		cboMes2.setBounds(445, 55, 97, 25);
		
		cboDia2 = new JComboBox ();
		cboDia2.setBounds(542, 55, 48, 25);
		
		cboAnio2 = new JComboBox ();
		cboAnio2.setBounds(590, 55, 65, 25);
		
		for (int i = 1; i <= 31; i++) 
		{
			String dias = "" + i;
			cboDia2.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio2.addItem (anios);
		}
				
		btnLimpiarOT = new JButton("Limpiar campos");
		btnLimpiarOT.setBounds(15, 599, 121, 35);
		btnLimpiarOT.addActionListener (this);
					
		btnGuardar = new JButton ("Confirmar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnGuardar.setBounds(403, 599, 120, 35);
		btnGuardar.addActionListener (this);
		
		btnCancelar = new JButton ("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCancelar.setBounds(535, 599, 120, 35);
		btnCancelar.addActionListener (this);
		
		jpOrdenDeTrabajo.setLayout(null);

		jpOrdenDeTrabajo.add (lbNro);
		jpOrdenDeTrabajo.add (txtNro);
		jpOrdenDeTrabajo.add (lbCliente);
		jpOrdenDeTrabajo.add (cboCliente);
		jpOrdenDeTrabajo.add (lbFechaC);
		jpOrdenDeTrabajo.add (lbFechaP);
		jpOrdenDeTrabajo.add (cboMes);
		jpOrdenDeTrabajo.add (cboDia);
		jpOrdenDeTrabajo.add (cboAnio);
		jpOrdenDeTrabajo.add (cboMes2);
		jpOrdenDeTrabajo.add (cboDia2);
		jpOrdenDeTrabajo.add (cboAnio2);
		jpOrdenDeTrabajo.add (cboEstado_1);	
		jpOrdenDeTrabajo.add (lbNombreOT);
		jpOrdenDeTrabajo.add (lbEstado);
		jpOrdenDeTrabajo.add (lbDescripcion);
		jpOrdenDeTrabajo.add (txtNombreOT);
		jpOrdenDeTrabajo.add (txtDescripcion);
		jpOrdenDeTrabajo.add (lbAlto);
		jpOrdenDeTrabajo.add (lbAncho);
		jpOrdenDeTrabajo.add (chbApaisado);
		jpOrdenDeTrabajo.add (txtAlto);
		jpOrdenDeTrabajo.add (txtAncho);
		jpOrdenDeTrabajo.add (lbTipoDeProducto);
		jpOrdenDeTrabajo.add (lbCantidadAEntregar);
		jpOrdenDeTrabajo.add (lbPreimpresion);
		jpOrdenDeTrabajo.add (lbCantidadDeHojasUtilizadas);
		jpOrdenDeTrabajo.add (txtCantidadDeHojasUtilizadas);
		jpOrdenDeTrabajo.add (txtPreimpresion);
		jpOrdenDeTrabajo.add (txtCantidadAEntregar);
		jpOrdenDeTrabajo.add (tabSecciones);
		jpOrdenDeTrabajo.add (btnLimpiarOT);
		jpOrdenDeTrabajo.add (btnGuardar);
		jpOrdenDeTrabajo.add (btnCancelar);
		
		getContentPane().add (jpOrdenDeTrabajo);

		//setVisible (true);
		
		//Para la pestaña de la Seccion Elementos
		JPanel panElementos = new JPanel();
		panElementos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panElementos.setBounds(0, 0, 640, 250);
			
		panElementos.setLayout(null);
		
		tabSecciones.addTab
		(
			"Elementos",
			new ImageIcon ("Imagenes/registrar.png"), 
			panElementos,
	        "Elementos del producto"
		);
		
		JScrollPane spElementos = new JScrollPane();
		spElementos.setBounds(10, 11, 615, 193);
		panElementos.add(spElementos);
		/*{null, "Original", null},
				{null, "Duplicado", null},
				{null, "Triplicado", null},
				{null, "Tapa", null},
				{null, "Cant. Hojas", null},*/
		tablaElementos = new JTable();
		tablaElementos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Elemento del producto", "Cantidad"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaElementos.getColumnModel().getColumn(0).setPreferredWidth(124);
		spElementos.setViewportView(tablaElementos);
		
		JButton btnAgregarFila = new JButton("Agregar Fila");
		btnAgregarFila.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DefaultTableModel temp = (DefaultTableModel) tablaElementos.getModel();
				Object nuevo[]= {"",""};
				temp.addRow(nuevo);
			}
		});
		btnAgregarFila.setBounds(10, 215, 96, 23);
		panElementos.add(btnAgregarFila);
		
		JButton btnNewButton_1 = new JButton("Borrar Fila");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{	
				DefaultTableModel temp = (DefaultTableModel) tablaElementos.getModel();
				temp.removeRow(temp.getRowCount()-1);
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(116, 215, 96, 23);
		panElementos.add(btnNewButton_1);
		
		JButton btnAlmacenar = new JButton("Almacenar");
		btnAlmacenar.addActionListener(new ActionListener() {
//Evento que ocurre cuando se presiona el boton almacenar en la seccion elementos
			public void actionPerformed(ActionEvent e) 
			{
				tablaMateriales.removeAll();
				
				Integer cantFilas = tablaElementos.getRowCount();
				DefaultTableModel temp = (DefaultTableModel) tablaMateriales.getModel();
				for (int i = 0; i < cantFilas; i++) 
				{
					Object nuevaFila[]= {tablaElementos.getValueAt(i, 0),Integer.parseInt(tablaElementos.getValueAt(i, 1).toString()),"","","","","","","","",""};
					temp.addRow(nuevaFila);
				}
				
				
				
				
				// Valores para el combo
				String calidades[] = Calidad.getCalidades();
				TableColumn columnaCalidad = tablaMateriales.getColumnModel().getColumn(5);//table es la JTable, ponele que la col 0 es la del combo.
				columnaCalidad.setCellEditor(new MyComboBoxEditor(calidades));
				
				// Valores para el combo
				String variantes[] = Variante.getVariantes(); 
				TableColumn columnaVariante = tablaMateriales.getColumnModel().getColumn(4);//table es la JTable, ponele que la col 0 es la del combo.
				columnaVariante.setCellEditor(new MyComboBoxEditor(variantes));
				
				// Valores para el combo
				String formatos[] = Formato_Papel.getFormatos();
				TableColumn columnaFormato = tablaMateriales.getColumnModel().getColumn(3);//table es la JTable, ponele que la col 0 es la del combo.
				columnaFormato.setCellEditor(new MyComboBoxEditor(formatos));
			}
		});
		
		btnAlmacenar.setBounds(310, 215, 96, 23);
		panElementos.add(btnAlmacenar);
		tabSecciones.setEnabledAt(0, true);
		tabSecciones.setDisabledIconAt(0, null);
		tabSecciones.setMnemonicAt(0, KeyEvent.VK_E);
		
		
		
		panMateriales = new JPanel();
		
		
		
		panMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabSecciones.addTab("Materiales", new ImageIcon ("Imagenes/registrar.png"), panMateriales, "Materiales");
		tabSecciones.setEnabledAt(1, true);
		
		panMateriales.setLayout(null);
		
		spMateriales = new JScrollPane();
		spMateriales.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMateriales.setBounds(10, 11, 615, 228);
		panMateriales.add(spMateriales);
		
		tablaMateriales = new JTable();
		tablaMateriales.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Elemento", "Cantidad", "Gramaje", "Formato", "Variante", "Calidad", "Pliegos en demasia", "Poses x Pliego", "Pliegos x Hoja", "Hojas", "Pliegos Netos"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Object.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaMateriales.getColumnModel().getColumn(0).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(0).setPreferredWidth(95);
		tablaMateriales.getColumnModel().getColumn(0).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(1).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(1).setPreferredWidth(63);
		tablaMateriales.getColumnModel().getColumn(1).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(2).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(2).setPreferredWidth(59);
		tablaMateriales.getColumnModel().getColumn(2).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(3).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(3).setPreferredWidth(77);
		tablaMateriales.getColumnModel().getColumn(3).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(4).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablaMateriales.getColumnModel().getColumn(4).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(5).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablaMateriales.getColumnModel().getColumn(5).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(6).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(6).setPreferredWidth(114);
		tablaMateriales.getColumnModel().getColumn(6).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(7).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(7).setPreferredWidth(90);
		tablaMateriales.getColumnModel().getColumn(7).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(8).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(8).setPreferredWidth(90);
		tablaMateriales.getColumnModel().getColumn(8).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(9).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(9).setPreferredWidth(56);
		tablaMateriales.getColumnModel().getColumn(9).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(10).setResizable(false);
		tablaMateriales.getColumnModel().getColumn(10).setPreferredWidth(80);
		tablaMateriales.getColumnModel().getColumn(10).setMinWidth(30);
		spMateriales.setViewportView(tablaMateriales);
      
		JPanel panOrdenEjecucion = new JPanel();
		panOrdenEjecucion.setBorder(new EmptyBorder(5, 5, 5, 5));
		panOrdenEjecucion.setLayout(null);
        
		tabSecciones.addTab
		(
			"Orden de ejecución",
			new ImageIcon ("Imagenes/registrar.png"), 
			panOrdenEjecucion,
	        "Listado de tareas o procesos"
		);
		
		JScrollPane spListaDeProcesos = new JScrollPane();
		spListaDeProcesos.setBounds(10, 11, 204, 207);
		panOrdenEjecucion.add(spListaDeProcesos);
		
		final JList listaProcesos = new JList();
		spListaDeProcesos.setViewportView(listaProcesos);
		listaProcesos.setModel(new AbstractListModel() {
			String[] values = new String[] {"CTP", "Pel\u00EDculas", "Copia de chapas", "Corte inicial", "Impresi\u00F3n", "Barniz", "Laminado", "Trazado", "Puntillado", "Medio corte", "Troquelado", "Doblado", "Intercalado", "Emblocado", "Agujereado", "Abrochado", "Encuadernaci\u00F3n", "Confecci\u00F3n de sobres", "Corte final", "Empaque"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		spOrdenEjecucion = new JScrollPane();
		spOrdenEjecucion.setBounds(224, 11, 401, 239);
		panOrdenEjecucion.add(spOrdenEjecucion);
		
		tablaOrdenDeEjecucion = new JTable();
		tablaOrdenDeEjecucion.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Proceso", "Proveedor", "Observaciones", "Cumplida"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaOrdenDeEjecucion.getColumnModel().getColumn(0).setPreferredWidth(148);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(1).setPreferredWidth(139);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(2).setPreferredWidth(202);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(3).setPreferredWidth(62);
		spOrdenEjecucion.setViewportView(tablaOrdenDeEjecucion);
		
		JButton btnConfirmarSeleccion = new JButton("Confirmar Selecci\u00F3n");
		btnConfirmarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Integer cantFilas = tablaOrdenDeEjecucion.getRowCount();
				DefaultTableModel temp = (DefaultTableModel) tablaOrdenDeEjecucion.getModel();
				System.out.println(listaProcesos.getSelectedValues());
				for(int i = 0; i < cantFilas; i ++ )
				{

					
					Object nuevo[]= {"","","",""};
					temp.addRow(nuevo);
					tablaOrdenDeEjecucion.setValueAt(listaProcesos.getSelectedValues(), i, 0);
					
				}
				
			}
		});
		btnConfirmarSeleccion.setBounds(36, 229, 130, 23);
		panOrdenEjecucion.add(btnConfirmarSeleccion);
		
		tabSecciones.setMnemonicAt(1, KeyEvent.VK_O);
		//columnaTercerizada.setCellEditor(new MyCheckBoxEditor());
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setVisible(true);
		
		JLabel lblMedidaFinal = new JLabel("Medida Final");
		lblMedidaFinal.setBounds(15, 166, 80, 14);
		jpOrdenDeTrabajo.add(lblMedidaFinal);
		
		txtTipoProducto = new JTextField("");
		txtTipoProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtTipoProducto.setBounds(105, 195, 210, 25);
		jpOrdenDeTrabajo.add(txtTipoProducto);
		
		txtClear();
	}

	
	//Chequear un poco lo ingresado antes de guardar
	public void actionPerformed (ActionEvent ae) 
	{

		Object obj = ae.getSource();
		
		if (obj == btnGuardar) 
		{
			if (txtNombreOT.getText().equals("")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"Esta OT no tiene nombre asignado",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtNombreOT.requestFocus ();
				
			}
			else if (txtDescripcion.getText().equals("")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"No hay descripción de este trabajo",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtDescripcion.requestFocus ();
				
			}
			else if (txtTipoProducto.getText().equals("")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"Ingrese Tipo de producto",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtTipoProducto.requestFocus ();
				
			}
			else if (txtCantidadAEntregar.getText().equals("") || txtCantidadAEntregar.getText().equals("0")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe especificar un valor en la Cantidad a Entregar",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtCantidadAEntregar.requestFocus ();
				
			}
			else 
 {

				cargarTablas(); // Cargaría la tabla en memoria
				obj = btnCancelar;

			}
		}
		if (obj == btnCancelar) 
		{
			txtClear ();
			setVisible (false);
			dispose();
		}
		if (obj == btnLimpiarOT)
		{
			txtClear ();
		}

	}
	
	void cargarTablas() 
	{	
		//Se obtienen las variables para crear una nueva OT
		String fechaCon = (String) cboAnio.getSelectedItem() +"-"+ dameNumeroMes((String)cboMes.getSelectedItem()) +"-"+ cboDia.getSelectedItem();
		String fechaProm = (String) cboAnio2.getSelectedItem() +"-"+ dameNumeroMes((String) cboMes2.getSelectedItem()) +"-"+ cboDia2.getSelectedItem();
		Integer cantImp =  Integer.parseInt(txtPreimpresion.getText());
		Integer ancho = Integer.parseInt(txtAncho.getText());
		Integer alto = Integer.parseInt(txtAlto.getText());
		String TipoProd= txtTipoProducto.getText();
		boolean apaisado=chbApaisado.isSelected();
		Integer hojasUti = Integer.parseInt(txtCantidadDeHojasUtilizadas.getText());
		Integer cantEntr = Integer.parseInt(txtCantidadAEntregar.getText());
		Integer cliente = Cliente.getId_cliente((String) cboCliente.getSelectedItem());

		
		
		//Se da de alta una nueva OT
		Orden_Trabajo ot1= new Orden_Trabajo(TipoProd, cliente, fechaCon, fechaProm, txtNombreOT.getText(), txtDescripcion.getText(),cantEntr,cantImp,ancho,alto,apaisado,"Pendiente",hojasUti);
		ot1.Alta();
		
		/*//Se obtienen los valores guardados en la tabla Orden de ejecucion para crear filas en la tabla procesos_x_orden_trabajo de la BD
		Integer cantFilas = tablaOrdenEjecucion.getRowCount();
		Integer id_OT = Integer.parseInt(this.txtNro.getText());
		for (int i = 0; i < cantFilas; i++) 
		{
			Integer id_Proveedor = Proveedor.getId_Proveedor(tablaOrdenEjecucion.getValueAt(i, 2).toString()); ;
			Procesos_x_OT e = new Procesos_x_OT(id_OT,false,id_Proveedor,tablaElementos.getValueAt(i, 3).toString());
			e.Alta();
		}*/
		
		//Se obtienen los valores guardados en la tabla Elementos para crear filas en la tabla Elemento de la BD
		Integer cantFilas = tablaElementos.getRowCount();
		Integer id_OT = Integer.parseInt(this.txtNro.getText());
		for (int i = 0; i < cantFilas; i++) 
		{
			Elemento e = new Elemento(id_OT,tablaElementos.getValueAt(i, 0).toString(),Integer.parseInt(tablaElementos.getValueAt(i, 1).toString()));
			e.Alta();
		}
		
		//Se obtienen los valores guardados en la tabla Materiales para crear filas en la tabla Materiales de la BD
		Integer id_Elem = Elemento.getMaxId_elemento();
		for (int i = 0; i < cantFilas; i++) 
		{
			//Busco los FK para la tabla materiales de BD
			Integer id_for = Formato_Papel.getId_Formato(tablaMateriales.getValueAt(i, 3).toString());
			Integer id_var = Variante.getId_Variante(tablaMateriales.getValueAt(i, 4).toString());
			Integer id_cal = Calidad.getId_Calidad(tablaMateriales.getValueAt(i, 5).toString());
			
			//Obtengo los datos de lal tabla materiales necesarios para calcular los Pliegos Netos 
			Integer cantElemento = Integer.parseInt(tablaMateriales.getValueAt(i, 1).toString());
			Integer posesXpliego = Integer.parseInt(tablaMateriales.getValueAt(i, 7).toString());
			Integer totalPliegosNetos = (cantEntr * cantElemento) / posesXpliego; 
			tablaMateriales.setValueAt(totalPliegosNetos, i, 10); 
			
			//Obtengo los datos de lal tabla materiales necesarios para calcular la cantidad de hojas
			Integer pliegosNetos = Integer.parseInt(tablaMateriales.getValueAt(i, 10).toString());
			Integer pliegosEnDemasia = Integer.parseInt(tablaMateriales.getValueAt(i, 6).toString());
			Integer pliegosXhoja = Integer.parseInt(tablaMateriales.getValueAt(i, 8).toString());
			
			Integer hojas = (pliegosEnDemasia + pliegosNetos) / pliegosXhoja;
			tablaMateriales.setValueAt(hojas, i, 9); 
			
			//Se da de alta la tabla de materiales con todos los datos ingresados por el usuario.
			Materiales m = new Materiales(id_Elem,Integer.parseInt(tablaMateriales.getValueAt(i, 2).toString()), 
					id_for,id_var,id_cal,pliegosEnDemasia,posesXpliego,pliegosXhoja,
					Integer.parseInt(tablaMateriales.getValueAt(i, 9).toString()),pliegosNetos);
			m.Alta();
		}
		
	}
	
	static String dameNumeroMes(String mes)
	{
		if(mes == "Enero")
		{
			return "01";
		}
		else if(mes == "Febrero")
		{
			return "02";
		}
		else if(mes == "Marzo")
		{
			return "03";
		}
		else if(mes == "Abril")
		{
			return "04";
		}else if(mes == "Mayo")
		{
			return "05";
		}
		else if(mes=="Junio")
		{
			return "06";
		}
		else if(mes=="Julio")
		{
			return "07";
		}
		else if(mes=="Agosto")
		{
			return "08";
		}
		else if(mes=="Septiembre")
		{
			return "09";
		}
		else if(mes=="Octubre")
		{
			return "10";
		}
		else if(mes=="Noviembre")
		{
			return "11";
		}
		else
		{
			return "12";
		}
	}
	
	private void llenarMateriales(){
		JScrollPane spMateriales = new JScrollPane();
		spMateriales.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMateriales.setBounds(10, 11, 615, 228);
		panMateriales.add(spMateriales);
		
		JTable tableMateriales = new JTable();
		tableMateriales.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tableMateriales.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tableMateriales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableMateriales.setModel(new DefaultTableModel(
			new Object[][] 
					{
				
					{"asldaskl", null, null, null, null, null, null, null, null, null, null},{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] 
					{
				"Elemento", "Cantidad", "Calidad", "Variante", "Gramaje", "Formato", "Poses x Pliego", "Pliegos en Demasia", "Pliegos x hoja", "Hojas", "Pliegos Netos"
			}
		) {
			Class[] columnTypes = new Class[] 
					{
				String.class, Integer.class, String.class, String.class, Integer.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
					{
				true, true, true, true, true, true, true, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		tableMateriales.getColumnModel().getColumn(4).setPreferredWidth(56);
		tableMateriales.getColumnModel().getColumn(6).setPreferredWidth(83);
		tableMateriales.getColumnModel().getColumn(7).setPreferredWidth(105);
		tableMateriales.getColumnModel().getColumn(8).setPreferredWidth(95);
		spMateriales.setViewportView(tableMateriales);
		
		
		// Valores para el combo
		String calidades[] = Calidad.getCalidades();
		TableColumn columnaCalidad = tableMateriales.getColumnModel().getColumn(2);
		columnaCalidad.setCellEditor(new MyComboBoxEditor(calidades));
		
		// Valores para el combo
		String variantes[] = Variante.getVariantes(); 
		TableColumn columnaVariante = tableMateriales.getColumnModel().getColumn(3);
		columnaVariante.setCellEditor(new MyComboBoxEditor(variantes));
		
		// Valores para el combo
		String formatos[] = Formato_Papel.getFormatos();
		TableColumn columnaFormato = tableMateriales.getColumnModel().getColumn(5);
		columnaFormato.setCellEditor(new MyComboBoxEditor(formatos));
	}
	

	void txtClear () 
	{
		txtNombreOT.setText ("");
		txtDescripcion.setText ("");
		txtTipoProducto.setText ("");
		txtCantidadDeHojasUtilizadas.setText ("0");
		txtAncho.setText("0");
		txtAlto.setText("0");
		txtCantidadAEntregar.setText("1");
		txtPreimpresion.setText("0");
		txtCantidadDeHojasUtilizadas.setText("0");
		chbApaisado.setSelected(false);
	}
	
	JTextField getTxtNro()
	{
		return this.txtNro;
	}
	
	JTextField getTipoProducto()
	{
		return this.txtTipoProducto;
	}
	
	JComboBox getCboMes()
	{
		return this.cboMes;
	}
	
	JComboBox getCboDia()
	{
		return this.cboDia;
	}
	
	JComboBox getCboAnio()
	{
		return this.cboAnio;
	}
	
	JComboBox getCboMes2()
	{
		return this.cboMes2;
	}
	
	JComboBox getCboDia2()
	{
		return this.cboDia2;
	}
	
	JComboBox getCboAnio2()
	{
		return this.cboAnio2;
	}
	
	JTextField getTxtNombreOT()
	{
		return this.txtNombreOT;
	}
	
	JComboBox getEstado()
	{
		return this.cboEstado_1;
	}
	
	JTextField getTxtDescripcion()
	{
		return this.txtDescripcion;
	}

	JTextField getTxtAncho()
	{
		return this.txtAncho;
	}
	
	JTextField getTxtAlto()
	{
		return this.txtAlto;
	}
	
	JCheckBox getChbApaisado()
	{
		return this.chbApaisado;
	}
	
	JTextField getTxtTipoProducto()
	{
		return this.txtTipoProducto;
	}
	
	JTextField getTxtCantidadAEntregar()
	{
		return this.txtCantidadAEntregar;
	}
	
	JTextField getTxtPreimpresion()
	{
		return this.txtPreimpresion;
	}
	
	JTextField getTxtCantidadDeHojasUtilizadas()
	{
		return this.txtCantidadDeHojasUtilizadas;
	}
	
	JTable getTablaElementos()
	{
		return this.tablaElementos;
	}

	public JComboBox getCliente() 
	{
		return this.cboCliente;
	}
	
	public JTable getTablaMateriales()
	{
		return this.tablaElementos;
	}
	
	public JTable getTablaOrdenTrabajo()
	{
		return this.tablaOrdenDeEjecucion;
	}
}	