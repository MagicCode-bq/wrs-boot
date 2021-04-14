package org.lbq.wrsboot.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * null
 * @TableName file
 */
@Data
public class Files implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 相对路径
     */
    private String path;

    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 上传的用户
     */
    private String user;

    /**
     * 创建的时间
     */
    private String createtime;

    /**
     * 修改的时间
     */
    private String updatetime;

    /**
     * 已上传的分片
     */
    private Integer shardindex;

    /**
     * 分片大小
     */
    private Integer shardsize;

    /**
     * 分片总数
     */
    private Integer shardtotal;

    /**
     * 文件标识
     */
    private String filekey;

    private static final long serialVersionUID = 1L;

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
        Files other = (Files) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSuffix() == null ? other.getSuffix() == null : this.getSuffix().equals(other.getSuffix()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getShardindex() == null ? other.getShardindex() == null : this.getShardindex().equals(other.getShardindex()))
            && (this.getShardsize() == null ? other.getShardsize() == null : this.getShardsize().equals(other.getShardsize()))
            && (this.getShardtotal() == null ? other.getShardtotal() == null : this.getShardtotal().equals(other.getShardtotal()))
            && (this.getFilekey() == null ? other.getFilekey() == null : this.getFilekey().equals(other.getFilekey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSuffix() == null) ? 0 : getSuffix().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getShardindex() == null) ? 0 : getShardindex().hashCode());
        result = prime * result + ((getShardsize() == null) ? 0 : getShardsize().hashCode());
        result = prime * result + ((getShardtotal() == null) ? 0 : getShardtotal().hashCode());
        result = prime * result + ((getFilekey() == null) ? 0 : getFilekey().hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Files{");
        sb.append("id=").append(id);
        sb.append(", path='").append(path).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", size=").append(size);
        sb.append(", user='").append(user).append('\'');
        sb.append(", createtime='").append(createtime).append('\'');
        sb.append(", updatetime='").append(updatetime).append('\'');
        sb.append(", shardindex=").append(shardindex);
        sb.append(", shardsize=").append(shardsize);
        sb.append(", shardtotal=").append(shardtotal);
        sb.append(", key='").append(filekey).append('\'');
        sb.append('}');
        return sb.toString();
    }
}