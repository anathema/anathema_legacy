package net.sf.anathema.character.library.taskpane;

public interface ITaskPaneGroupViewFactory<V extends ITaskPaneGroupView> {

  public V createView();
}