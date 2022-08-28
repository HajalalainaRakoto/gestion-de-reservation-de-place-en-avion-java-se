package PackageModele;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ModeleReservation {
    private Modele connection = new Modele();
    private ModelePlace modelePlace = new ModelePlace();

    public void Ajout(String nAvion, String nPlace, String dateReservation, String nomVoyageur) throws SQLException {
        dateReservation = ", '"+dateReservation+"', ";
        nomVoyageur = "'"+nomVoyageur+"'";
        nAvion = nAvion+", ";
        String sql = "INSERT INTO reservation VALUES (NULL, " + nAvion + nPlace + dateReservation + nomVoyageur+")";
        this.connection.Modification(sql);
        modelePlace.Update(nPlace, "true");
    }

    public void Supprimer(String nReservation, String nAvion, String nPlace) throws SQLException {
        String sql = "DELETE FROM reservation WHERE n_avion = "+nAvion+" AND n_place = "+nPlace+" AND n_reservation = "+nReservation;
        this.connection.Modification(sql);
        modelePlace.Update(nPlace, "false");
    }

    public void Update(String nAvion, String nPlace, String dateReservation, String nomVoyageur, String nReservation) throws SQLException {
        nAvion = nAvion+", ";
        nPlace = nPlace+", ";
        dateReservation = "'"+dateReservation+"', ";
        nomVoyageur = "'"+nomVoyageur+"'";
        String sql = "UPDATE  reservation SET n_avion = " + nAvion + "n_place = " + nPlace;
        sql += "date_reservation = " + dateReservation +"nom_voyageur = "+nomVoyageur+ " WHERE n_reservation = " + nReservation;
        this.connection.Modification(sql);

    }

    public String[][] getData() throws SQLException {
        String sql = "SELECT * FROM reservation";
        ResultSet resultat = this.connection.Requete(sql);
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        int nombreColonne = resultatMeta.getColumnCount();
        int nombreLigne = this.connection.nombreLigne(resultat);

        String[][] data = new String[nombreLigne][nombreColonne];

        int j = 0;
        while (resultat.next()) {
            String nReservation = resultat.getString("n_reservation");
            String nAvion = resultat.getString("n_avion");
            String nPlace = resultat.getString("n_place");
            String dateReservation = resultat.getString("date_reservation");
            String nomVoyageur = resultat.getString("nom_voyageur");
            data[j][0] = nReservation;
            data[j][1] = nAvion;
            data[j][2] = nPlace;
            data[j][3] = dateReservation;
            data[j][4] = nomVoyageur;
            j++;
        }

        return data;
    }

    public String[] getYear() throws SQLException {
        String sql = "SELECT DISTINCT(YEAR(date_reservation)) AS annee FROM reservation";
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        String[] data = new String[nombreLigne];
        resultat.beforeFirst();
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("annee");
            i++;
        }
        return data;
    }

    public String[] getMonth() throws SQLException {
        String sql = "SELECT DISTINCT(MONTHNAME(date_reservation)) AS mois FROM reservation";
        ResultSet resultat = this.connection.Requete(sql);
        int nombreLigne = this.connection.nombreLigne(resultat);
        String[] data = new String[nombreLigne];
        resultat.beforeFirst();
        int i = 0;
        while (resultat.next()) {
            data[i] = resultat.getString("mois");
            i++;
        }
        return data;
    }
}
