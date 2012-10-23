package vista_Controlador;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Calidad;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Procesos_x_OT;
import Modelo.Proveedor;
import Modelo.Variante;

@SuppressWarnings("serial")
public class Calendario 
extends JInternalFrame 
implements ItemListener,ActionListener, Config
{
	private JPanel pan1 = new JPanel();
	private JPanel pan2 = new JPanel();
	private JPanel pan3 = new JPanel();


	
	private Choice chAnio  = new Choice();
	private Choice chMes = new Choice();

	private JLabel lblAnio  = new JLabel("   Seleccione el Año: ");
	private JLabel lblMes = new JLabel("   seleccione el Mes: ");
	ArrayList<String> ot2 = new ArrayList();
	private JButton btnMatriz[]= new JButton[31];

  	String dias[] = 
  	{
		"DOMINGO", 
		"LUNES", 
		"MARTES", 
		"MIÉRCOLES",
		"JUEVES",
		"VIERNES", 
		"SÁBADO"
  	};

  	String meses[]=
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

	String diaTemp="", anio, mm, dd;

	int 
	btn_tag=1,
	diaSeleccionado=1, 
	Anio, 
	DiaDeLaSemana,
	totalDiasDelMes,
	MM, 
	id=1;
	boolean 
	filaUno=true;

	JLabel txt_lbl;
	TextArea ta;
	JButton Ingresar,Modificar,Borrar,Siguiente,Anterior,Salir;
	private final JPanel panel = new JPanel();
	
	private java.util.Date fechaHoy= new java.util.Date ();
	
	private SimpleDateFormat fechaDiaHoy = new SimpleDateFormat ("dd", Locale.getDefault());
	private SimpleDateFormat fechaMesHoy = new SimpleDateFormat ("MM", Locale.getDefault());
	private SimpleDateFormat fechaAnioHoy = new SimpleDateFormat ("yy", Locale.getDefault());
	private SimpleDateFormat fechaAnio4Hoy = new SimpleDateFormat ("yyyy", Locale.getDefault());
	private SimpleDateFormat fechaMesNombreHoy = new SimpleDateFormat ("MMMM", Locale.getDefault());
	private String diaHoy = fechaDiaHoy.format (fechaHoy);
	private String mesHoy = fechaMesHoy.format (fechaHoy);
	private String anioHoy = fechaAnioHoy.format (fechaHoy);
	private String mesNHoy = fechaMesNombreHoy.format (fechaHoy);
	private String anio4Hoy = fechaAnio4Hoy.format (fechaHoy);
	Calendario()
	{
		super("Calendario");
		setIconifiable(true);
		setClosable(true);			
		aplylisteners();		
		startup();
	}
	

	public Choice getChAnio() 
	{
		return chAnio;
	}


	public void setChAnio(Choice chAnio) 
	{
		this.chAnio = chAnio;
	}


	public Choice getChMes() 
	{
		return chMes;
	}


	public void setChMes(Choice chMes) 
	{
		this.chMes = chMes;
	}


	public JButton[] getBtnMatriz() 
	{
		return btnMatriz;
	}


	public void setBtnMatriz(JButton[] btnMatriz) 
	{
		this.btnMatriz = btnMatriz;
	}


	public String[] getMeses() 
	{
		return meses;
	}


	public void setMeses(String[] meses) 
	{
		this.meses = meses;
	}


	public void setAnio(String anio) 
	{
		this.anio = anio;
	}


	public String getMm() 
	{
		return mm;
	}


	public void setMm(String mm) 
	{
		this.mm = mm;
	}


	public String getDd() 
	{
		return dd;
	}


	public void setDd(String dd) 
	{
		this.dd = dd;
	}


	public int getBtn_tag() 
	{
		return btn_tag;
	}


	public void setBtn_tag(int btn_tag) 
	{
		this.btn_tag = btn_tag;
	}


	public int getAnio() 
	{
		return Anio;
	}


	public void setAnio(int anio) 
	{
		Anio = anio;
	}


	public int getDiaDeLaSemana() 
	{
		return DiaDeLaSemana;
	}


	public void setDiaDeLaSemana(int diaDeLaSemana) 
	{
		DiaDeLaSemana = diaDeLaSemana;
	}


	public int getMM() 
	{
		return MM;
	}


	public void setMM(int mM) 
	{
		MM = mM;
	}


	public JButton getBorrar() 
	{
		return Borrar;
	}


	public void setBorrar(JButton borrar) 
	{
		Borrar = borrar;
	}


	public JButton getAnterior() 
	{
		return Anterior;
	}


	public void setAnterior(JButton anterior) 
	{
		Anterior = anterior;
	}


	public java.util.Date getFechaHoy() 
	{
		return fechaHoy;
	}


	public void setFechaHoy(java.util.Date fechaHoy) 
	{
		this.fechaHoy = fechaHoy;
	}


	public SimpleDateFormat getFechaDiaHoy() 
	{
		return fechaDiaHoy;
	}


	public void setFechaDiaHoy(SimpleDateFormat fechaDiaHoy) 
	{
		this.fechaDiaHoy = fechaDiaHoy;
	}


	public SimpleDateFormat getFechaMesHoy() 
	{
		return fechaMesHoy;
	}


	public void setFechaMesHoy(SimpleDateFormat fechaMesHoy) 
	{
		this.fechaMesHoy = fechaMesHoy;
	}


	public SimpleDateFormat getFechaAnioHoy() 
	{
		return fechaAnioHoy;
	}


	public void setFechaAnioHoy(SimpleDateFormat fechaAnioHoy) 
	{
		this.fechaAnioHoy = fechaAnioHoy;
	}


	public SimpleDateFormat getFechaAnio4Hoy() 
	{
		return fechaAnio4Hoy;
	}


	public void setFechaAnio4Hoy(SimpleDateFormat fechaAnio4Hoy) 
	{
		this.fechaAnio4Hoy = fechaAnio4Hoy;
	}


	public SimpleDateFormat getFechaMesNombreHoy() 
	{
		return fechaMesNombreHoy;
	}


	public void setFechaMesNombreHoy(SimpleDateFormat fechaMesNombreHoy) 
	{
		this.fechaMesNombreHoy = fechaMesNombreHoy;
	}


	public String getDiaHoy() 
	{
		return diaHoy;
	}


	public void setDiaHoy(String diaHoy) 
	{
		this.diaHoy = diaHoy;
	}


	public String getMesHoy() 
	{
		return mesHoy;
	}


	public void setMesHoy(String mesHoy) 
	{
		this.mesHoy = mesHoy;
	}


	public String getAnioHoy() 
	{
		return anioHoy;
	}


	public void setAnioHoy(String anioHoy) 
	{
		this.anioHoy = anioHoy;
	}


	public String getMesNHoy() 
	{
		return mesNHoy;
	}


	public void setMesNHoy(String mesNHoy) 
	{
		this.mesNHoy = mesNHoy;
	}


	public String getAnio4Hoy() 
	{
		return anio4Hoy;
	}


	public void setAnio4Hoy(String anio4Hoy) 
	{
		this.anio4Hoy = anio4Hoy;
	}


	public void aplylisteners()
	{
		chAnio.addItemListener(this);
		chMes.addItemListener(this);
	}

	public void startup()
	{
		fixItems();
		panel1();		
		panel2();	
		panel3();
		
	}

	public void itemStateChanged(ItemEvent ie)
	{	
		panel3();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand();
		if(msg.indexOf('(')>=0)
		{
			int fin = msg.indexOf('(');
			msg = msg.substring(0, fin);
		}
		else
		{
			
		}
		diaSeleccionado=Integer.parseInt(msg.trim());
		db(diaSeleccionado);
		
	}

	public void fixItems()
	{
		for(int i=2000; i<=2030; i++)
			chAnio.add(" "+i); 
	
		for(int i=0; i<12; i++)
			chMes.add(meses[i]);
		chAnio.select(Integer.valueOf(anioHoy));
		chMes.select(Integer.valueOf(mesHoy)-1);
	}

	public void panel1()
	{
			pan1.setBackground(Color.GRAY);
			pan1.setLayout(new GridLayout(0, 4, 0, 0));
			lblAnio.setForeground(new Color(255, 255, 255));
			pan1.add(lblAnio);
			pan1.add(chAnio);
			lblMes.setForeground(new Color(255, 255, 255));
			pan1.add(lblMes);
			pan1.add(chMes);
			
	}

	public void panel2()
	{
		pan2.setForeground(Color.BLACK);
		pan2.setBackground(Color.ORANGE);
		pan2.removeAll();
		pan2.setLayout(new GridLayout(1,7,5,5));
		for(int i=0; i<7;i++)
			pan2.add(new JLabel(dias[i])); 
	}
	public void panel3()
	{
		pan3.removeAll(); 
		DiaDeLaSemana=0;
		totalDiasDelMes=0;
		calculosNecesarios();
		String tulTip = "";
		for(int i=0; i<totalDiasDelMes; i++)
		{
			btnMatriz[i]=new JButton(" "+btn_tag);
			btn_tag++;
			btnMatriz[i].addActionListener(this);
			if (i == Integer.valueOf(diaHoy))
			{
				if (chMes.getSelectedItem().toLowerCase().equals(mesNHoy.toLowerCase()) && chAnio.getSelectedItem().trim().equals(anio4Hoy.toString()))
				{
					btnMatriz[i-1].setForeground(Color.BLUE);
					btnMatriz[i-1].setIcon(new ImageIcon ("Imagenes/hoy.png"));
				}
			}
			else if (Orden_Trabajo.getId_OTSegunFecha(anio+"-"+ mm + "-" + i).size()>0)
			{
				Integer tamanio = Orden_Trabajo.getId_OTSegunFecha(anio+"-"+ mm + "-" + i).size();
				tulTip = Integer.valueOf(tamanio).toString();
				btnMatriz[i-1].setForeground(Color.RED);
				btnMatriz[i-1].setIcon(new ImageIcon ("Imagenes/tarea.png"));
				btnMatriz[i-1].setText(i + " (" + tulTip + ") " );
				String numFact = "";
				String texto = "<html>";
				for (int j=0;j<tamanio;j++)
				{
					numFact = Orden_Trabajo.getId_OTSegunFecha(anio+"-"+ mm + "-" + i).get(j);
					numFact = Metodos.EnteroAFactura(Integer.valueOf(numFact));
					texto = texto + numFact + "<br>";
					
				}	
				
				btnMatriz[i-1].setToolTipText(texto + "</html>");
			}
		}
		sketch();
		display();
	}

	public void sketch()
	{
		
		pan3.setLayout(new GridLayout(0,7));
		for(int i=0; i<totalDiasDelMes; i++)
		{
  			for(int g=1;g<8;g++)
  			{
				if(filaUno)
				{
					if(g<DiaDeLaSemana)
						pan3.add(new JLabel(""));
					else if(g>=DiaDeLaSemana)
					{ 	filaUno=false;
						pan3.add(btnMatriz[i]);
					}
				}
				else
				pan3.add(btnMatriz[i]);
			}
		}
		
		btnMatriz[Integer.parseInt(diaHoy)].setFocusPainted(true);
	}

	public void display()
	{
		filaUno=true;	
		btn_tag=1;
		getContentPane().add(pan1, BorderLayout.NORTH);
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(4, 0, 0,8));
		getContentPane().add(pan2, BorderLayout.CENTER);
		getContentPane().add(pan3, BorderLayout.SOUTH);
		pack();
	}

	public void calculosNecesarios()
	{
		Anio= Integer.parseInt(chAnio.getSelectedItem().trim());
		MM =chMes.getSelectedIndex();
		anio=Integer.toString(Anio);
		mm = Integer.toString(++MM);
		dd = Integer.toString(diaSeleccionado);
		diaTemp = anio + "/" + mm + "/" + dd;
		id=1;
		java.util.Date Date = null;
		try 
		{
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		    Date = formatter.parse(diaTemp);
		} 
		catch (ParseException e)
		{	
			System.out.println(e.toString());
		    e.printStackTrace();
		}
		java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
		cal.setTime(Date);
		DiaDeLaSemana= cal.get(java.util.Calendar.DAY_OF_WEEK);
		totalDiasDelMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public void db(int diaS)
	{
		String fecha=anio+"-"+ mm + "-" + diaS;
		ArrayList<String> ot= Orden_Trabajo.getId_OTSegunFecha(fecha);
		String id_ot="";
		String msg="";
		if(ot.size()>0)
		{
			for(int i=0;i<ot.size();i++)
			{
				id_ot=Metodos.EnteroAFactura(Integer.parseInt(ot.get(i)));
				muestra(id_ot);
			}
		}
		else
		{
			msg="No existen Ordenes de Trabajo para la fecha seleccionada";
			JOptionPane.showInternalMessageDialog(this, msg, "ORDEN TRABAJO", JOptionPane.PLAIN_MESSAGE);
		}
	}
		
	public void muestra(String ideOT)
	{
		ot2 = Orden_Trabajo.getOT_SegunID(ideOT.replaceAll("0001-",""));
		final OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
		getDesktopPane().add(nuevaOT);
		nuevaOT.show ();
		nuevaOT.getEstado().setEnabled(true);
		nuevaOT.getTxtNro().setText(Metodos.EnteroAFactura((Integer.valueOf(ot2.get(0)))));
		nuevaOT.getTipoProducto().setText(ot2.get(1).toString());
		nuevaOT.getTipoProducto().setEditable(false);
		nuevaOT.getCboMes().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(ot2.get(3).toString(), 1)));
		nuevaOT.getCboMes().setEnabled(false);
		nuevaOT.getCboDia().getModel().setSelectedItem(Metodos.separar(ot2.get(3).toString(), 2));
		nuevaOT.getCboDia().setEnabled(false);
		nuevaOT.getCboAnio().getModel().setSelectedItem(Metodos.separar(ot2.get(3).toString(), 0));
		nuevaOT.getCboAnio().setEnabled(false);
		nuevaOT.getCboMes2().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(ot2.get(4).toString(), 1)));
		nuevaOT.getCboMes2().setEnabled(false);
		nuevaOT.getCboDia2().getModel().setSelectedItem(Metodos.separar(ot2.get(4).toString(), 2));
		nuevaOT.getCboDia2().setEnabled(false);
		nuevaOT.getCboAnio2().getModel().setSelectedItem(Metodos.separar(ot2.get(4).toString(), 0));
		nuevaOT.getCboAnio2().setEnabled(false);
		nuevaOT.getTxtNombreOT().setText((String) ot2.get(5));
		nuevaOT.getTxtNombreOT().setEditable(false);
		nuevaOT.getTxtDescripcion().setText((String) ot2.get(6));
		nuevaOT.getTxtDescripcion().setEditable(false);
		nuevaOT.getTxtCantidadAEntregar().setText(ot2.get(6).toString());
		nuevaOT.getTxtCantidadAEntregar().setEditable(false);
		nuevaOT.getTxtPreimpresion().setText(Integer.toString(Integer.valueOf(ot2.get(8))));
		nuevaOT.getTxtPreimpresion().setEditable(false);
		nuevaOT.getTxtAncho().setText(ot2.get(9).toString());
		nuevaOT.getTxtAncho().setEditable(false);
		nuevaOT.getTxtAlto().setText(ot2.get(10).toString());
		nuevaOT.getTxtAlto().setEditable(false);
		nuevaOT.getChbApaisado().getModel().setSelected(Metodos.esApaisadaB(ot2.get(11).toString()));
		nuevaOT.getChbApaisado().setEnabled(false);
		nuevaOT.getEstado().getModel().setSelectedItem((String)ot2.get(12));
		nuevaOT.getCliente().setSelectedItem(ot2.get(2).toString());
		nuevaOT.getCliente().setEnabled(false);
		nuevaOT.getBtnLimpiarCampos().setEnabled(false);
		
		//Muestra los datos de la tabla Elemento
		Integer id_OT=Metodos.FacturaAEntero(ideOT);
		Integer cantFilas = Elemento.cantidadFilas(id_OT);
		ArrayList<String> elemento = Elemento.nombreDeElemento(id_OT);
		ArrayList<Integer> cantidad = Elemento.cantidadDeElemento(id_OT);
		DefaultTableModel temp = (DefaultTableModel) nuevaOT.getTablaElementos().getModel();
		nuevaOT.getTablaElementos().setEnabled(false);
		Object nuevaFilaElemento[]= {"",""};
		for (int i = 0; i < cantFilas; i++) 
		{
			temp.addRow(nuevaFilaElemento);
			temp.setValueAt(elemento.get(i), i, 0);
			temp.setValueAt(cantidad.get(i), i, 1);	
		}
		nuevaOT.getBtnAgregarFila().setEnabled(false);
		nuevaOT.getBtnBorrarFila().setEnabled(false);
		nuevaOT.getBtnAlmacenar().setEnabled(false);
		nuevaOT.getTablaElementos().setEnabled(false);
		
		//Muestra los datos de la tabla Materiales
		
		ArrayList<Integer> gramaje = Materiales.getGramaje(id_OT);
		ArrayList<Integer> poses_x_pliego = Materiales.getPoses_x_pliego(id_OT);
		ArrayList<Integer> pliegos_netos = Materiales.getPliegos_netos(id_OT);
		ArrayList<Integer> pliegos_en_demasia = Materiales.getPliegos_en_demasia(id_OT);
		ArrayList<Integer> hojas = Materiales.getHojas(id_OT);
		ArrayList<Integer> id_calidad = Materiales.getID_Calidad(id_OT);
		ArrayList<Integer> id_variante = Materiales.getID_Variante(id_OT);
		ArrayList<Integer> id_formato_papel = Materiales.getId_formato_papel(id_OT);
		ArrayList<Integer> pliegos_x_hoja = Materiales.getPliegos_x_Hojas(id_OT);
		
		DefaultTableModel tempMat = (DefaultTableModel) nuevaOT.getTablaMateriales().getModel();
		Object nuevaFilaMateriales[]= {"",0, 0,"", "", "", 0, 0, 0, 0, 0};
		cantFilas=Materiales.getID_Materiales(id_OT).size();
		for (int i = 0; i < cantFilas; i++) 
		{
			tempMat.addRow(nuevaFilaMateriales);
			tempMat.setValueAt(elemento.get(i), i, 0);
			tempMat.setValueAt(cantidad.get(i), i, 1);	
			tempMat.setValueAt(gramaje.get(i), i, 2);	
			tempMat.setValueAt((Formato_Papel.getTamanio(id_formato_papel.get(i))), i, 3);	
			tempMat.setValueAt(Variante.getNombre(id_variante.get(i)), i, 4);	
			tempMat.setValueAt(Calidad.getNombre(id_calidad.get(i)), i, 5);
			tempMat.setValueAt(pliegos_en_demasia.get(i), i, 6);	
			tempMat.setValueAt(poses_x_pliego.get(i), i, 7);	
			tempMat.setValueAt(pliegos_x_hoja.get(i), i, 8);	
			tempMat.setValueAt(hojas.get(i), i, 9);	
			tempMat.setValueAt(pliegos_netos.get(i), i, 10);
		}
		nuevaOT.getTablaMateriales().setEnabled(false);
			
		//Muestra los datos de la tabla Orden de ejecucion
		cantFilas = Procesos_x_OT.getCantidadFilas(id_OT);
		ArrayList<String> procesos = Procesos_x_OT.BuscarProc_x_OT(id_OT);
		ArrayList<Boolean> tercerizadas = Procesos_x_OT.getTercerizada(id_OT);
		ArrayList<Integer> proveedor = Procesos_x_OT.getProveedor(id_OT);
		ArrayList<String> observaciones = Procesos_x_OT.getObservaciones(id_OT);
		ArrayList<Boolean> cumplida = Procesos_x_OT.getCumplida(id_OT);
		
		//permite que la columna cumplida sea editable
		nuevaOT.getTablaOrdenEjecucion().setModel(new DefaultTableModel
		(
			new Object[][] {},
			new String[] {"Proceso", "Tercerizada", "Proveedor", "Observaciones", "Cumplida"}) 
			{
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] 
				{
						String.class, Boolean.class, String.class, String.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) 
				{
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] 
				{
					false, false,false, false, true
				};
				public boolean isCellEditable(int row, int column) 
				{
					return columnEditables[column];
				}
			}
		);
				
		DefaultTableModel tempOE = (DefaultTableModel) nuevaOT.getTablaOrdenEjecucion().getModel();
		Object nuevaFilaOrdenEjecucion[]= {"",false, "","", false};

		for (int i = 0; i < cantFilas; i++) 
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

