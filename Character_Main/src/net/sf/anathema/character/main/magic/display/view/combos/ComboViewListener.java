package net.sf.anathema.character.main.magic.display.view.combos;

public interface ComboViewListener {

  void charmAdded(Object addedCharm);

  void charmRemoved(Object[] removedCharms);

  void comboFinalized();

  void comboCleared();
}