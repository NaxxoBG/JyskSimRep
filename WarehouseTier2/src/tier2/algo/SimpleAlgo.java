package tier2.algo;

import java.util.ArrayList;
import java.util.List;

import tier2.model.Pallet;

public class SimpleAlgo implements IPickAlgo {

	public List<Pallet> getBestPallets(List<Pallet> allPallets, int count) {
		List<Pallet> pallets = new ArrayList<>();
		int temp = 0;
		for (Pallet pallet : allPallets) {
			temp += pallet.getCount();
			pallets.add(pallet);
			if (temp > count) {
				break;
			}
		}
		return pallets;
	}
}