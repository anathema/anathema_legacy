package net.sf.anathema.charmentry.demo;

import net.disy.commons.core.message.IBasicMessage;

public interface ICharmEntryProperties {

  public String getHeaderDataTitle();

  public String getCharmNameLabel();

  public String getCharacterTypeLabel();

  public String getEditionLabel();

  public String getBookLabel();

  public String getPageLabel();

  public IBasicMessage getHeaderDataMessage();

  public IBasicMessage getUndefinedNameMessage();

  public IBasicMessage getUndefinedTypeMessage();

  public IBasicMessage getUndefinedEditionMessage();
}