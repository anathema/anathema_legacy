package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISourceList {

  IExaltedSourceBook getPrimarySource();

  boolean isEmpty();
}