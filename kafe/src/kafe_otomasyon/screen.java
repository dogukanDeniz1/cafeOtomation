package kafe_otomasyon;

import org.jfree.chart.*;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class screen extends JPanel{

    user_proccess proccess = new user_proccess();
    Order currentOrders[] = new Order[12];
    private int orderNoCounter;
    private int currentMasaNo;

    Object[][][] data = {};

    String[] columnNamesUrunler = {"Ürün Adı", "Fiyat"};
    String[] columNamesEklenenUrunler = {"Ürün Adı","Adet","Fiyat"};
    String[] columNamesAdminUrunler = {"Ürün Adı","Fiyat"};
    String[] columnNamesUsers = {"Ad","Soyad","Kullanıcı Adı","Pozisyon","Şifre"};
    String[] columnNamesStock = {"Ürün Adı","Stok Sayısı"};
    String[] graphNames = proccess.graphNames();
    String monthOrYear = "Yıllık";



    private JFrame frame = new JFrame("Kafe Bilgi Sistemi");

    private JPasswordField passwordField1;
    private JTextField IDTextField;
    private JButton loginButton;
    private JRadioButton loginAsWaiterRadioButton;

    private JPanel panelNow;
    private JPanel login_sc;
    private JPanel adminMain_sc;
    private JPanel waiterMain_sc;
    private JPanel tables_sc;
    private JPanel specifiedTable_sc;

    private JPanel tables;
    private JPanel info;

    private JButton table1;
    private JButton table6;
    private JButton table2;
    private JButton table3;
    private JButton table4;
    private JButton table5;

    private JButton table7;
    private JButton table8;
    private JButton table9;
    private JButton table10;
    private JButton table11;
    private JButton table12;
    private JButton exitButton;
    private JLabel label;
    private JLabel showID;


    private JButton urunEkleButton;
    private JButton urunSilButton;
    private JButton geriButton;
    private JButton masa_kapatButton;
    private JLabel showMasaNo;

    private JTable urunlerTable = new JTable();
    private JTable eklenenUrunlerTable= new JTable();
    private JTable adminUrunlerTable = new JTable();
    private JTable usersTable = new JTable();
    private JTable stockProducts = new JTable();

    private JScrollPane urunlerScrollPane;
    private JScrollPane eklenenUrunlerScrollPane;
    private JScrollPane adminProductOperationsScrollPane;
    private JScrollPane adminUsersScrollPane;
    private JScrollPane productStockScrollPane;

    private JLabel totalPriceText;
    private JPanel InfoPanel;
    private JButton adminExitButton;
    private JLabel adminName_label;
    private JTabbedPane adminMenuPane;
    private JTextField adminProductName;
    private JFormattedTextField formattedTextFieldPrice;
    private JButton adGüncelleButton;
    private JButton fiyatGüncelleButton;
    private JButton ürünEkleButton;
    private JButton ürünSilButton;
    private JTextField adminUserNameText;
    private JPasswordField adminUserPasswordText;
    private JPasswordField adminUserPasswordCorrectionText;
    private JButton kullanıcıEkleButton;
    private JButton kullanıcıSilButton;

    private JRadioButton garsonRadioButton;
    private JRadioButton yoneticiRadioButton;
    private JTextField adminUserRealName;
    private JTextField adminUserRealSurname;
    private JLabel userErrorMsg;

    private JFormattedTextField stockNumberText;
    private JButton setStock;
    private JButton addStockButton;
    private JButton decStockButton;
    private JPanel raporSection;
    private JPanel GraphPanel;
    private JButton downloadGraphButton;
    private JButton son5YılButton;
    private JButton son12AyButton;
    private JPanel comboBoxPanel;
    private JLabel typeLabel;
    private JComboBox whatGraphComboBox = new JComboBox(graphNames);

    DefaultTableModel urunlerTableModel = new DefaultTableModel(columnNamesUrunler,0);
    DefaultTableModel eklenenUrunlerTableModel = new DefaultTableModel(columNamesEklenenUrunler,0);
    DefaultTableModel adminUrunlerTableModel = new DefaultTableModel(columNamesAdminUrunler,0);
    DefaultTableModel userTableModel = new DefaultTableModel(columnNamesUsers,0);
    DefaultTableModel stockProductTableModel = new DefaultTableModel(columnNamesStock,0);
    CardLayout cl = new CardLayout();

    List<JButton> buttons = new ArrayList<>();

    public void refreshButtonBackground(){

        for(int i = 0;i<currentOrders.length;i++){
            if(currentOrders[i] != null && !(currentOrders[i].alinanUrunler.isEmpty())){
                buttons.get(i).setBackground(Color.ORANGE);
            }
            else{
                buttons.get(i).setBackground(Color.LIGHT_GRAY);
            }
        }


    }

    public void initButtons(){
        buttons.add(table1);
        buttons.add(table2);
        buttons.add(table3);
        buttons.add(table4);
        buttons.add(table5);
        buttons.add(table6);
        buttons.add(table7);
        buttons.add(table8);
        buttons.add(table9);
        buttons.add(table10);
        buttons.add(table11);
        buttons.add(table12);
    }

    public void switchPanel(String panelID){
        cl.show(panelNow,panelID);
    }

    public static void refreshEklenenTable(int currentMasaNo,Order[] currentOrders,DefaultTableModel eklenenUrunlerTableModel){
        Object[] data = new Object[3] ;
        int i = 0;

        eklenenUrunlerTableModel.setRowCount(0);
        if(currentOrders[currentMasaNo] != null){
            for (Urunler urun:currentOrders[currentMasaNo].alinanUrunler
            ) {
                data[0] = urun.getName();
                data[1] = urun.getNumber();
                data[2] = (float)(urun.getValue()*urun.getNumber());
                eklenenUrunlerTableModel.addRow(data);
            }
        }

    }
    public static Float calculatePrice(int currentMasaNo, Order[] currentOrders){
        Float price = new Float(0);

        if(currentOrders[currentMasaNo] != null){
            for (Urunler urun:currentOrders[currentMasaNo].alinanUrunler
            ) {
                price = price + (urun.getValue()*urun.getNumber());
            }
        }
        System.out.println(price);
        return price;

    }



    public screen() {



        orderNoCounter = proccess.getCurrentOrderNo();


        panelNow.setLayout(cl);
        panelNow.add(login_sc,"login");
        panelNow.add(adminMain_sc,"adminMain");
        panelNow.add(waiterMain_sc,"waiterMain");
        panelNow.add(specifiedTable_sc,"specified_table");
        panelNow.add(tables_sc,"tables");
        cl.show(panelNow,"login");



        urunlerTable.setModel(urunlerTableModel);
        urunlerTable.setFillsViewportHeight(true);
        urunlerScrollPane.setViewportView(urunlerTable);

        eklenenUrunlerTable.setModel(eklenenUrunlerTableModel);
        eklenenUrunlerTable.setFillsViewportHeight(true);
        eklenenUrunlerScrollPane.setViewportView(eklenenUrunlerTable);

        adminUrunlerTable.setModel(adminUrunlerTableModel);
        adminUrunlerTable.setFillsViewportHeight(true);
        adminProductOperationsScrollPane.setViewportView(adminUrunlerTable);

        usersTable.setModel(userTableModel);
        usersTable.setFillsViewportHeight(true);
        adminUsersScrollPane.setViewportView(usersTable);

        stockProducts.setModel(stockProductTableModel);
        stockProducts.setFillsViewportHeight(true);
        productStockScrollPane.setViewportView(stockProducts);

        urunGoster(proccess.getUrunlerData(),urunlerTableModel);
        urunGoster(proccess.getAdminUrunlerData(),adminUrunlerTableModel);
        urunGoster(proccess.getUsersData(),userTableModel);
        urunGoster(proccess.getStockData(),stockProductTableModel);

        SqlDataSet.createGraphofOrders(proccess.getAllOrders(),0,proccess);
        JFreeChart chart = ChartFactory.createBarChart("","","", SqlDataSet.dataset, PlotOrientation.VERTICAL,false,false,false);
        CategoryPlot catPlot = chart.getCategoryPlot();
        catPlot.setRangeGridlinePaint(Color.black);


        ChartPanel chartPanel = new ChartPanel(chart);
        GraphPanel.removeAll();;
        GraphPanel.add(chartPanel,BorderLayout.CENTER);
        GraphPanel.validate();

        comboBoxPanel.add(whatGraphComboBox);

        initButtons();


        //login screen butonu
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String id = IDTextField.getText();
                String password = passwordField1.getText();
                proccess.setUsernameL(id);
                proccess.setPasswordL(password);

                if (loginAsWaiterRadioButton.isSelected()) {
                    proccess.setPosition("employee");
                    if(proccess.login()){
                        showID.setText(proccess.getUsernameL());

                        switchPanel("tables");
                    }
                    else{
                        System.out.println("Yanlış girdin");
                    }

                }
                else{
                    proccess.setPosition("staff");
                    if(proccess.login()){
                        switchPanel("adminMain");
                        adminName_label.setText(proccess.getUsernameL());
                    }
                    else{
                        System.out.println("Yanlış girdin");
                    }

                }


            }
        });



        frame.add(panelNow);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        table1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                showMasaNo.setText("Masa 1");
                currentMasaNo = 0;
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchPanel("login");

            }


        });
        table2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                showMasaNo.setText("Masa 2");
                currentMasaNo = 1;
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshButtonBackground();
                switchPanel("tables");
            }
        });


        urunEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if(currentOrders[currentMasaNo] == null){
                    currentOrders[currentMasaNo] = new Order(currentMasaNo,proccess.getUsernameL());
                }
                int selected_row = urunlerTable.getSelectedRow();

                if(selected_row == -1){
                    if(urunlerTable.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{


                    String product_name = (String)urunlerTableModel.getValueAt(selected_row,0);
                    Float value = (Float)urunlerTableModel.getValueAt(selected_row,1);


                    currentOrders[currentMasaNo].addOrder(new Urunler(product_name,value));


                }
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
            }
        });
        urunSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int selected_row = eklenenUrunlerTable.getSelectedRow();

                if(selected_row == -1){
                    if(eklenenUrunlerTable.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{


                    String product_name = (String)eklenenUrunlerTable.getValueAt(selected_row,0);


                    currentOrders[currentMasaNo].deleteOrder(product_name);

                    totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                    refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);

                }
            }
        });
        masa_kapatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                orderNoCounter++;

                List<String> ordersName = new ArrayList<String>();
                Integer[] ordersNumber = new Integer[100];

                for (int i = 0;i<eklenenUrunlerTableModel.getRowCount();i++){
                    ordersName.add(eklenenUrunlerTableModel.getValueAt(i,0).toString());
                    ordersNumber[i] = (Integer)eklenenUrunlerTableModel.getValueAt(i,1);
                }
                System.out.println("counter" + orderNoCounter);
                proccess.setMasaHesabi(orderNoCounter,ordersName,ordersNumber,calculatePrice(currentMasaNo,currentOrders));
                eklenenUrunlerTableModel.setRowCount(0);
                urunlerTableModel.setRowCount(0);
                urunGoster(proccess.getUrunlerData(),urunlerTableModel);
                currentOrders[currentMasaNo] = null;
                stockProductTableModel.setRowCount(0);
                urunGoster(proccess.getStockData(),stockProductTableModel);

                totalPriceText.setText("0");

            }
        });

        table3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 2;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 3;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 4;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 5;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 6;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 7;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 8;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 9;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 11;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });
        table12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMasaNo.removeAll();
                currentMasaNo = 11;
                showMasaNo.setText("Masa " + (currentMasaNo+1));
                totalPriceText.setText(calculatePrice(currentMasaNo,currentOrders).toString());
                refreshEklenenTable(currentMasaNo,currentOrders,eklenenUrunlerTableModel);
                switchPanel("specified_table");
            }
        });


        adminExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel("login");
            }
        });
        formattedTextFieldPrice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();

                if(Character.isLetter(c)){
                    formattedTextFieldPrice.setEditable(false);
                }
                else{
                    formattedTextFieldPrice.setEditable(true);
                }

            }
        });
        ürünEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                proccess.productAdd(adminProductName.getText(),Float.parseFloat(formattedTextFieldPrice.getText()));

                urunlerTableModel.setRowCount(0);
                urunGoster(proccess.getUrunlerData(),urunlerTableModel);
                adminUrunlerTableModel.setRowCount(0);
                urunGoster(proccess.getAdminUrunlerData(),adminUrunlerTableModel);
                stockProductTableModel.setRowCount(0);
                urunGoster(proccess.getStockData(),stockProductTableModel);
                graphNames = proccess.graphNames();
                comboBoxPanel.removeAll();
                whatGraphComboBox = new JComboBox(graphNames);
                comboBoxPanel.add(whatGraphComboBox);


            }
        });
        ürünSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = adminUrunlerTable.getSelectedRow();

                if(selected_row == -1){
                    if(adminUrunlerTable.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String product_name = (String)adminUrunlerTable.getValueAt(selected_row,0);

                    proccess.productDelete(product_name);
                    urunGoster(proccess.getUrunlerData(),urunlerTableModel);
                    adminUrunlerTableModel.setRowCount(0);
                    urunGoster(proccess.getAdminUrunlerData(),adminUrunlerTableModel);
                    stockProductTableModel.setRowCount(0);
                    urunGoster(proccess.getStockData(),stockProductTableModel);
                    graphNames = proccess.graphNames();
                    comboBoxPanel.removeAll();
                    whatGraphComboBox = new JComboBox(graphNames);
                    comboBoxPanel.add(whatGraphComboBox);

                }

            }
        });
        adGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = adminUrunlerTable.getSelectedRow();

                if(selected_row == -1){
                    if(adminUrunlerTable.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String product_name = (String)adminUrunlerTable.getValueAt(selected_row,0);

                    proccess.updateProductName(product_name,adminProductName.getText());

                    urunGoster(proccess.getUrunlerData(),urunlerTableModel);
                    adminUrunlerTableModel.setRowCount(0);
                    urunGoster(proccess.getAdminUrunlerData(),adminUrunlerTableModel);
                }

                graphNames = proccess.graphNames();


            }
        });
        fiyatGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = adminUrunlerTable.getSelectedRow();

                if(selected_row == -1){
                    if(adminUrunlerTable.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String product_name = (String)adminUrunlerTable.getValueAt(selected_row,0);

                    proccess.updateProductPrice(product_name,Float.parseFloat(formattedTextFieldPrice.getText()));

                    urunGoster(proccess.getUrunlerData(),urunlerTableModel);
                    adminUrunlerTableModel.setRowCount(0);
                    urunGoster(proccess.getAdminUrunlerData(),adminUrunlerTableModel);
                }
            }
        });

        kullanıcıEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(adminUserPasswordText.getText().equals(adminUserPasswordCorrectionText.getText())){
                    userErrorMsg.setText("");

                    if(garsonRadioButton.isSelected()){
                        userErrorMsg.setText("");
                        proccess.registerUser(adminUserRealName.getText(),adminUserRealSurname.getText(),adminUserNameText.getText(),"employee",adminUserPasswordText.getText());
                    }
                    else if(yoneticiRadioButton.isSelected()){
                        userErrorMsg.setText("");
                        proccess.registerUser(adminUserRealName.getText(),adminUserRealSurname.getText(),adminUserNameText.getText(),"staff",adminUserPasswordText.getText());
                    }
                    else{
                        userErrorMsg.setText("Kullanıcı tipini seçiniz !!!");
                    }
                }
                else{

                    userErrorMsg.setText("Şifreler aynı değil !!!");
                }

                userTableModel.setRowCount(0);
                urunGoster(proccess.getUsersData(),userTableModel);


            }
        });
        kullanıcıSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = usersTable.getSelectedRow();

                if(selected_row == -1){
                    if(userTableModel.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String username = (String)userTableModel.getValueAt(selected_row,2);

                    proccess.deleteUser(username);
                    userTableModel.setRowCount(0);
                    urunGoster(proccess.getUsersData(),userTableModel);

                }

            }
        });
        setStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = stockProducts.getSelectedRow();

                if(selected_row == -1){
                    if(stockProductTableModel.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String productName = (String)stockProductTableModel.getValueAt(selected_row,0);
                    int number = Integer.parseInt(stockNumberText.getText());
                    int currentStock = Integer.parseInt(stockProductTableModel.getValueAt(selected_row,1).toString());

                    proccess.updateStockNumber(productName,1,number,currentStock);
                    stockProductTableModel.setRowCount(0);
                    urunGoster(proccess.getStockData(),stockProductTableModel);


                }


            }
        });
        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = stockProducts.getSelectedRow();

                if(selected_row == -1){
                    if(stockProductTableModel.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String productName = (String)stockProductTableModel.getValueAt(selected_row,0);
                    int number = Integer.parseInt(stockNumberText.getText());
                    int currentStock = Integer.parseInt(stockProductTableModel.getValueAt(selected_row,1).toString());

                    proccess.updateStockNumber(productName,2,number,currentStock);
                    stockProductTableModel.setRowCount(0);
                    urunGoster(proccess.getStockData(),stockProductTableModel);


                }
            }
        });
        decStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected_row = stockProducts.getSelectedRow();

                if(selected_row == -1){
                    if(stockProductTableModel.getRowCount() == 0){
                        System.out.println("Tablo Boş");
                    }
                    else{
                        System.out.println("Ürün seçiniz");
                    }
                }
                else{

                    String productName = (String)stockProductTableModel.getValueAt(selected_row,0);
                    int number = Integer.parseInt(stockNumberText.getText());
                    int currentStock = Integer.parseInt(stockProductTableModel.getValueAt(selected_row,1).toString());

                    proccess.updateStockNumber(productName,3,number,currentStock);
                    stockProductTableModel.setRowCount(0);
                    urunGoster(proccess.getStockData(),stockProductTableModel);


                }
            }
        });
        stockNumberText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();

                if(Character.isLetter(c)){
                    stockNumberText.setEditable(false);
                }
                else{
                    stockNumberText.setEditable(true);
                }
            }
        });
        son5YılButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                monthOrYear = "Yıllık";
                SqlDataSet.dataset.clear();
                graphNames = proccess.graphNames();

                String graphOption = whatGraphComboBox.getSelectedItem().toString();
                System.out.println(graphOption);
                if(graphOption.equals("Toplam Kazanç")){

                    SqlDataSet.createGraphofOrders(proccess.getAllOrders(),0,proccess);
                    JFreeChart chart = ChartFactory.createBarChart("","","", SqlDataSet.dataset, PlotOrientation.VERTICAL,false,false,false);
                    CategoryPlot catPlot = chart.getCategoryPlot();
                    catPlot.setRangeGridlinePaint(Color.black);

                    ChartPanel chartPanel = new ChartPanel(chart);
                    GraphPanel.removeAll();;
                    GraphPanel.add(chartPanel,BorderLayout.CENTER);
                    GraphPanel.validate();

                }
                else{

                    SqlDataSet.createGraphOfProducts(proccess.getProductgraph(),0,proccess,graphOption);
                    JFreeChart chart = ChartFactory.createBarChart("","","", SqlDataSet.dataset, PlotOrientation.VERTICAL,false,false,false);
                    CategoryPlot catPlot = chart.getCategoryPlot();
                    catPlot.setRangeGridlinePaint(Color.black);

                    ChartPanel chartPanel = new ChartPanel(chart);
                    GraphPanel.removeAll();;
                    GraphPanel.add(chartPanel,BorderLayout.CENTER);
                    GraphPanel.validate();
                }

            }
        });

        whatGraphComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(whatGraphComboBox.getSelectedItem().toString().equals("Toplam Kazanç")){
                    typeLabel.setText(" TL   ");
                }
                else{
                    typeLabel.setText(" Adet   ");
                }
            }
        });
        son12AyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                monthOrYear = "Aylık";
                SqlDataSet.dataset.clear();
                graphNames = proccess.graphNames();

                String graphOption = whatGraphComboBox.getSelectedItem().toString();
                System.out.println(graphOption);
                if(graphOption.equals("Toplam Kazanç")){

                    SqlDataSet.createGraphofOrders(proccess.getAllOrders(),1,proccess);
                    JFreeChart chart = ChartFactory.createBarChart("","","", SqlDataSet.dataset, PlotOrientation.VERTICAL,false,false,false);
                    CategoryPlot catPlot = chart.getCategoryPlot();
                    catPlot.setRangeGridlinePaint(Color.black);

                    ChartPanel chartPanel = new ChartPanel(chart);
                    GraphPanel.removeAll();
                    GraphPanel.add(chartPanel,BorderLayout.CENTER);
                    GraphPanel.validate();

                }
                else{

                    SqlDataSet.createGraphOfProducts(proccess.getProductgraph(),1,proccess,graphOption);
                    JFreeChart chart = ChartFactory.createBarChart("","","", SqlDataSet.dataset, PlotOrientation.VERTICAL,false,false,false);
                    CategoryPlot catPlot = chart.getCategoryPlot();
                    catPlot.setRangeGridlinePaint(Color.black);

                    ChartPanel chartPanel = new ChartPanel(chart);
                    GraphPanel.removeAll();
                    GraphPanel.add(chartPanel,BorderLayout.CENTER);
                    GraphPanel.validate();
                }

            }
        });
        downloadGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                LocalDate chartdate = LocalDate.now();
                try{
                    final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                    final File file1 = new File(chartdate.toString() + "_" +monthOrYear + "_" + whatGraphComboBox.getSelectedItem().toString()+".png");
                    ChartUtilities.saveChartAsPNG(file1,chart,800,800);


                }
                catch (Exception ex){

                }

            }
        });
    }

    public static void urunGoster(Object[][] data, DefaultTableModel model)
    {

        for (int i = 0;i<data.length;i++){
            model.addRow(data[i]);
        }
    }
    public static JTable createEklenenTable(Object[][][] data){
        String[] columnNames = {"Ürün Adı","Adet","Fiyat"};
        JTable table = new JTable(data,columnNames);
        table.setFillsViewportHeight(true);
        return table;
    }


    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");

            new screen();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
