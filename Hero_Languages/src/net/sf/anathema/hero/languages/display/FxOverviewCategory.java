package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxOverviewCategory implements IOverviewCategory {
  public FxOverviewCategory(MigPane parent, String label) {
  }

  @Override
  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public IValueView<String> addStringValueView(String labelText) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
