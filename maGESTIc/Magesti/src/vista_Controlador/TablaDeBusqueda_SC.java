package vista_Controlador;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Detalle;
import Modelo.Formato_Papel;
import Modelo.Orden_Trabajo;
import Modelo.Proveedor;
import Modelo.Recepcion_pedido;
import Modelo.Solicitud_compra;
import Modelo.Stock;
import Modelo.Variante;

import java.awt.Dimension;
import java.awt.Toolkit;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class TablaDeBusqueda_SC extends JInternalFrame 
{
	private JPanel jpMostrar = new JPanel ();
	private static DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private JInternalFrame RP;
	private static JTable tablaBusqueda;
	
	TablaDeBusqueda_SC(String titulo) 
	{
		super (titulo, true, true, true, true);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize (d.width, d.height);
		
		jpMostrar.setLayout(null);
		jspTabla = new JScrollPane (tablaBusqueda);
		jspTabla.setBounds(10, 70,d.width-30, d.height-250);
		jpMostrar.add (jspTabla);
		jpMostrar.setBounds(10, 11, d.width-35, d.height-230);
		tablaBusqueda = new JTable();
		tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		//tablaBusqueda.setSelectionBackground(Color.blue);


		tablaBusqueda.addMouseListener
		(
			new MouseAdapter() 
			{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{

				int filaElegida = tablaBusqueda.rowAtPoint(arg0.getPoint());
				final SolicitudDeCompra nuevaSC = new SolicitudDeCompra(true);
				
				getDesktopPane().add(nuevaSC);
				nuevaSC.show ();
				nuevaSC.getBtnAgregar().setEnabled(false);
				nuevaSC.getBtnAlmacenar().setEnabled(false);
				nuevaSC.getBtnBorrar().setEnabled(false);
				nuevaSC.getBtnEditarValores().setEnabled(false);
				nuevaSC.getBtnImprimirReporte().setEnabled(true);
				
				//Cargo en la ventana de SC los valores de la fila elegida
				final Integer id_SC=Metodos.FacturaAEntero(tablaBusqueda.getValueAt(filaElegida, 0).toString());
				
				Solicitud_compra SC=Solicitud_compra.getSC(id_SC);
				
				nuevaSC.getTxtNumero().setText(Metodos.EnteroAFactura(SC.getId_solicitud_compra()));
				
				nuevaSC.getCbProveedor().setSelectedItem(Proveedor.getRazonSocial(SC.getId_proveedor()));
				nuevaSC.getCbProveedor().setEnabled(false);
				
				nuevaSC.getTxtFecha().setText(Metodos.YMDaDMY(SC.getF_confeccion()));
				nuevaSC.getTxtVendedor().setText(SC.getVendedor());
				nuevaSC.getTxtVendedor().setEditable(false);
				
				//String nom_ot=tablaBusqueda.getValueAt(filaElegida, 4).toString();
				String nom_ot=Orden_Trabajo.getId_Con_nom_OT(SC.getId_orden_trabajo());
				nuevaSC.getCbNroOT().getModel().setSelectedItem(nom_ot);
				nuevaSC.getCbNroOT().setEnabled(false);
				
				
				//si envia a proveedor...
				if(SC.isEnvia_proveedor()){
					nuevaSC.getRdbtnEnviarAProveedor().setSelected(true);
					nuevaSC.getTxtDireccionRetiro().setEnabled(false);
				}else{
					nuevaSC.getRdbtnRetirar().setSelected(true);
					nuevaSC.getTxtDireccionRetiro().setText(SC.getDireccion_retiro());
					nuevaSC.getTxtDireccionRetiro().setEditable(false);
				}
				nuevaSC.getRdbtnEnviarAProveedor().setEnabled(false);
				nuevaSC.getRdbtnRetirar().setEnabled(false);
				
				
				nuevaSC.getCbMes().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(SC.getF_entrega(), 1)));
				nuevaSC.getCbMes().setEnabled(false);
				
				nuevaSC.getCbDia().getModel().setSelectedItem(Metodos.separar(SC.getF_entrega(), 2));
				nuevaSC.getCbDia().setEnabled(false);
				
				nuevaSC.getCbAnio().getModel().setSelectedItem(Metodos.separar(SC.getF_entrega(), 0));
				nuevaSC.getCbAnio().setEnabled(false);
				
				
				nuevaSC.getLbFechaEntrega().setVisible(false);
				String estado=Recepcion_pedido.dameEstado(id_SC);
				if(estado.toUpperCase().equals("RECIBIDO") || estado.toUpperCase().equals("RECHAZADO"))
				{
					nuevaSC.getfechaHora().setText(Metodos.dateFormatConHora(Recepcion_pedido.dameF_h_recibido(id_SC)));
					nuevaSC.getfechaHora().setVisible(true);
				}
				
				//si horario entrega es M
				if(SC.getHorario_entrega().equals("M")){
					nuevaSC.getRbManiana().setSelected(true);
				}else{
					nuevaSC.getRbTarde().setSelected(true);
				}
				nuevaSC.getRbTarde().setEnabled(false);
				nuevaSC.getRbManiana().setEnabled(false);
				
				nuevaSC.getTxtSubtotal().setText(Metodos.pasarAPesos(SC.getSubtotal().toString()));
				nuevaSC.getTxtIVA().setText(tablaBusqueda.getValueAt(filaElegida, 10).toString()+" %");
				nuevaSC.getTxtMontoIVA().setText(Metodos.pasarAPesos(SC.getMonto_iva().toString()));
				nuevaSC.getTxtTotal().setText(Metodos.pasarAPesos(SC.getTotal().toString()));
				
				
				
				/*
				 *	Muestra los datos de la seccion Detalles 
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
				
				
				DefaultTableModel temp = (DefaultTableModel) nuevaSC.getTablaDetalles().getModel();
				Metodos.borrarFilas(temp);
				Object nuevaFilaElemento[]= {"",""};

				final Integer cantFilas = Detalle.cantidadFilas(id_SC);

				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFilaElemento);
					temp.setValueAt(cantidad.get(i), i, 0);
					temp.setValueAt(gramaje.get(i), i, 1);
					temp.setValueAt(Calidad.getNombre(id_Calidad.get(i)), i, 2);	
					temp.setValueAt((Formato_Papel.getTamanio(id_formato_Papel.get(i))), i, 3);
					temp.setValueAt(Variante.getNombre(id_Variante.get(i)), i, 4);
					temp.setValueAt(marca.get(i), i, 5);
					temp.setValueAt(precio_Unitario.get(i), i, 6);
					temp.setValueAt(unidad_medida.get(i), i, 7);
					temp.setValueAt(importe.get(i), i, 8);
					
				}
				nuevaSC.getBtnAgregar().setEnabled(false);
				nuevaSC.getBtnBorrar().setEnabled(false);
				nuevaSC.getBtnAlmacenar().setEnabled(false);
				nuevaSC.getBtnConfirmar().setEnabled(false);
				nuevaSC.getTablaDetalles().setEnabled(false);
				
				
				/*
				 * seccion asentar RP
				 */
				
				if(Recepcion_pedido.dameEstado(id_SC).toUpperCase().equals("RECIBIDO") || Recepcion_pedido.dameEstado(id_SC).toUpperCase().equals("RECHAZADO")){
					//cargar datos en seccion RP
					String txtDescripcionincidencia=Recepcion_pedido.dametxtDescripcion(id_SC);
					if(Recepcion_pedido.dameEstado(id_SC).toUpperCase().equals("RECIBIDO"))
					{
						nuevaSC.getTxtDescripcionIncidencia().setText(txtDescripcionincidencia+"\n         RECIBIDO");
						
					}else{
						nuevaSC.getTxtDescripcionIncidencia().setText(txtDescripcionincidencia+"\n       RECHAZADO");
					}			
					nuevaSC.getBtnRechazarRecepcion().setEnabled(false);
					nuevaSC.getBtnConfirmarRecepcion().setEnabled(false);
					nuevaSC.getBtnIncompleta().setEnabled(false);
					nuevaSC.getTxtDescripcionIncidencia().setEnabled(false);

				}
				else if(Recepcion_pedido.dameEstado(id_SC).toUpperCase().equals("INCOMPLETO") ){
					nuevaSC.getBtnRechazarRecepcion().setEnabled(false);
					nuevaSC.getBtnConfirmarRecepcion().setEnabled(false);
					
					
					
				}
				else{
					nuevaSC.getBtnRechazarRecepcion().setEnabled(true);
					nuevaSC.getBtnConfirmarRecepcion().setEnabled(true);
					nuevaSC.getBtnIncompleta().setEnabled(true);
					nuevaSC.getTxtDescripcionIncidencia().setEnabled(true);
					
					

					//Accion boton RECHAZAR
					nuevaSC.getBtnRechazarRecepcion().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (nuevaSC.getTxtDescripcionIncidencia()
									.getText().length() > 500) {
								JOptionPane
										.showMessageDialog(
												null,
												"La descripcion no puede exceder los 500 Caracteres\nNo exceda el límite, por favor");
							}
							else
							{
								String f_h_recibido=Metodos.getDateTimeActual(0);
								Recepcion_pedido rp= new Recepcion_pedido(id_SC, "Rechazado",f_h_recibido, nuevaSC.getTxtDescripcionIncidencia().getText());
								rp.Alta();
								nuevaSC.dispose();	
							}	
						}
					});
					
					//Accion boton RECIBIDO
					nuevaSC.getBtnConfirmarRecepcion().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
									if (nuevaSC.getTxtDescripcionIncidencia()
											.getText().length() > 500) {
										JOptionPane
												.showMessageDialog(
														null,
														"La descripcion no puede exceder los 500 Caracteres\nNo exceda el límite, por favor");
									} else {

										Detalle.setAllAsRecibidos(id_SC);
										String f_h_recibido = Metodos
												.getDateTimeActual(0);

										// ALTA RP
										Recepcion_pedido rp = new Recepcion_pedido(
												id_SC,
												"Recibido",
												f_h_recibido,
												nuevaSC.getTxtDescripcionIncidencia()
														.getText());
										rp.Alta();

										// ALTA DE STOCK
										ArrayList<Integer> id_det = Detalle
												.getIdDetalles(id_SC);
										Integer id_ot = Solicitud_compra
												.getId_OT(id_SC);

										for (int i = 0; i < id_det.size(); i++) {
											Stock st = new Stock(id_ot, id_SC,
													id_det.get(i), 0, false,
													true);
											st.Alta();
										}
										Adm_Stock.Actualizar();
										nuevaSC.dispose();
									}
								}
					});
					
				}
				//Accion boton INCOMPLETA
				nuevaSC.getBtnIncompleta().addActionListener(new ActionListener() 
				{
				@Override
					public void actionPerformed(ActionEvent e) 
				{
					if (RP == null || !RP.isShowing()) 
					{
						RP = new Recepcion_Pedido(id_SC,cantFilas,nuevaSC);
						Magesti.getEscritorio().add (RP);						
						RP.show ();
					}else{
						RP.toFront();
					}
				}
				});
			}
		});
		
		getContentPane().add (jpMostrar, BorderLayout.CENTER);
		
		// Llenamos el modelo
		dtmMagesti = new DefaultTableModel(null, getColumnas()){
				
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false,false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};

			setFilas();

			tablaBusqueda.setModel(dtmMagesti);
			jspTabla.add(tablaBusqueda);
			this.setSize(500, 200);

			jspTabla.setViewportView(tablaBusqueda);
			
			/*
			 * Ocultar SC que ya fueron recibidas
			 */
			JButton btnOcultarRecibidos = new JButton("Ocultar Recibidos");
			btnOcultarRecibidos.setToolTipText("Ocultar las Solicitudes de Compra que ya fueron recibidas");
			btnOcultarRecibidos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Metodos.borrarFilas((DefaultTableModel) tablaBusqueda.getModel());
					setFilasSinRecibidos();
					
				}
			});
			btnOcultarRecibidos.setBounds(24, 21, 134, 23);
			jpMostrar.add(btnOcultarRecibidos);
			
			
			/*
			 * Ocultar SC que fueron rechazadas
			 */
			JButton btnOcultarRechazados = new JButton("Ocultar Rechazados");
			btnOcultarRechazados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Metodos.borrarFilas((DefaultTableModel) tablaBusqueda.getModel());
					setFilasSinRechazos();
					
				}
			});
			btnOcultarRechazados.setBounds(183, 21, 145, 23);
			jpMostrar.add(btnOcultarRechazados);
			
			
			/*
			 * Mostrar Todas las SC
			 */
			JButton btnMostrarTodos = new JButton("Mostrar Todo");
			btnMostrarTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Actualizar();
				}
			});
			btnMostrarTodos.setBounds(338, 21, 136, 23);
			jpMostrar.add(btnMostrarTodos);

			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
		

		// Encabezados de la tabla
		private static String[] getColumnas() 
		{
			String columna[] = Solicitud_compra.getNomColum();
			return columna;
		}

		
		
		private static void setFilas() 
		 {
			ResultSet result=ConexionDB.getbaseDatos().consultar(
			"SELECT s.id_solicitud_compra, s.f_confeccion, p.razon_social, s.vendedor, o.nombre_trabajo, s.envia_proveedor, s.direccion_retiro, s.f_entrega, s.horario_entrega, s.subtotal, s.porcentaje_iva, s.monto_iva, s.total FROM solicitud_compra s, orden_trabajo o, proveedor p where o.id_orden_trabajo=s.id_orden_trabajo AND s.id_proveedor=p.id_proveedor order by id_solicitud_compra");
						
			
				Integer CantColumnas=13;
				// Numero de columnas de la tabla.Si se agregan columnas, se agregan aca tmb
				Object datos[] = new Object[getColumnas().length]; 

				try 
				{
					while (result.next()) 
					{
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
							if(i==0)
							{
								datos[i] = Metodos.EnteroAFactura((Integer) datos[i]);
							}
							if(i==1)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
							if (i==5)
							{
								datos[i]=Solicitud_compra.enviaProveedor((Boolean) datos[i]);
							}
							if(i==7)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
							
						}
						dtmMagesti.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
		
		
		
		private static void setFilasSinRecibidos() 
		 {
			ResultSet result=ConexionDB.getbaseDatos().consultar(
			"SELECT s.id_solicitud_compra, s.f_confeccion, p.razon_social, s.vendedor, o.nombre_trabajo, s.envia_proveedor, s.direccion_retiro, s.f_entrega, s.horario_entrega, s.subtotal, s.porcentaje_iva, s.monto_iva, s.total FROM solicitud_compra s, orden_trabajo o, proveedor p where o.id_orden_trabajo=s.id_orden_trabajo AND s.id_proveedor=p.id_proveedor AND id_solicitud_compra not in (SELECT id_solicitud_compra FROM Recepcion_pedido WHERE estado='Recibido') order by id_solicitud_compra;");
						
			
				Integer CantColumnas=13;
				// Numero de columnas de la tabla.Si se agregan columnas, se agregan aca tmb
				Object datos[] = new Object[getColumnas().length]; 

				try 
				{
					while (result.next()) 
					{
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
							if(i==0)
							{
								datos[i] = Metodos.EnteroAFactura((Integer) datos[i]);
							}
							if(i==1)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
							if (i==5)
							{
								datos[i]=Solicitud_compra.enviaProveedor((Boolean) datos[i]);
							}
							if(i==7)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
						}
						dtmMagesti.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
		
		
		
		private static void setFilasSinRechazos() 
		 {
			ResultSet result=ConexionDB.getbaseDatos().consultar(
			"SELECT s.id_solicitud_compra, s.f_confeccion, p.razon_social, s.vendedor, o.nombre_trabajo, s.envia_proveedor, s.direccion_retiro, s.f_entrega, s.horario_entrega, s.subtotal, s.porcentaje_iva, s.monto_iva, s.total FROM solicitud_compra s, orden_trabajo o, proveedor p where o.id_orden_trabajo=s.id_orden_trabajo AND s.id_proveedor=p.id_proveedor AND id_solicitud_compra not in (SELECT id_solicitud_compra FROM Recepcion_pedido WHERE estado='Rechazado') order by id_solicitud_compra;");
						
			
				Integer CantColumnas=13;
				// Numero de columnas de la tabla.Si se agregan columnas, se agregan aca tmb
				Object datos[] = new Object[getColumnas().length]; 

				try 
				{
					while (result.next()) 
					{
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
							if(i==0)
							{
								datos[i] = Metodos.EnteroAFactura((Integer) datos[i]);
							}
							if(i==1)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
							if (i==5)
							{
								datos[i]=Solicitud_compra.enviaProveedor((Boolean) datos[i]);
							}
							if(i==7)
							{
								datos[i] = Metodos.YMDaDMY(datos[i].toString());
							}
						}
						dtmMagesti.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
		
		
		
		static void Actualizar()
		{
			try
			{
				Metodos.borrarFilas((DefaultTableModel)tablaBusqueda.getModel());
				setFilas();
			}
			catch(Exception e)
			{
				
			}
		}
}