package kafe_otomasyon;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class user_proccess{


    private String usernameL;
    private String passwordL;
    private String position;
    private Connection con = null;
    private Statement statement =null;
    private PreparedStatement preparedStatement = null;
    private Statement urunlerStatement = null;

    public Object[][] getProductgraph(){

        String urunlerSorgu = "Select * From ordered_products";

        int i = 0,j;


        Object[][] data = new Object[1][6];
        Object[][] tmp = new Object[1][6];

        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);

            while(resultSet.next()){

                Calendar cal = Calendar.getInstance();
                data[i][0] = resultSet.getString("product");
                data[i][1] = resultSet.getInt("number");
                data[i][2] = resultSet.getInt("order_id");
                cal.setTime(resultSet.getDate("date"));
                data[i][3] = cal.get(Calendar.DAY_OF_MONTH);
                data[i][4] = cal.get(Calendar.MONTH) + 1;
                data[i][5] = cal.get(Calendar.YEAR);


                for(j=0;j<i+1;j++){
                    tmp[j][0] = data[j][0];
                    tmp[j][1] = data[j][1];
                    tmp[j][2] = data[j][2];
                    tmp[j][3] = data[j][3];
                    tmp[j][4] = data[j][4];
                    tmp[j][5] = data[j][5];


                }

                data = new Object[i+2][6];

                for(j=0;j<i+1;j++){
                    data[j][0] = tmp[j][0];
                    data[j][1] = tmp[j][1];
                    data[j][2] = tmp[j][2];
                    data[j][3] = tmp[j][3];
                    data[j][4] = tmp[j][4];
                    data[j][5] = tmp[j][5];


                }
                tmp = new Object[i+2][6];

                i++;

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }








        return data;

    }


    public String[] graphNames(){

        String urunlerSorgu = "Select * From products";
        String[] data = new String[40];

        int i = 1,j = 0;

        data[0] = "Toplam Kazanç";

        try {

            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);

            while(resultSet.next()){


                data[i] = resultSet.getString("product_name");
                i = i+1;

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

        String[] graph = new String[i];

        for(j=0;j<i;j++){
            graph[j] = data[j];
        }

        return graph;

    }

    public Object[][] getAllOrders(){

        String urunlerSorgu = "Select * From orders";
        Object[][] data = new Object[getCurrentOrderNo()][6];
        int i = 0;


        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);

            while(resultSet.next()){

                Calendar cal = Calendar.getInstance();
                data[i][0] = resultSet.getInt("order_no");
                data[i][1] = resultSet.getInt("total_price");
                data[i][2] = resultSet.getString("waiter_name");
                cal.setTime(resultSet.getDate("date"));
                data[i][3] = cal.get(Calendar.DAY_OF_MONTH);
                data[i][4] = cal.get(Calendar.MONTH) + 1 ;
                data[i][5] = cal.get(Calendar.YEAR);

                i = i+1;


            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

        return data;
    }

    public void updateStockNumber(String pName, int option, int number , int currentStock){

        String sorgu;
        //SET
        if(option == 1){
            sorgu = "Update products Set stock_number = '"+ number +"' where product_name = '" + pName + "'";
        }
        //ADD
        else if(option == 2){
            number = number + currentStock;
            sorgu = "Update products Set stock_number = '"+ number +"' where product_name = '" + pName + "'";

        }
        //SUB
        else{
            number = currentStock - number;
            sorgu = "Update products Set stock_number = '"+ number +"' where product_name = '" + pName + "'";
        }



        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

    }

    public Object[][] getStockData(){

        String urunlerSorgu = "Select * From products";
        Object[][] data = new Object[40][2];
        Object[][] tmp = new Object[1][2];
        int i = 0,j=0;


        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);

            while(resultSet.next()){

                data[i][0] = resultSet.getString("product_name");
                data[i][1] = resultSet.getInt("stock_number");
                i = i+1;

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }


        int N = i;

        for(i=1;i<N;i++){
            j=i;

            while(j>0 && (int)data[j][1] < (int)data[j-1][1]){

                tmp[0][0] = data[j][0];
                tmp[0][1] = data[j][1];

                data[j][0] = data[j-1][0];
                data[j][1] = data[j-1][1];

                data[j-1][0] = tmp[0][0];
                data[j-1][1] = tmp[0][1];

                j--;
            }
        }



        return data;


    }


    public void deleteUser(String username){
        String sorgu = "DELETE from users where username = '" + username + "'";

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

    }

    public void registerUser(String name, String surname, String id, String position, String password){

        String sorgu  = "INSERT INTO users (name, surname, username, position , password) VALUES(" + "'" + name + "'," + "'" + surname + "','" + id + "','" + position + "','"
                + password + "')";

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }
    }

    public Object[][] getUsersData(){

        String urunlerSorgu = "Select * From users";
        Object[][] data = new Object[40][5];
        int i = 0,j=0;

        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);
            while(resultSet.next()){

                data[i][0] = resultSet.getString("name");
                data[i][1] = resultSet.getString("surname");
                data[i][2] = resultSet.getString("username");
                data[i][3] = resultSet.getString("position");
                data[i][4] = resultSet.getString("password");
                i = i+1;

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

        return data;
    }



    public void updateProductPrice(String p, float newPrice){

        String sorgu = "Update products Set value = '"+ newPrice +"' where product_name = '" + p + "'";

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }
    }

    public void updateProductName(String p , String newName){

        String sorgu = "Update products Set product_name = '"+ newName +"' where product_name = '" + p + "'";

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }
    }

    public void productDelete(String p){

        String sorgu  = "DELETE from products where product_name = '" + p + "'";

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

    }

    public void productAdd(String productName , float price){
        String sorgu  = "INSERT INTO products (product_name, value, stock_number) VALUES(" + "'" + productName + "'," + "'" + price + "', '1') " ;

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorgu);

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

    }

    public  Object [][] getAdminUrunlerData(){
        String urunlerSorgu = "Select * From products";
        Object[][] data = new Object[40][2];
        int i = 0,j=0;

        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);
            while(resultSet.next()){

                data[i][0] = resultSet.getString("product_name");
                data[i][1] = resultSet.getFloat("value");
                i = i+1;

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

        return data;
    }


    public int getCurrentOrderNo(){
        String sorgu = "SELECT id FROM orders ORDER BY ID DESC LIMIT 1";
        int orderNo = 0;

        try {

            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(sorgu);

            if(resultSet.next()){
                orderNo = resultSet.getInt("id");
            }
            else{
                return 0;
            }




        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }
        return orderNo;
    }


    public void setMasaHesabi(int orderCounter, List<String> names,Integer[] numbers,Float price){

        int i = 0,stock_number;

        LocalDate date = LocalDate.now();

        String sorguOrder = "INSERT INTO orders (order_no,total_price,waiter_name,date) VALUES("+"'" + orderCounter + "'," + "'" + price + "'," + "'" + usernameL + "'," + "" +
                "'" + date +  "')" ;

        try {
            urunlerStatement = con.createStatement();
            urunlerStatement.executeUpdate(sorguOrder);

            while(numbers[i] != null){
                System.out.println(names.get(i));
                String sorguProducts = "INSERT INTO ordered_products (product,number,order_id,date) VALUES("+"'"+ names.get(i) + "'," + "'" + numbers[i] + "'," + "'" + orderCounter + "', '"+ date +"')";
                String getStock = "Select * From products where product_name = '" + names.get(i)+"'";
                ResultSet resultSet = urunlerStatement.executeQuery(getStock);
                resultSet.next();
                stock_number = resultSet.getInt("stock_number");
                stock_number = stock_number - numbers[i];
                String updateStock = "UPDATE products set stock_number = '"+ stock_number +"' where product_name = '" + names.get(i) + "' ";
                urunlerStatement.executeUpdate(updateStock);
                urunlerStatement.executeUpdate(sorguProducts);
                i++;
            }



        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }
    }



    public  Object [][] getUrunlerData(){
        String urunlerSorgu = "Select * From products";
        Object[][] data = new Object[40][2];
        int i = 0,j=0;

        try {
            urunlerStatement = con.createStatement();
            ResultSet resultSet = urunlerStatement.executeQuery(urunlerSorgu);
            while(resultSet.next()){
                if(resultSet.getInt("stock_number") > 0){
                    data[i][0] = resultSet.getString("product_name");
                    data[i][1] = resultSet.getFloat("value");
                    i = i+1;
                }

            }

        }
        catch(SQLException ex) {
            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE, null, ex
            );
        }

        return data;
    }

    public boolean login(){
        String query = "Select * From users where username = ? and password = ? and position = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,usernameL);
            preparedStatement.setString(2,passwordL);
            preparedStatement.setString(3,position);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }catch (SQLException ex) {

            Logger.getLogger(user_proccess.class.getName()).log(Level.SEVERE,null,ex
            );
        }
        return false;
    }



    public Connection getCon() {
        return con;
    }

    public user_proccess(){
        String url = "jdbc:mysql://" +Database.host+ ":"
                +Database.port+ "/" + Database.db_name;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Driver could not find...");
        }
        try {
            con = DriverManager.getConnection(url, Database.username,
                    Database.password);

        }catch (SQLException ex){
            System.out.println("Connection Unsuccesfull");


        }
    }



    public String getUsernameL() {
        return usernameL;
    }

    public void setUsernameL(String usernameL) {
        this.usernameL = usernameL;
    }

    public String getPasswordL() {
        return passwordL;
    }

    public void setPasswordL(String passwordL) {
        this.passwordL = passwordL;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
