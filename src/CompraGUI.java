import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompraGUI extends JFrame {
    private JTextField txtNombreProducto, txtPrecioProducto, txtCantidadProducto;
    private JTextArea txtResumen;
    private JButton btnAgregarProducto, btnCalcularTotal;
    private double totalCompra = 0;

    public CompraGUI() {
        setTitle("Calculadora de Compra");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,2));

        add(new JLabel("Nombre del producto:"));
        txtNombreProducto = new JTextField();
        add(txtNombreProducto);

        add(new JLabel("Precio del producto:"));
        txtPrecioProducto = new JTextField();
        add(txtPrecioProducto);

        add(new JLabel("Cantidad:"));
        txtCantidadProducto = new JTextField();
        add(txtCantidadProducto);

        btnAgregarProducto = new JButton("Agregar producto");
        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombreProducto.getText();
                    double precio = Double.parseDouble(txtPrecioProducto.getText());
                    int cantidad = Integer.parseInt(txtCantidadProducto.getText());

                    Producto producto = new Producto(nombre, precio, cantidad);
                    totalCompra += producto.calcularSubtotal();

                    txtResumen.append(producto.getNombre() + ": $" + producto.calcularSubtotal() + "\n");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CompraGUI.this, "Ingrese un precio y cantidad válidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnAgregarProducto);

        add(new JLabel());
        add(new JLabel());

        add(new JLabel("Total de la compra:"));
        txtResumen = new JTextArea();
        add(new JScrollPane(txtResumen));

        btnCalcularTotal = new JButton("Calcular compra:");
        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResumen.append("\nTotal de la compra: $" + totalCompra);
            }
        });
        add(btnCalcularTotal);

        setVisible(true);

    }
}
