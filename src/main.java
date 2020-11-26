import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class main extends JDialog {

    protected static Connection connection;

    protected static Statement statement;
    protected static ResultSet resultSet;

    protected static Statement statementAdm;
    protected static ResultSet resultSetAdm;

    protected static Statement statementEmp;
    protected static ResultSet resultSetEmp;

    public static void main(String[] args) throws ClassNotFoundException,
            SQLException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        String url = "jdbc:mysql://localhost/car service?serverTimezone=UTC";
        String username = "root";
        String password = "1243";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        statementAdm = connection.createStatement();
        statementEmp = connection.createStatement();

        аuthorisation login = new аuthorisation();
        login.setSize(400,300);
        login.setTitle("Окно авторизации");
        login.setVisible(true);
    }
}
