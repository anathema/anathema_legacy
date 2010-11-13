package net.sf.anathema.framework.presenter.action;

import java.util.Locale;

public enum SupportedLocale {

  English(Locale.ENGLISH),

  Italian(Locale.ITALIAN),

  Spanish(new Locale("es", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

  private final Locale locale;

  private SupportedLocale(Locale locale) {
    this.locale = locale;
  }

  public Locale getLocale() {
    return locale;
  }
}