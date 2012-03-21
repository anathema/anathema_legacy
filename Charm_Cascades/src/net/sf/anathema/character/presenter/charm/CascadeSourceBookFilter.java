package net.sf.anathema.character.presenter.charm;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.presenter.magic.SourceBookCharmFilter;
import net.sf.anathema.charmtree.presenter.CharmGroupCollection;
import org.dom4j.Element;

import java.util.List;

public class CascadeSourceBookFilter extends SourceBookCharmFilter {
  private final CharmGroupCollection charmGroups;


  public CascadeSourceBookFilter(CharmGroupCollection charmGroups) {
    super(ExaltedEdition.SecondEdition);
    this.charmGroups = charmGroups;
    prepareEdition(ExaltedEdition.SecondEdition);
  }

  @Override
  protected List<ICharmGroup> getAllCharmGroups() {
    return Lists.newArrayList(charmGroups.getCharmGroups());
  }

  @Override
  protected boolean mustBeShownDueToCircumstance(ICharm charm) {
    return false;
  }

  @Override
  public void save(Element parent) {
    //nothing to do, applies to characters only
  }

  @Override
  public boolean load(Element node) {
    //applies to characters only
    return false;
  }
}
