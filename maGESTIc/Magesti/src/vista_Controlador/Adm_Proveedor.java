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

public class Adm_Proveedor extends JInternalFrame 
{
	private JTable tablaDatos;
	private JTable tablaContacto;
	
	public Adm_Proveedor() 
	{
		super ("Administracion de Proveedor", false, true, false, true);
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
				GuardarDatos();
				Actualizar();
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
				
					DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatos.getModel();
					Object nuevaFilaDatos[]= {"","","","","","","",true};
					tablaTempDatos.addRow(nuevaFilaDatos);
					
					DefaultTableModel tablaTempContacto = (DefaultTableModel) tablaContacto.getModel();
					Object nuevaFilaContacto[]= {"","","","",""};
					tablaTempContacto.addRow(nuevaFilaContacto);
			}
		}
		);
		getContentPane().add(btnAgregar);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-30, d.height-190);
		getContentPane().add(tabbedPane);
		
		JPanel panDatos = new JPanel();
		panDatos.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		tabbedPane.addTab("Datos                         ",  new ImageIcon ("Imagenes/datos.png"), panDatos, null);
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
				"Nro", "Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Mail", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaDatos.getColumnModel().getColumn(0).setResizable(false);
		tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDatos.getColumnModel().getColumn(1).setResizable(false);
		tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaDatos.getColumnModel().getColumn(2).setResizable(false);
		tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(102);
		tablaDatos.getColumnModel().getColumn(3).setResizable(false);
		tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(101);
		tablaDatos.getColumnModel().getColumn(4).setResizable(false);
		tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(225);
		tablaDatos.getColumnModel().getColumn(5).setResizable(false);
		tablaDatos.getColumnModel().getColumn(6).setResizable(false);
		tablaDatos.getColumnModel().getColumn(6).setPreferredWidth(137);
		tablaDatos.getColumnModel().getColumn(7).setResizable(false);
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
		tabbedPane.addTab("Contacto                   ", new ImageIcon ("Imagenes/contacto.png"), panContacto, null);
		panContacto.setLayout(null);
		
		JScrollPane spContacto = new JScrollPane();
		spContacto.setBounds(0, 0, d.width-193, d.height-190);
		panContacto.add(spContacto);
		
		tablaContacto = new JTable();
		tablaContacto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Nombre", "Telefono", "Mail", "Direccion de retiro"
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
		tablaContacto.getColumnModel().getColumn(0).setResizable(false);
		tablaContacto.getColumnModel().getColumn(0).setPreferredWidth(15);
		tablaContacto.getColumnModel().getColumn(1).setResizable(false);
		tablaContacto.getColumnModel().getColumn(1).setPreferredWidth(195);
		tablaContacto.getColumnModel().getColumn(2).setResizable(false);
		tablaContacto.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaContacto.getColumnModel().getColumn(3).setResizable(false);
		tablaContacto.getColumnModel().getColumn(3).setPreferredWidth(130);
		tablaContacto.getColumnModel().getColumn(4).setResizable(false);
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
		
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaDatos.getModel());
			Metodos.borrarFilas((DefaultTableModel)tablaContacto.getModel());
			setFilas();
		}
		
		private void GuardarDatos()
		{
			Integer cantFilasDatos = tablaDatos.getRowCount();
			for (int i = 0; i < cantFilasDatos; i++) 
			{

				String Nro_Proveedor = tablaDatos.getValueAt(i, 0).toString();
				String razon_social = tablaDatos.getValueAt(i, 1).toString();
				String CUIT = tablaDatos.getValueAt(i, 2).toString();
				String cond_IVA = tablaDatos.getValueAt(i, 3).toString();
				String direccion = tablaDatos.getValueAt(i, 4).toString();
				String telefono = tablaDatos.getValueAt(i, 5).toString();
				String mail = tablaDatos.getValueAt(i, 6).toString();
				System.out.println(mail);
				boolean activo = (Boolean) tablaDatos.getValueAt(i, 7);
				
				String nombre_contacto = tablaContacto.getValueAt(i, 1).toString();
				String telefono_contacto = tablaContacto.getValueAt(i, 2).toString();
				String mail_contacto = tablaContacto.getValueAt(i, 3).toString();
				String direccion_retiro = tablaContacto.getValueAt(i, 4).toString();
					
				if(Nro_Proveedor.equals(""))
				{
					Proveedor pro = new Proveedor(razon_social,CUIT,cond_IVA,direccion,telefono,
							mail,nombre_contacto,telefono_contacto,mail_contacto,direccion_retiro, activo);
					pro.Alta();
				}
				else
				{
					ConexionDB.getbaseDatos().ejecutar("UPDATE proveedor SET razon_social = "+ "'"+razon_social+"'" + ",cuit = " + CUIT + ",cond_iva = "
							+"'" +cond_IVA+"'" + ",direccion = " + "'"+direccion+"'" + ",telefono = " + "'"+telefono+"'" + ",mail = " + "'"+ mail + "'" + ",nombre_contacto =" +
							"'"+nombre_contacto+"'" + ",telefono_contacto = "+ telefono_contacto + ",mail_contacto = " + "'"+mail_contacto+"'" + ",direccion_retiro = " + "'"+direccion_retiro+"'"
							+ ",activo = " + activo + " WHERE id_proveedor =" + Integer.parseInt(Nro_Proveedor));
				}
			}
			JOptionPane.showMessageDialog(null,"Se guardaron los cambios realizados");
		}
}
