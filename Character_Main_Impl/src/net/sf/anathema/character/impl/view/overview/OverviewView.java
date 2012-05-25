package net.sf.anathema.character.impl.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class OverviewView implements IOverviewView, IView {

  private final Box panel = new Box(BoxLayout.Y_AXIS);

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public final IOverviewCategory addOverviewCategory(String borderText) {
    return new OverviewCategory(panel, borderText, true);
  }
}