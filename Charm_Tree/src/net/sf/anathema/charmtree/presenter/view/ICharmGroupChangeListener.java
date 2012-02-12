package net.sf.anathema.charmtree.presenter.view;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);
  
  void reselect();
}