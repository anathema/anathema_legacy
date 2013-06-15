package net.sf.anathema.charmtree.view;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void reselect();
}