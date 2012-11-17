package Modelo;
import java.util.ArrayList;


public class AdministradorAlquileres {

	
	public ArrayList<Inmueble> inmueblesSinAlquileres(ArrayList<Inmueble> inmuebles,ArrayList<Alquiler> alquileres, String fecha){
		ArrayList<Inmueble> inmueblesSinAlquileres=new ArrayList<Inmueble>();
		
		for(int i=0;i<inmuebles.size();i++){
			Boolean noTieneAlquileresPautados=false;
			for(int j=0; j < alquileres.size(); j++){
				if(alquileres.get(j).getInmueble().getId_Inmueble().equals(inmuebles.get(i).getId_Inmueble())){					
					if( EsFechaPrimeraMenorQueSegunda(fecha, alquileres.get(j).getFechaFinalizacion()) == false  ){
						noTieneAlquileresPautados=true;
					}else{
						j=alquileres.size()-1;
					}
					if( j == alquileres.size()-1 && noTieneAlquileresPautados){
						inmueblesSinAlquileres.add(inmuebles.get(i));
					}
				}		
			}
		}
		return inmueblesSinAlquileres;
	}
	
	
	

	// se toma como dia de cobro la fecha de firma
	public Double calcularAlquileres(ArrayList<Alquiler> alquileres, String F_Rango_inicio, String F_Rango_fin){
	Double acumulador=0.0;
		
		if(EsFechaPrimeraMenorQueSegunda(F_Rango_fin, F_Rango_inicio)){
			return acumulador;
		}
	
		for (int i = 0; i < alquileres.size(); i++) {
			String FechaFirmaAlq=alquileres.get(i).getFechaFirma();
			String FechaInicioAlq=alquileres.get(i).getFechaInicio();
			String FechaFinalizacionAlq=alquileres.get(i).getFechaFinalizacion();
			//si la fecha de inicio es menor que la fecha de fin del rango pasado como parametro y
			//la fecha de firma es menor que la fecha de finalizacion
			//nota:la fecha de inicio del alquiler se va incrementando de a "un mes"
			if((EsFechaPrimeraMenorQueSegunda(FechaFirmaAlq,FechaInicioAlq) || FechaFirmaAlq.equals(FechaInicioAlq)) && (EsFechaPrimeraMenorQueSegunda(FechaInicioAlq, FechaFinalizacionAlq) || FechaInicioAlq.equals(FechaFinalizacionAlq))){
				
				while( EsFechaPrimeraMenorQueSegunda(FechaInicioAlq, F_Rango_fin) && EsFechaPrimeraMenorQueSegunda(FechaInicioAlq, FechaFinalizacionAlq)){
					if(EsFechaPrimeraMenorQueSegunda(FechaInicioAlq, F_Rango_inicio)){
						FechaInicioAlq=mesProximo(FechaInicioAlq);
					}else if(EsFechaPrimeraMenorQueSegunda(F_Rango_inicio, FechaInicioAlq) || F_Rango_inicio.equals(FechaInicioAlq)){
						acumulador+=alquileres.get(i).getMontoMensual();
					}
					FechaInicioAlq=mesProximo(FechaInicioAlq);
				}
				
			}
		}
		
		return acumulador;
	}
	
	
	public String mesProximo(String fechaFirma) {
		
		String[] ReParseIntA;
		ReParseIntA = fechaFirma.split("/");
		 Integer anioFecha = Integer.parseInt(ReParseIntA[2]);
		 Integer mesFecha  = Integer.parseInt(ReParseIntA[1]);
		 Integer diaFecha  = Integer.parseInt(ReParseIntA[0]);
		 
		 if(mesFecha==12){
			 mesFecha=01;
			 anioFecha++;
		 }else{
			 mesFecha++;
		 }
		 return diaFecha+"/"+mesFecha+"/"+anioFecha;
	}





	public boolean EsFechaPrimeraMenorQueSegunda(String fecha,
			String fechaFinalizacion) {
		String[] ReParseIntA;
		 ReParseIntA = fecha.split("/");
		 Integer anioFecha = Integer.parseInt(ReParseIntA[2]);
		 Integer mesFecha  = Integer.parseInt(ReParseIntA[1]);
		 Integer diaFecha  = Integer.parseInt(ReParseIntA[0]);
		 String[] ReParseIntP;
		 ReParseIntP = fechaFinalizacion.split("/");
		 Integer anioFinalizacion = Integer.parseInt(ReParseIntP[2]);
		 Integer mesFinalizacion = Integer.parseInt(ReParseIntP[1]);
		 Integer diaFinalizacion = Integer.parseInt(ReParseIntP[0]);


		if (anioFinalizacion < anioFecha) 
		{
			return false;
		} 
		else if (anioFinalizacion.equals(anioFecha)) 
		{
			if (mesFinalizacion < mesFecha) 
			{
				return false;
			} 
			else if (mesFinalizacion.equals(mesFecha)) 
			{
				if (diaFinalizacion <= diaFecha) 
				{
					return false;
				} 
				else 
				{
					return true;
				}
			} 
			else 
			{
				return true;
			}
		}
		else 
		{
			return true;
		}
	}
	
	
}
