package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.SwingActionTool;
import net.sf.anathema.lib.gui.list.SmartJList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.getComponentSpacing;

public class SingleSelectionToolListView<T> implements ToolListView<T> {

  private final SmartJList<T> list;
  private final JPanel buttonPanel = new JPanel(new GridLayout(1, 0, getComponentSpacing(), getComponentSpacing()));
  private JPanel content;

  public SingleSelectionToolListView(Class<T> contentClass) {
    list = new SmartJList<>(contentClass);
    getList().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  @Override
  public void setObjects(T[] items) {
    list.setObjects(items);
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        listener.run();
      }
    });
  }

  @Override
  public T[] getSelectedItems() {
    return list.getSelectedValues();
  }

  protected JComponent getDisplayComponent() {
    return list;
  }

  protected boolean isScrollable() {
    return true;
  }

  protected SmartJList<T> getList() {
    return list;
  }

  public void setListCellRenderer(ListCellRenderer renderer) {
    list.setCellRenderer(renderer);
  }

  @Override
  public Tool addTool() {
    SwingActionTool tool = new SwingActionTool();
    buttonPanel.add(new JButton(tool.getAction()));
    return tool;
  }

  public JComponent getComponent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel panel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
    JComponent component = isScrollable() ? new JScrollPane(getDisplayComponent()) : getDisplayComponent();
    panel.add(component, new CC().grow().push());
    panel.add(buttonPanel);
    return panel;
  }
}
