package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Modelo.Calidad;
import Modelo.Detalle;
import Modelo.Formato_Papel;
import Modelo.Orden_Trabajo;
import Modelo.Procesos_x_OT;
import Modelo.Recepcion_pedido;
import Modelo.Solicitud_compra;
import Modelo.Stock;
import Modelo.Variante;

import java.awt.Font;

@SuppressWarnings("serial")



public class Recepcion_Pedido extends JInternalFrame implements ActionListener, Config
{
	private JTable tablaDetalles,tablaRecibido;
	private JPanel JpSolicitudDeCompra;
	private JButton btnCerrar,btnAceptar,btnSelecAll;
	private JScrollPane spDetalles,spRecibido ;
	
	
//	private static Recepcion_Pedido instancia=null;
//	
//	public static Recepcion_Pedido getInstancia(Integer id_SC, Integer cantFilas, SolicitudDeCompra SC){
//		if(instancia == null){
//			instancia= new Recepcion_Pedido(id_SC, cantFilas, SC);
//		}
//		return instancia;
//	}
	
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
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, Double.class, String.class, Double.class
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
		tablaDetalles.getTableHeader().setReorderingAllowed(false);
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
				
		final Integer cantfilastrue= Recepcion_pedido.getCantidadFilasRecibidas(id_SC);

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
			public boolean isCellEditable(int row, int column) 
			{
				//verifica solo las filas que no hayan sido recibidas aun
				if(row >= cantfilastrue){
					return true;
				}
				return false;
				
			}
		});
		tablaRecibido.getColumnModel().getColumn(0).setResizable(false);
		tablaRecibido.getColumnModel().getColumn(0).setMaxWidth(100);
		tablaRecibido.getTableHeader().setReorderingAllowed(false);
		spRecibido.setViewportView(tablaRecibido);
		
		DefaultTableModel tempRecibido = (DefaultTableModel) tablaRecibido.getModel();
		Object nuevaFila[]= {false};
		
		for (int i = 0; i < cantFilas; i++) 
		{
			tempRecibido.addRow(nuevaFila);
			//Integer id_detalle;
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
				int cantTrue=cantfilastrue;
				ArrayList<Integer> cantHojasRP=new ArrayList<Integer>();
				ArrayList<String> marcaRP=new ArrayList<String>();
				ArrayList<Integer> id_calRP=new ArrayList<Integer>();
				ArrayList<Integer> id_forRP=new ArrayList<Integer>();
				ArrayList<Integer> id_varRP=new ArrayList<Integer>();
				ArrayList<Integer> gramRP=new ArrayList<Integer>();

				for (int i = cantfilastrue; i < cantFilas; i++) 
				{
					boolean recibido=(Boolean) tempRecibido.getValueAt(i, 0);
					if(recibido){
						cantTrue++;
						cantHojasRP.add(Detalle.getCantHojas(idDetalle[i]));
						marcaRP.add(Detalle.getMarca(idDetalle[i]));
						id_calRP.add(Detalle.getidCalidad(idDetalle[i]));
						id_forRP.add(Detalle.getidFormato(idDetalle[i]));
						id_varRP.add(Detalle.getidVariante(idDetalle[i]));
						gramRP.add(Detalle.getGramaje(idDetalle[i]));
					}
					Detalle.setAsRecibido(idDetalle[i],recibido);

				}
				String f_h_recibido=Metodos.getDateTimeActual();
				//si estan marcadas todas las filas como recibidas, pone la RP como recibido
				if(cantTrue==tempRecibido.getRowCount()){
					Recepcion_pedido rp= new Recepcion_pedido(id_SC, "Recibido", f_h_recibido, SC.getTxtDescripcionIncidencia().getText());
					rp.Alta();
				}else{
					Recepcion_pedido rp= new Recepcion_pedido(id_SC, "Incompleto", f_h_recibido, SC.getTxtDescripcionIncidencia().getText());
					rp.Alta();
				}
				
				/*
				 * alta STOCK
				 */
				for(int i=0;i<cantHojasRP.size();i++){
					Integer id_ot=Solicitud_compra.getId_OT(id_SC);
					Integer cnth= cantHojasRP.get(i),cantusadas=0,remanente=0;
					String marca= marcaRP.get(i);
					Integer id_cal= id_calRP.get(i);
					Integer id_for= id_forRP.get(i);
					Integer id_var= id_varRP.get(i);
					Integer gram= gramRP.get(i);
					
					Stock st= new Stock(id_ot, id_SC, cnth, cantusadas, marca, id_cal, id_for, id_var, gram, remanente);
					st.Alta();
				}
				
				dispose();
				SC.dispose();
			}
		});
		
						
	}
		
		
		


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		 if(obj==btnCerrar){
			setVisible (false);
			dispose();
		}
		
	}
};
	
	


	