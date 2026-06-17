package net.sf.anathema.framework.fx.compat;

/**
 * Compatibility replacement for {@code org.controlsfx.dialog.DialogStyle}, which was removed in
 * ControlsFX 8.20+. Anathema only ever used {@link #NATIVE}; the value is now a no-op marker kept
 * so existing call sites compile unchanged.
 */
public enum DialogStyle {
  NATIVE, CROSS_PLATFORM, UNDECORATED
}
