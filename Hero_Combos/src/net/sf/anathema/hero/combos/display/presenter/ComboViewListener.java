package net.sf.anathema.hero.combos.display.presenter;

public interface ComboViewListener {

  void charmAdded(Object addedCharm);

  void charmRemoved(Object[] removedCharms);

  void comboFinalized();

  void comboCleared();
}