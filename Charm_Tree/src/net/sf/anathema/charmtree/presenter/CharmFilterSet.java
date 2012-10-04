package net.sf.anathema.charmtree.presenter;

import com.google.common.collect.Lists;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class CharmFilterSet {
  private List<ICharmFilter> filterSet = new ArrayList<ICharmFilter>();

  public void init(Iterable<ICharmFilter> filters) {
    filterSet = Lists.newArrayList(filters);
  }

  public void resetAllFilters() {
    for (ICharmFilter filter : filterSet) {
      filter.reset();
    }
  }

  public void applyAllFilters() {
    for (ICharmFilter filter : filterSet) {
      filter.apply();
    }
  }

  public JComponent createFilterPanel(IResources resources) {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    for (ICharmFilter filter : filterSet) {
      panel.add(filter.getFilterPreferencePanel(resources));
    }
    return panel;

  }

  public boolean acceptsCharm(ICharm charm) {
    for (ICharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, false)) {
        return false;
      }
    }
    return true;
  }

  public boolean filterCharm(ICharm charm, boolean isAncestor) {
    for (ICharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, isAncestor)) {
        return false;
      }
    }
    return true;
  }
}
