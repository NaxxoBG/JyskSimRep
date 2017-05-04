package tier2.ws;

import model.Good;
import model.Order;
import model.Pallet;

public class WarehouseWS {

	public boolean insertPallet(Pallet pallet){
		System.out.println("Pallet ID: " + pallet.getId());
		System.out.println("Pallet count: " + pallet.getCount());
		System.out.println("GoodID : " + pallet.getGood().getGoodId());
		
		DatabaseRemote.insertGood(pallet.getGood());
		return DatabaseRemote.insertPallet(pallet);
	}
	
	public Pallet[] executeOrder(Order order){
		System.out.println("Order id: " + order.getId());
		Good[] goods = order.getGoods();
		for(Good g : goods){
			System.out.println(g.getName());
			System.out.println(g.getManufacturer());
			System.out.println(g.getPalletId());
		}
		
		Pallet[] pallets = new Pallet[2];
		pallets[0] = new Pallet();
		pallets[0].setId(12);
		pallets[0].setCount(5);
		
		pallets[1] = new Pallet();
		pallets[1].setId(21);
		pallets[1].setCount(50);
		
		return pallets;
		
	}
	
	public Pallet getPallet(){
		Pallet p = new Pallet();
		p.setCount(1);
		//p.setGoodId(2);
		p.setId(3);
		return p;
	}
}
