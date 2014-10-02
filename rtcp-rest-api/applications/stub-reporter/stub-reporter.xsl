<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
<xsl:template match="/">
<html>
<head>
<style type="text/css">
body {font-family: Tahoma, Geneva, sans-serif; }
h2 { text-indent: 20px;}
h3 { text-indent: 30px;}
h4 { text-indent: 40px;}
</style>
</head>
<body>
    <h1>Domains</h1>
      <xsl:for-each select="server/domains/domain">
        <xsl:sort select="@name"/>
        <h2>Domain : <xsl:value-of select="@name"/></h2>
          <xsl:for-each select="environments/environment">
            <xsl:sort select="@name"/>
            <h3>Environment : <xsl:value-of select="@name"/></h3>
            <xsl:for-each select="stubs/stub" >
            <xsl:sort select="@name"/>
            <h4>Stub : <xsl:value-of select="@name"/></h4>
          </xsl:for-each>
          </xsl:for-each>
      </xsl:for-each>
</body>
</html>
</xsl:template>

</xsl:stylesheet>
