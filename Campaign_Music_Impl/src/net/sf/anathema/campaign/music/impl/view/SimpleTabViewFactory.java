package net.sf.anathema.campaign.music.impl.view;

import javax.swing.JComponent;

import net.sf.anathema.framework.presenter.view.ITabView;

public class SimpleTabViewFactory {

  public ITabView< ? > createTabView(final JComponent component) {
    return new ITabView<Object>() {

      public JComponent getComponent() {
        return component;
      }

      public void initGui(Object properties) {
        // Nothing to do
      }

      public boolean needsScrollbar() {
        return false;
      }
    };
  }
}