package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.framework.value.IIntValueView;

public interface CategorizedSpecialNodeView {
  IIntValueView addCategory(String labelText, int maxValue, int value);
}