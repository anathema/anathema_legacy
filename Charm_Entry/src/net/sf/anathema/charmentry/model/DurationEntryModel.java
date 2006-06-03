package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DurationEntryModel implements IDurationEntryModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public DurationEntryModel(final ICharmTypeEntryModel typeModel, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    typeModel.addModelListener(new IChangeListener() {
      public void changeOccured() {
        if (typeModel.getCharmType() == CharmType.Permanent) {
          setDuration("Permanent"); //$NON-NLS-1$
        }
      }
    });
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public String[] getDurations() {
    return new String[] { "Instant" }; //$NON-NLS-1$
  }

  public void setDuration(String newValue) {
    charmData.setDuration(SimpleDuration.getDuration(newValue));
    control.fireChangedEvent();
  }

  public IDuration getDuration() {
    return charmData.getDuration();
  }
}