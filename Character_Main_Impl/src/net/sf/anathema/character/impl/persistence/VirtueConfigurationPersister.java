package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_VIRTUES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_VIRTUE_FLAW;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class VirtueConfigurationPersister extends AbstractCharacterPersister {

  private final IAdditionalPersister virtueFlawPersister;

  public VirtueConfigurationPersister(IAdditionalPersister virtueFlawPersister) {
    this.virtueFlawPersister = virtueFlawPersister;
  }

  public void save(Element parent, ICoreTraitConfiguration traitConfiguration) {
    Element virtuesElement = parent.addElement(TAG_VIRTUES);
    for (IModifiableTrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      saveTrait(virtuesElement, virtue.getType().getId(), virtue);
    }
  }

  public void load(Element parent, ICoreTraitConfiguration traitConfiguration, IAdditionalModel virtueFlawModel)
      throws PersistenceException {
    Element virtuesElement = parent.element(TAG_VIRTUES);
    if (virtuesElement == null) {
      return;
    }
    for (Object virtueObjectElement : virtuesElement.elements()) {
      Element virtueElement = (Element) virtueObjectElement;
      VirtueType virtueType = VirtueType.valueOf(virtueElement.getName());
      restoreTrait(virtueElement, traitConfiguration.getTrait(virtueType));
    }
    Element flawElement = parent.element(TAG_VIRTUE_FLAW);
    if (virtueFlawModel == null || flawElement == null) {
      return;
    }
    virtueFlawPersister.load(parent, virtueFlawModel);
  }
}