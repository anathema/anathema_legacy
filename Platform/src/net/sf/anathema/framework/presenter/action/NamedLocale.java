package net.sf.anathema.framework.presenter.action;

import java.util.Locale;

import net.sf.anathema.lib.util.IIdentificate;

public enum NamedLocale implements IIdentificate {

  English {
    @Override
    public Locale getLocale() {
      return Locale.ENGLISH;
    }
  },

  Spanish {
    @Override
    public Locale getLocale() {
      return new Locale("es", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
  };
  public abstract Locale getLocale();

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}