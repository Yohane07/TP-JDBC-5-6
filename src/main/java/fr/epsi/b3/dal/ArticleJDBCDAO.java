package fr.epsi.b3.dal;

import java.util.ResourceBundle;

public class ArticleJDBCDAO {
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PWD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle( "db" );
        DB_URL = bundle.getString( "db.url" );
        DB_USER = bundle.getString( "db.login" );
        DB_PWD = bundle.getString( "db.password" );
    }

}
