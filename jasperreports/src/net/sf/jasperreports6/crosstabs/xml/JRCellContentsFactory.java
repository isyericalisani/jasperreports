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

import java.util.Map;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports6.engine.JRStyle;
import net.sf.jasperreports6.engine.design.JasperDesign;
import net.sf.jasperreports6.engine.type.ModeEnum;
import net.sf.jasperreports6.engine.util.JRColorUtil;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRCellContentsFactory extends JRBaseFactory
{
	public static final String ELEMENT_cellContents = "cellContents";
	
	public static final String ATTRIBUTE_backcolor = "backcolor";
	public static final String ATTRIBUTE_mode = "mode";
	public static final String ATTRIBUTE_style = "style";

	@Override
	public Object createObject(Attributes attributes)
	{
		JRDesignCellContents cell = new JRDesignCellContents();
		
		String backcolor = attributes.getValue(ATTRIBUTE_backcolor);
		if (backcolor != null && backcolor.length() > 0)
		{
			cell.setBackcolor(JRColorUtil.getColor(backcolor, null));
		}
		
		ModeEnum mode = ModeEnum.getByName(attributes.getValue(ATTRIBUTE_mode));
		if (mode != null)
		{
			cell.setMode(mode);
		}
		
		String styleName = attributes.getValue(ATTRIBUTE_style);
		if (styleName != null)
		{
			JasperDesign jasperDesign = (JasperDesign)digester.peek(digester.getCount() - 2);
			Map<String, JRStyle> stylesMap = jasperDesign.getStylesMap();

			if (stylesMap.containsKey(styleName))
			{
				JRStyle style = stylesMap.get(styleName);
				cell.setStyle(style);
			}
			else
			{
				cell.setStyleNameReference(styleName);
			}
		}

		return cell;
	}
}
