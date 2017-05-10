package tier2.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tier2.model.Pallet;

public class SmallestAlgo implements IPickAlgo{

	@Override
	public List<Pallet> getBestPallets(List<Pallet> allPallets, int count) {
		allPallets.sort(Comparator.comparing(Pallet::getCount));
		List<Pallet> result = new ArrayList<Pallet>();
		int totalCount = 0;
		for(Pallet p : allPallets){
			result.add(p);
			totalCount += p.getCount();
			if(totalCount > count) break;
		}
		return result;

	}
}
