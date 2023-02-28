package fr.epsi.b3.test;

import fr.epsi.b3.bo.Article;
import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.ArticleDaoJdbc;
import fr.epsi.b3.dal.FournisseurDaoJdbc;

import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Math.round;

public class TestJdbcArticles {
    private final FournisseurDaoJdbc fournisseurDaoJDBC;
    private final ArticleDaoJdbc articleDaoJDBC;

    public TestJdbcArticles(FournisseurDaoJdbc fournisseurDaoJDBC, ArticleDaoJdbc articleDaoJDBC) {
        this.fournisseurDaoJDBC = fournisseurDaoJDBC;
        this.articleDaoJDBC = articleDaoJDBC;
    }

    public void insert() throws SQLException {
        Fournisseur fournisseur = new Fournisseur("La Maison de la Peinture");
        int idFournisseur = this.fournisseurDaoJDBC.add(fournisseur);
        fournisseur.setId(idFournisseur);

        Article article1 = new Article("Peinture blanche 1L", 12.50, fournisseur);
        Article article2 = new Article("Peinture rouge mate 1L", 15.50, fournisseur);
        Article article3 = new Article("Peinture noire laquée 1L", 17.80, fournisseur);
        Article article4 = new Article("Peinture bleue mate 1L", 15.50, fournisseur);

        this.articleDaoJDBC.insert(article1);
        this.articleDaoJDBC.insert(article2);
        this.articleDaoJDBC.insert(article3);
        this.articleDaoJDBC.insert(article4);

        for (Article temp : this.articleDaoJDBC.findAll()) {
            this.articleDaoJDBC.update(temp , new Article(temp.getnom(), (double) round(temp.getprix()*0.75 * 100) /100, temp.getFournisseur()));
        }

        for (Article temp : this.articleDaoJDBC.findAll()) {
            System.out.printf("%s; %f; %s%n", temp.getnom(), temp.getprix(), temp.getFournisseur().getNom());
        }

        System.out.println("Le prix moyen de l'article est " + round(this.articleDaoJDBC.getMoyenne() * 100) / 100);

        for (Article article : this.articleDaoJDBC.findAll()) {
            if(article.getnom().contains("Peinture")){
                articleDaoJDBC.delete(article.getId());
            }
        }
        for (Fournisseur fournisseurTemp : this.fournisseurDaoJDBC.findAll()) {
            if(Objects.equals(fournisseurTemp.getNom(), "La Maison de la Peinture")){
                fournisseurDaoJDBC.deleteById(fournisseurTemp.getId());
            }
        }

    }
}


