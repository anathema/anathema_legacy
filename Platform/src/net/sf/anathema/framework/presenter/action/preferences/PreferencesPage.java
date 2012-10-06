package net.sf.anathema.framework.presenter.action.preferences;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreferencesPage extends AbstractDialogPage {

  private final Map<String, JPanel> panelsByName = new LinkedHashMap<String, JPanel>();
  private IResources resources;
  private IPreferencesElement[] elements;

  public PreferencesPage(IResources resources, IPreferencesElement[] elements) {
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
      if (!panelsByName.containsKey(category)) {
        JPanel categoryPanel = new JPanel(new MigLayout(new LC().insets("0").fillX().wrapAfter(2)));
        panelsByName.put(category, categoryPanel);
        String tabName = resources.getString("AnathemaCore.Preferences.Group." + category);
        panel.addTab(tabName, categoryPanel);
      }
      element.addComponent(panelsByName.get(category), resources);
    }
    return panel;
  }
}