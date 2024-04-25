import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFrame extends JFrame implements ActionListener {
    // Components

    private DefaultListModel<String> model; // model pour stocker les tâches
    private JList<String> list; //Liste pour afficher les tâches

    private void initUI() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        // Pour le JTextField
        c.weightx = 0.9; // Lui donner la majeure partie de l'espace horizontal
        c.gridx = 0; // Position sur la grille x (début)
        c.gridy = 0; // Position sur la grille y (en haut)
        t = new JTextField(10); // Ajustez ce nombre selon le besoin
        inputPanel.add(t, c);

        // Pour le JButton
        c.weightx = 0.1; // Lui donner moins d'espace horizontal
        c.gridx = 1; // Position à côté du JTextField
        c.gridy = 0; // Même rangée que le JTextField
        b = new JButton("Submit");
        b.addActionListener(this);
        inputPanel.add(b, c);

        // Ajouter le panel à votre JFrame
        this.add(inputPanel, BorderLayout.NORTH);
    }



    // Constructor
    public TextFrame(){
        // Initialisation de la fenêtre et des composants
        super("Todo list"); // Appelle le constructeur de JFrame
        initUI();


        model = new DefaultListModel<>();
        list = new JList<>(model);
        t = new JTextField(10); //texte avec 16 colonnes
        b = new JButton("Submit");
        b.addActionListener(this); // link listener + bouton

        //Update to center list object
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected,boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index ,isSelected , cellHasFocus);
                setHorizontalAlignment(CENTER);
                return this;
            }
        });


        // Panel pour organiser les composants
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(t);
        inputPanel.add(b);

        //margin de linput
        inputPanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        inputPanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);


        //Configuration de la liste
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(8);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250,80));


        //disposition des composants dans la fenêtre
        this.setLayout(new BorderLayout()); // BorderLayout gestionnaire de mise en page
        this.add(inputPanel, BorderLayout.NORTH); // Placer le panel en haut de la fenêtre
        this.add(listScroller, BorderLayout.CENTER); // Placer le défilement de la liste au centre


        // Configuration finale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    // Méthode d'ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        String task = t.getText().trim();
        if (!task.isEmpty()) {
            model.addElement(task);//add task to model
            t.setText(""); // reset text area after adding the task model
        }
    }
}