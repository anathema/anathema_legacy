package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISourceList {

  IExaltedSourceBook getPrimarySource();

  IExaltedSourceBook getSource(IExaltedEdition edition);
}