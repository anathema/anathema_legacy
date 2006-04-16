package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface IMartialArtsCharm extends ICharm {
  public static final IIdentificate FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$

  public MartialArtsLevel getLevel();
}