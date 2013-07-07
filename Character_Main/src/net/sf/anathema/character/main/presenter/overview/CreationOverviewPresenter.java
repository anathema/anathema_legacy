package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.view.overview.CategorizedOverview;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.ISpendingModel;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreationOverviewPresenter implements Presenter {

  private final Resources resources;
  private final CategorizedOverview view;
  private final Hero hero;
  private final IBonusPointManagement management;
  private final HeroTemplate template;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();
  private final Map<String, IOverviewCategory> categoriesById = new LinkedHashMap<>();

  public CreationOverviewPresenter(Resources resources, Hero hero, CategorizedOverview overviewView, IBonusPointManagement management) {
    this.management = management;
    this.resources = resources;
    this.hero = hero;
    this.template = hero.getTemplate();
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
    IOverviewModel[] allModels = management.getAllModels();
    initConcept();
    initCategories(PointModelFetcher.fetch(hero).getBonusCategories());
    initCategories(allModels);
    for (IOverviewModel model : PointModelFetcher.fetch(hero).getBonusOverviewModels()) {
      model.accept(new InitPresentationVisitor());
    }
    for (IOverviewModel model : allModels) {
      model.accept(new InitPresentationVisitor());
    }
    updateOverview();
  }

  private void initCategories(Iterable<WeightedCategory> bonusCategories) {
    for (WeightedCategory category : bonusCategories) {
      initCategory(category.getId());
    }
  }

  private void initCategories(IOverviewModel[] allModels) {
    for (IOverviewModel model : allModels) {
      initCategory(model.getCategoryId());
    }
  }

  private void initCategory(String categoryId) {
    IOverviewCategory category = categoriesById.get(categoryId);
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

  private void initConcept() {
    if (!template.getCasteCollection().isEmpty()) {
      IOverviewCategory category = view.addOverviewCategory(getString("Overview.Creation.Category.Concept"));
      IValueView<String> casteView = category.addStringValueView(getString(template.getPresentationProperties().getCasteLabelResource()));
      IValueModel<String> casteModel = new IValueModel<String>() {
        @Override
        public String getValue() {
          return getCasteValueResourceKey();
        }

        @Override
        public String getId() {
          return getString(template.getPresentationProperties().getCasteLabelResource());
        }

        @Override
        public void accept(IOverviewModelVisitor visitor) {
          visitor.visitStringValueModel(this);
        }

        @Override
        public String getCategoryId() {
          return "Concept";
        }
      };
      presenters.add(new StringSubPresenter(casteModel, casteView, resources));
    }
  }

  //TODO (Duplication) Duplicates rendering info from AgnosticCasteUI
  private String getCasteValueResourceKey() {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return "Caste." + casteType.getId();
  }

  private class InitPresentationVisitor implements IOverviewModelVisitor {
    @Override
    public void visitStringValueModel(IValueModel<String> visitedModel) {
      //Nothing to do
    }

    @Override
    public void visitIntegerValueModel(IValueModel<Integer> visitedModel) {
      IValueView<Integer> valueView = categoriesById.get(visitedModel.getCategoryId()).addIntegerValueView(getLabelString(visitedModel), 2);
      presenters.add(new ValueSubPresenter(visitedModel, valueView));
    }

    @Override
    public void visitAllotmentModel(ISpendingModel visitedModel) {
      ILabelledAlotmentView valueView = categoriesById.get(visitedModel.getCategoryId()).addAlotmentView(getLabelString(visitedModel), 2);
      presenters.add(new AlotmentSubPresenter(visitedModel, valueView));
    }
  }
}