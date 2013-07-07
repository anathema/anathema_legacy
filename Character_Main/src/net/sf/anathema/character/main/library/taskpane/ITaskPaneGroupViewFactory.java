package net.sf.anathema.character.main.library.taskpane;

public interface ITaskPaneGroupViewFactory<V extends ITaskPaneGroupView> {

  V createView();
}