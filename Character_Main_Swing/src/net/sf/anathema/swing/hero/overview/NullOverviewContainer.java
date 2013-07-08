package net.sf.anathema.swing.hero.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.view.CategorizedOverview;
import net.sf.anathema.character.main.view.OverviewDisplay;

public class NullOverviewContainer implements CategorizedOverview {
  @Override
  public OverviewCategory addOverviewCategory(String borderLabel) {
    return new SwingOverviewCategory(null, null, false);
  }

  @Override
  public void showIn(OverviewDisplay characterPane) {
    //nothing to do
  }
}
