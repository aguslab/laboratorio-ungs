package vista_Controlador;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
import Modelo.Orden_Trabajo;
import Modelo.Tipo_producto;


@SuppressWarnings("serial")

public class OrdenDeTrabajo extends JInternalFrame implements ActionListener, Config
{
	
	private JPanel jpOrdenDeTrabajo = new JPanel();
	
	private JLabel 
		lbNro, 
		lbCliente, 
		lbFechaC, 
		lbFechaP, 
		lbNombreOT,
		lbEstado,
		lbDescripcion,
		lbAncho,
		lbAlto,
		lbTipoDeProducto,
		lbCantidadAEntregar,
		lbPreimpresion,
		lbOriginal,
		lbDuplicado,
		lbTriplicado,
		lbTapa,
		lbPaginasInteriores,
		lbCantidadDeHojasUtilizadas;
	
	private JTextField 
		txtNro, 
		txtNombreOT,
		txtDescripcion,
		txtAncho,
		txtAlto,
		txtCantidadAEntregar,
		txtPreimpresion,
		txtOriginal,
		txtDuplicado,
		txtTriplicado,
		txtTapas,
		txtPaginasInteriores,
		txtCantidadDeHojasUtilizadas;
	
	private JComboBox<String> 
		cboCliente,
		cboMes, 
		cboDia, 
		cboAnio,
		cboMes2, 
		cboDia2, 
		cboAnio2,
		cboEstado,
		cboTipoDeProducto;
	
	private JButton
		btnLimpiarOT,
		btnGuardar, 
		btnCancelar;
	
	private JCheckBox
		chbApaisado;
	
	private JTabbedPane
		tabSecciones;
	
	private JTable tablaOE, tablaMateriales;
	
	private DefaultTableModel dtmMateriales;
	
	
	//ArrayList<String> Clientes= Cliente.getClientes();
	
	String Clientes[] = Cliente.getClientes(); 
	
	/*{ PLAN B 
		"Cosméticos Avon S.A.C.I.", 
		"Garbarino S.A.", 
		"Fravega S.A.C.I.", 
		"Cablevisión S.A.", 
	};*/
	
	String Meses[] = 
	{
		"Enero", 
		"Febrero", 
		"Marzo", 
		"Abril", 
		"Mayo", 
		"Junio",
		"Julio", 
		"Agosto", 
		"Septiembre", 
		"Octubre", 
		"Noviembre", 
		"Diciembre"
	};
	
	String Estados[] = 
	{
		"Pendiente", 
		"En proceso", 
		"Cerrado" 
	};
	
	String TipoDeProducto[] = Tipo_producto.getTiposProd();
	/*{  PLAN B
		"Block de facturas", 
		"Revista", 
		"Calendario",
		"Cuaderno"
	};*/

