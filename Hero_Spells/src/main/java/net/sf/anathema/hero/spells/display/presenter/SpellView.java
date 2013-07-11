package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface SpellView {

  void addCircleSelection(Identifier[] circles, SpellViewProperties properties);

  MagicLearnView addMagicLearnView(MagicLearnProperties properties);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}