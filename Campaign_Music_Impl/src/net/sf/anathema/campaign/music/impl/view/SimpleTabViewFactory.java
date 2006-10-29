package net.sf.anathema.campaign.music.impl.view;

import javax.swing.JComponent;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;

public class SimpleTabViewFactory {

  public IInitializableContentView< ? > createTabView(final JComponent component) {
    return new IInitializableContentView<Object>() {

      public JComponent getComponent() {
        return component;
      }

      public void initGui(Object properties) {
        // Nothing to do
      }
    };
  }
}