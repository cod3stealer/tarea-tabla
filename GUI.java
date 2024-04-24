import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    ArrayList<Alumno> clase;
    JComboBox Calumno;
    JPanel panel;
    JScrollPane tabla;
    String[] column = {"Nombre","Apellido","Clase"};
    DefaultTableModel modelo = new DefaultTableModel(column,0);
    JTextField nombre;
    ArrayList<String[]> data = getData();
    JTextField apellido;

    public GUI (Clase clase) {
        this.clase = clase.getClase();
        setTitle("Window");
        setSize(1200,1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel = addAlumno();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panel, gbc);

        tabla = addTable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tabla, gbc);

        setVisible(true);
    }
    public JPanel addAlumno (){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);
        JLabel lblAlumno = new JLabel("Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = padding;
        panel.add(lblAlumno, gbc);
        nombre = new JTextField();
        nombre.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nombre, gbc);
        String[] opciones = {"DAM 1", "DAM 2"};
        Calumno = new JComboBox(opciones);
        Calumno.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(Calumno, gbc);
        JLabel lblApelido = new JLabel("Apelido");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblApelido, gbc);
        apellido = new JTextField();
        apellido.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(apellido, gbc);
        JButton addTable = new JButton("Add Table");
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(addTable, gbc);
        addTable.addActionListener(e -> {
            newAlumno();
            data = getData();
            addRowTable();
            tabla.repaint();
        });
        panel.setVisible(true);
        return panel;
    }
    public JScrollPane addTable(){
        for (String[] row: data){
            modelo.addRow(row);
        }
        JTable table= new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
    public JScrollPane addRowTable(){
        modelo.addRow(new String[]{
                nombre.getText(),
                apellido.getText(),
                Calumno.getSelectedItem().toString()
        });
        JTable table= new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
    public ArrayList<String[]> getData() {
        ArrayList<String[]> data = new ArrayList<>();
        for (int i = 0; i < clase.size(); i++){
            data.add(new String[]{clase.get(i).getNombre(),
                    clase.get(i).getApellido(), clase.get(i).getClase()});
        }
        return data;
    }
    public void newAlumno(){
        clase.add(new Alumno(
                nombre.getText(),
                apellido.getText(),
                Calumno.getSelectedItem().toString()
        ));
    }
}
