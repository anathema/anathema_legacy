package net.sf.anathema.demo.lib.gui.table;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.table.columsettings.StringTableColumnSettings;
import org.junit.runner.RunWith;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

@RunWith(DemoAsTestRunner.class)
public class SmartTableDemo extends SwingDemoCase {

  public void demoStringSmartTableNoHeaderNoGrid() {
    ITableColumnViewSettings[] tableColumnViewSettings = new ITableColumnViewSettings[]{new StringTableColumnSettings()};
    DefaultTableModel tableModel = new DefaultTableModel(10, 1);
    SmartTable smartTable = new SmartTable(tableModel, tableColumnViewSettings);
    smartTable.getTable().setTableHeader(null);
    smartTable.getTable().setGridColor(new Color(0, 0, 0, 0));
    show(smartTable.getComponent());
  }
}