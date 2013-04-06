package net.sf.anathema.framework.view.item;

import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.framework.view.SwingItemView;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import org.jmock.example.announcer.Announcer;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

public class ItemViewManagement implements IComponentItemViewManagement {

  private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  private ItemTabComponent itemTabComponent = new ItemTabComponent(tabbedPane);
  private Map<Component, ItemView> itemViewsByComponent = new HashMap<>();
  private Announcer<IViewSelectionListener> control = Announcer.to(IViewSelectionListener.class);

  @Override
  public void addItemView(SwingItemView view, Action closeAction) {
    JComponent component = view.getComponent();
    itemViewsByComponent.put(component, view);
    Icon icon = new ImageProvider().getImageIcon(view.getIconPath());
    tabbedPane.addTab(view.getName(), icon, component);
    int currentIndex = tabbedPane.getTabCount() - 1;
    itemTabComponent.setNewComponentAt(view, closeAction, currentIndex);
    tabbedPane.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        fireItemViewChanged(getSelectedItemView());
      }
    });
    tabbedPane.setSelectedIndex(currentIndex);
  }

  @Override
  public JComponent getComponent() {
    return tabbedPane;
  }

  private ItemView getSelectedItemView() {
    return itemViewsByComponent.get(tabbedPane.getSelectedComponent());
  }

  @Override
  public void addViewSelectionListener(IViewSelectionListener listener) {
    control.addListener(listener);
  }

  private void fireItemViewChanged(ItemView view) {
    control.announce().viewSelectionChangedTo(view);
  }

  @Override
  public void setSelectedItemView(SwingItemView view) {
    tabbedPane.setSelectedComponent(view.getComponent());
  }

  @Override
  public void removeItemView(SwingItemView view) {
    Component component = view.getComponent();
    itemViewsByComponent.remove(component);
    tabbedPane.remove(component);
    itemTabComponent.dispose(view);
  }
}