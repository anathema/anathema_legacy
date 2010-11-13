package net.sf.anathema.charmentry.properties;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class KeywordEntryPageProperties implements IKeywordEntryPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;
  private final BasicUi ui;

  public KeywordEntryPageProperties(IResources resources) {
    this.resources = resources;
    this.ui = new BasicUi(resources);
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.Keywords.Message.Default")); //$NON-NLS-1$
  }

  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  public String getPageTitle() {
    return resources.getString("CharmEntry.Keywords.Title"); //$NON-NLS-1$
  }

  public Icon getAddIcon() {
    return ui.getAddIcon();
  }

  public String getKeywordLabel() {
    return resources.getString("CharmEntry.Keywords.Keyword"); //$NON-NLS-1$
  }

  public Icon getRemoveIcon() {
    return ui.getRemoveIcon();
  }

  public ListCellRenderer getKeywordRenderer() {
    return new IdentificateSelectCellRenderer("Keyword.", resources); //$NON-NLS-1$
  }
}
