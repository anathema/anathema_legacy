package net.sf.anathema.character.intimacies.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class IntimaciesModel extends AbstractRemovableEntryModel<IIntimacy> implements
    IAdditionalModel,
    IIntimaciesModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ChangeControl bonusPointControl = new ChangeControl();
  private final ChangeControl changeControl = new ChangeControl();
  private final ICharacterModelContext context;
  private String name;

  public IntimaciesModel(
      IAdditionalTemplate additionalTemplate,
      final ICharacterModelContext context,
      IChangeListener[] listeners) {
    this.additionalTemplate = additionalTemplate;
    this.context = context;
    for (IChangeListener listener : listeners) {
      addBonusPointsChangeListener(listener);
    }
    VirtueChangeListener convictionListener = new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        for (IIntimacy entry : getEntries()) {
          entry.resetCurrentValue();
        }
        fireModelChangedEvent();
      }
    };
    convictionListener.addTraitType(VirtueType.Conviction);
    ConfigurableCharacterChangeListener maximumListener = new ConfigurableCharacterChangeListener() {
      @Override
      public void configuredChangeOccured() {
        changeControl.fireChangedEvent();
        fireEntryChanged();
      }
    };
    maximumListener.addTraitType(VirtueType.Compassion);
    maximumListener.addTraitType(OtherTraitType.Willpower);
    context.getCharacterListening().addChangeListener(convictionListener);
    context.getCharacterListening().addChangeListener(maximumListener);
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new IntimaciesBonusPointCalculator(this);
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    bonusPointControl.addChangeListener(listener);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public int getFreeIntimacies() {
    return getCompassionValue();
  }

  private int getCompassionValue() {
    return context.getTraitCollection().getTrait(VirtueType.Compassion).getCurrentValue();
  }

  public void setCurrentName(String name) {
    this.name = name;
    fireEntryChanged();
  }

  @Override
  protected IIntimacy createEntry() {
    Intimacy intimacy = new Intimacy(name, getIntialValue(), getConviction(), context.getTraitContext());
    intimacy.setComplete(!context.getBasicCharacterContext().isExperienced());
    return intimacy;
  }

  private void fireModelChangedEvent() {
    changeControl.fireChangedEvent();
  }

  public int getCompletionValue() {
    return 5;
  }

  private IGenericTrait getConviction() {
    return context.getTraitCollection().getTrait(VirtueType.Conviction);
  }

  public int getIntimaciesLimit() {
    return getCompassionValue() + context.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
  }

  private Integer getIntialValue() {
    if (context.getBasicCharacterContext().isExperienced()) {
      return 0;
    }
    return getConviction().getCurrentValue();
  }

  @Override
  protected boolean isEntryAllowed() {
    return getEntries().size() < getIntimaciesLimit() && !StringUtilities.isNullOrEmpty(name);
  }

  public void addModelChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public IIntimacy commitSelection() {
    IIntimacy intimacy = super.commitSelection();
    bonusPointControl.fireChangedEvent();
    return intimacy;
  }

  @Override
  public void removeEntry(IIntimacy entry) {
    super.removeEntry(entry);
    bonusPointControl.fireChangedEvent();
  }
}