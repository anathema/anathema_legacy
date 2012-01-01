package net.sf.anathema.character.view.magic;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

public interface IComboViewProperties extends IMagicLearnProperties {

  Icon getFinalizeButtonIcon();

  Icon getFinalizeXPButtonIcon();

  String getFinalizeButtonToolTip();

  String getFinalizeXPButtonToolTip();

  String getAvailableComboCharmsLabel();

  String getComboedCharmsLabel();

  Icon getClearButtonIcon();

  String getClearButtonToolTip();

  Icon getCancelEditButtonIcon();

  String getFinalizeButtonEditToolTip();

  String getCancelButtonEditToolTip();

  boolean canFinalizeWithXP();
}