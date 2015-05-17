<workbook>
	<worksheet name="Sheet1">
		<section startRow="0" endRow="0" />
		<loop startRow="1" items="result01" var="h"
			varType="${clazz}">
			<section startRow="1" endRow="1">
<#list ms as m>
			<mapping row="1" col="${m.col}">h.${m.field}</mapping>
</#list>
			</section>
			<loopbreakcondition>
				<rowcheck offset="0">
					<cellcheck offset="0" />
				</rowcheck>
			</loopbreakcondition>
		</loop>
	</worksheet>
</workbook>