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

@SuppressWarnings("serial")
public class Adm_Stock extends JInternalFrame 
{
	private SolicitudDeCompra nuevaSC;
	private SacarDeStock ss;
	private static JTable tablaStock;
	private JScrollPane spStock;
	private JPanel panStock;
	private TableRowSorter<DefaultTableModel> trsfiltro;
	private JComboBox cbGramaje,cbVariante,cbFormato,cbOT;
	private static DefaultTableModel modelo;
	
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
			
			
			JButton button = new JButton("Solicitud de compra", new ImageIcon ("Imagenes/clientes.png"));
			button.addActionListener(new ActionListener() 
			{
				
				public void actionPerformed(ActionEvent arg0) 
				{
					if (nuevaSC == null || nuevaSC.isClosed() || (!nuevaSC.isShowing() && !nuevaSC.isIcon())) 
					{
						nuevaSC = new SolicitudDeCompra(false);
						getDesktopPane().add(nuevaSC);
						nuevaSC.show ();
					}
					else
					{
						nuevaSC.toFront();
					}
				}
			});
			button.setBounds(d.width-317, d.height-210, 160, 35);
			getContentPane().add(button);
			
			spStock= new JScrollPane();
			spStock.setBounds(0, 52, 1325, 486);
			
			panStock= new JPanel();
			panStock.setBounds(10, 11, d.width-35, d.height-230);
			panStock.setLayout(null);

			tablaStock= new JTable();
			
			modelo= new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"<html>Nro Partida<br>Stock</html>", "<html>Orden de <br>Trabajo</html>", "<html>Solicitud<br> de Compra</html>", "Gramaje", "Calidad", "Formato", "Variante", "Marca", "<html>Hojas <br>totales</html>", "<html>Hojas <br>usadas</html>", "Remanente"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Boolean.class
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

			setFilas();
			tablaStock.setModel(modelo);
			
			spStock.setViewportView(tablaStock);
			getContentPane().add(panStock);
			panStock.add(spStock);
			
			
			tablaStock.getColumnModel().getColumn(0).setPreferredWidth(120);
			tablaStock.getColumnModel().getColumn(1).setPreferredWidth(150);
			tablaStock.getColumnModel().getColumn(2).setPreferredWidth(90);
			tablaStock.getColumnModel().getColumn(3).setPreferredWidth(70);
			tablaStock.getColumnModel().getColumn(4).setPreferredWidth(110);
			tablaStock.getTableHeader().setReorderingAllowed(false);
			
			//mostrarTabla();

			/*
			 * ACCION AL SELECCIONAR UNA FILA DE STOCK
			 */
			tablaStock.addMouseListener
			(new MouseAdapter()
			
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int filaElegida = tablaStock.rowAtPoint(arg0.getPoint());
					if (ss == null || ss.isClosed() || (!ss.isShowing() && !ss.isIcon())) 
					{
						ss = new SacarDeStock(tablaStock,filaElegida);
						getDesktopPane().add(ss);
						ss.show ();
					}
					else
					{
						ss.toFront();
					}
				}
			});
			
			
			
			
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
			cbGramaje.setBounds(83, 6, 80, 23);
			panStock.add(cbGramaje);
			cbGramaje.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String selec=cbGramaje.getSelectedItem().toString();
					int columGramaje=3;
					filtro(selec, columGramaje);
				}
			});
			
			

			String[] formatos= Stock.getFormatosEnStock();
			for(int i=0;i<formatos.length;i++){
				String id_form=formatos[i];
				formatos[i]=Formato_Papel.getTamanio(Integer.parseInt(id_form));
			}
			cbFormato = new JComboBox(formatos);
			cbFormato.setBounds(243, 6, 80, 23);
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
			cbOT.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String select=cbOT.getSelectedItem().toString();
					int columOT=1;
					filtro(select, columOT);
				}
			});
			
			
			JButton btnVerTodo = new JButton("Ver Todo");
			btnVerTodo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Actualizar();
					trsfiltro = new TableRowSorter<DefaultTableModel>(modelo);
					tablaStock.setRowSorter(trsfiltro);
				}
			});
			btnVerTodo.setBounds(1023, 6, 89, 23);
			panStock.add(btnVerTodo);
			
			
	
		}
	
	

	private void filtro(String cadena, int columna)
	{
		cadena = "^" + cadena + "$";
		trsfiltro = new TableRowSorter<DefaultTableModel>(modelo);
		trsfiltro.setRowFilter(RowFilter.regexFilter(cadena, columna));
		tablaStock.setModel(modelo);
		tablaStock.setRowSorter(trsfiltro);
		
	}

	private static void setFilas() {
		ResultSet result = ConexionDB
				.getbaseDatos()
				.consultar(
					"SELECT  o.nombre_trabajo, s.id_stock, o.id_orden_trabajo, sc.id_solicitud_compra, d.gramaje, c.nombre, f.tamanio, v.nombre, d.marca, d.cantidad, s.cant_hojas_usadas, s.remanente FROM stock s inner join detalle d on s.id_detalle=d.id_detalle inner join orden_trabajo o on o.id_orden_trabajo=s.id_orden_trabajo inner join solicitud_compra sc on s.id_solicitud_compra=sc.id_solicitud_compra inner join calidad c on c.id_calidad=d.id_calidad inner join formato_papel f on f.id_formato_papel=d.id_formato_papel inner join variante v on v.id_variante=d.id_variante WHERE s.activo=true  ORDER BY id_stock;");

		Integer CantColumnas = 11;
		Object datos[] = new Object[CantColumnas];
		try {
			
				
				while (result.next()) {
				for (int i = 0; i < CantColumnas; i++) {
					datos[i] = result.getObject(i + 2);
					
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
			
	public static void Actualizar()
	{
		if(tablaStock != null){
		Metodos.borrarFilas((DefaultTableModel)tablaStock.getModel());
		setFilas();
		}
	}
}
