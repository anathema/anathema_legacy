package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.lib.data.Condition;

public class ElementalSubeffect extends Subeffect {
  private final Element element;

  public ElementalSubeffect(Element element, IBasicCharacterData data, Condition condition) {
    super(element.name(), data, condition);
    this.element = element;
  }

  public boolean matches(ICasteType casteType) {
    return element.matches(casteType);
  }
}