package fr.epsi.b3.dal;

import fr.epsi.b3.bo.Article;
import fr.epsi.b3.bo.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleDaoJdbc implements ArticleDAO {
    private static final String INSERT_QUERY = "INSERT INTO article(NOM, PRIX, ID_FOURNISSEUR) VALUES(?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT a.*, f.ID as ID_FOURNISSEUR, f.NOM as NAME_FOURNISSEUR FROM article a LEFT JOIN fournisseur f ON f.ID = a.ID_FOURNISSEUR";
    private static final String UPDATE_QUERY = "UPDATE article SET NOM=?, PRIX=?, ID_FOURNISSEUR=? WHERE ID=?";
    private static final String DELETE_ID_QUERY = "DELETE FROM article WHERE ID=?";
    private static final String GET_PRICE_AVERAGE = "SELECT AVG(PRIX) as MOYENNE FROM article";
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
    public void insert(Article article) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, article.getnom());
            preparedStatement.setDouble(2, article.getprix());
            preparedStatement.setInt(3, article.getFournisseur().getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Article> findAll() throws SQLException {
        List<Article> Articles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while ( resultSet.next() ) {
                int id = resultSet.getInt( "ID" );
                String nom = resultSet.getString( "NOM" );
                Double prix = resultSet.getDouble( "PRIX" );
                int fournisseurId = resultSet.getInt("FOURNISSEUR_ID");
                String fournisseurNom = resultSet.getString("FOURNISSEUR_NAME");
                Fournisseur fournisseur = new Fournisseur(fournisseurId, fournisseurNom);
                Article article = new Article( id, nom, prix, fournisseur);
                Articles.add( article );
            }
        }
        return Articles;
    }

    @Override
    public void update(Article exArticle, Article newArticle) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, newArticle.getnom());
            preparedStatement.setDouble(2, newArticle.getprix());
            preparedStatement.setInt(3, newArticle.getFournisseur().getId());
            preparedStatement.setString(4, Integer.toString(exArticle.getId()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(int articleId) throws SQLException {
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID_QUERY)) {
            preparedStatement.setString(1, String.valueOf(articleId));
            preparedStatement.executeUpdate();
        }
    }

    public Double getMoyenne() throws SQLException {
        double res = 0;
        try ( Connection connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PWD );
              PreparedStatement preparedStatement = connection.prepareStatement(GET_PRICE_AVERAGE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                res = resultSet.getDouble("MOYENNE");
            }
        }
        return res;
    }

}
