package net.sf.anathema.development.character;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionWeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.intimacies.IntimaciesEncoder;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfSecondPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.solar.reporting.SecondEditionSolarPartEncoder;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.SolarVirtueFlawModel;
import net.sf.anathema.development.character.magic.DemoGenericCombo;
import net.sf.anathema.dummy.character.additional.DemoAlienArmour;
import net.sf.anathema.dummy.character.additional.DemoIntimacy;
import net.sf.anathema.dummy.character.equipment.DemoMeleeWeapon;
import net.sf.anathema.dummy.character.equipment.DemoNaturalArmour;
import net.sf.anathema.dummy.character.equipment.DemoRangeWeapon;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.lib.control.BrowserControl;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCharacterSheetDemo {

  public static void main(String[] args) {
    Document document = new Document(PageSize.A4);
    try {
      System.err.println(document.getPageSize());
      File outputStream = new File("iText.pdf");
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputStream));
      document.open();
      PdfContentByte directContent = writer.getDirectContent();
      IGenericCharacter character = createDemoCharacter(CharacterType.SOLAR);
      DemoGenericDescription description = createDemoDescription();
      IResources resources = createDemoResources();
      PdfPageConfiguration pageConfiguration = PdfPageConfiguration.create(document.getPageSize());
      PdfEncodingRegistry encodingRegistry = createEncodingRegistry(resources);
      PdfFirstPageEncoder partEncoder = new PdfFirstPageEncoder(new SecondEditionSolarPartEncoder(
          resources,
          encodingRegistry,
          7), encodingRegistry, resources, 7, pageConfiguration);
      partEncoder.encode(document, directContent, character, description);
      document.newPage();
      new PdfSecondPageEncoder(resources, encodingRegistry.getBaseFont(), pageConfiguration).encode(
          document,
          directContent,
          character,
          description);
      BrowserControl.displayUrl(outputStream.toURL());
    }
    catch (Exception de) {
      de.printStackTrace();
    }
    finally {
      document.close();
    }
  }

  private static PdfEncodingRegistry createEncodingRegistry(IResources resources) {
    PdfEncodingRegistry encodingRegistry = new PdfEncodingRegistry();
    encodingRegistry.setArmourContentEncoder(new SecondEditionArmourEncoder(resources, encodingRegistry.getBaseFont()));
    encodingRegistry.setWeaponContentEncoder(new SecondEditionWeaponryEncoder(resources, encodingRegistry.getBaseFont()));
    encodingRegistry.setIntimaciesEncoder(new IntimaciesEncoder(encodingRegistry.getBaseFont()));
    return encodingRegistry;
  }

  private static IResources createDemoResources() {
    Locale.setDefault(Locale.ENGLISH);
    IAnathemaResources resources = new AnathemaResources();
    return resources;
  }

  private static IGenericCharacter createDemoCharacter(CharacterType characterType) {
    DemoGenericCharacter character = new DemoGenericCharacter(characterType);
    character.getConcept().setConceptText("Tolles Konzept");
    character.getConcept().setCasteType(AbyssalCaste.Day);
    character.getConcept().setWillpowerRegainingConceptName("Pedestrian Motivation");
    character.setRuleSet(ExaltedRuleSet.SecondEdition);
    character.addSpecialtyTrait(AbilityType.Archery);
    character.addSpecialtyTrait(AbilityType.Bureaucracy);
    character.addSubbedTrait(AbilityType.Craft);
    character.setPainTolerance(3);
    character.addCombo(new DemoGenericCombo("Combo 1", new ICharm[] {
        new DummyCharm("FirstCharm.Id"),
        new DummyCharm("SecondCharm.Id") }));
    character.addCombo(new DemoGenericCombo("Combo Nummero 2", new ICharm[] {
        new DummyCharm("Und noch eine Charm Id"),
        new DummyCharm("Und noch eine") }));
    character.getEquipmentModel().addPrintArmour(new DemoNaturalArmour());
    character.getEquipmentModel().addPrintArmour(new DemoAlienArmour());
    character.getEquipmentModel().addPrintWeapon(new DemoRangeWeapon());
    character.getEquipmentModel().addPrintWeapon(new DemoMeleeWeapon());
    character.getIntimaciesModel().addEntry(new DemoIntimacy("Intimacy 1", 1, character));
    character.getIntimaciesModel().addEntry(new DemoIntimacy("Intimacy 2", 3, character));
    String longIntimacy = "Und ich bin jetzt eine ganz super lange Intimacy damit wir auch einen Umbruch bekommen";
    character.getIntimaciesModel().addEntry(new DemoIntimacy(longIntimacy, 0, character));
    ICharacterModelContext context = new CharacterModelContext(character);
    SolarVirtueFlawModel virtueFlawModel = new SolarVirtueFlawModel(context, new SolarVirtueFlawTemplate());
    virtueFlawModel.getVirtueFlaw().getName().setText("Doll, doll, böse");
    virtueFlawModel.getVirtueFlaw().getLimitBreak().setText(
        "Oh nein nicht schon wieder. Diese verschissene Welt hat mich gar nicht verdient.");
    character.addAdditionalModel(virtueFlawModel);
    return character;
  }

  private static DemoGenericDescription createDemoDescription() {
    DemoGenericDescription description = new DemoGenericDescription();
    description.setName("Hugo the Brave");
    description.setPlayer("Max the Wicked");
    return description;
  }
}