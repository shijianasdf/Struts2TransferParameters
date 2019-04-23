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

//**************************************************************************************************************
//
//  This is a test/example file that shows you one way you could use a vizuly object.
//  We have tried to make these examples easy enough to follow, while still using some more advanced
//  techniques.  Vizuly does not rely on any libraries other than D3.  These examples do use jQuery and
//  materialize.css to simplify the examples and provide a decent UI framework.
//
//**************************************************************************************************************


// html element that holds the chart
var viz_container;

// our weighted tree
var viz;

// our theme
var theme;

// nested data
var data = {};

// stores the currently selected value field
var valueField = "Federal";
var valueFields = ["Federal", "State", "Local"];

// tip显示内容，格式化
//var formatCurrency = function (d) { if (isNaN(d)) d = 0; return "$" + d3.format(",.2f")(d) + " Billion"; };
var formatCurrency = function (d) { if (isNaN(d)) d = 0; return "" + d3.format(",.2f")(d) + " Score"; };

//
function loadData() {
	//使用d3加载数据,并传递读取的数据给csv
	//https://github.com/d3/d3/wiki/CSV%E6%A0%BC%E5%BC%8F%E5%8C%96
	//d3.csv - 获取一个CSV (comma-separated values, 冒号分隔值)文件。
	//d3.csv.parse - 将CSV文件字符串转化成object的数组，object的key由第一行决定。如： [{"Year": "1997", "Length": "2.34"}, {"Year": "2000", "Length": "2.38"}]
	//d3.csv.parseRows - 将CSV文件字符串转化成数组的数组。如： [ ["Year", "Length"],["1997", "2.34"],["2000", "2.38"] ]
	//d3.csv.format - 将object的数组转化成CSV文件字符串，是d3.csv.parse的逆操作。
	//d3.csv.formatRows - 将数组的数组转化成CSV文件字符串，是d3.csv.parseRows的逆操作。
	/*
	 *data:
	 * Year,Make,Model,Length
		1997,Ford,E350,2.34
		2000,Mercury,Cougar,2.38
	
	d3.csv("data/test.csv", function(d) {
		alert(d.length);
		
		d3.csv.format(d,function(da){alert(da)});
		d.forEach(function(x){
	        	alert(x.Year+x.Make+x.Length);
	        	
		});
	});
	*/
    //d3.csv("data/weightedtree_federal_budget.csv", function (csv) {
	// d3.csv("data/weightedtree_test.csv", function (csv) {
	d3.csv("data/weightedtree_test.csv", function (csv) {
    	alert(typeof(csv));
    	alert(csv[1]);
    	//[1, 2 ,3, 4].forEach(alert);
    	//alert(d3.csv.parse("foo,bar\n1,2"));
    	//alert(d3.csv.parse("foo\tbar\n1\t2"));
		alert(JSON.stringify(csv));
		
		// 前期处理数据,经过prepData处理后的nest数据
        data.values=prepData(csv);
        //alert(JSON.stringify(data.values));
        // alert(JSON.stringify(data));
        
        //alert(JSON.stringify(data.children(function (d) { return d.values })));
        // 数据初始化
        initialize();

    });
    

}

