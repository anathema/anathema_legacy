package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

public interface ISpellViewProperties extends IMagicLearnProperties {

  String getCircleLabel();

  ListCellRenderer getCircleSelectionRenderer();

  ListSelectionListener getRemoveButtonEnabledListener(JButton button, JList list);
}