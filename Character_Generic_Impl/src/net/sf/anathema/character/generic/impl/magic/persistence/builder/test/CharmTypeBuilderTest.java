package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmTypeBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class CharmTypeBuilderTest extends BasicTestCase {

  private CharmTypeBuilder builder = new CharmTypeBuilder();

  public void testTypeRequired() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm></charm>"; //$NON-NLS-1$
        Element element = DocumentUtilities.read(xml).getRootElement();
        builder.build(element);
      }
    });
  }

  public void testMangledType() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm><charmtype type=\"simp\"/></charm>"; //$NON-NLS-1$
        Element element = DocumentUtilities.read(xml).getRootElement();
        builder.build(element);
      }
    });
  }

  public void testBuildSupplementalCharm() throws Exception {
    String xml = "<charm><charmtype type=\"Supplemental\"/></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertEquals(CharmType.Supplemental, model.getCharmType());
  }

  public void testNullSpecialModel() throws Exception {
    String xml = "<charm><charmtype type=\"ExtraAction\"><special speed=\"6\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  public void testReflexiveSpecialModelWithOneStep() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"><special primaryStep=\"5\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertIsAssignableFrom(IReflexiveSpecialsModel.class, model.getSpecialsModel().getClass());
    IReflexiveSpecialsModel specialModel = (IReflexiveSpecialsModel) model.getSpecialsModel();
    assertEquals(5, specialModel.getPrimaryStep());
  }

  public void testReflexiveSpecialModelWithTwoSteps() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"><special primaryStep=\"5\" secondaryStep=\"7\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertIsAssignableFrom(IReflexiveSpecialsModel.class, model.getSpecialsModel().getClass());
    IReflexiveSpecialsModel specialModel = (IReflexiveSpecialsModel) model.getSpecialsModel();
    assertEquals(5, specialModel.getPrimaryStep());
    assertEquals(new Integer(7), specialModel.getSecondaryStep());
  }

  public void testReflexiveWithoutSpecial() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  public void testSimpleWithoutSpecial() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  public void testSimpleSpecialModel() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"><special speed=\"4\" turntype=\"LongTick\" defense=\"-2\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertIsAssignableFrom(ISimpleSpecialsModel.class, model.getSpecialsModel().getClass());
    ISimpleSpecialsModel specialModel = (ISimpleSpecialsModel) model.getSpecialsModel();
    assertEquals(4, specialModel.getSpeed());
    assertEquals(TurnType.LongTick, specialModel.getTurnType());
    assertEquals(-2, specialModel.getDefenseModifier());
  }
}