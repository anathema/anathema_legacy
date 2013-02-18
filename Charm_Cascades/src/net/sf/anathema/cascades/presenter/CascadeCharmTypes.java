package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.presenter.magic.AbstractCharmTypes;
import net.sf.anathema.lib.util.Identified;

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
  protected List<Identified> getCurrentCharacterTypes() {
    Set<Identified> set = new LinkedHashSet<>();
    for (ICharacterType type : characterTypes.findAll()) {
      ICharacterTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type);
      if (defaultTemplate == null) {
        continue;
      }
      if (defaultTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms()) {
        set.add(type);
      }
    }
    return new ArrayList<>(set);
  }

  @Override
  protected List<Identified> getAdditionalCharmTypes() {
    Set<Identified> set = new LinkedHashSet<>();
    for (ICharacterType type : characterTypes.findAll()) {
      ICharacterTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type);
      if (defaultTemplate == null) {
        continue;
      }
      ICharmTemplate charmTemplate = defaultTemplate.getMagicTemplate().getCharmTemplate();
      if (!charmTemplate.hasUniqueCharms()) {
        continue;
      }
      set.add(charmTemplate.getUniqueCharmType().getId());
    }
    return new ArrayList<>(set);
  }
}