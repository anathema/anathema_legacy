package net.sf.anathema.hero.charms.display.presenter;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void reselect();
}