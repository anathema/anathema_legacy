package net.sf.anathema.framework.view.item;

import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.framework.view.SwingItemView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import org.jdesktop.swingx.JXLabel;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import static java.awt.FlowLayout.CENTER;
import static javax.swing.SwingConstants.LEFT;

public class ItemTabComponent {

  private JTabbedPane tabbedPane;
  private Map<ItemView, ObjectValueListener<String>> nameListenersByView = new HashMap<>();

  public ItemTabComponent(JTabbedPane tabbedPane) {
    this.tabbedPane = tabbedPane;
  }

  public void setNewComponentAt(SwingItemView view, Action closeAction, int currentIndex) {
    tabbedPane.setTabComponentAt(currentIndex, createTabComponent(view, closeAction));
    initNameListening(view);
  }

  private JComponent createTabComponent(ItemView view, Action closeAction) {
    JPanel panel = new JPanel(new FlowLayout(CENTER, 0, 0));
    panel.setFocusable(false);
    panel.setOpaque(false);
    Icon icon = new ImageProvider().getImageIcon(view.getIconPath());
    JXLabel label = new JXLabel(view.getName(), icon, LEFT);
    panel.add(label);
    panel.add(createButton(closeAction));
    return panel;
  }

  private AbstractButton createButton(Action closeAction) {
    JButton button = new JButton(closeAction);
    button.setMargin(new Insets(1, 1, 1, 1));
    button.setPreferredSize(new Dimension(24, 24));
    button.setBorderPainted(false);
    return button;
  }

  private void initNameListening(final SwingItemView view) {
    ObjectValueListener<String> listener = new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        setItemViewName(view, newValue);
      }
    };
    nameListenersByView.put(view, listener);
    view.addNameChangedListener(listener);
  }

  private void setItemViewName(SwingItemView view, String name) {
    int index = tabbedPane.indexOfComponent(view.getComponent());
    JPanel tabComponent = (JPanel) tabbedPane.getTabComponentAt(index);
    JXLabel label = (JXLabel) tabComponent.getComponent(0);
    label.setText(name);
    tabComponent.revalidate();
  }

  public void dispose(ItemView view) {
    disposeNameListening(view);
  }

  private void disposeNameListening(ItemView view) {
    ObjectValueListener<String> listener = nameListenersByView.get(view);
    nameListenersByView.remove(view);
    view.removeNameChangedListener(listener);
  }
}
