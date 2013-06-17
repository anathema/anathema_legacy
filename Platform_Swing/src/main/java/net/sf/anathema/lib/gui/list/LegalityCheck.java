package net.sf.anathema.lib.gui.list;

public interface LegalityCheck<T> {

  boolean isLegal(T object);
}