package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Modelo.Calidad;
import Modelo.Detalle;
import Modelo.Formato_Papel;
import Modelo.Orden_Trabajo;
import Modelo.Recepcion_pedido;
import Modelo.Solicitud_compra;
import Modelo.Variante;

import java.awt.Font;

@SuppressWarnings("serial")



public class Recepcion_Pedido extends JInternalFrame implements ActionListener, Config
{
	private JTable tablaDetalles,tablaRecibido;
	private JPanel JpSolicitudDeCompra;
	private JButton btnCerrar,btnAceptar,btnSelecAll;
	private JScrollPane spDetalles,spRecibido ;
	
	
	
	public Recepcion_Pedido(final Integer id_SC, final Integer cantFilas, final SolicitudDeCompra SC) {
		super ("Solicitud de Compra (SC)", false, true, false, true);
		
		setSize (925, 300);
		
		getContentPane().setLayout(null);
		
		
		JpSolicitudDeCompra = new JPanel();
		JpSolicitudDeCompra.setBounds(0, 0, 915, 271);
		getContentPane().add(JpSolicitudDeCompra);
		JpSolicitudDeCompra.setLayout(null);
		
		
		
		
		btnCerrar = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCerrar.setBounds(241, 241, 120, 25);
		JpSolicitudDeCompra.add(btnCerrar);
		btnCerrar.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setBounds(111, 241, 120, 25);
		JpSolicitudDeCompra.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		spDetalles = new JScrollPane();
		spDetalles.setViewportBorder(null);
		spDetalles.setBounds(10, 11, 795, 219);
		JpSolicitudDeCompra.add(spDetalles);

		
		tablaDetalles = new JTable();
		tablaDetalles.setEnabled(false);
		tablaDetalles.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaDetalles.setShowGrid(false);
		tablaDetalles.setFont(new Font("Arial", Font.PLAIN, 12));
		tablaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
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
		});
		tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDetalles.getColumnModel().getColumn(1).setMaxWidth(200);
		tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(2).setPreferredWidth(120);
		tablaDetalles.getColumnModel().getColumn(3).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(3).setMaxWidth(200);
		tablaDetalles.getColumnModel().getColumn(4).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(4).setPreferredWidth(60);
		tablaDetalles.getColumnModel().getColumn(4).setMaxWidth(100);
		tablaDetalles.getColumnModel().getColumn(5).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(5).setPreferredWidth(60);
		tablaDetalles.getColumnModel().getColumn(5).setMaxWidth(100);
		tablaDetalles.getColumnModel().getColumn(6).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaDetalles.getColumnModel().getColumn(6).setMaxWidth(100);
		tablaDetalles.getColumnModel().getColumn(7).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(7).setPreferredWidth(110);
		tablaDetalles.getColumnModel().getColumn(7).setMaxWidth(110);
		tablaDetalles.getColumnModel().getColumn(8).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(8).setPreferredWidth(95);
		tablaDetalles.getColumnModel().getColumn(8).setMaxWidth(200);
		spDetalles.setViewportView(tablaDetalles);
		
		
		tablaDetalles.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		/*
		 * cargar tabla Detalles
		 */
		
		
		ArrayList<Integer> cantidad = Detalle.cantidadDeDetalle(id_SC);
		ArrayList<String> marca = Detalle.marcaDeDetalle(id_SC);
		ArrayList<Integer> id_Calidad = Detalle.calidadDeDetalle(id_SC);
		ArrayList<Integer> id_formato_Papel = Detalle.formato_papel_DeDetalle(id_SC);
		ArrayList<Integer> id_Variante = Detalle.varianteDeDetalle(id_SC);
		ArrayList<Integer> gramaje = Detalle.gramajeDeDetalle(id_SC);
		ArrayList<Double> precio_Unitario= Detalle.precioUnitarioDeDetalle(id_SC);
		ArrayList<String> unidad_medida= Detalle.unidadMedidaDeDetalle(id_SC);
		ArrayList<Double> importe= Detalle.importeDeDetalle(id_SC);
		
		
		DefaultTableModel temp = (DefaultTableModel) tablaDetalles.getModel();
		Object nuevaFilaDetalles[]= {"",""};
		
		final Integer[] idDetalle= new Integer[cantFilas];
		for (int i = 0; i < cantFilas; i++) 
		{
			temp.addRow(nuevaFilaDetalles);
			temp.setValueAt(cantidad.get(i), i, 0);
			temp.setValueAt(marca.get(i), i, 1);
			temp.setValueAt(Calidad.getNombre(id_Calidad.get(i)), i, 2);	
			temp.setValueAt(Variante.getNombre(id_Variante.get(i)), i, 3);
			temp.setValueAt((Formato_Papel.getTamanio(id_formato_Papel.get(i))), i, 4);
			temp.setValueAt(gramaje.get(i), i, 5);
			temp.setValueAt(precio_Unitario.get(i), i, 6);
			temp.setValueAt(unidad_medida.get(i), i, 7);
			temp.setValueAt(importe.get(i), i, 8);
			
			idDetalle[i]=Detalle.dameIdDetalle(id_SC, cantidad.get(i), marca.get(i), id_Calidad.get(i), id_formato_Papel.get(i), id_Variante.get(i), gramaje.get(i), precio_Unitario.get(i), unidad_medida.get(i), importe.get(i));
		}
		
		
		/*
		 * cargar tabla recibidos
		 */
				
		
		spRecibido = new JScrollPane();
		spRecibido.setViewportBorder(null);
		spRecibido.setBounds(800, 11, 88, 219);
		JpSolicitudDeCompra.add(spRecibido);
		
		tablaRecibido = new JTable();
		tablaRecibido.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaRecibido.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Recibido"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaRecibido.getColumnModel().getColumn(0).setResizable(false);
		tablaRecibido.getColumnModel().getColumn(0).setMaxWidth(100);
		spRecibido.setViewportView(tablaRecibido);
		
		DefaultTableModel tempRecibido = (DefaultTableModel) tablaRecibido.getModel();
		Object nuevaFila[]= {false};
		
		for (int i = 0; i < cantFilas; i++) 
		{
			tempRecibido.addRow(nuevaFila);
			Integer id_detalle;
			tablaRecibido.setValueAt(Detalle.isRecibido(idDetalle[i]), i, 0);
		}
		
		
		
		btnSelecAll = new JButton("Seleccionar todos", null);
		btnSelecAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < cantFilas; i++) 
				{
					tablaRecibido.setValueAt(true, i, 0);
				}
				
			}
		});
		btnSelecAll.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelecAll.setBounds(588, 241, 143, 25);
		JpSolicitudDeCompra.add(btnSelecAll);
		
		

		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tempRecibido = (DefaultTableModel) tablaRecibido.getModel();
				
				for (int i = 0; i < cantFilas; i++) 
				{
					boolean estado=(Boolean) tempRecibido.getValueAt(i, 0);
					Detalle.setAsRecibido(idDetalle[i],estado);
				}
				Date f_h_recibido=Orden_Trabajo.getDateTimeActual();
				Recepcion_pedido rp= new Recepcion_pedido(id_SC, "Incompleto", f_h_recibido, SC.getTxtDescripcionIncidencia().getText());
				rp.Alta();

				dispose();
				SC.dispose();
			}
		});
		
						
	}
		
		
		


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==btnAceptar){
			//faltaria verificar que no sean solo espacios el nombre del vendedor
			
		}
		else if(obj==btnCerrar){
			setVisible (false);
			dispose();
		}
		
	}
	
	
	private void cargarTablas() {
		
		
		
		
		
		//dar de alta SC
		

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
				
				}
		}
};
	
	


	