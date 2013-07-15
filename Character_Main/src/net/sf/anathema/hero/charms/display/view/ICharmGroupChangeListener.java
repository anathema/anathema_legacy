package net.sf.anathema.hero.charms.display.view;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void reselect();
}