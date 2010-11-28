package net.sf.anathema.framework.model;

import javax.swing.Action;

import net.sf.anathema.framework.repository.IItem;

public interface IItemActionFactory {

  public Action createAction(IItem item);
}