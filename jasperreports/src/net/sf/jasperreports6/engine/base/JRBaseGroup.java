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
package net.sf.jasperreports6.engine.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import net.sf.jasperreports6.engine.JRBand;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRGroup;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.JRSection;
import net.sf.jasperreports6.engine.JRVariable;
import net.sf.jasperreports6.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports6.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports6.engine.type.FooterPositionEnum;
import net.sf.jasperreports6.engine.util.CloneStore;
import net.sf.jasperreports6.engine.util.JRCloneUtils;
import net.sf.jasperreports6.engine.util.StoreCloneable;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBaseGroup implements JRGroup, Serializable, JRChangeEventsSupport, StoreCloneable<JRBaseGroup>
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE = "minHeightToStartNewPage";
	
	public static final String PROPERTY_MIN_DETAILS_TO_START_FROM_TOP = "minDetailsToStartFromTop";
	
	public static final String PROPERTY_FOOTER_POSITION = "footerPosition";
	
	public static final String PROPERTY_KEEP_TOGETHER = "keepTogether";
	
	public static final String PROPERTY_PREVENT_ORPHAN_FOOTER = "preventOrphanFooter";
	
	public static final String PROPERTY_RESET_PAGE_NUMBER = "isResetPageNumber";
	
	public static final String PROPERTY_REPRINT_HEADER_ON_EACH_PAGE = "isReprintHeaderOnEachPage";
	
	public static final String PROPERTY_REPRINT_HEADER_ON_EACH_COLUMN = "isReprintHeaderOnEachColumn";
	
	public static final String PROPERTY_START_NEW_COLUMN = "isStartNewColumn";
	
	public static final String PROPERTY_START_NEW_PAGE = "isStartNewPage";

	/**
	 *
	 */
	protected String name;
	protected boolean isStartNewColumn;
	protected boolean isStartNewPage;
	protected boolean isResetPageNumber;
	protected boolean isReprintHeaderOnEachPage;
	protected boolean isReprintHeaderOnEachColumn;
	protected int minHeightToStartNewPage;
	protected int minDetailsToStartFromTop;
	protected FooterPositionEnum footerPositionValue = FooterPositionEnum.NORMAL;
	protected boolean keepTogether;
	protected boolean preventOrphanFooter;

	/**
	 *
	 */
	protected JRExpression expression;
	protected JRSection groupHeaderSection;
	protected JRSection groupFooterSection;
	protected JRVariable countVariable;
	

	/**
	 *
	 */
	protected JRBaseGroup()
	{
	}
		

	/**
	 *
	 */
	protected JRBaseGroup(JRGroup group, JRBaseObjectFactory factory)
	{
		factory.put(group, this);
		
		name = group.getName();
		isStartNewColumn = group.isStartNewColumn();
		isStartNewPage = group.isStartNewPage();
		isResetPageNumber = group.isResetPageNumber();
		isReprintHeaderOnEachPage = group.isReprintHeaderOnEachPage();
		isReprintHeaderOnEachColumn = group.isReprintHeaderOnEachColumn();
		minHeightToStartNewPage = group.getMinHeightToStartNewPage();
		minDetailsToStartFromTop = group.getMinDetailsToStartFromTop();
		footerPositionValue = group.getFooterPositionValue();
		keepTogether = group.isKeepTogether();
		preventOrphanFooter = group.isPreventOrphanFooter();
		
		expression = factory.getExpression(group.getExpression());

		groupHeaderSection = factory.getSection(group.getGroupHeaderSection());
		groupFooterSection = factory.getSection(group.getGroupFooterSection());
		countVariable = factory.getVariable(group.getCountVariable());
	}
		

	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public boolean isStartNewColumn()
	{
		return this.isStartNewColumn;
	}
		
	@Override
	public void setStartNewColumn(boolean isStart)
	{
		boolean old = this.isStartNewColumn;
		this.isStartNewColumn = isStart;
		getEventSupport().firePropertyChange(PROPERTY_START_NEW_COLUMN, old, this.isStartNewColumn);
	}
		
	@Override
	public boolean isStartNewPage()
	{
		return this.isStartNewPage;
	}
		
	@Override
	public void setStartNewPage(boolean isStart)
	{
		boolean old = this.isStartNewPage;
		this.isStartNewPage = isStart;
		getEventSupport().firePropertyChange(PROPERTY_START_NEW_PAGE, old, this.isStartNewPage);
	}
		
	@Override
	public boolean isResetPageNumber()
	{
		return this.isResetPageNumber;
	}
		
	@Override
	public void setResetPageNumber(boolean isReset)
	{
		boolean old = this.isResetPageNumber;
		this.isResetPageNumber = isReset;
		getEventSupport().firePropertyChange(PROPERTY_RESET_PAGE_NUMBER, old, this.isResetPageNumber);
	}
		
	@Override
	public boolean isReprintHeaderOnEachPage()
	{
		return this.isReprintHeaderOnEachPage;
	}
		
	@Override
	public void setReprintHeaderOnEachPage(boolean isReprint)
	{
		boolean old = this.isReprintHeaderOnEachPage;
		this.isReprintHeaderOnEachPage = isReprint;
		getEventSupport().firePropertyChange(PROPERTY_REPRINT_HEADER_ON_EACH_PAGE, old, this.isReprintHeaderOnEachPage);
	}
		
	@Override
	public boolean isReprintHeaderOnEachColumn()
	{
		return this.isReprintHeaderOnEachColumn;
	}
		
	@Override
	public void setReprintHeaderOnEachColumn(boolean isReprint)
	{
		boolean old = this.isReprintHeaderOnEachColumn;
		this.isReprintHeaderOnEachColumn = isReprint;
		getEventSupport().firePropertyChange(PROPERTY_REPRINT_HEADER_ON_EACH_COLUMN, old, this.isReprintHeaderOnEachColumn);
	}
		
	@Override
	public int getMinHeightToStartNewPage()
	{
		return this.minHeightToStartNewPage;
	}

	@Override
	public void setMinHeightToStartNewPage(int minHeight)
	{
		int old = this.minHeightToStartNewPage;
		this.minHeightToStartNewPage = minHeight;
		getEventSupport().firePropertyChange(PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE, old, this.minHeightToStartNewPage);
	}
	
	@Override
	public int getMinDetailsToStartFromTop()
	{
		return this.minDetailsToStartFromTop;
	}
	
	@Override
	public void setMinDetailsToStartFromTop(int minDetails)
	{
		int old = this.minDetailsToStartFromTop;
		this.minDetailsToStartFromTop = minDetails;
		getEventSupport().firePropertyChange(PROPERTY_MIN_DETAILS_TO_START_FROM_TOP, old, this.minDetailsToStartFromTop);
	}
	
	@Override
	public FooterPositionEnum getFooterPositionValue()
	{
		return this.footerPositionValue;
	}
		
	@Override
	public void setFooterPosition(FooterPositionEnum footerPositionValue)
	{
		FooterPositionEnum old = this.footerPositionValue;
		this.footerPositionValue = footerPositionValue;
		getEventSupport().firePropertyChange(PROPERTY_FOOTER_POSITION, old, this.footerPositionValue);
	}
		
	@Override
	public boolean isKeepTogether()
	{
		return this.keepTogether;
	}
		
	@Override
	public void setKeepTogether(boolean keepTogether)
	{
		boolean old = this.keepTogether;
		this.keepTogether = keepTogether;
		getEventSupport().firePropertyChange(PROPERTY_KEEP_TOGETHER, old, this.keepTogether);
	}
		
	@Override
	public boolean isPreventOrphanFooter()
	{
		return this.preventOrphanFooter;
	}
		
	@Override
	public void setPreventOrphanFooter(boolean preventOrphanFooter)
	{
		boolean old = this.preventOrphanFooter;
		this.preventOrphanFooter = preventOrphanFooter;
		getEventSupport().firePropertyChange(PROPERTY_PREVENT_ORPHAN_FOOTER, old, this.preventOrphanFooter);
	}
		
	@Override
	public JRExpression getExpression()
	{
		return this.expression;
	}
	
	@Override
	public JRSection getGroupHeaderSection()
	{
		return this.groupHeaderSection;
	}
		
	@Override
	public JRSection getGroupFooterSection()
	{
		return this.groupFooterSection;
	}
		
	@Override
	public JRVariable getCountVariable()
	{
		return this.countVariable;
	}

	
	@Override
	public Object clone() 
	{
		return clone(null);
	}

	@Override
	public JRBaseGroup clone(CloneStore cloneStore)
	{
		JRBaseGroup clone = null;

		try
		{
			clone = (JRBaseGroup)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		if (cloneStore != null)
		{
			//early store for circular dependencies
			cloneStore.store(this, clone);
		}
	
		clone.expression = JRCloneUtils.nullSafeClone(expression);
		clone.groupHeader = JRCloneUtils.nullSafeClone(groupHeader);
		clone.groupFooter = JRCloneUtils.nullSafeClone(groupFooter);
		clone.countVariable = cloneStore == null ? JRCloneUtils.nullSafeClone(countVariable) 
				: cloneStore.clone(countVariable);
		clone.eventSupport = null;
		
		return clone;
	}

	
	private transient JRPropertyChangeSupport eventSupport;
	
	@Override
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}

	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private byte footerPosition;
	/**
	 * @deprecated
	 */
	private JRBand groupHeader;
	/**
	 * @deprecated
	 */
	private JRBand groupFooter;
	
	@SuppressWarnings("deprecation")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			if (footerPosition == 0)
			{
				// stream class didn't have footerPosition, use default.
				// ideally we should have used PSEUDO_SERIAL_VERSION_UID to 
				// detect this case, but there was no PSEUDO_SERIAL_VERSION_UID
				// when footerPosition was introduced
				footerPositionValue = FooterPositionEnum.NORMAL;
			}
			else
			{
				// convert stream byte value to enum
				footerPositionValue = FooterPositionEnum.getByValue(footerPosition);
			}
		}

		if (groupHeader != null)
		{
			groupHeaderSection = new JRBaseSection(groupHeader);
			groupHeader = null;
		}
		
		if (groupFooter != null)
		{
			groupFooterSection = new JRBaseSection(groupFooter);
			groupFooter = null;
		}
	}

}
