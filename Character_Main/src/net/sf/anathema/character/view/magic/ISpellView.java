package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISpellView extends ISimpleTabView, IMagicLearnView {

  public void initGui(IIdentificate[] circles, ISpellViewProperties properties);

  public void addCircleSelectionListener(IObjectValueChangedListener listener);

  public void setSpellDetails(String name, String circle, String costString, String sourceString);
}