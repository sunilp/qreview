<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="html" indent="yes" />

	<xsl:template match="project-issues">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<link href="include/csa_export.css" rel="stylesheet" type="text/css" />
			</head>
			<body>
				<center>
					<table width="90%" border='0'>
						<tr>
							<td align="center">
								<img src="include/cigital_logo.gif" />
								<br />
								<br />
							</td>
						</tr>
						<tr>
							<td align="center">
								<h2>Cigital SecureAssist(TM)</h2>
							</td>
						</tr>
						<tr>
							<td>
								<br />
								<br />
							</td>
						</tr>
						<tr>
							<td align="center">
								<h3>Project Review Report for</h3>
								<br />
								<xsl:apply-templates select="solution" />
								<xsl:apply-templates select="project" />
							</td>
						</tr>
						<tr>
							<td>
								<br />
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<table border='0' width="100%">
									<tr>
										<td>
											<hr />
											<b>Review Summary</b>
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<table width="30%">
												<tr>
													<td align="left">
														<b>Files Reviewed :</b>
													</td>
													<td align="center">
														<xsl:value-of select="count(files/file)" />
													</td>
												</tr>
												<xsl:choose>
													<xsl:when test="/project-issues/project/files &gt; /project-issues/project/scanned-files"> 
														<tr>
															<td align="left">
																<b>Files Not Selected for Review :</b>
															</td>
															<td align="left">
																<xsl:value-of select="/project-issues/project/files - /project-issues/project/scanned-files" />
															</td>
														</tr>
													</xsl:when>
												</xsl:choose>
												<tr>
													<td align="left">
														<b>Issues Identified :</b>
													</td>
													<td align="center">
														<xsl:value-of select="count(files/file/issues/issue)" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center">
											<table width="90%">
												<tr>
													<td>
														<b>
															<i>Files by Type</i>
														</b>
													</td>
												</tr>
												<tr>
													<td align="center">
														<table id="table-csa" width="50%">
															<thead>
																<th>File Type</th>
																<th>File Count</th>
															</thead>
															<tbody>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.cs')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.cs']) &gt; 0">
																		<tr>
																			<td>C#</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.cs')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.cs'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.config')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.config']) &gt; 0">
																		<tr>
																			<td>Config</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.config')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.config'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.vb')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.vb']) &gt; 0">
																		<tr>
																			<td>VB .NET</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.vb')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.vb'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.aspx')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.aspx']) &gt; 0">
																		<tr>
																			<td>ASPX</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.aspx')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.aspx'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.ascx')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.ascx']) &gt; 0">
																		<tr>
																			<td>ASCX</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.ascx')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.ascx'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.master')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.master']) &gt; 0">
																		<tr>
																			<td>Master</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.master')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.master'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.java')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.java']) &gt; 0">
																		<tr>
																			<td>Java</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.java')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.java'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.jsp')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.jsp']) +
																		count(files/file/path[translate(substring(., string-length(.) - string-length('.jspf')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.jspf']) +
																		count(files/file/path[translate(substring(., string-length(.) - string-length('.xhtml')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.xhtml']) &gt; 0">
																		<tr>
																			<td>JSP</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.jsp')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.jsp']) +
																		count(files/file/path[translate(substring(., string-length(.) - string-length('.jspf')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.jspf']) +
																		count(files/file/path[translate(substring(., string-length(.) - string-length('.xhtml')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.xhtml'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.ftl')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.ftl']) &gt; 0">
																		<tr>
																			<td>FTL</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.ftl')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.ftl'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.xml')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.xml']) &gt; 0">
																		<tr>
																			<td>XML</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.xml')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.xml'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.properties')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.properties']) &gt; 0">
																		<tr>
																			<td>Properties</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.properties')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.properties'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
																<xsl:choose>
																	<xsl:when
																		test="count(files/file/path[translate(substring(., string-length(.) - string-length('.php')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.php']) &gt; 0">
																		<tr>
																			<td>PHP</td>
																			<td>
																				<xsl:value-of
																					select="count(files/file/path[translate(substring(., string-length(.) - string-length('.php')+1), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '.php'])" />
																			</td>
																		</tr>
																	</xsl:when>
																</xsl:choose>
															</tbody>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center">
											<table width="90%">
												<tr>
													<td>
														<b>
															<i>Issues by Importance</i>
														</b>
													</td>
												</tr>
												<tr>
													<td align="center">
														<table id="table-csa" width="50%">
															<thead>
																<th>Issue Importance</th>
																<th>Issue Count</th>
															</thead>
															<tbody>
																<tr>
																	<td>High</td>
																	<td>
																		<xsl:value-of
																			select="count(files/file/issues/issue/rule/importance[text() = 'high'])" />
																	</td>
																</tr>
																<tr>
																	<td>Medium</td>
																	<td>
																		<xsl:value-of
																			select="count(files/file/issues/issue/rule/importance[text() = 'medium'])" />
																	</td>
																</tr>
																<tr>
																	<td>Low</td>
																	<td>
																		<xsl:value-of
																			select="count(files/file/issues/issue/rule/importance[text() = 'low'])" />
																	</td>
																</tr>
															</tbody>
														</table>

													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border='0' width="100%">
									<tr>
										<td>
											<hr />
											<b>Issues</b>
											<hr />
										</td>
									</tr>
									<tr>
										<td>
											<xsl:apply-templates select="files" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</center>

				<br />
			</body>
		</html>
	</xsl:template>


	<xsl:template match="solution">
		<b>Solution Name: </b>
		<xsl:value-of select="./name" />
		<br />

	</xsl:template>

	<xsl:template match="project">
		<b>Project Name: </b>
		<xsl:value-of select="./name" />
		<br />
	</xsl:template>

	<xsl:template match="files">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="cu-hash" />
	<xsl:template match="id" />
	<xsl:template match="status" />

	<xsl:template match="location/offset" />
	<xsl:template match="location/length" />
	<xsl:template match="location/line" />

	<xsl:template match="files/file">
		<table width="100%">
			<tr>
				<td>
					<i>File :</i>
					<xsl:text> </xsl:text>
					<b>
						<xsl:value-of select="./path" />
					</b>
				</td>
			</tr>
			<tr>
				<td align="right">
					<table width="95%">
						<tr>
							<td>
								<xsl:apply-templates />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="path">
	</xsl:template>

	<xsl:template match="issues">
		<xsl:choose>
			<xsl:when test="count(./issue) = 0">
				No issues have been found in this file.
			</xsl:when>

			<xsl:when test="count(./issue) &gt; 0">
				<xsl:apply-templates />
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="issue/code">
		<table width="100%">
			<tr>
				<td><i>Code Snippet</i></td>
			</tr>
			<tr>
				<td align="right">
					<table width="95%">
						<xsl:apply-templates select="./line" />
					</table>
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="issue/code/line">
		<tr><xsl:choose>
			<xsl:when test="@number = ./../../location/line/text()">
				<td  bgcolor="yellow"><xsl:value-of select="@number" />:<xsl:value-of select="." /></td>
			</xsl:when>
			<xsl:when test="not(@number = ./../../location/line/text())">
				<td><xsl:value-of select="@number" />:<xsl:value-of select="." /></td>
			</xsl:when>
		</xsl:choose>
		
		</tr>
	</xsl:template>

	<xsl:template match="call-chain"><table width="100%">
			<tr>
				<td><i>Call Chain</i></td>
			</tr>
			<tr>
				<td align="right">
					<table width="95%">
						<xsl:apply-templates select="./call" />
					</table>
				</td>
			</tr>
		</table>
	</xsl:template>


	<xsl:template match="call-chain/call/file" />
	<xsl:template match="call-chain/call/code" />
	<xsl:template match="call-chain/call/code/line" />
	<xsl:template match="issue/rule" />

	<xsl:template match="call-chain/call">
	<tr><td>
	<xsl:value-of select="./file" />
		:
		<xsl:value-of select="./code/line/@number" />
		<xsl:text>  </xsl:text><xsl:value-of select="./code/line/text()" />
		
	</td></tr>
		
	</xsl:template>

	<xsl:template match="issue">
		<table width="100%" border='0'>
			<tr>
				<td colspan="2">
					<hr />
					<i>Issue</i>
					<hr />
				</td>
			</tr>
			<tr>
				<td width="15%">
					<i>Status</i>
				</td>
				<td>
					<xsl:value-of select="./status"></xsl:value-of>
				</td>
			</tr>
			<tr>
				<td>
					<i>Importance</i>
				</td>
				<td>
					<xsl:value-of
						select="translate(./rule/importance,
                                'abcdefghijklmnopqrstuvwxyz',
                                'ABCDEFGHIJKLMNOPQRSTUVWXYZ')"></xsl:value-of>
				</td>
			</tr>
			<tr>
				<td>
					<i>Rule Category</i>
				</td>
				<td>
					<xsl:value-of select="./rule/category"></xsl:value-of>
				</td>
			</tr>
			<tr>
				<td>
					<i>Rule Title</i>
				</td>
				<td>
					<xsl:value-of select="./rule/title"></xsl:value-of>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<xsl:apply-templates select="./code" />
				</td>
			</tr>
			<xsl:choose>
				<xsl:when test="count(./call-chain/call) &gt; 0">
					<tr>
						<td colspan="2">
							<xsl:apply-templates select="./call-chain" />
						</td>
					</tr>
				</xsl:when>
			</xsl:choose>
			<tr>
				<td colspan="2">
					<hr />
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="error">
		<table width="100%">
			<tr>
				<td>
				<xsl:choose>
					<xsl:when test="count(following-sibling::error) + count(preceding-sibling::error) &gt; 0">
						<xsl:variable name="cn"><xsl:number/></xsl:variable>
						<xsl:choose>
							<xsl:when test="$cn = 1">
								File could not be scanned due to following errors :
							</xsl:when>
							<xsl:otherwise>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						File could not be scanned due to following error :
					</xsl:otherwise>
				</xsl:choose>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<b>Error Id:</b>
							</td>
							<td>
								<i>
									<xsl:value-of select="./id" />
								</i>
							</td>
						</tr>
						<tr>
							<td>
								<b>Error Message:</b>
							</td>
							<td>
								<i>
									<xsl:value-of select="./message" />
								</i>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>