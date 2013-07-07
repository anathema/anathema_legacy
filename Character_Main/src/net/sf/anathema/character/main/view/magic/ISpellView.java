package net.sf.anathema.character.main.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellView extends IMagicLearnView {

  void prepare(ISpellViewProperties properties);

  void initGui(Identifier[] circles);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}