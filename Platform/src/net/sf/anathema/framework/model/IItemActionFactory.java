package net.sf.anathema.framework.model;

import net.sf.anathema.framework.repository.IItem;

import javax.swing.Action;

public interface IItemActionFactory {

  Action createAction(IItem item);
}