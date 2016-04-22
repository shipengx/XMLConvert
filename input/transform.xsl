<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:adara="http://www.adara.com/xsltfunctions" exclude-result-prefixes="adara">
    <xsl:output method="text" version="1.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <xsl:variable name="coefficient_variables" select="p/prediction/algorithm/coefficient" />
            
            <!-- here we loop through unique coefficient variables -->
            <xsl:for-each select="$coefficient_variables">
                <xsl:variable name="current_variable" select="normalize-space(@variable)" />
                <xsl:variable name="current_coefficient" select="normalize-space(@value)" />

                <!-- loop through variable set to find matching variables -->
                <xsl:for-each select="../../../transform/variables/variable-set" >
                    <xsl:variable name="default_variable" select="@default_variable" />
                    <xsl:if test="$current_variable=./@default_variable">
                        <xsl:variable name="source_set" select="@source_set" />
                        <!-- only print the source_set here for default value -->
                        <xsl:value-of select="adara:checkSourceSet($source_set)" />
                        <xsl:text>,-</xsl:text>
                    </xsl:if>

                    <xsl:for-each select="./variable[@name=$current_variable]" >
                        <xsl:variable name="source_set" select="../@source_set" />
                        <xsl:variable name="match" select="@match" />
                        <xsl:variable name="i" select="position()" />

                        <!-- below only prints the source_set here for the first match -->
                        <xsl:if test="$i = 1">
                            <xsl:value-of select="adara:checkSourceSet($source_set)" />
                            <xsl:text>,</xsl:text>
                        </xsl:if>
                        <!-- below we append semicolon at the same line if there is more than one match -->
                        <xsl:if test="$i > 1">
                            <xsl:text>;</xsl:text>
                        </xsl:if>
                        <!-- print match -->
                        <xsl:value-of select="$match" />
                    </xsl:for-each>
                </xsl:for-each>

                <!-- below if statement is for printing coefficient for each coefficient variable, except for 'BETA_NULL' -->
                <xsl:if test="$current_variable != 'BETA_NULL'">
                    <xsl:text>,</xsl:text>
                    <xsl:value-of select="$current_coefficient" />
                    <xsl:text>&#xa;</xsl:text>
                </xsl:if>
            </xsl:for-each>
        </xsl:template>

        <!-- below is a function for checking source_set value -->
        <xsl:function name="adara:checkSourceSet">
            <xsl:param name="source_set" />
            <xsl:choose>
                <xsl:when test="$source_set = 'os'">
                    <xsl:text>operatingSystem</xsl:text>
                </xsl:when>
                <xsl:when test="$source_set = 'slotVisibility'">
                    <xsl:text>adPosition</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$source_set" />
                </xsl:otherwise>
            </xsl:choose>
        </xsl:function>

</xsl:stylesheet>