	OrdenDeTrabajo()
	{	
		
		
		super ("Orden de Trabajo (OT)", false, true, false, true);
		
		
		
		
		
		setSize (680, 680);
		
		jpOrdenDeTrabajo.setBounds (0, 0, 500, 115);

		lbNro = new JLabel ("Número:");
		lbNro.setBounds(15, 20, 80, 25);
		lbNro.setForeground (Color.black);
		
	    lbCliente = new JLabel ("Cliente:");
	    lbCliente.setBounds(355, 20, 80, 25);
		lbCliente.setForeground (Color.black);
	    
		lbFechaC = new JLabel ("Fecha confec.:");
		lbFechaC.setBounds(15, 55, 80, 25);
		lbFechaC.setForeground (Color.black);
		
		lbFechaP = new JLabel ("Fecha prom.:");
		lbFechaP.setBounds(355, 55, 80, 25);
		lbFechaP.setForeground (Color.black);
		
		lbNombreOT = new JLabel ("Nombre OT:");
		lbNombreOT.setBounds(15, 90, 80, 25);
		lbNombreOT.setForeground (Color.black);
		
		lbEstado = new JLabel ("Estado:");
		lbEstado.setBounds(355, 90, 80, 25);
		lbEstado.setForeground (Color.black);
		
		lbDescripcion = new JLabel ("Descripcion:");
		lbDescripcion.setBounds(15, 125, 80, 25);
		lbDescripcion.setForeground (Color.black);

		String maxIdOT=Orden_Trabajo.getUltOT()+"";
		txtNro = new JTextField (maxIdOT);
		txtNro.setEditable(false);
		txtNro.setForeground(Color.RED);
		txtNro.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNro.setFocusable(false);
		txtNro.setBounds(105, 20, 210, 25);
		txtNro.setHorizontalAlignment (JTextField.LEFT);
		
		cboCliente = new JComboBox<String> (Clientes);
		cboCliente.setBounds(445, 20, 210, 25);
		
		txtNombreOT = new JTextField ();
		txtNombreOT.setBounds(105, 90, 210, 25);
		txtNombreOT.setHorizontalAlignment (JTextField.LEFT);
		
		txtDescripcion = new JTextField ();
		txtDescripcion.setBounds(105, 125, 550, 25);
		txtDescripcion.setHorizontalAlignment (JTextField.LEFT);
		
		lbAncho = new JLabel ("Ancho:");
		lbAncho.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAncho.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbAncho.setBounds(105, 161, 80, 25);
		lbAncho.setForeground (Color.black);
		
		txtAncho = new JTextField ("0");
		txtAncho.setBounds(202, 161, 100, 25);
		txtAncho.setHorizontalAlignment (JTextField.LEFT);
		
		lbAlto = new JLabel ("Alto:");
		lbAlto.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAlto.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbAlto.setBounds(312, 161, 80, 25);
		lbAlto.setForeground (Color.black);
		
		txtAlto = new JTextField ("0");
		txtAlto.setBounds(402, 161, 100, 25);
		txtAlto.setHorizontalAlignment (JTextField.LEFT);
		
		chbApaisado = new JCheckBox ("Apaisado");
		chbApaisado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chbApaisado.setBounds(580, 160, 80, 25);
		
		lbTipoDeProducto = new JLabel ("Tipo de Prod.:");
		lbTipoDeProducto.setBounds(15, 195, 80, 25);
		lbTipoDeProducto.setForeground (Color.black);
		
		lbCantidadAEntregar = new JLabel ("Cant. a Entr.:");
		lbCantidadAEntregar.setBounds(355, 195, 80, 25);
		lbCantidadAEntregar.setForeground (Color.black);
		
		txtCantidadAEntregar = new JTextField ("1");
		txtCantidadAEntregar.setBounds(445, 195, 210, 25);
		txtCantidadAEntregar.setHorizontalAlignment (JTextField.LEFT);
		
		lbPreimpresion = new JLabel ("Preimpresión:");
		lbPreimpresion.setBounds(15, 230, 80, 25);
		lbPreimpresion.setForeground (Color.black);
		
		txtPreimpresion = new JTextField ("0");
		txtPreimpresion.setBounds(105, 230, 210, 25);
		txtPreimpresion.setHorizontalAlignment (JTextField.LEFT);
		
		lbCantidadDeHojasUtilizadas = new JLabel ("Hojas utilizadas:");
		lbCantidadDeHojasUtilizadas.setBounds(15, 554, 100, 25);
		lbCantidadDeHojasUtilizadas.setForeground (Color.black);
		
		txtCantidadDeHojasUtilizadas = new JTextField ("0");
		txtCantidadDeHojasUtilizadas.setBounds(105,554, 100, 25);
		txtCantidadDeHojasUtilizadas.setHorizontalAlignment (JTextField.LEFT);
		
		tabSecciones = new JTabbedPane();
		tabSecciones.setBounds(15, 265, 640, 278);

		//Restriccion para que el usuario solo ingrese número.
		txtAncho.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtAlto.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtCantidadAEntregar.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtPreimpresion.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);

		cboMes = new JComboBox<String> (Meses); //Comentar esta línea si quieren utilizar el WB
		//cboMes = new JComboBox<String> ();	//Descomentar esta línea para utilizar el WB
		cboMes.setBounds(105, 55, 97, 25);
		
		cboDia = new JComboBox<String> ();
		cboDia.setBounds(202, 55, 48, 25);
		
		cboAnio = new JComboBox<String> ();
		cboAnio.setBounds(250, 55, 65, 25);
		
		cboEstado = new JComboBox<String> (Estados);	//Comentar esta línea si quieren utilizar el WB
		cboEstado = new JComboBox<String> ();
		cboEstado.setToolTipText("Pendiente");
		cboEstado.setBounds(445, 90, 210, 25);
		cboEstado.setEnabled(false);
		
		cboTipoDeProducto = new JComboBox<String> (TipoDeProducto); //Comentar esta línea si quieren utilizar el WB
		//cboTipoDeProducto = new JComboBox<String> ();				//Descomentar esta línea para utilizar el WB
		cboTipoDeProducto.setBounds(105, 195, 210, 25);
		
