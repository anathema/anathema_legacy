package net.sf.anathema.character.presenter.magic.filter;

public class Enablement {

  private boolean enabled = false;

  public boolean isEnabled() {
    return enabled;
  }

  public void enable() {
    this.enabled = true;
  }

  public boolean isEqualTo(Enablement other) {
    return enabled == other.enabled;
  }

  public void setValueTo(Enablement other) {
    enabled = other.enabled;
  }

  public void setValueTo(boolean other) {
    enabled = other;
  }
}
