package vista_Controlador;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import Modelo.Calidad;
import Modelo.Cliente;
import Modelo.ConexionDB;
import Modelo.Egreso_Stock;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Proceso;
import Modelo.Procesos_x_OT;
import Modelo.Proveedor;
import Modelo.Stock;
import Modelo.Variante;
import Modelo.Remanente_sc_ot;

@SuppressWarnings("serial")

public class OrdenDeTrabajo extends JInternalFrame implements ActionListener, Config
{
	private JPanel jpOrdenDeTrabajo = new JPanel();
	
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
		lbPreimpresion;
	
	private JTextField 
		txtNro, 
		txtNombreOT,
		txtDescripcion,
		txtCantidadAEntregar,
		txtPreimpresion;
	
	private JComboBox<String> 
		cboCliente,
		cboMes, 
		cboDia, 
		cboAnio,
		cboMes2, 
		cboDia2, 
		cboAnio2;
	private JComboBox<String> cboEstado_1;
	
	public JButton
		btnLimpiarOT,
		btnGuardar, 
		btnCancelar;
	
	private JCheckBox
		chbApaisado;
	
	private JTabbedPane
		tabSecciones;
	
	
	String Clientes[] = Cliente.getClientes(); 
	
	
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
		//"Cerrada" 
	};
	
	private JTextField txtTipoProducto;
	private JTable tablaElementos;
	private JPanel panMateriales;
	private JScrollPane spMateriales;
	private JScrollPane spListaDeProcesos;
	private JTable tablaMateriales;
	private JScrollPane spOrdenEjecucion;
	private JTable tablaOrdenDeEjecucion;
	private JButton btnConfirmarSeleccion;
	private JButton btnAgregarFila;
	private JButton btnBorrarFila;
	JButton btnAlmacenar;

	private JFormattedTextField txtAncho, txtAlto;
	private JButton btnImprimirReporte;
	private JButton btnUp, btnDown;
	private JTextField fechaHoraCierreOT;
	private JLabel lblFechaCierre;

	OrdenDeTrabajo()
	{	
		super ("Orden de Trabajo (OT)", false, true, false, true);
		
		setSize (928, 587);
		
		jpOrdenDeTrabajo.setBounds (0, 0, 500, 115);

		lbNro = new JLabel ("Número:");
		lbNro.setBounds(10, 11, 75, 25);
		lbNro.setForeground (Color.black);
		
	    lbCliente = new JLabel ("Cliente:");
	    lbCliente.setBounds(620, 54, 55, 25);
		lbCliente.setForeground (Color.black);
	    
		lbFechaC = new JLabel ("<html>Fecha de<br>confecci\u00F3n:</html>");
		lbFechaC.setHorizontalAlignment(SwingConstants.LEFT);
		lbFechaC.setBounds(312, 52, 75, 30);
		lbFechaC.setForeground (Color.black);
				
		lbFechaP = new JLabel ("<html>Fecha <br>prometida:</html>");
		lbFechaP.setBounds(10, 52, 75, 30);
		lbFechaP.setForeground (Color.black);
		
		lbNombreOT = new JLabel ("Nombre OT:");
		lbNombreOT.setBounds(312, 11, 75, 25);
		lbNombreOT.setForeground (Color.black);
		
		lbEstado = new JLabel ("Estado:");
		lbEstado.setBounds(620, 11, 55, 25);
		lbEstado.setForeground (Color.black);
		
		lbDescripcion = new JLabel ("Descripci\u00F3n:");
		lbDescripcion.setBounds(10, 97, 75, 25);
		lbDescripcion.setForeground (Color.black);

		
		String maxIdOT = Metodos.EnteroAFactura(Orden_Trabajo.getUltOT());
		
		txtNro = new JTextField (maxIdOT);
		txtNro.setEditable(false);
		txtNro.setForeground(Color.RED);
		txtNro.setFont(new Font("Arial", Font.BOLD, 11));
		txtNro.setFocusable(false);
		txtNro.setBounds(85, 11, 210, 25);
		txtNro.setHorizontalAlignment (JTextField.LEFT);
		
		cboCliente = new JComboBox (Clientes);
		cboCliente.setBounds(681, 54, 224, 25);
		
		txtNombreOT = new JTextField ();
		txtNombreOT.setBounds(387, 11, 210, 25);
		txtNombreOT.setHorizontalAlignment (JTextField.LEFT);
		txtNombreOT.addKeyListener
		(
			new KeyListener()
			{
				public void keyTyped(KeyEvent e)
				{
					if (txtNombreOT.getText().length()== 50 || e.getKeyChar ()=='\'' || e.getKeyChar ()=='-')
						e.consume();
				}
				public void keyPressed(KeyEvent arg0) 
				{
				}
				public void keyReleased(KeyEvent arg0)
				{
				}
			}
		);
		txtNombreOT.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		
		txtDescripcion = new JTextField ();
		txtDescripcion.setBounds(85, 97, 819, 25);
		txtDescripcion.setHorizontalAlignment (JTextField.LEFT);
		txtDescripcion.addKeyListener
		(
			new KeyListener()
			{
				public void keyTyped(KeyEvent e)
				{
					if (txtDescripcion.getText().length()== 100 || e.getKeyChar ()=='\'' || e.getKeyChar ()=='-')
						e.consume();
				}
				public void keyPressed(KeyEvent arg0) 
				{
				}
				public void keyReleased(KeyEvent arg0)
				{
				}
			}
		);
		txtDescripcion.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtDescripcion.getText().replace('\'', ' ');
		
		lbAncho = new JLabel ("Ancho:");
		lbAncho.setHorizontalAlignment(SwingConstants.LEFT);
		lbAncho.setFont(new Font("Arial", Font.ITALIC, 11));
		lbAncho.setBounds(117, 137, 50, 25);
		lbAncho.setForeground (Color.black);
		
		try
        {
            MaskFormatter mascara = new MaskFormatter("###.##");
            txtAlto = new JFormattedTextField(mascara);
            txtAlto.setValue("000.00");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
		
	    txtAlto.setBounds(338, 137, 100, 25);
		
		lbAlto = new JLabel ("Alto:");
		lbAlto.setHorizontalAlignment(SwingConstants.LEFT);
		lbAlto.setFont(new Font("Arial", Font.ITALIC, 11));
		lbAlto.setBounds(288, 137, 50, 25);
		lbAlto.setForeground (Color.black);
		
		try
        {
            MaskFormatter mascara = new MaskFormatter("###.##");
            txtAncho = new JFormattedTextField(mascara);
            txtAncho.setValue("000.00");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
		
	    txtAncho.setBounds(170, 137, 100, 25);
		
		
		chbApaisado = new JCheckBox ("Apaisado");
		chbApaisado.setFont(new Font("Arial", Font.ITALIC, 11));
		chbApaisado.setBounds(464, 137, 80, 25);
		
		lbTipoDeProducto = new JLabel ("<html>Tipo de<BR> Producto:</html>");
		lbTipoDeProducto.setBounds(10, 180, 75, 30);
		lbTipoDeProducto.setForeground (Color.black);
		
		lbCantidadAEntregar = new JLabel ("<html>Cantidad\r\n a <BR>entregar:</html>");
		lbCantidadAEntregar.setBounds(610, 135, 65, 30);
		lbCantidadAEntregar.setForeground (Color.black);
		
		txtCantidadAEntregar = new JTextField ("1");
		txtCantidadAEntregar.setBounds(681, 137, 224, 25);
		txtCantidadAEntregar.setHorizontalAlignment (JTextField.LEFT);
		
		lbPreimpresion = new JLabel ("Preimpresi\u00F3n (Cantidad de planchas): ");
		lbPreimpresion.setBounds(317, 180, 215, 30);
		lbPreimpresion.setForeground (Color.black);
		
		txtPreimpresion = new JTextField ("");
		txtPreimpresion.setBounds(542, 185, 131, 25);
		txtPreimpresion.setHorizontalAlignment (JTextField.LEFT);
		txtPreimpresion.addKeyListener
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))) || txtPreimpresion.getText().length() == 9) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		txtPreimpresion.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		tabSecciones = new JTabbedPane();
		tabSecciones.setBounds(10, 228, 895, 275);
		txtCantidadAEntregar.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtCantidadAEntregar.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))) || txtCantidadAEntregar.getText().length()== 9)
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		
		cboMes = new JComboBox<String>(); //Comentar esta línea si quieren utilizar el WB
		cboMes.getModel().setSelectedItem(Metodos.dameMes(Metodos.getMesActual()));
		cboMes.setBounds(435, 54, 97, 25);
		cboMes.setEnabled(false);
		
		cboDia = new JComboBox<String> ();
		cboDia.getModel().setSelectedItem(Metodos.getDiaDeHoy());
		cboDia.setEnabled(false);
		cboDia.setBounds(387, 54, 48, 25);
		
		cboAnio = new JComboBox<String> ();
		cboAnio.getModel().setSelectedItem(Metodos.getAnioActual());
		cboAnio.setEnabled(false);
		cboAnio.setBounds(532, 54, 65, 25);
		
		cboEstado_1 = new JComboBox (Estados);	
		//cboEstado_1 = new JComboBox ();
		cboEstado_1.setToolTipText("Estado de la orden de trabajo");
		cboEstado_1.setBounds(681, 11, 224, 25);
		cboEstado_1.setEnabled(false);
		String dias="";
		for (int i = 1; i <= 31; i++) 
		{
			if(i<10){
				dias = "0" + i;
			}else{
				dias = "" + i;	
			}
			cboDia.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio.addItem (anios);
		}
		
		cboMes2 = new JComboBox (Meses);
		cboMes2.setBounds(85, 54, 97, 25);
		
		cboDia2 = new JComboBox ();
		cboDia2.setBounds(182, 54, 48, 25);
		
		cboAnio2 = new JComboBox ();
		cboAnio2.setBounds(230, 54, 65, 25);
		for (int i = 1; i <= 31; i++) 
		{
			if(i<10){
				dias = "0" + i;
			}else{
				dias = "" + i;	
			}
			cboDia2.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio2.addItem (anios);
		}
				
		cboDia2.setSelectedItem(Metodos.getDiaDeHoy());
		cboMes2.setSelectedItem(Metodos.dameMes(Metodos.getMesActual()));
		cboAnio2.setSelectedItem(Metodos.getAnioActual());

		
		btnLimpiarOT = new JButton("Limpiar", new ImageIcon ("Imagenes/limpiar.png"));
		btnLimpiarOT.setBounds(10, 514, 121, 30);
		btnLimpiarOT.addActionListener (this);
					
		btnGuardar = new JButton ("Confirmar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnGuardar.setBounds(655, 514, 120, 30);
		btnGuardar.addActionListener (this);
		
		btnCancelar = new JButton ("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCancelar.setBounds(785, 514, 120, 30);
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
		jpOrdenDeTrabajo.add (txtAncho);
		jpOrdenDeTrabajo.add (txtAlto);
		jpOrdenDeTrabajo.add (lbTipoDeProducto);
		jpOrdenDeTrabajo.add (lbCantidadAEntregar);
		jpOrdenDeTrabajo.add (lbPreimpresion);
		jpOrdenDeTrabajo.add (txtPreimpresion);
		jpOrdenDeTrabajo.add (txtCantidadAEntregar);
		jpOrdenDeTrabajo.add (tabSecciones);
		jpOrdenDeTrabajo.add (btnLimpiarOT);
		jpOrdenDeTrabajo.add (btnGuardar);
		jpOrdenDeTrabajo.add (btnCancelar);
		
		getContentPane().add (jpOrdenDeTrabajo);

		//setVisible (true);
		
		
		/*
		 * 
		 * Para la pestaña de la Seccion Elementos
		 * 
		 */
		
		JPanel panElementos = new JPanel();
		panElementos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panElementos.setBounds(0, 0, 870, 250);
			
		panElementos.setLayout(null);
		
		tabSecciones.addTab
		(
			"Elementos",
			new ImageIcon ("Imagenes/registrar.png"), 
			panElementos,
	        "Elementos del producto"
		);
		
		JScrollPane spElementos = new JScrollPane();
		spElementos.setBounds(10, 11, 870, 184);
		panElementos.add(spElementos);

		tablaElementos = new JTable();
		tablaElementos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Elemento del producto", "Cantidad", "Hojas Previstas", "Hojas Utilizadas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaElementos.getColumnModel().getColumn(0).setPreferredWidth(124);
		tablaElementos.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaElementos.getColumnModel().getColumn(2).setMaxWidth(200);
		tablaElementos.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaElementos.getColumnModel().getColumn(3).setMaxWidth(200);
		spElementos.setViewportView(tablaElementos);
		tablaElementos.getTableHeader().setReorderingAllowed(false);
		
		btnAgregarFila = new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));
		btnAgregarFila.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DefaultTableModel temp = (DefaultTableModel) tablaElementos.getModel();
				Object nuevo[]= {"","",""};
				temp.addRow(nuevo);
			}
		});
		btnAgregarFila.setBounds(10, 206,120, 30);
		panElementos.add(btnAgregarFila);
		
		btnBorrarFila = new JButton("Borrar", new ImageIcon ("Imagenes/restar.png"));
		btnBorrarFila.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{	
				DefaultTableModel temp = (DefaultTableModel) tablaElementos.getModel();
				if(temp.getRowCount()>0){
					temp.removeRow(tablaElementos.getSelectedRow());	
				}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
				}
			}
		});
		btnBorrarFila .setBounds(140, 206,120,30);
		panElementos.add(btnBorrarFila);
		
		
		
		btnAlmacenar = new JButton("Almacenar", new ImageIcon ("Imagenes/ok.png"));
		btnAlmacenar.addActionListener(new ActionListener() 
		{
			//Evento que ocurre cuando se presiona el boton almacenar en la seccion elementos
			public void actionPerformed(ActionEvent e) 
			{
				Metodos.borrarFilas((DefaultTableModel) tablaMateriales
						.getModel());

				if (ExcedeLargoElemento()) {

					JOptionPane
							.showMessageDialog(
									null,
									"La longitud máxima de un elemento son 50 caracteres.\nNo exceda el límite, Por favor",
									qTITULO + " - Campo vacío",
									JOptionPane.WARNING_MESSAGE);

				} else if(txtCantidadAEntregar.getText().equals("") || Integer.parseInt(txtCantidadAEntregar.getText()) <= 0 ){
					
					JOptionPane
					.showMessageDialog(
							null,
							"Debe ingresar la cantidad a entregar",
							qTITULO + " - Campo vacío",
							JOptionPane.WARNING_MESSAGE);
					txtCantidadAEntregar.requestFocus();
					
				}else {
					
					txtCantidadAEntregar.setEnabled(false);
					
					Integer cantFilas = tablaElementos.getRowCount();

					DefaultTableModel temp = (DefaultTableModel) tablaMateriales
							.getModel();
					// cuenta la cantidad de filas no vacias que se agregan
					Integer c = 0;
					try {
						for (int i = 0; i < cantFilas; i++) {
							if (!tablaElementos.getValueAt(i, 0).toString()
									.equals("")
									&& !tablaElementos.getValueAt(i, 1).equals(
											"")) {
								Integer cantAEnretXCantElemento=(Integer) tablaElementos
										.getValueAt(i, 1) * Integer.parseInt(txtCantidadAEntregar.getText());
								Object nuevaFila[] = {
										tablaElementos.getValueAt(i, 0),
										cantAEnretXCantElemento,
										"", "", "", "", "", "", "", "", "" };
								temp.addRow(nuevaFila);
								c++;
							}
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null,
								"Debe ingresar un elemento y una cantidad.");
					}
					if (c == cantFilas && c != 0) {
						JOptionPane
								.showMessageDialog(null,
										"Se almaceno correctamente.Vaya a la seccion MATERIALES.");
					} else {
						JOptionPane.showMessageDialog(null,
								"Debe ingresar un elemento y una cantidad.");

					}

					// Valores para el combo
					String calidades[] = Calidad.getCalidades();
					TableColumn columnaCalidad = tablaMateriales
							.getColumnModel().getColumn(3);// table es la
															// JTable, ponele
															// que la col 0
															// es la del combo.
					columnaCalidad
							.setCellEditor(new MyComboBoxEditor(calidades));

					// Valores para el combo
					String variantes[] = Variante.getVariantes();
					TableColumn columnaVariante = tablaMateriales
							.getColumnModel().getColumn(5);// table es la
															// JTable, ponele
															// que la col 0
															// es la del combo.
					columnaVariante.setCellEditor(new MyComboBoxEditor(
							variantes));

					// Valores para el combo
					String formatos[] = Formato_Papel.getFormatos();
					TableColumn columnaFormato = tablaMateriales
							.getColumnModel().getColumn(4);// table es la
															// JTable, ponele
															// que la col 0
															// es la del combo.
					columnaFormato
							.setCellEditor(new MyComboBoxEditor(formatos));
				}
			}
		});
		
		btnAlmacenar.setBounds(760, 206,120, 30);
		panElementos.add(btnAlmacenar);
		tabSecciones.setEnabledAt(0, true);
		tabSecciones.setDisabledIconAt(0, null);
		tabSecciones.setMnemonicAt(0, KeyEvent.VK_E);
		
		
		
		/*
		 * 
		 * Para la pestaña Materiales
		 * 
		 */
		
		panMateriales = new JPanel();
		
		
		
		panMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabSecciones.addTab("Materiales", new ImageIcon ("Imagenes/registrar.png"), panMateriales, "Materiales");
		tabSecciones.setEnabledAt(1, true);
		
		panMateriales.setLayout(null);
		
		spMateriales = new JScrollPane();
		spMateriales.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMateriales.setBounds(10, 11, 870, 192);
		panMateriales.add(spMateriales);
		
		tablaMateriales = new JTable();
		tablaMateriales.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Elemento", "Cantidad", "Gramaje", "Calidad", "Formato", "Variante", "Pliegos en demasia", "Poses x Pliego", "Pliegos x Hoja", "Pliegos Netos", "Hojas" 
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Object.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true, true, true, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaMateriales.getColumnModel().getColumn(0).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(0).setPreferredWidth(95);
		tablaMateriales.getColumnModel().getColumn(0).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(1).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(1).setPreferredWidth(63);
		tablaMateriales.getColumnModel().getColumn(1).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(2).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(2).setPreferredWidth(59);
		tablaMateriales.getColumnModel().getColumn(2).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(3).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(3).setPreferredWidth(140);
		tablaMateriales.getColumnModel().getColumn(3).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(4).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(4).setPreferredWidth(77);
		tablaMateriales.getColumnModel().getColumn(4).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(5).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(5).setPreferredWidth(115);
		tablaMateriales.getColumnModel().getColumn(5).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(6).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(6).setPreferredWidth(128);
		tablaMateriales.getColumnModel().getColumn(6).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(7).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(7).setPreferredWidth(100);
		tablaMateriales.getColumnModel().getColumn(7).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(8).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(8).setPreferredWidth(90);
		tablaMateriales.getColumnModel().getColumn(8).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(9).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(9).setPreferredWidth(88);
		tablaMateriales.getColumnModel().getColumn(9).setMinWidth(30);
		tablaMateriales.getColumnModel().getColumn(10).setResizable(true);
		tablaMateriales.getColumnModel().getColumn(10).setPreferredWidth(80);
		tablaMateriales.getColumnModel().getColumn(10).setMinWidth(30);
		spMateriales.setViewportView(tablaMateriales);
		tablaMateriales.getTableHeader().setReorderingAllowed(false);
		
		
		TableColumnModel tcm = tablaMateriales.getColumnModel();
		tcm.addColumnModelListener(new TableColumnModelListener()
		{
			@Override
			public void columnSelectionChanged(ListSelectionEvent arg0) 
			{
				calcularPliegoYhojas();
			}
			
			@Override
			public void columnRemoved(TableColumnModelEvent arg0){}
			
			@Override
			public void columnMoved(TableColumnModelEvent arg0) {}
			
			@Override
			public void columnMarginChanged(ChangeEvent arg0) {}
			
			@Override
			public void columnAdded(TableColumnModelEvent arg0){}
		});
		
		
		spMateriales.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMateriales.setBounds(10, 11, 870, 228);
		panMateriales.add(spMateriales);
		
		tablaMateriales.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaMateriales.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaMateriales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
		JPanel panOrdenEjecucion = new JPanel();
		panOrdenEjecucion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panOrdenEjecucion.setLayout(null);
        
		tabSecciones.addTab
		(
			"Orden de ejecución",
			new ImageIcon ("Imagenes/registrar.png"), 
			panOrdenEjecucion,
	        "Listado de tareas o procesos"
		);
	
		
		/*
		 * Seccion Procesos
		 */

		spListaDeProcesos = new JScrollPane();
		spListaDeProcesos.setBounds(10, 11, 248, 184);
		panOrdenEjecucion.add(spListaDeProcesos);
		
		final JList listaProcesos = new JList();
		spListaDeProcesos.setViewportView(listaProcesos);
		listaProcesos.setModel(new AbstractListModel() 
		{
			String[] values = new String[] {};
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		listaProcesos.setToolTipText("Para seleccionar más de un proceso, mantenga presionado Ctrl mientras elige los procesos");
		String proveedores[] = Proceso.getProcesos();
		DefaultListModel modeloList = new DefaultListModel();
		for(int i = 0; i < proveedores.length; i ++)
		{
			modeloList.addElement(proveedores[i]);
		}
		listaProcesos.setModel(modeloList);
		
		spOrdenEjecucion = new JScrollPane();
		spOrdenEjecucion.setBounds(268, 12, 612, 184);
		panOrdenEjecucion.add(spOrdenEjecucion);
		
		tablaOrdenDeEjecucion = new JTable();
		tablaOrdenDeEjecucion.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"Proceso", "Tercerizada", "Proveedor", "Observaciones", "Cumplida"}) 
		{
			Class[] columnTypes = new Class[] 
			{
				String.class, Boolean.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row, int column) 
			{
				if(column==2 || column==3){
					return isTercerizadaSelected(row);
				}else if(column==1){
					return true;
				}
				return false;
			}
			private boolean isTercerizadaSelected(int row) {
				for(int i=0;i<tablaOrdenDeEjecucion.getRowCount();i++){
					if(!(Boolean) tablaOrdenDeEjecucion.getValueAt(i, 1)){
						tablaOrdenDeEjecucion.setValueAt("", i, 2);
						tablaOrdenDeEjecucion.setValueAt("", i, 3);
					}
				}
				return (Boolean) tablaOrdenDeEjecucion.getValueAt(row, 1);
			}
		});
		tablaOrdenDeEjecucion.getColumnModel().getColumn(0).setPreferredWidth(148);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(1).setPreferredWidth(77);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(2).setPreferredWidth(170);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(3).setPreferredWidth(202);
		tablaOrdenDeEjecucion.getColumnModel().getColumn(4).setPreferredWidth(65);
		spOrdenEjecucion.setViewportView(tablaOrdenDeEjecucion);
		tablaOrdenDeEjecucion.getTableHeader().setReorderingAllowed(false);
		tablaOrdenDeEjecucion.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaOrdenDeEjecucion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaOrdenDeEjecucion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaOrdenDeEjecucion.setBounds(268, 12, 612, 224);
		tablaOrdenDeEjecucion.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		

		
		btnConfirmarSeleccion = new JButton("Confirmar", new ImageIcon ("Imagenes/ok.png"));
		btnConfirmarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int selected[] = listaProcesos.getSelectedIndices();

				DefaultTableModel temp = (DefaultTableModel) tablaOrdenDeEjecucion.getModel();
				
				while(temp.getRowCount()>0){
					temp.removeRow(temp.getRowCount()-1);
				}
				
				for (int i=0; i < selected.length; i++) 
				{
					Object nuevo[]= {"",false,"","",false};
					temp.addRow(nuevo);
					tablaOrdenDeEjecucion.setValueAt(listaProcesos.getModel().getElementAt(selected[i]), i, 0);
				}
				
				// Valores para el combo
				String proveedores[] = Proveedor.getProveedores();
				TableColumn columnaProveedor = tablaOrdenDeEjecucion.getColumnModel().getColumn(2);
				columnaProveedor.setCellEditor(new MyComboBoxEditor(proveedores));
			}
		});
		btnConfirmarSeleccion.setBounds(67, 206, 120, 30);
		panOrdenEjecucion.add(btnConfirmarSeleccion);
		
		btnUp = new JButton(new ImageIcon("Imagenes/Up_Button.png"));
		btnUp.setPressedIcon(new ImageIcon("Imagenes/Up_Button_pressed.png"));

		//Subir una fila
		btnUp.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent arg0) 
			{
				int filaSelec=tablaOrdenDeEjecucion.getSelectedRow();
				try
				{	
					DefaultTableModel tempOE = (DefaultTableModel) tablaOrdenDeEjecucion.getModel();
					if(tempOE.getRowCount()>0)
					{
						//int filaSelec=tablaOrdenDeEjecucion.getSelectedRow();
						if(filaSelec==-1)
						{
							JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
						}
						else if(filaSelec==0)
						{
							JOptionPane.showMessageDialog(null,"Este proceso ya es el Primero de la lista");
						}
						else
						{
							tempOE.moveRow(filaSelec, filaSelec, filaSelec-1);
							//tablaO .setSelectionInterval(filaSelec-1,filaSelec-1);
							tablaOrdenDeEjecucion.getSelectionModel().setSelectionInterval(filaSelec-1,filaSelec-1);
						
						}
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"error!");
				}				
			}
		}
		);
		btnUp.setSelectedIcon(new ImageIcon("Imagenes/Up_Button_Selected.png"));
		btnUp.setBounds(288, 206, 35, 35);
		panOrdenEjecucion.add(btnUp);
		
		btnDown = new JButton(new ImageIcon("Imagenes/Down_Button.png"));
		btnDown.setPressedIcon(new ImageIcon("Imagenes/Down_Button_pressed.png"));
		btnDown.setSelectedIcon(new ImageIcon("Imagenes/Down_Button_Selected.png"));

		
		//Bajar una fila
		btnDown.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int filaSelec = tablaOrdenDeEjecucion.getSelectedRow();
				try 
				{
					DefaultTableModel tempOE = (DefaultTableModel) tablaOrdenDeEjecucion
							.getModel();
					if (tempOE.getRowCount() > 0) 
					{
						//int filaSelec = tablaOrdenDeEjecucion.getSelectedRow();
						if (filaSelec == -1) 
						{
							JOptionPane.showMessageDialog(null,
									"Debe seleccionar una fila");
						} 
						else if (filaSelec == tempOE.getRowCount() - 1) 
						{
							JOptionPane.showMessageDialog(null,
									"Este proceso ya es el Ultimo de la lista");
						} 
						else 
						{
							tempOE.moveRow(filaSelec, filaSelec, filaSelec + 1);
							tablaOrdenDeEjecucion.getSelectionModel().setSelectionInterval(filaSelec+1,filaSelec+1);
						}
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) 
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "error!");
				}
			}
		});
		btnDown.setBounds(333, 207, 35, 35);
		panOrdenEjecucion.add(btnDown);
		
		//controlar que si se hace click en tercerizada, se bloqueen los campos proveedor y observaciones
		tablaOrdenDeEjecucion.addMouseListener(new MouseAdapter() 
 {
			public void mouseClicked(MouseEvent e) 
			{
				int fila = tablaOrdenDeEjecucion.rowAtPoint(e.getPoint());
				int columna = tablaOrdenDeEjecucion.columnAtPoint(e.getPoint());

				for (int i = 0; i < tablaOrdenDeEjecucion.getRowCount(); i++) 
				{
					if (columna == 1)
					{
						if (!(Boolean) tablaOrdenDeEjecucion.getValueAt(fila,
								columna)) 
						{
							tablaOrdenDeEjecucion.setValueAt("", fila, 2);
							tablaOrdenDeEjecucion.setValueAt("", fila, 3);
						}
					}
				}
			}
		});
		
		
		tabSecciones.setMnemonicAt(1, KeyEvent.VK_O);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setVisible(true);
		
		JLabel lblMedidaFinal = new JLabel("Medida Final");
		lblMedidaFinal.setBounds(10, 137, 75, 25);
		jpOrdenDeTrabajo.add(lblMedidaFinal);
		
		txtTipoProducto = new JTextField("");
		txtTipoProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtTipoProducto.setBounds(85, 185, 210, 25);
		txtTipoProducto.addKeyListener
		(
			new KeyListener()
			{
				public void keyTyped(KeyEvent e)
				{
					if (txtTipoProducto.getText().length()== 50 || e.getKeyChar ()=='\'' || e.getKeyChar ()=='-')
						e.consume();
				}
				public void keyPressed(KeyEvent arg0) 
				{
				}
				public void keyReleased(KeyEvent arg0)
				{
				}
			}
		);
		txtTipoProducto.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		jpOrdenDeTrabajo.add(txtTipoProducto);
		txtClear();
		
		btnImprimirReporte = new JButton("Imprimir Reporte", new ImageIcon("Imagenes/imprimir.png"));
		btnImprimirReporte.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				elegirReporteAImprimir();
			}
		});
		btnImprimirReporte.setBounds(504, 514, 141, 30);
		jpOrdenDeTrabajo.add(btnImprimirReporte);
		btnImprimirReporte.setEnabled(false);
		
		fechaHoraCierreOT = new JTextField("");
		fechaHoraCierreOT.setBounds(764, 185, 141, 25);
		jpOrdenDeTrabajo.add(fechaHoraCierreOT);
		fechaHoraCierreOT.setEditable(false);
		fechaHoraCierreOT.setVisible(false);
		
		lblFechaCierre = new JLabel("<html>Fecha\r\n<BR>Cierre:</html>");
		lblFechaCierre.setForeground(Color.BLACK);
		lblFechaCierre.setBounds(713, 183, 41, 25);
		jpOrdenDeTrabajo.add(lblFechaCierre);
		lblFechaCierre.setVisible(false);
	}

	//Chequear un poco lo ingresado antes de guardar
	public void actionPerformed (ActionEvent ae) 
	{
		int clave = Metodos.FacturaAEntero(txtNro.getText());
		Object obj = ae.getSource();
		int flag=0;
		String estado= (String) this.cboEstado_1.getSelectedItem();
		String factual=cboDia.getSelectedItem().toString()+"-"+Metodos.dameNumeroMes(cboMes.getSelectedItem().toString())+"-"+cboAnio.getSelectedItem().toString();
		String fprometida=cboDia2.getSelectedItem().toString()+"-"+Metodos.dameNumeroMes(cboMes2.getSelectedItem().toString())+"-"+cboAnio2.getSelectedItem().toString();
		
		if (obj == btnGuardar) 
		{
			ResultSet sqlOT = ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo, estado FROM orden_trabajo WHERE id_orden_trabajo = "+clave);
			
			
			if (sqlOT != null) 
			{
				try 
				{
					while (sqlOT.next()) 
					{
						String sqlEstado = sqlOT.getString("estado");
						// Si esta "pendiente" y se cambia el estado a otro distinto de "pendiente"
						if (sqlEstado.equalsIgnoreCase("Pendiente") && cboEstado_1.getSelectedItem().toString().equalsIgnoreCase("Pendiente"))
						{
							flag = 1;
						}
						else if(sqlEstado.equalsIgnoreCase("En Proceso") && cboEstado_1.getSelectedItem().toString().equalsIgnoreCase("Pendiente")){
							flag=3;//intenta cambiar de 'En proceso' a 'Pendiente'
						}
						// Si esta "cerrada"
						else if (sqlEstado.equalsIgnoreCase("Cerrada"))
						{
							flag = 2;
						}
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			if (flag!=0) //Si no esta "En proceso"
			{
				if (flag==1) //Si esta "Pendiente"
				{
					JOptionPane.showMessageDialog 
					(
						this, 
						"Esta orden ya está 'Pendiente'\nSólo puede cambiar su estado",
						qTITULO + " - Error#01", 
						JOptionPane.ERROR_MESSAGE
						
					);
					flag = 0;
				}
				else if(flag==3){ //intenta cambiar de 'En proceso' a 'Pendiente'
					JOptionPane.showMessageDialog 
					(
						this, 
						"Esta orden se encuentra En Proceso\nSolo puede cambiar su estado a cerrada",
						qTITULO + " - Error#02", 
						JOptionPane.ERROR_MESSAGE
					);
					flag = 0;
				}
				else
				{
					JOptionPane.showMessageDialog 
					(
						this, 
						"Esta orden está cerrada\nNo puede realizar cambios",
						qTITULO + " - Error#02", 
						JOptionPane.ERROR_MESSAGE
					);
					flag = 0;
				}
					
			}
			
			else
			{
				if (txtNombreOT.getText().equals("")) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"Esta orden no tiene nombre asignado",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
					
					txtNombreOT.requestFocus ();
					
				}
				
				else if (txtNombreOT.getText().equalsIgnoreCase("stockear")) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"El nombre asignado a la Orden de Trabajo debe ser distinto.",
						qTITULO + " - Elija otro nombre", 
						JOptionPane.WARNING_MESSAGE
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
						JOptionPane.WARNING_MESSAGE
					);
					
					txtDescripcion.requestFocus ();
					
				}
				else if (txtTipoProducto.getText().equals("")) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"Ingrese el Tipo de producto",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
					
					txtTipoProducto.requestFocus ();
					
				}
				
				else if (txtPreimpresion.getText().equals("")) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"Ingrese cantidad de planchas",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
					
					txtPreimpresion.requestFocus();
				}
				
				else if (txtCantidadAEntregar.getText().equals("")) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"Ingrese cantidad a entregar",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
					txtCantidadAEntregar.requestFocus();
				}
				else if (Double.parseDouble(txtAlto.getText()) == 0) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"El alto no puede ser 0",
						qTITULO + " - Alto nulo", 
						JOptionPane.WARNING_MESSAGE
					);
					txtAlto.requestFocus();
				}
				else if (Double.parseDouble(txtAncho.getText()) == 0) 
				{
					
					JOptionPane.showMessageDialog 
					(
						this, 
						"El ancho no puede ser 0",
						qTITULO + " - Ancho nulo", 
						JOptionPane.WARNING_MESSAGE
					);
					txtAncho.requestFocus();
				}
				else if (elementosIguales()) 
				{	
					JOptionPane.showMessageDialog 
					(
						this, 
						"No puede haber 2 elementos iguales, si necesita más, aumente la cantidad de ese elemento",
						qTITULO + " - Elementos repetidos",
						JOptionPane.WARNING_MESSAGE
					);					
				}
				else if (Metodos.isFechaActualMenorFechaPrometida(factual, fprometida)==false) 
				{
					JOptionPane.showMessageDialog 
					(
						this, 
						"La fecha prometida debe ser mayor a la Fecha actual",
						qTITULO + " - Error en Fecha prometida", 
						JOptionPane.WARNING_MESSAGE
					);
				}
				else if(!materialesOk()){
					JOptionPane.showMessageDialog 
					(
						this, 
						"No puede dejar celdas vacias en la seccion Materiales. Verifique!",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
				}
				else if(!procesosOk()){
					JOptionPane.showMessageDialog 
					(
						this, 
						"Debe seleccionar al menos un proceso en la seccion Orden de Ejecucuión",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
				}
				else if (ExcedeLargoObservacion()) 
				{
					JOptionPane.showMessageDialog 
					(
						this, 
						"La longitud máxima de una observación son 100 caracteres.\nNo exceda el límite, Por favor",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
				}
				
				else if (!proveedorElegido()) 
				{
					JOptionPane.showMessageDialog 
					(
						this, 
						"Debe seleccionar un proveedor",
						qTITULO + " - Campo vacío", 
						JOptionPane.WARNING_MESSAGE
					);
				}

				else if(estado.equalsIgnoreCase("En Proceso"))
				{
					if (estado.equalsIgnoreCase("En Proceso")) {
						Orden_Trabajo.CambiarEstado(clave, "En Proceso");
					}

					// actualizar tareas cumplidas
					ArrayList<Integer> id_proc = this
							.getId_procesosTablaActual();
					Integer cantTrue = 0;
					ArrayList<Integer> proc = new ArrayList<Integer>();
					ArrayList<Boolean> cumplida = new ArrayList<Boolean>();
					for (int i = 0; i < tablaOrdenDeEjecucion.getRowCount(); i++) {
						boolean n = (Boolean) tablaOrdenDeEjecucion.getValueAt(
								i, 4);
						if (n) {
							cantTrue++;
						}
						proc.add(new Integer(i));
						cumplida.add(new Boolean(n));
					}
					// si se marcan como cumplidos todos los procesos
					if (cantTrue == tablaOrdenDeEjecucion.getRowCount()) {
						int reply = JOptionPane
								.showConfirmDialog(
										this,
										"Ha marcada que todas las tareas fueron realizadas,\ndesea cerrar la Orden de Trabajo?",
										qTITULO
												+ " - Cerrando Orden de Trabajo",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE);

						//warning si no uso hojas de stock
						if (reply == JOptionPane.YES_OPTION) {
							if (Orden_Trabajo.getHojasUtilizadas(clave) == 0) {
								int opcion = JOptionPane
										.showConfirmDialog(
												this,
												"Atención! Está a punto de cerrar una Orden De Trabajo para la cual no ha utilizado hojas de stock,\ndesea cerrarla de todas formas?",
												qTITULO
														+ " - Cerrando Orden de Trabajo",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.WARNING_MESSAGE);
								if (opcion == JOptionPane.YES_OPTION) {
									cerrarOT(clave, proc, id_proc, cumplida);
									obj = btnCancelar;
									
								}else{}//nohacenadasieligeno
							}else{//si se sacaron hojas, se cierra la OT
								cerrarOT(clave, proc, id_proc, cumplida);
								obj = btnCancelar;
							}
						}
					} else {// si no se marcaron todos los procesos como
							// cumplidos, guarda los seleccionados
						for (int i = 0; i < proc.size(); i++) {
							Procesos_x_OT.setAvanceOT(clave,
									id_proc.get(proc.get(i)), cumplida.get(i));
						}
						obj = btnCancelar;
					}
				}
				else 
				{
					cargarTablas(); // Cargaría la tabla en memoria
					obj = btnCancelar;
	
				}
			}
		}
		
		if (obj == btnCancelar) 
		{
			setVisible (false);
			dispose();
		}
		if (obj == btnLimpiarOT)
		{
			txtClear ();
		}
		TablaDeBusqueda.Actualizar();
		TablaDeBusqueda_Top5.Actualizar();
	}
	

	private void calcularPliegoYhojas(){
		Integer cantFilas= tablaMateriales.getRowCount();
		
		
		for (int i = 0; i < cantFilas; i++) {
			//solo si las columnas tiene valores
			if (!tablaMateriales.getValueAt(i, 1).toString().equals("")
					&& !tablaMateriales.getValueAt(i, 7).toString()
							.equals("")
					&& !tablaMateriales.getValueAt(i, 6).toString()
							.equals("")
					&& !tablaMateriales.getValueAt(i, 8).toString()
							.equals("")) {
				// Obtengo los datos de la tabla materiales necesarios
				// para calcular los Pliegos Netos
				Integer cantElementoXcantAEntregar = Integer.parseInt(tablaMateriales
						.getValueAt(i, 1).toString());
				Integer posesXpliego = Integer.parseInt(tablaMateriales
						.getValueAt(i, 7).toString());
				Integer totalPliegosNetos = (int) Math.ceil((cantElementoXcantAEntregar)/ posesXpliego);
				tablaMateriales.setValueAt(totalPliegosNetos, i, 9);

				// Obtengo los datos de la tabla materiales necesarios
				// para calcular la cantidad de hojas
				Integer pliegosNetos = Integer.parseInt(tablaMateriales
						.getValueAt(i, 9).toString());
				Integer pliegosEnDemasia = Integer
						.parseInt(tablaMateriales.getValueAt(i, 6)
								.toString());
				Integer pliegosXhoja = Integer.parseInt(tablaMateriales
						.getValueAt(i, 8).toString());

				Integer hojas = (int) Math.ceil((pliegosEnDemasia + pliegosNetos)
						/ pliegosXhoja);
				
				tablaMateriales.setValueAt(hojas, i, 10);
			}
		}
	}
	
	
	//cierra la OT, deja Stock como remanente o como inactivo. guarda las hojas restantes por sc de esta OT
	private void cerrarOT(int clave, ArrayList<Integer> proc, ArrayList<Integer> id_proc, ArrayList<Boolean> cumplida) {

		for (int i = 0; i < proc.size(); i++) {
			Procesos_x_OT.setAvanceOT(clave,
					id_proc.get(proc.get(i)),
					cumplida.get(i));
		}
		Orden_Trabajo.CambiarEstado(clave,
				"Cerrada");
		Orden_Trabajo.setF_h_cierre(clave,
				Metodos.getDateTimeActual(0));

		/*
		 * Si sobran hojas, quedan como remanente en
		 * Stock
		 */
		ArrayList<Integer> id_Stock = Stock
				.getIdStockSegunOT(clave);

		for (int j = 0; j < id_Stock.size(); j++) {
			Integer hojas_usadas = Stock
					.getHojasUsadas(id_Stock.get(j));
			Integer hojas_totales = Stock
					.getHojasTotales(id_Stock
							.get(j));
			if (hojas_usadas < hojas_totales) {
				Stock.setStockComoRemanente(id_Stock
						.get(j));
			} else {
				Stock.setStockInactivo(id_Stock
						.get(j));
			}
			//guardo en una tabla aparte por SC y por OT, la cant de hojas que sobraron
			Remanente_sc_ot.llenarTablaRemanente_SC_OT(clave);
		}
		Adm_Stock.Actualizar();
	}





	private boolean elementosIguales() {
		
		for(int i=0;i < tablaElementos.getRowCount(); i++){
			
			for(int j=i+1;j<tablaElementos.getRowCount();j++){
				
				if(tablaElementos.getValueAt(i, 0).toString().equalsIgnoreCase(tablaElementos.getValueAt(j, 0).toString())){
					return true;
				}	
			}
			
		}
		
		return false;
	}





	void cargarTablas() 
	{	
		//Se obtienen las variables para crear una nueva OT
		String fechaCon = (String) cboAnio.getSelectedItem() +"-"+ Metodos.dameNumeroMes((String)cboMes.getSelectedItem()) +"-"+ cboDia.getSelectedItem();
		String fechaProm = (String) cboAnio2.getSelectedItem() +"-"+ Metodos.dameNumeroMes((String) cboMes2.getSelectedItem()) +"-"+ cboDia2.getSelectedItem();
		Integer cantImp =  Integer.parseInt(txtPreimpresion.getText());
		Double alto = Double.parseDouble(txtAlto.getText());
		Double ancho = Double.parseDouble(txtAncho.getText());
		String TipoProd= txtTipoProducto.getText();
		boolean apaisado=chbApaisado.isSelected();
		Integer cantEntr = Integer.parseInt(txtCantidadAEntregar.getText());
		Integer cliente = Cliente.getId_cliente((String) cboCliente.getSelectedItem());

		
		
		//Se da de alta una nueva OT
		Orden_Trabajo ot1= new Orden_Trabajo(TipoProd, cliente, fechaCon, fechaProm, txtNombreOT.getText(), txtDescripcion.getText(),cantEntr,cantImp,ancho,alto,apaisado,"Pendiente",0,null,null);
		ot1.Alta();
		
		//Se obtienen los valores guardados en la tabla Orden de ejecucion para crear filas en la tabla procesos_x_orden_trabajo de la BD
				Integer cantFilasProc = tablaOrdenDeEjecucion.getRowCount();
				Integer id_OT = Metodos.FacturaAEntero(this.txtNro.getText());
				for (int i = 0; i < cantFilasProc; i++) 
				{
					Integer id_Proceso = Proceso.getIdProceso((String) tablaOrdenDeEjecucion.getValueAt(i, 0));
					boolean isTercerizada = (Boolean) tablaOrdenDeEjecucion.getValueAt(i, 1);
					boolean isCumplida = (Boolean) tablaOrdenDeEjecucion.getValueAt(i, 4);
					Integer id_Proveedor;
					String observaciones;
					if( isTercerizada == true)
					{
						id_Proveedor = Proveedor.getId_Proveedor(tablaOrdenDeEjecucion.getValueAt(i, 2).toString());
						observaciones = tablaOrdenDeEjecucion.getValueAt(i, 3).toString();
					}
					else
					{
						id_Proveedor = null;
						observaciones = null;
					}
					
					Procesos_x_OT pxt = new Procesos_x_OT(id_Proceso,id_OT,isTercerizada,id_Proveedor, isCumplida,observaciones,i);
					pxt.Alta();
				}	
		
		
		
		//Se obtienen los valores guardados en la tabla Elementos para crear filas en la tabla Elemento de la BD
				
		Integer cantFilas = tablaElementos.getRowCount();
		for (int i = 0; i < cantFilas; i++) 
		{
			
			Elemento e = new Elemento(id_OT,tablaElementos.getValueAt(i, 0).toString(),Integer.parseInt(tablaElementos.getValueAt(i, 1).toString()));
			e.Alta();
			
			//id_elem tiene el ultimo elemento que se agrego
			Integer id_Elem = Elemento.getMaxId_elemento();
			
			//Busco los FK para la tabla materiales de BD
			Integer id_cal = Calidad.getId_Calidad(tablaMateriales.getValueAt(i, 3).toString());
			Integer id_for = Formato_Papel.getId_Formato(tablaMateriales.getValueAt(i, 4).toString());
			Integer id_var = Variante.getId_Variante(tablaMateriales.getValueAt(i, 5).toString());
			
			//Obtengo los demas datos para la tabla de materiales
			
			Integer gramaje = Integer.parseInt(tablaMateriales.getValueAt(i, 2).toString());
			Integer pliegosEnDemasia = Integer.parseInt(tablaMateriales.getValueAt(i, 6).toString());
			Integer posesXpliego = Integer.parseInt(tablaMateriales.getValueAt(i, 7).toString());
			Integer pliegosXhoja = Integer.parseInt(tablaMateriales.getValueAt(i, 8).toString());
			Integer pliegosNetos = Integer.parseInt(tablaMateriales.getValueAt(i, 9).toString());
			Integer hojas = Integer.parseInt(tablaMateriales.getValueAt(i, 10).toString());
			
			//Se da de alta la tabla de materiales con todos los datos ingresados por el usuario.
			Materiales m = new Materiales(id_Elem,gramaje,id_cal,id_for,id_var,pliegosEnDemasia,posesXpliego,
					pliegosXhoja,hojas,pliegosNetos);
			m.Alta();
		}
		
	}

	void txtClear () 
	{
		txtNombreOT.setText ("");
		txtDescripcion.setText ("");
		txtTipoProducto.setText ("");
		txtCantidadAEntregar.setText("1");
		txtPreimpresion.setText("");
		txtAlto.setValue("000.00");
		txtAncho.setValue("000.00");
		chbApaisado.setSelected(false);
	}
	
	public boolean procesosOk(){
		Integer cantFilas=tablaOrdenDeEjecucion.getRowCount();
		
		return cantFilas>0;
	}
	
	public boolean materialesOk(){
		
		Integer cantFilas=tablaMateriales.getRowCount();
		Integer cantCol=tablaMateriales.getColumnCount();
		boolean ok=cantFilas>0;
		for(int i=0;i<cantFilas;i++){
			for(int j=0;j<cantCol;j++){
				ok=tablaMateriales.getValueAt(i, j) != null;
				ok=ok && !tablaMateriales.getValueAt(i, j).toString().equals("");	
			}
		}
		return ok;
	}
	
	public ArrayList<Integer> getId_procesosTablaActual() {
		ArrayList<Integer> idproc = new ArrayList<Integer>();
		for (int i = 0; i < tablaOrdenDeEjecucion.getRowCount(); i++) {
			Integer id_Proceso = Proceso
					.getIdProceso((String) tablaOrdenDeEjecucion.getValueAt(i,
							0));
			idproc.add(id_Proceso);
		}
		return idproc;
	}

	
	JTextField getTxtNro()
	{
		return this.txtNro;
	}
	
	JTextField getTipoProducto()
	{
		return this.txtTipoProducto;
	}
	
	JComboBox<String> getCboMes()
	{
		return this.cboMes;
	}
	
	JComboBox<String> getCboDia()
	{
		return this.cboDia;
	}
	
	JComboBox<String> getCboAnio()
	{
		return this.cboAnio;
	}
	
	JComboBox<String> getCboMes2()
	{
		return this.cboMes2;
	}
	
	JComboBox<String> getCboDia2()
	{
		return this.cboDia2;
	}
	
	JComboBox<String> getCboAnio2()
	{
		return this.cboAnio2;
	}
	
	JTextField getTxtNombreOT()
	{
		return this.txtNombreOT;
	}
	
	JComboBox<String> getEstado()
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
	
	JTable getTablaElementos()
	{
		return this.tablaElementos;
	}

	
	public JComboBox<String> getCliente() 
	{
		return this.cboCliente;
	}
	
	public JTable getTablaMateriales()
	{
		return this.tablaMateriales;
	}
	
	public JTable getTablaOrdenEjecucion()
	{
		return this.tablaOrdenDeEjecucion;
	}
	
	public JButton getBtnLimpiarCampos() 
	{
		return this.btnLimpiarOT;
	}
	
	public JButton getBtnConfirmarSeleccion() {
		return btnConfirmarSeleccion;
	}


	public JButton getBtnAgregarFila() {
		return btnAgregarFila;
	}


	public JButton getBtnBorrarFila() {
		return btnBorrarFila;
	}


	public JButton getBtnGuardar() {
		return btnGuardar;
	}


	public JButton getBtnAlmacenar() {
		return btnAlmacenar;
	}


	public String[] getMeses() {
		return Meses;
	}
	
	public JButton getBtnImprimirReporte()
	{
		return this.btnImprimirReporte;
	}
	
	public JButton getBtnUp() {
		return btnUp;
	}

	public JButton getBtnDown() {
		return btnDown;
	}
	
	public JTextField getFechaHoraCerrada()
    {
            return fechaHoraCierreOT;
    }
	
	
	
	public JLabel getLblFechaCierre() {
		return lblFechaCierre;
	}





	private boolean proveedorElegido()
	{
		Integer cantFilasProc = tablaOrdenDeEjecucion.getRowCount();
		Integer id_Proveedor;
		for (int i = 0; i < cantFilasProc; i++) 
		{
			boolean isTercerizada = (Boolean) tablaOrdenDeEjecucion.getValueAt(i, 1);
			if(isTercerizada == true)
			{
				 id_Proveedor = Proveedor.getId_Proveedor(tablaOrdenDeEjecucion.getValueAt(i, 2).toString());
				 if(id_Proveedor == null )
				 {
					 return false;
				 }
			}
		}
		return true;
	}
	
	
	private boolean ExcedeLargoObservacion() {

		for (int i = 0; i < tablaOrdenDeEjecucion.getRowCount(); i++) {
			if (tablaOrdenDeEjecucion.getValueAt(i, 3).toString().length() > 100) {
				return true;
			}
		}
		return false;
	}

	private boolean ExcedeLargoElemento() {

		for (int i = 0; i < tablaElementos.getRowCount(); i++) {
			if (tablaElementos.getValueAt(i, 0).toString().length() > 50) {
				return true;
			}
		}
		return false;
	}

	
	
	@SuppressWarnings("deprecation")
	private void reporteOT()
	{
		String fechaConfec = getCboDia().getSelectedItem().toString()+ " de " + getCboMes().getSelectedItem().toString() + " del " + getCboAnio().getSelectedItem().toString();
		String fechaPrometida = getCboDia2().getSelectedItem().toString()+ " de " + getCboMes2().getSelectedItem().toString() + " del " + getCboAnio2().getSelectedItem().toString();
		Double ancho = Double.parseDouble(this.getTxtAncho().getText());
		Double alto = Double.parseDouble(this.getTxtAlto().getText());
		String nroOT = this.getTxtNro().getText();
		Integer id_OT=Metodos.FacturaAEntero(nroOT);

		String apaisada = "";
		if(this.getChbApaisado().isSelected())
			apaisada = "Si";
		else
			apaisada = "No";
		
		//guardo en un arraylist las filas de la tabla Elementos
		ArrayList<FilaElementos> rElementos = new ArrayList<FilaElementos>();
		Integer cantFilas = tablaElementos.getRowCount();
		for (int i = 0; i < cantFilas; i++) 
		{
			rElementos.add(new FilaElementos((String) tablaElementos.getValueAt(i, 0), (Integer) tablaElementos.getValueAt(i, 1),
					(Integer) tablaElementos.getValueAt(i, 2), (Integer) tablaElementos.getValueAt(i, 3)));
					
		}
		
		//guardo en un arraylist las filas de la tabla Materiales
		ArrayList<FilaMateriales> rMateriales = new ArrayList<FilaMateriales>();
		cantFilas = tablaMateriales.getRowCount();
		for (int i = 0; i < cantFilas; i++) 
		{
			rMateriales.add(new FilaMateriales((String) tablaMateriales.getValueAt(i, 0), (Integer) tablaMateriales.getValueAt(i, 1),
					(Integer) tablaMateriales.getValueAt(i, 2), (String) tablaMateriales.getValueAt(i, 3), (String) tablaMateriales.getValueAt(i, 4), (String) tablaMateriales.getValueAt(i, 5),
					(Integer) tablaMateriales.getValueAt(i, 6), (Integer) tablaMateriales.getValueAt(i, 7), (Integer) tablaMateriales.getValueAt(i, 8), (Integer) tablaMateriales.getValueAt(i, 9), 
					(Integer) tablaMateriales.getValueAt(i,10)));
					
		}
		//guardo en un arraylist las filas de la tabla Orden de ejecucion
		ArrayList<FilaOEjecucion> rOEjecucion = new ArrayList<FilaOEjecucion>();
		cantFilas = tablaOrdenDeEjecucion.getRowCount();
		String tercerizada = "";
		String cumplida = "";
		
		Integer cantCumplidas=Procesos_x_OT.getCantidadFilasCumplidas(id_OT);
		for (int i = 0; i < cantFilas; i++) 
		{
			if(tablaOrdenDeEjecucion.getValueAt(i, 1).equals(true))
				tercerizada = "Si";
			else
				tercerizada = "No";
			
			if(i < cantCumplidas)
				cumplida = "Si";
			else
				cumplida = "No";
			
			rOEjecucion.add(new FilaOEjecucion((String) tablaOrdenDeEjecucion.getValueAt(i, 0), tercerizada,
					(String) tablaOrdenDeEjecucion.getValueAt(i, 2), (String) tablaOrdenDeEjecucion.getValueAt(i, 3), cumplida));
					
		}
		
		String estadoOT=Orden_Trabajo.getEstadoOT(id_OT);
		ReporteOT r = new ReporteOT(nroOT,getTxtNombreOT().getText(), getCliente().getSelectedItem().toString(),
				getTxtDescripcion().getText(), getTipoProducto().getText(), getTxtPreimpresion().getText(),
				estadoOT, fechaPrometida, fechaConfec,ancho,alto,getTxtCantidadAEntregar().getText(),apaisada, rElementos, rMateriales,rOEjecucion);
		
		ArrayList<ReporteOT> reportes = new ArrayList<ReporteOT>();
		reportes.add(r);
		
		JasperReport reporte = null;
		try 
		{
			reporte = (JasperReport) JRLoader.loadObjectFromLocation("reporteOT.jasper");
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		JasperPrint jasperPrint = null;
		try
		{
			jasperPrint = JasperFillManager.fillReport(reporte, null,
					new JRBeanCollectionDataSource(reportes));
			JasperViewer.viewReport(jasperPrint,false);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void reporteFinal()
	{
		String nroOT = getTxtNro().getText();
		Integer id_OT = Metodos.FacturaAEntero(nroOT);

		//guardo en un arraylist las filas de la tabla Elementos
        ArrayList<FilaElementos> rElementos = new ArrayList<FilaElementos>();
        Integer cantFilas = tablaElementos.getRowCount();
        for (int i = 0; i < cantFilas; i++)
        {
        	rElementos.add(new FilaElementos((String) tablaElementos.getValueAt(i, 0), (Integer) tablaElementos.getValueAt(i, 1),
                          (Integer) tablaElementos.getValueAt(i, 2), (Integer) tablaElementos.getValueAt(i, 3)));
        }
        
      //Guardo en un arraylist las filas de la tabla Orden de ejecucion
      		ArrayList<FilaOEjecucion> rOEjecucion = new ArrayList<FilaOEjecucion>();
      		cantFilas = tablaOrdenDeEjecucion.getRowCount();
      		String tercerizada = "";
      		String cumplida = "";
      		
      		Integer cantCumplidas=Procesos_x_OT.getCantidadFilasCumplidas(id_OT);
      		for (int i = 0; i < cantFilas; i++) 
      		{
      			if(tablaOrdenDeEjecucion.getValueAt(i, 1).equals(true))
      				tercerizada = "Si";
      			else
      				tercerizada = "No";
      			
      			if(i < cantCumplidas)
      				cumplida = "Si";
      			else
      				cumplida = "No";
      			
      			rOEjecucion.add(new FilaOEjecucion((String) tablaOrdenDeEjecucion.getValueAt(i, 0), tercerizada,
      					(String) tablaOrdenDeEjecucion.getValueAt(i, 2), (String) tablaOrdenDeEjecucion.getValueAt(i, 3), cumplida));
      					
      		}
        
        
		ReporteFinal r = new ReporteFinal(nroOT, getTxtNombreOT().getText(),Egreso_Stock.getSC(id_OT),Egreso_Stock.getRetirosStock(id_OT), rElementos, rOEjecucion);
		
		ArrayList<ReporteFinal> reportes = new ArrayList<ReporteFinal>();
		reportes.add(r);
		JasperReport reporte = null;

		try 
		{
		reporte = (JasperReport) JRLoader.loadObjectFromLocation("reporteFinal.jasper");
		} 
		catch (Exception e1) 
		{
		e1.printStackTrace();
		}
		JasperPrint jasperPrint = null;
		try 
		{
		jasperPrint = JasperFillManager.fillReport(reporte, null,
		new JRBeanCollectionDataSource(reportes));
		JasperViewer.viewReport(jasperPrint,false);
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
		}

	}
	
	private void elegirReporteAImprimir() 
	{
		int reply = JOptionPane.showOptionDialog
		(
			this,
			"Seleccione el reporte que desea imprimir\n(o presione Cancelar para salir)",
			qTITULO + " - Imprimir Reporte", 
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE, 
			null,
			new Object[] { "Orden de Trabajo", "Reporte Final", "Cancelar" },
			3
		);
	
		if (reply == 0) 
		{
			this.reporteOT();
		}
		
		else if (reply == 1) 
		{
			int id_OT=Metodos.FacturaAEntero(this.getTxtNro().getText());
			String estadoOT=Orden_Trabajo.getEstadoOT(id_OT);

			if(estadoOT.equalsIgnoreCase("Cerrada"))
			{
				this.reporteFinal();
			}
			
			else
			{
				JOptionPane.showMessageDialog 
				(
					this, 
					"La orden de trabajo no está cerrada.","Error al generar reporte", 
					JOptionPane.ERROR_MESSAGE
					
				);
			}
		}
		else if (reply == 2) 
		{
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
}		