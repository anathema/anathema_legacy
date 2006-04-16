package net.sf.anathema.character.generic.magic;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface IMartialArtsCharm extends ICharm {
  public static final IIdentificate FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$
  public static final String ALLOWS_CELESTIAL_ATTRIBUTE = "AllowsCelestial"; //$NON-NLS-1$
  public static final IIdentificate UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  public static final IIdentificate NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$

}