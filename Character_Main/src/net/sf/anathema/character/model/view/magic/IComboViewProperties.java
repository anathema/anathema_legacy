package net.sf.anathema.character.model.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;
import net.sf.anathema.lib.file.RelativePath;

public interface IComboViewProperties extends IMagicLearnProperties {

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