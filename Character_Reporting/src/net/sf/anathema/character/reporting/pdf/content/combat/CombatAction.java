package net.sf.anathema.character.reporting.pdf.content.combat;

public class CombatAction {
  public String name;
  public String speed;
  public String dv;

  public CombatAction(String name, String speed, String dv) {
    this.name = name;
    this.speed = speed;
    this.dv = dv;
  }
}
