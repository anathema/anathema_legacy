package net.sf.anathema.swing.hero.traitview;

import net.miginfocom.layout.CC;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigurableLayout {
  public static ConfigurableLayout Right() {
    return new ConfigurableLayout(new CC().alignX("right"), new CC().growX().pushX());
  }

  public static ConfigurableLayout Left() {
    return new ConfigurableLayout(new CC().alignX("left"), new CC());
  }

  private final CC labelAlignment;
  private final CC dotAlignment;
  public JLabel label;
  public JComponent display;

  public ConfigurableLayout(CC dotAlignment, CC labelAlignment) {
    this.labelAlignment = labelAlignment;
    this.dotAlignment = dotAlignment;
  }

  public ConfigurableLayout addLabel(JLabel jLabel) {
    this.label = jLabel;
    return this;
  }

  public ConfigurableLayout addDisplay(JComponent component) {
    this.display = component;
    return this;
  }

  public void toPanel(JPanel panel) {
    panel.add(label, labelAlignment);
    panel.add(display, dotAlignment);
  }
}