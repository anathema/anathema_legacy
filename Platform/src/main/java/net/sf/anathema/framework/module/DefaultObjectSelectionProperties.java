package net.sf.anathema.framework.module;

import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

public final class DefaultObjectSelectionProperties implements IObjectSelectionProperties {
  private Resources resources;
  final String loadMessageKey;
  final String titleKey;

  public DefaultObjectSelectionProperties(Resources resources, String loadMessageKey, String titleKey) {
    this.resources = resources;
    this.loadMessageKey = loadMessageKey;
    this.titleKey = titleKey;
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(resources.getString(loadMessageKey), MessageType.NORMAL);
  }

  @Override
  public String getTitle() {
    return resources.getString(titleKey);
  }
}