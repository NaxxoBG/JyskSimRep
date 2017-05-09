package tier2.algo;

import java.util.List;

import tier2.model.Pallet;

public interface IPickUpAlgo {
	List<Pallet> getBestPallets(List<Pallet> allPallets, int count);
}