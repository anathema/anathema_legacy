package net.sf.anathema.lib.workflow.wizard.selection;

public class LenientLegalityProvider<V> implements ILegalityProvider<V> {

  public boolean isLegal(Object value) {
    return true;
  }
}