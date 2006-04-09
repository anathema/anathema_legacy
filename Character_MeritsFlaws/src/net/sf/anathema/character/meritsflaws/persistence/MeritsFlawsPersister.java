package net.sf.anathema.character.meritsflaws.persistence;

import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_CREATION_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_EXPERIENCE_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_ID;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.TAG_CATEGORIZATION;

import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.persistence.AbstractQualityPersister;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.MultiValuePerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;
import net.sf.anathema.character.meritsflaws.presenter.IPerkVisitor;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class MeritsFlawsPersister extends AbstractQualityPersister<IPerk> {

  private static final String TAG_MERITSFLAWS = "meritsFlaws"; //$NON-NLS-1$
  private static final String TAG_PERK = "perk"; //$NON-NLS-1$
  private static final String ATTRIB_CATEGORY = "category"; //$NON-NLS-1$

  @Override
  protected void saveSpecials(Element parent, IPerk perk) {
    Element categorizationElement = parent.element(TAG_CATEGORIZATION);
    categorizationElement.addAttribute(ATTRIB_CATEGORY, perk.getCategory().getId());
    perk.accept(new IPerkVisitor() {
      public void visitMultiValuePerk(MultiValuePerk visitedPerk) {
        // Nothing to do
      }
    });
  }

  public void load(Element parent, IQualityModel<IPerk> model) throws PersistenceException {
    Element perksElement = parent.element(getGroupElementName());
    if (perksElement == null) {
      return;
    }
    final IMeritsFlawsModel meritsFlawsModel = (IMeritsFlawsModel) model;
    for (final Element savedSelectionElement : ElementUtilities.elements(perksElement)) {
      String perkId = savedSelectionElement.attributeValue(ATTRIB_ID);
      Element categorizationElement = savedSelectionElement.element(TAG_CATEGORIZATION);
      String typeId = categorizationElement.attributeValue(ATTRIB_TYPE);
      PerkType type = PerkType.valueOf(typeId);
      String categoryId = categorizationElement.attributeValue(ATTRIB_CATEGORY);
      PerkCategory category = PerkCategory.valueOf(categoryId);
      final int value = ElementUtilities.getRequiredIntAttrib(savedSelectionElement, ATTRIB_VALUE);
      final boolean creationActive = ElementUtilities.getBooleanAttribute(
          savedSelectionElement,
          ATTRIB_CREATION_ACTIVE,
          true);
      final boolean experienceActive = ElementUtilities.getBooleanAttribute(
          savedSelectionElement,
          ATTRIB_EXPERIENCE_ACTIVE,
          false);
      meritsFlawsModel.getPerk(perkId, type, category).accept(new IPerkVisitor() {
        public void visitMultiValuePerk(MultiValuePerk visitedPerk) {
          meritsFlawsModel.addQualitySelection(new QualitySelection<IPerk>(
              visitedPerk,
              value,
              creationActive,
              experienceActive));
        }
      });
    }
  }

  @Override
  protected String getSingleElementName() {
    return TAG_PERK;
  }

  @Override
  protected String getGroupElementName() {
    return TAG_MERITSFLAWS;
  }
}