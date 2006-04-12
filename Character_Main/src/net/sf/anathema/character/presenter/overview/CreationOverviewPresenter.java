package net.sf.anathema.character.presenter.overview;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
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
    initConcept();
    initAttributes();
    initAbilities();
    initAdvantages();
    initCharms();
    initTotal();
    view.initGui();
    updateOverview();
  }

  private void initTotal() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.BonusPoints.Title")); //$NON-NLS-1$
    if (statistics.getExtendedConfiguration().getAdditionalModels().length > 0) {
      IValueView<Integer> miscView = category.addIntegerValueView(getString("Overview.MiscPointsCategory"), 2); //$NON-NLS-1$
      presenters.add(new ValueSubPresenter(management.getAdditionalModelModel(), miscView));
    }
    IAdditionalRules additionalRules = template.getAdditionalRules();
    if (additionalRules == null || additionalRules.getAdditionalBonusPointPools().length == 0) {
      ILabelledAlotmentView totalView = category.addAlotmentView(getString("Overview.BonusPointsCategory"), 2); //$NON-NLS-1$
      presenters.add(new AlotmentSubPresenter(
          management.getTotalModel(),
          totalView,
          getCreationPoints().getBonusPointCount()));
    }
    else {
      IAdditionalAlotmentView totalView = category.addAdditionalAlotmentView(
          getString("Overview.BonusPointsCategory"), 5); //$NON-NLS-1$
      presenters.add(new AdditionalAlotmentSubPresenter(
          management.getTotalModel(),
          totalView,
          getCreationPoints().getBonusPointCount()));

    }
  }

  private void initConcept() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Concept.Title")); //$NON-NLS-1$
    if (!template.getCasteCollection().isEmpty()) {
      IValueView<String> casteView = category.addStringValueView(getString(template.getPresentationProperties()
          .getCasteLabelResource()));
      IValueModel<String> casteModel = new IValueModel<String>() {
        public String getValue() {
          return getCasteValueResourceKey();
        }
      };
      presenters.add(new StringSubPresenter(casteModel, casteView, resources));
    }
    final String[] resourcekey = new String[1];
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        resourcekey[0] = "CharacterConcept.Nature"; //$NON-NLS-1$
      }

      public void accept(IMotivation motivation) {
        resourcekey[0] = "CharacterConcept.Motivation"; //$NON-NLS-1$
      }
    });
    IValueView<String> willpowerView = category.addStringValueView(getString(resourcekey[0]));
    IValueModel<String> willpowerModel = new IValueModel<String>() {
      public String getValue() {
        return getWillpowerRegainingConceptValue();
      }
    };
    presenters.add(new StringSubPresenter(willpowerModel, willpowerView, resources));
  }

  private void initAdvantages() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Advantages.Title")); //$NON-NLS-1$
    ILabelledAlotmentView virtueView = category.addAlotmentView(getString("Overview.VirtueCategory"), 2); //$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getVirtueModel(),
        virtueView,
        getCreationPoints().getVirtueCreationPoints()));
    ILabelledAlotmentView backgroundView = category.addAlotmentView(getString("Overview.BackgroundCategory"), 2); //$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getBackgroundModel(),
        backgroundView,
        getCreationPoints().getBackgroundPointCount()));
  }

  private void initCharms() {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Charms.Title")); //$NON-NLS-1$
    ILabelledAlotmentView favoredCharmView = category.addAlotmentView(getString("Overview.FavoredCharmCategory"), 2); //$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getFavoredCharmModel(),
        favoredCharmView,
        template.getCreationPoints().getFavoredCreationCharmCount()));
    IAdditionalRules additionalRules = template.getAdditionalRules();
    if (additionalRules == null || additionalRules.getAdditionalMagicLearnPools().length == 0) {
      ILabelledAlotmentView defaultCharmView = category.addAlotmentView(getString("Overview.GeneralCharmCategory"), 2); //$NON-NLS-1$
      presenters.add(new AlotmentSubPresenter(
          management.getDefaultCharmModel(),
          defaultCharmView,
          template.getCreationPoints().getDefaultCreationCharmCount()));
    }
    else {
      IAdditionalAlotmentView defaultCharmView = category.addAdditionalAlotmentView(
          getString("Overview.GeneralCharmCategory"), 3); //$NON-NLS-1$
      presenters.add(new AdditionalAlotmentSubPresenter(
          management.getDefaultCharmModel(),
          defaultCharmView,
          template.getCreationPoints().getDefaultCreationCharmCount()));
    }
  }

  private void initAbilities() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Abilities.Title")); //$NON-NLS-1$
    ILabelledAlotmentView favoredAbilityPickView = category.addAlotmentView(
        getString("Overview.FavoredAbilityCategory"), 2);//$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getFavoredAbilityPickModel(),
        favoredAbilityPickView,
        getCreationPoints().getAbilityCreationPoints().getFavorableTraitCount()));
    ILabelledAlotmentView favoredAbiltyDotView = category.addAlotmentView(
        getString("Overview.FavoredAbilityDotCategory"), 2); //$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getFavoredAbilityModel(),
        favoredAbiltyDotView,
        getCreationPoints().getAbilityCreationPoints().getFavoredDotCount()));
    ILabelledAlotmentView defaultAbilityDotView = category.addAlotmentView(
        getString("Overview.GeneralAbilityDotCategory"), 2); //$NON-NLS-1$
    presenters.add(new AlotmentSubPresenter(
        management.getDefaultAbilityModel(),
        defaultAbilityDotView,
        getCreationPoints().getAbilityCreationPoints().getDefaultDotCount()));
  }

  private void initAttributes() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Attributes.Title")); //$NON-NLS-1$
    initAttributePresentation(category, AttributeGroupPriority.Primary);
    initAttributePresentation(category, AttributeGroupPriority.Secondary);
    initAttributePresentation(category, AttributeGroupPriority.Tertiary);
  }

  private void initAttributePresentation(IOverviewCategory category, AttributeGroupPriority priority) {
    ILabelledAlotmentView alotmentView = category.addAlotmentView(getString("Overview.AttributeCategory." //$NON-NLS-1$
        + priority.getId()), 2);
    presenters.add(new AlotmentSubPresenter(
        management.getAttributeModel(priority),
        alotmentView,
        getCreationPoints().getAttributeCreationPoints().getCount(priority)));
  }

  private void updateOverview() {
    this.management.recalculate();
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
  }

  private ICreationPoints getCreationPoints() {
    return template.getCreationPoints();
  }

  private String getWillpowerRegainingConceptValue() {
    final String[] value = new String[1];
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        INatureType natureType = nature.getDescription().getType();
        if (natureType != null) {
          value[0] = natureType.getName();
        }
      }

      public void accept(IMotivation motivation) {
        if (motivation.getDescription().getText() != null) {
          value[0] = "Overview.Motivation.Selected"; //$NON-NLS-1$
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
    return template.getPresentationProperties().getCasteResourceBase() + casteType.getId();
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}