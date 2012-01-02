package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

import java.util.List;

public class ElementalCharmLearnCondition implements ICondition {
  private List<ElementalSubeffect> effectList;
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;
  private final IBasicCharacterData data;
  private final Element element;

  public ElementalCharmLearnCondition(List<ElementalSubeffect> effectList, ICharmLearnableArbitrator arbitrator, ICharm charm, IBasicCharacterData data, Element element) {
    this.effectList = effectList;
    this.arbitrator = arbitrator;
    this.charm = charm;
    this.data = data;
    this.element = element;
  }

  public boolean isFulfilled() {
    boolean learnable = arbitrator.isLearnable(charm);
    if (!data.getCharacterType().equals(CharacterType.DB)) {
      return learnable;
    }
    if (element.equals(data.getCasteType())) {
      return learnable;
    }
    for (ElementalSubeffect effect : effectList) {
      if (effect.isLearned() && effect.matches(data.getCasteType())) {
        return learnable;
      }
    }
    return false;
  }
}