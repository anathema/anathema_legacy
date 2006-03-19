package net.sf.anathema.framework.presenter.view;


public interface IMultiTabView extends ITabView<Object> {

  public void addTabView(ISimpleTabView view, String tabName);
}