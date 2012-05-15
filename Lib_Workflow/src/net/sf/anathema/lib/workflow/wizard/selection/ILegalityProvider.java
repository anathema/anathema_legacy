package net.sf.anathema.lib.workflow.wizard.selection;

public interface ILegalityProvider<V> {

  boolean isLegal(V value);
}