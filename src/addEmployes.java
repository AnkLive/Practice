import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class addEmployes extends admin {
    private JPanel contentPane;
    private JButton add;
    private JTextField idField;
    private JTextField loginField;
    private JTextField passwordField;
    private JLabel message;
    private JLabel idLabel;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel error;

    private int intId;
    private String strLogin;
    private String strPassword;

    public addEmployes() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(add);

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void add() {
        if (idField.getText().length() == 0 || loginField.getText().length() == 0 || passwordField.getText().length() == 0) {
            error.setText("Ошибка: заполните все поля.");
        } else if (idField.getText().length() >= 46 || loginField.getText().length() >= 46 || passwordField.getText().length() >= 46) {
            error.setText("Ошибка: поле не может содержать больше 45 символов.");
        } else if (idField.getText().length() != 0 && idField.getText().length() <= 45)
            try {
                intId = Integer.parseInt(idField.getText());
                strLogin = loginField.getText();
                strPassword = passwordField.getText();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO employes (id, login, password) \n" +
                        " VALUES (?, ?, ?);");
                stmt.setInt(1, intId);
                stmt.setString(2, strLogin);
                stmt.setString(3, strPassword);
                stmt.execute();
                stmt.close();
                statement.close();
                this.dispose();
                admin adm = new admin();
                adm.setTitle("Администратор");
                adm.setSize(600, 300);
                adm.setVisible(true);
            } catch (NumberFormatException numberFormatException) {
                error.setText("Ошибка: поле id может содержать только цифры.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}
