package net.sf.anathema.character.main.magic.basic.cost;

public class CostImpl implements Cost {

  public static final Cost NULL_COST = new CostImpl("0", "", false);
  private final String text;
  private final String costString;
  private final boolean permanent;

  public CostImpl(String costString, String text, boolean permanent) {
    this.costString = costString;
    this.text = text;
    this.permanent = permanent;
  }

  @Override
  public String getCost() {
    return costString;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public boolean isPermanent() {
    return permanent;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CostImpl)) {
      return false;
    }
    CostImpl other = (CostImpl) obj;
    return other.costString.equals(this.costString) && other.text.equals(this.text) && other.permanent == this.permanent;
  }

  @Override
  public int hashCode() {
    return text.hashCode() + 37 * costString.hashCode();
  }
}