package net.sf.anathema.character.presenter.overview;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.library.overview.IAdditionalAlotmentView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class CreationOverviewPresenter {

  private final IResources resources;
  private final IOverviewView view;
  private final ICharacterStatistics statistics;
  private final IBonusPointManagement management;
  private final ICharacterTemplate template;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<IOverviewSubPresenter>();
  private final Map<String, IOverviewCategory> categoriesById = new LinkedHashMap<String, IOverviewCategory>();

  public CreationOverviewPresenter(
      IResources resources,
      ICharacterStatistics statistics,
      IOverviewView overviewView,
      IBonusPointManagement management) {
    this.management = management;
    this.resources = resources;
    this.statistics = statistics;
    this.template = statistics.getCharacterTemplate();
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        updateOverview();
      }
    });
    this.view = overviewView;
  }

  public void init() {
    this.management.recalculate();
    IOverviewModel[] allModels = management.getAllModels();
    initConcept();
    initCategories(allModels);
    for (final IOverviewModel model : allModels) {
      model.accept(new IOverviewModelVisitor() {
        public void visitStringValueModel(IValueModel<String> visitedModel) {
          // TODO Implement
        }

        public void visitIntegerValueModel(IValueModel<Integer> visitedModel) {
          IValueView<Integer> valueView = categoriesById.get(visitedModel.getCategoryId()).addIntegerValueView(
              getLabelString(visitedModel),
              2);
          presenters.add(new ValueSubPresenter(visitedModel, valueView));
        }

        public void visitAlotmentModel(ISpendingModel visitedModel) {
          ILabelledAlotmentView valueView = categoriesById.get(visitedModel.getCategoryId()).addAlotmentView(
              getLabelString(visitedModel),
              2);
          presenters.add(new AlotmentSubPresenter(visitedModel, valueView));
        }

        public void visitAdditionalAlotmentModel(IAdditionalSpendingModel visitedModel) {
          if (visitedModel.isExtensionRequired()) {
            IAdditionalAlotmentView valueView = categoriesById.get(visitedModel.getCategoryId())
                .addAdditionalAlotmentView(getLabelString(visitedModel), visitedModel.getRequiredSize());
            presenters.add(new AdditionalAlotmentSubPresenter(visitedModel, valueView));
          }
          else {
            visitAlotmentModel(visitedModel);
          }
        }
      });
    }
    view.initGui();
    updateOverview();
  }

  private void initCategories(IOverviewModel[] allModels) {
    for (final IOverviewModel model : allModels) {
      final String categoryId = model.getCategoryId();
      IOverviewCategory category = categoriesById.get(categoryId);
      if (category == null) {
        category = view.addOverviewCategory(getString("Overview.Creation.Category." + categoryId)); //$NON-NLS-1$
        categoriesById.put(categoryId, category);
      }
    }
  }

  private void updateOverview() {
    this.management.recalculate();
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
  }

  private String getLabelString(IOverviewModel visitedModel) {
    return getString("Overview.Creation." + visitedModel.getCategoryId() + "." + visitedModel.getId()); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private String getString(String string) {
    return resources.getString(string);
  }

  private void initConcept() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Creation.Category.Concept")); //$NON-NLS-1$
    if (!template.getCasteCollection().isEmpty()) {
      IValueView<String> casteView = category.addStringValueView(getString(template.getPresentationProperties()
          .getCasteLabelResource()));
      IValueModel<String> casteModel = new IValueModel<String>() {
        public String getValue() {
          return getCasteValueResourceKey();
        }

        public String getId() {
          return getString(template.getPresentationProperties().getCasteLabelResource());
        }

        public void accept(IOverviewModelVisitor visitor) {
          visitor.visitStringValueModel(this);
        }

        public String getCategoryId() {
          return "Concept"; //$NON-NLS-1$
        }
      };
      presenters.add(new StringSubPresenter(casteModel, casteView, resources));
    }
    final String[] resourcekey = new String[1];
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        resourcekey[0] = "Overview.Creation.Concept.Nature.Label"; //$NON-NLS-1$
      }

      public void accept(IMotivation motivation) {
        resourcekey[0] = "Overview.Creation.Concept.Motivation.Label"; //$NON-NLS-1$
      }
    });
    IValueView<String> willpowerView = category.addStringValueView(getString(resourcekey[0]));
    IValueModel<String> willpowerModel = new IValueModel<String>() {
      public String getValue() {
        return getWillpowerRegainingConceptValue();
      }

      public String getId() {
        return resourcekey[0];
      }

      public void accept(IOverviewModelVisitor visitor) {
        visitor.visitStringValueModel(this);
      }

      public String getCategoryId() {
        return "Concept"; //$NON-NLS-1$
      }
    };
    presenters.add(new StringSubPresenter(willpowerModel, willpowerView, resources));
  }

  private String getWillpowerRegainingConceptValue() {
    final String[] value = new String[1];
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        INatureType natureType = nature.getDescription().getType();
        if (natureType != null) {
          value[0] = "Nature." + natureType.getId() + ".Name"; //$NON-NLS-1$//$NON-NLS-2$
        }
      }

      public void accept(IMotivation motivation) {
        if (motivation.getDescription().getText() != null) {
          value[0] = "Overview.Creation.Concept.Motivation.Selected"; //$NON-NLS-1$
        }
      }
    });
    return value[0];
  }

  private String getCasteValueResourceKey() {
    ICasteType casteType = statistics.getCharacterConcept().getCaste().getType();
    if (casteType.equals(ICasteType.NULL_CASTE_TYPE)) {
      return null;
    }
    return "Caste." + casteType.getId(); //$NON-NLS-1$
  }
}