package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.framework.swing.IView;

import javax.swing.JCheckBox;

public interface IWeaponTagsView extends IView {

  JCheckBox addCheckBox(String string);
}