package net.sf.anathema.hero.languages.display.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.platform.fx.BorderedTitledPane;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxOverviewCategory implements OverviewCategory {
  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(4));

  public FxOverviewCategory(final MigPane parent, final String label) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        BorderedTitledPane border = BorderedTitledPane.Create(label, panel);
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