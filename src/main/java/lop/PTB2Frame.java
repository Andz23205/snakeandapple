import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PTB2Frame extends JFrame implements ActionListener {

    JPanel formPanel = new JPanel();
    JLabel aLabel = new JLabel("Hệ số a:");
    JTextField txta = new JTextField();

    JLabel bLabel = new JLabel("Hệ số b:");
    JTextField txtb = new JTextField();

    JLabel cLabel = new JLabel("Hệ số c:");
    JTextField txtc = new JTextField();

    JLabel kqLabel = new JLabel("Kết quả:");
    JTextField txtkq = new JTextField();

    JPanel controlButtonsPanel = new JPanel();
    JButton btnGiai = new JButton("Giải PT");
    JButton btnClear = new JButton("Làm lại");
    JButton btnExit = new JButton("Thoát");

    public PTB2Frame() {
        setLayout(new BorderLayout());

        buildFormPanel();
        buildControlButtonsPanel();

        setSize(450, 300);
        setTitle("Giải phương trình bậc 2");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void buildFormPanel() {
        formPanel.setLayout(new GridBagLayout());
        add(formPanel, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // a
        c.gridx = 0; c.gridy = 0;
        formPanel.add(aLabel, c);
        c.gridx = 1;
        formPanel.add(txta, c);

        // b
        c.gridx = 0; c.gridy = 1;
        formPanel.add(bLabel, c);
        c.gridx = 1;
        formPanel.add(txtb, c);

        // c
        c.gridx = 0; c.gridy = 2;
        formPanel.add(cLabel, c);
        c.gridx = 1;
        formPanel.add(txtc, c);

        // kết quả
        c.gridx = 0; c.gridy = 3;
        formPanel.add(kqLabel, c);
        c.gridx = 1;
        txtkq.setEditable(false);
        formPanel.add(txtkq, c);

        // font
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        aLabel.setFont(font);
        bLabel.setFont(font);
        cLabel.setFont(font);
        kqLabel.setFont(font);
        txta.setFont(font);
        txtb.setFont(font);
        txtc.setFont(font);
        txtkq.setFont(font);
    }

    private void buildControlButtonsPanel() {
        controlButtonsPanel.setLayout(new FlowLayout());
        add(controlButtonsPanel, BorderLayout.SOUTH);

        controlButtonsPanel.add(btnGiai);
        controlButtonsPanel.add(btnClear);
        controlButtonsPanel.add(btnExit);

        btnGiai.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGiai) {
            giaiPT();
        } else if (e.getSource() == btnClear) {
            txta.setText("");
            txtb.setText("");
            txtc.setText("");
            txtkq.setText("");
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void giaiPT() {
        try {
            double a = Double.parseDouble(txta.getText());
            double b = Double.parseDouble(txtb.getText());
            double c = Double.parseDouble(txtc.getText());

            if (a == 0) {
                if (b == 0) {
                    txtkq.setText("Vô nghiệm");
                } else {
                    txtkq.setText("x = " + (-c / b));
                }
                return;
            }

            double delta = b * b - 4 * a * c;

            if (delta < 0) {
                txtkq.setText("Vô nghiệm");
            } else if (delta == 0) {
                txtkq.setText("x = " + (-b / (2 * a)));
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                txtkq.setText("x1 = " + x1 + ", x2 = " + x2);
            }

        } catch (NumberFormatException ex) {    
            txtkq.setText("Nhập sai dữ liệu!");
        }
    }

    public static void main(String[] args) {
        new PTB2Frame().setVisible(true);
    }

}