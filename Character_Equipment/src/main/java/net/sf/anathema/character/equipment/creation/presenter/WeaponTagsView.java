package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.framework.swing.IView;

import javax.swing.JCheckBox;

public interface WeaponTagsView extends IView {

  JCheckBox addCheckBox(String string);
}