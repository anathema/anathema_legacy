package net.sf.anathema.charmentry.properties;

import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface IKeywordEntryPageProperties {

  IBasicMessage getDefaultMessage();

  String getPageTitle();

  Icon getAddIcon();

  String getKeywordLabel();

  Icon getRemoveIcon();

  ListCellRenderer getKeywordRenderer();
}