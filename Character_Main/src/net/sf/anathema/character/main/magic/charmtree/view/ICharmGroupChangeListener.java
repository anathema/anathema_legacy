package net.sf.anathema.character.main.magic.charmtree.view;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void reselect();
}