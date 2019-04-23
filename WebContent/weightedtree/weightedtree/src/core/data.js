/*
 Copyright (c) 2016, BrightPoint Consulting, Inc.

 MIT LICENSE:

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 IN THE SOFTWARE.
 */

// @version 1.1.20

vizuly.data = {};

// Takes data produced by a d3.nest() call and aggregates the aggProperties according to the aggregateFunction
// This also will take all nth deep child nodes and create the same properties and associated values on the parent nest nodes.
// 函数说明：
// 根据从d3.nest()得到的数据nest，嵌套性质的json格式
// 以及aggProperties对应数据源定义的类别（"Federal", "State", "Local"）； 
// aggregateFunction对应函数
vizuly.data.aggregateNest = function(nest,aggProperties, aggregateFunction) {

    //Go down to the last depth and get source values so we can roll them up t
	// 最深层数据，也即是JSON中的最里层数据；目前是nest提取一行数据条目
    var deepestChildNode = nest[0];
    // alert('nest[0]');
	// alert(JSON.stringify(deepestChildNode));
    while (deepestChildNode.values) {
    	// 获得最里层value，也就是整个条目（保留格式和顺序的条目）；
        deepestChildNode = deepestChildNode.values[0]
    }
    // alert(JSON.stringify(deepestChildNode));
    
    var childProperties = [];
    
    // 获取json里的property也即是names，["Category","Level1","Level2","Level3","Level4","Federal","","GovXFer","State","Local","Total"]
    // alert(JSON.stringify( Object.getOwnPropertyNames(deepestChildNode)));
    // var xin = Object.getOwnPropertyNames(deepestChildNode);
    //  alert(xin[0]);
    
    // 提取json里的名称；其实也可以通过 var childProperties= Object.getOwnPropertyNames(deepestChildNode);直接获取
    Object.getOwnPropertyNames(deepestChildNode).forEach(function (name) {
        childProperties.push(name);
    })

    // alert('befor_:'+JSON.stringify(nest));
    aggregateNodes(nest);
    // alert('after_:'+JSON.stringify(nest));
    
     //alert('parent_:'+JSON.stringify(parent));
	 //alert('child_:'+JSON.stringify(child));
     //alert('nodes_:'+JSON.stringify(nodes));
     //alert('node_:'+JSON.stringify(node));
    

    function setSourceFields(child, parent) {
    	// parent=true;
    	// alert('parent_:'+JSON.stringify(parent));
    	// alert('child_:'+JSON.stringify(child));
        if (parent) {
            for (var i = 0; i < childProperties.length; i++) {
                var childProperty = childProperties[i];
                // 如果child中还有元素，就付给子child
                if (child[childProperty] != undefined) {
                    child["childProp_" + childProperty] = child[childProperty];
                }
                parent["childProp_" + childProperty] = (child["childProp_" + childProperty]) ? child["childProp_" + childProperty] : child[childProperty];
            }
        }
        // alert('parent'+JSON.stringify(parent));
        // alert('children'+JSON.stringify(child));
        
    }
    
    /*
    var testxin;
    testxin = {
    		'xin1':'xin1',
    		'xin2':'xin2'
    		
    }
    testxin['xin3'] = 'xin4';
    alert(testxin['xin3']);
    */
    
    // 自己内部可调用函数
    function aggregateNodes(nodes,parent) {
    	// alert('nodes.length:'+nodes.length+'\n'+JSON.stringify(nodes));
    	// alert('parent.length:'+typeof(parent)+'\n'+JSON.stringify(parent));
    	
    	// 递归读取嵌套内部数据，values对应的值
    	// 大条目循环
        for (var y = 0; y < nodes.length; y++) {
            var node = nodes[y];
            if (node.values) {
            	// 一直往里层循环
                aggregateNodes(node.values,node);
                
                // 大条目下，内部节点的循环
                for (var z = 0; z < node.values.length; z++) {
                    var child = node.values[z];
                    
                    // aggProperties对应数据源定义的类别（"Federal", "State", "Local"）； 
                    // 类别循环，
                    for (var i = 0; i < aggProperties.length; i++) {
                    	//判断是否含有对应的事项，如果没有添加事项；
                    	// 是函数aggregateFunction，在vizuly.data.aggregateNest由显示
                        if (isNaN(node["agg_" + aggProperties[i]])) node["agg_" + aggProperties[i]] = 0;
                        node["agg_" + aggProperties[i]] = aggregateFunction(node["agg_" + aggProperties[i]], child["agg_" + aggProperties[i]]);
                    }
                }
                //alert(JSON.stringify(node));
            }
            else {
                for (var i = 0; i < aggProperties.length; i++) {
                    node["agg_" + aggProperties[i]] = Number(node[aggProperties[i]]);
                    if (isNaN(node["agg_" + aggProperties[i]])) {
                        node["agg_" + aggProperties[i]] = 0;
                    }
                }
            }
            setSourceFields(node, parent);
        }

    }

}

