package net.sf.anathema.character.generic.impl.magic;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class SourceList implements ISourceList {

  private final Map<IExaltedEdition, IMagicSource> sourcesByEdition = new LinkedHashMap<IExaltedEdition, IMagicSource>();
  private IMagicSource primarySource;

  public IMagicSource getPrimarySource() {
    return primarySource;
  }

  public IMagicSource getSource(IExaltedEdition edition) {
    final IMagicSource magicSource = sourcesByEdition.get(edition);
    if (magicSource == null) {
      return primarySource;
    }
    return magicSource;
  }

  public void addSource(IMagicSource source) {
    if (primarySource == null) {
      this.primarySource = source;
    }
    if (source.getEdition() != null) {
      sourcesByEdition.put(source.getEdition(), source);
    }
  }
}