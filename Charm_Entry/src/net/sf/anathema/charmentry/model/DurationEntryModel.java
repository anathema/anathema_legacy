package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.duration.UntilEventDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class DurationEntryModel implements IDurationEntryModel {

  private final IConfigurableCharmData charmData;
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private String text;
  private String amount;

  public DurationEntryModel(final ICharmTypeEntryModel typeModel, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    typeModel.addModelListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        if (typeModel.getCharmType() == CharmType.Permanent) {
          setSimpleDuration("Permanent"); //$NON-NLS-1$
        }
      }
    });
  }

  @Override
  public void addModelListener(IChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public void setUntilDuration(String newValue) {
    if (StringUtilities.isNullOrTrimmedEmpty(newValue)) {
      clearDuration();
      return;
    }
    charmData.setDuration(new UntilEventDuration(newValue));
    fireChangeEvent();
  }

  private void fireChangeEvent() {
    control.announce().changeOccurred();
  }

  @Override
  public void setSimpleDuration(String newValue) {
    if (StringUtilities.isNullOrTrimmedEmpty(newValue)) {
      clearDuration();
      return;
    }
    charmData.setDuration(SimpleDuration.getDuration(newValue));
    fireChangeEvent();
  }

  @Override
  public boolean isDurationComplete() {
    return charmData.getDuration() != null;
  }

  @Override
  public void clearDuration() {
    charmData.setDuration(null);
    amount = null;
    fireChangeEvent();
  }

  @Override
  public void setValueForAmountDuration(int newValue) {
    this.amount = String.valueOf(newValue);
    setQualifiedAmountDuration();
  }

  @Override
  public void setTraitForAmountDuration(ITraitType newValue) {
    this.amount = newValue.getId();
    setQualifiedAmountDuration();
  }

  @Override
  public void setTextForAmountDuration(String newValue) {
    this.text = newValue;
    setQualifiedAmountDuration();
  }

  private void setQualifiedAmountDuration() {
    if (amount != null && !StringUtilities.isNullOrTrimmedEmpty(text)) {
      charmData.setDuration(new QualifiedAmountDuration(amount, text));
    }
    fireChangeEvent();
  }

}