package net.sf.anathema.charmentry.demo.page.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.charmentry.demo.ICharmEntryProperties;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryProperties implements ICharmEntryProperties {

  private final IResources resources;
  private final BasicMessage nameUndefinedMessage = new BasicMessage("Please enter the Charm's name.");
  private final BasicMessage editionUndefinedMessage = new BasicMessage("Please select the target Edition.");
  private final BasicMessage typeUndefinedMessage = new BasicMessage("Please select the target character type.");
  private final IBasicMessage basicDataMessage = new BasicMessage("Enter basic data");

  public CharmEntryProperties(IResources resources) {
    this.resources = resources;
  }

  public String getHeaderDataTitle() {
    return "Header Data";
  }

  public String getCharacterTypeLabel() {
    return "Character Type";
  }

  public String getCharmNameLabel() {
    return "Charm Name";
  }

  public String getEditionLabel() {
    return "Edition";
  }

  public IBasicMessage getHeaderDataMessage() {
    return basicDataMessage;
  }

  public String getBookLabel() {
    return "Book";
  }

  public String getPageLabel() {
    return "Page";
  }

  public IBasicMessage getUndefinedEditionMessage() {
    return editionUndefinedMessage;
  }

  public IBasicMessage getUndefinedNameMessage() {
    return nameUndefinedMessage;
  }

  public IBasicMessage getUndefinedTypeMessage() {
    return typeUndefinedMessage;
  }
}
