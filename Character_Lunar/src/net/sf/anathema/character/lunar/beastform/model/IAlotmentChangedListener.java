package net.sf.anathema.character.lunar.beastform.model;

public interface IAlotmentChangedListener {

  void totalChanged(int newValue);

  void spentChanged(int newValue);
}