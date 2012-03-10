package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

import java.util.LinkedHashMap;
import java.util.Map;

public class SourceList implements ISourceList {

  private final Map<IExaltedEdition, IExaltedSourceBook> sourcesByEdition = new LinkedHashMap<IExaltedEdition, IExaltedSourceBook>();
  private IExaltedSourceBook primarySource;

  @Override
  public IExaltedSourceBook getPrimarySource() {
    return primarySource;
  }

  @Override
  public boolean hasSource(IExaltedEdition edition) {
    IExaltedSourceBook magicSource = sourcesByEdition.get(edition);
    return magicSource != null;
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