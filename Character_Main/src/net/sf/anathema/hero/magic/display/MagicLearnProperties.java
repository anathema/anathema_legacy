package net.sf.anathema.hero.magic.display;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

import java.util.List;

public interface MagicLearnProperties {

  RelativePath getAddButtonIcon();

  String getAddButtonToolTip();

  RelativePath getRemoveButtonIcon();

  String getRemoveButtonToolTip();

  boolean isMagicSelectionAvailable(List selectedValue);

  AgnosticUIConfiguration getMagicRenderer();

  boolean isRemoveAllowed(List selectedObjects);
}