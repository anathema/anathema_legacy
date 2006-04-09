package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.nature.INatureType;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class CreationOverviewPresenter {

  private final IOverviewView view;
  private final ICharacterStatistics statistics;
  private final IBonusPointManagement management;

  private ILabelledAlotmentView virtueView;
  private IValueView<String> totalView;
  private ILabelledAlotmentView primaryAttributeView;
  private ILabelledAlotmentView secondaryAttributeView;
  private ILabelledAlotmentView tertiaryAttributeView;
  private ICharacterTemplate template;
  private ILabelledAlotmentView favoredAbilityPickView;
  private ILabelledAlotmentView favoredAbiltyDotView;
  private ILabelledAlotmentView defaultAbilityDotView;
  private ILabelledAlotmentView backgroundView;
  private IValueView<String> favoredCharmView;
  private IValueView<String> defaultCharmView;
  private IValueView<String> casteView;
  private IValueView<String> natureView;
  private final IResources resources;
  private IValueView<String> miscView;

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
    natureView = category.addStringValueView(getString("CharacterConcept.Nature")); //$NON-NLS-1$
  }

  private void initAdvantages() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Advantages.Title")); //$NON-NLS-1$
    virtueView = category.addAlotmentView(getString("Overview.VirtueCategory"), 2); //$NON-NLS-1$
    backgroundView = category.addAlotmentView(getString("Overview.BackgroundCategory"), 2); //$NON-NLS-1$
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
    favoredAbilityPickView = category.addAlotmentView(getString("Overview.FavoredAbilityCategory"), 2);//$NON-NLS-1$
    favoredAbiltyDotView = category.addAlotmentView(getString("Overview.FavoredAbilityDotCategory"), 2); //$NON-NLS-1$
    defaultAbilityDotView = category.addAlotmentView(getString("Overview.GeneralAbilityDotCategory"), 2); //$NON-NLS-1$
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
    updateNature();
    updateAttributes();
    updateAbilities();
    updateView(
        virtueView,
        management.getVirtueDotsSpent(),
        getCreationPoints().getVirtueCreationPoints(),
        management.getVirtueBonusPointsSpent());
    updateView(
        backgroundView,
        management.getBackgroundDotsSpent(),
        getCreationPoints().getBackgroundPointCount(),
        management.getBackgroundBonusPointsSpent());
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

  private void updateAbilities() {
    IFavorableTraitCreationPoints creationPoints = getCreationPoints().getAbilityCreationPoints();
    updateView(
        favoredAbilityPickView,
        management.getFavoredAbilityPicksSpent(),
        creationPoints.getFavorableTraitCount(),
        0);
    updateView(favoredAbiltyDotView, management.getFavoredAbilityDotsSpent(), creationPoints.getFavoredDotCount(), 0);
    updateView(
        defaultAbilityDotView,
        management.getDefaultAbilityDotsSpent(),
        creationPoints.getDefaultDotCount(),
        management.getAbilityBonusPointCosts());
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

  private void updateNature() {
    String natureValue = getNatureValue();
    natureView.setValue(natureValue == null ? "" : natureValue); //$NON-NLS-1$
    natureView.setTextColor(natureValue == null ? LegalityColorProvider.COLOR_LOW : LegalityColorProvider.COLOR_OKAY);
  }

  private String getNatureValue() {
    INatureType natureType = statistics.getCharacterConcept().getNature().getType();
    if (natureType == null) {
      return null;
    }
    return natureType.getName();
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