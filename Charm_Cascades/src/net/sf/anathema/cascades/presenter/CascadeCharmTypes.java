package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.presenter.magic.AbstractCharmTypes;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CascadeCharmTypes extends AbstractCharmTypes {
  private final CharacterTypes characterTypes;
  private ITemplateRegistry templateRegistry;

  public CascadeCharmTypes(CharacterTypes characterTypes, ITemplateRegistry templateRegistry) {
    this.templateRegistry = templateRegistry;
    this.characterTypes = characterTypes;
  }

  @Override
  protected List<Identifier> getCurrentCharacterTypes() {
    Set<Identifier> set = new LinkedHashSet<>();
    for (ICharacterType type : characterTypes.findAll()) {
      HeroTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type);
      if (defaultTemplate == null) {
        continue;
      }
      if (DefaultCharmTemplateRetriever.getCharmTemplate(defaultTemplate).canLearnCharms()) {
        set.add(type);
      }
    }
    return new ArrayList<>(set);
  }
}