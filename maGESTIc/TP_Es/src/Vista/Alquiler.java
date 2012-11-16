package Vista;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@SuppressWarnings("serial")
public class Alquiler extends JInternalFrame implements ActionListener
{
	private JPanel jpAlquiler = new JPanel();
	private JTable tablaAlquileres;
	private String guardados[][] = new String [500][8];
	private String registros[][] = new String [500][8];
	private int contador = 0;
	private int filas = 0;
	private	int total = 0;
	private	int auxiliar = 0;
	private FileInputStream fis;
	private DataInputStream dis;
	DefaultTableModel tablaTempDatos;
	Alquiler() 
	{
		super ("Administraci\u00F3n de Alquileres", false, true, false, true);
		setSize (456, 304);
		contador = filas()+1;
		jpAlquiler.setBounds(0, 0, 440, 184);
		jpAlquiler.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 440, 184);
		jpAlquiler.add(scrollPane);
		String[] Columnas = new String[] 
			{
				"Número", 
				"Inmueble", 
				"Locatario",
				"Fecha de Firma", 
				"Fecha de Inicio", 
				"Fecha de Finalización", 
				"Monto Mensual", 
				"Estado"
			};
		
		tablaAlquileres = new JTable();
		tablaAlquileres.setModel
		(
			new DefaultTableModel
			(
				new Object[][] 
				{
				},
				Columnas
			)
			{
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] 
				{
					Integer.class, 
					String.class, 
					String.class, 
					String.class, 
					String.class, 
					String.class, 
					String.class, 
					String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) 
				{
					return columnTypes[columnIndex];
				}
			
			 @Override 
			   public boolean isCellEditable (int row, int column) 
			   {   
				 if (column==0)
					 return false; 
				 else
					 return true;
			   } 
			}
		);
		
		
		for (int i=0;i<Columnas.length;i++)
		{
			tablaAlquileres.getColumnModel().getColumn(i).setPreferredWidth(125);
			tablaAlquileres.getColumnModel().getColumn(i).setResizable(false);
		}
		tablaAlquileres.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaAlquileres.getColumnModel().getColumn(0).setCellRenderer(tablaAlquileres.getTableHeader().getDefaultRenderer());
		
		
		Locatario loc = new Locatario();
		TableColumn columnaLocatario= tablaAlquileres.getColumnModel().getColumn(2);
		columnaLocatario.setCellEditor(new MyComboBoxEditor(loc.datos()));
		
		
		
		scrollPane.setViewportView(tablaAlquileres);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablaTempDatos = (DefaultTableModel) tablaAlquileres.getModel();
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 236, 440, 29);
		jpAlquiler.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		filas();
		
		tablaAlquileres.addMouseListener
		(
			new MouseAdapter() 
			{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int filaElegida = tablaAlquileres.rowAtPoint(arg0.getPoint());
				
				showRec(filaElegida);
			}
		});
		
		JLabel lblNewLabel = new JLabel("  Recuerde que cada fila es un alquiler (no puede editar la columna \"N\u00FAmero\")");
		panel_1.add(lblNewLabel, BorderLayout.WEST);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{	
						DefaultTableModel temp = (DefaultTableModel) tablaAlquileres.getModel();
						if(temp.getRowCount()>0)
						{
							temp.removeRow(tablaAlquileres.getSelectedRow());	
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
					}
				}
			}
		);
		btnBorrar.setBounds(10, 202, 89, 23);
		jpAlquiler.add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					setVisible(false);
					dispose();
				}
			}
		);
		btnCancelar.setBounds(341, 202, 89, 23);
		jpAlquiler.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(242, 202, 89, 23);
		btnNewButton.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					saveArray();
				}
			}
		);
		jpAlquiler.add(btnNewButton);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					Object nuevaFila[]= 
					{
						ultimoID()+1,
						"",
						"",
						"",
						"",
						"",
						"",
						""
					};
					tablaTempDatos.addRow(nuevaFila);
					contador++;
				}
			}
		);
		btnAgregar.setBounds(143, 202, 89, 23);
		jpAlquiler.add(btnAgregar);
		getContentPane().add(jpAlquiler);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
	}
	
	@SuppressWarnings("unused")
	void saveArray () 
	{
		boolean ok = true;
		if (tablaTempDatos.getRowCount()==0)
		{
			JOptionPane.showMessageDialog 
			(
				this, 
				"No hay datos para cargar",
				"AdmIN - Campos vacíos", 
				JOptionPane.WARNING_MESSAGE
			);
			ok = false;
		}
		
		for(int i=0;i<8;i++)
		{
			if (false)
			{
				
			}
			else if (ok && (tablaTempDatos.getValueAt(auxiliar, i).equals(null) || tablaTempDatos.getValueAt(auxiliar, i)==""))
			{
				JOptionPane.showMessageDialog 
				(
					this, 
					"Hay campos vacíos",
					"AdmIN - Campos vacíos", 
					JOptionPane.WARNING_MESSAGE
				);
				ok = false;
				break;
			}
			else if (ok)
			{
				guardados[contador][i] = tablaTempDatos.getValueAt(auxiliar, i).toString();
			}
		}
		
		if (ok)
		{
			saveFile ();
			auxiliar++;
		}
	
	}

	public int filas () 
	{
		try 
		{
			fis = new FileInputStream ("Alquileres.dat");
			dis = new DataInputStream (fis);
			while (true) 
			{
				for (int i = 0; i < 8; i++) 
				{
					registros[filas][i] = dis.readUTF ();
				}
				tablaTempDatos.addRow(registros[filas]);
				filas++;
			}
		}
		catch (Exception ex) 
		{
			total = filas;
			if (total == 0) 
			{ 
				
			}
			else 
			{
				try 
				{
					dis.close();
					fis.close();
				}
				catch (Exception exp) 
				{ 
					
				}
			}
		}
		return filas;
	}

	void saveFile () 
	{
		
		try 
		{
			FileOutputStream fos = new FileOutputStream ("Alquileres.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			for (int i=0;i<8;i++)
			{
				dos.writeUTF (guardados[contador][i]);
			}
			JOptionPane.showMessageDialog 
			(
					this, 
					"El registro se guardó exitosamente",
					"AdmIN - Registro Guardado", 
					JOptionPane.PLAIN_MESSAGE
			);
			dos.close();
			fos.close();
		}
		catch (IOException ioe) 
		{
			JOptionPane.showMessageDialog 
			(
					this, 
					"Hay problemas con el archivo",
					"AdmIN - Error", 
					JOptionPane.PLAIN_MESSAGE
			);
		}

	}
	public int ultimoID() 
	{
		int ultimo = 0;
		try 
		{
			fis = new FileInputStream ("Alquileres.dat");
			dis = new DataInputStream (fis);
			while (true) 
			{
				for (int i = 0; i < 8; i++) 
				{
					registros[filas][0] = dis.readUTF ();
					ultimo = Integer.valueOf(registros[filas][0]);
					
				}
				filas++;
				
			}
		}
		catch (Exception ex) 
		{
			total = filas;
			if (total == 0) 
			{ 
				
			}
			else 
			{
				try 
				{
					dis.close();
					fis.close();
				}
				catch (Exception exp) 
				{ 
					
				}
			}
		}
		return ultimo;
	}
	
	public void showRec (int intRec) 
	{

		findRec(registros[intRec][2]);
	}
	
	void findRec (String cuil) 
	{
		Locatario loc = new Locatario();
		String[] terna = loc.data(cuil);
		formLocatario flo = new formLocatario(terna[1],terna[0]);
		getDesktopPane().add(flo);
		flo.show ();
	}
	
	
}

