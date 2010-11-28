package net.sf.anathema.character.view.magic;

public interface IComboViewListener {

  public void charmAdded(Object addedCharm);

  public void charmRemoved(Object[] removedCharms);

  public void comboFinalized();

  public void comboCleared();
}