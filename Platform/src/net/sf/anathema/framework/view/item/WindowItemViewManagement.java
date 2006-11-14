package net.sf.anathema.framework.view.item;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JComponent;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.DockingWindowAdapter;
import net.infonode.docking.RootWindow;
import net.infonode.docking.View;
import net.infonode.docking.theme.BlueHighlightDockingTheme;
import net.infonode.docking.util.DockingUtil;
import net.infonode.util.Direction;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class WindowItemViewManagement implements IComponentItemViewManagement {

  private final RootWindow window = new RootWindow(null);
  private final Map<Component, IItemView> itemViewsByComponent = new HashMap<Component, IItemView>();
  private final Map<IItemView, IObjectValueChangedListener<String>> nameListenersByView = new HashMap<IItemView, IObjectValueChangedListener<String>>();
  private final GenericControl<IViewSelectionListener> control = new GenericControl<IViewSelectionListener>();

  public void addItemView(final IItemView view, final Action closeAction) {
    BlueHighlightDockingTheme theme = new BlueHighlightDockingTheme();
    window.getRootWindowProperties().addSuperObject(theme.getRootWindowProperties());
    window.getRootWindowProperties().getTabWindowProperties().getTabbedPanelProperties().setTabAreaOrientation(
        Direction.DOWN);

    JComponent component = view.getComponent();
    itemViewsByComponent.put(component, view);
    final View windowView = new View(view.getName(), view.getIcon(), component);
    windowView.addListener(new DockingWindowAdapter() {
      @Override
      public void windowClosed(DockingWindow dockingWindow) {
        if (dockingWindow == windowView) {
          closeAction.actionPerformed(null);
        }
      }

      @Override
      public void windowShown(DockingWindow dockingWindow) {
        if (dockingWindow == windowView) {
          fireItemViewChanged(view);
        }
      }
    });
    DockingUtil.addWindow(windowView, window);
    initNameListening(view);
    windowView.restoreFocus();
  }

  private void disposeNameListening(final IItemView view) {
    IObjectValueChangedListener<String> listener = nameListenersByView.get(view);
    nameListenersByView.remove(view);
    view.removeNameChangedListener(listener);
  }

  private void initNameListening(final IItemView view) {
    IObjectValueChangedListener<String> listener = new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        setItemViewName(view, newValue);
      }
    };
    nameListenersByView.put(view, listener);
    view.addNameChangedListener(listener);
  }

  public JComponent getComponent() {
    return window;
  }

  private void fireItemViewChanged(final IItemView view) {
    control.forAllDo(new IClosure<IViewSelectionListener>() {
      public void execute(IViewSelectionListener input) {
        input.viewSelectionChangedTo(view);
      }
    });
  }

  public void addViewSelectionListener(IViewSelectionListener listener) {
    control.addListener(listener);
  }

  public void removeViewSelectionListener(IViewSelectionListener listener) {
    control.removeListener(listener);
  }

  public void setSelectedItemView(IItemView view) {
    // getView(view).restoreFocus();
  }

  private View getView(IItemView view) {
    return findChildWindow(view.getComponent(), window);
  }

  private View findChildWindow(JComponent viewComponent, DockingWindow parentWindow) {
    for (int index = 0; index < parentWindow.getChildWindowCount(); index++) {
      DockingWindow childWindow = parentWindow.getChildWindow(index);
      if (!(childWindow instanceof View)) {
        View view = findChildWindow(viewComponent, childWindow);
        if (view != null) {
          return view;
        }
      }
      else {
        View childView = (View) childWindow;
        if (childView.getComponent() == viewComponent) {
          return childView;
        }
      }
    }
    return null;
  }

  public void removeItemView(IItemView view) {
    Component component = view.getComponent();
    itemViewsByComponent.remove(component);
    disposeNameListening(view);
    view.dispose();
  }

  private void setItemViewName(IItemView view, String name) {
    getView(view).getViewProperties().setTitle(name);
  }
}