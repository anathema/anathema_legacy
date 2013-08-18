package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.display.SpecialNodeView;

public interface ToggleButtonSpecialNodeView extends SpecialNodeView {
  IBooleanValueView addSubeffect(String label);
}
