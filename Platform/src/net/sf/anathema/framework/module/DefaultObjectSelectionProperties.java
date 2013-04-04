package net.sf.anathema.framework.module;

import net.sf.anathema.framework.repository.AnathemaDialogProperties;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

public final class DefaultObjectSelectionProperties extends AnathemaDialogProperties implements
    IObjectSelectionProperties {
  final String loadMessageKey;
  final String titleKey;

  public DefaultObjectSelectionProperties(Resources resources, String loadMessageKey, String titleKey) {
    super(resources);
    this.loadMessageKey = loadMessageKey;
    this.titleKey = titleKey;
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(getResources().getString(loadMessageKey), MessageType.NORMAL);
  }

  @Override
  public String getTitle() {
    return getResources().getString(titleKey);
  }
}