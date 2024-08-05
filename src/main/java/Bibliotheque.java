import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Bibliotheque {
    Map<String, Livres> livres;

    public Bibliotheque() {
        livres = new HashMap<>();
    }

    public void ajouterLivre(Livres livre) {
        livres.put(livre.getId(), livre);
    }

    public void supprimerLivre(String id) throws LivreNonTrouveException {
        if (livres.remove(id) == null) {
            throw new LivreNonTrouveException("Livre avec ID " + id + " non trouvé.");
        }
    }

    public List<Livres> rechercherParTitre(String titre) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getTitre().contains(titre)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public List<Livres> listerParLettre(char lettre) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getTitre().toUpperCase().startsWith(String.valueOf(lettre).toUpperCase())) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public int nombreDeLivres() {
        return livres.size();
    }

    public List<Livres> livresParCategorie(Categorie categorie) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getCategorie() == categorie) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public Livres afficherDetails(String id) {
        return livres.get(id);
    }

    public void sauvegarder(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Livres livre : livres.values()) {
                writer.write(livre.getId() + "," + livre.getTitre() + "," + livre.getAuteur() + "," + livre.getCategorie());
                writer.newLine();
            }
        }
    }

    public void charger(String cheminFichier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] composants = ligne.split(",");
                String id = composants[0];
                String titre = composants[1];
                String auteur = composants[2];
                Categorie categorie = Categorie.valueOf(composants[3]);
                ajouterLivre(new Livres(id, titre, auteur, categorie));
            }
        }
    }

    public void modifierLivre(String id, Livres nouveauLivre) throws LivreNonTrouveException {
        if (livres.containsKey(id)) {
            livres.put(id, nouveauLivre);
        } else {
            throw new LivreNonTrouveException("Livre avec ID " + id + " non trouvé.");
        }
    }
    private void ajouterLivre() {
        Stage stage = new Stage();
        stage.setTitle("Ajouter un Livre");

        VBox layout = new VBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField titreField = new TextField();
        titreField.setPromptText("Titre");

        TextField auteurField = new TextField();
        auteurField.setPromptText("Auteur");

        ComboBox<Categorie> categorieComboBox = new ComboBox<>();
        categorieComboBox.getItems().addAll(Categorie.values());

        Button addButton = new Button("Ajouter");

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String titre = titreField.getText();
            String auteur = auteurField.getText();
            Categorie categorie = categorieComboBox.getValue();

            Livres livre = new Livres(id, titre, auteur, categorie);
            Bibliotheque bibliotheque = new Bibliotheque();
            bibliotheque.ajouterLivre(livre);
            stage.close();
        });

        layout.getChildren().addAll(idField, titreField, auteurField, categorieComboBox, addButton);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}
