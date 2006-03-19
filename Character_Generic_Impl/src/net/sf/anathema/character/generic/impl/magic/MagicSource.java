package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.general.IMagicSource;

public class MagicSource implements IMagicSource {

  private static final String CUSTOM_SOURCE_NAME = "Custom"; //$NON-NLS-1$
  private final String source;
  private final String page;
  public static final IMagicSource CUSTOM_SOURCE = new MagicSource(CUSTOM_SOURCE_NAME, null);

  public MagicSource(String source, String page) {
    if (source == null) {
      source = CUSTOM_SOURCE_NAME;
      page = null;
    }
    this.source = source;
    this.page = page;
  }

  public String getSource() {
    return source;
  }

  public String getPage() {
    return page;
  }
}