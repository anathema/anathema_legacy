package net.sf.anathema.campaign.music.impl.persistence.categorization;

import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.MusicEvent;

public class EventProvider extends AbstractProvider<IMusicEvent> {

  public static IMusicEvent[] createDefaultMoods() {
    return new IMusicEvent[] { new MusicEvent("Change"), //$NON-NLS-1$
        new MusicEvent("Celebration"), //$NON-NLS-1$
        new MusicEvent("Creation"), //$NON-NLS-1$
        new MusicEvent("Cowardice"), //$NON-NLS-1$
        new MusicEvent("Defeat"), //$NON-NLS-1$
        new MusicEvent("Discovery"), //$NON-NLS-1$
        new MusicEvent("Loss"), //$NON-NLS-1$
        new MusicEvent("Profit"), //$NON-NLS-1$
        new MusicEvent("Ruin"), //$NON-NLS-1$
        new MusicEvent("Understanding"), //$NON-NLS-1$
        new MusicEvent("Victory") }; //$NON-NLS-1$
  }

  public EventProvider(MusicDatabasePersister dataBasePersister) {
    super(dataBasePersister, IMusicEvent.class);
  }

  @Override
  protected IMusicEvent[] createDefaultValues() {
    return createDefaultMoods();
  }
}