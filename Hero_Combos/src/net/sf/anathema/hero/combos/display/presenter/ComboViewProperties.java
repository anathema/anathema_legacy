package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.lib.file.RelativePath;

public interface ComboViewProperties extends MagicLearnProperties {

  RelativePath getFinalizeButtonIcon();

  String getFinalizeButtonToolTip();

  String getAvailableComboCharmsLabel();

  String getComboedCharmsLabel();

  RelativePath getClearButtonIcon();

  String getClearButtonToolTip();

  RelativePath getCancelEditButtonIcon();

  String getFinalizeButtonEditToolTip();

  String getCancelButtonEditToolTip();
}