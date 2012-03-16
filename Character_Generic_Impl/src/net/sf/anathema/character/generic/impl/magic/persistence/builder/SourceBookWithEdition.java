package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public class SourceBookWithEdition {
  public IExaltedSourceBook source;
  public IExaltedEdition edition;

  public SourceBookWithEdition(IExaltedSourceBook source, IExaltedEdition edition) {
    this.source = source;
    this.edition = edition;
  }
}