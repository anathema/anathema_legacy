package net.sf.anathema.framework.module;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.sf.anathema.framework.repository.AnathemaDialogProperties;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.resources.IResources;

public final class DefaultObjectSelectionProperties extends AnathemaDialogProperties implements
    IObjectSelectionProperties {
  final String loadMessageKey;
  final String titleKey;

  public DefaultObjectSelectionProperties(IResources resources, String loadMessageKey, String titleKey) {
    super(resources);
    this.loadMessageKey = loadMessageKey;
    this.titleKey = titleKey;
  }

  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(getResources().getString(loadMessageKey), MessageType.NORMAL);
  }

  public String getTitle() {
    return getResources().getString(titleKey);
  }
}