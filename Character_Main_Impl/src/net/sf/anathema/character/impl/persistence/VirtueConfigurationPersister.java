package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_VIRTUES;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class VirtueConfigurationPersister {

  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, ICoreTraitConfiguration traitConfiguration) {
    Element virtuesElement = parent.addElement(TAG_VIRTUES);
    for (ITrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      traitPersister.saveTrait(virtuesElement, virtue.getType().getId(), virtue);
    }
  }

  public void load(Element parent, ICoreTraitConfiguration traitConfiguration) throws PersistenceException {
    Element virtuesElement = parent.element(TAG_VIRTUES);
    if (virtuesElement == null) {
      return;
    }
    for (Object virtueObjectElement : virtuesElement.elements()) {
      Element virtueElement = (Element) virtueObjectElement;
      VirtueType virtueType = VirtueType.valueOf(virtueElement.getName());
      traitPersister.restoreTrait(virtueElement, traitConfiguration.getTrait(virtueType));
    }
  }
}