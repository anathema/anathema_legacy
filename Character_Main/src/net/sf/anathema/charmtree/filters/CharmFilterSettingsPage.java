package net.sf.anathema.charmtree.filters;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmFilterSettingsPage extends AbstractDialogPage {
  private Resources resources;
  private CharmFilterSet filterSet;

  public CharmFilterSettingsPage(Resources resources, CharmFilterSet filterSet) {
    super(resources.getString("CharmFilters.Instructions"));
    this.resources = resources;
    this.filterSet = filterSet;
  }

  @Override
  public JComponent createContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    for (ICharmFilter filter : filterSet.getAllFilters()) {
      panel.add(filter.getFilterPreferencePanel(resources));
    }
    return panel;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public String getTitle() {
    return resources.getString("CharmFilters.Filters");
  }
}