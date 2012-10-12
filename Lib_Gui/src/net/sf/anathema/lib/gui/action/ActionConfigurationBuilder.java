package net.sf.anathema.lib.gui.action;

import javax.swing.Icon;

public class ActionConfigurationBuilder {
  private String name;
  private Icon icon;
  private String tooltip;
  private Runnable runnable;

  public static ActionConfigurationBuilder anAction() {
    return new ActionConfigurationBuilder();
  }

  public ActionConfigurationBuilder called(String name){
    this.name = name;
    return this;
  }

  public ActionConfigurationBuilder symbolizedBy(Icon icon){
    this.icon = icon;
    return this;
  }

  public ActionConfigurationBuilder describedBy(String tooltip) {
    this.tooltip = tooltip;
    return this;
  }

  public ActionConfigurationBuilder doing(Runnable runnable) {
    this.runnable = runnable;
    return this;
  }

  public ActionConfigurationWithRunnable build() {
    return new RunnableActionConfiguration(name, icon, tooltip, runnable);
  }
}
