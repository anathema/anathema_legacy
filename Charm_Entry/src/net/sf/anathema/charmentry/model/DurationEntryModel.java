package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
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
          setDuration("Permanent");
        }
      }
    });
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public String[] getDurations() {
    return new String[] { DurationType.Instant.getId() };
  }

  public void setDuration(String newValue) {
    charmData.setDuration(Duration.getDuration(newValue));
    control.fireChangedEvent();
  }

  public Duration getDuration() {
    return charmData.getDuration();
  }
}