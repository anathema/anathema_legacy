package net.sf.anathema.framework.presenter.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class AbstractTabView<P> implements ITabView<P> {

  private final JPanel content = new JPanel();
  private final boolean scrollable;

  protected AbstractTabView(boolean scrollable) {
    this.scrollable = scrollable;
  }

  protected AbstractTabView() {
    this(true);
  }

  public final void initGui(P properties) {
    createContent(content, properties);
  }

  protected abstract void createContent(JPanel panel, P properties);

  public final JComponent getComponent() {
    return content;
  }

  public final boolean needsScrollbar() {
    return scrollable;
  }
}