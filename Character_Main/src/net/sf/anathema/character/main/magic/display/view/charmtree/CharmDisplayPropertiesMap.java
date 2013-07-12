package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public class CharmDisplayPropertiesMap {
  private ITemplateRegistry templateRegistry;

  public CharmDisplayPropertiesMap(ITemplateRegistry templateRegistry) {
    this.templateRegistry = templateRegistry;
  }

  public ITreePresentationProperties getDisplayProperties(CharacterType characterType) {
    HeroTemplate defaultTemplate = templateRegistry.getDefaultTemplate(characterType);
    return defaultTemplate.getPresentationProperties().getCharmPresentationProperties();
  }
}
