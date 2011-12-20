package net.sf.anathema.demo.character.reporting;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.equipment.dummy.DemoAlienArmour;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DemoNaturalArmour;
import net.sf.anathema.character.equipment.dummy.DemoRangeWeapon;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.ArmourTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.intimacies.reporting.SimpleIntimaciesEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.PdfMagicPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.solar.reporting.Simple2ndSolarPartEncoder;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.SolarVirtueFlawModel;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.control.BrowserControl;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

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
      SimpleEncodingRegistry encodingRegistry = createEncodingRegistry(resources);
      int essenceMax = 7;
      Simple2ndSolarPartEncoder partEncoder = new Simple2ndSolarPartEncoder(resources, encodingRegistry, essenceMax);
      PdfFirstPageEncoder pageEncoder = new PdfFirstPageEncoder(partEncoder, encodingRegistry, resources, essenceMax, pageConfiguration);
      ReportContentRegistry contentRegistry = new ReportContentRegistry();
      ReportContent content = new ReportContent(contentRegistry, character, description);
      SheetGraphics graphics = new SheetGraphics(directContent, encodingRegistry.getBaseFont(), encodingRegistry.getSymbolBaseFont());
      pageEncoder.encode(document, graphics, content);
      document.newPage();
      new PdfMagicPageEncoder(resources, encodingRegistry, pageConfiguration, false).encode(document, graphics, content);
      BrowserControl.displayUrl(outputStream.toURI().toURL());
    } catch (Exception de) {
      de.printStackTrace();
    } finally {
      document.close();
    }
  }

  private static SimpleEncodingRegistry createEncodingRegistry(IResources resources) {
    SimpleEncodingRegistry encodingRegistry = new SimpleEncodingRegistry();
    BaseFont baseFont = encodingRegistry.getBaseFont();
    encodingRegistry.setArmourContentEncoder(new ArmourEncoder(resources, baseFont, new ArmourTableEncoder(baseFont, resources)));
    encodingRegistry.setWeaponContentEncoder(new WeaponryEncoder(resources, baseFont));
    encodingRegistry.setIntimaciesEncoder(new SimpleIntimaciesEncoder(baseFont));
    return encodingRegistry;
  }

  private static IResources createDemoResources() {
    Locale.setDefault(Locale.ENGLISH);
    return new AnathemaResources();
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
    character.addCombo(new DemoGenericCombo("Combo 1", new ICharm[] { new DummyCharm("FirstCharm.Id"), new DummyCharm("SecondCharm.Id") }));
    character
      .addCombo(new DemoGenericCombo("Combo Nummero 2", new ICharm[] { new DummyCharm("Und noch eine Charm Id"), new DummyCharm("Und noch eine") }));
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
    virtueFlawModel.getVirtueFlaw().getName().setText("Doll, doll, boese");
    virtueFlawModel.getVirtueFlaw().getLimitBreak().setText("Oh nein nicht schon wieder. Diese verschissene Welt hat mich gar nicht verdient.");
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
