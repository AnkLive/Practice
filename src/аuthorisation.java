import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class аuthorisation extends main {

    private JPanel contentPane;
    protected JTextField loginField;
    protected JTextField passwordField;
    private JButton signIn;
    protected JLabel аuthorisationError;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel аuthorisationLabel;

    public аuthorisation() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(signIn);

        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    statementAdm = connection.createStatement();
                    statementEmp = connection.createStatement();
                    String admins = "SELECT * FROM administrators;";
                    String emp = "SELECT * FROM employes;";
                    resultSetAdm = statementAdm.executeQuery(admins);
                    resultSetEmp = statementEmp.executeQuery(emp);
                    while (resultSetAdm.next()) {
                        int id = resultSetAdm.getInt("id");
                        String login = resultSetAdm.getString("login");
                        String password = resultSetAdm.getString("password");
                        String guiLogin = loginField.getText();
                        String guiPassword = loginField.getText();
                        if (login.equals(guiLogin) && password.equals(guiPassword)) {
                            System.out.println("Успешный вход - администратор - " + id);
                            autAdm();
                            resultSetAdm.close();
                            statementAdm.close();
                            break;
                        } else {
                            аuthorisationError.setText("Ошибка авторизации: неверный логин или пароль.");
                        }
                    }
                    while (resultSetEmp.next()) {
                        int id = resultSetEmp.getInt("id");
                        String login = resultSetEmp.getString("login");
                        String password = resultSetEmp.getString("password");
                        String guiLogin = loginField.getText();
                        String guiPassword = loginField.getText();
                        if (login.equals(guiLogin) && password.equals(guiPassword)) {
                            System.out.println("Успешный вход - сотрудник - " + id);
                            autEmp();
                            resultSetEmp.close();
                            resultSetEmp.close();
                            break;
                        } else {
                            аuthorisationError.setText("Ошибка авторизации: неверный логин или пароль.");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void autAdm() {
        this.dispose();
        admin adm = new admin();
        adm.setTitle("Администратор");
        adm.setSize(600, 300);
        adm.setVisible(true);
    }

    private void autEmp() {
        this.dispose();
        employee emp = new employee();
        emp.setTitle("Сотрудник");
        emp.setSize(400, 300);
        emp.setVisible(true);
    }
}
