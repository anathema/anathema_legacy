package net.sf.anathema.lib.gui.widgets;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.data.IOverline;

public class DigitsOnlyDocument extends PlainDocument {

	private static final long serialVersionUID = -5971916517321536678L;
	private final boolean beepOnInvalidCharacter;
	private final IOverline overline;

	public DigitsOnlyDocument(boolean beepOnInvalidCharacter) {
		this(beepOnInvalidCharacter, new IOverline() {

			public int getNearestValue(int value) {
				return value;
			}

			public int getLowerBound() {
				return Integer.MIN_VALUE;
			}
		});
	}

	public DigitsOnlyDocument(boolean beepOnInvalidCharacter, IOverline overline) {
		this.beepOnInvalidCharacter = beepOnInvalidCharacter;
		this.overline = overline;
	}

	@Override
	public void replace(int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		String currentText = getText(offset, length);
		if (ObjectUtilities.equals(currentText, text)) {
			return;
		}
		String correctedString = getCorrectedString(offset, text);
		StringBuilder overallString = new StringBuilder();
		if (offset > 0) {
			overallString.append(getText(0, offset));
		}
		overallString.append(correctedString);
		int suffixStartIndex = offset + length;
		if (suffixStartIndex < getLength()) {
			overallString.append(getText(suffixStartIndex, getLength()
					- suffixStartIndex));
		}
		setValue(overallString, attrs);
	}

	private String getAdjustedValue(String value) throws BadLocationException {
		boolean isMinus = value.equals("-"); //$NON-NLS-1$
		if (overline.getLowerBound() < 0 && isMinus) {
			return value;
		}
		if (isMinus) {
			return getText(0, getLength());
		}
		int currentValue = Integer.parseInt(value);
		int nearestValue = overline.getNearestValue(currentValue);
		return String.valueOf(nearestValue);
	}

	@Override
	public void insertString(int offset, String string, AttributeSet a)
			throws BadLocationException {
		StringBuilder overallString = new StringBuilder();
		if (offset > 0) {
			overallString.append(getText(0, offset));
		}
		String correctedString = getCorrectedString(offset, string);
		overallString.append(correctedString);
		int suffixStartIndex = offset;
		if (suffixStartIndex < getLength()) {
			overallString.append(getText(suffixStartIndex, getLength()
					- suffixStartIndex));
		}
		setValue(overallString, a);
	}

	private void setValue(StringBuilder overallString, AttributeSet a)
			throws BadLocationException {
		String adjustedValue = getAdjustedValue(overallString.toString());
		super.remove(0, getLength());
		super.insertString(0, adjustedValue, a);
	}

	private String getCorrectedString(int offs, String str) {
		char[] sourceCharacters = str.toCharArray();
		StringBuilder legalStringBuilder = new StringBuilder();

		for (int index = 0; index < sourceCharacters.length; index++) {
			if (isLegalCharacter(offs + index, sourceCharacters[index])) {
				legalStringBuilder.append(sourceCharacters[index]);
			}
		}

		if (legalStringBuilder.length() < str.length()) {
			beep();
		}
		return legalStringBuilder.toString();
	}

	private boolean isLegalCharacter(int index, char character) {
		return Character.isDigit(character) || (index == 0 && character == '-');
		// '\u0030' through '\u0039', ISO-LATIN-1 digits ('0' through '9')
	}

	private void beep() {
		if (beepOnInvalidCharacter) {
			Toolkit.getDefaultToolkit().beep();
		}
	}
}