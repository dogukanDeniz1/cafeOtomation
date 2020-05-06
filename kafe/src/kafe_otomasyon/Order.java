package kafe_otomasyon;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int tableNO;
	private int orderNo;
	List<Urunler> alinanUrunler = new ArrayList<Urunler>();
	private String waiterName;


	public Order(int tableNO, String waiterName) {
		this.tableNO = tableNO;
		this.waiterName = waiterName;

	}

	public void addOrder(Urunler urun){

		int flag = 0;

		for (Urunler siparis:alinanUrunler
			 ) {
				if(urun.getName().equals(siparis.getName())){
					siparis.incNumber();
					flag = 1;
				}
		}
		if(flag == 0){
			alinanUrunler.add(urun);
		}

	}
	public void deleteOrder(String product){


		for(int i = 0;i<alinanUrunler.size();i++){


			if(alinanUrunler.get(i).getName().equals(product)){

				if(alinanUrunler.get(i).getNumber() == 1){
					alinanUrunler.remove(i);
				}
				else{
					alinanUrunler.get(i).decNumber();
				}


			}
		}

	}







}
