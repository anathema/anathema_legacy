package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class CharmDisplayPropertiesMap {
  private ITemplateRegistry templateRegistry;

  public CharmDisplayPropertiesMap(ITemplateRegistry templateRegistry) {
    this.templateRegistry = templateRegistry;
  }

  public ITreePresentationProperties getDisplayProperties(ICharacterType characterType) {
    ICharacterTemplate defaultTemplate = templateRegistry.getDefaultTemplate(characterType);
    return defaultTemplate.getPresentationProperties().getCharmPresentationProperties();
  }
}
