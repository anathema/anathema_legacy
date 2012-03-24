package net.sf.anathema.character.view.magic;

public interface IComboViewListener {

  void charmAdded(Object addedCharm);

  void charmRemoved(Object[] removedCharms);

  void comboFinalized();

  void comboCleared();
}