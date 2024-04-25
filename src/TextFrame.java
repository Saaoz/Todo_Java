import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFrame extends JFrame implements ActionListener {
    // Components
    private JTextField t; // Field for text input
    private JButton b;    // Button to trigger action
    private DefaultListModel<String> model; // model pour stocker les tâches
    private JList<String> list; //Liste pour afficher les tâches

    // Constructor
    public TextFrame(){
        // Initialisation de la fenêtre et des composants
        super("Todo list"); // Appelle le constructeur de JFrame
        //iniate list model and the list
        model = new DefaultListModel<>();
        list = new JList<>(model);

        initUI();

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

        //margin input
        inputPanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        inputPanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);

        Font fontList = new Font("Arial", Font.PLAIN, 25);

        //Configuration de la list
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(8);
        list.setFont(fontList);
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
//group in input + button
    private void initUI() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints c = new GridBagConstraints();

        Font font = new Font("Arial", Font.BOLD, 15);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5 ,5);

        c.weightx = 0.9;
        c.gridx = 0;
        c.gridy = 0;
        t = new JTextField(15);
        t.setFont(font);

        inputPanel.add(t, c);

        c.weightx = 0.1;
        c.gridx = 1;
        c.gridy = 0;
        b = new JButton("Submit");
        b.setFont(font);
        b.addActionListener(this);
        inputPanel.add(b, c);

        t.setPreferredSize(new Dimension(200, 20)); // Largeur de 200 pixels et hauteur de 20 pixels pour le champ de texte
        b.setPreferredSize(new Dimension(100, 20)); // Largeur de 100 pixels et hauteur de 20 pixels pour le bouton

        this.add(inputPanel, BorderLayout.NORTH);
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