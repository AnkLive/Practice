import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addClient extends employee {
    private JPanel contentPane;
    private JButton add;
    private JLabel message;
    private JLabel idLabel;
    private JLabel fullNameLabel;
    private JLabel carModelLabel;
    private JTextField idField;
    private JTextField fullNameField;
    private JTextField carModelField;
    private JLabel error;
    private JTextField genderField;
    private JTextField phoneNumberField;
    private JLabel genderLabel;
    private JLabel phoneNumberLabel;

    private int intId;
    private String strFullName;
    private String strCarModel;
    private String strGender;
    private String strPhoneNumber;

    public addClient() {
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
        if (idField.getText().length() == 0 || fullNameField.getText().length() == 0 || carModelField.getText().length() == 0
                || genderField.getText().length() == 0 || phoneNumberField.getText().length() == 0) {
            error.setText("Ошибка: заполните все поля.");
        } else if (idField.getText().length() >= 51 || fullNameField.getText().length() >= 51 || carModelField.getText().length() >= 51
                || genderField.getText().length() >= 51 || phoneNumberField.getText().length() >= 51) {
            error.setText("Ошибка: поле не может содержать больше 50 символов.");
        } else if (idField.getText().length() != 0 && idField.getText().length() <= 50)
            try {
                intId = Integer.parseInt(idField.getText());
                strFullName = fullNameField.getText();
                strCarModel = carModelField.getText();
                strGender = fullNameField.getText();
                strPhoneNumber = carModelField.getText();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO clients (id, full name, car model, gender, phone number) \n" +
                        " VALUES (?, ?, ?, ?, ?);");
                stmt.setInt(1, intId);
                stmt.setString(2, strFullName);
                stmt.setString(3, strCarModel);
                stmt.setString(4, strGender);
                stmt.setString(5, strPhoneNumber);
                stmt.execute();
                stmt.close();
                statement.close();
                this.dispose();
                employee emp = new employee();
                emp.setTitle("Сотрудник");
                emp.setSize(400, 300);
                emp.setVisible(true);
            } catch (NumberFormatException numberFormatException) {
                error.setText("Ошибка: поле id может содержать только цифры.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}
