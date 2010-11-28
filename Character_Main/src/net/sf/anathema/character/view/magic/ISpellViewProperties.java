package net.sf.anathema.character.view.magic;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

public interface ISpellViewProperties extends IMagicLearnProperties {

  public String getCircleString();

  public String getLearnedSpellString();

  public ListCellRenderer getCircleSelectionRenderer();

  public ListSelectionListener getRemoveButtonEnabledListener(JButton button, JList list);

  public String getDetailTitle();

  public String getCostString();

  public String getSourceString();

  public String getSelectionTitle();
}