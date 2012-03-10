package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.filters.SourceBookCharmFilterPage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SourceBookCharmFilter implements ICharmFilter {

  private final Map<IExaltedEdition, ArrayList<IExaltedSourceBook>> allMaterial = new HashMap<IExaltedEdition, ArrayList<IExaltedSourceBook>>();
  protected final Map<IExaltedEdition, ArrayList<IExaltedSourceBook>> excludedMaterial = new HashMap<IExaltedEdition, ArrayList<IExaltedSourceBook>>();
  private IExaltedEdition edition;
  protected boolean includePrereqs = true;

  private ArrayList<IExaltedSourceBook> workingExcludedMaterial;
  private boolean[] workingIncludePrereqs = new boolean[1];

  public SourceBookCharmFilter(IExaltedEdition edition) {
    this.edition = edition;
    for (ExaltedEdition thisEdition : ExaltedEdition.values()) {
      prepareEdition(thisEdition);
    }
  }

  private List<IExaltedSourceBook> prepareEdition(IExaltedEdition edition) {
    ArrayList<IExaltedSourceBook> materialList = new ArrayList<IExaltedSourceBook>();
    IExaltedSourceBook[] bookSet = ExaltedSourceBook.getSourcesForEdition(edition);
    Collections.addAll(materialList, bookSet);
    allMaterial.put(edition, materialList);
    ArrayList<IExaltedSourceBook> materialExcluded = new ArrayList<IExaltedSourceBook>();
    excludedMaterial.put(edition, materialExcluded);
    return excludedMaterial.get(edition);
  }

  private ArrayList<IExaltedSourceBook> getApprovedList(IExaltedEdition edition) {
    ArrayList<IExaltedSourceBook> approvedMaterial = new ArrayList<IExaltedSourceBook>(allMaterial.get(edition));
    approvedMaterial.removeAll(excludedMaterial.get(edition));
    return approvedMaterial;
  }

  @Override
  public boolean acceptsCharm(ICharm charm, boolean isAncestor) {
    if (isAncestor && includePrereqs) {
      return true;
    }
    if (mustBeShownDueToCircumstance(charm)) {
      return true;
    }
    IExaltedEdition edition = charm.getPrimarySource().getEdition();
    List<IExaltedSourceBook> excludedSourceList = excludedMaterial.get(edition);
    return !excludedSourceList.contains(charm.getPrimarySource());
  }

  protected abstract boolean mustBeShownDueToCircumstance(ICharm charm);

  @Override
  public JPanel getFilterPreferencePanel(IResources resources) {
    workingExcludedMaterial = new ArrayList<IExaltedSourceBook>(excludedMaterial.get(getEdition()));
    workingIncludePrereqs[0] = includePrereqs;
    SourceBookCharmFilterPage page = new SourceBookCharmFilterPage(resources, getApprovedList(getEdition()),
            workingExcludedMaterial, workingIncludePrereqs);
    return page.getContent();
  }

  @Override
  public void apply() {
    excludedMaterial.put(getEdition(), workingExcludedMaterial);
    includePrereqs = workingIncludePrereqs[0];
    reset();
  }

  @Override
  public boolean isDirty() {
    return workingExcludedMaterial != null || includePrereqs != workingIncludePrereqs[0];
  }

  @Override
  public void reset() {
    workingExcludedMaterial = null;
  }

  protected IExaltedEdition getEdition() {
    return edition;
  }
}