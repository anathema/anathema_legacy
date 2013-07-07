package net.sf.anathema.character.model.view.magic;

public interface IComboViewListener {

  void charmAdded(Object addedCharm);

  void charmRemoved(Object[] removedCharms);

  void comboFinalized();

  void comboCleared();
}