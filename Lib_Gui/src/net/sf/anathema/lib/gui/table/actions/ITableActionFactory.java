package net.sf.anathema.lib.gui.table.actions;

import net.sf.anathema.lib.gui.table.SmartTable;

import javax.swing.Action;

public interface ITableActionFactory {

  Action createAction(SmartTable table);
}