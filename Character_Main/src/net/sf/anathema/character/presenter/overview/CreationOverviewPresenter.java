package net.sf.anathema.character.presenter.overview;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
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

  private IValueView<String> totalView;
  private IValueView<String> favoredCharmView;
  private IValueView<String> defaultCharmView;
  private IValueView<String> casteView;
  private IValueView<String> natureView;
  private IValueView<String> miscView;
  private ILabelledAlotmentView primaryAttributeView;
  private ILabelledAlotmentView secondaryAttributeView;
  private ILabelledAlotmentView tertiaryAttributeView;

  public CreationOverviewPresenter(
      IResources resources,
      ICharacterStatistics statistics,
      IOverviewView bonusPointView,
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
    this.view = bonusPointView;
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
      miscView = category.addStringValueView(getString("Overview.MiscPointsCategory")); //$NON-NLS-1$
    }
    totalView = category.addStringValueView(getString("Overview.BonusPointsCategory")); //$NON-NLS-1$
  }

  private void initConcept() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Concept.Title")); //$NON-NLS-1$
    if (!template.getCasteCollection().isEmpty()) {
      casteView = category.addStringValueView(getString(template.getPresentationProperties().getCasteLabelResource()));
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
    natureView = category.addStringValueView(getString(resourcekey[0]));
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
    favoredCharmView = category.addStringValueView(getString("Overview.FavoredCharmCategory")); //$NON-NLS-1$
    defaultCharmView = category.addStringValueView(getString("Overview.GeneralCharmCategory")); //$NON-NLS-1$
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
    primaryAttributeView = category.addAlotmentView(getString("Overview.PrimaryAttributeCategory"), 2); //$NON-NLS-1$
    secondaryAttributeView = category.addAlotmentView(getString("Overview.SecondaryAttributeCategory"), 2); //$NON-NLS-1$
    tertiaryAttributeView = category.addAlotmentView(getString("Overview.TertiaryAttributeCategory"), 2); //$NON-NLS-1$
  }

  private void updateOverview() {
    this.management.recalculate();
    updateCaste();
    updateWillpowerRegainingConcept();
    updateAttributes();
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
    updateCharms();
    updateMiscView();
    updateTotalView();
  }

  private void updateMiscView() {
    if (miscView == null) {
      return;
    }
    miscView.setValue(String.valueOf(management.getAdditionalModelTotalValue()));
  }

  private ICreationPoints getCreationPoints() {
    return template.getCreationPoints();
  }

  private void updateCharms() {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    int favoredSpent = management.getFavoredCharmPicksSpent();
    int favoredTotal = template.getCreationPoints().getFavoredCreationCharmCount();
    favoredCharmView.setValue(favoredSpent + " / " //$NON-NLS-1$
        + favoredTotal);
    setFontParameters(favoredCharmView, favoredSpent, favoredTotal, 0);
    IAdditionalRules additionalRules = template.getAdditionalRules();
    int defaultSpent = management.getDefaultCharmPicksSpent();
    int defaultTotal = template.getCreationPoints().getDefaultCreationCharmCount();
    if (additionalRules == null || additionalRules.getAdditionalMagicLearnPools().length == 0) {
      defaultCharmView.setValue(defaultSpent + " / " + defaultTotal); //$NON-NLS-1$
    }
    else {
      defaultCharmView.setValue(defaultSpent + "+" //$NON-NLS-1$
          + management.getAdditionalMagicPointsSpent()
          + " / " //$NON-NLS-1$
          + defaultTotal
          + "+" //$NON-NLS-1$
          + management.getAdditionalMagicPointsAmount());

    }
    setFontParameters(defaultCharmView, defaultSpent, defaultTotal, management.getCharmBonusPointsSpent()
        + management.getSpellBonusPointsSpent());
  }

  private void updateAttributes() {
    updateAttributeGroup(primaryAttributeView, AttributeGroupPriority.Primary);
    updateAttributeGroup(secondaryAttributeView, AttributeGroupPriority.Secondary);
    updateAttributeGroup(tertiaryAttributeView, AttributeGroupPriority.Tertiary);
  }

  private void updateAttributeGroup(ILabelledAlotmentView alotmentView, AttributeGroupPriority priority) {
    updateView(
        alotmentView,
        management.getAttributeDotsSpent(priority),
        getCreationPoints().getAttributeCreationPoints().getCount(priority),
        management.getAttributeBonusPointsSpent(priority));
  }

  private void updateWillpowerRegainingConcept() {
    String natureValue = getWillpowerRegainingConceptValue();
    natureView.setValue(natureValue == null ? "" : natureValue); //$NON-NLS-1$
    natureView.setTextColor(natureValue == null ? LegalityColorProvider.COLOR_LOW : LegalityColorProvider.COLOR_OKAY);
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
          value[0] = resources.getString("Overview.Motivation.Selected"); //$NON-NLS-1$
        }
      }
    });
    return value[0];
  }

  private void updateCaste() {
    if (template.getCasteCollection().isEmpty()) {
      return;
    }
    String resourceKey = getCasteValueResourceKey();
    casteView.setValue(resourceKey == null ? "" : resources.getString(resourceKey)); //$NON-NLS-1$
    casteView.setTextColor(resourceKey == null ? LegalityColorProvider.COLOR_LOW : LegalityColorProvider.COLOR_OKAY);
  }

  private String getCasteValueResourceKey() {
    ICasteType casteType = statistics.getCharacterConcept().getCaste().getType();
    if (casteType.equals(ICasteType.NULL_CASTE_TYPE)) {
      return null;
    }
    return template.getPresentationProperties().getCasteResourceBase() + casteType.getId();
  }

  private void updateTotalView() {
    int bonusPointCount = getCreationPoints().getBonusPointCount() + management.getAdditionalGeneralBonusPoints();
    IAdditionalRules additionalRules = template.getAdditionalRules();
    if (additionalRules == null || additionalRules.getAdditionalBonusPointPools().length == 0) {
      totalView.setValue(management.getTotalBonusPointsSpent() + " / " + bonusPointCount); //$NON-NLS-1$
    }
    else {
      totalView.setValue(management.getStandardBonusPointsSpent() + "+" //$NON-NLS-1$
          + management.getAdditionalBonusPointSpent()
          + " / " //$NON-NLS-1$
          + bonusPointCount
          + "+" //$NON-NLS-1$
          + management.getAdditionalBonusPointAmount());
    }
    int pointsSpent = management.getStandardBonusPointsSpent();
    boolean overspent = pointsSpent > bonusPointCount;
    totalView.setTextColor(overspent ? LegalityColorProvider.COLOR_HIGH : LegalityColorProvider.COLOR_OKAY);
  }

  private String getString(String string) {
    return resources.getString(string);
  }

  private void updateView(final ILabelledAlotmentView alotmentView, int value, int alotment, int bonusPointsSpent) {
    alotmentView.setValue(value);
    alotmentView.setAlotment(alotment);
    setFontParameters(alotmentView, value, alotment, bonusPointsSpent);
  }

  private void setFontParameters(final IValueView valueView, int value, int alotment, int bonusPointsSpent) {
    LegalityFontProvider legalityFontProvider = new LegalityFontProvider();
    ValueLegalityState fontStyleState = bonusPointsSpent > 0 ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    valueView.setFontStyle(legalityFontProvider.getFontStyle(fontStyleState));
    LegalityColorProvider legalityColorProvider = new LegalityColorProvider();
    ValueLegalityState textColorState = null;
    if (value < alotment) {
      textColorState = ValueLegalityState.Lowered;
    }
    if (value == alotment) {
      textColorState = ValueLegalityState.Okay;
    }
    if (value > alotment) {
      textColorState = ValueLegalityState.Increased;
    }
    valueView.setTextColor(legalityColorProvider.getTextColor(textColorState));
  }
}