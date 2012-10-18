package vista_Controlador;

import javax.swing.JInternalFrame;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
import Modelo.ConexionDB;

import java.awt.Color;
import java.sql.ResultSet;


public class Adm_Cliente extends JInternalFrame 
{
	private JTable tablaDatosCliente;
	private JTable tablaContactoCliente;
	
	public Adm_Cliente() 
	{
		super ("Administracion de Clientes", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		getContentPane().setLayout(null);
		
		JButton button = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
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
				boolean todoOK=true, todoOKContacto=true;
				boolean result = true, cuitOK=true;
				String Nro_Cliente = "";
				String razon_social = "";
				String cuit = "";
				String cond_iva = "";
				String direccion = "";
				String telefono = "";
				String mail = "";
				
				String nombre_contacto = "";
				String telefono_contacto = "";
				String mail_contacto = "";
				String direccion_entrega = "";
				
				//Agregar clientes nuevos
				Integer cantFilasDatos = tablaDatosCliente.getRowCount();
				for (int i =0; i < cantFilasDatos; i++) 
 {
					Nro_Cliente = tablaDatosCliente.getValueAt(i, 0).toString();
					razon_social = tablaDatosCliente.getValueAt(i, 1)
							.toString();
					cuit = tablaDatosCliente.getValueAt(i, 2).toString();
					cond_iva = tablaDatosCliente.getValueAt(i, 3).toString();
					direccion = tablaDatosCliente.getValueAt(i, 4).toString();
					telefono = tablaDatosCliente.getValueAt(i, 5).toString();
					mail = tablaDatosCliente.getValueAt(i, 6).toString();
					boolean activo = (Boolean) tablaDatosCliente.getValueAt(i,
							7);

					nombre_contacto = tablaContactoCliente.getValueAt(i, 1)
							.toString();
					telefono_contacto = tablaContactoCliente.getValueAt(i, 2)
							.toString();
					mail_contacto = tablaContactoCliente.getValueAt(i, 3)
							.toString();
					direccion_entrega = tablaContactoCliente.getValueAt(i, 4)
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
							&& !direccion_entrega.equals("");

					if (todoOK == false || todoOKContacto == false) {
						result = false;
						if (!todoOK) {
							JOptionPane.showMessageDialog(null,
									"Falta completar datos del cliente");
							break;
						} else {
							JOptionPane
									.showMessageDialog(null,
											"Falta completar datos del contacto de cliente");
							break;
						}
					} else if (cuit.length() == 11 && Metodos.esNumero(cuit)) {
						if (Nro_Cliente.equals("")) {
							result = true;
							Cliente cli = new Cliente(razon_social, cuit,
									cond_iva, direccion, telefono, mail,
									nombre_contacto, telefono_contacto,
									mail_contacto, direccion_entrega, activo);
							result = result && cli.Alta();
						} else {
							Cliente.updateDatosCliente(Nro_Cliente,
									razon_social, cuit, cond_iva, direccion,
									telefono, mail, activo);
							Cliente.updateDatosContactoCliente(Nro_Cliente,
									nombre_contacto, telefono_contacto,
									mail_contacto, direccion_entrega);
						}
					} else {
						cuitOK=false;
						result = false;
					}

				}
				if(result){
					JOptionPane.showMessageDialog(null,"Se guardaron los cambios que realizo");
					Actualizar();
				}else{
					//JOptionPane.showMessageDialog(null,"No se han guardado todos los cambios. Verifique");
				}
				if(!cuitOK){
					JOptionPane.showMessageDialog(null,"ERROR! El CUIT deben ser 11 digitos numéricos seguidos");
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
				
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatosCliente.getModel();
				Object nuevaFilaDatos[]= {"","","","","","","",true};
				tablaTempDatos.addRow(nuevaFilaDatos);
				
				DefaultTableModel tablaTempContacto = (DefaultTableModel) tablaContactoCliente.getModel();
				Object nuevaFilaContacto[]= {"","","","",""};
				tablaTempContacto.addRow(nuevaFilaContacto);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		//
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-30, d.height-190);

		getContentPane().add(tabbedPane);
		// DATOS
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		panelDatos.setLayout(null);
		
		tabbedPane.addTab("Datos                         ",  new ImageIcon ("Imagenes/datos.png"), panelDatos, null);

		JScrollPane spDatosCliente = new JScrollPane();
		spDatosCliente.setViewportBorder(null);
		spDatosCliente.setBounds(0, 0, d.width-193, d.height-190);
		panelDatos.add(spDatosCliente);
		
		tablaDatosCliente = new JTable();
		spDatosCliente.setViewportView(tablaDatosCliente);
		
		tablaDatosCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Mail", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] 
			{
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
			{
					false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		tablaDatosCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDatosCliente.getColumnModel().getColumn(1).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaDatosCliente.getColumnModel().getColumn(2).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(2).setPreferredWidth(102);
		tablaDatosCliente.getColumnModel().getColumn(3).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(3).setPreferredWidth(101);
		tablaDatosCliente.getColumnModel().getColumn(4).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(4).setPreferredWidth(225);
		tablaDatosCliente.getColumnModel().getColumn(5).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(6).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(6).setPreferredWidth(137);
		tablaDatosCliente.getColumnModel().getColumn(7).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(7).setPreferredWidth(15);
		tablaDatosCliente.getTableHeader().setReorderingAllowed(false);
		
		
		
		
		// CONTACTO
		JPanel panelContacto = new JPanel();
		panelContacto.setBorder
		(
			new LineBorder
			(
				new Color(0, 0, 0)
			)
		);
		panelContacto.setLayout(null);
		
		tabbedPane.addTab("Contacto                   ",  new ImageIcon ("Imagenes/contacto.png"), panelContacto, null);
			
		JScrollPane spClienteContacto = new JScrollPane();
		spClienteContacto.setBounds(0, 0, d.width-193, d.height-190);
		panelContacto.add(spClienteContacto);
		
		tablaContactoCliente = new JTable();
		spClienteContacto.setViewportView(tablaContactoCliente);
		tablaContactoCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Nombre", "Telefono", "Mail", "Direccion de entrega"
			}
		) {
			Class[] columnTypes = new Class[] 
			{
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
			{
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		tablaContactoCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(0).setPreferredWidth(15);
		tablaContactoCliente.getColumnModel().getColumn(1).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(1).setPreferredWidth(195);
		tablaContactoCliente.getColumnModel().getColumn(2).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaContactoCliente.getColumnModel().getColumn(3).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(3).setPreferredWidth(130);
		tablaContactoCliente.getColumnModel().getColumn(4).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(4).setPreferredWidth(240);
		tablaContactoCliente.getTableHeader().setReorderingAllowed(false);
		this.setFilas();
		
	}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_cliente, razon_social, cuit, cond_iva, direccion,telefono,mail, activo from cliente");
		
			Integer CantColumnas=8;
			Object datos[] = new Object[CantColumnas];
			try 
			{
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatosCliente.getModel();
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
				e.printStackTrace();
			}
			
			
			CantColumnas=5;
			Object contactos[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_cliente, nombre_contacto, telefono_contacto, mail_contacto, direccion_entrega from cliente");
			
			try 
			{
				DefaultTableModel tablaTempContactos = (DefaultTableModel) tablaContactoCliente.getModel();
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
	
		//devuelve true si no esta vacia la celda (fila,columna) de la tabla pasada como parametro
		public static boolean sinVarVacios(JTable tabla, Integer fila, Integer columna){
			boolean ok= true;
			ok= ok && !tabla.getValueAt(fila, columna).toString().equals("");
			return ok;
		}
	
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaDatosCliente.getModel());
			Metodos.borrarFilas((DefaultTableModel)tablaContactoCliente.getModel());
			setFilas();
		}
}

