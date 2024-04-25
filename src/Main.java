import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // Créer l'interface graphique dans le thread de distribution d'événements (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextFrame(); // Crée et affiche la fenêtre
            }
        });
    }
}
