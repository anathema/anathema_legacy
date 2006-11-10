package net.sf.anathema.campaign.music.impl.persistence.categorization;

import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.campaign.music.presenter.MusicTheme;

public class ThemeProvider extends AbstractProvider<IMusicTheme> {

  // Friedship, Authority, Family, Tradition, Innovation, Responsibilty, Faith, Power, Duty, Personality
  public static IMusicTheme[] createDefaultThemes() {
    return new IMusicTheme[] { new MusicTheme("Action"), //$NON-NLS-1$
        new MusicTheme("Adventure"),//$NON-NLS-1$
        new MusicTheme("Battle"),//$NON-NLS-1$
        new MusicTheme("Combat"),//$NON-NLS-1$
        new MusicTheme("Exploration"),//$NON-NLS-1$
        new MusicTheme("Entropy"),//$NON-NLS-1$
        new MusicTheme("Knowledge"),//$NON-NLS-1$
        new MusicTheme("Infiltration"),//$NON-NLS-1$
        new MusicTheme("Intrigue"),//$NON-NLS-1$
        new MusicTheme("Investigation"),//$NON-NLS-1$
        new MusicTheme("Mystery"),//$NON-NLS-1$
        new MusicTheme("Occult"),//$NON-NLS-1$
        new MusicTheme("Philosophy"),//$NON-NLS-1$
        new MusicTheme("Rebellion"),//$NON-NLS-1$
        new MusicTheme("Religion"),//$NON-NLS-1$
        new MusicTheme("Romance"),//$NON-NLS-1$
        new MusicTheme("Society"),//$NON-NLS-1$
        new MusicTheme("War"), //$NON-NLS-1$
        new MusicTheme("Wisdom") };//$NON-NLS-1$
  }

  public ThemeProvider(MusicDatabasePersister dataBasePersister) {
    super(dataBasePersister, IMusicTheme.class);
  }

  @Override
  protected IMusicTheme[] createDefaultValues() {
    return createDefaultThemes();
  }
}