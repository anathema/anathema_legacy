package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmTypeBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.type.*;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharmTypeBuilderTest {

  private CharmTypeBuilder builder = new CharmTypeBuilder();

  @Test(expected = CharmException.class)
  public void testTypeRequired() throws Exception {
    String xml = "<charm></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    builder.build(element);
  }

  @Test(expected = CharmException.class)
  public void testMangledType() throws Exception {
    String xml = "<charm><charmtype type=\"simp\"/></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    builder.build(element);
  }

  @Test
  public void testBuildSupplementalCharm() throws Exception {
    String xml = "<charm><charmtype type=\"Supplemental\"/></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertEquals(CharmType.Supplemental, model.getCharmType());
  }

  @Test
  public void testNullSpecialModel() throws Exception {
    String xml = "<charm><charmtype type=\"ExtraAction\"><special speed=\"6\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  @Test
  public void testReflexiveSpecialModelWithOneStep() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"><special primaryStep=\"5\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertTrue(IReflexiveSpecialsModel.class.isInstance(model.getSpecialsModel()));
    IReflexiveSpecialsModel specialModel = (IReflexiveSpecialsModel) model.getSpecialsModel();
    assertEquals(new Integer(5), specialModel.getPrimaryStep());
  }

  @Test
  public void testReflexiveSpecialModelWithTwoSteps() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"><special primaryStep=\"5\" secondaryStep=\"7\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertTrue(IReflexiveSpecialsModel.class.isInstance(model.getSpecialsModel()));
    IReflexiveSpecialsModel specialModel = (IReflexiveSpecialsModel) model.getSpecialsModel();
    assertEquals(new Integer(5), specialModel.getPrimaryStep());
    assertEquals(new Integer(7), specialModel.getSecondaryStep());
  }

  @Test
  public void testReflexiveWithoutSpecial() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  @Test
  public void testSimpleWithoutSpecial() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertNull(model.getSpecialsModel());
  }

  @Test
  public void testSimpleSpecialModel() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"><special speed=\"4\" turntype=\"LongTick\" defense=\"-2\"/></charmtype></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ICharmTypeModel model = builder.build(element);
    assertTrue(ISimpleSpecialsModel.class.isInstance(model.getSpecialsModel()));
    ISimpleSpecialsModel specialModel = (ISimpleSpecialsModel) model.getSpecialsModel();
    assertEquals(4, specialModel.getSpeed());
    assertEquals(TurnType.LongTick, specialModel.getTurnType());
    assertEquals(-2, specialModel.getDefenseModifier());
  }
}