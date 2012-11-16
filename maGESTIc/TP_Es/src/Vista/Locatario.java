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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@SuppressWarnings("serial")
public class Locatario extends JInternalFrame implements ActionListener
{
	private JPanel jpLocatario = new JPanel();
	private JTable tablaLocatarios;
	private String guardados[][] = new String [500][3];
	private String registros[][] = new String [500][3];
	private int contador = 0;
	private int filas = 0;
	private	int total = 0;
	private	int auxiliar = 0;
	private FileInputStream fis;
	private DataInputStream dis;
	DefaultTableModel tablaTempDatos;
	Locatario() 
	{
		super ("Administraci\u00F3n de Locatarios", false, true, false, true);
		setSize (456, 304);
		contador = filas()+1;
		jpLocatario.setBounds(0, 0, 440, 184);
		jpLocatario.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 440, 184);
		jpLocatario.add(scrollPane);
		String[] Columnas = new String[] 
			{
				"Número", 
				"CUIT/CUIL", 
				"Nombre"
			};
		
		tablaLocatarios = new JTable();
		tablaLocatarios.setModel
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
			tablaLocatarios.getColumnModel().getColumn(i).setPreferredWidth(125);
			tablaLocatarios.getColumnModel().getColumn(i).setResizable(false);
		}
		tablaLocatarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaLocatarios.getColumnModel().getColumn(0).setCellRenderer(tablaLocatarios.getTableHeader().getDefaultRenderer());
		scrollPane.setViewportView(tablaLocatarios);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablaTempDatos = (DefaultTableModel) tablaLocatarios.getModel();
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 236, 440, 29);
		jpLocatario.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		filas();
		JLabel lblNewLabel = new JLabel("  Recuerde que cada fila es un Locatario (no puede editar la columna \"N\u00FAmero\")");
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
						DefaultTableModel temp = (DefaultTableModel) tablaLocatarios.getModel();
						if(temp.getRowCount()>0)
						{
							temp.removeRow(tablaLocatarios.getSelectedRow());	
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
		jpLocatario.add(btnBorrar);
		
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
		jpLocatario.add(btnCancelar);
		
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
		jpLocatario.add(btnNewButton);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					Object nuevaFila[]= 
					{
						contador,
						"",
						""
					};
					tablaTempDatos.addRow(nuevaFila);
					contador++;
				}
			}
		);
		btnAgregar.setBounds(143, 202, 89, 23);
		jpLocatario.add(btnAgregar);
		getContentPane().add(jpLocatario);
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
		
		for(int i=0;i<3;i++)
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
			fis = new FileInputStream ("Locatarios.dat");
			dis = new DataInputStream (fis);
			while (true) 
			{
				for (int i = 0; i < 3; i++) 
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
	
	public String[] datos() 
	{
		String[] cuils = new String[filas()];
		String[] aux = new String[filas()];
		int cont=0;
		try 
		{
			fis = new FileInputStream ("Locatarios.dat");
			dis = new DataInputStream (fis);
			while (true) 
			{
				for (int i = 0; i < 3; i++) 
				{
					aux[i] = dis.readUTF ();
					if (i==1)
					{
						System.out.println(aux[i]);
						cuils[cont]=aux[i];
						System.out.println(cuils.length);
					}
				}
				cont++;
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
		return cuils;
	}

	void saveFile () 
	{
		
		try 
		{
			FileOutputStream fos = new FileOutputStream ("Locatarios.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			for (int i=0;i<3;i++)
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
	public String[] data (String cuil) 
	{
		String[] data = new String[2];
		boolean found = false;
		for (int x = 0; x < 2; x++) 
		{
			if (registros[x][1].equals (cuil)) 
			{
				found = true;
				data[0] = registros[x][1];
				data[1] = registros[x][2];
				break;
			}
		}
		if (found == false) {

			JOptionPane.showMessageDialog (this, "El CUIL No. " + cuil+ " no existe.",
					"AdmIN - CUIL", JOptionPane.PLAIN_MESSAGE);
		}
		return data;

	}
	
	
}

