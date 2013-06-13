package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.lib.file.RelativePath;

import javax.swing.ListCellRenderer;

public interface IMagicLearnProperties {

  RelativePath getAddButtonIcon();

  String getAddButtonToolTip();

  RelativePath getRemoveButtonIcon();

  String getRemoveButtonToolTip();

  ListCellRenderer getAvailableMagicRenderer();

  ListCellRenderer getLearnedMagicRenderer();

  boolean isMagicSelectionAvailable(Object selectedValue);
}