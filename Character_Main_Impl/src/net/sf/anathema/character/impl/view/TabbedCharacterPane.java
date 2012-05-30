package net.sf.anathema.character.impl.view;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedMultipleContentView;

import javax.swing.JComponent;

public class TabbedCharacterPane implements CharacterPane {

  private final MultipleContentView contentView = new TabbedMultipleContentView();

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    MultipleContentView multipleTabView = new TabbedMultipleContentView(TabDirection.Up);
    contentView.addView(multipleTabView, new ContentProperties(header));
    return multipleTabView;
  }

  @Override
  public void setOverview(JComponent component) {
    //nothing to do
  }

  @Override
  public JComponent getComponent() {
    return contentView.getComponent();
  }
}
