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
  private final IBasicMessage defaultMessage = new BasicMessage("Select Keywords, if any.");
  private final BasicUi ui;

  public KeywordEntryPageProperties(IResources resources) {
    this.resources = resources;
    this.ui = new BasicUi(resources);
  }

  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  public String getPageTitle() {
    return "Keywords";
  }

  public Icon getAddIcon() {
    return ui.getAddIcon();
  }

  public String getKeywordLabel() {
    return "Keyword";
  }

  public Icon getRemoveIcon() {
    return ui.getRemoveIcon();
  }

  public ListCellRenderer getKeywordRenderer() {
    return new IdentificateSelectCellRenderer("Keyword.", resources); //$NON-NLS-1$
  }
}
