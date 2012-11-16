package Modelo;
import java.util.ArrayList;


public class AdministradorAlquileres {

	
	public ArrayList<Inmueble> inmueblesSinAlquileres(ArrayList<Inmueble> inmuebles,ArrayList<Alquiler> alquileres, String fecha){
		ArrayList<Inmueble> inmueblesSinAlquileres=new ArrayList<Inmueble>();
		
		for(int i=0;i<inmuebles.size();i++){
			Boolean noTieneAlquileresPautados=false;
			for(int j=0; j < alquileres.size(); i++){
				if(alquileres.get(j).getInmueble().getId_Inmueble().equals(inmuebles.get(i).getId_Inmueble())){					
					if( EsFechaPrimeraMenorQueSegunda(fecha, alquileres.get(j).getFechaFinalizacion()) == false  ){
						noTieneAlquileresPautados=true;
					}else{
						j=alquileres.size()-1;
					}
					if( j == alquileres.size()-1 && noTieneAlquileresPautados){
						inmueblesSinAlquileres.add(inmuebles.get(j));
					}
				}		
			}
		}
		
		
		return inmueblesSinAlquileres;
	}
	
	
	

	// se toma como dia de cobro la fecha de firma
	public Double calcularAlquileres(ArrayList<Alquiler> alquileres, String F_inicio, String F_fin){
	Double acumulador=0.0;
		
		if(EsFechaPrimeraMenorQueSegunda(F_fin, F_inicio) || F_fin.equals(F_inicio)){
			return acumulador;
		}
	
		for (int i = 0; i < alquileres.size(); i++) {
			String FechaFirma=alquileres.get(i).getFechaFirma();
			String FechaFinalizacion=alquileres.get(i).getFechaFinalizacion();
			//si la fecha de firma es menor que la fecha de fin del rango pasado como parametro y
			//la fecha de firma es menor que la fecha de finalizacion
			//nota:la fecha de firma se va incrementando de a "un mes"
				while(EsFechaPrimeraMenorQueSegunda(FechaFirma, F_fin) && EsFechaPrimeraMenorQueSegunda(FechaFirma, FechaFinalizacion)){
					if(EsFechaPrimeraMenorQueSegunda(F_inicio, FechaFirma)){
						acumulador+=alquileres.get(i).getMontoMensual();
					}
					FechaFirma=mesProximo(FechaFirma);
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
				if (diaFinalizacion < diaFecha) 
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
