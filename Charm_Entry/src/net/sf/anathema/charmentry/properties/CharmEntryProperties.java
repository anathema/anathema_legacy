package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryProperties {

  private final IResources resources;
  private final BasicMessage nameUndefinedMessage;
  private final BasicMessage editionUndefinedMessage;
  private final BasicMessage typeUndefinedMessage;
  private final IBasicMessage basicDataMessage;

  public CharmEntryProperties(IResources resources) {
    this.resources = resources;
    nameUndefinedMessage = new BasicMessage(resources.getString("CharmEntry.HeaderData.Message.UndefinedName")); //$NON-NLS-1$
    editionUndefinedMessage = new BasicMessage(resources.getString("CharmEntry.HeaderData.Message.UndefinedEdition")); //$NON-NLS-1$
    typeUndefinedMessage = new BasicMessage(resources.getString("CharmEntry.HeaderData.Message.UndefinedType")); //$NON-NLS-1$
    basicDataMessage = new BasicMessage(resources.getString("CharmEntry.HeaderData.Message.EnterData")); //$NON-NLS-1$
  }

  public String getHeaderDataTitle() {
    return resources.getString("CharmEntry.HeaderData.Title"); //$NON-NLS-1$
  }

  public String getCharacterTypeLabel() {
    return resources.getString("CharmEntry.HeaderData.CharacterType"); //$NON-NLS-1$
  }

  public String getCharmNameLabel() {
    return resources.getString("CharmEntry.HeaderData.CharmName"); //$NON-NLS-1$
  }

  public String getEditionLabel() {
    return resources.getString("CharmEntry.HeaderData.Edition"); //$NON-NLS-1$
  }

  public IBasicMessage getHeaderDataMessage() {
    return basicDataMessage;
  }

  public String getBookLabel() {
    return resources.getString("CharmEntry.HeaderData.Book"); //$NON-NLS-1$
  }

  public String getPageLabel() {
    return resources.getString("CharmEntry.HeaderData.Page"); //$NON-NLS-1$
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
