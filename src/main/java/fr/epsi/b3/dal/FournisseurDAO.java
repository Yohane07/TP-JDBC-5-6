package fr.epsi.b3.dal;

import fr.epsi.b3.bo.Fournisseur;

import java.util.List;
import java.sql.SQLException;

public interface FournisseurDAO {
    int add(Fournisseur fournisseur) throws SQLException;
    List<Fournisseur> findAll() throws SQLException;

    void update(Fournisseur oldFournisseur, Fournisseur newFournisseur) throws SQLException;

    void deleteById(int fournisseurId) throws SQLException;
}
