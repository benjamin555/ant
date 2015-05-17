package ${pkg};
import com.sp.net.annotation.HtmlElement;

public class ${clazz} {

<#list ms as m>
	@HtmlElement(xpath = "//*[@id='${m.field}']")
	private String ${m.field};
	
	public String get${m.field?cap_first}() {
		return ${m.field};
	}

	public void set${m.field?cap_first}(String ${m.field}) {
		this.${m.field} = ${m.field};
	}
</#list>

	
	
}

