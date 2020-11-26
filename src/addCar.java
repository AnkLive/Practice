import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addCar extends employee {
    private JPanel contentPane;
    private JButton add;
    private JLabel message;
    private JLabel idLabel;
    private JLabel modelLabel;
    private JLabel ownerLabel;
    private JTextField idField;
    private JTextField modelField;
    private JTextField ownerField;
    private JLabel error;

    private int intId;
    private String strCarModel;
    private String strOwner;

    public addCar() {
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
        if (idField.getText().length() == 0 || modelField.getText().length() == 0 || ownerField.getText().length() == 0) {
            error.setText("Ошибка: заполните все поля.");
        } else if (idField.getText().length() >= 46 || modelField.getText().length() >= 46 || ownerField.getText().length() >= 46) {
            error.setText("Ошибка: поле не может содержать больше 45 символов.");
        } else if (idField.getText().length() != 0 && idField.getText().length() <= 45)
            try {
                intId = Integer.parseInt(idField.getText());
                strCarModel = modelField.getText();
                strOwner = ownerField.getText();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO cars (id, model, owner) \n" +
                        " VALUES (?, ?, ?);");
                stmt.setInt(1, intId);
                stmt.setString(2, strCarModel);
                stmt.setString(3, strOwner);
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
