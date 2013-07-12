package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.view.CategorizedOverview;
import net.sf.anathema.character.main.view.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.hero.advance.creation.IBonusPointManagement;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreationOverviewPresenter implements Presenter {

  private final Resources resources;
  private final CategorizedOverview view;
  private final Hero hero;
  private final IBonusPointManagement management;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();
  private final Map<String, OverviewCategory> categoriesById = new LinkedHashMap<>();

  public CreationOverviewPresenter(Resources resources, Hero hero, CategorizedOverview overviewView, IBonusPointManagement management) {
    this.management = management;
    this.resources = resources;
    this.hero = hero;
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        updateOverview();
      }
    });
    this.view = overviewView;
  }

  @Override
  public void initPresentation() {
    this.management.recalculate();
    initCategory(management.getSummaryCategory());
    initCategories(PointModelFetcher.fetch(hero).getBonusCategories());
    for (IOverviewModel model : PointModelFetcher.fetch(hero).getBonusOverviewModels()) {
      model.accept(new InitPresentationVisitor());
    }
    management.getTotalModel().accept(new InitPresentationVisitor());
    updateOverview();
  }

  private void initCategories(Iterable<WeightedCategory> bonusCategories) {
    for (WeightedCategory category : bonusCategories) {
      initCategory(category.getId());
    }
  }

  private void initCategory(String categoryId) {
    OverviewCategory category = categoriesById.get(categoryId);
    if (category == null) {
      category = view.addOverviewCategory(getString("Overview.Creation.Category." + categoryId));
      categoriesById.put(categoryId, category);
    }
  }

  private void updateOverview() {
    this.management.recalculate();
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
  }

  private String getLabelString(IOverviewModel visitedModel) {
    return getString("Overview.Creation." + visitedModel.getCategoryId() + "." + visitedModel.getId());
  }

  private String getString(String string) {
    return resources.getString(string);
  }

  private class InitPresentationVisitor implements IOverviewModelVisitor {
    @Override
    public void visitStringValueModel(IValueModel<String> visitedModel) {
      OverviewCategory overviewCategory = categoriesById.get(visitedModel.getCategoryId());
      IValueView<String> casteView = overviewCategory.addStringValueView(getString(visitedModel.getId()));
      presenters.add(new StringSubPresenter(visitedModel, casteView, resources));
    }

    @Override
    public void visitIntegerValueModel(IValueModel<Integer> visitedModel) {
      OverviewCategory overviewCategory = categoriesById.get(visitedModel.getCategoryId());
      IValueView<Integer> valueView = overviewCategory.addIntegerValueView(getLabelString(visitedModel), 2);
      presenters.add(new ValueSubPresenter(visitedModel, valueView));
    }

    @Override
    public void visitAllotmentModel(SpendingModel visitedModel) {
      OverviewCategory overviewCategory = categoriesById.get(visitedModel.getCategoryId());
      ILabelledAlotmentView valueView = overviewCategory.addAlotmentView(getLabelString(visitedModel), 2);
      presenters.add(new AlotmentSubPresenter(visitedModel, valueView));
    }
  }
}