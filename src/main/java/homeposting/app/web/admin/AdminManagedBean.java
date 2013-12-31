package homeposting.app.web.admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name= "adminManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "adminViewId", pattern = "/admin", viewId = "/views/admin/main_admin.xhtml")
public class AdminManagedBean {

}
