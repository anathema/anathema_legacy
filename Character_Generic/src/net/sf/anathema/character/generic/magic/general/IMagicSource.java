package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface IMagicSource {
  public static final String CUSTOM_SOURCE_NAME = "Custom"; //$NON-NLS-1$

  public String getSource();

  public String getPage();

  public IExaltedEdition getEdition();
}