package fr.epsi.b3;


import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.ArticleDaoJdbc;
import fr.epsi.b3.dal.FournisseurDaoJdbc;
import fr.epsi.b3.test.*;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Main {
    private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PWD;

    static {
        System.out.println("Class  in loading");
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        DB_URL = bundle.getString("db.url");
        DB_LOGIN = bundle.getString("db.login");
        DB_PWD= bundle.getString("db.password");
    }

    public static void tpJdbc5() {
        try (Connection connection= DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
             Statement statement = connection.createStatement();) {
            TestInsertion testInsert = new TestInsertion();
            testInsert.Insert(new Fournisseur("Haribot c est pour la vie"));

            TestUpdate testUpdate= new TestUpdate();
            testUpdate.Update(new Fournisseur(1, "tu vaux beaucoup plus que tu ne crois"), new Fournisseur("mr Le Fournisseur"));

            TestFindAll testFindAll = new TestFindAll();
            List<Fournisseur> listeDesFournisseurs = testFindAll.FindAll();
            System.out.println(listeDesFournisseurs.size());

            TestDelete testDelete= new TestDelete();
            testDelete.Delete(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tpJdbc6() {
        try (Connection connection= DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
             Statement statement = connection.createStatement();) {

            TestJdbcArticles test = new TestJdbcArticles(new FournisseurDaoJdbc(), new ArticleDaoJdbc());
            test.insert();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
            tpJdbc5();
            tpJdbc6();

    }
}