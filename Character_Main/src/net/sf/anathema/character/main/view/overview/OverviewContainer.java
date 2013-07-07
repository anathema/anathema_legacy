package net.sf.anathema.character.main.view.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.library.overview.SwingOverviewCategory;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class OverviewContainer implements CategorizedOverview {

  private final Box panel = new Box(BoxLayout.Y_AXIS);

  @Override
  public final OverviewCategory addOverviewCategory(String borderText) {
    return new SwingOverviewCategory(panel, borderText, true);
  }

  @Override
  public void showIn(OverviewDisplay display) {
    display.setOverview(panel);
  }
}