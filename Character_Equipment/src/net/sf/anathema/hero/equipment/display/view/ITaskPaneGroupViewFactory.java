package net.sf.anathema.hero.equipment.display.view;

public interface ITaskPaneGroupViewFactory<V extends ITaskPaneGroupView> {

  V createView();
}