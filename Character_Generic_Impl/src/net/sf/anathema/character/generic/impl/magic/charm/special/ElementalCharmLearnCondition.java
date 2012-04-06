package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalCharmLearnCondition implements ICondition {
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;
  private final IBasicCharacterData data;
  private final ElementalMultipleEffectCharm multiEffectCharm;
  private final Element element;

  public ElementalCharmLearnCondition(ElementalMultipleEffectCharm multiEffectCharm, ICharmLearnableArbitrator arbitrator, ICharm charm, IBasicCharacterData data, Element element) {
    this.multiEffectCharm = multiEffectCharm;
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
    if (data.getCasteType().getId() == null) {
    	return false;
    }
    if (element.matches(data.getCasteType())) {
      return learnable;
    }
    if (multiEffectCharm != null) {
	    for (ElementalSubeffect effect : multiEffectCharm.getSessionSubeffects(data)) {
	      if (effect.isLearned() && effect.matches(data.getCasteType())) {
	        return learnable;
	      }
	    }
    }
    return false;
  }
}