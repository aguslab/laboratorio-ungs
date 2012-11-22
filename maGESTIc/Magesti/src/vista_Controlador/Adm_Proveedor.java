package vista_Controlador;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Proveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class Adm_Proveedor extends JInternalFrame 
{
	private JTable tablaDatos;
	private JTable tablaContacto;
	private JTabbedPane tabSecciones; 
	private boolean permitir=true;
	public Adm_Proveedor() 
	{
		super ("Administracion de Proveedores", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible (false);
				dispose();
			}
		});
		button.setBounds(10, d.height-250, 120, 30);
		getContentPane().add(button);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-210, 120, 30);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				boolean todoOK = true, todoOKContacto = true;
				boolean result = true, cuitOK = true;

				String id = "";
				String razon_social = "";
				String cuit = "";
				String cond_iva = "";
				String direccion = "";
				String telefono = "";
				String mail = "";

				String nombre_contacto = "";
				String telefono_contacto = "";
				String mail_contacto = "";
				String direccion_retiro = "";

				if (ExcedeLargoNombre()) {
					result = false;
					JOptionPane
							.showMessageDialog(
									null,
									"La longitud máxima del nombre de un proveedor o su contacto no puede exceder los 100 Caracteres\nNo exceda el límite, por favor");
				} else if (ExcedeLargoCondIVA()) {
					result = false;
					JOptionPane
							.showMessageDialog(
									null,
									"La longitud máxima de la condicion de IVA no puede exceder los 50 Caracteres\nNo exceda el límite, por favor");
				} else if (ExcedeLargoDireccion()) {
					result = false;
					JOptionPane
							.showMessageDialog(
									null,
									"La longitud máxima de la direccion de un proveedor o su contacto no puede exceder los 100 Caracteres\nNo exceda el límite, por favor");
				} else if (ExcedeLargoTelefono()) {
					result = false;
					JOptionPane
							.showMessageDialog(null,
									"El número de Teléfono de un proveedor o su contacto no puede ser tan extenso");
				} else if (ExcedeLargoMail()) {
					result = false;
					JOptionPane
							.showMessageDialog(
									null,
									"La longitud máxima de la direccion de mail de un proveedor o su contacto no puede exceder los 50 Caracteres\nNo exceda el límite, por favor");
				} else {

					// Agregar proveedores nuevos
					Integer cantFilasDatos = tablaDatos.getRowCount();
					for (int i = 0; i < cantFilasDatos; i++) {
						id = tablaDatos.getValueAt(i, 0).toString();
						razon_social = tablaDatos.getValueAt(i, 1).toString();
						cuit = tablaDatos.getValueAt(i, 2).toString();
						cond_iva = tablaDatos.getValueAt(i, 3).toString();
						direccion = tablaDatos.getValueAt(i, 4).toString();
						telefono = tablaDatos.getValueAt(i, 5).toString();
						mail = tablaDatos.getValueAt(i, 6).toString();
						boolean activo = (Boolean) tablaDatos.getValueAt(i, 7);

						nombre_contacto = tablaContacto.getValueAt(i, 1)
								.toString();
						telefono_contacto = tablaContacto.getValueAt(i, 2)
								.toString();
						mail_contacto = tablaContacto.getValueAt(i, 3)
								.toString();
						direccion_retiro = tablaContacto.getValueAt(i, 4)
								.toString();

						todoOK = todoOK && !razon_social.equals("");
						todoOK = todoOK && !cuit.equals("");
						todoOK = todoOK && !cond_iva.equals("");
						todoOK = todoOK && !direccion.equals("");
						todoOK = todoOK && !telefono.equals("");
						todoOK = todoOK && !mail.equals("");

						todoOKContacto = todoOKContacto
								&& !nombre_contacto.equals("");
						todoOKContacto = todoOKContacto
								&& !telefono_contacto.equals("");
						todoOKContacto = todoOKContacto
								&& !mail_contacto.equals("");
						todoOKContacto = todoOKContacto
								&& !direccion_retiro.equals("");

						if (todoOK == false || todoOKContacto == false) {
							result = false;
							if (!todoOK) {
								JOptionPane.showMessageDialog(null,
										"Falta completar datos del Proveedor");
								tabSecciones.setSelectedIndex(0);
								break;
							} else {
								JOptionPane
										.showMessageDialog(null,
												"Falta completar datos del contacto de Proveedor");
								tabSecciones.setSelectedIndex(1);
								break;
							}
						} else if (cuit.length() == 11
								&& Metodos.esNumero(cuit)) {
							if (id.equals("")) {
								result = true;
								Proveedor proveedor = new Proveedor(
										razon_social, cuit, cond_iva,
										direccion, telefono, mail,
										nombre_contacto, telefono_contacto,
										mail_contacto, direccion_retiro, activo);
								result = result && proveedor.Alta();
							} else {
								Proveedor.updateDatosProveedor(id,
										razon_social, cuit, cond_iva,
										direccion, telefono, mail, activo);
								Proveedor.updateDatosContactoProveedor(id,
										nombre_contacto, telefono_contacto,
										mail_contacto, direccion_retiro);
							}
						} else {
							cuitOK = false;
							result = false;
						}
					}

				}
				if (result) {
					JOptionPane.showMessageDialog(null,
							"Se guardaron los cambios que realizo");
					permitir=true;
					Actualizar();
				} else {
					// JOptionPane.showMessageDialog(null,"No se han guardado todos los cambios. Verifique");
				}
				if (!cuitOK) {
					JOptionPane
							.showMessageDialog(null,
									"ERROR! El CUIT deben ser 11 digitos numéricos seguidos");
					tabSecciones.setSelectedIndex(0);
				}

			}
		}
		);
		getContentPane().add(btnConfirmar);
		
		JButton btnAgregar = new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));

		btnAgregar.setBounds(10, d.height-290, 120, 30);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(permitir){
					permitir=false;
					DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatos.getModel();
					Object nuevaFilaDatos[]= {"","","","","","","",true};
					tablaTempDatos.addRow(nuevaFilaDatos);
					
					DefaultTableModel tablaTempContacto = (DefaultTableModel) tablaContacto.getModel();
					Object nuevaFilaContacto[]= {"","","","",""};
					tablaTempContacto.addRow(nuevaFilaContacto);
				}else{
					JOptionPane
					.showMessageDialog(null,
							"Sólo puede agregar de a una fila por vez.\nAlmacene la fila anterior y luego agregue una nueva");
				}
					
			}
		}
		);
		getContentPane().add(btnAgregar);

		tabSecciones = new JTabbedPane(JTabbedPane.LEFT);
		tabSecciones.setBounds(10, 11, d.width-30, d.height-190);
		getContentPane().add(tabSecciones);
		
		JPanel panDatos = new JPanel();
		panDatos.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		tabSecciones.addTab("Datos                         ",  new ImageIcon ("Imagenes/datos.png"), panDatos, null);
		panDatos.setLayout(null);
		
		JScrollPane spDatos = new JScrollPane();
		spDatos.setBounds(0, 0, d.width-193, d.height-190);
		panDatos.add(spDatos);
		
		tablaDatos = new JTable();
		spDatos.setViewportView(tablaDatos);
		tablaDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Email", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(102);
		tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(101);
		tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(225);
		tablaDatos.getColumnModel().getColumn(6).setPreferredWidth(137);
		tablaDatos.getColumnModel().getColumn(7).setPreferredWidth(15);
		spDatos.setViewportView(tablaDatos);
		
		tablaDatos.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaDatos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaDatos.getTableHeader().setReorderingAllowed(false);
		
		JPanel panContacto = new JPanel();
		panContacto.setBorder
		(
			new LineBorder
			(
				new Color(0, 0, 0)
			)
		);
		tabSecciones.addTab("Contacto                   ", new ImageIcon ("Imagenes/contacto.png"), panContacto, null);
		panContacto.setLayout(null);
		
		JScrollPane spContacto = new JScrollPane();
		spContacto.setBounds(0, 0, d.width-193, d.height-190);
		panContacto.add(spContacto);
		
		tablaContacto = new JTable();
		tablaContacto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Nombre", "Telefono", "Email", "Direccion de retiro"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaContacto.getColumnModel().getColumn(0).setPreferredWidth(15);
		tablaContacto.getColumnModel().getColumn(1).setPreferredWidth(195);
		tablaContacto.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaContacto.getColumnModel().getColumn(3).setPreferredWidth(130);
		tablaContacto.getColumnModel().getColumn(4).setPreferredWidth(240);
		spContacto.setViewportView(tablaContacto);
		
		tablaContacto.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaContacto.getTableHeader().setReorderingAllowed(false);
		setFilas();

	}
	
		private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_proveedor, razon_social, cuit, cond_iva, direccion,telefono,mail,activo from proveedor");
		
			Integer CantColumnas=8;
			Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

			try 
			{
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatos.getModel();
				while (result.next()) 
				{
					
					for (int i = 0; i < CantColumnas; i++) 
					{
						datos[i] = result.getObject(i + 1);
					}
					tablaTempDatos.addRow(datos);
				}
			} 
			catch (Exception e) 
			{
			}
			
			
			CantColumnas=5;
			Object contactos[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_proveedor,nombre_contacto, telefono_contacto, mail_contacto, direccion_retiro from proveedor");
			
			try 
			{
				DefaultTableModel tablaTempContactos = (DefaultTableModel) tablaContacto.getModel();
				while (result.next()) 
				{
					
					for (int i = 0; i < CantColumnas; i++) 
					{
						contactos[i] = result.getObject(i + 1);
					}
					tablaTempContactos.addRow(contactos);
				}
			} 
			catch (Exception e) 
			{
			}
		}
		
		private boolean ExcedeLargoNombre() {

			for (int i = 0; i < tablaDatos.getRowCount(); i++) {
				if(tablaDatos.getValueAt(i, 1).toString().length()>100 || tablaContacto.getValueAt(i, 1).toString().length()>100){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoCondIVA() {

			for (int i = 0; i < tablaDatos.getRowCount(); i++) {
				if(tablaDatos.getValueAt(i, 3).toString().length()>50){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoDireccion() {

			for (int i = 0; i < tablaDatos.getRowCount(); i++) {
				if(tablaDatos.getValueAt(i, 4).toString().length()>100 || tablaContacto.getValueAt(i, 4).toString().length()>100){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoTelefono() {

			for (int i = 0; i < tablaDatos.getRowCount(); i++) {
				if(tablaDatos.getValueAt(i, 5).toString().length()>30 || tablaContacto.getValueAt(i, 2).toString().length()>30){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoMail() {

			for (int i = 0; i < tablaDatos.getRowCount(); i++) {
				if(tablaDatos.getValueAt(i, 6).toString().length()>50 || tablaContacto.getValueAt(i, 3).toString().length()>50){
					return true;
				}
			}
			
			return false;
		}
		
		
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaDatos.getModel());
			Metodos.borrarFilas((DefaultTableModel)tablaContacto.getModel());
			setFilas();
		}

}
