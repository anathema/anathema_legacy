package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.general.IMagicSource;

public interface IConfigurableMagicSource extends IMagicSource {

  public void setSource(String newSource);

  public void setPage(Integer newPage);
}