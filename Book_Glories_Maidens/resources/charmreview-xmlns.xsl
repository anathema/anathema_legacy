<?xml version="1.0" encoding="UTF-8"?>
<!--

    charmreview-xmlns.xml - XML stylesheet that implements XML namespaces.
    Copyright (C) 2007  Paul R. Adams (pauladams@hotmail.com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

-->
<xsl:stylesheet xmlns:chrm="http://anathema.sourceforge.net/charms" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" />
	<!-- XSLT keys, this section exists for future use. -->
	<xsl:key name="charms" match="chrm:charm" use="@id"/>
	<xsl:key name="genericCharms" match="chrm:genericCharm" use="@id"/>
	<xsl:key name="charmAlternatives" match="chrm:alternatives/chrm:alternative/chrm:charmReference" use="@id"/>
	<!--
		Primary template for the rendering of charm XML into HTML.  This template applies either:  
		chrm:charm OR chrm:genericCharm depending upon which elements are avaliable in the source XML file.. 
	-->
	<xsl:template match="chrm:charmlist">
	<xsl:element name="html">
		<xsl:attribute name="lang">en</xsl:attribute>
		<!-- Head -->
		<xsl:element name="head">
			<xsl:element name="title">You are reviewing: 
				<xsl:choose>
					<xsl:when test="chrm:genericCharm!=''"><xsl:value-of select="chrm:genericCharm/@exalt" /> Excellencies</xsl:when>
					<xsl:otherwise><xsl:value-of select="chrm:charm/@group" /></xsl:otherwise>
				</xsl:choose>
			</xsl:element>
		</xsl:element>
		<!-- CSS Test, below is reserved for future use. -->
		<!--xsl:element name="style">
			<xsl:attribute name="type">text/css</xsl:attribute>
			<xsl:attribute name="media">all</xsl:attribute>
			.title { font-weight: bold; }
		</xsl:element-->
		<!-- End Head -->
			<!-- Body -->
			<xsl:element name="body">
				<xsl:element name="p"><xsl:value-of select="../comment()"/></xsl:element>
				<xsl:element name="p">There are <xsl:value-of select="count(chrm:genericCharm)+count(chrm:charm)"/> charms in <xsl:value-of select="comment()" />.</xsl:element>
				<xsl:element name="hr"/>
				<!-- Individual Charm Rendering -->
				<!--
					I would rather match <alternatives /> first, <genericCharm /> second, and <charm />
					third but that kind of granular control is beyond my grasp, at the moment.  As 
					such, I'm settling for an ascending sort based on the element names.
					
					The alternative that I've persued, calling <alternatives /> as a named template
					left an empty charm at the end of the HTML, probably because <alternatives /> 
					doesn't match known charm templates.  Even if this worked, I would have to figure
					out a way to call <genericCharm /> and <charm /> specifically.  XSLT variables
					and parameters would work but I'm not sure how to do that, for the moment.
				-->
				<xsl:apply-templates match="*">
					<xsl:sort select="name(.)" />
				</xsl:apply-templates>
				<!-- Loop to Next Charm -->
			</xsl:element>
			<!-- End Body -->
		</xsl:element>
		<!-- End HTML -->
	</xsl:template>
	
	<!-- Template for rendering the infrequent "alternatives" entries. -->
	
	<xsl:template match="chrm:alternatives">
		<xsl:element name="div"><xsl:attribute name="style">background-color: black; color: white</xsl:attribute>
			<xsl:element name="br" />
			<xsl:element name="p"><xsl:element name="b">Alternative Charms: </xsl:element>
				<xsl:for-each select="chrm:alternative/chrm:charmReference">
					<xsl:value-of select="@id" /><xsl:text>, </xsl:text>
				</xsl:for-each>
			</xsl:element>
			<xsl:element name="br" />
		</xsl:element>
		<xsl:element name="hr" />
	</xsl:template>
	
	<!-- Template for all charm entries. -->
	
	<xsl:template match="*">
		<xsl:call-template name="Line_1"/>
		<xsl:call-template name="Line_2"/>
		<xsl:call-template name="Line_3"/>
		<xsl:call-template name="Line_4"/>
		<xsl:call-template name="Line_5"/>
		<xsl:call-template name="Line_6"/>
		<!-- Other "System" Information -->
		<xsl:element name="div"><xsl:attribute name="style">background-color: silver</xsl:attribute>
			<!-- List any restrictions on selecting the charm. -->
			<xsl:call-template name="charmRestrictions" />
			<!-- List he Exalt to whose tree the charm belongs. -->
			<xsl:call-template name="writeExalt"/>
			<!-- What group is the charm a part of? -->
			<xsl:call-template name="charmGroup" />
			<!-- Are there any hidden keywords? -->
			<xsl:call-template name="hiddenKeywords" />
			<!-- Which book did this charm come from?  What page? -->
			<xsl:call-template name="charmSource" />
			<!-- Are there any comments listed in the charm XML? -->
			<xsl:call-template name="writeCharmComments" />
		</xsl:element>
		<!-- End DIV -->
		<xsl:element name="hr"/>
	</xsl:template>
	
	<!-- Supplementary Templates:  these do the "common" repetitive tasks. -->
	
	<xsl:template name="Line_1">
		<xsl:element name="p"><xsl:element name="b">Name: </xsl:element><xsl:value-of select="@id"/></xsl:element>
	</xsl:template>
	
	<xsl:template name="Line_2">
		<xsl:element name="p"><xsl:element name="b">Cost: </xsl:element><xsl:for-each select="chrm:cost"><xsl:call-template name="charmCost" /></xsl:for-each>; 
			<xsl:element name="b">Mins: </xsl:element>
			<xsl:for-each select="chrm:prerequisite/chrm:trait">
				<xsl:value-of select="@id"/><xsl:text> </xsl:text><xsl:value-of select="@value"/>, 
			</xsl:for-each>
			Essence <xsl:value-of select="chrm:prerequisite/chrm:essence/@value"/>
		</xsl:element>
	</xsl:template>
		
	<xsl:template name="Line_3">
		<xsl:element name="p"><xsl:element name="b">Type: </xsl:element>
			<xsl:for-each select="chrm:charmtype">
				<xsl:choose>  
					<xsl:when test="@type='Simple'">
						<xsl:text>Simple </xsl:text>
							<xsl:if test="chrm:special/@*!=''"><xsl:text>(</xsl:text>
								<xsl:if test="chrm:special/@speed!=6"><xsl:value-of select="concat('Speed ',chrm:special/@speed)"/></xsl:if>
								<xsl:if test="chrm:special/@turntype='LongTick'">
								<xsl:if test="chrm:special/@speed=6"><xsl:value-of select="concat('Speed ', chrm:special/@speed)"/></xsl:if> in long ticks</xsl:if>
								<xsl:if test="chrm:special/@turntype='DramaticAction'">dramatic action</xsl:if>
								<xsl:if test="chrm:special/@speed!=6"><xsl:if test="chrm:special/@defense!=-1">, </xsl:if></xsl:if>
								<xsl:if test="chrm:special/@defense!=-1">DV<xsl:value-of select="chrm:special/@defense"/></xsl:if>
								<xsl:text>)</xsl:text>
							</xsl:if>
					</xsl:when>
					<xsl:when test="@type='Reflexive'">
						<xsl:text>Reflexive </xsl:text>
						<xsl:if test="chrm:special/@primaryStep!=''">
							<xsl:value-of select="concat('(Step ',chrm:special/@primaryStep)"/>
							<xsl:if test="chrm:special/@secondaryStep!=''">
								<xsl:text> or </xsl:text><xsl:value-of select="chrm:special/@secondaryStep"/>
							</xsl:if>)
						</xsl:if>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@type"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
		
	<xsl:template name="Line_4">
		<xsl:element name="p"><xsl:element name="b">Keywords: </xsl:element>
			<xsl:for-each select="chrm:charmAttribute[@visualize='true']">
				<xsl:value-of select="@attribute" />,
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="Line_5">
		<xsl:element name="p"><xsl:element name="b">Duration: </xsl:element>
			<xsl:choose>
				<xsl:when test="chrm:duration/@amount!=''">
					<xsl:value-of select="chrm:duration/@amount"/><xsl:text> </xsl:text><xsl:value-of select="chrm:duration/@unit"/>
				</xsl:when>
				<xsl:when test="chrm:duration/@event!=''">
					Until <xsl:value-of select="chrm:duration/@event"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="chrm:duration/@duration"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="Line_6">
		<xsl:element name="p"><xsl:element name="b">Prerequisite Charms:  </xsl:element>
			<xsl:if test="chrm:prerequisite/chrm:charmAttributeRequirement/@attribute!=''">
				<xsl:for-each select="chrm:prerequisite/chrm:charmAttributeRequirement">
					<xsl:value-of select="@count" />x <xsl:value-of select="@attribute" />, 
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="chrm:prerequisite/chrm:selectiveCharmGroup/@threshold!=''">
				<xsl:for-each select="chrm:prerequisite/chrm:selectiveCharmGroup/chrm:charmReference">
					<xsl:value-of select="@id" />,
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="chrm:prerequisite/chrm:charmReference/@id!=''">
				<xsl:for-each select="chrm:prerequisite/chrm:charmReference">
					<xsl:value-of select="@id" />, 
				</xsl:for-each>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	
	<!-- Non-Line Templates -->
	
	<xsl:template name="charmCost">
		<xsl:choose>
		 <xsl:when test="*">
			<xsl:if test="boolean(number(chrm:essence/@cost))='true'">
				<xsl:if test="chrm:essence/@cost!=''"><xsl:value-of select="concat(chrm:essence/@cost,'m')"/></xsl:if>
			</xsl:if>
			<xsl:if test="boolean(number(chrm:essence/@cost))=false">
				<xsl:if test="chrm:essence/@cost!=''"><xsl:value-of select="chrm:essence/@cost"/></xsl:if>
			</xsl:if>
			<xsl:if test="chrm:essence/@text!=''"><xsl:text> </xsl:text><xsl:value-of select="chrm:essence/@text"/></xsl:if>
			<xsl:if test="chrm:willpower/@cost!=''">, <xsl:value-of select="concat(chrm:willpower/@cost,'wp')"/></xsl:if>
			<xsl:if test="chrm:willpower/@text!=''"><xsl:text> </xsl:text><xsl:value-of select="chrm:willpower/@text"/></xsl:if>
			<xsl:if test="chrm:health/@cost!=''">, <xsl:value-of select="concat(chrm:health/@cost,'lhl')"/></xsl:if>
			<xsl:if test="chrm:health/@text!=''"><xsl:text> </xsl:text><xsl:value-of select="chrm:health/@text"/></xsl:if>
			<xsl:if test="chrm:experience/@cost!=''">, <xsl:value-of select="concat(chrm:experience/@cost,'xp')"/></xsl:if>
			<xsl:if test="chrm:experience/@text!=''"><xsl:text> </xsl:text><xsl:value-of select="chrm:experience/@text"/></xsl:if>
		</xsl:when>
			<xsl:otherwise>Permanent</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="charmGroup">
		<xsl:if test="@group!=''">
			<xsl:element name="p"><xsl:element name="b">Group: </xsl:element><xsl:value-of select="@group"/></xsl:element>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="charmRestrictions">
		<xsl:element name="p"><xsl:element name="b">Restrictions: </xsl:element>
			<xsl:for-each select="chrm:combo/chrm:restrictions/chrm:genericCharmReference">
				<xsl:value-of select="@id" />, 
			</xsl:for-each>
			<xsl:for-each select="chrm:combo/chrm:restrictions/chrm:charm">
				<xsl:value-of select="@id" />, 
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="charmSource">
		<xsl:element name="p"><xsl:element name="b">Source: </xsl:element>
			<xsl:for-each select="chrm:source">
				<xsl:value-of select="@source"/>
				<xsl:if test="@page!=''"> on page <xsl:value-of select="@page"/></xsl:if>, 
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="hiddenKeywords">
		<xsl:element name="p"><xsl:element name="b">Hidden Keywords: </xsl:element>
			<xsl:for-each select="chrm:genericCharmAttribute[@visualize=boolean('')]">
				<xsl:value-of select="@attribute" />,
			</xsl:for-each>
			<xsl:for-each select="chrm:charmAttribute[@visualize=boolean('')]">
				<xsl:value-of select="@attribute" />,
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="writeCharmComments">
		<xsl:element name="table">
			<xsl:element name="tr">
				<xsl:element name="td"><xsl:element name="b">Comment: </xsl:element></xsl:element>
				<xsl:element name="td"></xsl:element>
			</xsl:element>
			<xsl:for-each select="descendant::comment()">
				<xsl:element name="tr">
					<xsl:element name="td"></xsl:element>
					<xsl:element name="td"><xsl:value-of select="."/></xsl:element>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="writeExalt">
		<xsl:element name="p"><xsl:element name="b">Exalt: </xsl:element><xsl:value-of select="@exalt"/></xsl:element>
	</xsl:template>
	
</xsl:stylesheet>