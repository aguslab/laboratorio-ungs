package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Inmueble extends JInternalFrame {
	private JTable tablaInmuebles;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Inmueble frame = new Inmueble();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Inmueble() 
	{
		setTitle("Administraci\u00F3n de Inmuebles");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 440, 184);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 440, 173);
		panel.add(scrollPane);
		
		tablaInmuebles = new JTable();
		tablaInmuebles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Duenio", "Alquiler", "Provincia", "Municipio", "Localidad", "Codigo Postal", "Calle", "Numero", "Piso", "Superficie Total", "Superficie Cubierta", "Cant. Habitaciones", "Cochera", "Pileta", "Cant. Banios", "Estado Edificio", "Telefono", "Internet/WIFI"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Integer.class, Boolean.class, Boolean.class, Integer.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaInmuebles.getColumnModel().getColumn(0).setPreferredWidth(76);
		tablaInmuebles.getColumnModel().getColumn(1).setPreferredWidth(82);
		tablaInmuebles.getColumnModel().getColumn(10).setPreferredWidth(91);
		tablaInmuebles.getColumnModel().getColumn(11).setPreferredWidth(103);
		tablaInmuebles.getColumnModel().getColumn(12).setPreferredWidth(105);
		tablaInmuebles.getColumnModel().getColumn(16).setPreferredWidth(83);
		tablaInmuebles.getColumnModel().getColumn(18).setPreferredWidth(79);
		scrollPane.setViewportView(tablaInmuebles);
		tablaInmuebles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 236, 440, 29);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("  Recuerde que cada fila es un inmueble (no puede editar la columna \"N\u00FAmero\")");
		panel_1.add(lblNewLabel, BorderLayout.WEST);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{	
				DefaultTableModel temp = (DefaultTableModel) tablaInmuebles.getModel();
				if(temp.getRowCount()>0){
					temp.removeRow(tablaInmuebles.getSelectedRow());	
				}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
				}
			}
		});
		btnBorrar.setBounds(10, 202, 89, 23);
		getContentPane().add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				dispose();
			}
		});
		btnCancelar.setBounds(341, 202, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(242, 202, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaInmuebles.getModel();
				Object nuevaFila[]= {null,"","","","","","","","","",0.0,0.0,false,false,0,0,"",false};
				tablaTempDatos.addRow(nuevaFila);
			}
		});
		btnAgregar.setBounds(143, 202, 89, 23);
		getContentPane().add(btnAgregar);

	}
}
