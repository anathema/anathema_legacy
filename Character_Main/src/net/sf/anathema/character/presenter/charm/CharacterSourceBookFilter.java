package net.sf.anathema.character.presenter.charm;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.collection.ListOrderedSet;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.addAll;

public class CharacterSourceBookFilter extends SourceBookCharmFilter {

  private static final String TAG_FILTERNAME = "SourceFilter";
  private static final String TAG_SOURCEBOOK = "SourceBook";
  private static final String ATTRIB_NAME = "name";
  private static final String ATTRIB_EDITION = "edition";
  private static final String ATTRIB_SHOWPREREQ = "showprereqs";

  private ICharmConfiguration characterSet;

  public CharacterSourceBookFilter(IExaltedEdition edition, ICharmConfiguration characterSet) {
    super(edition);
    this.characterSet = characterSet;
    prepareEdition(edition);
  }

  @Override
  protected boolean mustBeShownDueToCircumstance(ICharm charm) {
    for (ICharm learnedCharm : characterSet.getLearnedCharms(true)) {
      if (learnedCharm == charm) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected List<IExaltedSourceBook> getBooks(IExaltedEdition edition) {
    List<ICharm> allCharms = getAllCharmsAvailable();
    return getSourceBooksFromCharms(allCharms);
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
    List<ILearningCharmGroup> allGroups = newArrayList(characterSet.getAllGroups());
    List<ICharm> allCharms = newArrayList();
    for (ILearningCharmGroup group : allGroups) {
      addAll(allCharms, group.getAllCharms());
    }
    return allCharms;
  }

  private List<IExaltedSourceBook> reduceToUniqueBooks(List<IExaltedSourceBook> allBooks) {
    Set<IExaltedSourceBook> uniqueBooks = new ListOrderedSet<IExaltedSourceBook>();
    uniqueBooks.addAll(allBooks);
    return new ArrayList<IExaltedSourceBook>(uniqueBooks);
  }
}