package net.sf.anathema.fx.hero.overview;

import javafx.scene.control.TitledPane;
import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.view.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.StyledTitledPane;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxOverviewCategory implements OverviewCategory {
  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(4));

  public FxOverviewCategory(final MigPane parent, final String label) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        TitledPane border = StyledTitledPane.Create(label, panel);
        parent.add(border);
      }
    });
  }

  @Override
  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength) {
    FxAlotmentOverview view = new FxAlotmentOverview(labelText);
    view.addTo(panel);
    return view;
  }

  @Override
  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength) {
    FxIntegerOverview view = new FxIntegerOverview(labelText);
    view.addTo(panel);
    return view;
  }

  @Override
  public IValueView<String> addStringValueView(String labelText) {
    FxStringOverview view = new FxStringOverview(labelText);
    view.addTo(panel);
    return view;
  }
}