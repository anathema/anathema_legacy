package net.sf.anathema.character.persistence;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.persistence.ICharacterXmlConstants.TAG_ESSENCE;

public class EssenceConfigurationPersister {
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, TraitMap configuration) {
    traitPersister.saveTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }

  public void load(Element parent, TraitMap configuration) throws PersistenceException {
    traitPersister.restoreTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }
}