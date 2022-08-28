package PackageModele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelePlace {
    private Modele connection = new Modele();

    public String[] getIdFalse(String nAvion) throws SQLException {
        String sql = "SELECT n_place FROM place WHERE n_avion = "+nAvion+" AND occupation = false";
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        resultat.beforeFirst();
        String[] data = new String[nombreLigne];
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("n_place");
            i++;
        }
        return data;
    }

    public String[] getAllId(String nAvion) throws SQLException {
        String sql = "SELECT n_place FROM place WHERE n_avion = "+nAvion;
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        resultat.beforeFirst();
        String[] data = new String[nombreLigne];
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("n_place");
            i++;
        }
        return data;
    }

    public void Ajout(String nAvion) throws SQLException {
        ModeleAvion modeleAvion = new ModeleAvion();
        String occupation = ", "+false;
        int nbPlaces = modeleAvion.getNbPlaces(nAvion);
        int i = 0;
        while (i<nbPlaces) {
            String sql = "INSERT INTO place VALUES (NULL, " + nAvion + occupation +")";
            this.connection.Modification(sql);
            i++;
        }
    }

    public void Update(String nPlace, String occupation) throws SQLException {
        String sql = "UPDATE  place SET occupation = " + occupation + " WHERE n_place = " + nPlace;
        this.connection.Modification(sql);
    }

    public String NombrePlace(String nAvion, String occupation) throws SQLException {
        String sql = "SELECT COUNT(n_place) AS nb_place_occupation FROM place WHERE n_avion = "+nAvion +" AND occupation = "+occupation;
        ResultSet resultat = this.connection.Requete(sql);
        resultat.first();
        String nbPlaceDisponible = resultat.getString("nb_place_occupation");
        return nbPlaceDisponible;
    }

    public void Supprimer(String n_avion) throws SQLException {
        String sql = "DELETE FROM place WHERE n_avion = "+n_avion;
        this.connection.Modification(sql);
    }

    public boolean getOccupation(String nAvion, String nPlace) throws SQLException {
        String sql = "SELECT occupation FROM place WHERE n_avion = "+nAvion +" AND n_place = "+nPlace;
        ResultSet resultat = this.connection.Requete(sql);
        resultat.first();
        boolean occupation = resultat.getBoolean("occupation");
        return occupation;
    }

}
