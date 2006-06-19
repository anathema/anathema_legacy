package net.sf.anathema.lib.gui.list.actionview.demo;

import javax.swing.JComponent;

import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.StringTableColumnSettings;
import de.jdemo.extensions.SwingDemoCase;

public class EditableActionAddableListViewDemo extends SwingDemoCase {

  public void demo() {
    EditableActionAddableListView<String> view = new EditableActionAddableListView<String>("Demo:", new StringTableColumnSettings(), String.class); //$NON-NLS-1$
    JComponent content = view.getContent();
    view.setListItems(new String[] { "Hallo", "Sandra", "String" }); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    show(content);
  }
}