package net.sf.anathema.hero.charms.persistence;

import net.sf.anathema.hero.charms.persistence.special.CharmSpecialsPto;

import java.util.ArrayList;
import java.util.List;

public class CharmListPto {

  public List<CharmPto> charms = new ArrayList<>();
  public List<CharmSpecialsPto> charmSpecials = new ArrayList<>();
}
