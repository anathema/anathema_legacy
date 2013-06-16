package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_WILLPOWER;

public class WillpowerConfigurationPersister {
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, IDefaultTrait willpower) {
    traitPersister.saveTrait(parent, TAG_WILLPOWER, willpower);
  }

  public void load(Element parent, IDefaultTrait trait) throws PersistenceException {
    traitPersister.restoreTrait(parent, TAG_WILLPOWER, trait);
  }
}