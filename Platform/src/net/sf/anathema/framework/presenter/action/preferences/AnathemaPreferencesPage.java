package net.sf.anathema.framework.presenter.action.preferences;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnathemaPreferencesPage extends AbstractDialogPage {

  private IResources resources;
  private IPreferencesElement[] elements;
  private final Map<String, JPanel> tabContentByCategory = new LinkedHashMap<String, JPanel>();
  private final Map<String, IGridDialogPanel> panelsByName = new HashMap<String, IGridDialogPanel>();

  public AnathemaPreferencesPage(IResources resources, IPreferencesElement[] elements) {
    super(resources.getString("AnathemaCore.Tools.Preferences.Instruction")); //$NON-NLS-1$
    this.resources = resources;
    this.elements = elements;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.Preferences.Name"); //$NON-NLS-1$
  }

  @Override
  public JComponent createContent() {
    JTabbedPane panel = new JTabbedPane();
    for (IPreferencesElement element : elements) {
      String category = element.getCategory().getId();
      if (tabContentByCategory.get(category) == null) {
        createCategoryTab(panel, category);
        panelsByName.put(category, new DefaultGridDialogPanel());
      }
      element.addComponent(panelsByName.get(category), resources);
    }
    for (String key : tabContentByCategory.keySet()) {
      JPanel categoryPanel = tabContentByCategory.get(key);
      categoryPanel.add(panelsByName.get(key).getComponent());
    }
    return panel;
  }

  private void createCategoryTab(JTabbedPane panel, String category) {
    String tabName = resources.getString("AnathemaCore.Preferences.Group." + category);
    JPanel content = new JPanel(new GridDialogLayout(1, false));
    tabContentByCategory.put(category, content);
    panel.addTab(tabName, content);
  }
}
