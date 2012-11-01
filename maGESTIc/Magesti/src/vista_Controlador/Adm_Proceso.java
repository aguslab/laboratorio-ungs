package vista_Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Proceso;

public class Adm_Proceso extends JInternalFrame 
{
	private JTable tablaProcesos;
	
	public Adm_Proceso() 
	{
		
		super ("Administracion de Procesos", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		getContentPane().setLayout(null);
		
		JButton btnCerrar = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCerrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setVisible (false);
				dispose();
			}
		});
		btnCerrar.setBounds(10, d.height-250, 120, 30);
		getContentPane().add(btnCerrar);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-210, 120, 30);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean todoOKCalidad = true, todoOKFormato = true, todoOKVariante = true;
				boolean result = true;
				String nro_Proceso = "";
				String nombreProceso = "";
				Boolean activo = true;
				
				//Agregar calidades nuevas
				Integer cantFilasCalidad = tablaProcesos.getRowCount();
				for (int i =0; i < cantFilasCalidad; i++) 
				{
					nro_Proceso = tablaProcesos.getValueAt(i, 0).toString();
					nombreProceso = tablaProcesos.getValueAt(i, 1).toString();
					activo = (Boolean) tablaProcesos.getValueAt(i, 2);

					todoOKCalidad = todoOKCalidad && !nombreProceso.equals("");
					
					if (todoOKCalidad == false) 
					{
						result = false;
						JOptionPane.showMessageDialog(null, "Falta completar datos de la calidad");
						break;
					} 
					else if (todoOKCalidad) 
					{
						if (nro_Proceso.equals("")) 
						{
							result = true;
							Proceso pro = new Proceso(nro_Proceso,activo);
							result = result && pro.Alta();
						} 
						else 
						{
							Proceso.updateProceso(nro_Proceso,nombreProceso,activo);
						}
					} 
					else 
					{
						result = false;
					}
				}
				
				if(result)
				{
					JOptionPane.showMessageDialog(null,"Se guardaron los cambios realizados");
					Actualizar();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"No se han guardado todos los cambios. Verifique");
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
				DefaultTableModel tablaTemp = (DefaultTableModel) tablaProcesos.getModel();
				Object nuevaFila[]= {"","",true};
				tablaTemp.addRow(nuevaFila);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		//
		final JTabbedPane tabProcesos = new JTabbedPane(JTabbedPane.LEFT);
		tabProcesos.setBounds(10, 11, d.width-846, d.height-190);
		getContentPane().add(tabProcesos);
		
		JPanel panelProceso = new JPanel();
		panelProceso.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		panelProceso.setLayout(null);
		
		tabProcesos.addTab("Procesos                         ", null, panelProceso, null);

		JScrollPane spProceso = new JScrollPane();
		spProceso.setViewportBorder(null);
		spProceso.setBounds(0, 0, d.width-1000, d.height-190);
		panelProceso.add(spProceso);
		
		//Tabla Calidad
		
		tablaProcesos = new JTable();
		spProceso.setViewportView(tablaProcesos);
		
		tablaProcesos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Nombre", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaProcesos.getColumnModel().getColumn(0).setResizable(false);
		tablaProcesos.getColumnModel().getColumn(0).setPreferredWidth(55);
		tablaProcesos.getColumnModel().getColumn(1).setResizable(false);
		tablaProcesos.getColumnModel().getColumn(1).setPreferredWidth(233);
		tablaProcesos.getColumnModel().getColumn(2).setResizable(false);
		tablaProcesos.getColumnModel().getColumn(2).setPreferredWidth(29);
		tablaProcesos.getTableHeader().setReorderingAllowed(false);
		this.setFilas();
	}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT * FROM proceso");
		
			Integer CantColumnas = 3;
			Object datosProceso[] = new Object[CantColumnas];
			try 
			{
				DefaultTableModel tablaTempProceso = (DefaultTableModel) tablaProcesos.getModel();
				while (result.next()) 
				{
					for (int i = 0; i < CantColumnas; i++) 
					{
						datosProceso[i] = result.getObject(i + 1);
					}
					tablaTempProceso.addRow(datosProceso);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
		//devuelve true si no esta vacia la celda (fila,columna) de la tabla pasada como parametro
		public static boolean sinVarVacios(JTable tabla, Integer fila, Integer columna)
		{
			boolean ok= true;
			ok= ok && !tabla.getValueAt(fila, columna).toString().equals("");
			return ok;
		}
	
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaProcesos.getModel());
			setFilas();
		}
}
