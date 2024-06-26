/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports6.engine.xml;

import java.util.Map;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.engine.JRPrintText;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.JRStyle;
import net.sf.jasperreports6.engine.JasperPrint;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRPrintFontFactory extends JRBaseFactory
{
	public static final String EXCEPTION_MESSAGE_KEY_UNKNOWN_STYLE = "xml.print.font.factory.unknown.style";

	@Override
	public Object createObject(Attributes atts)
	{
		JRPrintText element = (JRPrintText) digester.peek();
		JRPrintXmlLoader printXmlLoader = (JRPrintXmlLoader)digester.peek(digester.getCount() - 1);
		JasperPrint jasperPrint = (JasperPrint)digester.peek(digester.getCount() - 2);

		if (
			element.getStyle() == null
			&& element.getStyleNameReference() == null
			)
		{
			String styleName = atts.getValue(JRXmlConstants.ATTRIBUTE_reportFont);
			if (styleName != null)
			{
				Map<String,JRStyle> stylesMap = jasperPrint.getStylesMap();

				if (!stylesMap.containsKey(styleName))
				{
					printXmlLoader.addError(					
						new JRRuntimeException(
							EXCEPTION_MESSAGE_KEY_UNKNOWN_REPORT_STYLE,
							new Object[]{styleName}
						)
					);
				}

				element.setStyle(stylesMap.get(styleName));
			}
		}

		if (atts.getValue(JRXmlConstants.ATTRIBUTE_fontName) != null)
		{
			element.setFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_fontName));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isBold) != null)
		{
			element.setBold(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isBold)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isItalic) != null)
		{
			element.setItalic(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isItalic)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isUnderline) != null)
		{
			element.setUnderline(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isUnderline)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isStrikeThrough) != null)
		{
			element.setStrikeThrough(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isStrikeThrough)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_size) != null)
		{
			element.setFontSize(Float.parseFloat(atts.getValue(JRXmlConstants.ATTRIBUTE_size)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_pdfFontName) != null)
		{
			element.setPdfFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfFontName));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_pdfEncoding) != null)
		{
			element.setPdfEncoding(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfEncoding));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isPdfEmbedded) != null)
		{
			element.setPdfEmbedded(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isPdfEmbedded)));
		}
		return element;
	}
	

}
