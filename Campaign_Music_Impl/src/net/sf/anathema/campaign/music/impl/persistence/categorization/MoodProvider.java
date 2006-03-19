package net.sf.anathema.campaign.music.impl.persistence.categorization;

import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.MusicMood;

public class MoodProvider extends AbstractProvider<IMusicMood> {

  public static IMusicMood[] createDefaultFeelings() {
    return new IMusicMood[] { new MusicMood("Aggressive"), //$NON-NLS-1$
        new MusicMood("Brash"), //$NON-NLS-1$
        new MusicMood("Cheerful"), //$NON-NLS-1$
        new MusicMood("Ceremonial"), //$NON-NLS-1$
        new MusicMood("Excited"), //$NON-NLS-1$
        new MusicMood("Dark"), //$NON-NLS-1$
        new MusicMood("Epic"), //$NON-NLS-1$
        new MusicMood("Genteel"), //$NON-NLS-1$ Affektiert
        new MusicMood("Happy"), //$NON-NLS-1$
        new MusicMood("Heroic"), //$NON-NLS-1$
        new MusicMood("Light-Hearted"), //$NON-NLS-1$
        new MusicMood("Melancholic"), //$NON-NLS-1$
        new MusicMood("Mellow"), //$NON-NLS-1$
        new MusicMood("Pathetic"), //$NON-NLS-1$
        new MusicMood("Respectful"), //$NON-NLS-1$
        new MusicMood("Romantic"), //$NON-NLS-1$
        new MusicMood("Sad"), //$NON-NLS-1$
        new MusicMood("Silent"), //$NON-NLS-1$
        new MusicMood("Solemn"), //$NON-NLS-1$
        new MusicMood("Tragic"), //$NON-NLS-1$
        new MusicMood("Violent"), //$NON-NLS-1$
        new MusicMood("Wrath") }; //$NON-NLS-1$
  }

  public MoodProvider(MusicDatabasePersister dataBasePersister) {
    super(dataBasePersister, IMusicMood.class);
  }

  @Override
  protected IMusicMood[] createDefaultValues() {
    return createDefaultFeelings();
  }
}