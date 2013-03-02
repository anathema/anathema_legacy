package net.sf.anathema.character.model.background;

public interface IBackgroundListener {
  void backgroundAdded(IBackground background);

  void backgroundRemoved(IBackground background);
}