package vista_Controlador;

import javax.swing.JInternalFrame;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Adm_Cliente extends JInternalFrame 
{
	
	private JTable tablaDatosCliente;
	private JTable tablaContactoCliente;
	public Adm_Cliente() 
	{
		super ("Administracion de Clientes", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		getContentPane().setLayout(null);
		
		JButton button = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		button.setBounds(10, d.height-225, 120, 35);
		getContentPane().add(button);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-185, 120, 35);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		}
		);
		getContentPane().add(btnConfirmar);
		//
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-40, d.height-165);

		getContentPane().add(tabbedPane);
		// DATOS
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		panelDatos.setLayout(null);
		
		tabbedPane.addTab("Datos                         ",  new ImageIcon ("Imagenes/datos.png"), panelDatos, null);


		Object[][] dataDC = 
		{
			{"test s.a", "00-00000000-0","test","test","00-0000-0000"},
		};

		String[] columnaNombreDC =
		{
			"Razón social", 
			"CUIT",
            "Condición IVA",
            "Dirección",
            "Teléfono"
        };
		
		JScrollPane spDatosCliente = new JScrollPane();
		spDatosCliente.setViewportBorder(null);
		spDatosCliente.setBounds(0, 0, d.width-210, d.height-165);
		panelDatos.add(spDatosCliente);
		
		tablaDatosCliente = new JTable(dataDC, columnaNombreDC);
		spDatosCliente.setViewportView(tablaDatosCliente);
		// CONTACTO
		JPanel panelContacto = new JPanel();
		panelContacto.setBorder
		(
			new LineBorder
			(
				new Color(0, 0, 0)
			)
		);
		panelContacto.setLayout(null);
		
		tabbedPane.addTab("Contacto                      ",  new ImageIcon ("Imagenes/contacto.png"), panelContacto, null);
		
		Object[][] dataCC = 
			{
				{"test", "00-0000-0000","test@test"},
			};

			String[] columnaNombreCC =
			{
				"Nombre", 
				"Teléfono",
	            "Correo electrónico"
	        };
			
		JScrollPane spClienteContacto = new JScrollPane();
		spClienteContacto.setBounds(0, 0, d.width-210, d.height-165);
		panelContacto.add(spClienteContacto);
		
		tablaContactoCliente = new JTable(dataCC, columnaNombreCC);
		spClienteContacto.setViewportView(tablaContactoCliente);
		
	}
}
