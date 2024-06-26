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
package net.sf.jasperreports6.crosstabs.xml;


import org.xml.sax.Attributes;

import net.sf.jasperreports6.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports6.engine.design.JasperDesign;
import net.sf.jasperreports6.engine.type.RunDirectionEnum;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;
import net.sf.jasperreports6.engine.xml.JRXmlConstants;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRCrosstabFactory extends JRBaseFactory
{
	public static final String ELEMENT_crosstab = "crosstab";
	public static final String ELEMENT_titleCell = "titleCell";
	public static final String ELEMENT_whenNoDataCell = "whenNoDataCell";
	public static final String ELEMENT_crosstabHeaderCell = "crosstabHeaderCell";
	
	public static final String ELEMENT_parametersMapExpression = "parametersMapExpression";
	
	public static final String ATTRIBUTE_columnBreakOffset = "columnBreakOffset";
	public static final String ATTRIBUTE_isRepeatColumnHeaders = "isRepeatColumnHeaders";
	public static final String ATTRIBUTE_isRepeatRowHeaders = "isRepeatRowHeaders";
	public static final String ATTRIBUTE_ignoreWidth = "ignoreWidth";
	
	@Override
	public Object createObject(Attributes atts)
	{
		JasperDesign jasperDesign = (JasperDesign)digester.peek(digester.getCount() - 2);
		
		JRDesignCrosstab crosstab = new JRDesignCrosstab(jasperDesign);
		
		String columnBreakOffsetAttr = atts.getValue(ATTRIBUTE_columnBreakOffset);
		if (columnBreakOffsetAttr != null && columnBreakOffsetAttr.length() > 0)
		{
			crosstab.setColumnBreakOffset(Integer.parseInt(columnBreakOffsetAttr));
		}
		
		String repeatColHeadersAttr = atts.getValue(ATTRIBUTE_isRepeatColumnHeaders);
		if (repeatColHeadersAttr != null && repeatColHeadersAttr.length() > 0)
		{
			crosstab.setRepeatColumnHeaders(Boolean.valueOf(repeatColHeadersAttr));
		}
		
		String repeatRowHeadersAttr = atts.getValue(ATTRIBUTE_isRepeatRowHeaders);
		if (repeatRowHeadersAttr != null && repeatColHeadersAttr.length() > 0)
		{
			crosstab.setRepeatRowHeaders(Boolean.valueOf(repeatRowHeadersAttr));
		}
		
		String runDirectionAttr = atts.getValue(JRXmlConstants.ATTRIBUTE_runDirection);
		if (runDirectionAttr != null)
		{
			RunDirectionEnum runDir = RunDirectionEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_runDirection));
			if (runDir != null)
			{
				crosstab.setRunDirection(runDir);
			}
		}
		
		String ignoreWidthAttr = atts.getValue(ATTRIBUTE_ignoreWidth);
		if (ignoreWidthAttr != null)
		{
			crosstab.setIgnoreWidth(Boolean.valueOf(ignoreWidthAttr));
		}
		
		return crosstab;
	}
}
