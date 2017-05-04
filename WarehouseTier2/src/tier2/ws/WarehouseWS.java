package tier2.ws;

import tier2.model.Good;
import tier2.model.Order;
import tier2.model.Pallet;

public class WarehouseWS {

	public boolean insertPallet(Pallet pallet){
		//add good and pallet to database
		System.out.println("Pallet ID: " + pallet.getId());
		System.out.println("Pallet count: " + pallet.getCount());
		System.out.println("GoodID : " + pallet.getGoodId());
		return true;
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
		pallets[0].setGoodId(1478);
		pallets[0].setCount(5);
		
		pallets[1] = new Pallet();
		pallets[1].setId(21);
		pallets[1].setGoodId(8741);
		pallets[1].setCount(50);
		
		return pallets;
		
	}
	
	public Pallet getPallet(){
		Pallet p = new Pallet();
		p.setCount(1);
		p.setGoodId(2);
		p.setId(3);
		return p;
	}
}
