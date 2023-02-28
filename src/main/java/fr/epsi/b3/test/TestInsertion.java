package fr.epsi.b3.test;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.FournisseurDaoJdbc;

import java.sql.SQLException;

public class TestInsertion {
    private FournisseurDaoJdbc fournisseurJDBCDAO;
    public TestInsertion() {
        this.fournisseurJDBCDAO = new FournisseurDaoJdbc();
    }
    public void Insert(Fournisseur fournisseur) throws SQLException {
        this.fournisseurJDBCDAO.add(fournisseur);
    }
}
