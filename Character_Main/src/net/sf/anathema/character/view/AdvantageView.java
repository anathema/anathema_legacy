package net.sf.anathema.character.view;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface AdvantageView extends IInitializableContentView<IAdvantageViewProperties> {

  IIntValueView addVirtue(String labelText, int value, int maxValue);

  IIntValueView addWillpower(String labelText, int value, int maxValue);

  IIntValueView addEssenceView(String labelText, int value, int maxValue, Trait trait);

  IValueView<String> addPoolView(String labelText, String value);
}