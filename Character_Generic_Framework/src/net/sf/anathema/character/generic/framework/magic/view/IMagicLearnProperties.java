package net.sf.anathema.character.generic.framework.magic.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface IMagicLearnProperties {

  Icon getAddButtonIcon();

  String getAddButtonToolTip();

  Icon getRemoveButtonIcon();

  String getRemoveButtonToolTip();

  ListCellRenderer getAvailableMagicRenderer();

  ListCellRenderer getLearnedMagicRenderer();
  
  boolean isMagicSelectionAvailable(Object selectedValue);
}