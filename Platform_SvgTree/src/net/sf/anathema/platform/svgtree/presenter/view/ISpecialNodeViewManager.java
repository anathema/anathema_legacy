package net.sf.anathema.platform.svgtree.presenter.view;

public interface ISpecialNodeViewManager<T extends ISVGSpecialNodeView> {

  public void setSpecialNodeViewVisible(ISvgTreeView view, T treeView, boolean visible);
}