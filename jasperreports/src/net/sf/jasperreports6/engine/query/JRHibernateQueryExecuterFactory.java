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
package net.sf.jasperreports6.engine.query;

import java.util.Map;

import net.sf.jasperreports.annotations.properties.Property;
import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports6.engine.JRDataset;
import net.sf.jasperreports6.engine.JRException;
import net.sf.jasperreports6.engine.JRPropertiesUtil;
import net.sf.jasperreports6.engine.JRValueParameter;
import net.sf.jasperreports6.engine.JasperReportsContext;
import net.sf.jasperreports6.engine.util.Designated;
import net.sf.jasperreports6.properties.PropertyConstants;

/**
 * Query executer factory for HQL queries that uses Hibernate 3.
 * <p/>
 * The factory creates {@link net.sf.jasperreports6.engine.query.JRHibernateQueryExecuter JRHibernateQueryExecuter}
 * query executers. 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRHibernateQueryExecuterFactory extends AbstractQueryExecuterFactory implements Designated
{
	
	public static final String QUERY_EXECUTER_NAME = "net.sf.jasperreports6.query.executer:HQL";

	/**
	 * HQL query language.
	 */
	public static final String QUERY_LANGUAGE_HQL = "hql";
	
	/**
	 * Built-in parameter holding the value of the Hibernate session to be used for creating the query.
	 */
	public final static String PARAMETER_HIBERNATE_SESSION = "HIBERNATE_SESSION";
	
	/**
	 * Built-in parameter used for collection filter queries.
	 * <p/>
	 * The value of this parameter will be used as the collection to filter using the query.
	 */
	public final static String PARAMETER_HIBERNATE_FILTER_COLLECTION = "HIBERNATE_FILTER_COLLECTION";
	
	private final static Object[] HIBERNATE_BUILTIN_PARAMETERS = {
		//passing the parameter type as class name and not class in order to 
		//avoid a dependency on Hibernate classes so that reports that have
		//HQL queries would load even when Hibernate is not present
		PARAMETER_HIBERNATE_SESSION,  "org.hibernate.Session",
		PARAMETER_HIBERNATE_FILTER_COLLECTION,  "java.lang.Object",
		};

	/**
	 * Property specifying the query execution type.
	 * <p/>
	 * Possible values are:
	 * <ul>
	 * 	<li><em>list</em> (default) - the query will be run by calling <code>org.hibernate.Query.list()</code></li>
	 * 	<li><em>iterate</em> - the query will be run by calling <code>org.hibernate.Query.iterate()</code></li>
	 * 	<li><em>scroll</em> - the query will be run by calling <code>org.hibernate.Query.scroll()</code></li>
	 * </ul>
	 */
	@Property(
			category = PropertyConstants.CATEGORY_DATA_SOURCE,
			defaultValue = "list",
			scopes = {PropertyScope.CONTEXT, PropertyScope.DATASET},
			scopeQualifications = {JRHibernateQueryExecuterFactory.QUERY_EXECUTER_NAME},
			sinceVersion = PropertyConstants.VERSION_1_2_0
			)
	public static final String PROPERTY_HIBERNATE_QUERY_RUN_TYPE = JRPropertiesUtil.PROPERTY_PREFIX + "hql.query.run.type";
	
	/**
	 * Property specifying the number of result rows to be retrieved at once when the execution type is <em>list</em>.
	 * <p/>
	 * Result pagination is implemented by <code>org.hibernate.Query.setFirstResult()</code> and <code>org.hibernate.Query.setMaxResults()</code>.
	 * <p/>
	 * By default, all the rows are retrieved (no result pagination is performed).
	 */
	@Property(
			category = PropertyConstants.CATEGORY_DATA_SOURCE,
			defaultValue = "0",
			scopes = {PropertyScope.CONTEXT, PropertyScope.DATASET},
			scopeQualifications = {JRHibernateQueryExecuterFactory.QUERY_EXECUTER_NAME},
			sinceVersion = PropertyConstants.VERSION_1_2_0,
			valueType = Integer.class
			)
	public static final String PROPERTY_HIBERNATE_QUERY_LIST_PAGE_SIZE = JRPropertiesUtil.PROPERTY_PREFIX + "hql.query.list.page.size";
	
	/**
	 * Property specifying whether hibernate session cache should be cleared between two consecutive fetches when using pagination.
	 * <p/>
	 * By default, the cache cleanup is not performed.
	 * <p/>
	 * @see net.sf.jasperreports6.engine.query.JRHibernateQueryExecuterFactory#PROPERTY_HIBERNATE_QUERY_LIST_PAGE_SIZE
	 */
	@Property(
			category = PropertyConstants.CATEGORY_DATA_SOURCE,
			defaultValue = PropertyConstants.BOOLEAN_FALSE,
			scopes = {PropertyScope.CONTEXT, PropertyScope.DATASET},
			scopeQualifications = {JRHibernateQueryExecuterFactory.QUERY_EXECUTER_NAME},
			sinceVersion = PropertyConstants.VERSION_1_3_1,
			valueType = Boolean.class
			)
	public static final String PROPERTY_HIBERNATE_CLEAR_CACHE = JRPropertiesUtil.PROPERTY_PREFIX + "hql.clear.cache";
	
	/**
	 * Property specifying whether field descriptions should be used to determine the mapping between the fields
	 * and the query return values.
	 */
	@Property(
			category = PropertyConstants.CATEGORY_DATA_SOURCE,
			defaultValue = PropertyConstants.BOOLEAN_TRUE,
			scopes = {PropertyScope.CONTEXT, PropertyScope.DATASET},
			scopeQualifications = {JRHibernateQueryExecuterFactory.QUERY_EXECUTER_NAME},
			sinceVersion = PropertyConstants.VERSION_1_2_0,
			valueType = Boolean.class
			)
	public static final String PROPERTY_HIBERNATE_FIELD_MAPPING_DESCRIPTIONS = JRPropertiesUtil.PROPERTY_PREFIX + "hql.field.mapping.descriptions";
	
	/**
	 * Value of the {@link #PROPERTY_HIBERNATE_QUERY_RUN_TYPE PROPERTY_HIBERNATE_QUERY_RUN_TYPE} property
	 * corresponding to <em>list</em> execution type.
	 */
	public static final String VALUE_HIBERNATE_QUERY_RUN_TYPE_LIST = "list"; 
	
	/**
	 * Value of the {@link #PROPERTY_HIBERNATE_QUERY_RUN_TYPE PROPERTY_HIBERNATE_QUERY_RUN_TYPE} property
	 * corresponding to <em>iterate</em> execution type.
	 */
	public static final String VALUE_HIBERNATE_QUERY_RUN_TYPE_ITERATE = "iterate"; 
	
	/**
	 * Value of the {@link #PROPERTY_HIBERNATE_QUERY_RUN_TYPE PROPERTY_HIBERNATE_QUERY_RUN_TYPE} property
	 * corresponding to <em>scroll</em> execution type.
	 */
	public static final String VALUE_HIBERNATE_QUERY_RUN_TYPE_SCROLL = "scroll";

	
	/**
	 * Returns an array containing the {@link #PARAMETER_HIBERNATE_SESSION PARAMETER_HIBERNATE_SESSION} and
	 * {@link #PARAMETER_HIBERNATE_FILTER_COLLECTION PARAMETER_HIBERNATE_FILTER_COLLECTION} parameters.
	 */
	@Override
	public Object[] getBuiltinParameters()
	{
		return HIBERNATE_BUILTIN_PARAMETERS;
	}

	@Override
	public JRQueryExecuter createQueryExecuter(
		JasperReportsContext jasperReportsContext, 
		JRDataset dataset, 
		Map<String, ? extends JRValueParameter> parameters
		) throws JRException
	{
		return new JRHibernateQueryExecuter(jasperReportsContext, dataset, parameters);
	}

	/**
	 * Returns <code>true</code> for all parameter types.
	 */
	@Override
	public boolean supportsQueryParameterType(String className)
	{
		return true;
	}

	@Override
	public String getDesignation()
	{
		return QUERY_EXECUTER_NAME;
	}
}
