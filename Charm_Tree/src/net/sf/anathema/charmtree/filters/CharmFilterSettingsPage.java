package net.sf.anathema.charmtree.filters;

import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public class CharmFilterSettingsPage extends AbstractDialogPage {
  private IResources resources;
  private CharmFilterSet filterSet;

  public CharmFilterSettingsPage(IResources resources, CharmFilterSet filterSet) {
    super(resources.getString("CharmFilters.Instructions"));
    this.resources = resources;
    this.filterSet = filterSet;
  }

  @Override
  public JComponent createContent() {
    return filterSet.creteFilterPanel(resources);
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