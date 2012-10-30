package vista_Controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;

public class Adm_Stock extends JInternalFrame 
{
	private static JTable tablaStock;
	private JButton btnRetirarStock;
	
		public Adm_Stock() 
		{
			super ("Administracion de Stock", false, true, false, true);
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
			btnCerrar.setBounds(d.width-145, d.height-210, 120, 35);
			getContentPane().add(btnCerrar);
			
			JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
			btnConfirmar.setBounds(d.width-280, d.height-210, 120, 35);
			btnConfirmar.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					Integer cantFilasDatos = tablaStock.getRowCount();
					/*
					 * VER SI HACE ALGO EL GUARDAR
					 */
				}
			}
			);
			getContentPane().add(btnConfirmar);
			
			JButton button = new JButton("Solicitud de compra", new ImageIcon ("Imagenes/clientes.png"));
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					final SolicitudDeCompra nuevaSC = new SolicitudDeCompra(false);
					getDesktopPane().add(nuevaSC);
					nuevaSC.show ();
				}
			});
			button.setBounds(d.width-455, d.height-210, 160, 35);
			getContentPane().add(button);
			
			JPanel panStock = new JPanel();
			panStock.setBounds(10, 11, d.width-35, d.height-230);
			getContentPane().add(panStock);
			panStock.setLayout(null);
			
			JScrollPane spStock = new JScrollPane();
			spStock.setBounds(0, 0, d.width-35, d.height-230);
			panStock.add(spStock);
			
			tablaStock = new JTable();
			tablaStock.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"<html>Nro Partida<br>Stock</html>", "<html>Orden de <br>Trabajo</html>", "<html>Solicitud<br> de Compra</html>", "Marca", "Calidad", "Formato", "Variante", "Gramaje", "<html>Hojas <br>totales</html>", "<html>Hojas <br>usadas</html>", "Remanente"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tablaStock.getColumnModel().getColumn(0).setPreferredWidth(100);
			tablaStock.getColumnModel().getColumn(1).setPreferredWidth(100);
			tablaStock.getColumnModel().getColumn(2).setPreferredWidth(107);
			spStock.setViewportView(tablaStock);
			tablaStock.getTableHeader().setReorderingAllowed(false);
			
			setFilas();
			
			/*
			 * 
			 * Accion al seleccionar una fila de Stock
			 * 
			 */
			
			tablaStock.addMouseListener
			(new MouseAdapter()
			
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int filaElegida = tablaStock.rowAtPoint(arg0.getPoint());
					final SacarDeStock ss = new SacarDeStock(tablaStock,filaElegida);
					getDesktopPane().add(ss);
					ss.show ();
				}
			});
			
			
		}
			
	private void setFilas() {
		ResultSet result = ConexionDB
				.getbaseDatos()
				.consultar(
						"SELECT  o.nombre_trabajo, s.id_stock, o.id_orden_trabajo, sc.id_solicitud_compra, s.marca, c.nombre, f.tamanio, v.nombre, s.gramaje, s.cant_hojas_totales, s.cant_hojas_usadas, s.remanente FROM stock s, orden_trabajo o, solicitud_compra sc, calidad c,formato_papel f, variante v WHERE s.activo=true AND s.id_orden_trabajo=o.id_orden_trabajo AND s.id_solicitud_compra=sc.id_solicitud_compra AND s.id_calidad=c.id_calidad AND f.id_formato_papel=s.id_formato AND v.id_variante=s.id_variante;");

		Integer CantColumnas = 11;
		Object datos[] = new Object[CantColumnas];
		try {
			DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaStock.getModel();
			while (result.next()) {
				for (int i = 0; i < CantColumnas; i++) {
					datos[i] = result.getObject(i + 2);
					if(i==0){
						
					}
					if (i == 1) {
						if (datos[i].equals(1)) {
							datos[i] = "Stockear";
						} else {
							datos[i] = Metodos
									.EnteroAFactura((Integer) datos[i])
									+ " - "
									+ result.getObject(1).toString();
						}
					}
					if (i== 0 || i == 2) {
						Integer n = (Integer) datos[i];
						datos[i] = Metodos.EnteroAFactura(n);
					}
				}
				tablaTempDatos.addRow(datos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
			
	public static JTable getTablaStock() {
		return tablaStock;
	}

	
			
}
