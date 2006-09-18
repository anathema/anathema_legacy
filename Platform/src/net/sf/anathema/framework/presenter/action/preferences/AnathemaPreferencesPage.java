package net.sf.anathema.framework.presenter.action.preferences;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.infonode.tabbedpanel.Tab;
import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaPreferencesPage extends AbstractDialogPage {

  private IResources resources;
  private IPreferencesElement[] elements;
  private final Map<String, Tab> tabsByName = new LinkedHashMap<String, Tab>();
  private final Map<String, IGridDialogPanel> panelsByName = new HashMap<String, IGridDialogPanel>();

  public AnathemaPreferencesPage(IResources resources, IPreferencesElement[] elements) {
    super(new BasicMessage(resources.getString("AnathemaCore.Tools.Preferences.Instruction"))); //$NON-NLS-1$
    this.resources = resources;
    this.elements = elements;
  }

  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.Preferences.Name"); //$NON-NLS-1$
  }

  public JComponent createContent() {
    TabbedPanel panel = new TabbedPanel();
    for (IPreferencesElement element : elements) {
      String category = element.getCategory().getId();
      Tab categoryTab = tabsByName.get(category);
      if (categoryTab == null) {
        categoryTab = createCategoryTab(panel, category);
        panelsByName.put(category, new DefaultGridDialogPanel(false));
      }
      element.addCompoment(panelsByName.get(category), resources);
    }
    for (String key : tabsByName.keySet()) {
      Tab tab = tabsByName.get(key);
      tab.getContentComponent().add(panelsByName.get(key).getContent());
    }
    return panel;
  }

  private Tab createCategoryTab(TabbedPanel panel, String category) {
    Tab tab = new TitledTab(
        resources.getString("AnathemaCore.Preferences.Group." + category), null, new JPanel(new GridDialogLayout(1, false)), null); //$NON-NLS-1$
    tabsByName.put(category, tab);
    panel.addTab(tab);
    return tab;
  }
}