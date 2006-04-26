package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ESSENCE;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class EssenceConfigurationPersister extends AbstractCharacterPersister {

  public void save(Element parent, ICoreTraitConfiguration configuration) {
    saveTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }

  public void load(Element parent, ICoreTraitConfiguration configuration) throws PersistenceException {
    restoreTrait(parent, TAG_ESSENCE, configuration.getTrait(OtherTraitType.Essence));
  }
}