package net.sf.anathema.character.main.magic.model.magic.source;

import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;

public interface ISourceList {

  IExaltedSourceBook getPrimarySource();

  boolean isEmpty();
}