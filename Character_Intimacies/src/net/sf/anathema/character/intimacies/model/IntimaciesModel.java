package net.sf.anathema.character.intimacies.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class IntimaciesModel extends AbstractRemovableEntryModel<IIntimacy> implements IIntimaciesModel {

  private final ChangeControl changeControl = new ChangeControl();
  private final ICharacterModelContext context;
  private String name;

  public IntimaciesModel(final ICharacterModelContext context) {
    this.context = context;
    VirtueChangeListener convictionListener = new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        for (IIntimacy entry : getEntries()) {
          entry.resetCurrentValue();
        }
        fireModelChangedEvent();
      }
    };
    convictionListener.addTraitTypes(VirtueType.Conviction);
    ConfigurableCharacterChangeListener maximumListener = new ConfigurableCharacterChangeListener() {
      @Override
      public void configuredChangeOccured() {
        changeControl.fireChangedEvent();
        fireEntryChanged();
      }
    };
    maximumListener.addTraitTypes(VirtueType.Compassion, OtherTraitType.Willpower);
    context.getCharacterListening().addChangeListener(convictionListener);
    context.getCharacterListening().addChangeListener(maximumListener);
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
}