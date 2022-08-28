package PackageModele;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ModeleAvion {
    private Modele connection = new Modele();

    public void Ajout(String design, String nbPlaces, String nVol) throws SQLException {
        ModelePlace modelePlace = new ModelePlace();
        design = "'"+design+"'";
        nbPlaces = ", "+nbPlaces+", ";
        String sql = "INSERT INTO avion VALUES (NULL, " + nVol + nbPlaces + design + ")";
        this.connection.Modification(sql);
        int id = getId();
        String idEnLettre = id+"";
        modelePlace.Ajout(idEnLettre);
    }

    public void Supprimer(String n_avion, String n_vol) throws SQLException {
        String sql = "DELETE FROM avion WHERE n_avion = "+n_avion+" AND n_vol = "+n_vol;
        this.connection.Modification(sql);
    }

    public void Update(String design, String nbPlaces, String nVol, String nAvion) throws SQLException {
        ModelePlace modelePlace = new ModelePlace();
        design = "'"+design+"', ";
        nbPlaces = "'"+nbPlaces+"', ";
        String sql = "UPDATE  avion SET design = " + design + "nb_places = " + nbPlaces;
        sql += "n_vol = " + nVol + " WHERE n_avion = " + nAvion;
        this.connection.Modification(sql);
        modelePlace.Supprimer(nAvion);
        modelePlace.Ajout(nAvion);
    }

    public String[][] getData() throws SQLException {
        String sql = "SELECT * FROM avion";
        ResultSet resultat = this.connection.Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = this.connection.nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            int nVol = resultat.getInt("n_vol");
            String nbPlaces = resultat.getString("nb_places");
            String design = resultat.getString("design");
            int nAvion = resultat.getInt("n_avion");
            data[j][1] = nVol + "";
            data[j][2] = nbPlaces;
            data[j][3] = design;
            data[j][0] = nAvion + "";
            j++;
        }

        return data;
    }

    public String[] getAllID() throws SQLException {
        String sql = "SELECT n_avion FROM avion";
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        resultat.beforeFirst();
        String[] data = new String[nombreLigne];
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("n_avion");
            i++;
        }
        return data;
    }

    public int getNbPlaces(String n_avion) throws SQLException {
        String sql = "SELECT nb_places FROM avion WHERE n_avion = "+n_avion;
        ResultSet resultat = this.connection.Requete(sql);
        resultat.first();
        int res = resultat.getInt("nb_places");
        return res;
    }

    public int getId() throws SQLException {
        String sql = "SELECT * FROM avion";
        ResultSet resultat = this.connection.Requete(sql);
        resultat.last();
        int res = resultat.getInt("n_avion");
        return res;
    }

}
