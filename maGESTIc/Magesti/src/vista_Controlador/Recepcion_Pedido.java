package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Modelo.Calidad;
import Modelo.Detalle;
import Modelo.Formato_Papel;
import Modelo.Recepcion_pedido;
import Modelo.Solicitud_compra;
import Modelo.Stock;
import Modelo.Variante;

import java.awt.Font;

@SuppressWarnings("serial")



public class Recepcion_Pedido extends JInternalFrame implements ActionListener, Config
{
	private JTable tablaDetalles;
	private JPanel JpSolicitudDeCompra;
	private JButton btnCerrar,btnAceptar,btnSelecAll;
	private JScrollPane spDetalles;
	
	
	public Recepcion_Pedido(final Integer id_SC, final Integer cantFilas, final SolicitudDeCompra SC) 
	{
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
		spDetalles.setBounds(10, 11, 866, 219);
		JpSolicitudDeCompra.add(spDetalles);

		
		tablaDetalles = new JTable();
		tablaDetalles.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaDetalles.setShowGrid(false);
		tablaDetalles.setFont(new Font("Arial", Font.PLAIN, 12));
		tablaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tablaDetalles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html>Cantidad <br>de Hojas</html>", "Marca", "Calidad", "Variante", "Formato", "Gramaje", "<html>Precio<br> Unitario</html>", "<html>Unidad de<br> Medida</html>", "Importe", "Recibido"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, Double.class, String.class, Double.class,Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row, int column) 
			{
				//verifica solo las filas que no hayan sido recibidas aun
				boolean recibido[]= Recepcion_pedido.dameOrdenRecibidos(id_SC);
				if(column == 9){
					return !recibido[row];//si esta recibido, devuelve false.sino true
				}
				return false;
				
			}
		});
		tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(1).setPreferredWidth(103);
		tablaDetalles.getColumnModel().getColumn(1).setMaxWidth(200);
		tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(2).setPreferredWidth(119);
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
		tablaDetalles.getColumnModel().getColumn(7).setPreferredWidth(108);
		tablaDetalles.getColumnModel().getColumn(7).setMaxWidth(110);
		tablaDetalles.getColumnModel().getColumn(8).setResizable(false);
		tablaDetalles.getColumnModel().getColumn(8).setPreferredWidth(85);
		tablaDetalles.getColumnModel().getColumn(8).setMaxWidth(200);
		spDetalles.setViewportView(tablaDetalles);
		
		tablaDetalles.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaDetalles.getTableHeader().setReorderingAllowed(false);
		tablaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		/*
		 * cargar tabla Detalles
		 */
		
		final ArrayList<Detalle> detalles=Detalle.getDetalles(id_SC);
		

		
		DefaultTableModel temp = (DefaultTableModel) tablaDetalles.getModel();
		Object nuevaFilaDetalles[]= {"",""};
		
		for (int i = 0; i < cantFilas; i++) 
		{
			temp.addRow(nuevaFilaDetalles);
			temp.setValueAt(detalles.get(i).getCantidad(), i, 0);//cantidad
			temp.setValueAt(detalles.get(i).getMarca(), i, 1);//marca
			temp.setValueAt(Calidad.getNombre(detalles.get(i).getId_calidad()), i, 2);//calidad	
			temp.setValueAt(Variante.getNombre(detalles.get(i).getId_variante()), i, 3);//variante
			temp.setValueAt((Formato_Papel.getTamanio(detalles.get(i).getId_formato_papel())), i, 4);//formatoPapel
			temp.setValueAt(detalles.get(i).getGramaje(), i, 5);//gramaje
			temp.setValueAt(detalles.get(i).getPrecio_unitario(), i, 6);//precioUnitario
			temp.setValueAt(detalles.get(i).getUnidad_medida_del_precio(), i, 7);//unidadMEdida
			temp.setValueAt(detalles.get(i).getImporte(), i, 8);//importe
			
			tablaDetalles.setValueAt(Detalle.isRecibido(detalles.get(i).getId_detalle()), i, 9);
		}
		
		
		/*
		 * cargar tabla recibidos
		 */
				
		btnSelecAll = new JButton("Seleccionar todos", null);
		btnSelecAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < cantFilas; i++) 
				{
					tablaDetalles.setValueAt(true, i, 9);
				}
				
			}
		});
		btnSelecAll.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelecAll.setBounds(588, 241, 143, 25);
		JpSolicitudDeCompra.add(btnSelecAll);
		
		
		/*
		 * 
		 * Accion boton ACEPTAR
		 * 
		 */
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tempDet = (DefaultTableModel) tablaDetalles
						.getModel();
				
				int cont=0;
				for (int i = 0; i < cantFilas; i++) {
				boolean recibido = (Boolean) tempDet.getValueAt(i,9);
						if (recibido) {
							cont++;
						}
					}
				
				if(cont == 0){
					JOptionPane
					.showMessageDialog(
							null,
							"Debe seleccionar al menos un detalle como recibido");
				}else{
				
				if (SC.getTxtDescripcionIncidencia().getText().length() > 500) {
					JOptionPane
							.showMessageDialog(
									null,
									"La descripcion no puede exceder los 500 Caracteres\nNo exceda el límite, por favor");
				} else {
					int cantTrue = Recepcion_pedido
							.getCantidadFilasRecibidas(id_SC);
					ArrayList<Integer> posiciones = new ArrayList<Integer>();

					for (int i = 0; i < cantFilas; i++) {
						if (tempDet.isCellEditable(i, 9)) {
							boolean recibido = (Boolean) tempDet.getValueAt(i,
									9);
							if (recibido) {
								cantTrue++;
								posiciones.add(new Integer(i));
							}
							Detalle.setAsRecibido(detalles.get(i)
									.getId_detalle(), recibido);
						}
					}
					String f_h_recibido = Metodos.getDateTimeActual(0);
					// si estan marcadas todas las filas como recibidas, pone la
					// RP como recibido
					if (cantTrue == tempDet.getRowCount()) {
						Recepcion_pedido rp = new Recepcion_pedido(id_SC,
								"Recibido", f_h_recibido, SC
										.getTxtDescripcionIncidencia()
										.getText());
						rp.Alta();
					} else {
						Recepcion_pedido rp = new Recepcion_pedido(id_SC,
								"Incompleto", f_h_recibido, SC
										.getTxtDescripcionIncidencia()
										.getText());
						rp.Alta();
					}

					/*
					 * alta STOCK
					 */
					for (int i = 0; i < posiciones.size(); i++) {
						Integer id_ot = Solicitud_compra.getId_OT(id_SC);
						Integer cantusadas = 0;
						Boolean remanente = false;

						Stock st = new Stock(id_ot, id_SC, detalles.get(
								posiciones.get(i)).getId_detalle(), cantusadas,
								remanente, true);
						st.Alta();
					}

					dispose();
					SC.dispose();
					}
				}
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
	
	


	