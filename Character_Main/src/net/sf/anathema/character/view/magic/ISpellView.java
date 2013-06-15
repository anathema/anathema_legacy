package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.util.Identified;

public interface ISpellView extends IView, IMagicLearnView {

  void prepare(ISpellViewProperties properties);

  void initGui(Identified[] circles);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}