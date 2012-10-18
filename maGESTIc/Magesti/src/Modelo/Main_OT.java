package Modelo;

import java.math.BigInteger;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import vista_Controlador.Metodos;


public class Main_OT {

		public static void main(String[] args) throws SQLException {
	
		
			
			System.out.println(Metodos.getDateTimeActual());
			
			//System.out.println(Cliente.updateDatosContactoCliente("1", "asdasdas", "s22222222", "2222222", "dadsdasdas"));
			
			/*		
			Detalle d= new Detalle(1, 123, "saa", 1, 2, 3, 12, 12.3, "KG", 134.12,false);
			System.out.println(d.Alta());
			*/
			
			/*
			String[] a=Orden_Trabajo.getId_nom_OT();
			for(int i=0;i<a.length;i++){
				System.out.println(a[i]);
			}
			*/
			
			
			
			/*//Solicitud de compra
			Solicitud_compra sc= new Solicitud_compra("2012-10-12", 2, "javier", 1, true, null, "2012-10-13", "M", 13.2, 13.2, 14.1, 165.2);
			System.out.println(sc.Alta());
			System.out.println(Solicitud_compra.getUltId_SC());
			*/			
			
			/*
			ArrayList<Integer> id_mat=Materiales.getID_Materiales(21);
			for(int i=0;i<id_mat.size();i++){
				System.out.println("pos:"+i+" "+id_mat.get(i));
			}
			
			System.out.println("hojas");System.out.println();
			
			*/
			
			/*ArrayList<Integer> hojas=Materiales.getHojas(21);
			for(int i=0;i<hojas.size();i++){
				System.out.println("pos:"+i+" "+hojas.get(i));
			}*/
			
			
			
			
			
		/*	
			//System.out.println(Elemento.cantidadFilas(2));
			ArrayList<String> a= Elemento.nombreDeElemento(8);
			ArrayList<Integer> b= Elemento.cantidadDeElemento(8);
			if(a.size()==b.size()){
				System.out.println("IGUALESSSSSSSSSS");
			}
			for(int i=0;i<a.size();i++){
				System.out.println("pos:"+i+" "+a.get(i));
				System.out.println("pos:"+i+" "+b.get(i));
			}
			*/
			
	/*		// ORDEN DE TRABAJO
		Orden_Trabajo ot1= new Orden_Trabajo("producto blabla", 1, "2012-9-29", "2012-10-29", "Javier", "esta es una descripcion",13, 9,12.2,34.7,false,"pendiente",null);
		System.out.println(ot1.Alta());
		ArrayList<Orden_Trabajo> lot=ot1.Buscar();
		lot.get(0).mostrarDatos();
		lot.get(1).mostrarDatos();
	*/
		
	
		/*
		 * 		VARIANTE
		Variante v1= new Variante("hola");
	System.out.println(v1.Alta());
	ArrayList<Variante> vr=v1.Buscar();
	*/
	
	/*	PROCESOS
	Proceso p= new Proceso("nombreProc");
	System.out.println(p.Alta());
	ArrayList<Proceso> lproc=p.Buscar();

	*/	
		
	/*	CALIDAD
	Calidad cal= new Calidad("calidad1");
	System.out.println(cal.Alta());
	ArrayList<Calidad> lcal=cal.Buscar();
	*/
		
	
	
		/*	//ELEMENTO
		Elemento elem= new Elemento(1, "Revista", 12);
		System.out.println(elem.Alta());
		ArrayList<Elemento> lelem=elem.Buscar();
*/
	

		
	/*	FORMATO_PAPEL
		
		Formato_Papel for_p= new Formato_Papel(12, 12);
		System.out.println(for_p.Alta());
		ArrayList<Formato_Papel> lcal=for_p.Buscar();	
		
	*/		
		
		/*
	Cliente cli= new Cliente("persona", new BigInteger("12345678901") , "condiva", "defensa 1919", "12343567892", "aksdhask1@gmail.com", "javier", "1231231234", "asdjh@gmail.com", "direccion2 1234");
	System.out.println(cli.Alta());
	ArrayList<Cliente> lcli=cli.Buscar();
	*/
		
	/* Proceso_x_OT
		Procesos_x_OT pro_ot= new Procesos_x_OT(3, 4, 1,true, "probando");
		System.out.println(pro_ot.Alta());
		ArrayList<Procesos_x_OT> lp_ot=pro_ot.Buscar();
		ArrayList<String> lpro_x_OT=Procesos_x_OT.BuscarProc_x_OT(2);
		Integer a= lpro_x_OT.size();
		Integer i=0;
		while(i<a){
			System.out.println(lpro_x_OT.get(i));
			i++;
		}
	*/	
		
		
	/* Tipo_Producto	
		Tipo_producto tp= new Tipo_producto("asd", 1);
		System.out.println(tp.Alta());
		ArrayList<Tipo_producto> ltp=tp.Buscar();

	*/
			
		//System.out.println(Orden_Trabajo.getUltOT());
		
	/*
		Materiales m= new Materiales(1,12, 43, 12, 4, 1, 12, 1, 2, 2);
		System.out.println(m.Alta());
		ArrayList<Materiales> l_m=m.Buscar();
	*/	

		 }
		
	}





