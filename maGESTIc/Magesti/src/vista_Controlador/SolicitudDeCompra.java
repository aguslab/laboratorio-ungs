package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.TextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Modelo.Calidad;
import Modelo.Detalle;
import Modelo.Formato_Papel;
import Modelo.Orden_Trabajo;
import Modelo.Proveedor;
import Modelo.Recepcion_pedido;
import Modelo.Solicitud_compra;
import Modelo.Variante;

import java.awt.Font;

@SuppressWarnings("serial")



public class SolicitudDeCompra extends JInternalFrame implements ActionListener, Config
{
	private JTextField txtNumero;
	private JTextField txtVendedor;
	private JTextField txtTotal;
	private JTextField txtMontoIVA;
	private JTextField txtIVA;
	private JTextField txtSubtotal;
	private JTextField txtDireccionRetiro;
	private JTextField txtFecha;
	private JTable tablaDetalles;
	private JPanel JpSolicitudDeCompra;
	private JLabel lbNumero,lbDireccionRetiro ;
	private JLabel lbProveedor,lbNroOT,lbFechaEntrega;
	private JComboBox cbProveedor ;
	private JLabel txtFechaConfec ;
	private JLabel lbVendedor;
	private JComboBox cbMes, cbDia, cbAnio;
	private JButton btnBorrar,btnAlmacenar,btnAgregar,btnCerrar,btnConfirmar,btnConfirmarRecepcion,btnRechazarRecepcion,btnIncompleta;
	private JRadioButton rbManiana,rbTarde,rdbtnRetirar,rdbtnEnviarAProveedor; 
	private ButtonGroup grupoHorario,grupoCondicionEntrega;
	private JComboBox cbNroOT;
	private TextArea txtDescripcionIncidencia;

	
	public SolicitudDeCompra(boolean  RP) {
		super ("Solicitud de Compra (SC)", false, true, false, true);
		
		setSize (925, 580);
		Calendar fecha= Calendar.getInstance();
		Integer mm=fecha.get(Calendar.MONTH)+1;
		Integer dd=fecha.get(Calendar.DATE);
		Integer aaaa=fecha.get(Calendar.YEAR);
		getContentPane().setLayout(null);
		
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
		
		
		
		
		JpSolicitudDeCompra = new JPanel();
		JpSolicitudDeCompra.setBounds(0, 0, 915, 549);
		getContentPane().add(JpSolicitudDeCompra);
		JpSolicitudDeCompra.setLayout(null);
		
		lbNumero = new JLabel("N\u00FAmero:");
		lbNumero.setFont(new Font("Arial", Font.PLAIN, 12));
		lbNumero.setBounds(10, 11, 75, 30);
		JpSolicitudDeCompra.add(lbNumero);
		
		String maxId = Orden_Trabajo.EnteroAFactura(Solicitud_compra.getUltId_SC());
		
		txtNumero = new JTextField(maxId);
		txtNumero.setFont(new Font("Arial", Font.BOLD, 12));
		txtNumero.setForeground(Color.RED);
		txtNumero.setBounds(85, 14, 164, 25);
		JpSolicitudDeCompra.add(txtNumero);
		txtNumero.setEditable(false);
		txtNumero.setColumns(10);
		
		lbProveedor = new JLabel("Proveedor:");
		lbProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lbProveedor.setBounds(10, 54, 75, 30);
		JpSolicitudDeCompra.add(lbProveedor);
		
		String[] proveedores=Proveedor.getProveedores();
		cbProveedor = new JComboBox(proveedores);
		cbProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		cbProveedor.setBounds(85, 57, 363, 25);
		JpSolicitudDeCompra.add(cbProveedor);
		
		txtFechaConfec = new JLabel("<html>Fecha de<br> confeccion:</html>");
		txtFechaConfec.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFechaConfec.setBounds(259, 11, 75, 30);
		JpSolicitudDeCompra.add(txtFechaConfec);
		
		txtFecha = new JTextField(aaaa + "-" + mm + "-" + dd);
		txtFecha.setHorizontalAlignment(SwingConstants.LEFT);
		txtFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFecha.setBounds(344, 14, 148, 25);
		JpSolicitudDeCompra.add(txtFecha);
		txtFecha.setEditable(false);
		
		lbVendedor = new JLabel("Vendedor:");
		lbVendedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lbVendedor.setBounds(458, 54, 75, 30);
		JpSolicitudDeCompra.add(lbVendedor);
		
		txtVendedor = new JTextField();
		txtVendedor.setFont(new Font("Arial", Font.PLAIN, 12));
		txtVendedor.setBounds(534, 57, 371, 25);
		
		JpSolicitudDeCompra.add(txtVendedor);
		txtVendedor.setColumns(10);
		txtVendedor.getInputMap(txtVendedor.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		
		//limitar la cantidad de caracteres a 30
		txtVendedor.addKeyListener(new KeyListener(){
		public void keyTyped(KeyEvent e){
			int limite=30;
			if (txtVendedor.getText().length()== limite){
				getToolkit().beep ();
				e.consume();
			}
				
		}
		
		
		
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		});
		
		
		lbNroOT = new JLabel("<html>N\u00B0 Orden<br> de Trabajo:</html>");
		lbNroOT.setFont(new Font("Arial", Font.PLAIN, 12));
		lbNroOT.setBounds(502, 11, 75, 30);
		JpSolicitudDeCompra.add(lbNroOT);
		
		String [] numot_nombreot=Orden_Trabajo.getId_nom_OT();
		cbNroOT = new JComboBox(numot_nombreot);
		cbNroOT.setForeground(new Color(70, 130, 180));
		cbNroOT.setFont(new Font("Arial", Font.BOLD, 12));
		cbNroOT.setBounds(587, 14, 318, 25);
		JpSolicitudDeCompra.add(cbNroOT);
		
		JTabbedPane Secciones = new JTabbedPane(JTabbedPane.TOP);
		Secciones.setBounds(10, 95, 895, 247);
		JpSolicitudDeCompra.add(Secciones);
		
		JPanel panCondicionEntrega = new JPanel();
		panCondicionEntrega.setBorder(new LineBorder(new Color(0, 0, 0)));
		Secciones.addTab("Condiciones de Entrega", null, panCondicionEntrega, null);
		panCondicionEntrega.setLayout(null);
		
		lbDireccionRetiro = new JLabel("Direcci\u00F3n de Retiro:");
		lbDireccionRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		lbDireccionRetiro.setBounds(22, 154, 121, 14);
		panCondicionEntrega.add(lbDireccionRetiro);
		
		txtDireccionRetiro = new JTextField();
		txtDireccionRetiro.setEnabled(false);
		txtDireccionRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDireccionRetiro.setBounds(141, 149, 731, 25);
		panCondicionEntrega.add(txtDireccionRetiro);
		txtDireccionRetiro.setColumns(10);
		txtDireccionRetiro.getInputMap(txtVendedor.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		
		txtDireccionRetiro.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int limite=100;
				if (txtVendedor.getText().length()== limite){
					getToolkit().beep ();
					e.consume();
				}				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lbFechaEntrega = new JLabel("Fecha Entrega:");
		lbFechaEntrega.setFont(new Font("Arial", Font.PLAIN, 12));
		lbFechaEntrega.setBounds(22, 24, 93, 14);
		panCondicionEntrega.add(lbFechaEntrega);
		
		cbMes = new JComboBox(Meses);
		cbMes.setFont(new Font("Arial", Font.PLAIN, 12));
		cbMes.setBounds(117, 19, 82, 25);
		panCondicionEntrega.add(cbMes);
		
		cbDia = new JComboBox();
		cbDia.setFont(new Font("Arial", Font.PLAIN, 12));
		cbDia.setBounds(200, 19, 61, 25);
		panCondicionEntrega.add(cbDia);
		for (int i = 1; i <= 31; i++) 
		{
			String dias = "" + i;
			cbDia.addItem (dias);
		}
		
		cbAnio = new JComboBox();
		cbAnio.setFont(new Font("Arial", Font.PLAIN, 12));
		cbAnio.setBounds(262, 19, 61, 25);
		panCondicionEntrega.add(cbAnio);
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cbAnio.addItem (anios);
		}
		
		JPanel pHorarioEntrega = new JPanel();
		pHorarioEntrega.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Horario de entrega", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pHorarioEntrega.setBounds(458, 63, 414, 68);
		panCondicionEntrega.add(pHorarioEntrega);
		pHorarioEntrega.setLayout(null);
		
		grupoHorario = new ButtonGroup();
		rbManiana = new JRadioButton("Ma\u00F1ana");
		rbManiana.setFont(new Font("Arial", Font.PLAIN, 12));
		rbManiana.setBounds(32, 27, 72, 23);
		grupoHorario.add(rbManiana);
		pHorarioEntrega.add(rbManiana);
		rbManiana.setSelected(true);
		rbTarde = new JRadioButton("Tarde");
		rbTarde.setFont(new Font("Arial", Font.PLAIN, 12));
		rbTarde.setBounds(124, 27, 72, 23);
		grupoHorario.add(rbTarde);
		pHorarioEntrega.add(rbTarde);
		
		JPanel pCondicionEntrega = new JPanel();
		pCondicionEntrega.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Condici\u00F3n de Entrega", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pCondicionEntrega.setBounds(22, 63, 414, 68);
		panCondicionEntrega.add(pCondicionEntrega);
		pCondicionEntrega.setLayout(null);
		
		grupoCondicionEntrega = new ButtonGroup();
		rdbtnRetirar = new JRadioButton("Retirar");
		
		rdbtnRetirar.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnRetirar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtDireccionRetiro.setEnabled(true);
			}
		});
		rdbtnRetirar.setBounds(28, 27, 72, 23);
		grupoCondicionEntrega.add(rdbtnRetirar);
		pCondicionEntrega.add(rdbtnRetirar);
		
		rdbtnEnviarAProveedor = new JRadioButton("Enviar a Proveedor");
		rdbtnEnviarAProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnEnviarAProveedor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDireccionRetiro.setEnabled(false);
			}
		});
		rdbtnEnviarAProveedor.setBounds(110, 27, 143, 23);
		grupoCondicionEntrega.add(rdbtnEnviarAProveedor);
		pCondicionEntrega.add(rdbtnEnviarAProveedor);
		rdbtnEnviarAProveedor.setSelected(true);
		JPanel panDetalles = new JPanel();
		panDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		Secciones.addTab("Detalles", null, panDetalles, null);
		panDetalles.setLayout(null);
		
		JScrollPane spDetalles = new JScrollPane();
		spDetalles.setBounds(10, 11, 870, 156);
		panDetalles.add(spDetalles);
		
		tablaDetalles = new JTable();
		tablaDetalles.setFont(new Font("Arial", Font.PLAIN, 12));
		tablaDetalles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cant. Hojas", "Marca", "Calidad", "Variante", "Formato", "Gramaje", "Precio Unitario", "Unidad de Medida", "Importe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Object.class, Object.class, Integer.class, Double.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(90);
		tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(1).setPreferredWidth(85);
		tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(2).setPreferredWidth(140);
		tablaDetalles.getColumnModel().getColumn(3).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(3).setPreferredWidth(115);
		tablaDetalles.getColumnModel().getColumn(4).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(4).setPreferredWidth(85);
		tablaDetalles.getColumnModel().getColumn(5).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(5).setPreferredWidth(85);
		tablaDetalles.getColumnModel().getColumn(6).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(6).setPreferredWidth(105);
		tablaDetalles.getColumnModel().getColumn(7).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(7).setPreferredWidth(110);
		tablaDetalles.getColumnModel().getColumn(8).setResizable(false);
		spDetalles.setViewportView(tablaDetalles);
		
		tablaDetalles.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaDetalles.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		//
		//Autocalcular importe
		//
		TableColumnModel tc= tablaDetalles.getColumnModel();
		tc.addColumnModelListener(new TableColumnModelListener() {
			
			@Override
			public void columnSelectionChanged(ListSelectionEvent arg0) {
				Integer cantFilas = tablaDetalles.getRowCount();

				for (int i = 0; i < cantFilas; i++) {
					// solo si las columnas tiene valores
					if (tablaDetalles.getValueAt(i, 1) != null
							&& tablaDetalles.getValueAt(i, 5) != null
							&& tablaDetalles.getValueAt(i, 6) != null
							&& tablaDetalles.getValueAt(i, 7) != null
							&& !tablaDetalles.getValueAt(i, 1).toString()
									.equals("")
							&& !tablaDetalles.getValueAt(i, 5).toString()
									.equals("")
							&& !tablaDetalles.getValueAt(i, 6).toString()
									.equals("")
							&& !tablaDetalles.getValueAt(i, 7).toString()
									.equals("")) {
						// calcular importe
						Double importe=getImporte(i);
						tablaDetalles.setValueAt(importe, i, 8);
						
					}
				}
			}
			
			@Override
			public void columnRemoved(TableColumnModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void columnMoved(TableColumnModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void columnMarginChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void columnAdded(TableColumnModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
			
		
		
		btnBorrar= new JButton("Borrar", new ImageIcon ("Imagenes/restar.png"));
		btnBorrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
				DefaultTableModel temp = (DefaultTableModel) tablaDetalles.getModel();
				if(temp.getRowCount()>0)
				{
					temp.removeRow(tablaDetalles.getSelectedRow());	
				}
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
					JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
				}
			}
		});
		btnBorrar.setBounds(140, 178, 120, 30);
		panDetalles.add(btnBorrar);
		
		btnAgregar= new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DefaultTableModel temp = (DefaultTableModel) tablaDetalles.getModel();
				Object nuevo[]= {""};
				temp.addRow(nuevo);
				
				String calidades[] = Calidad.getCalidades();
				TableColumn columnaCalidad = tablaDetalles.getColumnModel().getColumn(2);//table es la JTable, ponele que la col n es la del combo.
				columnaCalidad.setCellEditor(new MyComboBoxEditor(calidades));
				
				// Valores para el combo
				String variantes[] = Variante.getVariantes(); 
				TableColumn columnaVariante = tablaDetalles.getColumnModel().getColumn(3);//table es la JTable, ponele que la col n es la del combo.
				columnaVariante.setCellEditor(new MyComboBoxEditor(variantes));
				
				// Valores para el combo
				String formatos[] = Formato_Papel.getFormatos();
				TableColumn columnaFormato = tablaDetalles.getColumnModel().getColumn(4);//table es la JTable, ponele que la col n es la del combo.
				columnaFormato.setCellEditor(new MyComboBoxEditor(formatos));
				
				// Valores para el combo
				String unidad_medida[] = {"Resma","Kg","Hoja"};
				TableColumn columnaUnidMedida= tablaDetalles.getColumnModel().getColumn(7);//table es la JTable, ponele que la col n es la del combo.
				columnaUnidMedida.setCellEditor(new MyComboBoxEditor(unidad_medida));
				
			}
		});
		btnAgregar.setBounds(10, 178, 120, 30);
		panDetalles.add(btnAgregar);
		
		btnAlmacenar = new JButton("Almacenar", new ImageIcon ("Imagenes/ok.png"));
		btnAlmacenar.setBounds(760, 178, 120, 30);
		panDetalles.add(btnAlmacenar);
		btnAlmacenar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 
				// PRIMERO CHEQUEAR QUE ESTEN TODOS LOS CAMPOS COMPLETOS
				//DESPUES...
				int cantFilas=tablaDetalles.getRowCount(),cantCol=tablaDetalles.getColumnCount();
				boolean celdas_ok=false;
				for(int i=0;i<cantFilas;i++){
					for(int j=0;j<cantCol;j++){
						if (tablaDetalles.getValueAt(i, j) == null
								|| tablaDetalles.getValueAt(i, j).toString()
										.equals("")) {
							celdas_ok=false;
							break;
						}else if(i==(cantFilas-1) && j==(cantCol-1) ){
							celdas_ok=true;
						}
						}
					}
				
				if(celdas_ok){
					//calcular subotal, monto IVA, total
					Double subtotal=0.0;
					Double monto_iva=0.0;
					Double total=0.0;
					cantFilas=tablaDetalles.getRowCount();
					for (int k = 0; k < cantFilas; k++) {
						subtotal = subtotal
								+ (Double) tablaDetalles.getValueAt(k, 8);
					}
					monto_iva = subtotal * Config.IVA / 100;
					total = subtotal + monto_iva;
					DecimalFormat df= new DecimalFormat("0.00");
					df.format(subtotal);
					txtSubtotal.setText(pasarAPesos(df.format(subtotal)));
					txtMontoIVA.setText("$ " + df.format(monto_iva));
					txtTotal.setText("$ " + df.format(total));
				}else{
					JOptionPane.showMessageDialog(null,"Debe completar todas las celdas!!!");
				}
				
			}
		});
		
		btnCerrar = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCerrar.setBounds(785, 501, 120, 30);
		JpSolicitudDeCompra.add(btnCerrar);
		btnCerrar.addActionListener(this);
		
		btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmar.setBounds(655, 501, 120, 30);
		JpSolicitudDeCompra.add(btnConfirmar);
		btnConfirmar.addActionListener(this);
		
		txtTotal = new JTextField();
		txtTotal.setBackground(new Color(255, 165, 0));
		txtTotal.setForeground(new Color(0, 0, 0));
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setFont(new Font("Arial", Font.BOLD, 12));
		txtTotal.setBounds(819, 465, 86, 25);
		JpSolicitudDeCompra.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtMontoIVA = new JTextField();
		txtMontoIVA.setEditable(false);
		txtMontoIVA.setHorizontalAlignment(SwingConstants.CENTER);
		txtMontoIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMontoIVA.setBounds(819, 434, 86, 25);
		JpSolicitudDeCompra.add(txtMontoIVA);
		txtMontoIVA.setColumns(10);
		
		
		txtIVA = new JTextField(Config.IVA.toString()+" %");
		txtIVA.setEditable(false);
		txtIVA.setHorizontalAlignment(SwingConstants.CENTER);
		txtIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		txtIVA.setBounds(819, 403, 86, 25);
		JpSolicitudDeCompra.add(txtIVA);
		txtIVA.setColumns(10);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSubtotal.setBounds(819, 373, 86, 25);
		JpSolicitudDeCompra.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 353, 729, 137);
		JpSolicitudDeCompra.add(panel);
		panel.setLayout(null);
		panel.setEnabled(true);
		
		
		btnConfirmarRecepcion = new JButton("Confirmar", new ImageIcon ("Imagenes/ok.png"));
		btnConfirmarRecepcion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmarRecepcion.setBounds(10, 11, 120, 30);
		panel.add(btnConfirmarRecepcion);
		
		btnRechazarRecepcion = new JButton("Rechazar", new ImageIcon ("Imagenes/no.png"));
		btnRechazarRecepcion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRechazarRecepcion.setBounds(10, 52, 120, 30);
		panel.add(btnRechazarRecepcion);
		
		btnIncompleta = new JButton("Incompleta", new ImageIcon ("Imagenes/incompleto.png"));
		btnIncompleta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnIncompleta.setBounds(10, 93, 120, 30);
		panel.add(btnIncompleta);
		
		txtDescripcionIncidencia = new TextArea();
		txtDescripcionIncidencia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescripcionIncidencia.setBounds(143, 10, 576, 113);
		
		panel.add(txtDescripcionIncidencia);
		
		JLabel lbSubtotal = new JLabel("Subtotal:");
		lbSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
		lbSubtotal.setBounds(749, 378, 68, 14);
		JpSolicitudDeCompra.add(lbSubtotal);
		
		JLabel lbIVA = new JLabel("% IVA:");
		lbIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		lbIVA.setBounds(749, 408, 68, 14);
		JpSolicitudDeCompra.add(lbIVA);
		
		JLabel lbMontoIVA = new JLabel("Monto IVA:");
		lbMontoIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		lbMontoIVA.setBounds(749, 440, 68, 14);
		JpSolicitudDeCompra.add(lbMontoIVA);
		
		JLabel lbTotal = new JLabel("TOTAL:");
		lbTotal.setFont(new Font("Arial", Font.BOLD, 12));
		lbTotal.setBounds(749, 470, 68, 14);
		JpSolicitudDeCompra.add(lbTotal);
		
		
		
		if (RP)
		{
			btnConfirmarRecepcion.setEnabled(true);
			btnRechazarRecepcion.setEnabled(true);
			btnIncompleta.setEnabled(true);
			txtDescripcionIncidencia.setEnabled(true);
		}
		else
		{
			btnConfirmarRecepcion.setEnabled(false);
			btnRechazarRecepcion.setEnabled(false);
			btnIncompleta.setEnabled(false);
			txtDescripcionIncidencia.setEnabled(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int clave = Orden_Trabajo.FacturaAEntero(txtNumero.getText());
		Object obj = e.getSource();
		if(obj==btnConfirmar){
			String sVendedor = txtVendedor.getText().trim();
			String sDirRetiro = txtDireccionRetiro.getText().trim();
			//faltaria verificar que no sean solo espacios el nombre del vendedor
			if(txtVendedor.getText()==null || txtVendedor.getText().equals("") || sVendedor.length() == 0){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe especificar el nombre del vendedor",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
				txtVendedor.requestFocus();
			}
			else if(grupoHorario.getSelection()==null){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe selecionar un horario de entrega",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
				rbManiana.requestFocusInWindow();
			}
			else if(grupoCondicionEntrega.getSelection()==null){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe selecionar el modo de entrega",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
				//rdbtnRetirar.requestFocusInWindow();
			}		
			else if(txtSubtotal.getText()==null || txtSubtotal.getText().equals("")){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe completar la seccion detalles y luego presionar el botón 'Almacenar'",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
			}
			else if(txtTotal.getText().toString().equals("$ 0,00")){
				JOptionPane.showMessageDialog 
				(
					this, 
					"El valor total debe ser mayor a $ 0,00",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
			}
			else {// ////////////////////////
				if (rdbtnRetirar.isSelected()) {
					if (txtDireccionRetiro.getText() == null
							|| txtDireccionRetiro.getText().equals("") || sDirRetiro.length() == 0) {
						JOptionPane.showMessageDialog(this,
								"Debe especificar la dirección de retiro",
								qTITULO + " - Campo vacío",
								JOptionPane.WARNING_MESSAGE);
						txtDireccionRetiro.requestFocus();
					}
				}
				cargarTablas();
				setVisible(false);
				dispose();
			}
		}
		else if(obj==btnCerrar){
			setVisible (false);
			dispose();
		}
		
	}
	
	
	private void cargarTablas() {
		
		String fechaConfeccion = txtFecha.getText();
		Integer id_proveedor = Proveedor.getId_Proveedor((String) cbProveedor.getSelectedItem());
		String vendedor = (String) txtVendedor.getText();
		Integer id_OT=getIdEnCombo();
		boolean envia_proveedor=rdbtnEnviarAProveedor.isSelected();
		String direccionRetiro="";
		if(!envia_proveedor){
			direccionRetiro=txtDireccionRetiro.getText();	
		}
		
		String fechaEntrega= (String) cbAnio.getSelectedItem() +"-"+ OrdenDeTrabajo.dameNumeroMes((String)cbMes.getSelectedItem()) +"-"+ cbDia.getSelectedItem();
		String horEntrega;
		if(rbTarde.isSelected()){
			horEntrega="T";
		}else{
			horEntrega="M";
		}
		
		Double subtotal= pasarADouble(txtSubtotal.getText());
		Double porcentaje_iva=Config.IVA;
		Double monto_iva= pasarADouble(txtMontoIVA.getText());
		Double total=pasarADouble(txtTotal.getText());
		
		//dar de alta SC
		Solicitud_compra sc = new Solicitud_compra(fechaConfeccion, id_proveedor, vendedor, id_OT, envia_proveedor, direccionRetiro, fechaEntrega, horEntrega, subtotal, porcentaje_iva, monto_iva, total);
		sc.Alta();

		//cargar datos en detalles para esa SC
		int cantFilas=tablaDetalles.getRowCount(),cantCol=tablaDetalles.getColumnCount();
		for(int i=0;i<cantFilas;i++){
				Integer cantidad=(Integer) tablaDetalles.getValueAt(i, 0);
				String marca=tablaDetalles.getValueAt(i, 1).toString();
				Integer id_calidad = Calidad.getId_Calidad(tablaDetalles.getValueAt(i, 2).toString());
				Integer id_variante = Variante.getId_Variante(tablaDetalles.getValueAt(i, 3).toString());
				Integer id_formato = Formato_Papel.getId_Formato(tablaDetalles.getValueAt(i, 4).toString());
				Integer gramaje=(Integer) tablaDetalles.getValueAt(i, 5);
				Double precio_unitario=(Double) tablaDetalles.getValueAt(i, 6);
				String unidad_medida_del_precio=tablaDetalles.getValueAt(i, 7).toString();
				Double importe=(Double) tablaDetalles.getValueAt(i, 8);
				
				//dar de alta detalle
				Detalle detalle= new Detalle(Orden_Trabajo.FacturaAEntero(txtNumero.getText()), cantidad, marca, id_calidad, id_formato, id_variante, gramaje, precio_unitario, unidad_medida_del_precio, importe,false);
				detalle.Alta();
				}
		}
		
	
	public Integer getIdEnCombo(){
		String id="";
		String otSelec=(String) cbNroOT.getSelectedItem();
		for(int i=0;otSelec.charAt(i)!= ' ';i++){
				id=id+otSelec.charAt(i);
		}
		return Orden_Trabajo.FacturaAEntero(id);
	}
	
	

	private Double getImporte(Integer i) {
		Double importe=0.0;
		String unidad_medida_precio="";
		Integer cant_hojas=0;
		Double precio_unitario=0.0;
		//for(int i=0;i<tablaDetalles.getRowCount();i++){
			cant_hojas=(Integer) tablaDetalles.getValueAt(i, 0);
			precio_unitario=(Double) tablaDetalles.getValueAt(i, 6);
			unidad_medida_precio=tablaDetalles.getValueAt(i, 7).toString();
			
		//si la unidad de medida del precio es RESMA
			if(unidad_medida_precio.toUpperCase().equals("RESMA")){
				//calculo cuantas resmas hay 
				Double cant_resma= (double)cant_hojas/Config.Resma;
				//y lo multiplico por el precio unitario
				importe=(cant_resma)*(precio_unitario);
		//si la unidad de medida del precio es KG
			}else if(unidad_medida_precio.toUpperCase().equals("KG")){
				//calculo el formato(ancho x alto)
				Double formato=(double) (Integer.parseInt(tablaDetalles.getValueAt(i, 4).toString().substring(0, 2))*Integer.parseInt(tablaDetalles.getValueAt(i, 4).toString().substring(3)));
				Integer gramaje=(Integer) tablaDetalles.getValueAt(i, 5);
				//lo multiplico por el gramaje y la cantidad de hojas
				Double peso=formato*gramaje*cant_hojas;
				//obtengo el peso en KG y lo multiplico por precio unitario
				importe=(peso/1000)*precio_unitario;
		//si la unidad de medida del precio es HOJA
			}else{
				//preciounitario x cantidad de hojas
				importe=precio_unitario*cant_hojas;
			}
		//}		
		return importe;
	}
	
	public static String pasarAPesos(String df){
		return "$ "+df;
	}
	
	private Double pasarADouble(String cant){
		String monto=cant.substring(2);//saco el sigo $ 
		return Double.parseDouble(monto.replace(',', '.'));
	}

		
	
	
	//
	//getters
	//
	
	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public JTextField getTxtVendedor() {
		return txtVendedor;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public JTextField getTxtMontoIVA() {
		return txtMontoIVA;
	}

	public JTextField getTxtIVA() {
		return txtIVA;
	}

	public JTextField getTxtSubtotal() {
		return txtSubtotal;
	}

	public JTextField getTxtDireccionRetiro() {
		return txtDireccionRetiro;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public JTable getTablaDetalles() {
		return tablaDetalles;
	}

	public JPanel getJpSolicitudDeCompra() {
		return JpSolicitudDeCompra;
	}

	public JLabel getLbNumero() {
		return lbNumero;
	}

	public JLabel getLbDireccionRetiro() {
		return lbDireccionRetiro;
	}

	public JLabel getLbProveedor() {
		return lbProveedor;
	}

	public JLabel getLbNroOT() {
		return lbNroOT;
	}

	public JLabel getLbFechaEntrega() {
		return lbFechaEntrega;
	}

	public JComboBox getCbProveedor() {
		return cbProveedor;
	}

	public JLabel getTxtFechaConfec() {
		return txtFechaConfec;
	}

	public JLabel getLbVendedor() {
		return lbVendedor;
	}

	public JComboBox getCbMes() {
		return cbMes;
	}

	public JComboBox getCbDia() {
		return cbDia;
	}

	public JComboBox getCbAnio() {
		return cbAnio;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnAlmacenar() {
		return btnAlmacenar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public JButton getBtnConfirmarRecepcion() {
		return btnConfirmarRecepcion;
	}

	public JButton getBtnRechazarRecepcion() {
		return btnRechazarRecepcion;
	}

	public JButton getBtnIncompleta() {
		return btnIncompleta;
	}

	public JRadioButton getRbManiana() {
		return rbManiana;
	}

	public JRadioButton getRbTarde() {
		return rbTarde;
	}

	public JRadioButton getRdbtnRetirar() {
		return rdbtnRetirar;
	}

	public JRadioButton getRdbtnEnviarAProveedor() {
		return rdbtnEnviarAProveedor;
	}

	public ButtonGroup getGrupoHorario() {
		return grupoHorario;
	}

	public ButtonGroup getGrupoCondicionEntrega() {
		return grupoCondicionEntrega;
	}

	public JComboBox getCbNroOT() {
		return cbNroOT;
	}

	public TextArea getTxtDescripcionIncidencia() {
		return txtDescripcionIncidencia;
	}
	
	
	
	
	
};