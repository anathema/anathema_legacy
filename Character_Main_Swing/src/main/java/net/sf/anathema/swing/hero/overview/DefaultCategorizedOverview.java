package net.sf.anathema.swing.hero.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class DefaultCategorizedOverview implements CategorizedOverview {

  private final Box panel = new Box(BoxLayout.Y_AXIS);

  @Override
  public final OverviewCategory addOverviewCategory(String borderText) {
    return new SwingOverviewCategory(panel, borderText, true);
  }

  @Override
  public void showIn(OverviewDisplay display) {
    display.setOverviewPane(panel);
  }
}