package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.hero.magic.display.IMagicLearnView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellView extends IMagicLearnView {

  void prepare(ISpellViewProperties properties);

  void initGui(Identifier[] circles);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}