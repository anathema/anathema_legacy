package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.IView;

public interface IBeastformView extends IView {

  IIntValueView addAttributeValueView(String string, int currentValue, int maximalValue);

  void addMutationsView(IMutationsView mutationView);
}