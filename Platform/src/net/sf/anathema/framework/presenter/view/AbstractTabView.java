package net.sf.anathema.framework.presenter.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public abstract class AbstractTabView<P> implements ITabView<P> {

  private final JPanel content = new JPanel();
  private final String header;
  private final boolean scrollable;

  protected AbstractTabView(String header, boolean scrollable) {
    this.header = header;
    this.scrollable = scrollable;
  }

  protected AbstractTabView(String header) {
    this(header, true);
  }

  public final void initGui(P properties) {
    if (header != null) {
      content.setBorder(new TitledBorder(header));
    }
    createContent(content, properties);
  }

  protected abstract void createContent(JPanel panel, P properties);

  public final JComponent getComponent() {
    return content;
  }

  protected final JPanel addTitledPanel(String title, JPanel container, GridDialogPanel contentDialogPanel) {
    JPanel newPanel = contentDialogPanel.getContent();
    newPanel.setBorder(new TitledBorder(title));
    container.add(newPanel);
    return newPanel;
  }

  public final boolean needsScrollbar() {
    return scrollable;
  }

  protected final JPanel addTitledPanel(
      String title,
      JPanel container,
      IGridDialogPanel contentPanel,
      IGridDialogLayoutData constraint) {
    JPanel newPanel = contentPanel.getContent();
    newPanel.setBorder(new TitledBorder(title));
    container.add(newPanel, constraint);
    return newPanel;
  }
}