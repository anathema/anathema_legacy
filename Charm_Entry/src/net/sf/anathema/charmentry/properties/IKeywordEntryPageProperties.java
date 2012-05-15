package net.sf.anathema.charmentry.properties;

import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface IKeywordEntryPageProperties {

  public IBasicMessage getDefaultMessage();

  public String getPageTitle();

  public Icon getAddIcon();

  public String getKeywordLabel();

  public Icon getRemoveIcon();

  public ListCellRenderer getKeywordRenderer();
}