package net.sf.anathema.character.view;

import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IBasicAdvantageView extends IInitializableContentView<IAdvantageViewProperties> {

  IIntValueView addVirtue(String labelText, int value, int maxValue);

  IIntValueView addWillpower(String labelText, int value, int maxValue);

  IIntValueView addEssenceView(String labelText, int value, int maxValue, IDefaultTrait trait);

  IValueView<String> addPoolView(String labelText, String value);
}