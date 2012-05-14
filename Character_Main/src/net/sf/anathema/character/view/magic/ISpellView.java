package net.sf.anathema.character.view.magic;

import javax.swing.JLabel;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface ISpellView extends IView, IMagicLearnView {

  public void initGui(IIdentificate[] circles);

  public void addCircleSelectionListener(ObjectValueListener<CircleType> listener);

  public IValueView<String> addDetailValueView(String label);

  public JLabel addDetailTitleView();
}