package net.sf.anathema.lib.workflow.wizard.selection;

public class LenientLegalityProvider<V> implements ILegalityProvider<V> {

  @Override
  public boolean isLegal(Object value) {
    return true;
  }
}