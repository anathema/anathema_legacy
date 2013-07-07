package net.sf.anathema.character.main.magic.view;

public interface IComboViewListener {

  void charmAdded(Object addedCharm);

  void charmRemoved(Object[] removedCharms);

  void comboFinalized();

  void comboCleared();
}