package net.sf.anathema.character.equipment.creation.view;

import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.JCheckBox;

public interface IWeaponTagsView extends IPageContent {

  JCheckBox addCheckBox(String string);
}