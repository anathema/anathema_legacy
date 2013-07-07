package net.sf.anathema.character.main.magic.display.view.charmtree;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void reselect();
}