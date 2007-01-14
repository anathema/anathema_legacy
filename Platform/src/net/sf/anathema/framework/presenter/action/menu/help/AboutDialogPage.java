package net.sf.anathema.framework.presenter.action.menu.help;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.resources.IResources;

public class AboutDialogPage extends AbstractDialogPage {

  private final IResources resources;

  public AboutDialogPage(IResources resources) {
    super(new BasicMessage(resources.getString("Help.AboutDialog.CreatedLabel"))); //$NON-NLS-1$
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
    panel.add(new JLabel(getString("Help.AboutDialog.Artwork")), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$
    panel.add(new JLabel("Martin Nerukar"), GridDialogLayoutData.RIGHT); //$NON-NLS-1$
    panel.add(new JLabel(getString("Help.AboutDialog.Translation.Spanish")), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$
    panel.add(new JLabel("Ricardo Rodriguez"), GridDialogLayoutData.RIGHT); //$NON-NLS-1$
    panel.add(new JLabel(getString("Help.AboutDialog.CharmTreeVisualization")), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$
    panel.add(new JLabel("Daniel Hohenberger"), GridDialogLayoutData.RIGHT); //$NON-NLS-1$
    panel.add(new JLabel(getString("Help.AboutDialog.CharacterSheet")), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$
    panel.add(new JLabel("voidstate"), GridDialogLayoutData.RIGHT); //$NON-NLS-1$
    panel.add(new JLabel(getString("Help.AboutDialog.NameTokenDatabase")), IGridDialogLayoutData.DEFAULT); //$NON-NLS-1$
    panel.add(new JLabel("voidstate"), GridDialogLayoutData.RIGHT); //$NON-NLS-1$
    return panel;
  }

  private String getString(String key) {
    return resources.getString(key);
  }
}