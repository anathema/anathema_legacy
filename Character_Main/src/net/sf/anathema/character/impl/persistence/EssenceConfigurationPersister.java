package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.TraitCollection;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ESSENCE;

public class EssenceConfigurationPersister {
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, TraitCollection configuration) {
    traitPersister.saveTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }

  public void load(Element parent, TraitCollection configuration) throws PersistenceException {
    traitPersister.restoreTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }
}