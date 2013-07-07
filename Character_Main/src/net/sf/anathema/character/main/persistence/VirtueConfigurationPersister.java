package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_VIRTUES;

public class VirtueConfigurationPersister {

  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, TraitMap traitConfiguration) {
    Element virtuesElement = parent.addElement(TAG_VIRTUES);
    for (Trait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      traitPersister.saveTrait(virtuesElement, virtue.getType().getId(), virtue);
    }
  }

  public void load(Element parent, TraitMap traitConfiguration) throws PersistenceException {
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