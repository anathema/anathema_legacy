package net.sf.anathema.character.view.magic;

import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISpellView extends ISimpleTabView {

  public void initGui(IIdentificate[] circles, ISpellViewProperties properties);

  public void setLearnedSpells(Object[] spells);

  public void setAllSpells(Object[] spells);

  public void addSpellViewListener(IMagicViewListener listener);

  public void addCircleSelectionListener(IObjectValueChangedListener listener);

  public void clearSelection();

  public void addSpellSelectionListener(ListSelectionListener listener);

  public void setSpellDetails(String name, String circle, String costString, String sourceString);
}