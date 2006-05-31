package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ISourceList {

  public IMagicSource getPrimarySource();

  public IMagicSource getSource(IExaltedEdition edition);

}
