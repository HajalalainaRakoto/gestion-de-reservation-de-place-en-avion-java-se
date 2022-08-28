package PackageModele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Modele {

    private Connection connection;
    private Statement state;
    private ResultSet resultat;

    public Modele() {
        String url = "jdbc:mysql://localhost/gestion_de_reservation_de_place_en_avion";
        String user = "root";
        String password = "";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.state = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Modification(String sql) throws SQLException {
        this.state.executeUpdate(sql);
    }

    public ResultSet Requete(String sql) throws SQLException {
        this.resultat = this.state.executeQuery(sql);
        return resultat;
    }

    public int nombreLigne(ResultSet resultat) throws SQLException{
        int nombreLigne = 0;

        while (resultat.next()) {
            nombreLigne++;
        }
        resultat.beforeFirst();

        return nombreLigne;
    }

    public String[][] getAllData(String nAvion) throws SQLException {
        String sql = "SELECT nom_voyageur, place.n_place, vol.n_vol, date_reservation FROM avion, vol, reservation, place ";
        sql += "WHERE avion.n_vol = vol.n_vol AND reservation.n_avion = avion.n_avion AND reservation.n_place = place.n_place ";
        sql+= "AND avion.n_avion = "+nAvion;
        ResultSet resultat = Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            String nomVoyageur = resultat.getString("nom_voyageur");
            String nPlace = resultat.getString("n_place");
            String nVol = resultat.getString("n_vol");
            String dateReservation = resultat.getString("date_reservation");
            data[j][0] = nomVoyageur;
            data[j][1] = nPlace;
            data[j][2] = nVol;
            data[j][3] = dateReservation;
            j++;
        }

        return data;
    }

    public String[][] recherche(String mois, String annee) throws SQLException {
        String sql = "SELECT avion.n_avion, SUM(frais) AS somme_frais FROM avion, vol, reservation WHERE ";
        sql += "avion.n_vol = vol.n_vol AND reservation.n_avion = avion.n_avion AND MONTHNAME(date_reservation) = '"+mois+"' AND YEAR(date_reservation) = "+annee;
        sql += " GROUP BY avion.n_avion";
        ResultSet resultat = Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            String nAvion = resultat.getString("n_avion");
            String nPlace = resultat.getString("somme_frais");
            data[j][0] = nAvion;
            data[j][1] = nPlace;
            j++;
        }

        return data;
    }

    public String[][] rechercheAnnee(String annee) throws SQLException {
        String sql = "SELECT avion.n_avion, SUM(frais) AS somme_frais FROM avion, vol, reservation WHERE ";
        sql += "avion.n_vol = vol.n_vol AND reservation.n_avion = avion.n_avion AND YEAR(date_reservation) = "+annee;
        sql += " GROUP BY avion.n_avion";
        ResultSet resultat = Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            String nAvion = resultat.getString("n_avion");
            String nPlace = resultat.getString("somme_frais");
            data[j][0] = nAvion;
            data[j][1] = nPlace;
            j++;
        }

        return data;
    }

}
