package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalSubeffect extends Subeffect implements IElementalSubeffect {

  private final DBAspect aspect;

  public ElementalSubeffect(DBAspect aspect, IBasicCharacterData data, ICondition learnable) {
    super(aspect.getId(), data, learnable);
    this.aspect = aspect;
  }

  @Override
  public boolean matches(ICasteType casteType) {
    return aspect.equals(casteType);
  }
}