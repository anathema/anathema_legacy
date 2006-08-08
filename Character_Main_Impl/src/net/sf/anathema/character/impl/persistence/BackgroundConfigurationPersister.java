package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_BACKGROUNDS;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

import org.dom4j.Element;

public class BackgroundConfigurationPersister extends AbstractCharacterPersister {

  private static final String TAG_BACKGROUND = "Background"; //$NON-NLS-1$
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;

  public BackgroundConfigurationPersister(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    this.backgroundRegistry = backgroundRegistry;
  }

  public void save(Element parent, IBackgroundConfiguration backgrounds) {
    Element backgroundsElement = parent.addElement(TAG_BACKGROUNDS);
    for (IModifiableTrait background : backgrounds.getBackgrounds()) {
      ITraitType backgroundTemplate = background.getType();
      String backgroundId = backgroundTemplate.getId();
      Element backgroundElement = saveTrait(backgroundsElement, TAG_BACKGROUND, background);
      backgroundElement.addCDATA(backgroundId);
    }
  }

  public void load(Element parent, IBackgroundConfiguration backgrounds) throws PersistenceException {
    Element backgroundsElement = parent.element(TAG_BACKGROUNDS);
    if (backgroundsElement == null) {
      return;
    }
    for (Object backgroundObjectElement : backgroundsElement.elements()) {
      Element backgroundElement = (Element) backgroundObjectElement;
      loadBackground(backgrounds, backgroundElement);
    }
  }

  private void loadBackground(IBackgroundConfiguration backgrounds, Element element) throws PersistenceException {
    String backgroundId = element.getName();
    if (backgroundId.equals(TAG_BACKGROUND)) {
      backgroundId = element.getText();
    }
    IModifiableTrait background;
    if (backgroundRegistry.idRegistered(backgroundId)) {
      background = backgrounds.addBackground(backgroundRegistry.getById(backgroundId));
    }
    else {
      background = backgrounds.addBackground(backgroundId);
    }
    if (background == null) {
      throw new PersistenceException("Error reading Background: " + backgroundId); //$NON-NLS-1$
    }
    restoreTrait(element, background);
  }
}