package net.sf.anathema.charmtree.presenter;

public interface CharmFilterDefinitionView {
  void whenEditIsFinished(FilterDefinitionListener listener);

  void show();
}