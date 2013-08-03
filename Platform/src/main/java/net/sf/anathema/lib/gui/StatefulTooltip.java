package net.sf.anathema.lib.gui;

public interface StatefulTooltip extends ConfigurableTooltip {
  void reset();

  void apply();
}