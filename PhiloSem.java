// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;
import java.util.ArrayList;
public class PhiloSem implements StrategiePhilo {

    /****************************************************************/

    private ArrayList<Semaphore> listeSemaphoresFourchettes;

    public PhiloSem (int nbPhilosophes) {
        // Approche 1
	listeSemaphoresFourchettes = new ArrayList<>();
        for(int i = 0; i < nbPhilosophes; i++){
            listeSemaphoresFourchettes.add(new Semaphore(1));
        }

    }

    /** Le philosophe no demande les fourchettes.
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException{
        //Approche 1
	int fourchetteGauche = Main.FourchetteGauche(no);
        int fourchetteDroite = Main.FourchetteDroite(no);

        listeSemaphoresFourchettes.get(fourchetteGauche).acquire();
        IHMPhilo.poser(fourchetteGauche, EtatFourchette.AssietteDroite);
        Thread.sleep(10000);
        listeSemaphoresFourchettes.get(fourchetteDroite).acquire();
        IHMPhilo.poser(fourchetteDroite, EtatFourchette.AssietteGauche);
	
	

    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no){
     	// Approche 1
	int fourchetteGauche = Main.FourchetteGauche(no);
        int fourchetteDroite = Main.FourchetteDroite(no);

        listeSemaphoresFourchettes.get(fourchetteGauche).release();
        IHMPhilo.poser(fourchetteGauche, EtatFourchette.Table);

        listeSemaphoresFourchettes.get(fourchetteDroite).release();
        IHMPhilo.poser(fourchetteDroite, EtatFourchette.Table);
	
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie ???";
    }

}

