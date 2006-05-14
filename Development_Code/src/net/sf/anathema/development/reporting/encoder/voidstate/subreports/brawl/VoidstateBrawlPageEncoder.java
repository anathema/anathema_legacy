package net.sf.anathema.development.reporting.encoder.voidstate.subreports.brawl;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.datasource.MeleeWeaponDataSource;
import net.sf.anathema.character.generic.framework.reporting.template.AbstractMagicUserCharacterReportTemplate;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateBrawlPageEncoder extends AbstractCharacterSheetPageEncoder implements IVoidStateFormatConstants {

  public int encodeBand(Element parent) {
    Rectangle bounds = calculateBounds();
    String dataSourceParameterCall = ParameterUtilities.parameterString(AbstractMagicUserCharacterReportTemplate.MELEE_WEAPON_DATA_SOURCE);
    for (int weaponIndex = 0; weaponIndex < 4; weaponIndex++) {
      String printWhenExpression = dataSourceParameterCall + methodCall("getRowCount") + " > " + weaponIndex; //$NON-NLS-1$ //$NON-NLS-2$
      String printName = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_PRINT_NAME) }); //$NON-NLS-1$
      String speed = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_SPEED) }); //$NON-NLS-1$
      String accuracy = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_ACCURACY) }); //$NON-NLS-1$
      String damage = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_DAMAGE) }); //$NON-NLS-1$
      String defense = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_DEFENSE) }); //$NON-NLS-1$
      String rate = dataSourceParameterCall
          + methodCall("getValue", new Object[] { weaponIndex, quotify(MeleeWeaponDataSource.COLUMN_RATE) }); //$NON-NLS-1$
      Rectangle textRectangle = calculateTextRectangle(bounds, weaponIndex);
      addBrawlWeapon(parent, textRectangle, printName, speed, accuracy, damage, defense, rate, printWhenExpression);
    }
    String clinchRemarkPrintWhenExpression = dataSourceParameterCall + methodCall("getRowCount") + " < " + 4; //$NON-NLS-1$ //$NON-NLS-2$
    addClinchRemark(parent, calculateTextRectangle(bounds, 3), clinchRemarkPrintWhenExpression);
    return bounds.height;
  }

  private void addClinchRemark(Element parent, Rectangle rectangle, String printWhenExpression) {
    addTextWithPrintWhenExpression(
        parent,
        rectangle,
        5,
        quotify("(Clinches cause piercing damage)"),
        printWhenExpression,
        FONT_SIZE - 1);
  }

  private Rectangle calculateTextRectangle(Rectangle bounds, int weaponIndex) {
    Rectangle textRectangle = new Rectangle(bounds);
    textRectangle.x += (weaponIndex % 2 == 0) ? 0 : 158;
    textRectangle.y += (weaponIndex - 1 <= 0) ? 0 : LINE_HEIGHT;
    return textRectangle;
  }

  private void addBrawlWeapon(
      Element parent,
      Rectangle bounds,
      String printName,
      String speed,
      String accuracy,
      String damage,
      String defense,
      String rate,
      String printWhenExpression) {
    Element caretElement = addCaret(parent, bounds.getLocation(), FONT_SIZE, LINE_HEIGHT);
    addPrintWhenExpression(caretElement, printWhenExpression);
    int xOffset = 5;
    addTextWithPrintWhenExpression(
        parent,
        bounds,
        xOffset,
        printName + "+" + quotify(":"),
        printWhenExpression,
        FONT_SIZE);
    xOffset += 25;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, quotify("Spd"), printWhenExpression, FONT_SIZE - 1);
    xOffset += 13;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, speed, printWhenExpression, FONT_SIZE - 1);
    xOffset += 8;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, quotify("Acc"), printWhenExpression, FONT_SIZE - 1);
    xOffset += 12;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, accuracy, printWhenExpression, FONT_SIZE - 1);
    xOffset += 8;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, quotify("Dmg"), printWhenExpression, FONT_SIZE - 1);
    xOffset += 14;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, damage, printWhenExpression, FONT_SIZE - 1);
    xOffset += 11;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, quotify("Def"), printWhenExpression, FONT_SIZE - 1);
    xOffset += 12;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, defense, printWhenExpression, FONT_SIZE - 1);
    xOffset += 8;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, quotify("Rate"), printWhenExpression, FONT_SIZE - 1);
    xOffset += 15;
    addTextWithPrintWhenExpression(parent, bounds, xOffset, rate, printWhenExpression, FONT_SIZE - 1);
  }

  private void addTextWithPrintWhenExpression(
      Element parent,
      Rectangle bounds,
      int xOffset,
      String text,
      String printWhenExpression,
      int fontSize) {
    Element textElement = addTextElement(
        parent,
        text,
        fontSize,
        VALUE_LEFT,
        bounds.x + xOffset,
        bounds.y,
        300,
        LINE_HEIGHT);
    addPrintWhenExpression(textElement, printWhenExpression);
  }

  public static Rectangle calculateBounds() {
    return new Rectangle(0, 0, 330, 22);
  }

  public String getGroupName() {
    return "VoidStateBrawlSubreport";
  }
}