		for (int i = 1; i <= 31; i++) 
		{
			String dias = "" + i;
			cboDia.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio.addItem (anios);
		}
		
		cboMes2 = new JComboBox<String> (Meses);
		//cboMes2 = new JComboBox<String> ();
		cboMes2.setBounds(445, 55, 97, 25);
		
		cboDia2 = new JComboBox<String> ();
		cboDia2.setBounds(542, 55, 48, 25);
		
		cboAnio2 = new JComboBox<String> ();
		cboAnio2.setBounds(590, 55, 65, 25);
		
		for (int i = 1; i <= 31; i++) 
		{
			String dias = "" + i;
			cboDia2.addItem (dias);
		}
		
		for (int i = 2012; i <= 2042; i++) 
		{
			String anios = "" + i;
			cboAnio2.addItem (anios);
		}
				
		btnLimpiarOT = new JButton("Limpiar campos");
		btnLimpiarOT.setBounds(15, 599, 121, 35);
		btnLimpiarOT.addActionListener (this);
					
		btnGuardar = new JButton ("Confirmar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnGuardar.setBounds(403, 599, 120, 35);
		btnGuardar.addActionListener (this);
		
		btnCancelar = new JButton ("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		btnCancelar.setBounds(535, 599, 120, 35);
		btnCancelar.addActionListener (this);
		
		jpOrdenDeTrabajo.setLayout(null);

		jpOrdenDeTrabajo.add (lbNro);
		jpOrdenDeTrabajo.add (txtNro);
		jpOrdenDeTrabajo.add (lbCliente);
		jpOrdenDeTrabajo.add (cboCliente);
		jpOrdenDeTrabajo.add (lbFechaC);
		jpOrdenDeTrabajo.add (lbFechaP);
		jpOrdenDeTrabajo.add (cboMes);
		jpOrdenDeTrabajo.add (cboDia);
		jpOrdenDeTrabajo.add (cboAnio);
		jpOrdenDeTrabajo.add (cboMes2);
		jpOrdenDeTrabajo.add (cboDia2);
		jpOrdenDeTrabajo.add (cboAnio2);
		jpOrdenDeTrabajo.add (cboEstado);	
		jpOrdenDeTrabajo.add (lbNombreOT);
		jpOrdenDeTrabajo.add (lbEstado);
		jpOrdenDeTrabajo.add (lbDescripcion);
		jpOrdenDeTrabajo.add (txtNombreOT);
		jpOrdenDeTrabajo.add (txtDescripcion);
		jpOrdenDeTrabajo.add (lbAlto);
		jpOrdenDeTrabajo.add (lbAncho);
		jpOrdenDeTrabajo.add (chbApaisado);
		jpOrdenDeTrabajo.add (txtAlto);
		jpOrdenDeTrabajo.add (txtAncho);
		jpOrdenDeTrabajo.add (lbTipoDeProducto);
		jpOrdenDeTrabajo.add (lbCantidadAEntregar);
		jpOrdenDeTrabajo.add (lbPreimpresion);
		jpOrdenDeTrabajo.add (lbCantidadDeHojasUtilizadas);
		jpOrdenDeTrabajo.add (txtCantidadDeHojasUtilizadas);
		jpOrdenDeTrabajo.add (txtPreimpresion);
		jpOrdenDeTrabajo.add (cboTipoDeProducto);
		jpOrdenDeTrabajo.add (txtCantidadAEntregar);
		jpOrdenDeTrabajo.add (tabSecciones);
		jpOrdenDeTrabajo.add (btnLimpiarOT);
		jpOrdenDeTrabajo.add (btnGuardar);
		jpOrdenDeTrabajo.add (btnCancelar);
		
		getContentPane().add (jpOrdenDeTrabajo);

		setVisible (true);
		
		//Para la pestaña de la Seccion Elementos
		JPanel panElementos = new JPanel();
		
		panElementos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panElementos.setBounds(0, 0, 640, 250);
		
			lbOriginal = new JLabel("Original: ");
			lbOriginal.setBounds(15, 10, 100, 25);
			
			lbDuplicado = new JLabel("Duplicado: ");
			lbDuplicado.setBounds(15, 45, 100, 25);
			
			lbTriplicado = new JLabel("Triplicado: ");
			lbTriplicado.setBounds(15, 80, 100, 25);
			
			txtOriginal = new JTextField("0");
			txtOriginal.setBounds(115, 10, 100, 25);
			
			txtDuplicado = new JTextField("0");
			txtDuplicado.setBounds(115, 45, 100, 25);
			
			txtTriplicado = new JTextField("0");
			txtTriplicado.setBounds(115, 80, 100, 25);
			
			lbTapa = new JLabel("Tapa: ");
			lbTapa.setBounds(15, 115, 100, 25);
			
			lbPaginasInteriores = new JLabel("Páginas. Int.: ");
			lbPaginasInteriores.setBounds(15, 150, 100, 25);
			
			txtTapas = new JTextField("0");
			txtTapas.setBounds(115, 115, 100, 25);
			
			txtPaginasInteriores = new JTextField("0");
			txtPaginasInteriores.setBounds(115, 150, 100, 25);
			
		panElementos.setLayout(null);

		panElementos.add(lbOriginal);
		panElementos.add(lbDuplicado);
		panElementos.add(lbTriplicado);
		panElementos.add(lbTapa);
		panElementos.add(lbPaginasInteriores);
		panElementos.add(txtOriginal);
		panElementos.add(txtDuplicado);
		panElementos.add(txtTriplicado);
		panElementos.add(txtTapas);
		panElementos.add(txtPaginasInteriores);
		
		tabSecciones.addTab
		(
			"Elementos",
			new ImageIcon ("Imagenes/registrar.png"), 
			panElementos,
	        "Elementos del producto"
		);
		
		tabSecciones.setEnabledAt(0, true);
		tabSecciones.setDisabledIconAt(0, null);
		tabSecciones.setMnemonicAt(0, KeyEvent.VK_E);
		
		
		try
		{
		   Class.forName("com.mysql.jdbc.Driver");
           Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/Magesti","tp_labo", "laboratorio");
          
           Statement s = conexion.createStatement();
           ResultSet rs = s.executeQuery ("select * from materiales");
           ResultSetMetaData metaDatos = rs.getMetaData();
           dtmMateriales  = new DefaultTableModel ();

   		   JPanel panMateriales = new JPanel();
           panMateriales.setBorder(new EmptyBorder(5, 5, 5, 5));
           panMateriales.setLayout(null);
           
           int numeroColumnas = metaDatos.getColumnCount();
           Object[] etiquetas = new Object[numeroColumnas];

           for (int i = 0; i < numeroColumnas; i++)
           {
              etiquetas[i] = metaDatos.getColumnLabel(i + 1); 
           }
          
           
           	tablaMateriales = new JTable(dtmMateriales);
           	tablaMateriales.setBounds(0, 0, 640, 248);
           	tablaMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
           	tablaMateriales.setEnabled(true);
           	
           	
           	dtmMateriales.setColumnIdentifiers(etiquetas);
 

           	while (rs.next())
        	{
        	   Object [] fila = new Object[numeroColumnas];
        	   for (int i=0;i<10;i++)
        	      fila[i] = rs.getObject(i+1);
        	   dtmMateriales.addRow(fila); 
        	   
        	}
            panMateriales.add(tablaMateriales);

    		tabSecciones.addTab
    		(
    			"Materiales",
    			new ImageIcon ("Imagenes/registrar.png"), 
    			panMateriales,
    		    "Materiales involucrados"
    		);
    		
    		tabSecciones.setMnemonicAt(1, KeyEvent.VK_M);
           conexion.close();
		} 
		catch (Exception e)
		{
		   e.printStackTrace();
		}
      
      
      
		JPanel panOrdenEjecucion = new JPanel();
		panOrdenEjecucion.setBorder(new EmptyBorder(5, 5, 5, 5));
		panOrdenEjecucion.setLayout(null);
		
			//Acá hay una tabla que se llenaría con la tabla de Orden de Ejecución
        	tablaOE = new JTable();
        	tablaOE.setBounds(0, 0, 640, 248);
        	tablaOE.setBorder(new LineBorder(new Color(0, 0, 0)));
        	tablaOE.setEnabled(false);
        	
        panOrdenEjecucion.add(tablaOE);
        
		tabSecciones.addTab
		(
			"Orden de ejecución",
			new ImageIcon ("Imagenes/registrar.png"), 
			panOrdenEjecucion,
	        "Listado de tareas o procesos"
		);
		
		tabSecciones.setMnemonicAt(2, KeyEvent.VK_O);
		
		JLabel lblMedidaFinal = new JLabel("Medida Final");
		lblMedidaFinal.setBounds(15, 166, 80, 14);
		jpOrdenDeTrabajo.add(lblMedidaFinal);
		
		//Verificar que lo ingresado sea sólo numérico
		txtOriginal.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtDuplicado.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtTriplicado.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtTapas.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtPaginasInteriores.addKeyListener 
		(
				new KeyAdapter() 
				{
					public void keyTyped (KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
				}
		);
		
		txtClear();

	}

	//Chequear un poco lo ingresado antes de guardar
	public void actionPerformed (ActionEvent ae) 
	{

		Object obj = ae.getSource();

		if (obj == btnGuardar) 
		{
			
			if (txtCantidadDeHojasUtilizadas.getText().equals("0")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this,
					"Debe declarar la cantidad de Hojas Utilizadas",
					qTITULO + " - Campo vacío",
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtCantidadDeHojasUtilizadas.requestFocus();
			}
			else if (txtNombreOT.getText().equals("")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"Esta OT no tiene nombre asignado",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtNombreOT.requestFocus ();
				
			}
			else if (txtDescripcion.getText().equals("")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"No hay descripción de este trabajo",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtDescripcion.requestFocus ();
				
			}
			else if (txtCantidadAEntregar.getText().equals("") || txtCantidadAEntregar.getText().equals("0")) 
			{
				
				JOptionPane.showMessageDialog 
				(
					this, 
					"Debe especificar un valor en la Cantidad a Entregar",
					qTITULO + " - Campo vacío", 
					JOptionPane.PLAIN_MESSAGE
				);
				
				txtCantidadAEntregar.requestFocus ();
				
			}
			else 
 {

				cargarTablas(); // Cargaría la tabla en memoria
				obj = btnCancelar;

			}
		}
		if (obj == btnCancelar) 
		{
			txtClear ();
			setVisible (false);
			dispose();
		}
		if (obj == btnLimpiarOT)
		{
			txtClear ();
		}

	}
	
	void cargarTablas() 
	{
		/*
		 * 	Aquí colocaríamos el código para cargar la tabla
		 */
		
		String fechaCon = (String) cboAnio.getSelectedItem() +"-"+ dameNumeroMes((String)cboMes.getSelectedItem()) +"-"+ cboDia.getSelectedItem();
		String fechaProm = (String) cboAnio2.getSelectedItem() +"-"+ dameNumeroMes((String) cboMes2.getSelectedItem()) +"-"+ cboDia2.getSelectedItem();
		Integer cantImp =  Integer.parseInt(txtPreimpresion.getText());
		Integer ancho = Integer.parseInt(txtAncho.getText());
		Integer alto = Integer.parseInt(txtAlto.getText());
		
		boolean apaisado=chbApaisado.isSelected();

		Orden_Trabajo ot1= new Orden_Trabajo(1, 2, fechaCon, fechaProm, txtNombreOT.getText(), txtDescripcion.getText(),cantImp,ancho,alto,apaisado,"Pendiente");
		ot1.Alta();
		
	}
	
	static String dameNumeroMes(String mes)
	{
		if(mes == "Enero")
		{
			return "01";
		}
		else if(mes == "Febrero")
		{
			return "02";
		}
		else if(mes == "Marzo")
		{
			return "03";
		}
		else if(mes == "Abril")
		{
			return "04";
		}else if(mes == "Mayo")
		{
			return "05";
		}
		else if(mes=="Junio")
		{
			return "06";
		}
		else if(mes=="Julio")
		{
			return "07";
		}
		else if(mes=="Agosto")
		{
			return "08";
		}
		else if(mes=="Septiembre")
		{
			return "09";
		}
		else if(mes=="Octubre")
		{
			return "10";
		}
		else if(mes=="Noviembre")
		{
			return "11";
		}
		else
		{
			return "12";
		}

	}
	
	
	
	void txtClear () 
	{
		txtNombreOT.setText ("");
		txtDescripcion.setText ("");
		txtCantidadDeHojasUtilizadas.setText ("0");
		txtAncho.setText("0");
		txtAlto.setText("0");
		txtCantidadAEntregar.setText("1");
		txtPreimpresion.setText("0");
		txtOriginal.setText("0");
		txtDuplicado.setText("0");
		txtTriplicado.setText("0");
		txtTapas.setText("0");
		txtPaginasInteriores.setText("0");
		txtCantidadDeHojasUtilizadas.setText("0");
		chbApaisado.setSelected(false);
	}
	
}	