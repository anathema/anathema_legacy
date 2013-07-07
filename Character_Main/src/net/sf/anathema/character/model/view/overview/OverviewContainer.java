package net.sf.anathema.character.model.view.overview;

import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.character.main.library.overview.OverviewCategory;

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