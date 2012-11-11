package vista_Controlador;


import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Formato_Papel;
import Modelo.Variante;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Adm_Atributos extends JInternalFrame 
{
	private JTable tablaCalidad;
	private JTable tablaFormato;
	private JTable tablaVariante;
	private Integer tabSeleccionado;
	
	public Adm_Atributos() 
	{
		
		super ("Administracion de Atributos del Papel", false, true, false, true);
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
				String nro_Calidad = "";
				String nombreCalidad = "";
				Boolean activoCal = true;
				String nro_Formato = "";
				String tamanioFormato = "";
				Boolean activoFor = true;
				String nro_Variante = "";
				String nombreVariante = "";
				Boolean activoVar = true;
				
				if(ExcedeLargoCalidad()){
					result = false;
					JOptionPane.showMessageDialog(null, "La longitud máxima del nombre de una Calidad es 30 Caracteres\nNo exceda el límite, por favor");
				}
				else if(ExcedeLargoFormato()){
					result = false;
					JOptionPane.showMessageDialog(null, "La longitud máxima de un Formato es 10 Caracteres\nNo exceda el límite, por favor");
				}
				else if(ErrorFormatoPapel()){
					result = false;
					JOptionPane.showMessageDialog(null, "El formato del tamaño de papel debe ser nxn\nPor favor, respete el formato");
				}
				else if(ExcedeLargoVariante()){
					result = false;
					JOptionPane.showMessageDialog(null, "La longitud máxima del nombre de una Variante es 30 Caracteres\nNo exceda el límite, por favor");
				}
				else {
					// Agregar calidades nuevas
					Integer cantFilasCalidad = tablaCalidad.getRowCount();
					for (int i = 0; i < cantFilasCalidad; i++) {
						nro_Calidad = tablaCalidad.getValueAt(i, 0).toString();
						nombreCalidad = tablaCalidad.getValueAt(i, 1)
								.toString();
						activoCal = (Boolean) tablaCalidad.getValueAt(i, 2);

						todoOKCalidad = todoOKCalidad
								&& !nombreCalidad.equals("");

						if (todoOKCalidad == false) {
							result = false;
							JOptionPane.showMessageDialog(null,
									"Falta completar datos de la calidad");
							break;
						} else if (todoOKCalidad) {
							if (nro_Calidad.equals("")) {
								result = true;
								Calidad cal = new Calidad(nombreCalidad,
										activoCal);
								result = result && cal.Alta();
							} else {
								Calidad.updateCalidad(nro_Calidad,
										nombreCalidad, activoCal);
							}
						} else {
							result = false;
						}
					}

					Integer cantFilasFormato = tablaFormato.getRowCount();
					for (int i = 0; i < cantFilasFormato; i++) {
						nro_Formato = tablaFormato.getValueAt(i, 0).toString();
						tamanioFormato = tablaFormato.getValueAt(i, 1)
								.toString();
						activoFor = (Boolean) tablaFormato.getValueAt(i, 2);

						todoOKFormato = todoOKFormato
								&& !tamanioFormato.equals("");

						if (todoOKFormato == false) {
							result = false;
							JOptionPane.showMessageDialog(null,
									"Falta completar datos del formato");
							break;
						} else if (todoOKFormato) {
							if (nro_Formato.equals("")) {
								result = true;
								Formato_Papel fto = new Formato_Papel(
										tamanioFormato, activoFor);
								result = result && fto.Alta();
							} else {
								Formato_Papel.updateFormato(nro_Formato,
										tamanioFormato, activoFor);
							}
						} else {
							result = false;
						}
					}

					Integer cantFilasVariante = tablaVariante.getRowCount();
					for (int i = 0; i < cantFilasVariante; i++) {
						nro_Variante = tablaVariante.getValueAt(i, 0)
								.toString();
						nombreVariante = tablaVariante.getValueAt(i, 1)
								.toString();
						activoVar = (Boolean) tablaVariante.getValueAt(i, 2);

						todoOKVariante = todoOKVariante
								&& !nombreVariante.equals("");

						if (todoOKVariante == false) {
							result = false;
							JOptionPane.showMessageDialog(null,
									"Falta completar datos de la variante");
							break;
						} else if (todoOKVariante) {
							if (nro_Variante.equals("")) {
								result = true;
								Variante var = new Variante(nombreVariante,
										activoVar);
								result = result && var.Alta();
							} else {
								Variante.updateVariante(nro_Variante,
										nombreVariante, activoVar);
							}
						} else {
							result = false;
						}
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
				if(tabSeleccionado == 0)
				{
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaCalidad.getModel();
					int cantFilas=tablaTemp.getRowCount();
					Object nuevaFila[]= {"","",true};
					tablaTemp.addRow(nuevaFila);
				}
				
				if(tabSeleccionado == 1)
				{
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaFormato.getModel();
					int cantFilas=tablaTemp.getRowCount();
					Object nuevaFila[]= {"","",true};
					tablaTemp.addRow(nuevaFila);
				}
				
				if(tabSeleccionado == 2)
				{
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaVariante.getModel();
					int cantFilas=tablaTemp.getRowCount();
					Object nuevaFila[]= {"","",true};
					tablaTemp.addRow(nuevaFila);
				}
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		//
		final JTabbedPane tabAtributos = new JTabbedPane(JTabbedPane.LEFT);
		tabAtributos.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent arg0) 
			{
				if (tabAtributos.getTitleAt(tabAtributos.getSelectedIndex()).equals("Calidades                         ")) 
			    {
					tabSeleccionado = 0;
			    }
				else if (tabAtributos.getTitleAt(tabAtributos.getSelectedIndex()).equals("Formatos                         ")) 
			    {
					tabSeleccionado = 1;
			    }
				else
				{
					tabSeleccionado = 2;
				}
			}
		});
		tabAtributos.setBounds(10, 11, (d.width/2)-28, d.height-190);
		getContentPane().add(tabAtributos);
		
		JPanel panelCalidad = new JPanel();
		panelCalidad.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		panelCalidad.setLayout(null);
		
		tabAtributos.addTab("Calidades                         ",  new ImageIcon ("Imagenes/papel.png"), panelCalidad, null);

		JScrollPane spCalidad = new JScrollPane();
		spCalidad.setViewportBorder(null);
		spCalidad.setBounds(0, 0, 465, 578);
		panelCalidad.add(spCalidad);
		
		//Tabla Calidad
		
		tablaCalidad = new JTable();
		spCalidad.setViewportView(tablaCalidad);
		
		tablaCalidad.setModel(new DefaultTableModel(
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
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaCalidad.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaCalidad.getColumnModel().getColumn(1).setResizable(false);
		tablaCalidad.getColumnModel().getColumn(1).setPreferredWidth(248);
		tablaCalidad.getColumnModel().getColumn(2).setPreferredWidth(15);
		tablaCalidad.setRowHeight(25);
		tablaCalidad.getTableHeader().setReorderingAllowed(false);
		
		
		
		
		// Tabla Formato
		JPanel panelFormato = new JPanel();
		panelFormato.setBorder
		(
			new LineBorder
			(
				new Color(0, 0, 0)
			)
		);
		panelFormato.setLayout(null);
		
		tabAtributos.addTab("Formatos                         ",new ImageIcon ("Imagenes/regla.png"), panelFormato, null);
			
		JScrollPane spFormato = new JScrollPane();
		spFormato.setBounds(0, 0, 465, 578);
		panelFormato.add(spFormato);
		
		tablaFormato = new JTable();
		spFormato.setViewportView(tablaFormato);
		tablaFormato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Tama\u00F1o", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaFormato.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaFormato.getColumnModel().getColumn(1).setResizable(false);
		tablaFormato.getColumnModel().getColumn(1).setPreferredWidth(248);
		tablaFormato.getColumnModel().getColumn(2).setPreferredWidth(15);
		tablaFormato.setRowHeight(25);
		tablaFormato.getTableHeader().setReorderingAllowed(false);
		
	
	// Tabla Variante
			JPanel panelVariante = new JPanel();
			panelVariante.setBorder
			(
				new LineBorder
				(
					new Color(0, 0, 0)
				)
			);
			panelVariante.setLayout(null);
			
			tabAtributos.addTab("Variantes                         ",new ImageIcon ("Imagenes/quimico.png"), panelVariante, null);
				
			JScrollPane spVariante = new JScrollPane();
			spVariante.setBounds(0, 0, 465, 578);
			panelVariante.add(spVariante);
			
			tablaVariante = new JTable();
			spVariante.setViewportView(tablaVariante);
			tablaVariante.setModel(new DefaultTableModel(
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
				boolean[] columnEditables = new boolean[] {
					false, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tablaVariante.getColumnModel().getColumn(0).setPreferredWidth(40);
			tablaVariante.getColumnModel().getColumn(1).setResizable(false);
			tablaVariante.getColumnModel().getColumn(1).setPreferredWidth(248);
			tablaVariante.getColumnModel().getColumn(2).setPreferredWidth(15);
			tablaVariante.setRowHeight(25);
			tablaVariante.getTableHeader().setReorderingAllowed(false);
			this.setFilas();
			
		}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT * FROM calidad");
		
			Integer CantColumnas = 3;
			Object datosCalidad[] = new Object[CantColumnas];
			try 
			{
				DefaultTableModel tablaTempCalidad = (DefaultTableModel) tablaCalidad.getModel();
				while (result.next()) 
				{
					for (int i = 0; i < CantColumnas; i++) 
					{
						datosCalidad[i] = result.getObject(i + 1);
					}
					tablaTempCalidad.addRow(datosCalidad);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Object datosFormato[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT * FROM formato_papel");
			
			try 
			{
				DefaultTableModel tablaTempFormato = (DefaultTableModel) tablaFormato.getModel();
				while (result.next()) 
				{
					for (int i = 0; i < CantColumnas; i++) 
					{
						datosFormato[i] = result.getObject(i + 1);
					}
					tablaTempFormato.addRow(datosFormato);
				}
			} 
			catch (Exception e) 
			{
			}
			
			Object datosVariante[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT * FROM variante");
			
			try 
			{
				DefaultTableModel tablaTempVariante = (DefaultTableModel) tablaVariante.getModel();
				while (result.next()) 
				{
					for (int i = 0; i < CantColumnas; i++) 
					{
						datosVariante[i] = result.getObject(i + 1);
					}
					tablaTempVariante.addRow(datosVariante);
				}
			} 
			catch (Exception e) 
			{
			}
		}
	

	
		private boolean ExcedeLargoCalidad() {

			for (int i = 0; i < tablaCalidad.getRowCount(); i++) {
				if(tablaCalidad.getValueAt(i, 1).toString().length()>30){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoFormato() {

			for (int i = 0; i < tablaFormato.getRowCount(); i++) {
				if((int) tablaFormato.getValueAt(i, 1).toString().length()>11){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ExcedeLargoVariante() {

			for (int i = 0; i < tablaVariante.getRowCount(); i++) {
				if(tablaVariante.getValueAt(i, 1).toString().length()>30){
					return true;
				}
			}
			
			return false;
		}
		
		private boolean ErrorFormatoPapel(){
			
			for (int i = 0; i < tablaFormato.getRowCount(); i++) {
				try {
					Metodos.getAncho(tablaFormato.getValueAt(i, 1).toString());
					Metodos.getAlto(tablaFormato.getValueAt(i, 1).toString());
				} catch (Exception e) {
					return true;
				}
			}
			
			return false;
		}
		
		
		
		
		private void Actualizar()
		{
			Metodos.borrarFilas((DefaultTableModel)tablaCalidad.getModel());
			Metodos.borrarFilas((DefaultTableModel)tablaFormato.getModel());
			Metodos.borrarFilas((DefaultTableModel)tablaVariante.getModel());
			setFilas();
		}
}
