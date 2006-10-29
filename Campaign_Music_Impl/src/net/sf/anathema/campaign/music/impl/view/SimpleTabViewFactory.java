package net.sf.anathema.campaign.music.impl.view;

import javax.swing.JComponent;

import net.sf.anathema.framework.presenter.view.IContentView;

public class SimpleTabViewFactory {

  public IContentView< ? > createTabView(final JComponent component) {
    return new IContentView<Object>() {

      public JComponent getComponent() {
        return component;
      }

      public void initGui(Object properties) {
        // Nothing to do
      }
    };
  }
}