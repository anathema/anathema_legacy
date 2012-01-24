package net.sf.anathema.initialization;

import net.sf.anathema.framework.extension.IAnathemaExtension;

public class ExtensionWithId {
  
  public final String id;
  public final IAnathemaExtension extension;

  public ExtensionWithId(String id, IAnathemaExtension extension) {
    this.id = id;
    this.extension = extension;
  }
}