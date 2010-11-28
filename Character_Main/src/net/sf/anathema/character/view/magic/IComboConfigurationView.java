package net.sf.anathema.character.view.magic;

import javax.swing.Action;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IComboConfigurationView extends IView {

  public void initGui(IComboViewProperties properties);

  public void setAllCharms(Object[] charms);

  public void addComboViewListener(IComboViewListener listener);

  public void setComboCharms(Object[] charms);

  public ITextView addComboNameView(String viewTitle);

  public ITextView addComboDescriptionView(String viewTitle);

  public IComboView addComboView(String name, String description, Action deleteAction, Action editAction);

  public void deleteView(IComboView view);

  public void setEditState(boolean editing);
}