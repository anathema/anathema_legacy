package net.sf.anathema.character.presenter.magic;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.filters.SourceBookCharmFilterPage;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.addAll;

public abstract class SourceBookCharmFilter implements ICharmFilter {

  private final ArrayList<IExaltedSourceBook> allMaterial = new ArrayList<IExaltedSourceBook>();
  protected final ArrayList<IExaltedSourceBook> excludedMaterial = new ArrayList<IExaltedSourceBook>();
  protected boolean includePrereqs = true;

  private ArrayList<IExaltedSourceBook> workingExcludedMaterial;
  private boolean[] workingIncludePrereqs = new boolean[1];

  protected void prepareEdition() {
    List<IExaltedSourceBook> bookSet = getBooks();
    allMaterial.addAll(bookSet);
  }

  private List<IExaltedSourceBook> getBooks() {
    List<ICharm> allCharms = getAllCharmsAvailable();
    return getSourceBooksFromCharms(allCharms);
  }

  @Override
  public boolean acceptsCharm(ICharm charm, boolean isAncestor) {
    if (isAncestor && includePrereqs) {
      return true;
    }
    if (mustBeShownDueToCircumstance(charm)) {
      return true;
    }
    return !isExcluded(charm.getPrimarySource());
  }

  private boolean isExcluded(IExaltedSourceBook primarySource) {
    List<IExaltedSourceBook> excludedSourceList = excludedMaterial;
    return excludedSourceList.contains(primarySource);
  }

  protected abstract boolean mustBeShownDueToCircumstance(ICharm charm);

  @Override
  public JPanel getFilterPreferencePanel(IResources resources) {
    workingExcludedMaterial = new ArrayList<IExaltedSourceBook>(excludedMaterial);
    workingIncludePrereqs[0] = includePrereqs;
    SourceBookCharmFilterPage page = new SourceBookCharmFilterPage(resources, getApprovedList(),
            workingExcludedMaterial, workingIncludePrereqs);
    return page.getContent();
  }

  private List<IExaltedSourceBook> getApprovedList() {
    List<IExaltedSourceBook> approvedMaterial = new ArrayList<IExaltedSourceBook>(allMaterial);
    approvedMaterial.removeAll(excludedMaterial);
    return approvedMaterial;
  }

  @Override
  public void apply() {
    excludedMaterial.clear();
    excludedMaterial.addAll(workingExcludedMaterial);
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

  private List<IExaltedSourceBook> getSourceBooksFromCharms(List<ICharm> allCharms) {
    List<IExaltedSourceBook> allBooks = Lists.transform(allCharms, new Function<ICharm, IExaltedSourceBook>() {
      @Override
      public IExaltedSourceBook apply(ICharm input) {
        return input.getPrimarySource();
      }
    });
    return reduceToUniqueBooks(allBooks);
  }

  private List<ICharm> getAllCharmsAvailable() {
    List<ICharmGroup> allGroups = getAllCharmGroups();
    List<ICharm> allCharms = newArrayList();
    for (ICharmGroup group : allGroups) {
      addAll(allCharms, group.getAllCharms());
    }
    return allCharms;
  }

  private List<IExaltedSourceBook> reduceToUniqueBooks(List<IExaltedSourceBook> allBooks) {
    Set<IExaltedSourceBook> uniqueBooks = new ListOrderedSet<IExaltedSourceBook>();
    uniqueBooks.addAll(allBooks);
    return new ArrayList<IExaltedSourceBook>(uniqueBooks);
  }

  protected abstract List<ICharmGroup> getAllCharmGroups();
}
