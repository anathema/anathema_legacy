package net.sf.anathema.character.intimacies.model;

import javax.swing.event.ChangeListener;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.lib.control.ChangeControl;

public class IntimaciesModel extends AbstractRemovableEntryModel<IIntimacy> implements
    IAdditionalModel,
    IIntimaciesModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ChangeControl bonusPointControl = new ChangeControl(this);
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
      }
    };
    convictionListener.addTraitType(VirtueType.Conviction);
    VirtueChangeListener compassionListener = new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        // TODO Auto-generated method stub
      }
    };
    compassionListener.addTraitType(VirtueType.Compassion);
    context.getCharacterListening().addChangeListener(convictionListener);
    context.getCharacterListening().addChangeListener(compassionListener);
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
    return context.getTraitCollection().getTrait(VirtueType.Compassion).getCurrentValue();
  }

  public void setCurrentName(String name) {
    this.name = name;
    fireEntryComplete();
  }

  @Override
  protected IIntimacy createEntry() {
    Intimacy intimacy = new Intimacy(name, getIntialValue(), context.getTraitCollection().getTrait(
        VirtueType.Conviction), context.getTraitContext());
    return intimacy;
  }

  private Integer getIntialValue() {
    if (context.getBasicCharacterContext().isExperienced()) {
      return 0;
    }
    return context.getTraitCollection().getTrait(VirtueType.Conviction).getCurrentValue();
  }

  @Override
  protected boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(name);
  }
}