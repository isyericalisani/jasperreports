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
package net.sf.jasperreports6.components.map;

import java.util.List;

import net.sf.jasperreports6.engine.JRCloneable;
import net.sf.jasperreports6.engine.JRElementDataset;

/**
 * The ItemData interface
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @deprecated Replaced by {@link net.sf.jasperreports6.components.items.ItemData}.
 */
public interface ItemData extends JRCloneable
{

	/**
	 * Returns a list of {@link net.sf.jasperreports6.components.map.Item Item} 
	 * objects. Each item in the list provides a collection of item properties.
	 * 
	 * @return a list of items
	 * 
	 * @see net.sf.jasperreports6.components.map.Item
	 * @see net.sf.jasperreports6.components.map.ItemProperty
	 */
	public List<Item> getItems();
	
	/**
	 * Returns the dataset information that will be used by the {@link net.sf.jasperreports6.components.map.ItemData ItemData} object.
	 * <p>
	 * This information is required either to use the main dataset or to instantiate a 
	 * subdataset from the report. The data produced by the subdataset is fed to the items list.
	 * 
	 * @return the dataset
	 * @see net.sf.jasperreports6.engine.JRElementDataset
	 */
	public JRElementDataset getDataset();

}
