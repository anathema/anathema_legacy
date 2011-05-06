package net.sf.anathema.character.mutations.view;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

public interface IMutationLearnViewProperties extends IMagicLearnProperties {

  public ListSelectionListener getRemoveButtonEnabledListener(JButton button, JList list);

}
