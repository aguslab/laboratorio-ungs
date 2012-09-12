
public class TaTeTi
{
	public TaTeTi()
	{
		tablero = new char[3][3];
		turno = 'x';
		ganador = ' ';

		for(int i=0; i<3; ++i)
			for(int j=0; j<3; ++j)
				tablero[i][j] = ' ';
	}
	
	public void Mostrar()
	{
		for(int i=0; i<3; ++i)
		{
			for(int j=0; j<3; ++j)
				System.out.print((tablero[i][j] == ' ' ? '·' : tablero[i][j]) + "  ");
			
			System.out.println();
		}

		System.out.println();
	}
	
	public char ProximoTurno()
	{
		return turno;
	}
	
	public void Jugar(int fila, int columna)
	{
		if( tablero[fila][columna] != ' ' )
			throw new RuntimeException("Error! Se intentó jugar en una posición no vacía. ");
		
		if( Terminado() == true )
			throw new RuntimeException("Error! Se intentó jugar pero el juego ya terminó. ");
		
		tablero[fila][columna] = turno;
		turno = turno == 'x' ? 'o' : 'x';
	}
	
	public boolean Terminado()
	{
		for(int i=0; i<3; ++i) if( tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][2] != ' ' )
		{
			ganador = tablero[i][0];
			return true;
		}
		
		for(int j=0; j<3; ++j) if( tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j] && tablero[2][j] != ' ' )
		{
			ganador = tablero[0][j];
			return true;
		}
		
		if( tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[2][2] != ' ' )
		{
			ganador = tablero[0][0];
			return true;
		}

		if( tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[2][0] != ' ' )
		{
			ganador = tablero[0][2];
			return true;
		}
		
		int cont = 0;
		for(int i=0; i<3; ++i)
			for(int j=0; j<3; ++j)
				if( tablero[i][j] != ' ')
					++cont;
		
		if( cont == 9 )
		{
			ganador = ' ';
			return true;
		}

		return false;
	}
	
	public char Ganador()
	{
		if( Terminado() == false )
			throw new RuntimeException( "Error! Se consultó el ganador de un juego que no está terminado." );
		
		return ganador;
	}
	
	private char tablero[][];
	private char turno;
	private char ganador;
}
