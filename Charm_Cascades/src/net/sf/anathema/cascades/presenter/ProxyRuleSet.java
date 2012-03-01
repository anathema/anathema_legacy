package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;

public class ProxyRuleSet implements IExaltedRuleSet {

  private IExaltedRuleSet delegate;

  public void setDelegate(IExaltedRuleSet delegate) {
    this.delegate = delegate;
  }

  @Override
  public void accept(IRuleSetVisitor visitor) {
    delegate.accept(visitor);
  }

  @Override
  public IExaltedEdition getEdition() {
    return delegate.getEdition();
  }

  @Override
  public IExaltedRuleSet getBasicRuleset() {
    return delegate.getBasicRuleset();
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
}