import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private Bibliotheque bibliotheque;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion de Bibliothèque");

        bibliotheque = new Bibliotheque();

        // Layout principal
        VBox layout = new VBox(10);

        // Boutons
        Button btnAdd = new Button("Ajouter un livre");
        Button btnView = new Button("Afficher tous les livres");
        Button btnSearch = new Button("Rechercher un livre par titre");
        Button btnSave = new Button("Sauvegarder les livres");
        Button btnLoad = new Button("Charger les livres");

        // Actions des boutons
        btnAdd.setOnAction(e -> ajouterLivre());
        btnView.setOnAction(e -> afficherLivres());
        btnSearch.setOnAction(e -> rechercherLivre());
        btnSave.setOnAction(e -> sauvegarderLivres());
        btnLoad.setOnAction(e -> chargerLivres());

        // Ajout des boutons au layout
        layout.getChildren().addAll(btnAdd, btnView, btnSearch, btnSave, btnLoad);

        // Configuration de la scène
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ajouterLivre() {
        // Implémenter l'ajout d'un livre (ouvrir une nouvelle fenêtre ou une nouvelle scène pour les détails du livre)
    }

    private void afficherLivres() {
        // Implémenter l'affichage des livres
    }

    private void rechercherLivre() {
        // Implémenter la recherche d'un livre par titre
    }

    private void sauvegarderLivres() {
        try {
            bibliotheque.sauvegarder("livres.csv");
            System.out.println("Livres sauvegardés dans 'livres.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerLivres() {
        try {
            bibliotheque.charger("livres.csv");
            System.out.println("Livres chargés depuis 'livres.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
