package net.sf.anathema.charmentry.demo.page;

import javax.swing.Icon;

import net.disy.commons.core.message.IBasicMessage;

public interface IKeywordEntryPageProperties {

  public IBasicMessage getDefaultMessage();

  public String getPageTitle();

  public Icon getAddIcon();

  public String getKeywordLabel();

  public Icon getRemoveIcon();
}