package com.yxb.permission.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    private Integer id;
    @NotBlank(message = "权限名称不能为空")
    private String name;
    @NotBlank(message = "权限url不能为空")
    private String url;
    private boolean open = true;
    private boolean checked = false;
    public int childSize = 0;
    private List<Permission> children = new ArrayList<Permission>();
    private List<Permission> childNodes = new ArrayList<Permission>();
    private String note;
    private Integer parentId;
    private String type;
    /**
     * 默认有效
     */
    private String status = "1";
    private String icon;
    /**
     * 默认排序999
     */
    private String sort = "999";
    /**
     * 时间戳
     */
    private Long timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getChildSize() {
        return childSize;
    }

    public void setChildSize(int childSize) {
        this.childSize = childSize;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public List<Permission> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Permission> childNodes) {
        this.childNodes = childNodes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
