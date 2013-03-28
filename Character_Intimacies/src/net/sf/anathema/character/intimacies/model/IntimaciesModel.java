package net.sf.anathema.character.intimacies.model;

import com.google.common.base.Strings;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class IntimaciesModel extends AbstractRemovableEntryModel<IIntimacy> implements IIntimaciesModel {

  private final Announcer<IChangeListener> changeControl = Announcer.to(IChangeListener.class);
  private final ICharacterModelContext context;
  private String name;

  public IntimaciesModel(ICharacterModelContext context) {
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
        fireModelChangedEvent();
        fireEntryChanged();
      }
    };
    maximumListener.addTraitTypes(VirtueType.Compassion, OtherTraitType.Willpower);
    context.getCharacterListening().addChangeListener(convictionListener);
    context.getCharacterListening().addChangeListener(maximumListener);
  }

  @Override
  public int getFreeIntimacies() {
    if (context.getAdditionalRules().isRevisedIntimacies()) {
      return getCompassionValue() + context.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
    }
    else {
      return getCompassionValue();
    }
  }

  protected int getCompassionValue() {
    return context.getTraitCollection().getTrait(VirtueType.Compassion).getCurrentValue();
  }

  @Override
  public void setCurrentName(String name) {
    this.name = name;
    fireEntryChanged();
  }

  @Override
  protected IIntimacy createEntry() {
    Intimacy intimacy = new Intimacy(name, getIntialValue(), getConviction(), context.getTraitContext());
    intimacy.setComplete(!context.getBasicCharacterContext().isExperienced());
    intimacy.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        fireModelChangedEvent();
      }      
    });
    return intimacy;
  }

  private void fireModelChangedEvent() {
    changeControl.announce().changeOccurred();
  }

  @Override
  public int getCompletionValue() {
    return 5;
  }

  private IGenericTrait getConviction() {
    return context.getTraitCollection().getTrait(VirtueType.Conviction);
  }

  @Override
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
    return getEntries().size() < getIntimaciesLimit() && !Strings.isNullOrEmpty(name);
  }

  @Override
  public void addModelChangeListener(IChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public boolean isCharacterExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }
}