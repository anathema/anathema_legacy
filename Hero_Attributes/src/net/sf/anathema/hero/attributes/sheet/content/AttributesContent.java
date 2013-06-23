package net.sf.anathema.hero.attributes.sheet.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.List;

public class AttributesContent extends AbstractSubBoxContent {

  private AttributesPrintModel attributeModel;

  public AttributesContent(Hero hero, IGenericCharacter character, Resources resources) {
    super(resources);
    this.attributeModel = new AttributesPrintModel(character, hero);
  }

  public int getTraitMaximum() {
    return attributeModel.getTraitMaximum();
  }

  @Override
  public String getHeaderKey() {
    return "Attributes";
  }

  public List<PrintAttributeGroup> getAttributeGroups() {
    PrintAttributeIterator iterator = new PrintAttributeIterator(getResources(), attributeModel);
    attributeModel.iterate(iterator);
    return iterator.groups;
  }
}
