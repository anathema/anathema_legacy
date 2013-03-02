package net.sf.anathema.character.impl.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.view.overview.CategorizedOverview;
import net.sf.anathema.character.view.overview.OverviewDisplay;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class OverviewContainer implements CategorizedOverview {

  private final Box panel = new Box(BoxLayout.Y_AXIS);

  @Override
  public final IOverviewCategory addOverviewCategory(String borderText) {
    return new OverviewCategory(panel, borderText, true);
  }

  @Override
  public void showIn(OverviewDisplay display) {
    display.setOverview(panel);
  }
}