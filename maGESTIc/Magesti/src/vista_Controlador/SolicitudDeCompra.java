package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
	public SolicitudDeCompra(boolean  RP) {
super ("Solicitud de Compra (SC)", false, true, false, true);
		
	setSize (680, 680);
		Calendar fecha= Calendar.getInstance();
		Integer mm=fecha.get(Calendar.MONTH)+1;
		Integer dd=fecha.get(Calendar.DATE);
		Integer aaaa=fecha.get(Calendar.YEAR);
		//jpOrdenDeTrabajo.setBounds (0, 0, 500, 115);
		getContentPane().setLayout(null);
		
		JPanel JpSolicitudDeCompra = new JPanel();
		JpSolicitudDeCompra.setBounds(0, 0, 670, 645);
		getContentPane().add(JpSolicitudDeCompra);
		JpSolicitudDeCompra.setLayout(null);
		
		JLabel lbNumero = new JLabel("N\u00FAmero:");
		lbNumero.setFont(new Font("Arial", Font.PLAIN, 12));
		lbNumero.setBounds(25, 28, 65, 14);
		JpSolicitudDeCompra.add(lbNumero);
		
		txtNumero = new JTextField("test");
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNumero.setForeground(Color.RED);
		txtNumero.setBounds(144, 23, 164, 25);
		JpSolicitudDeCompra.add(txtNumero);
		txtNumero.setEditable(false);
		txtNumero.setColumns(10);
		
		JLabel lbProveedor = new JLabel("Proveedor:");
		lbProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lbProveedor.setBounds(25, 96, 68, 14);
		JpSolicitudDeCompra.add(lbProveedor);
		
		JComboBox cbProveedor = new JComboBox();
		cbProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		cbProveedor.setBounds(144, 91, 282, 25);
		JpSolicitudDeCompra.add(cbProveedor);
		
		JLabel txtFechaConfec = new JLabel("Fecha de confeccion:");
		txtFechaConfec.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFechaConfec.setBounds(379, 23, 129, 14);
		JpSolicitudDeCompra.add(txtFechaConfec);
		
		txtFecha = new JTextField(aaaa + "-" + mm + "-" + dd);
		txtFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFecha.setBounds(500, 17, 148, 25);
		JpSolicitudDeCompra.add(txtFecha);
		txtFecha.setEditable(false);
		/*
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(139, 44, 46, 25);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(183, 44, 42, 25);
		getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(225, 44, 60, 25);
		getContentPane().add(comboBox_3);
		*/
		JLabel lbVendedor = new JLabel("Vendedor:");
		lbVendedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lbVendedor.setBounds(25, 128, 63, 14);
		JpSolicitudDeCompra.add(lbVendedor);
		
		txtVendedor = new JTextField();
		txtVendedor.setFont(new Font("Arial", Font.PLAIN, 12));
		txtVendedor.setBounds(144, 123, 282, 25);
		JpSolicitudDeCompra.add(txtVendedor);
		txtVendedor.setColumns(10);
		
		JLabel lbNroOT = new JLabel("N\u00B0 Orden de Trabajo:");
		lbNroOT.setFont(new Font("Arial", Font.PLAIN, 12));
		lbNroOT.setBounds(25, 64, 121, 14);
		JpSolicitudDeCompra.add(lbNroOT);
		
		JComboBox cbNroOT = new JComboBox();
		cbNroOT.setFont(new Font("Arial", Font.PLAIN, 12));
		cbNroOT.setBounds(144, 59, 282, 25);
		JpSolicitudDeCompra.add(cbNroOT);
		
		JTabbedPane Secciones = new JTabbedPane(JTabbedPane.TOP);
		Secciones.setBounds(25, 171, 623, 225);
		JpSolicitudDeCompra.add(Secciones);
		
		JPanel panCondicionEntrega = new JPanel();
		panCondicionEntrega.setBorder(new LineBorder(new Color(0, 0, 0)));
		Secciones.addTab("Condiciones de Entrega", null, panCondicionEntrega, null);
		panCondicionEntrega.setLayout(null);
		
		JLabel lbDireccionRetiro = new JLabel("Direcci\u00F3n de Retiro:");
		lbDireccionRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		lbDireccionRetiro.setBounds(22, 154, 121, 14);
		panCondicionEntrega.add(lbDireccionRetiro);
		
		txtDireccionRetiro = new JTextField();
		txtDireccionRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDireccionRetiro.setBounds(141, 149, 451, 25);
		panCondicionEntrega.add(txtDireccionRetiro);
		txtDireccionRetiro.setColumns(10);
		
		JLabel lbFechaEntrega = new JLabel("Fecha Entrega:");
		lbFechaEntrega.setFont(new Font("Arial", Font.PLAIN, 12));
		lbFechaEntrega.setBounds(22, 24, 93, 14);
		panCondicionEntrega.add(lbFechaEntrega);
		
		JComboBox cbMes = new JComboBox();
		cbMes.setFont(new Font("Arial", Font.PLAIN, 12));
		cbMes.setBounds(119, 19, 69, 25);
		panCondicionEntrega.add(cbMes);
		
		JComboBox cbDia = new JComboBox();
		cbDia.setFont(new Font("Arial", Font.PLAIN, 12));
		cbDia.setBounds(189, 19, 61, 25);
		panCondicionEntrega.add(cbDia);
		
		JComboBox cbAnio = new JComboBox();
		cbAnio.setFont(new Font("Arial", Font.PLAIN, 12));
		cbAnio.setBounds(252, 19, 61, 25);
		panCondicionEntrega.add(cbAnio);
		
		JPanel pHorarioEntrega = new JPanel();
		pHorarioEntrega.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Horario de entrega", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pHorarioEntrega.setBounds(362, 63, 223, 68);
		panCondicionEntrega.add(pHorarioEntrega);
		pHorarioEntrega.setLayout(null);
		
		ButtonGroup grupoHorario = new ButtonGroup();
		JRadioButton rbManiana = new JRadioButton("Ma\u00F1ana");
		rbManiana.setFont(new Font("Arial", Font.PLAIN, 12));
		rbManiana.setBounds(27, 27, 72, 23);
		grupoHorario.add(rbManiana);
		pHorarioEntrega.add(rbManiana);
		
		JRadioButton rbTarde = new JRadioButton("Tarde");
		rbTarde.setFont(new Font("Arial", Font.PLAIN, 12));
		rbTarde.setBounds(124, 27, 72, 23);
		grupoHorario.add(rbTarde);
		pHorarioEntrega.add(rbTarde);
		
		JPanel pCondicionEntrega = new JPanel();
		pCondicionEntrega.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Condici\u00F3n de Entrega", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pCondicionEntrega.setBounds(22, 63, 276, 68);
		panCondicionEntrega.add(pCondicionEntrega);
		pCondicionEntrega.setLayout(null);
		
		ButtonGroup grupoCondicionEntrega = new ButtonGroup();
		JRadioButton rdbtnRetirar = new JRadioButton("Retirar");
		rdbtnRetirar.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnRetirar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtDireccionRetiro.setEnabled(true);
			}
		});
		rdbtnRetirar.setBounds(16, 27, 109, 23);
		grupoCondicionEntrega.add(rdbtnRetirar);
		pCondicionEntrega.add(rdbtnRetirar);
		
		JRadioButton rdbtnEnviarAProveedor = new JRadioButton("Enviar a Proveedor");
		rdbtnEnviarAProveedor.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnEnviarAProveedor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDireccionRetiro.setEnabled(false);
			}
		});
		rdbtnEnviarAProveedor.setBounds(127, 27, 143, 23);
		grupoCondicionEntrega.add(rdbtnEnviarAProveedor);
		pCondicionEntrega.add(rdbtnEnviarAProveedor);
		
		JPanel panDetalles = new JPanel();
		panDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		Secciones.addTab("Detalles", null, panDetalles, null);
		panDetalles.setLayout(null);
		
		JScrollPane spDetalles = new JScrollPane();
		spDetalles.setBounds(10, 11, 598, 175);
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
		
		JButton btnCerrar = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCerrar.setBounds(528, 599, 120, 35);
		JpSolicitudDeCompra.add(btnCerrar);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmar.setBounds(398, 599, 120, 35);
		JpSolicitudDeCompra.add(btnConfirmar);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTotal.setBounds(562, 551, 86, 25);
		JpSolicitudDeCompra.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtMontoIVA = new JTextField();
		txtMontoIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMontoIVA.setBounds(562, 520, 86, 25);
		JpSolicitudDeCompra.add(txtMontoIVA);
		txtMontoIVA.setColumns(10);
		
		txtIVA = new JTextField();
		txtIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		txtIVA.setBounds(562, 489, 86, 25);
		JpSolicitudDeCompra.add(txtIVA);
		txtIVA.setColumns(10);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSubtotal.setBounds(562, 459, 86, 25);
		JpSolicitudDeCompra.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 412, 446, 168);
		JpSolicitudDeCompra.add(panel);
		panel.setLayout(null);
		panel.setEnabled(true);
		
		
		JButton btnConfirmarRecepcion = new JButton("Confirmar", new ImageIcon ("Imagenes/ok.png"));
		btnConfirmarRecepcion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmarRecepcion.setBounds(10, 11, 120, 35);
		panel.add(btnConfirmarRecepcion);
		
		JButton btnRechazarRecepcion = new JButton("Rechazar", new ImageIcon ("Imagenes/no.png"));
		btnRechazarRecepcion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRechazarRecepcion.setBounds(10, 55, 120, 35);
		panel.add(btnRechazarRecepcion);
		
		JButton btnIncompleta = new JButton("Incompleta", new ImageIcon ("Imagenes/incompleto.png"));
		btnIncompleta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnIncompleta.setBounds(10, 101, 120, 35);
		panel.add(btnIncompleta);
		
		TextArea txtDescripcionIncidencia = new TextArea();
		txtDescripcionIncidencia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescripcionIncidencia.setBounds(143, 10, 293, 149);
		panel.add(txtDescripcionIncidencia);
		
		JLabel lbSubtotal = new JLabel("Subtotal:");
		lbSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
		lbSubtotal.setBounds(492, 464, 68, 14);
		JpSolicitudDeCompra.add(lbSubtotal);
		
		JLabel lbIVA = new JLabel("% IVA:");
		lbIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		lbIVA.setBounds(492, 494, 68, 14);
		JpSolicitudDeCompra.add(lbIVA);
		
		JLabel lbMontoIVA = new JLabel("Monto IVA:");
		lbMontoIVA.setFont(new Font("Arial", Font.PLAIN, 12));
		lbMontoIVA.setBounds(492, 526, 68, 14);
		JpSolicitudDeCompra.add(lbMontoIVA);
		
		JLabel lbTotal = new JLabel("TOTAL:");
		lbTotal.setFont(new Font("Arial", Font.BOLD, 12));
		lbTotal.setBounds(492, 556, 68, 14);
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
		// TODO Auto-generated method stub
		
	}
	};
	



	