function prepData(csv) {

    var values=[];
    
    //Clean federal budget data and remove all rows where all values are zero or no labels
    // 对csv所有行（记录条目）处理，判断对应valueFields里的类型值，只要不全为零，就加到values中；
    // 其中k会自动加，此处的k可以去掉
    csv.forEach(function (d,k) { //循环   d代表矩阵里的元素     k(0至2)
    	alert(d);
    	alert(k);
        var t = 0;
        for (var i = 0; i < valueFields.length; i++) {
            t += Number(d[valueFields[i]]);
            //if(Number(d[valueFields[i]]) == 0)alert(k);
            //alert(k);
        }
        if (t > 0) {
        	//push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
            values.push(d);
            //alert(k);
        }
    })
    alert(JSON.stringify(values));
    //alert(values.constructor);
    //Make our data into a nested tree.  If you already have a nested structure you don't need to do this.
    //从values的所有条目中选取对应key的信息,一个条目
    //此处是第二个key（比如：Level2）是第一个key的value,第三个key是第二个的values，所有的json条目（name：value）是第三个values；通过alert(JSON.stringify(nest));即可查询：[{'key':'Pene','values':[{'key':'adfg','values':[{'afs'}]}]}]
    // [{"key":"Old age1","values":[{"key":"Pensions","values":[{"key":"Federal employee retirement and disability (602)","values":[{"Category":"Special Benefits","Level1":"Old age1","Level2":"Pensions","Level3":"Federal employee retirement and disability (602)","Level4":"Special Benefits","Federal":"0.38","":"b","GovXFer":"","State":"","Local":"","Total":"0.38"}]}]}]},
    // {"key":"Old age2","values":[{"key":"Pensions","values":[{"key":"","values":[{"Category":"Special Benefits","Level1":"Old age2","Level2":"Pensions","Level3":"","Level4":"Special Benefits","Federal":"0.38","":"b","GovXFer":"","State":"","Local":"","Total":"0.38"}]}]}]}]
    var nest = d3.nest()
        .key(function (d) {
            return d.Level1;
        })
        .key(function (d) {
            return d.Level2;
        })
        .key(function (d) {
            return d.Level3;
        })
        .entries(values);
    	alert(JSON.stringify(nest));
    // 测试是否json格式转换
   /* 
   var harvest = [{type: "apple", color: "green", value: 1}, 
               {type: "apple", color: "red", value: 2}, 
               {type: "grape", color: "green", value: 3},
               {type: "grape", color: "red", value: 4 }]
    
   sum_by = "color";
   var rollup = d3.nest().key(function(d) {
    return d.type;
   }).entries(harvest);
   //把json变成字符串
   //JSON.parse() 是用于从一个字符串中解析出json对象
    alert(JSON.stringify(rollup));
    */
    
    //var keyL='';
    //for(var keyV in nest){alert(keyV);alert(nest[keyV].constructor);};
    //alert(keyL);
    
   // alert(nest.constructor);

    //This will be a viz.data function;
    // 此函数说明定义在data.js中；
    
    //提取权重,并做树格式数据化
    // alert('befor_:'+JSON.stringify(nest));
    vizuly.data.aggregateNest(nest, valueFields, function (a, b) {
    	//alert(Number(a)+':'+Number(b));
    	
        return Number(a) + Number(b);
    });
    // alert('after_:'+JSON.stringify(nest));
    
    //alert(JSON.stringify(child));
    //alert(JSON.stringify(parent));
    
    
    //Remove empty child nodes left at end of aggregation and add unqiue ids
    function removeEmptyNodes(node,parentId,childId) {
        if (!node) return;
        node.id=parentId + "_" + childId;
        if (node.values) {
            for(var i = node.values.length - 1; i >= 0; i--) {
                node.id=parentId + "_" + i;
                if(!node.values[i].key && !node.values[i].Level4) {
                    node.values.splice(i, 1);
                }
                else {
                    removeEmptyNodes(node.values[i],node.id,i)
                }
            }
        }
    }

    var node={};
    node.values = nest;
    removeEmptyNodes(node,"0","0");
    
    
    
    
     // alert('node'+JSON.stringify(node));
     // alert('nest'+JSON.stringify(nest));
    
    //alert(node.values.length);
    //alert(nest.length);
    
    return nest;
}
// 数据初始化，函数重写，weightedtree.js中也有initialize函数的定义
function initialize() {
	
	//alert(JSON.stringify(document.getElementById("viz_container")));
	//配置指定容器
	
    viz = vizuly.viz.weighted_tree(document.getElementById("viz_container"));
    // alert(typeof(viz));

    //Here we create three vizuly themes for each radial progress component.
    //A theme manages the look and feel of the component output.  You can only have
    //one component active per theme, so we bind each theme to the corresponding component.
    theme = vizuly.theme.weighted_tree(viz).skin(vizuly.skin.WEIGHTED_TREE_AXIIS);
   
    //Like D3 and jQuery, vizuly uses a function chaining syntax to set component properties
    //Here we set some bases line properties for all three components.
    
    //alert(typeof(xin));
    viz.data(data)                                                      // Expects hierarchical array of objects.
        .width(600)                                                     // Width of component
        .height(600)                                                    // Height of component
        //.parents(function (d) { return d.values })
        .children(function (d) { return d.values })                     // Denotes the property that holds child object array
        .key(function (d) { return d.id })                              // Unique key
        .value(function (d) {
            return Number(d["agg_" + valueField]) })                    // The property of the datum that will be used for the branch and node size
        .fixedSpan(-1)                                                  // fixedSpan > 0 will use this pixel value for horizontal spread versus auto size based on viz width，如果>1就会面临着显示成一条竖线
        .label(function (d) {                                           // returns label for each node.
            return trimLabel(d.key || (d['Level' + d.depth]))})
        .on("measure",onMeasure)                                        // Make any measurement changes
        .on("mouseover",onMouseOver)                                    // mouseover callback - all viz components issue these events
        .on("mouseout",onMouseOut)                                      // mouseout callback - all viz components issue these events
        .on("click",onClick);                                           // mouseout callback - all viz components issue these events
    
    //alert(typeof(viz));
    //We use this function to size the components based on the selected value from the RadiaLProgressTest.html page.
    changeSize(d3.select("#currentDisplay").attr("item_value"));

    // Open up some of the tree branches.
    //打开指定的节点
    viz.toggleNode(data.values[1]);
    // viz.toggleNode(data.values[2].values[0]);
    //viz.toggleNode(data.values[3]);
    //viz.toggleNode(data.values[4]);


}


