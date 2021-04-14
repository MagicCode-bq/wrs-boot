package org.lbq.wrsboot.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * null
 * @TableName menu
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 对应请求URL
     */
    private String url;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 对应的组件
     */
    private String component;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 图标
     */
    private String iconcls;

    /**
     * Mate数据
     */
     private  Mate mate;

    /**
     * 父菜单ID
     */
    private Integer parentid;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
    * 菜单包含的子菜单
    */
    List<Menu> children;



    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIconcls() == null ? other.getIconcls() == null : this.getIconcls().equals(other.getIconcls()))
            && (this.getParentid() == null ? other.getParentid() == null : this.getParentid().equals(other.getParentid()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIconcls() == null) ? 0 : getIconcls().hashCode());
        result = prime * result + ((getParentid() == null) ? 0 : getParentid().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", component='").append(component).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", iconcls='").append(iconcls).append('\'');
        sb.append(", mate=").append(mate);
        sb.append(", parentid=").append(parentid);
        sb.append(", enabled=").append(enabled);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}