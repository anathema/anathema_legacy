package net.sf.anathema.framework.repository;

public class ItemAdapter implements IItemListener {

  @Override
  public void printNameChanged(String newName) {
    //nothing to do;
  }

  public void dirtyChanged() {
    //nothing to do
  }
}