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
package net.sf.jasperreports6.components.map.fill;

import net.sf.jasperreports6.components.items.Item;
import net.sf.jasperreports6.components.items.ItemData;
import net.sf.jasperreports6.components.items.fill.FillItem;
import net.sf.jasperreports6.engine.component.FillContextProvider;
import net.sf.jasperreports6.engine.fill.JRFillObjectFactory;

/**
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 */
public class FillStyleItemData extends FillItemData
{
	/**
	 *
	 */
	public FillStyleItemData(
		FillContextProvider fillContextProvider,
		ItemData itemData, 
		JRFillObjectFactory factory
		)
	{
		super(fillContextProvider, itemData, factory);
	}
	
	@Override
	public FillItem getFillItem(FillContextProvider fillContextProvider, Item item, JRFillObjectFactory factory){
		return new FillStyleItem(fillContextProvider, item, factory);
	}
}
