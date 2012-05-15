package net.sf.anathema.character.mutations.view;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

public interface IMutationLearnViewProperties extends IMagicLearnProperties {

  ListSelectionListener getRemoveButtonEnabledListener(JButton button, JList list);

}
