package com.presto.common.code.helper.orm;

import com.presto.common.code.helper.WriteAble;
import com.presto.common.utils.Strings;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by shihao on 16/10/29.
 */
public class Table implements WriteAble {

    private String fullName;
    private String name;
    private String className;
    private String pkg;
    private String comment;
    private List<Column> cols = new ArrayList<Column>();
    private String idColName;

    static List<String> needInCols = Arrays.asList("status", "type");

    private static Map<String, String> importMap = new HashMap<String, String>();

    static {
        importMap.put(java.sql.Date.class.getName(), Date.class.getName());
        importMap.put(java.sql.Timestamp.class.getName(), Date.class.getName());
        importMap.put(BigInteger.class.getName(), Long.class.getName());
    }

    private Set<String> imports = new HashSet<String>();

    Table(String pack, String tname, String prefix, String comment) {
        this.fullName = tname;
        this.name = tname.toLowerCase().replaceFirst(prefix.toLowerCase(), "");
        this.pkg = pack;
        this.className = Strings.camelName(name, false);
        this.comment = comment;
    }

    public void write(String out) {
        String path = out.endsWith(File.separator) ? out : out + File.separator;
        String[] pkgs = pkg.split("\\.");
        StringBuilder sb = new StringBuilder(pkgs[0]);
        sb.append(File.separator);
        for (int i = 1; i < pkgs.length; i++) {
            sb.append(pkgs[i]).append(File.separator);
        }
        path = path + sb.toString();
        path = path.endsWith(File.separator) ? path : path + File.separator;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        VelocityContext vc = new VelocityContext();
        vc.put("pkg", pkg);
        vc.put("table", this);

        write(vc, "template/orm/TemplateEO.java", path + className + ".java");
        write(vc, "template/orm/TemplateMapper.xml", path + className + "Mapper.xml");

    }

    void addColumn(String colName, String javaType, String jdbcType, String comment) {
        if (!imports.contains(javaType)) {
            if (importMap.containsKey(javaType))
                javaType = importMap.get(javaType);
            if (!javaType.startsWith("java.lang"))
                imports.add(javaType);
        }
        javaType = javaType.substring(javaType.lastIndexOf('.') + 1);
        boolean needInCond = needInCols.contains(colName);
        if (StringUtils.isNotBlank(comment)) {
            comment = comment.replaceAll("\n", "");
        }
        cols.add(new Column(colName, javaType, jdbcType, comment, needInCond));
        if (needInCond && !imports.contains(List.class.getName())) {
            imports.add(List.class.getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getCols() {
        return cols;
    }

    public void setCols(List<Column> cols) {
        this.cols = cols;
    }

    public String getIdColName() {
        return idColName;
    }

    public void setIdColName(String idColName) {
        this.idColName = idColName;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
