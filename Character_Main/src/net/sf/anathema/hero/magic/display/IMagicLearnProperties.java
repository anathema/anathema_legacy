package net.sf.anathema.hero.magic.display;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public interface IMagicLearnProperties {

  RelativePath getAddButtonIcon();

  String getAddButtonToolTip();

  RelativePath getRemoveButtonIcon();

  String getRemoveButtonToolTip();

  AgnosticUIConfiguration<Identifier> getLearnedMagicRenderer();

  boolean isMagicSelectionAvailable(Object selectedValue);

  AgnosticUIConfiguration getAvailableMagicRenderer();

  LegalityCheck getLegalityCheck();

  boolean isRemoveAllowed(List selectedObjects);
}