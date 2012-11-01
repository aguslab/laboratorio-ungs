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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelo.ConexionDB;
import Modelo.Formato_Papel;
import Modelo.Stock;
import Modelo.Variante;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Adm_Stock extends JInternalFrame 
{
	private static JTable tablaStock;
	private JScrollPane spStock;
	private JPanel panStock;
	private TableRowSorter<DefaultTableModel> trsfiltro;
	private JComboBox cbGramaje,cbVariante,cbFormato,cbOT;
	private DefaultTableModel modelo;
	
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
			
//			JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
//			btnConfirmar.setBounds(d.width-280, d.height-210, 120, 35);
//			getContentPane().add(btnConfirmar);
			
			
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
			button.setBounds(d.width-317, d.height-210, 160, 35);
			getContentPane().add(button);
			
			spStock= new JScrollPane();
			spStock.setBounds(0, 52, 1325, 486);
			
			panStock= new JPanel();
			panStock.setBounds(10, 11, d.width-35, d.height-230);
			panStock.setLayout(null);

			setFilas();

			
			
			spStock.setViewportView(tablaStock);
			getContentPane().add(panStock);
			panStock.add(spStock);
			
			JLabel lblGramaje = new JLabel("Gramaje: ");
			lblGramaje.setBounds(10, 5, 63, 25);
			panStock.add(lblGramaje);
			
			JLabel lblFormato = new JLabel("Formato: ");
			lblFormato.setBounds(181, 5, 52, 25);
			panStock.add(lblFormato);
			
			JLabel lblVariante = new JLabel("Variante: ");
			lblVariante.setBounds(344, 5, 63, 25);
			panStock.add(lblVariante);
			
			String[] gramajes= Stock.getGramajesEnStock();
			cbGramaje = new JComboBox(gramajes);
			cbGramaje.setBounds(83, 6, 70, 23);
			panStock.add(cbGramaje);
			cbGramaje.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String selec=cbGramaje.getSelectedItem().toString();
					int columGramaje=7;
					filtro(selec, columGramaje);
				}
			});
			
			

			String[] formatos= Stock.getFormatosEnStock();
			for(int i=0;i<formatos.length;i++){
				String id_form=formatos[i];
				formatos[i]=Formato_Papel.getTamanio(Integer.parseInt(id_form));
			}
			cbFormato = new JComboBox(formatos);
			cbFormato.setBounds(243, 6, 70, 23);
			panStock.add(cbFormato);
			cbFormato.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String select=cbFormato.getSelectedItem().toString();
					int columFormato=5;
					filtro(select, columFormato);
				}
			});
			
			JLabel lblOT = new JLabel("Orden Trabajo: ");
			lblOT.setBounds(613, 5, 87, 25);
			panStock.add(lblOT);
			
			String[] variantes=Stock.getVariantesDeStock();
			for(int i=0;i<variantes.length;i++){
				String id_var=variantes[i];
				variantes[i]=Variante.getNombre(Integer.parseInt(id_var));
			}
				
			cbVariante = new JComboBox(variantes);
			cbVariante.setBounds(417, 6, 165, 23);
			panStock.add(cbVariante);
			cbVariante.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {

					String select=cbVariante.getSelectedItem().toString();
					int columVariante=6;
					filtro(select, columVariante);
				}
			});
			
			String[] OTs=Stock.getOTsDeStock();
			cbOT = new JComboBox(OTs);
			cbOT.setBounds(710, 6, 285, 23);
			panStock.add(cbOT);
			
			JButton btnVerTodo = new JButton("Ver Todo");
			btnVerTodo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					setFilas();
				}
			});
			btnVerTodo.setBounds(1023, 6, 89, 23);
			panStock.add(btnVerTodo);
			
			cbOT.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String select=cbOT.getSelectedItem().toString();
					int columOT=1;
					filtro(select, columOT);
				}
			});

			
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
			
	private void filtro(String cadena, int columna) {
		
		
		trsfiltro = new TableRowSorter<DefaultTableModel>(modelo);
		trsfiltro.setRowFilter(RowFilter.regexFilter(cadena, columna));
		//Metodos.borrarFilas(modelo);
		tablaStock.setModel(modelo);
		tablaStock.setRowSorter(trsfiltro);
	}

	private void setFilas() {
		ResultSet result = ConexionDB
				.getbaseDatos()
				.consultar(
						"SELECT  o.nombre_trabajo, s.id_stock, o.id_orden_trabajo, sc.id_solicitud_compra, s.marca, c.nombre, f.tamanio, v.nombre, s.gramaje, s.cant_hojas_totales, s.cant_hojas_usadas, s.remanente FROM stock s, orden_trabajo o, solicitud_compra sc, calidad c,formato_papel f, variante v WHERE s.activo=true AND s.id_orden_trabajo=o.id_orden_trabajo AND s.id_solicitud_compra=sc.id_solicitud_compra AND s.id_calidad=c.id_calidad AND f.id_formato_papel=s.id_formato AND v.id_variante=s.id_variante;");

		Integer CantColumnas = 11;
		Object datos[] = new Object[CantColumnas];
		try {
			modelo= new DefaultTableModel(
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
				};
				
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
				modelo.addRow(datos);
				tablaStock = new JTable(modelo);

				spStock.setViewportView(tablaStock);
				getContentPane().add(panStock);
				panStock.add(spStock);
				
				tablaStock.getColumnModel().getColumn(0).setPreferredWidth(120);
				tablaStock.getColumnModel().getColumn(1).setPreferredWidth(130);
				tablaStock.getColumnModel().getColumn(2).setPreferredWidth(107);
				tablaStock.getTableHeader().setReorderingAllowed(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
			
	void Actualizar()
	{
		Metodos.borrarFilas((DefaultTableModel)tablaStock.getModel());
		setFilas();
	}
}
