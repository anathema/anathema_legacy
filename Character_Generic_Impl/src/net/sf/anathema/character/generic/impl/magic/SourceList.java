package net.sf.anathema.character.generic.impl.magic;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public class SourceList implements ISourceList {

  private final Map<IExaltedEdition, IExaltedSourceBook> sourcesByEdition = new LinkedHashMap<IExaltedEdition, IExaltedSourceBook>();
  private IExaltedSourceBook primarySource;

  public IExaltedSourceBook getPrimarySource() {
    return primarySource;
  }

  public IExaltedSourceBook getSource(IExaltedEdition edition) {
    final IExaltedSourceBook magicSource = sourcesByEdition.get(edition);
    if (magicSource == null) {
      return primarySource;
    }
    return magicSource;
  }

  public void addSource(IExaltedSourceBook source) {
    if (primarySource == null) {
      this.primarySource = source;
    }
    if (source.getEdition() != null) {
      sourcesByEdition.put(source.getEdition(), source);
    }
  }
}