package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_WILLPOWER;

public class WillpowerConfigurationPersister {
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, Trait willpower) {
    traitPersister.saveTrait(parent, TAG_WILLPOWER, willpower);
  }

  public void load(Element parent, Trait trait) throws PersistenceException {
    traitPersister.restoreTrait(parent, TAG_WILLPOWER, trait);
  }
}