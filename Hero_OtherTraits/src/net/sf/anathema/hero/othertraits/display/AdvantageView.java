package net.sf.anathema.hero.othertraits.display;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.value.IIntValueView;

public interface AdvantageView {

  void initGui(AdvantageViewProperties properties);

  IIntValueView addVirtue(String labelText, int value, int maxValue);

  IIntValueView addWillpower(String labelText, int value, int maxValue);

  IIntValueView addEssenceView(String labelText, int value, int maxValue, Trait trait);

  IValueView<String> addPoolView(String labelText, String value);
}