package net.sf.anathema.swing.hero.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.fx.hero.overview.FxOverviewCategory;
import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class DefaultCategorizedOverview implements CategorizedOverview {

  private final MigPane panel = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));

  @Override
  public final OverviewCategory addOverviewCategory(String borderText) {
    return new FxOverviewCategory(panel, borderText);
  }

  @Override
  public void showIn(OverviewDisplay display) {
    display.setOverviewPane(panel);
  }
}