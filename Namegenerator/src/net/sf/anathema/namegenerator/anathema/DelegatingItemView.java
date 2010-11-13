package net.sf.anathema.namegenerator.anathema;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.lib.gui.IView;

public class DelegatingItemView extends AbstractItemView {

  private final IView view;

  protected DelegatingItemView(String name, Icon icon, IView view) {
    super(name, icon);
    this.view = view;
  }

  public JComponent getComponent() {
    return view.getComponent();
  }
}