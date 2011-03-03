package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_BACKGROUNDS;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

import org.dom4j.Element;

public class BackgroundConfigurationPersister {

  private static final String TAG_BACKGROUND = "Background"; //$NON-NLS-1$
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;
  private final TraitPersister persister = new TraitPersister();

  public BackgroundConfigurationPersister(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    this.backgroundRegistry = backgroundRegistry;
  }

  public void save(Element parent, IBackgroundConfiguration backgrounds) {
    Element backgroundsElement = parent.addElement(TAG_BACKGROUNDS);
    for (IDefaultTrait background : backgrounds.getBackgrounds()) {
      ITraitType backgroundTemplate = background.getType();
      String backgroundId = backgroundTemplate.getId();
      Element backgroundElement = persister.saveTrait(backgroundsElement, TAG_BACKGROUND, background);
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
    IDefaultTrait background;
    if (backgroundRegistry.idRegistered(backgroundId)) {
      background = backgrounds.addBackground(backgroundRegistry.getById(backgroundId), true);
    }
    else {
      background = backgrounds.addBackground(backgroundId, true);
    }
    if (background == null) {
      throw new PersistenceException("Error reading Background: " + backgroundId); //$NON-NLS-1$
    }
    persister.restoreTrait(element, background);
  }
}