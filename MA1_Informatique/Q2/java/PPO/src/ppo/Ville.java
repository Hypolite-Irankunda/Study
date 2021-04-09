/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppo;

/**
 *
 * @author hypo
 */
public class Ville {

    private String nomVille;
    private String nomPays;
    private int nbreHabitants;
    
    
    public Ville() {
    }

    
    public Ville(String nomVille, String nomPays, int nbreHabitants) {
        this.nomVille = nomVille;
        this.nomPays = nomPays;
        this.nbreHabitants = nbreHabitants;
    }

    
    
    
    
    
    
    public int getNbreHabitants() {
        return nbreHabitants;
    }

    public String getNomPays() {
        return nomPays;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNbreHabitants(int nbreHabitants) {
        this.nbreHabitants = nbreHabitants;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }
    
    
   
    
    
    
}
