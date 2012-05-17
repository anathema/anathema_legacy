package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISpellView extends IView, IMagicLearnView {

  void initGui(IIdentificate[] circles);

  void addCircleSelectionListener(ObjectValueListener<CircleType> listener);
}