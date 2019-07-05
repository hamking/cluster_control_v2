package com.adb.autoComponent;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

public class ChangeNodes {

    /**
     * 从指定节点开始,递归遍历所有子节点
     * @param node node
     */
    public void getNodes(Element node){

        List<Attribute> listAttr=node.attributes();
        for(Attribute attr:listAttr){
            String name=attr.getName();
            String value=attr.getValue();
            if (name.equals ("class")){
                if (value.contains("$")){
                    node.setName(value.replace("$","."));
                }else {
                    node.setName(value);
                }
            }
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();
        for(Element e:listElement){
            getNodes(e);
        }
    }
}
