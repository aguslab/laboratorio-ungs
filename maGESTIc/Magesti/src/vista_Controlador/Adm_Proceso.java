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
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Proceso;

public class Adm_Proceso extends JInternalFrame 
{
	private JTable tablaProcesos;
	private JButton btnUp,btnDown;
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
		
		
		
		
		btnUp = new JButton(new ImageIcon("Imagenes/Up_Button.png"));
		btnUp.setPressedIcon(new ImageIcon("Imagenes/Up_Button_pressed.png"));
		
				//Subir una fila
				btnUp.addActionListener(new ActionListener() 
				{
					
					public void actionPerformed(ActionEvent arg0) 
					{
						int filaSelec=tablaProcesos.getSelectedRow();
						try
						{	
							DefaultTableModel tempOE = (DefaultTableModel) tablaProcesos.getModel();
							if(tempOE.getRowCount()>0)
							{
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
									//cambio el nro de orden
									int f1=(Integer) tempOE.getValueAt(filaSelec, 1);
									int f2=(Integer) tempOE.getValueAt(filaSelec-1, 1);
									tempOE.setValueAt(f1, filaSelec-1, 1);
									tempOE.setValueAt(f2, filaSelec, 1);
									//intercambio filas
									tempOE.moveRow(filaSelec, filaSelec, filaSelec-1);
									//cambio la fila seleccionada por la que subi
									tablaProcesos.getSelectionModel().setSelectionInterval(filaSelec-1,filaSelec-1);
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
				
				btnDown = new JButton(new ImageIcon("Imagenes/Down_Button.png"));
				btnDown.setPressedIcon(new ImageIcon("Imagenes/Down_Button_pressed.png"));
				btnDown.setSelectedIcon(new ImageIcon("Imagenes/Down_Button_Selected.png"));
				
						
						//Bajar una fila
						btnDown.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent arg0) 
							{
								int filaSelec = tablaProcesos.getSelectedRow();
								try 
								{
									DefaultTableModel tempOE = (DefaultTableModel) tablaProcesos.getModel();
									if (tempOE.getRowCount() > 0) 
									{
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
											//cambio el nro de orden
											int f1=(Integer) tempOE.getValueAt(filaSelec, 1);
											int f2=(Integer) tempOE.getValueAt(filaSelec+1, 1);
											tempOE.setValueAt(f1, filaSelec+1, 1);
											tempOE.setValueAt(f2, filaSelec, 1);
											//intercambio filas
											tempOE.moveRow(filaSelec, filaSelec, filaSelec + 1);
											//cambio la fila seleccionada por la que baje
											tablaProcesos.getSelectionModel().setSelectionInterval(filaSelec+1,filaSelec+1);
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
						btnDown.setBounds(69, 412, 35, 35);
						getContentPane().add(btnDown);
				btnUp.setSelectedIcon(new ImageIcon("Imagenes/Up_Button_Selected.png"));
				btnUp.setBounds(24, 410, 35, 35);
				getContentPane().add(btnUp);
		btnCerrar.setBounds(10, d.height-270, 120, 30);
		getContentPane().add(btnCerrar);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-230, 120, 30);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean result = true;
				Integer orden = null;
				String nombreProceso = "";
				Boolean activo = true;
				Integer ultProc= Proceso.getUltProceso();
				if(tieneNombresVacios()){
					result = false;
					JOptionPane.showMessageDialog(null, "Falta completar nombre de proceso");
				}
				else if(tieneNombreRepetidos()){
					result = false;
					JOptionPane.showMessageDialog(null, "No puede haber dos procesos con igual nombre");
				}
				
				else {
					Integer cantFilasProcesos = tablaProcesos.getRowCount();
					for (int i = 0; i < cantFilasProcesos; i++) {
						Integer id= Integer.parseInt(tablaProcesos.getValueAt(i, 0).toString());
						orden = Integer.parseInt(tablaProcesos.getValueAt(i, 1).toString());
						nombreProceso = tablaProcesos.getValueAt(i, 2).toString();
						activo = (Boolean) tablaProcesos.getValueAt(i, 3);

						if (id > ultProc) {
							result = true;
							Proceso pro = new Proceso(nombreProceso,orden,activo);
							result = result && pro.Alta();
						} else {
							result = Proceso.updateProceso(id,nombreProceso, orden,activo);
						}
					}
				}
				if(result)
				{
					JOptionPane.showMessageDialog(null,"Se guardaron los cambios realizados");
					Actualizar();
				}

				
			}

			
		}
		);
		getContentPane().add(btnConfirmar);
		
		JButton btnAgregar = new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));
		btnAgregar.setBounds(10, d.height-310, 120, 30);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DefaultTableModel tablaTemp = (DefaultTableModel) tablaProcesos.getModel();
				int cantFilas=tablaTemp.getRowCount();
				Object nuevaFila[]= {(cantFilas+1),(cantFilas+1),"",true};
				tablaTemp.addRow(nuevaFila);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		//
		final JTabbedPane tabProcesos = new JTabbedPane(JTabbedPane.LEFT);
		tabProcesos.setBounds(10, 11, 652, 557);
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
		
		tabProcesos.addTab("Procesos                         ",  new ImageIcon ("Imagenes/engranaje.png"), panelProceso, null);

		JScrollPane spProceso = new JScrollPane();
		spProceso.setViewportBorder(null);
		spProceso.setBounds(0, 0,469,557);
		panelProceso.add(spProceso);
		
		//Tabla Calidad
		
		tablaProcesos = new JTable();
		spProceso.setViewportView(tablaProcesos);
		
		tablaProcesos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Nro", "Nombre", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaProcesos.getColumnModel().getColumn(0).setResizable(false);
		tablaProcesos.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaProcesos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaProcesos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaProcesos.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaProcesos.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablaProcesos.getColumnModel().getColumn(3).setPreferredWidth(30);
		tablaProcesos.setRowHeight(25);
		
		
		
		
		
		
		
		
		
		
		
		
		tablaProcesos.getTableHeader().setReorderingAllowed(false);
		this.setFilas();
	}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_proceso, orden,nombre, activo FROM proceso ORDER BY orden");
		
			Integer CantColumnas = 4;
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
	

	public boolean tieneNombresVacios() {
		for (int i = 0; i < tablaProcesos.getRowCount(); i++) {
			if(tablaProcesos.getValueAt(i, 2).toString().equals("")){
				return true;
			}
		}
		return false;
	}
		
//		private Integer getNroProc(String nro_Proceso) {
//			String proc = "";
//			for (int i = 0; nro_Proceso.charAt(i) != '-'; i++) {
//				proc = proc + nro_Proceso.charAt(i);
//			}
//
//			return Integer.parseInt(proc);
//		}
		
		private boolean tieneNombreRepetidos(){
			boolean tieneNombreRep=false;
			
			for(int i=0;i<tablaProcesos.getRowCount();i++){
				for(int j=i+1;j<tablaProcesos.getRowCount();j++){
					if(tablaProcesos.getValueAt(i, 2).toString().equalsIgnoreCase(tablaProcesos.getValueAt(j, 2).toString())){
						tieneNombreRep=true;
						break;
					}
				}
			}
			
			return tieneNombreRep;
		}
	
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaProcesos.getModel());
			setFilas();
		}
}
