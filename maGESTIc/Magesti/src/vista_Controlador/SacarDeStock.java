package vista_Controlador;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Modelo.Calidad;
import Modelo.Detalle;
import Modelo.Egreso_Stock;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Stock;
import Modelo.Variante;

@SuppressWarnings("serial")



public class SacarDeStock extends JInternalFrame implements ActionListener, Config
{
	private JTextField txtRetiro;
	private JTextField txtEmpleado;
	private JTable tablaStock;
	private JPanel JpAdmStock;
	private JLabel lbNroRetiro,lblFechaDeRetiro,lblOT;
	private JLabel lbEmpleado;
	private JTextField textField;
	private JComboBox cboOT;
	private JButton btnRetirarStock,btncerrar;
	private JTabbedPane tpElementos;
	private JPanel panElementos;
	private JScrollPane spElementos;
	private JTable tablaElementos;
	
	public SacarDeStock(final JTable Stock_Disponible, final Integer filaElegida) 
	{
		super ("Retiro de Stock", false, true, false, true);
		
		setSize (1024, 643);
		
		getContentPane().setLayout(null);
		
		
		
		JpAdmStock = new JPanel();
		JpAdmStock.setBounds(0, 0, 1008, 603);
		getContentPane().add(JpAdmStock);
		JpAdmStock.setLayout(null);
		
		lbNroRetiro = new JLabel("N\u00FAmero Retiro:");
		lbNroRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		lbNroRetiro.setBounds(10, 11, 106, 30);
		JpAdmStock.add(lbNroRetiro);
		
		
		//Obtener maxID num retiro
		Integer maxID=Egreso_Stock.getUltEgreso();
		
		txtRetiro = new JTextField(Metodos.EnteroAFactura(maxID));
		txtRetiro.setFont(new Font("Arial", Font.BOLD, 12));
		txtRetiro.setForeground(Color.RED);
		txtRetiro.setBounds(115, 14, 148, 25);
		JpAdmStock.add(txtRetiro);
		txtRetiro.setEditable(false);
		txtRetiro.setColumns(10);
		
		
		lbEmpleado = new JLabel("Empleado:");
		lbEmpleado.setFont(new Font("Arial", Font.PLAIN, 12));
		lbEmpleado.setBounds(286, 54, 75, 30);
		JpAdmStock.add(lbEmpleado);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmpleado.setBounds(362, 57, 285, 25);
		
		JpAdmStock.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		txtEmpleado.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		
		//limitar la cantidad de caracteres a 30
		txtEmpleado.addKeyListener(new KeyListener(){
		public void keyTyped(KeyEvent e){
			int limite=30;
			if (txtEmpleado.getText().length()== limite){
				getToolkit().beep ();
				e.consume();
			}
				
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent arg0) {}
		});
		
		JTabbedPane Secciones = new JTabbedPane(JTabbedPane.TOP);
		Secciones.setBounds(10, 95, 988, 206);
		JpAdmStock.add(Secciones);
		
		
		
		
		JPanel panDetalles = new JPanel();
		panDetalles.setBorder(new LineBorder(new Color(0, 0, 0)));
		Secciones.addTab("Detalles", null, panDetalles, null);
		panDetalles.setLayout(null);
		
		JScrollPane spDetalles = new JScrollPane();
		spDetalles.setBounds(10, 11, 963, 164);
		panDetalles.add(spDetalles);
		
		tablaStock = new JTable();
		tablaStock.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro Partida Stock", "Solicitud de Compra", "Calidad", "Formato", "Variante", "Gramaje", "Hojas totales", "Hojas usadas", "Remanente", "Hojas a Retirar", "Nro Elemento"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class, Boolean.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaStock.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaStock.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaStock.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaStock.getColumnModel().getColumn(3).setPreferredWidth(110);
		tablaStock.getColumnModel().getColumn(4).setPreferredWidth(110);
		tablaStock.getColumnModel().getColumn(6).setPreferredWidth(80);
		tablaStock.getColumnModel().getColumn(8).setPreferredWidth(90);
		tablaStock.getColumnModel().getColumn(9).setPreferredWidth(100);
		spDetalles.setViewportView(tablaStock);
		tablaStock.getTableHeader().setReorderingAllowed(false);
		
		tablaStock.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaStock.getTableHeader().setReorderingAllowed(false);
		tablaStock.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaStock.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaStock.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
		
		lblFechaDeRetiro = new JLabel("Fecha de Retiro:");
		lblFechaDeRetiro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaDeRetiro.setBounds(10, 52, 95, 30);
		JpAdmStock.add(lblFechaDeRetiro);
		
		textField = new JTextField("2012-10-25");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setBounds(115, 57, 148, 25);
		JpAdmStock.add(textField);

		
		lblOT = new JLabel("Para OT Nro");
		lblOT.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOT.setBounds(286, 11, 75, 30);
		JpAdmStock.add(lblOT);
		
		btncerrar = new JButton("Cerrar", null);
		btncerrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btncerrar.setBounds(838, 550, 120, 30);
		JpAdmStock.add(btncerrar);
		btncerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnRetirarStock = new JButton("Retirar de Stock", null);
		btnRetirarStock.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRetirarStock.setBounds(699, 550, 120, 30);
		JpAdmStock.add(btnRetirarStock);
		
		tpElementos = new JTabbedPane(JTabbedPane.TOP);
		tpElementos.setBounds(10, 322, 623, 206);
		JpAdmStock.add(tpElementos);
		
		panElementos = new JPanel();
		panElementos.setLayout(null);
		panElementos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tpElementos.addTab("Elementos", null, panElementos, null);
		
		spElementos = new JScrollPane();
		spElementos.setBounds(10, 11, 605, 164);
		panElementos.add(spElementos);
		
		tablaElementos = new JTable();
		tablaElementos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro Elemento", "Elemento", "Gramaje", "Calidad", "Formato", "Variante", "Hojas Previstas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaElementos.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaElementos.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablaElementos.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaElementos.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaElementos.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaElementos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaElementos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaElementos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spElementos.setViewportView(tablaElementos);
		btnRetirarStock.addActionListener(this);
		tablaElementos.getTableHeader().setReorderingAllowed(false);

		
		
		//si no es para Stockear, cargo la tabla de los elementos de esa OT seleccionada
		if(!Stock_Disponible.getValueAt(filaElegida, 1).toString().equalsIgnoreCase("Stockear") && !(Boolean) Stock_Disponible.getValueAt(filaElegida, 10)){
			String [] numot_nombreot = new String[1];
			numot_nombreot[0] = Stock_Disponible.getValueAt(filaElegida, 1).toString();
			cboOT = new JComboBox(numot_nombreot);
			cboOT.setEnabled(false);

			cboOT.setForeground(new Color(70, 130, 180));
			cboOT.setFont(new Font("Arial", Font.BOLD, 12));
			cboOT.setBounds(362, 14, 285, 25);
			JpAdmStock.add(cboOT);
			
			cargarElementosOT((Metodos.getIdOTsinNombre(Stock_Disponible.getValueAt(filaElegida, 1).toString())));
			cargarTablaStock(Stock_Disponible, filaElegida);
		}else{
			String [] numot_nombreot=Orden_Trabajo.getId_nom_OT_SinStock();
			cboOT= new JComboBox(numot_nombreot);
			cboOT.setToolTipText("Seleccione la Orden de Trabajo para la cual desea retirar hojas");
			
			cboOT.setEnabled(true);

			cboOT.setForeground(new Color(70, 130, 180));
			cboOT.setFont(new Font("Arial", Font.BOLD, 12));
			cboOT.setBounds(362, 14, 285, 25);
			JpAdmStock.add(cboOT);

			cboOT.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Metodos.borrarFilas((DefaultTableModel)tablaElementos.getModel());
					Metodos.borrarFilas((DefaultTableModel)tablaStock.getModel());
					
					cargarElementosOT(Metodos.getIdEnCombo(cboOT));
					cargarTablaStock(Stock_Disponible, filaElegida);
				}
			});
		}

		
		
	}

	private void cargarTablaStock(JTable stock, Integer filaElegida)
	{
		DefaultTableModel ts= (DefaultTableModel) tablaStock.getModel();
		//Cambia el alto de la fila para que entre el JSpinner
		tablaStock.setRowHeight(23);
		
		Object nuevaFilaDetalles[]= {"","",0,"","","","",0,false,0,""};
		
		
		Integer id_stock=Metodos.FacturaAEntero(stock.getValueAt(filaElegida, 2).toString());
		ArrayList<Detalle> detalles=Detalle.getDetallesRecibidos(id_stock);
		
		for(int i=0;i<detalles.size();i++){
			ts.addRow(nuevaFilaDetalles);
			
			//NO ES NECESARIO MOSTRAR ORDEN DE TRABAJO, SE MUESTRA EN EL COMBOBOX
			tablaStock.setValueAt(stock.getValueAt(filaElegida, 0), i, 0);
			tablaStock.setValueAt(stock.getValueAt(filaElegida, 2), i, 1);
			tablaStock.setValueAt(Calidad.getNombre(detalles.get(i).getId_calidad()), i, 2);
			tablaStock.setValueAt(Formato_Papel.getTamanio(detalles.get(i).getId_formato_papel()), i, 3);
			tablaStock.setValueAt(Variante.getNombre(detalles.get(i).getId_variante()), i, 4);
			tablaStock.setValueAt(detalles.get(i).getGramaje(), i, 5);
			tablaStock.setValueAt(detalles.get(i).getCantidad(), i, 6);
			tablaStock.setValueAt(stock.getValueAt(filaElegida, 9), i, 7);
			tablaStock.setValueAt(stock.getValueAt(filaElegida, 10), i, 8);
			
			Integer hojasTotal=detalles.get(i).getCantidad();
			Integer hojasUsadas=Integer.parseInt(stock.getValueAt(filaElegida, 9).toString());
			String cant[] = new String[(hojasTotal+1) - hojasUsadas];
			for (int j = 0; j < cant.length; j++) {
				int a=j;
				cant[j] = "" + a;
			}
			//pongo un JSpinner en el lugar de hojas a retirar
			
			TableColumn columnaCantHojasRetirar=tablaStock.getColumnModel().getColumn(9);
			columnaCantHojasRetirar.setCellEditor(new SpinnerEditor(cant));
			
			//Un comboBox con el nro del elemento de la tabla de elementos
			//debreria elegir segun el elemento para el cual desea sacar hojas
			Integer id_OT=Metodos.getIdEnCombo(cboOT);
			String[] elem= new String[tablaElementos.getRowCount()+1];
			for(int k=0;k<elem.length-1;k++){
				elem[k]=tablaElementos.getValueAt(k, 0).toString();
			}
			
			TableColumn columnaElemento=tablaStock.getColumnModel().getColumn(10);
			columnaElemento.setCellEditor(new MyComboBoxEditor(elem));
		}
	}
	
	
	public void cargarElementosOT(Integer id_OT){
		ArrayList<Elemento> elementos= Elemento.getElementos(id_OT);
		ArrayList<Materiales> materiales= Materiales.getMateriales(id_OT);
		DefaultTableModel telem= (DefaultTableModel) tablaElementos.getModel();
		Object nuevaFilaElementos[]= {"","","","","","",""};

		for(int i=0;i<elementos.size();i++){
			telem.addRow(nuevaFilaElementos);
			tablaElementos.setValueAt(elementos.get(i).getId_elemento(), i, 0);
			tablaElementos.setValueAt(elementos.get(i).getTipo_elemento(), i, 1);
			tablaElementos.setValueAt(materiales.get(i).getGramaje(), i, 2);
			tablaElementos.setValueAt(Calidad.getNombre(materiales.get(i).getId_calidad()), i, 3);
			tablaElementos.setValueAt(Formato_Papel.getTamanio(materiales.get(i).getId_formato_papel()), i, 4);
			tablaElementos.setValueAt(Variante.getNombre(materiales.get(i).getId_variante()), i, 5);
			tablaElementos.setValueAt(materiales.get(i).getHojas(), i, 6);
		}
	}
	
	
	@Override

	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();

		if(obj==btnRetirarStock){
			//CHEAQUEAR LAS COSAS
			if(txtEmpleado.getText().equals("")){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe ingresar el Nombre del Empleado",
					qTITULO + " - Campo vacío", 
					JOptionPane.WARNING_MESSAGE
				);
				txtEmpleado.requestFocus();
			}else if(!CeldasRetirarOK()){
				JOptionPane.showMessageDialog 
				(
					this, 
					"La cantidad de hojas a retirar debe ser mayor a 0",
					qTITULO + " - Agregue hojas a Retirar", 
					JOptionPane.WARNING_MESSAGE
				);
			}
			else if(!itemsSeCorresponden()){
				JOptionPane.showMessageDialog 
				(
					this, 
					"Verifique que el elemento seleccionado posea atributos de papel igual al disponible en Stock",
					qTITULO + " - Calidad, Variante, Formato  o Gramaje no coinciden", 
					JOptionPane.WARNING_MESSAGE
				);
			}
			else{
				//modificar Stock y Registrar Retiro de Stock
				modificarHojasDeStockYRegistrarRetiro();
				dispose();
			}
		}
	}
	


	private boolean itemsSeCorresponden() {
		ArrayList<Integer > filasMoficadas = this.dameFilasModificadas();
		
		if(filasMoficadas.size()>0){
			
		for(int i=0; i < filasMoficadas.size();i++){

			Materiales m=Materiales.Buscar(Integer.parseInt(tablaStock.getValueAt(filasMoficadas.get(i), 10).toString()));
			String calidad=Calidad.getNombre(m.getId_calidad());
			String formato=Formato_Papel.getTamanio(m.getId_formato_papel());
			String variante=Variante.getNombre(m.getId_variante());
			Integer gramaje=m.getGramaje();	
			if(!tablaStock.getValueAt(filasMoficadas.get(i), 2).toString().equalsIgnoreCase(calidad) ||
			!tablaStock.getValueAt(filasMoficadas.get(i), 3).toString().equalsIgnoreCase(formato) ||
			!tablaStock.getValueAt(filasMoficadas.get(i), 4).toString().equalsIgnoreCase(variante) ||
			!tablaStock.getValueAt(filasMoficadas.get(i), 5).toString().equalsIgnoreCase(gramaje.toString())){
				return false;
			}
			//HAY QUE VERIFICAR QUE NO SE SAQUEN MAS HOJAS DE LAS PREVISTAS???¿¿¿
		}
		}else{
			return false;
		}
		
		return true;
	}

	private void modificarHojasDeStockYRegistrarRetiro() {
		ArrayList<Integer> fmodif=this.dameFilasModificadas();

		for (int i = 0; i < fmodif.size(); i++) {
			Integer id_Stock = Metodos.FacturaAEntero(tablaStock.getValueAt(fmodif.get(i),0).toString());

			
			if (tablaStock.getValueAt(fmodif.get(i), 9) != null && !tablaStock.getValueAt(fmodif.get(i), 9).toString().equals("0")) {
				Integer hojasUsadas = Integer.parseInt(tablaStock.getValueAt(fmodif.get(i), 7).toString()) + Integer.parseInt(tablaStock.getValueAt(fmodif.get(i), 9).toString());
				//Actualizar la cant de Hojas usadas en Stock
				boolean ok = Stock.SacarHojas(id_Stock, hojasUsadas);
				if (ok) {// registrar retiro
					Integer id_mat=Integer.parseInt(tablaStock.getValueAt(fmodif.get(i), 10).toString());
					Egreso_Stock es = new Egreso_Stock(id_Stock, id_mat, txtEmpleado.getText(), hojasUsadas,
							Metodos.getDateTimeActual());
					es.Alta();
					
					Integer id_OT=Metodos.getIdEnCombo(cboOT);
//					Integer hojasUtilizadas_x_OT=Egreso_Stock.getHojasUsadasPorOT(id_OT);
					
					Orden_Trabajo.CambiarCantHojasUtil(id_OT, Integer.parseInt(tablaStock.getValueAt(fmodif.get(i), 9).toString()));
				}
			}
		}
	}

	private boolean CeldasRetirarOK(){

		Integer cantHojas=0;
		//verifico que No sean NULL todas las celdas de Hojas a Retirar
		for(int i=0;i<tablaStock.getRowCount();i++){
			if( tablaStock.getValueAt(i, 9)!=null){
				cantHojas= cantHojas + Integer.parseInt(tablaStock.getValueAt(i, 9).toString());
			}
		}
		return cantHojas > 0 ;
	}
	
	
	private ArrayList<Integer> dameFilasModificadas(){
		ArrayList<Integer > filasMoficadas=new ArrayList<Integer>();
		
		for(int i=0;i<tablaStock.getRowCount();i++){
			if(Integer.parseInt(tablaStock.getValueAt(i, 9).toString())>0 && tablaStock.getValueAt(i, 10)!=null && !tablaStock.getValueAt(i, 10).toString().equals("")){
				filasMoficadas.add(i);
			}
		}
		return filasMoficadas;
	}
};