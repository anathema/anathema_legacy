package net.sf.anathema.character.main.magic.general;

import net.sf.anathema.character.main.rules.IExaltedSourceBook;

public interface ISourceList {

  IExaltedSourceBook getPrimarySource();

  boolean isEmpty();
}