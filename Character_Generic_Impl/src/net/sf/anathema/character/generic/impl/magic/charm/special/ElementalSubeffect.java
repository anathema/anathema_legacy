package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalSubeffect extends Subeffect {
  private final Element element;

  public ElementalSubeffect(Element element, IBasicCharacterData data, ICondition learnable) {
    super(element.name(), data, learnable);
    this.element = element;
  }

  public boolean matches(ICasteType casteType) {
    return element.matches(casteType);
  }
}