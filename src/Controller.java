public class Controller {
    public Controller() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        System.out.println(databaseConnector.getDatabaseContent());
    }
}
