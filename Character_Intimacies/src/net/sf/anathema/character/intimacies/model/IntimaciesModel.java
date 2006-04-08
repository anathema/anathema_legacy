package net.sf.anathema.character.intimacies.model;

import javax.swing.event.ChangeListener;

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
import net.sf.anathema.lib.control.ChangeControl;
import net.sf.anathema.lib.control.ChangeListenerClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IChangeListener;

public class IntimaciesModel extends AbstractRemovableEntryModel<IIntimacy> implements
    IAdditionalModel,
    IIntimaciesModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ChangeControl bonusPointControl = new ChangeControl(this);
  private final GenericControl<IChangeListener> changeControl = new GenericControl<IChangeListener>();
  private final ICharacterModelContext context;
  private String name;

  public IntimaciesModel(
      IAdditionalTemplate additionalTemplate,
      final ICharacterModelContext context,
      ChangeListener[] listeners) {
    this.additionalTemplate = additionalTemplate;
    this.context = context;
    for (ChangeListener listener : listeners) {
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
        changeControl.forAllDo(new ChangeListenerClosure());
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

  public void addBonusPointsChangeListener(ChangeListener listener) {
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
    return intimacy;
  }

  private void fireModelChangedEvent() {
    changeControl.forAllDo(new ChangeListenerClosure());
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
    changeControl.addListener(listener);
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
}