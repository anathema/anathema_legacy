package net.sf.anathema.charmentry.model.data;

public class ConfigurableCost implements IConfigurableCost {

  private String value;
  private String text;
  private boolean permanent;

  public String getCost() {
    return value;
  }

  public String getText() {
    return text;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean isPermanent() {
    return permanent;
  }

  public void setPermanent(boolean permanent) {
    this.permanent = permanent;
  }
}