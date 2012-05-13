package net.sf.anathema.framework.view.item;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.lib.control.IObjectValueChangedListener;
import org.jdesktop.swingx.JXLabel;
import org.jmock.example.announcer.Announcer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ItemViewManagement implements IComponentItemViewManagement {

  private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
  private final Map<Component, IItemView> itemViewsByComponent = new HashMap<Component, IItemView>();
  private final Map<IItemView, IObjectValueChangedListener<String>> nameListenersByView = new HashMap<IItemView, IObjectValueChangedListener<String>>();
  private final Announcer<IViewSelectionListener> control = Announcer.to(IViewSelectionListener.class);

  @Override
  public void addItemView(final IItemView view, Action closeAction) {
    JComponent component = view.getComponent();
    itemViewsByComponent.put(component, view);
    tabbedPane.addTab(view.getName(), view.getIcon(), component);
    int currentIndex = tabbedPane.getTabCount() - 1;
    tabbedPane.setTabComponentAt(currentIndex, createTabComponent(view, closeAction));
    tabbedPane.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        fireItemViewChanged(getSelectedItemView());
      }
    });
    initNameListening(view);
    tabbedPane.setSelectedIndex(currentIndex);
  }

  private AbstractButton createButton(Action closeAction) {
    JButton button = new JButton(closeAction);
    button.setMargin(new Insets(1, 1, 1, 1));
    button.setPreferredSize(new Dimension(24, 24));
    button.setBorderPainted(false);
    return button;
  }

  private void disposeNameListening(final IItemView view) {
    IObjectValueChangedListener<String> listener = nameListenersByView.get(view);
    nameListenersByView.remove(view);
    view.removeNameChangedListener(listener);
  }

  private void initNameListening(final IItemView view) {
    IObjectValueChangedListener<String> listener = new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        setItemViewName(view, newValue);
      }
    };
    nameListenersByView.put(view, listener);
    view.addNameChangedListener(listener);
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

  private void fireItemViewChanged(final IItemView view) {
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
    disposeNameListening(view);
    view.dispose();
  }

  private JComponent createTabComponent(IItemView view, Action closeAction) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel.setFocusable(false);
    panel.setOpaque(false);
    JXLabel label = new JXLabel(view.getName(), view.getIcon(), SwingConstants.LEFT);
    panel.add(label);
    panel.add(createButton(closeAction));
    return panel;
  }

  private void setItemViewName(IItemView view, String name) {
    int index = tabbedPane.indexOfComponent(view.getComponent());
    JPanel tabComponent = (JPanel) tabbedPane.getTabComponentAt(index);
    JXLabel label = (JXLabel) tabComponent.getComponent(0);
    label.setText(name);
    tabComponent.revalidate();
  }
}
