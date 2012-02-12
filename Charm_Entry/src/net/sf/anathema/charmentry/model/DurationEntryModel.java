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
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DurationEntryModel implements IDurationEntryModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();
  private String text;
  private String amount;

  public DurationEntryModel(final ICharmTypeEntryModel typeModel, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    typeModel.addModelListener(new IChangeListener() {
      public void changeOccurred() {
        if (typeModel.getCharmType() == CharmType.Permanent) {
          setSimpleDuration("Permanent"); //$NON-NLS-1$
        }
      }
    });
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void setUntilDuration(String newValue) {
    if (StringUtilities.isNullOrTrimmedEmpty(newValue)) {
      clearDuration();
      return;
    }
    charmData.setDuration(new UntilEventDuration(newValue));
    control.fireChangedEvent();
  }

  public void setSimpleDuration(String newValue) {
    if (StringUtilities.isNullOrTrimmedEmpty(newValue)) {
      clearDuration();
      return;
    }
    charmData.setDuration(SimpleDuration.getDuration(newValue));
    control.fireChangedEvent();
  }

  public boolean isDurationComplete() {
    return charmData.getDuration() != null;
  }

  public void clearDuration() {
    charmData.setDuration(null);
    amount = null;
    control.fireChangedEvent();
  }

  public void setValueForAmountDuration(int newValue) {
    this.amount = String.valueOf(newValue);
    setQualifiedAmountDuration();
  }

  public void setTraitForAmountDuration(ITraitType newValue) {
    this.amount = newValue.getId();
    setQualifiedAmountDuration();
  }

  public void setTextForAmountDuration(String newValue) {
    this.text = newValue;
    setQualifiedAmountDuration();
  }

  private void setQualifiedAmountDuration() {
    if (amount != null && !StringUtilities.isNullOrTrimmedEmpty(text)) {
      charmData.setDuration(new QualifiedAmountDuration(amount, text));
    }
    control.fireChangedEvent();
  }

}