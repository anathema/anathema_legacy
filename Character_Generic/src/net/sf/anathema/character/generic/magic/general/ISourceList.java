package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISourceList {

  public IExaltedSourceBook getPrimarySource();

  public IExaltedSourceBook getSource(IExaltedEdition edition);
}