package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractCharmSetBuilder implements ICharmSetBuilder {

  @Override
  public Charm[] buildCharms(Document charmDoc, List<SpecialCharmDto> specialCharms) throws PersistenceException {
    Collection<CharmImpl> allCharms = new HashSet<>();
    Element charmListElement = charmDoc.getRootElement();
    buildCharms(allCharms, specialCharms, charmListElement);
    return allCharms.toArray(new Charm[allCharms.size()]);
  }

  protected abstract void buildCharms(Collection<CharmImpl> allCharms, List<SpecialCharmDto> specialCharms, Element charmListElement) throws
          PersistenceException;

  protected final void createCharm(Collection<CharmImpl> allCharms, List<SpecialCharmDto> specialCharms, ICharmBuilder currentbuilder,
                                   Element charmElement) throws PersistenceException {
    CharmImpl newCharm = currentbuilder.buildCharm(charmElement, specialCharms);
    if (allCharms.contains(newCharm)) {
      allCharms.remove(newCharm);
    }
    allCharms.add(newCharm);
  }
}