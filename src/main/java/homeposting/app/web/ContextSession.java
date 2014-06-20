package homeposting.app.web;

import org.ocpsoft.logging.Logger;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.config.mapping.UrlMapping;

public class ContextSession {
	public ContextSession() {
		
	}
	
	public String getMainContextName(){
		String path = PrettyContext.getCurrentInstance().getContextPath();
		//Logger.getLogger("PATH").info(path);
		path = PrettyContext.getCurrentInstance().getCurrentViewId();
		//Logger.getLogger("CURRENT").info(path);
		path = PrettyContext.getCurrentInstance().getDynaViewId();
		//Logger.getLogger("DYNA").info(path);

		UrlMapping urlmap = PrettyContext.getCurrentInstance().getCurrentMapping();
		/*Logger.getLogger("ID").info(urlmap.getId());
		Logger.getLogger("PARENT").info(urlmap.getParentId());
		Logger.getLogger("PATTERN").info(urlmap.getPattern());
		Logger.getLogger("VIEW").info(urlmap.getViewId());*/

		String pattern = urlmap.getPattern();
		String result;
		if(pattern.length() < 2){
			result = "none";
		}else{
			int nextIndex = pattern.indexOf("/",1);
			if(nextIndex < 0){
				result = pattern.substring(1);
			}else{
				result = pattern.substring(1,nextIndex);
			}
		}
		//Logger.getLogger("RESULT").info("'" + result + "'");
		return result;
	}
}
