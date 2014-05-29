package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.character.framework.library.overview.OverviewCategory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.advance.creation.IBonusPointManagement;
import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;

public class CreationOverviewPresenter {

  private final Resources resources;
  private final CategorizedOverview view;
  private final Hero hero;
  private final IBonusPointManagement management;
  private final OverviewUpdater updater = new OverviewUpdater();
  private final MappedOverviewCategories categoriesById = new MappedOverviewCategories();

  public CreationOverviewPresenter(Resources resources, Hero hero, CategorizedOverview overviewView, IBonusPointManagement management) {
    this.management = management;
    this.resources = resources;
    this.hero = hero;
    hero.getChangeAnnouncer().addListener(flavor -> updateOverview());
    this.view = overviewView;
  }

  public void initPresentation() {
    initCategories();
    initModels();
    updateOverview();
  }

  private void initCategories() {
    initCategory(management.getSummaryCategory());
    initCategories(PointModelFetcher.fetch(hero).getBonusCategories());
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

  private void initModels() {
    for (IOverviewModel model : PointModelFetcher.fetch(hero).getBonusOverviewModels()) {
      model.accept(new InitOverviewPresentationVisitor(resources, updater, categoriesById));
    }
    management.getTotalModel().accept(new InitOverviewPresentationVisitor(resources, updater, categoriesById));
  }

  private void updateOverview() {
    this.management.recalculate();
    this.updater.update();
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}