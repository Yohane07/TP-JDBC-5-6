package fr.epsi.b3.test;

import fr.epsi.b3.dal.FournisseurDaoJdbc;

import java.sql.SQLException;

public class TestDelete {
    private FournisseurDaoJdbc fournisseurJDBCDAO;
    public TestDelete() {
        this.fournisseurJDBCDAO = new FournisseurDaoJdbc();
    }
    public void Delete(int fournisseurId) throws SQLException {
        this.fournisseurJDBCDAO.deleteById(fournisseurId);
    }
}
