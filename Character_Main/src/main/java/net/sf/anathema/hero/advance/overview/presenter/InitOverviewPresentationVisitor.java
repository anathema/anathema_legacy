package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.character.framework.display.labelledvalue.IValueView;
import net.sf.anathema.character.framework.display.labelledvalue.LabelledAllotmentView;
import net.sf.anathema.character.framework.library.overview.OverviewCategory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.hero.points.overview.SpendingModel;

public class InitOverviewPresentationVisitor implements IOverviewModelVisitor {

  private Resources resources;
  private OverviewUpdater updater;
  private OverviewCategories categories;

  public InitOverviewPresentationVisitor(Resources resources, OverviewUpdater updater, OverviewCategories categories) {
    this.resources = resources;
    this.updater = updater;
    this.categories = categories;
  }

  @Override
  public void visitStringValueModel(IValueModel<String> visitedModel) {
    OverviewCategory overviewCategory = categories.get(visitedModel.getCategoryId());
    IValueView<String> casteView = overviewCategory.addStringValueView(getString(visitedModel.getId()));
    updater.add(new StringSubPresenter(visitedModel, casteView, resources));
  }

  @Override
  public void visitIntegerValueModel(IValueModel<Integer> visitedModel) {
    OverviewCategory overviewCategory = categories.get(visitedModel.getCategoryId());
    IValueView<Integer> valueView = overviewCategory.addIntegerValueView(getLabelString(visitedModel), 2);
    updater.add(new ValueSubPresenter(visitedModel, valueView));
  }

  @Override
  public void visitAllotmentModel(SpendingModel visitedModel) {
    OverviewCategory overviewCategory = categories.get(visitedModel.getCategoryId());
    LabelledAllotmentView valueView = overviewCategory.addAlotmentView(getLabelString(visitedModel), 2);
    updater.add(new AllotmentSubPresenter(visitedModel, valueView));
  }


  private String getLabelString(IOverviewModel visitedModel) {
    return getString("Overview.Creation." + visitedModel.getCategoryId() + "." + visitedModel.getId());
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}
