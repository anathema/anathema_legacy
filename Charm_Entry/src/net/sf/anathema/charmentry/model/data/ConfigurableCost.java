package net.sf.anathema.charmentry.model.data;

public class ConfigurableCost implements IConfigurableCost {

  private String value;
  private String text;
  private boolean permanent;

  @Override
  public String getCost() {
    return value;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
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