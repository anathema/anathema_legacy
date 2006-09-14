package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_WILLPOWER;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class WillpowerConfigurationPersister {
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, ITrait willpower) {
    traitPersister.saveTrait(parent, TAG_WILLPOWER, willpower);
  }

  public void load(Element parent, ITrait trait) throws PersistenceException {
    traitPersister.restoreTrait(parent, TAG_WILLPOWER, trait);
  }
}