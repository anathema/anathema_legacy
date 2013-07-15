package net.sf.anathema.hero.equipment.persister;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPto {

  public String templateId;
  public String customTitle;
  public String description;
  public String material;
  public List<EquipmentStatsPto> printStats = new ArrayList<>();
}
