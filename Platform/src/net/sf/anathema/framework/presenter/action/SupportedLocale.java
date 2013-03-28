package net.sf.anathema.framework.presenter.action;

import java.util.Locale;

public enum SupportedLocale {

  English(Locale.ENGLISH),

  Italian(Locale.ITALIAN),

  Spanish(new Locale("es", "", "")),
      
  Portuguese(new Locale("pt", "", ""));

  private final Locale locale;

  private SupportedLocale(Locale locale) {
    this.locale = locale;
  }

  public Locale getLocale() {
    return locale;
  }
}