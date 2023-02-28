package fr.epsi.b3.dal;

import fr.epsi.b3.bo.Fournisseur;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class FournisseurDaoJdbc implements FournisseurDAO{
    private static final String INSERT_QUERY = "INSERT INTO fournisseur(NOM) VALUES(?)";
    private static final String SELECT_QUERY = "SELECT * FROM fournisseur";
    private static final String UPDATE_QUERY = "UPDATE fournisseur SET NOM=? WHERE ID=?";
    private static final String DELETE_ID_QUERY = "DELETE FROM fournisseur WHERE ID=?";
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PWD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle( "db" );
        DB_URL = bundle.getString( "db.url" );
        DB_USER = bundle.getString( "db.login" );
        DB_PWD = bundle.getString( "db.password" );
    }

    @Override
    public List<Fournisseur> findAll() throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();

        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);
              ResultSet resultSet = prepareStatement.executeQuery()) {

            while ( resultSet.next() ) {
                int id = resultSet.getInt( "ID" );
                String nom = resultSet.getString( "NOM" );
                Fournisseur fournisseur = new Fournisseur( id, nom );
                fournisseurs.add( fournisseur );
            }
        }


        return fournisseurs;
    }
    @Override
    public int add(Fournisseur fournisseur) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, fournisseur.getNom());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            int lastInsertedId = 0;
            if(resultSet.next())
            {
                lastInsertedId = resultSet.getInt(1);
            }
            return lastInsertedId;
        }
    }
    @Override
    public void update(Fournisseur oldFournisseur, Fournisseur newFournisseur) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement.setString(1, newFournisseur.getNom());
            prepareStatement.setString(2, Integer.toString(oldFournisseur.getId()));
            prepareStatement.executeUpdate();
        }
    }

    public void deleteById(int fournisseurId) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ID_QUERY)) {
            prepareStatement.setString(1, String.valueOf(fournisseurId));
            prepareStatement.executeUpdate();
        }
    }
}

