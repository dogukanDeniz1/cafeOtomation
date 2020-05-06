package kafe_otomasyon;

import org.jfree.data.category.DefaultCategoryDataset;

import java.time.LocalDate;

public class SqlDataSet {

    static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private static String[] months = {"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};


    public static void createGraphOfProducts(Object[][] data , int option , user_proccess user, String pName){

        int i;
        LocalDate date = LocalDate.now();

        Integer[] totalNumber = new Integer[5];

        //Year
        if(option == 0){

            totalNumber[0] = 0;
            totalNumber[1] = 0;
            totalNumber[2] = 0;
            totalNumber[3] = 0;
            totalNumber[4] = 0;

            for(i=0;i<data.length-1;i++){

                if(data[i][0].equals(pName)){

                    if(data[i][5].equals(date.getYear())){
                        totalNumber[0] = totalNumber[0] + Integer.parseInt(data[i][1].toString());
                    }
                    else if(data[i][5].equals(date.getYear() - 1)){
                        totalNumber[1] = totalNumber[1] + Integer.parseInt(data[i][1].toString());
                    }
                    else if(data[i][5].equals(date.getYear() - 2)){
                        totalNumber[2] = totalNumber[2] + Integer.parseInt(data[i][1].toString());
                    }
                    else if(data[i][5].equals(date.getYear() - 3)){
                        totalNumber[3] = totalNumber[3] + Integer.parseInt(data[i][1].toString());
                    }
                    else if(data[i][5].equals(date.getYear() - 4)){
                        totalNumber[4] = totalNumber[4] + Integer.parseInt(data[i][1].toString());
                    }

                }

            }

            dataset.setValue(totalNumber[4],"",Integer.toString(date.getYear()-4));
            dataset.setValue(totalNumber[3],"",Integer.toString(date.getYear()-3));
            dataset.setValue(totalNumber[2],"",Integer.toString(date.getYear()-2));
            dataset.setValue(totalNumber[1],"",Integer.toString(date.getYear()-1));
            dataset.setValue(totalNumber[0],"",Integer.toString(date.getYear()));

        }

        //Month
        else if(option == 1){
            totalNumber = new Integer[12];

            for(i=0;i<12;i++){
                totalNumber[i] = 0;
            }

            int currentMonth = date.getMonthValue();


            for(i=0;i<data.length-1;i++){

                if(data[i][0].equals(pName)){

                    if(data[i][5].equals(date.getYear())){

                        if((int)data[i][4] <= currentMonth){

                            totalNumber[currentMonth - (int)data[i][4]] = totalNumber[currentMonth - (int)data[i][4]] + Integer.parseInt(data[i][1].toString());

                        }
                    }
                        else if(data[i][5].equals(date.getYear() - 1)){
                            if((int)data[i][4] > currentMonth){

                                totalNumber[12 - (int)data[i][4] + currentMonth] =  totalNumber[12 - (int)data[i][4] + currentMonth] + Integer.parseInt(data[i][1].toString());

                        }
                    }
                }
            }

            System.out.println(currentMonth);
            currentMonth--;
            String month;
            for(i =11;i>=0;i--){

                if(i>currentMonth){
                    month = months[currentMonth + 12 - i];
                }
                else{
                    month = months[currentMonth - i];
                }

                dataset.setValue(totalNumber[i],"",month);
            }
        }
    }

    public static void createGraphofOrders(Object[][] data , int option , user_proccess user){

        int i,j,orderVolume = user.getCurrentOrderNo() - 1 ;
        Double[] totalMoney = new Double[5];
        LocalDate date = LocalDate.now();

        totalMoney[0] = 0.0;
        totalMoney[1] = 0.0;
        totalMoney[2] = 0.0;
        totalMoney[3] = 0.0;
        totalMoney[4] = 0.0;

        //Year
        if(option == 0){


            for(i=0;i<orderVolume;i++){

                if(data[i][5].equals(date.getYear())){
                    totalMoney[0] = totalMoney[0] + Double.parseDouble(data[i][1].toString()); ;

                }
                else if(data[i][5].equals(date.getYear() - 1 )){
                    totalMoney[1] = totalMoney[1] + Double.parseDouble(data[i][1].toString());

                }
                else if(data[i][5].equals(date.getYear() - 2 )){
                    totalMoney[2] = totalMoney[2] + Double.parseDouble(data[i][1].toString());

                }
                else if(data[i][5].equals(date.getYear() - 3 )){
                    totalMoney[3] = totalMoney[3] + Double.parseDouble(data[i][1].toString());

                }
                else if(data[i][5].equals(date.getYear() - 4 )){
                    totalMoney[4] = totalMoney[4] + Double.parseDouble(data[i][1].toString());

                }

            }

            dataset.setValue(totalMoney[4],"",Integer.toString(date.getYear()-4));
            dataset.setValue(totalMoney[3],"",Integer.toString(date.getYear()-3));
            dataset.setValue(totalMoney[2],"",Integer.toString(date.getYear()-2));
            dataset.setValue(totalMoney[1],"",Integer.toString(date.getYear()-1));
            dataset.setValue(totalMoney[0],"",Integer.toString(date.getYear()));


        }
        //Month
        else if(option == 1){

            totalMoney = new Double[12];

            for(i=0;i<12;i++){
                totalMoney[i] = 0.0;
            }

            int currentMonth = date.getMonthValue();


            for(i=0;i<orderVolume;i++){

                if(data[i][5].equals(date.getYear())){

                    if((int)data[i][4] <= currentMonth){

                        totalMoney[currentMonth - (int)data[i][4]] = totalMoney[currentMonth - (int)data[i][4]] + Double.parseDouble(data[i][1].toString());

                    }

                }
                else if(data[i][5].equals(date.getYear() - 1)){

                    if((int)data[i][4] > currentMonth){

                        totalMoney[12 - (int)data[i][4] + currentMonth] = totalMoney[12 - (int)data[i][4] + currentMonth] + Double.parseDouble(data[i][1].toString());

                    }

                }

            }
            System.out.println(currentMonth);
            currentMonth--;
            String month;
            for(i =11;i>=0;i--){

                if(i>currentMonth){
                    month = months[currentMonth + 12 - i];
                }
                else{
                    month = months[currentMonth - i];
                }

                dataset.setValue(totalMoney[i],"",month);
            }


        }

    }

}
