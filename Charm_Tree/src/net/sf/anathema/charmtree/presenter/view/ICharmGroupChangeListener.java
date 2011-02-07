package net.sf.anathema.charmtree.presenter.view;

public interface ICharmGroupChangeListener {

  public void valueChanged(Object charmGroup, Object type);
  
  public void reselect();
}