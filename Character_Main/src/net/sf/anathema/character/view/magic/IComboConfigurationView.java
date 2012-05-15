package net.sf.anathema.character.view.magic;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.Action;

public interface IComboConfigurationView extends IView {

  void initGui(IComboViewProperties properties);

  void setAllCharms(Object[] charms);

  void addComboViewListener(IComboViewListener listener);

  void setComboCharms(Object[] charms);

  ITextView addComboNameView(String viewTitle);

  ITextView addComboDescriptionView(String viewTitle);

  IComboView addComboView(String name, String description, Action deleteAction, Action editAction);

  void deleteView(IComboView view);

  void setEditState(boolean editing);
}