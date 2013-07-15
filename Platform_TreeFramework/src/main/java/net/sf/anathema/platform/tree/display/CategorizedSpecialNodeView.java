package net.sf.anathema.platform.tree.display;

import net.sf.anathema.framework.value.IntValueView;

public interface CategorizedSpecialNodeView {
  IntValueView addCategory(String labelText, int maxValue, int value);
}