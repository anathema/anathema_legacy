package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class ProxyRuleSet implements IExaltedRuleSet {

  private IExaltedRuleSet delegate;

  public void setDelegate(IExaltedRuleSet delegate) {
    this.delegate = delegate;
  }

  @Override
  public IExaltedEdition getEdition() {
    return delegate.getEdition();
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  public boolean hasDelegate() {
    return delegate != null;
  }

  public IExaltedRuleSet getDelegate() {
    return delegate;
  }

  public boolean equals(Object obj) {
    if (obj instanceof IExaltedRuleSet && hasDelegate()) {
      return delegate.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (hasDelegate()) return delegate.hashCode();
    return super.hashCode();
  }
}