package vista_Controlador;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class Splash extends JWindow implements Config
{

	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

	public Splash () 
	{

		JLabel lbImage    = new JLabel (new ImageIcon ("Imagenes/Splash.PNG"));
		JLabel lblVersion = new JLabel (qVERSION);
		JLabel lblDerechos = new JLabel("<html>Copyright ©2012 De Napoli, Godoy, Jiménez y asociados. Todos los derechos reservados. MAGESTI es propiedad intelectual de los autores mencionados. MySQL y Java son marcas registradas\n de Oracle Corporation.</html>");
		Color cl = new Color (0, 0, 0);
		lbImage.setBorder (new LineBorder (cl, 0));
		lblDerechos.setFont(new Font("Arial", Font.PLAIN, 10));
		lblDerechos.setBounds(10, 237, 390, 60);
		lblVersion.setFont(new Font("Arial", Font.PLAIN, 24));
		lblVersion.setBounds(350, 10, 150, 60);

	    getContentPane().add(lblDerechos);
	    getContentPane().add(lblVersion);
		getContentPane().add (lbImage, BorderLayout.CENTER);
		pack();

		setSize (getSize().width, getSize().height);
		setLocation (pantalla.width / 2 - getWidth() / 2, pantalla.height / 2 - getHeight() / 2);

		//this.setVisible(true);
		
		/*
		try 
		{
			Thread.sleep(4000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		*/
		new Magesti();

		toFront();
		dispose ();
		setVisible (false);

	}

	public static void main (String args[]) 
	{
		new Splash ();
		
	}

}
