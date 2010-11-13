package net.sf.anathema.character.generic.framework.magic.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface IMagicLearnProperties {

  public Icon getAddButtonIcon();

  public String getAddButtonToolTip();

  public Icon getRemoveButtonIcon();

  public String getRemoveButtonToolTip();

  public ListCellRenderer getAvailableMagicRenderer();

  public ListCellRenderer getLearnedMagicRenderer();

  public boolean isMagicSelectionAvailable(Object selectedValue);

  public int getAvailableListSelectionMode();
}