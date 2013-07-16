package net.sf.anathema.character.main.magic.model.source;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class SourceBookImpl extends ReflectionEqualsObject implements SourceBook {

  private String id;

  public SourceBookImpl(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
