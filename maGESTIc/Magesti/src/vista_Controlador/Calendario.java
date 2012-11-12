package vista_Controlador;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.*;
import Modelo.Orden_Trabajo;

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
	private JLabel lblMes = new JLabel("   Seleccione el Mes: ");
	ArrayList<String> ot2 = new ArrayList<String>();
	private JButton btnMatriz[]= new JButton[31];
	
	private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
	private Dimension dimBarra = null; 

	String dias[] = 
  	{
		"    DOMINGO", 
		"       LUNES", 
		"      MARTES", 
		"   MIÉRCOLES",
		"      JUEVES",
		"     VIERNES", 
		"     SÁBADO"
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
	DiaDeLaSemana=0,
	totalDiasDelMes=0,
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
		setIconifiable(false);
		setClosable(false);			
		aplylisteners();		
		startup();
	}
	
	
	
	public void ocultarBarraTitulo()
	{
	Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
	dimBarra = Barra.getPreferredSize();
	Barra.setSize(0,0);
	Barra.setPreferredSize(new Dimension(0,0));
	repaint();
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
		ocultarBarraTitulo();
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
			pan1.setBackground(new Color(83,130,161));
			pan1.setLayout(new GridLayout(0, 4));
			lblAnio.setForeground(new Color(0, 0, 0));
			pan1.add(lblAnio);
			pan1.add(chAnio);
			pan1.add(lblMes);
			pan1.add(chMes);
			
	}

	public void panel2()
	{
		pan2.setLayout(new GridLayout(1,7));
		pan2.setForeground(Color.BLACK);
		pan2.setBackground(new Color(231,111,0));
		pan2.removeAll();
		
		 for(int i=0; i<7;i++)
		{
			pan2.add(new JLabel(dias[i])).setFont(new Font("Arial", Font.PLAIN, 9));
		}
	}
	public void panel3()
	{
		pan3.removeAll(); 
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
		
		btnMatriz[Integer.parseInt(diaHoy)-1].setFocusPainted(true);
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
					OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
					getDesktopPane().add(nuevaOT);
					nuevaOT.show ();
					new BusquedaOrdenTrabajo(nuevaOT, Integer.parseInt(ot.get(i)));
					diaSeleccionado=1;
				}
	                        
			}
			else
			{
				msg="No existen Ordenes de Trabajo para la fecha seleccionada";
				JOptionPane.showInternalMessageDialog(this, msg, "ORDEN TRABAJO", JOptionPane.PLAIN_MESSAGE);
			}
		}
	

}

