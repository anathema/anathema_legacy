package net.sf.anathema.hero.charms.persistence;

import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPto;

import java.util.ArrayList;
import java.util.List;

public class CharmListPto {

  public List<CharmGroupPto> groups = new ArrayList<>();
  public List<SpecialCharmPto> charmSpecials = new ArrayList<>();
}
