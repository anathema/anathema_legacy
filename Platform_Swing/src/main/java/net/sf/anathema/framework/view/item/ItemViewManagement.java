package net.sf.anathema.framework.view.item;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.Action;
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
  private Map<Component, IItemView> itemViewsByComponent = new HashMap<>();
  private Announcer<IViewSelectionListener> control = Announcer.to(IViewSelectionListener.class);

  @Override
  public void addItemView(IItemView view, Action closeAction) {
    JComponent component = view.getComponent();
    itemViewsByComponent.put(component, view);
    tabbedPane.addTab(view.getName(), view.getIcon(), component);
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

  private IItemView getSelectedItemView() {
    return itemViewsByComponent.get(tabbedPane.getSelectedComponent());
  }

  @Override
  public void addViewSelectionListener(IViewSelectionListener listener) {
    control.addListener(listener);
  }

  private void fireItemViewChanged(IItemView view) {
    control.announce().viewSelectionChangedTo(view);
  }

  @Override
  public void setSelectedItemView(IItemView view) {
    tabbedPane.setSelectedComponent(view.getComponent());
  }

  @Override
  public void removeItemView(IItemView view) {
    Component component = view.getComponent();
    itemViewsByComponent.remove(component);
    tabbedPane.remove(component);
    itemTabComponent.dispose(view);
  }
}