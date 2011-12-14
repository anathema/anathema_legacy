package net.sf.anathema.framework.presenter.action.menu.help;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.resources.IResources;

public class AboutDialogPage extends AbstractDialogPage {

  private final IResources resources;

  public AboutDialogPage(IResources resources) {
    super(resources.getString("Help.AboutDialog.CreatedLabel")); //$NON-NLS-1$
    this.resources = resources;
  }

  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(getString("Help.AboutDialog.CreatedLabel")); //$NON-NLS-1$
  }

  public String getTitle() {
    return getString("Help.AboutDialog.Title"); //$NON-NLS-1$
  }

  @Override
  public String getDescription() {
    return "Anathema v" //$NON-NLS-1$
        + getString("Anathema.Version.Numeric") //$NON-NLS-1$
        + " \"" //$NON-NLS-1$
        + getString("Anathema.Version.Title") //$NON-NLS-1$
        + "\"" //$NON-NLS-1$
        + " (" //$NON-NLS-1$
        + getString("Help.AboutDialog.BuiltLabel") //$NON-NLS-1$
        + " " //$NON-NLS-1$
        + getString("Anathema.Version.Built") //$NON-NLS-1$
        + ")"; //$NON-NLS-1$
  }

  public JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    addCredit(panel, "Help.AboutDialog.Artwork", "Martin Nerukar"); //$NON-NLS-1$//$NON-NLS-2$
    addCredit(panel, "Help.AboutDialog.Translation.Spanish", "Ricardo Rodriguez"); //$NON-NLS-1$//$NON-NLS-2$
    addCredit(panel, "Help.AboutDialog.Translation.Italian", "Giovanni D'Addabbo & Team ITA"); //$NON-NLS-1$//$NON-NLS-2$
    addCredit(panel, "Help.AboutDialog.CharmTreeVisualization", "Daniel Hohenberger"); //$NON-NLS-1$//$NON-NLS-2$
    addCredit(panel, "Help.AboutDialog.CharacterSheet", "voidstate"); //$NON-NLS-1$//$NON-NLS-2$
    addCredit(panel, "Help.AboutDialog.NameTokenDatabase", "voidstate"); //$NON-NLS-1$//$NON-NLS-2$
    return panel;
  }

  private void addCredit(JPanel panel, String key, String name) {
    panel.add(new JLabel(getString(key)), IGridDialogLayoutData.DEFAULT);
    panel.add(new JLabel(name), GridDialogLayoutData.RIGHT);
  }

  private String getString(String key) {
    return resources.getString(key);
  }
}