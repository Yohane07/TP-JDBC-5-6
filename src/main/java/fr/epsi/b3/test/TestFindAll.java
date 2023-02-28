package fr.epsi.b3.test;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.FournisseurDaoJdbc;

import java.sql.SQLException;
import java.util.List;

public class TestFindAll {
    private FournisseurDaoJdbc fournisseurJDBCDAO;
    public TestFindAll() {
        this.fournisseurJDBCDAO = new FournisseurDaoJdbc();
    }
    public List<Fournisseur> FindAll() throws SQLException {
        return this.fournisseurJDBCDAO.findAll();
    }
}
