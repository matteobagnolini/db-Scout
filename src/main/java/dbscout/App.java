package dbscout;

import dbscout.data.DAOUtils;

public class App {
    public static void main(String[] args) {
        // new Controller
        // start new connection with database
        // new Model
        // new View
        // view.setController()
        // controller.loginView()
    
        var connection = DAOUtils.localMySQLConnection("dbscout", "root", "");

    }
}
