
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Passenger {
    private String seatNumber;
    private String trainNumber;
    private String firstName;
    private String secondName;
    private int age;
    private String price;
    private String disability;

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/myData";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch(Exception e){System.out.println(e);}

        return null;
    }

    public static void createTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Passengers(id int NOT NULL AUTO_INCREMENT, trainNumber varchar(255), seatNumber varchar(255), firstName varchar(255), secondName varchar(255), disability varchar(255), age int, price varchar(255), PRIMARY KEY(id))");
            create.executeUpdate();

        } catch (Exception e) { System.out.println(e); }
    }

    public static void findData() throws Exception {
        try {
            Scanner ninethScanner = new Scanner(System.in);
            System.out.println("ID: ");

            int inputID = ninethScanner.nextInt();

            Connection con = getConnection();
            String query = "SELECT * FROM Passengers WHERE id  =  '" + inputID + "'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int yourId = rs.getInt(1);
                String yourTrainNumber = rs.getString(2);
                String yourSeatNumber = rs.getString(3);
                String yourFirstName = rs.getString(4);
                String yourSecondName = rs.getString(5);
                String yourDisability = rs.getString(6);
                String yourAge = rs.getString(7);
                String yourPrice = rs.getString(8);

                System.out.println("ID: " + yourId);
                System.out.println("Train number: " + yourTrainNumber);
                System.out.println("Seat number: " + yourSeatNumber);
                System.out.println("First name: " + yourFirstName);
                System.out.println("Second name: " + yourSecondName);
                System.out.println("Disability: " + yourDisability);
                System.out.println("Age: " + yourAge);
                System.out.println("Price: " + yourPrice);
            }
        } catch (Exception e) { System.out.println(e); }
    }

    public static void changeData() throws Exception {
        try {
            Scanner ninethScanner = new Scanner(System.in);
            System.out.println("ID: ");

            int inputID = ninethScanner.nextInt();

            Scanner tenthScanner = new Scanner(System.in);
            System.out.println("New train number: ");

            String inputNewTrainNumber = tenthScanner.nextLine();

            Scanner eleventhScanner = new Scanner(System.in);
            System.out.println("New seat number: ");

            String inputNewSeatNumber = eleventhScanner.nextLine();

            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE Passengers SET trainNumber = '"+inputNewTrainNumber+"' ");
            st.executeUpdate();
            PreparedStatement st2 = con.prepareStatement("UPDATE Passengers SET seatNumber = '"+inputNewSeatNumber+"' ");
            st2.executeUpdate();
        } catch (Exception e) { System.out.println(e); }
    }

    public static void deleteData() throws Exception {
        try {
            Scanner twlScanner = new Scanner(System.in);
            System.out.println("ID: ");

            int findInputID = twlScanner.nextInt();

            Connection con = getConnection();
            PreparedStatement sr = con.prepareStatement("DELETE FROM Passengers WHERE id = '"+findInputID+"' ");
            sr.executeUpdate();
        } catch (Exception e) { System.out.println(e); }
    }

    public static void insertData() throws Exception {
        try {
            Scanner secondScanner = new Scanner(System.in);
            System.out.println("Train number: ");

            String inputTrainNumber = secondScanner.nextLine();

            Scanner thirdScanner = new Scanner(System.in);
            System.out.println("Seat number: ");

            String inputSeatNumber = thirdScanner.nextLine();

            Scanner fourthScanner = new Scanner(System.in);
            System.out.println("First name: ");

            String inputFirstName = fourthScanner.nextLine();

            Scanner fivethScanner = new Scanner(System.in);
            System.out.println("Second name: ");

            String inputSecondName = fivethScanner.nextLine();

            Scanner sixthScanner = new Scanner(System.in);
            System.out.println("Disability (1 - YES, 0 - NO): ");

            String inputDisability = sixthScanner.nextLine();

            Scanner seventhScanner = new Scanner(System.in);
            System.out.println("Age: ");

            int inputAge = seventhScanner.nextInt();

            int inputPrice = 0;

            if (inputAge <= 18) {
                inputPrice = 100;
            } else if (inputAge > 18 && inputAge < 23) {
                inputPrice = 150;
            } else if (inputAge >= 23) {
                inputPrice = 200;
            } else if (inputPrice > 45) {
                inputPrice = 150;
            }

            if (Objects.equals(inputDisability, "1")) {
                inputPrice *= 0.8;
            }

            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("INSERT INTO Passengers (trainNumber, seatNumber, firstName, secondName, disability, age, price) VALUES ('"+inputTrainNumber+"', '"+inputSeatNumber+"', '"+inputFirstName+"', '"+inputSecondName+"', '"+inputDisability+"', '"+inputAge+"', '"+inputPrice+"')");
            create.executeUpdate();

        } catch (Exception e) { System.out.println(e); }
    }

    Passenger(String trainNumber, String seatNumber, String firstName, String secondName, String disability, int age, String price) {
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.disability = disability;
        this.age = age;
        this.price = price;
    }

    public  void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public void setDisability(String disability) { this.disability = disability; }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getDisability() {
        return disability;
    }

    public String getSeatNumber() {
        return  seatNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getPrice() {
        return price;
    }

    public static void main(String[] args) throws Exception {
        getConnection();
        createTable();

        Passenger[] listOfPassengers = new Passenger[40];
        listOfPassengers[0] = new Passenger("1", "1", "Zhanibek", "Bakyt", "0", 19, "0");

        System.out.println("Меню программы");
        System.out.println("1. Добавить пассажира");
        System.out.println("2. Удалить пассажира");
        System.out.println("3. Изменить билет");
        System.out.println("4. Посмотреть билет");

        System.out.println("Choose menu's number: ");
        Scanner firstScanner = new Scanner(System.in);

        String firstInput = firstScanner.nextLine();
        if (Objects.equals(firstInput, "1")) {
            insertData();
        } else if (Objects.equals(firstInput, "2")) {
            deleteData();
        } else if (Objects.equals(firstInput, "3")) {
            changeData();
        } else if (Objects.equals(firstInput, "4")) {
            findData();
        }
    }
}