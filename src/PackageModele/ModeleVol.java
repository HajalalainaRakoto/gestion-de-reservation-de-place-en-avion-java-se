package PackageModele;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ModeleVol {
    private Modele connection = new Modele();

    public void Ajout(String ville_depart, String ville_arrivee, String frais) throws SQLException {
        ville_depart = "'"+ville_depart+"', ";
        ville_arrivee = "'"+ville_arrivee+"', ";
        frais = "'"+frais+"'";
        String sql = "INSERT INTO vol VALUES (NULL," + ville_depart + ville_arrivee + frais + ")";
        this.connection.Modification(sql);
    }

    public void Supprimer(String n_vol) throws SQLException {
        String sql = "DELETE FROM vol WHERE n_vol = "+n_vol;
        this.connection.Modification(sql);
    }

    public void Update(String ville_depart, String ville_arrivee, String frais, String n_vol) throws SQLException {
        ville_depart = "'"+ville_depart+"', ";
        ville_arrivee = "'"+ville_arrivee+"', ";
        String sql = "UPDATE  vol SET ville_depart = " + ville_depart + "ville_arrivee = " + ville_arrivee;
        sql += "frais = " + frais + " WHERE n_vol = " + n_vol;
        this.connection.Modification(sql);

    }

    public String[][] getData() throws SQLException {
        String sql = "SELECT * FROM vol";
        ResultSet resultat = this.connection.Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = this.connection.nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            String nVol = resultat.getString("n_vol");
            String villeDepart = resultat.getString("ville_depart");
            String villeArrivee = resultat.getString("ville_arrivee");
            String frais = resultat.getString("frais");
            data[j][0] = nVol;
            data[j][1] = villeDepart;
            data[j][2] = villeArrivee;
            data[j][3] = frais;
            j++;
        }

        return data;
    }

    public String[] getAllID() throws SQLException {
        String sql = "SELECT n_vol FROM vol";
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        resultat.beforeFirst();
        String[] data = new String[nombreLigne];
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("n_vol");
            i++;
        }
        return data;
    }

}
