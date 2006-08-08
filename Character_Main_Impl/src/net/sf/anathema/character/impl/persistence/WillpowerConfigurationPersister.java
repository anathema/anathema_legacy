package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_WILLPOWER;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class WillpowerConfigurationPersister extends AbstractCharacterPersister {
  public void save(Element parent, IModifiableTrait willpower) {
    saveTrait(parent, TAG_WILLPOWER, willpower);
  }

  public void load(Element parent, IModifiableTrait trait) throws PersistenceException {
    restoreTrait(parent, TAG_WILLPOWER, trait);
  }
}