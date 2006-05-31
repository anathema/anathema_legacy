package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class MagicSource implements IMagicSource {

  private final String source;
  private final String page;
  private IExaltedEdition edition;
  public static final IMagicSource CUSTOM_SOURCE = new MagicSource(CUSTOM_SOURCE_NAME, null);

  public MagicSource(String source, String page) {
    this(source, page, null);
  }

  public MagicSource(String source, String page, IExaltedEdition edition) {
    if (source == null) {
      source = CUSTOM_SOURCE_NAME;
      page = null;
    }
    this.source = source;
    this.page = page;
    this.edition = edition;
  }

  public String getSource() {
    return source;
  }

  public String getPage() {
    return page;
  }

  public IExaltedEdition getEdition() {
    return edition;
  }
}