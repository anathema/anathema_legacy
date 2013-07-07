package net.sf.anathema.character.main.magic.display.view.spells;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.display.view.magic.IMagicLearnView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellView extends IMagicLearnView {

  void prepare(ISpellViewProperties properties);

  void initGui(Identifier[] circles);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}