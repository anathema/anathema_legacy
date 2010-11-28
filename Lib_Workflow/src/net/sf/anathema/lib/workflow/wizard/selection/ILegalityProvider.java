package net.sf.anathema.lib.workflow.wizard.selection;

public interface ILegalityProvider<V> {

  public boolean isLegal(V value);
}