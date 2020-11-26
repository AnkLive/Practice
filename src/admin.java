import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class admin extends main {
    private JPanel contentPane;
    private JButton addNewAdministrators;
    private JButton BackToAuthorisation;
    private JButton addNewEmployes;
    private JButton viewToAdministrators;
    private JButton viewToEmployes;
    private JButton viewToClients;
    private JButton viewToCars;
    protected JButton back;
    private JLabel labelName;
    private JLabel viewTable;
    private JPanel tablePane;

    public admin() {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(viewToAdministrators);
        getRootPane().setDefaultButton(viewToEmployes);
        getRootPane().setDefaultButton(viewToClients);
        getRootPane().setDefaultButton(viewToCars);

        getRootPane().setDefaultButton(BackToAuthorisation);

        getRootPane().setDefaultButton(addNewAdministrators);
        getRootPane().setDefaultButton(addNewEmployes);

        viewToAdministrators.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Administrators();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        viewToEmployes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Employes();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        viewToClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Clients();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        viewToCars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Cars();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        BackToAuthorisation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });

        addNewAdministrators.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newAdm();
            }
        });

        addNewEmployes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newEmp();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void Administrators() throws SQLException {
        viewAdministrators();
    }

    private void Employes() throws SQLException {
        viewEmployes();
    }

    private void Clients() throws SQLException {
        viewClients();
    }

    private void Cars() throws SQLException {
        viewCars();
    }

    private void back() {
        this.dispose();
        аuthorisation login = new аuthorisation();
        login.setSize(500,300);
        login.setTitle("Окно авторизации");
        login.setVisible(true);
    }

    private void newAdm() {
        this.dispose();
        addAdmin adm = new addAdmin();
        adm.setSize(500,300);
        adm.setTitle("Добавить нового администратора");
        adm.setVisible(true);
    }

    private void newEmp() {
        this.dispose();
        addEmployes emp = new addEmployes();
        emp.setSize(500,300);
        emp.setTitle("Добавить нового сотрудника");
        emp.setVisible(true);
    }

    /**
     * ########|| Administrators ||#########################################################||
     */

    private void viewAdministrators() throws SQLException {
        try
        {
            Frame frame = new JFrame("Таблица администраторы");
            setModal(true);
            // Устанавливаем менеджер расположения как в дотнете
            ((JFrame) frame).getContentPane().setLayout(new BorderLayout());
            // Создаем табличку и добавляем ее в центр окна
            JTable dbTable = new JTable();
            // JScrollPane нужна для отображения заголовка (ну и для скроллинга таблицы) -
            // в противном случае P_1 и P_2 в заголовке отображены не будут
            JScrollPane pane = new JScrollPane();
            pane.getViewport().add(dbTable);
            ((JFrame) frame).getContentPane().add(pane, BorderLayout.CENTER);

            // Получаю данные из БД
            Vector values = getDataFromDBAdministrators();

            // "Шапка" - т.е. имена полей
            Vector header = new Vector();
            header.add("id");
            header.add("login");
            header.add("password");

            // Помещаю в модель таблицы данные
            DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();
            // Сначала данные, потом шапка
            dtm.setDataVector(values, header);
            // Ну все, теперь только размеры, видимость и чтобы по крестику закрывалось :-)
            frame.toFront();
            frame.repaint();
            this.setModal(true);
            //frame.initModality(Modelity.WINDOW_MODAL);
            frame.setSize(640, 480);
            frame.setVisible(true);
            //((JFrame) frame).setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
        public Vector getDataFromDBAdministrators() throws Exception {
            Vector result = new Vector();
            statement = connection.createStatement();
            String admins = "SELECT * FROM administrators;";
            resultSet = statement.executeQuery(admins);

            // пока у нас есть данные - выполняем цикл
            String id, login, password;

            while (resultSet.next()) {
                // Создаем новый список <P_1, P_2>
                Vector element = new Vector();

                // Первой колонкой у нас объявлен P_1
                id = resultSet.getString(1);
                // Второй - P_2
                login = resultSet.getString(2);

                password = resultSet.getString(3);
                // Добавляем по порядку
                element.add(id);
                element.add(login);
                element.add(password);

                // Присоединяем список к результату
                result.add(element);
            }
            // Освобождаем все ресурсы:
            resultSet.close();
            statement.close();

            return result;
    }

    /**
     * ########|| Employes ||#########################################################||
     */

    private void viewEmployes() throws SQLException {
        try
        {
            Frame frame = new JFrame("Таблица сотрудники");
            setModal(true);
            // Устанавливаем менеджер расположения как в дотнете
            ((JFrame) frame).getContentPane().setLayout(new BorderLayout());
            // Создаем табличку и добавляем ее в центр окна
            JTable dbTable = new JTable();
            // JScrollPane нужна для отображения заголовка (ну и для скроллинга таблицы) -
            // в противном случае P_1 и P_2 в заголовке отображены не будут
            JScrollPane pane = new JScrollPane();
            pane.getViewport().add(dbTable);
            ((JFrame) frame).getContentPane().add(pane, BorderLayout.CENTER);

            // Получаю данные из БД
            Vector values = getDataFromDBEmployes();

            // "Шапка" - т.е. имена полей
            Vector header = new Vector();
            header.add("id");
            header.add("login");
            header.add("password");

            // Помещаю в модель таблицы данные
            DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();
            // Сначала данные, потом шапка
            dtm.setDataVector(values, header);
            // Ну все, теперь только размеры, видимость и чтобы по крестику закрывалось :-)
            frame.toFront();
            frame.repaint();
            this.setModal(true);
            //frame.initModality(Modelity.WINDOW_MODAL);
            frame.setSize(640, 480);
            frame.setVisible(true);
            //((JFrame) frame).setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public Vector getDataFromDBEmployes() throws Exception {
        Vector result = new Vector();
        statement = connection.createStatement();
        String emp = "SELECT * FROM employes;";
        resultSet = statement.executeQuery(emp);

        // пока у нас есть данные - выполняем цикл
        String id, login, password;

        while (resultSet.next()) {
            // Создаем новый список <P_1, P_2>
            Vector element = new Vector();

            // Первой колонкой у нас объявлен P_1
            id = resultSet.getString(1);
            // Второй - P_2
            login = resultSet.getString(2);

            password = resultSet.getString(3);
            // Добавляем по порядку
            element.add(id);
            element.add(login);
            element.add(password);

            // Присоединяем список к результату
            result.add(element);
        }
        // Освобождаем все ресурсы:
        resultSet.close();
        statement.close();

        return result;
    }

    /**
     * ########|| Clients ||#########################################################||
     */

    private void viewClients() throws SQLException {
        try
        {
            Frame frame = new JFrame("Таблица клиенты");
            setModal(true);
            // Устанавливаем менеджер расположения как в дотнете
            ((JFrame) frame).getContentPane().setLayout(new BorderLayout());
            // Создаем табличку и добавляем ее в центр окна
            JTable dbTable = new JTable();
            // JScrollPane нужна для отображения заголовка (ну и для скроллинга таблицы) -
            // в противном случае P_1 и P_2 в заголовке отображены не будут
            JScrollPane pane = new JScrollPane();
            pane.getViewport().add(dbTable);
            ((JFrame) frame).getContentPane().add(pane, BorderLayout.CENTER);

            // Получаю данные из БД
            Vector values = getDataFromDBClients();

            // "Шапка" - т.е. имена полей
            Vector header = new Vector();
            header.add("id");
            header.add("full name");
            header.add("car model");
            header.add("gender");
            header.add("phone number");

            // Помещаю в модель таблицы данные
            DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();
            // Сначала данные, потом шапка
            dtm.setDataVector(values, header);
            // Ну все, теперь только размеры, видимость и чтобы по крестику закрывалось :-)
            frame.toFront();
            frame.repaint();
            this.setModal(true);
            //frame.initModality(Modelity.WINDOW_MODAL);
            frame.setSize(640, 480);
            frame.setVisible(true);
            //((JFrame) frame).setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public Vector getDataFromDBClients() throws Exception {
        Vector result = new Vector();
        statement = connection.createStatement();
        String emp = "SELECT * FROM clients;";
        resultSet = statement.executeQuery(emp);

        // пока у нас есть данные - выполняем цикл
        String id, full_name, car_model, gender, phone_number;

        while (resultSet.next()) {
            // Создаем новый список <P_1, P_2>
            Vector element = new Vector();

            // Первой колонкой у нас объявлен P_1
            id = resultSet.getString(1);
            // Второй - P_2
            full_name = resultSet.getString(2);

            car_model = resultSet.getString(3);

            gender = resultSet.getString(4);

            phone_number = resultSet.getString(5);
            // Добавляем по порядку
            element.add(id);
            element.add(full_name);
            element.add(car_model);
            element.add(gender);
            element.add(phone_number);

            // Присоединяем список к результату
            result.add(element);
        }
        // Освобождаем все ресурсы:
        resultSet.close();
        statement.close();

        return result;
    }

    /**
     * ########|| Cars ||#########################################################||
     */

    private void viewCars() throws SQLException {
        try
        {
            Frame frame = new JFrame("Таблица автомобили");
            setModal(true);
            // Устанавливаем менеджер расположения как в дотнете
            ((JFrame) frame).getContentPane().setLayout(new BorderLayout());
            // Создаем табличку и добавляем ее в центр окна
            JTable dbTable = new JTable();
            // JScrollPane нужна для отображения заголовка (ну и для скроллинга таблицы) -
            // в противном случае P_1 и P_2 в заголовке отображены не будут
            JScrollPane pane = new JScrollPane();
            pane.getViewport().add(dbTable);
            ((JFrame) frame).getContentPane().add(pane, BorderLayout.CENTER);

            // Получаю данные из БД
            Vector values = getDataFromDBCars();

            // "Шапка" - т.е. имена полей
            Vector header = new Vector();
            header.add("id");
            header.add("model");
            header.add("owner (full name)");

            // Помещаю в модель таблицы данные
            DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();
            // Сначала данные, потом шапка
            dtm.setDataVector(values, header);
            // Ну все, теперь только размеры, видимость и чтобы по крестику закрывалось :-)
            frame.toFront();
            frame.repaint();
            this.setModal(true);
            //frame.initModality(Modelity.WINDOW_MODAL);
            frame.setSize(640, 480);
            frame.setVisible(true);
            //((JFrame) frame).setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public Vector getDataFromDBCars() throws Exception {
        Vector result = new Vector();
        statement = connection.createStatement();
        String emp = "SELECT * FROM cars;";
        resultSet = statement.executeQuery(emp);

        // пока у нас есть данные - выполняем цикл
        String id, model, owner;

        while (resultSet.next()) {
            // Создаем новый список <P_1, P_2>
            Vector element = new Vector();

            // Первой колонкой у нас объявлен P_1
            id = resultSet.getString(1);
            // Второй - P_2
            model = resultSet.getString(2);

            owner = resultSet.getString(3);
            // Добавляем по порядку
            element.add(id);
            element.add(model);
            element.add(owner);

            // Присоединяем список к результату
            result.add(element);
        }
        // Освобождаем все ресурсы:
        resultSet.close();
        statement.close();

        return result;
    }
}
