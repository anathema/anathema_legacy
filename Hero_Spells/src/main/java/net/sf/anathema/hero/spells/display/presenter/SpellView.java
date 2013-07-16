package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.hero.charms.display.magic.MagicLearnProperties;
import net.sf.anathema.hero.charms.display.magic.MagicLearnView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface SpellView {

  void addCircleSelection(Identifier[] circles, SpellViewProperties properties);

  void showSelectedCircle(CircleType newValue);

  MagicLearnView addMagicLearnView(MagicLearnProperties properties);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}