function trimLabel(label) {
   return (String(label).length > 20) ? String(label).substr(0, 17) + "..." : label;
}


var datatip='<div class="tooltip" style="width: 250px; background-opacity:.5">' +
    '<div class="header1">HEADER1</div>' +
    '<div class="header-rule"></div>' +
    '<div class="header2"> HEADER2 </div>' +
    '<div class="header-rule"></div>' +
    '<div class="header3"> HEADER3 </div>' +
    '</div>';


// This function uses the above html template to replace values and then creates a new <div> that it appends to the
// document.body.  This is just one way you could implement a data tip.
function createDataTip(x,y,h1,h2,h3) {

    var html = datatip.replace("HEADER1", h1);
    html = html.replace("HEADER2", h2);
    html = html.replace("HEADER3", h3);
    //alert(html);
    d3.select("body")
        .append("div")
        .attr("class", "vz-weighted_tree-tip")
        .style("position", "absolute")
        .style("top", y + "px")
        .style("left", (x - 125) + "px")
        .style("opacity",0)
        .html(html)
        .transition().style("opacity",1);

}

function onMeasure() {
   // Allows you to manually override vertical spacing
   // viz.tree().nodeSize([100,0]);
}

function onMouseOver(e,d,i) {
    if (d == data) return;
    var rect = e.getBoundingClientRect();
    if (d.target) d = d.target; //This if for link elements
    createDataTip(rect.left, rect.top, (d.key || (d['Level' + d.depth])), formatCurrency(d["agg_" + valueField]),valueField);
    //createDataTip(rect.left, rect.top, (d.key || (d['Level' + d.depth])), formatCurrency(d["agg_" + valueField]),'xintest');


}

function onMouseOut(e,d,i) {
    d3.selectAll(".vz-weighted_tree-tip").remove();
}



//We can capture click events and respond to them
function onClick(g,d,i) {
    viz.toggleNode(d);
}



//This function is called when the user selects a different skin.
function changeSkin(val) {
    if (val == "None") {
        theme.release();
    }
    else {
        theme.viz(viz);
        theme.skin(val);
    }

    viz().update();  //We could use theme.apply() here, but we want to trigger the tween.
}

//This changes the size of the component by adjusting the radius and width/height;
function changeSize(val) {
    var s = String(val).split(",");
    viz_container.transition().duration(300).style('width', s[0] + 'px').style('height', s[1] + 'px');
    
    viz.width(s[0]).height(s[1]*.8).update();
}

//This sets the same value for each radial progress
function changeData(val) {
    valueField=valueFields[Number(val)];
    viz.update();
}






