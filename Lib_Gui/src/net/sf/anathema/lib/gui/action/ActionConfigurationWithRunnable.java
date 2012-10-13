package net.sf.anathema.lib.gui.action;

public interface ActionConfigurationWithRunnable extends IActionConfiguration, Enabler {
  Runnable getRunnable();
}