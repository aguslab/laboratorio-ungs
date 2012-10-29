package vista_Controlador;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Calidad;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Hojas_Utilizadas;
import Modelo.Materiales;
import Modelo.Proveedor;
import Modelo.Variante;
import Modelo.Procesos_x_OT;



@SuppressWarnings("serial")
public class BusquedaOrdenTrabajo extends JInternalFrame 
{
	
	BusquedaOrdenTrabajo(final OrdenDeTrabajo nuevaOT, JTable tablaBusqueda, int filaElegida) 
	{
				nuevaOT.getEstado().setEnabled(true);
				//Cargo en la ventana de OT los valores de la fila elegida
				nuevaOT.getTxtNro().setText((tablaBusqueda.getValueAt(filaElegida, 0).toString()));
				
				nuevaOT.getTipoProducto().setText((String) tablaBusqueda.getValueAt(filaElegida, 1));
				nuevaOT.getTipoProducto().setEditable(false);
				
				nuevaOT.getCboMes().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 1)));
				nuevaOT.getCboMes().setEnabled(false);
				
				nuevaOT.getCboDia().getModel().setSelectedItem(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 2));
				nuevaOT.getCboDia().setEnabled(false);
				
				nuevaOT.getCboAnio().getModel().setSelectedItem(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 0));
				nuevaOT.getCboAnio().setEnabled(false);
				
				nuevaOT.getCboMes2().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 1)));
				nuevaOT.getCboMes2().setEnabled(false);
				
				nuevaOT.getCboDia2().getModel().setSelectedItem(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 2));
				nuevaOT.getCboDia2().setEnabled(false);
				
				nuevaOT.getCboAnio2().getModel().setSelectedItem(Metodos.separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 0));
				nuevaOT.getCboAnio2().setEnabled(false);
				
				nuevaOT.getTxtNombreOT().setText((String) tablaBusqueda.getValueAt(filaElegida, 5));
				nuevaOT.getTxtNombreOT().setEditable(false);
				
				nuevaOT.getTxtDescripcion().setText((String) tablaBusqueda.getValueAt(filaElegida, 6));
				nuevaOT.getTxtDescripcion().setEditable(false);
				
				nuevaOT.getTxtCantidadAEntregar().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 7)));
				nuevaOT.getTxtCantidadAEntregar().setEditable(false);
				
				nuevaOT.getTxtPreimpresion().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 8)));
				nuevaOT.getTxtPreimpresion().setEditable(false);
				
				nuevaOT.getTxtAncho().setText(Metodos.valorAncho(tablaBusqueda.getValueAt(filaElegida, 9).toString()));
				nuevaOT.getTxtAncho().setEditable(false);
				
				nuevaOT.getTxtAlto().setText(Metodos.valorAncho(tablaBusqueda.getValueAt(filaElegida, 10).toString()));
				nuevaOT.getTxtAlto().setEditable(false);
				
				//nuevaOT.getChbApaisado().getModel().setSelected((Boolean) tablaBusqueda.getValueAt(filaElegida, 11));
				nuevaOT.getChbApaisado().getModel().setSelected(Metodos.esApaisadaB(tablaBusqueda.getValueAt(filaElegida, 11).toString()));
				nuevaOT.getChbApaisado().setEnabled(false);
				
				nuevaOT.getEstado().getModel().setSelectedItem((String)tablaBusqueda.getValueAt(filaElegida, 12));
				
				nuevaOT.getCliente().setSelectedItem(tablaBusqueda.getValueAt(filaElegida, 2).toString());
				nuevaOT.getCliente().setEnabled(false);
				nuevaOT.getBtnLimpiarCampos().setEnabled(false);
				
				nuevaOT.getBtnUp().setEnabled(false);			
				nuevaOT.getBtnDown().setEnabled(false);
				
				
				
				nuevaOT.getTablaElementos().setModel(new DefaultTableModel(new Object[][] {},
						new String[] {"Elemento", "Cantidad","Hojas Previstas" ,"Hojas Utilizadas"}) 
					{
						Class[] columnTypes = new Class[] 
						{
							String.class, Integer.class, Integer.class, Integer.class
						};
						public Class getColumnClass(int columnIndex) 
						{
							return columnTypes[columnIndex];
						}
						boolean[] columnEditables = new boolean[] 
						{
							false, false, true, true
						};
						public boolean isCellEditable(int row, int column) 
						{
							return columnEditables[column];
						}
					});
				
				//Muestra los datos de la tabla Elemento
				Integer id_OT=Metodos.FacturaAEntero(nuevaOT.getTxtNro().getText());
				Integer cantFilas = Elemento.cantidadFilas(id_OT);
				ArrayList<String> elemento = Elemento.nombreDeElemento(id_OT);
				ArrayList<Integer> cantidad = Elemento.cantidadDeElemento(id_OT);
				ArrayList<Integer> cantHojasUtil= Hojas_Utilizadas.getCantHojas(Elemento.getIdElementos(id_OT));
				DefaultTableModel temp = (DefaultTableModel) nuevaOT.getTablaElementos().getModel();
				nuevaOT.getTablaElementos().setEnabled(false);
				Object nuevaFilaElemento[]= {"",""};
				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFilaElemento);
					temp.setValueAt(elemento.get(i), i, 0);
					temp.setValueAt(cantidad.get(i), i, 1);	
					temp.setValueAt(0, i, 2);
					temp.setValueAt(cantHojasUtil.get(i), i, 3);	

					
				}
				nuevaOT.getBtnAgregarFila().setEnabled(false);
				nuevaOT.getBtnBorrarFila().setEnabled(false);
				nuevaOT.getBtnAlmacenar().setEnabled(false);
				
				nuevaOT.getTablaElementos().setEnabled(true);
				
				
				
				
				//Muestra los datos de la tabla Materiales
				
				ArrayList<Integer> id_m= Materiales.getID_Materiales(id_OT);
				
				ArrayList<Materiales> materiales = new ArrayList<Materiales>();

				DefaultTableModel tempMat = (DefaultTableModel) nuevaOT.getTablaMateriales().getModel();
				Object nuevaFilaMateriales[]= {"",0, 0,"", "", "", 0, 0, 0, 0, 0};
				cantFilas=id_m.size();
				
				for (int i = 0; i < cantFilas; i++) 
				{
					tempMat.addRow(nuevaFilaMateriales);
					materiales.add(Materiales.Buscar(id_m.get(i)));						
					tempMat.setValueAt(elemento.get(i), i, 0);
					tempMat.setValueAt(cantidad.get(i), i, 1);	
					tempMat.setValueAt(materiales.get(i).getGramaje(), i, 2);	
					tempMat.setValueAt((Formato_Papel.getTamanio(materiales.get(i).getId_formato_papel())), i, 3);	
					tempMat.setValueAt(Variante.getNombre(materiales.get(i).getId_variante()), i, 4);	
					tempMat.setValueAt(Calidad.getNombre(materiales.get(i).getId_calidad()), i, 5);	
					tempMat.setValueAt(materiales.get(i).getPliegos_en_demasia(), i, 6);	
					tempMat.setValueAt(materiales.get(i).getPoses_x_pliego(), i, 7);	
					tempMat.setValueAt(materiales.get(i).getPliegos_x_hoja(), i, 8);	
					tempMat.setValueAt(materiales.get(i).getHojas(), i, 9);	
					tempMat.setValueAt(materiales.get(i).getPliegos_netos(), i, 10);
				}
				
				nuevaOT.getTablaMateriales().setEnabled(false);
				
				
				//Muestra los datos de la tabla Orden de ejecucion
				
				Integer cantFilasProcesos = Procesos_x_OT.getCantidadFilas(id_OT);
				ArrayList<String> procesos = Procesos_x_OT.BuscarProc_x_OT(id_OT);
				ArrayList<Boolean> tercerizadas = Procesos_x_OT.getTercerizada(id_OT);
				ArrayList<Integer> proveedor = Procesos_x_OT.getProveedor(id_OT);
				ArrayList<String> observaciones = Procesos_x_OT.getObservaciones(id_OT);
				ArrayList<Boolean> cumplida = Procesos_x_OT.getCumplida(id_OT);
				final Integer cantfilastrue= Procesos_x_OT.getCantidadFilasCumplidas(id_OT);
				//permite que la columna cumplida sea editable
				nuevaOT.getTablaOrdenEjecucion().setModel(new DefaultTableModel(new Object[][] {},
						new String[] {"Proceso", "Tercerizada", "Proveedor", "Observaciones", "Cumplida"}) 
					{
						Class[] columnTypes = new Class[] 
						{
							String.class, Boolean.class, String.class, String.class, Boolean.class
						};
						public Class getColumnClass(int columnIndex) 
						{
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int row, int column) 
						{
							//verifica solo las filas cuyos procesos no esten cumplidos aun
							if(column==4 && row >= cantfilastrue){
								//si es la primera fila y es falsa devuelve true
								if(row==0 && (Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row, 4) == false){
									return true;
								}else if((Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row, 4)== false){
									//si la fila es falsa pero la anterior true, devuelve true
									if(row>0 && (Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row-1, 4)==true){
										return true;
									}
								}else if((Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row, 4)== true){
									//si la fila elegida es true y la siguien false, devuelve true
									if(row<nuevaOT.getTablaOrdenEjecucion().getRowCount()-1 && (Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row+1, 4)==false){
										return true;
									//si la fila elegida en la ultima y es true,devuelve true	
									}else if(row == nuevaOT.getTablaOrdenEjecucion().getRowCount()-1 && (Boolean) nuevaOT.getTablaOrdenEjecucion().getValueAt(row, 4)==true){
											return true;
										}
								}
							}
							return false;
						}
					});
				
				DefaultTableModel tempOE = (DefaultTableModel) nuevaOT.getTablaOrdenEjecucion().getModel();

				Object nuevaFilaOrdenEjecucion[]= {"",false,"","", false};

				for (int i = 0; i < cantFilasProcesos; i++) 
				{
					tempOE.addRow(nuevaFilaOrdenEjecucion);
					tempOE.setValueAt(procesos.get(i), i, 0);
					tempOE.setValueAt(tercerizadas.get(i), i, 1);	
					tempOE.setValueAt(Proveedor.getRazonSocial(proveedor.get(i)), i, 2);	
					tempOE.setValueAt(observaciones.get(i), i, 3);	
					tempOE.setValueAt(cumplida.get(i), i, 4);	
				}
				nuevaOT.getBtnConfirmarSeleccion().setEnabled(false);
				nuevaOT.getTablaOrdenEjecucion().setAutoResizeMode(1);
				
	}			
}