package fr.epsi.b3.test;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.FournisseurDaoJdbc;

import java.sql.SQLException;

public class TestUpdate {
    private FournisseurDaoJdbc fournisseurDaoJDBC;
    public TestUpdate() {
        this.fournisseurDaoJDBC = new FournisseurDaoJdbc();
    }
    public void Update(Fournisseur exFournisseur, Fournisseur newFournisseur) throws SQLException {
        this.fournisseurDaoJDBC.update(exFournisseur, newFournisseur);
    }
}
