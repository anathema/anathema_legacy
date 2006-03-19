package net.sf.anathema.character.lunar.heartsblood.presenter;


public interface IHeartsBloodModelListener {

  public void fireSelectionAdded(IAnimalForm form);

  public void fireSelectionRemoved(IAnimalForm form);

  public void entryComplete(boolean complete);
}