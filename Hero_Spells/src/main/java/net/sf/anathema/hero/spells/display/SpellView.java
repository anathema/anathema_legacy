package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface SpellView extends MagicLearnView {

  void initGui(Identifier[] circles, ISpellViewProperties properties);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}