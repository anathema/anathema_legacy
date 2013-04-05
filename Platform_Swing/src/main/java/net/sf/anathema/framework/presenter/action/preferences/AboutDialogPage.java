package net.sf.anathema.framework.presenter.action.preferences;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialogPage extends AbstractDialogPage {

  private final Resources resources;

  public AboutDialogPage(Resources resources) {
    super(resources.getString("Help.AboutDialog.CreatedLabel")+"\n\n"+resources.getString("Help.AboutDialog.LicenseLabel"));
    this.resources = resources;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(getString("Help.AboutDialog.CreatedLabel"));
  }

  @Override
  public String getTitle() {
    return getString("Help.AboutDialog.Title");
  }

  @Override
  public String getDescription() {
    return "Anathema v"
        + getString("Anathema.Version.Numeric")
        + " \""
        + getString("Anathema.Version.Title")
        + "\""
        + " ("
        + getString("Help.AboutDialog.BuiltLabel")
        + " "
        + getString("Anathema.Version.Built")
        + ")";
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(2).fill()));
    addCredit(panel, "Help.AboutDialog.Artwork", "Martin Nerukar");
    addCredit(panel, "Help.AboutDialog.Translation.Spanish", "Ricardo Rodriguez");
    addCredit(panel, "Help.AboutDialog.Translation.Italian", "Giovanni D'Addabbo & Team ITA");
    addCredit(panel, "Help.AboutDialog.CharmTreeVisualization", "Daniel Hohenberger");
    addCredit(panel, "Help.AboutDialog.CharmDescriptions", "Official Exalted Wiki");
    addCredit(panel, "Help.AboutDialog.CharacterSheet", "voidstate");
    addCredit(panel, "Help.AboutDialog.NameTokenDatabase", "voidstate");
    addCredit(panel, "Help.AboutDialog.CharmCards", "Martin 'Democritus' Nerurkar, David J. Prokopetz, and James 'Bazzalisk' Barrett");
    addCredit(panel, "Help.AboutDialog.CharmCards.Additional", "Raiya and feminaexlux");
    return panel;
  }

  private void addCredit(JPanel panel, String key, String name) {
    panel.add(new JLabel(getString(key)), new CC().growX().pushX());
    panel.add(new JLabel(name), new CC().alignX("right"));
  }

  private String getString(String key) {
    return resources.getString(key);
  }
}