package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.filters.SourceBookCharmFilterPage;
import net.sf.anathema.lib.resources.IResources;
import org.dom4j.Element;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceBookCharmFilter implements ICharmFilter {

  private static final String TAG_FILTERNAME = "SourceFilter";
  private static final String TAG_SOURCEBOOK = "SourceBook";
  private static final String ATTRIB_NAME = "name";
  private static final String ATTRIB_EDITION = "edition";
  private static final String ATTRIB_SHOWPREREQ = "showprereqs";

  private final Map<IExaltedEdition, ArrayList<IExaltedSourceBook>> allMaterial = new HashMap<IExaltedEdition, ArrayList<IExaltedSourceBook>>();
  private final Map<IExaltedEdition, ArrayList<IExaltedSourceBook>> excludedMaterial = new HashMap<IExaltedEdition, ArrayList<IExaltedSourceBook>>();
  private IExaltedEdition edition;
  private ICharmConfiguration characterSet;
  private boolean includePrereqs = true;

  private ArrayList<IExaltedSourceBook> workingExcludedMaterial;
  private boolean[] workingIncludePrereqs = new boolean[1];

  public SourceBookCharmFilter(IExaltedEdition edition) {
    this(edition, null);
  }

  public SourceBookCharmFilter(IExaltedEdition edition, ICharmConfiguration characterSet) {
    this.edition = edition;
    this.characterSet = characterSet;
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
    if (isAncestor && includePrereqs) return true;
    if (characterSet != null) for (ICharm learnedCharm : characterSet.getLearnedCharms(true))
      if (learnedCharm == charm) return true;
    IExaltedEdition edition = charm.getPrimarySource().getEdition();
    List<IExaltedSourceBook> excludedSourceList = excludedMaterial.get(edition);
    return !excludedSourceList.contains(charm.getPrimarySource());
  }

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

  @Override
  public void save(Element parent) {
    Element sourceBookFilter = parent.addElement(TAG_FILTERNAME);
    for (IExaltedEdition edition : ExaltedEdition.values()) {
      List<IExaltedSourceBook> list = excludedMaterial.get(edition);
      if (list != null) for (IExaltedSourceBook book : list) {
        Element bookElement = sourceBookFilter.addElement(TAG_SOURCEBOOK);
        bookElement.addAttribute(ATTRIB_NAME, book.getId());
        bookElement.addAttribute(ATTRIB_EDITION, edition.getId());
      }
    }
    sourceBookFilter.addAttribute(ATTRIB_SHOWPREREQ, includePrereqs ? "true" : "false");
  }

  @Override
  public boolean load(Element node) {
    if (node.getName().equals(TAG_FILTERNAME)) {
      for (Object bookNode : node.elements()) {
        try {
          Element sourceBook = (Element) bookNode;
          String editionString = sourceBook.attributeValue(ATTRIB_EDITION);
          String idString = sourceBook.attributeValue(ATTRIB_NAME);
          IExaltedEdition edition = ExaltedEdition.valueOf(editionString);
          IExaltedSourceBook book = ExaltedSourceBook.valueOf(idString);
          excludedMaterial.get(edition).add(book);
        } catch (Exception e) {
          excludedMaterial.get(getEdition()).clear();
          return false;
        }
      }
      includePrereqs = node.attributeValue(ATTRIB_SHOWPREREQ).equals("true");
      return true;
    }
    return false;
  }

  protected IExaltedEdition getEdition() {
    return edition;
  }
}