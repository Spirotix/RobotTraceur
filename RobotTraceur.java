import iut.algo.*;

/** Robot
 * @author Théo Harel
 * @version 1 du 03/11/2023
 */




public class RobotTraceur_Theo_Harel
{

	public static void main(String[] arg)
	{
		/*-------------*/
		/* Données     */
		/*-------------*/

		final int TAILLE_CERCLE = 10;

		int cordx       ;
		int cordy       ;
		char direction  ;
		String deplacement;

		int depx;
		int depy;
		
		Boolean trait;
		
		/*--------------*/
		/* Instructions */
		/*              */

		ControleGraphique ct;
		
		ct = new ControleGraphique();
		ct.setTaille( 600,500 );
		
		cordx = 100;
		cordy = 100;
		depy = cordy;
		depx = cordx;
		
		direction = 'N';
		
		trait = true;
		
		//dessin du premier robot
		ct.prendreStylo (Couleur.VERT);
		ct.setTypeAction('M');
		ct.cercle( cordx, cordy, TAILLE_CERCLE );
		ct.ligne ( cordx  , cordy - 10 , cordx + 7 , cordy + 7);
		ct.ligne ( cordx  , cordy - 10 , cordx - 7 , cordy + 7);

		System.out.print ("[" + direction +" " + cordx + ":" + cordy + "]    ");
		System.out.print ("action : ");
		deplacement = Clavier.lireString();

		while (!deplacement.equals("FIN"))
		{
			depy = cordy;
			depx = cordx;
			if (Robot.estEntier(deplacement) && Integer.parseInt(deplacement) > 0 )
			{

				
				
				//donner les coordonner pour le trait
				switch (direction)
				{
					case('O'):
					cordx = cordx - Integer.parseInt(deplacement);
					break;

					case('N'):
					cordy = cordy - Integer.parseInt(deplacement);
					break;

					case('E'):
					cordx = cordx + Integer.parseInt(deplacement);
					break;

					case('S'):
					cordy = cordy + Integer.parseInt(deplacement);
					break;
				}

			}
			else
			{
				//tourner a droite
				if (deplacement.equals("D"))
				{
					
					switch (direction)
					{
						case('O'):
						direction = 'N';
						
						break;

						case('N'):
						direction = 'E';
						break;

						case('E'):
						direction = 'S';
						break;

						case('S'):
						direction = 'O';
						break;
					}
				}
				else
				{
					//Tourner à gauche
					if (deplacement.equals("G"))
					{
						
					
						switch (direction)
						{
							case('O'):
							direction = 'S';
							break;

							case('N'):
							direction = 'O';
							break;

							case('E'):
							direction = 'N';
							break;

							case('S'):
							direction = 'E';
							break;
						}
					}
					else
					{
						if (deplacement.equals("T"))
						{
							if (trait)
							{
								ct.setTypeAction('M');
								ct.prendreStylo (Couleur.ROUGE);
								trait = false;
							}
							else
							{
								ct.setTypeAction('M');
								ct.prendreStylo (Couleur.VERT);
								trait = true;
							}
						}
					}
				}
			}
			
			//dessin du trait
			
			if (trait)
			{
				ct.setTypeAction('F');
				ct.prendreStylo (Couleur.ROSE);
				ct.ligne ( depx, depy, cordx, cordy );
			}
			
			//dessin du robot
			ct.effacer('M');
			ct.setTypeAction('M');
			
			//couleur Robot
			if (trait)
			{
				ct.prendreStylo (Couleur.VERT);
			}
			else
			{
				ct.prendreStylo (Couleur.ROUGE);
			}
			
			ct.cercle( cordx, cordy, TAILLE_CERCLE );
			
			switch (direction)
			{
				case ('E'):
				ct.ligne ( cordx + 10 , cordy , cordx - 7 , cordy - 7);
				ct.ligne ( cordx + 10 , cordy , cordx - 7 , cordy + 7);
				break;
				
				case ('O'):
				ct.ligne ( cordx - 10 , cordy , cordx + 7 , cordy + 7);
				ct.ligne ( cordx - 10 , cordy , cordx + 7 , cordy - 7);
				break;
				
				case ('N'):
				ct.ligne ( cordx , cordy - 10 , cordx + 7 , cordy + 7);
				ct.ligne ( cordx , cordy - 10 , cordx - 7 , cordy + 7);
				break;
				
				case ('S'):
				ct.ligne ( cordx , cordy + 10 , cordx + 7 , cordy - 7);
				ct.ligne ( cordx , cordy + 10 , cordx - 7 , cordy - 7);
				break;
			}
			
			ct.majDessin();
			
			//afficher la commande
			System.out.print ("[" + direction + " " + cordx + ":" + cordy + "]    ");
			System.out.print ("action : ");
			deplacement = Clavier.lireString();
			


		}
	
	System.out.println("Veuillez fermer la fenêtre graphique pour quitter");

	}

	public static Boolean estEntier(String deplacement)
	{
		try
		{
			Integer.parseInt(deplacement);
			return true;
		} catch (NumberFormatException nfe)
		{
			return false;
		}
	}

}

