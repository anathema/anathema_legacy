package net.sf.anathema.charmentry.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.presenter.ISimpleSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.ITraitSelectionChangedListener;
import net.sf.anathema.charmentry.util.CharmUtilities;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.booleanvalue.BooleanValueControl;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class CharmEntryModel implements ISimpleSpecialsArbitrator, IReflexiveSpecialsArbitrator {

  private ConfigurableCharmData charmData = new ConfigurableCharmData();
  private final CharacterType[] legalCharacterTypes = createLegalCharacterTypeArray();
  private final GenericControl<ITraitSelectionChangedListener> control = new GenericControl<ITraitSelectionChangedListener>();
  private final ChangeControl modelControl = new ChangeControl();
  private final IntValueControl essenceControl = new IntValueControl();
  private final ObjectValueControl<String> groupControl = new ObjectValueControl<String>();
  private final BooleanValueControl completionControl = new BooleanValueControl();
  private final PrimaryPrerequisiteLegalityChecker checker = new PrimaryPrerequisiteLegalityChecker();
  private IKeywordEntryModel keywordModel = new KeywordEntryModel();
  private final SimpleSpecialsEntryModel simpleCharmSpecials = new SimpleSpecialsEntryModel(this);
  private final ReflexiveSpecialsEntryModel reflexiveCharmSpecials = new ReflexiveSpecialsEntryModel(this);

  private CharacterType[] createLegalCharacterTypeArray() {
    List<CharacterType> legalTypes = new ArrayList<CharacterType>();
    Collections.addAll(legalTypes, CharacterType.getAllExaltTypes());
    legalTypes.remove(CharacterType.SIDEREAL);
    return legalTypes.toArray(new CharacterType[legalTypes.size()]);
  }

  public IConfigurableCharmData getCharmData() {
    return charmData;
  }

  public void setCharmName(String name) {
    charmData.setName(name);
    setCharmId();
    checkCompletion();
  }

  private void checkCompletion() {
    if (StringUtilities.isNullOrEmpty(charmData.getName())) {
      completionControl.fireValueChangedEvent(false);
      return;
    }
    if (charmData.getCharacterType() == null) {
      completionControl.fireValueChangedEvent(false);
      return;
    }
    IGenericTrait primaryPrerequiste = charmData.getPrimaryPrerequiste();
    if (primaryPrerequiste == null || primaryPrerequiste.getType() == null) {
      completionControl.fireValueChangedEvent(false);
      return;
    }
    if (charmData.getCharmTypeModel().getCharmType() == null) {
      completionControl.fireValueChangedEvent(false);
      return;
    }
    if (charmData.getDuration() == null) {
      completionControl.fireValueChangedEvent(false);
      return;
    }
    completionControl.fireValueChangedEvent(true);
  }

  public void clearCharmData() {
    charmData = new ConfigurableCharmData();
    keywordModel.clear();
    // TODO!
  }

  public void setCharacterType(CharacterType type) {
    if (!ArrayUtilities.contains(getLegalCharacterTypes(), type)) {
      return;
    }
    charmData.setCharacterType(type);
    charmData.setGroupId(""); //$NON-NLS-1$
    setCharmId();
    checkPrimaryPrerequisite();
    checkCompletion();
    modelControl.fireChangedEvent();
  }

  private void checkPrimaryPrerequisite() {
    if (!checker.isLegalPrimaryPrerequisite(charmData.getCharacterType(), getPrimaryPrerequisite())) {
      changePrimaryPrerequisiteWithoutChecking(ValuedTraitType.NULL_TYPE);
    }
  }

  private void changePrimaryPrerequisiteWithoutChecking(IGenericTrait prerequisite) {
    if (charmData.getCharacterType() != CharacterType.LUNAR && prerequisite.getType() != null) {
      setCharmGroup(prerequisite.getType().getId());
    }
    charmData.setPrimaryPrerequisite(prerequisite);
    firePrimaryPrerequisiteChanged(prerequisite);
    checkCompletion();
  }

  private void setCharmId() {
    if (charmData.getCharacterType() == null || charmData.getName() == null) {
      return;
    }
    charmData.setId(CharmUtilities.createIDFromName(charmData.getCharacterType(), charmData.getName()));
  }

  public CharacterType[] getLegalCharacterTypes() {
    return legalCharacterTypes;
  }

  public void setCharmType(CharmType type) {
    final CharmTypeModel charmTypeModel = charmData.getCharmTypeModel();
    charmTypeModel.setCharmType(type);
    if (isSimpleSpecialsAvailable()) {
      charmTypeModel.setSpecialModel(simpleCharmSpecials);
    }
    else if (isReflexiveSpecialsAvailable()) {
      charmTypeModel.setSpecialModel(reflexiveCharmSpecials);
    }
    else {
      charmTypeModel.setSpecialModel(null);
    }
    checkCompletion();
    modelControl.fireChangedEvent();
  }

  public void setDuration(Duration duration) {
    charmData.setDuration(duration);
  }

  public void setPrerequisite(IGenericTrait prerequisite) {
    ITraitType type = prerequisite.getType();
    if (type == null) {
      return;
    }
    if (type == OtherTraitType.Essence) {
      if (prerequisite.getCurrentValue() != charmData.getEssence().getCurrentValue()) {
        charmData.setEssencePrerequisite(prerequisite);
        fireEssencePrerequisiteChanged();
      }
      return;
    }
    charmData.addPrerequisite(prerequisite);
  }

  private void fireEssencePrerequisiteChanged() {
    essenceControl.fireValueChangedEvent(charmData.getEssence().getCurrentValue());
  }

  public void setPrimaryPrerequisite(final IGenericTrait prerequisite) {
    if (prerequisite.equals(getPrimaryPrerequisite())) {
      return;
    }
    CharacterType characterType = charmData.getCharacterType();
    boolean legalPrerequisite = checker.isLegalPrimaryPrerequisite(characterType, prerequisite);
    if (!legalPrerequisite) {
      return;
    }
    changePrimaryPrerequisiteWithoutChecking(prerequisite);
    checkCompletion();
  }

  public IGenericTrait getPrimaryPrerequisite() {
    if (charmData.getPrimaryPrerequisiteType() == null) {
      return ValuedTraitType.NULL_TYPE;
    }
    return charmData.getPrerequisiteByType(charmData.getPrimaryPrerequisiteType());
  }

  private void firePrimaryPrerequisiteChanged(final IGenericTrait prerequisite) {
    control.forAllDo(new IClosure<ITraitSelectionChangedListener>() {
      public void execute(ITraitSelectionChangedListener input) {
        input.selectionChanged(prerequisite.getType(), prerequisite.getCurrentValue());
      }
    });
  }

  public void removePrerequisite(IGenericTrait prerequisite) {
    if (prerequisite.getType() == OtherTraitType.Essence) {
      throw new IllegalArgumentException("Must not remove Essence prerequisite."); //$NON-NLS-1$
    }
    if (prerequisite.getType() == charmData.getPrimaryPrerequisiteType()) {
      throw new IllegalArgumentException("Must not remove primary prerequisite."); //$NON-NLS-1$
    }
    charmData.removePrerequisite(prerequisite);
  }

  public ITraitType[] getLegalPrimaryPrerequisiteTypes() {
    if (charmData.getCharacterType() == CharacterType.LUNAR) {
      return AttributeType.values();
    }
    return AbilityType.getAbilityTypes(ExaltedRuleSet.CoreRules);
  }

  public void addPrimaryPrerequisiteListener(ITraitSelectionChangedListener listener) {
    control.addListener(listener);
  }

  public void addEssencePrerequisiteListener(IIntValueChangedListener listener) {
    essenceControl.addIntValueChangeListener(listener);
  }

  public void setSource(String book, Integer page) {
    charmData.getSource().setSource(book);
    charmData.getSource().setPage(page);
  }

  public void setSourceBook(String book) {
    charmData.getSource().setSource(book);
  }

  public void setSourcePage(Integer newValue) {
    charmData.getSource().setPage(newValue);
  }

  public void setEssenceCostValue(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setValue(newValue);
  }

  public void setEssenceCostText(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setText(newValue);
  }

  public void setWillpowerCostValue(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setValue(newValue);
  }

  public void setWillpowerCostText(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setText(newValue);
  }

  public void setHealthCostValue(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setValue(newValue);
  }

  public void setHealthCostText(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setText(newValue);
  }

  public void setXpCostValue(String newValue) {
    charmData.getPermanentCost().getXPCost().setValue(newValue);
  }

  public void setXpCostText(String newValue) {
    charmData.getPermanentCost().getXPCost().setText(newValue);
  }

  public void setPrerequisiteCharms(ICharm[] charms) {
    charmData.setParentCharms(charms);
  }

  public void addCharmCompleteListener(IBooleanValueChangedListener listener) {
    completionControl.addValueChangeListener(listener);
  }

  public void setCharmGroup(String newValue) {
    charmData.setGroupId(newValue);
    fireGroupChanged();
  }

  private void fireGroupChanged() {
    groupControl.fireValueChangedEvent(charmData.getGroupId());
  }

  public void addCharmGroupIdListener(IObjectValueChangedListener<String> listener) {
    groupControl.addObjectValueChangeListener(listener);
  }

  public void setEdition(ExaltedEdition edition) {
    charmData.setEdition(edition);
    modelControl.fireChangedEvent();
  }

  public IKeywordEntryModel getKeywordModel() {
    return keywordModel;
  }

  public boolean isReflexiveSpecialsAvailable() {
    return charmData.getEdition() == ExaltedEdition.SecondEdition
        && charmData.getCharmTypeModel().getCharmType() == CharmType.Reflexive;
  }

  public boolean isSimpleSpecialsAvailable() {
    return charmData.getEdition() == ExaltedEdition.SecondEdition
        && charmData.getCharmTypeModel().getCharmType() == CharmType.Simple;
  }

  public ISimpleSpecialsEntryModel getSimpleCharmSpecialsModel() {
    return simpleCharmSpecials;
  }

  public IReflexiveSpecialsEntryModel getReflexiveCharmSpecialsModel() {
    return reflexiveCharmSpecials;
  }

  public void addModelChangeListener(IChangeListener listener) {
    modelControl.addChangeListener(listener);
  }

  public void setRequiresExcellency(boolean required) {
    charmData.setExcellencyRequired(required);

  }
}