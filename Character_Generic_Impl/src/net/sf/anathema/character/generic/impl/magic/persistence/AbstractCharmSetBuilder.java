package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.Collection;
import java.util.HashSet;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.Element;

public abstract class AbstractCharmSetBuilder implements ICharmSetBuilder {

  @Override
  public ICharm[] buildCharms(Document charmDoc) throws PersistenceException {
    Collection<Charm> allCharms = new HashSet<Charm>();
    Element charmListElement = charmDoc.getRootElement();
    buildCharms(allCharms, charmListElement);
    return allCharms.toArray(new ICharm[allCharms.size()]);
  }

  protected abstract void buildCharms(Collection<Charm> allCharms, Element charmListElement)
      throws PersistenceException;

  protected final void createCharm(Collection<Charm> allCharms, ICharmBuilder currentbuilder, Element charmElement)
      throws PersistenceException {
    Charm newCharm = currentbuilder.buildCharm(charmElement);
    if (allCharms.contains(newCharm)) {
      allCharms.remove(newCharm);
    }
    allCharms.add(newCharm);
  }
}