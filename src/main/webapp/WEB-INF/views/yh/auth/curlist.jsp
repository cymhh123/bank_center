<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.jsp" %>
</head>
<body>
<div id="app" class="page-container">
    <div class="row cl">
        <div class="col-sm-4">
            <div class="panel panel-primary">
                <div class="panel-header">
                    认证实时播报
                    <span class="time" class="text-r"></span>
                </div>
                <div class="panel-body">
                    <div v-if="notReadList.length==0" class="Huialert Huialert-info"><i class="icon-remove"></i>暂无数据</div>
                    <table class="table table-border table-bg table-hover radius">
                        <tbody>
                        <tr v-for="item in notReadList" class="text-c" role="row">
                            <td>
                                <img width="80" height="60" class="picture-thumb" v-bind:src="item.imgUrl">
                            </td>
                            <td class="text-l">
                                <div class="c-999 f-12">
                                    姓名：{{item.persion}}
                                    <span class="ml-20">时间：<time title="{{item.createDate}}" datetime="{{item.createDate}}">{{item.createDate}}</time></span>
                                </div>
                                <div class="c-999 f-12">
                                    <span>卡号：{{item.cardNo}}</span>
                                </div>
                                <div class="c-999 f-12">
                                    <span>设别号：{{item.deviceNo}}</span>
                                </div>
                                <div class="f-12 c-999">
                                    {{item.pointName}}
                                </div>
                            </td>
                            <td>
                                <span class="label label-success radius" v-if="item.status==1">
                                    认证通过
                                </span>
                                <span class="label label-danger radius" v-else>
                                    认证失败
                                </span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="panel panel-success">
                <div class="panel-header">
                    认证历史记录
                    <span class="time" class="text-r"></span>
                </div>
                <div class="panel-body">
                    <div v-if="readList.length==0" class="Huialert Huialert-success"><i class="icon-remove"></i>暂无数据</div>
                    <table v-if="readList.length!=0" class="table table-border table-bordered table-bg table-striped table-hover">
                        <thead>
                        <tr class="text-c">
                            <th>认证人姓名</th>
                            <th>认证人卡号</th>
                            <th>网点名称</th>
                            <th>设备号</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>认证图像</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in readList" class="text-c">
                            <td>
                                <span data-toggle="tooltip" v-bind:title="item.persion">{{item.persion | substring 5}}</span>
                            </td>
                            <td>
                                <span data-toggle="tooltip" v-bind:title="item.cardNo">{{item.cardNo | substring 16}}</span>
                            </td>
                            <td>
                                <span data-toggle="tooltip" v-bind:title="item.pointName">{{item.pointName | substring 16}}</span>
                            </td>
                            <td>
                                <span data-toggle="tooltip" v-bind:title="item.deviceNo">{{item.deviceNo | substring 12}}</span>
                            </td>
                            <td>
                    <span class="label label-success radius" v-if="item.status==1">
                        认证通过
                    </span>
                    <span class="label label-danger radius" v-else>
                        认证失败
                    </span>
                            </td>
                            <td>{{item.createDate}}</td>
                            <td>
                                <img width="80" height="60" class="picture-thumb" v-bind:src="item.imgUrl">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <audio id="sound"  autoplay="autoplay"/>
    </audio>
</div>
<script type="text/javascript">
    $(function () {
        var baseVueObj = function (listUrl) {
            var initObj = {
                el: '#app',
                data: {
                    notReadList: [], //数据集合
                    readList: [] //数据集合
                },
                ready: function () {
                    var self = this;
                    var time = new Date();
                    var m = time.getMonth() + 1;
                    var t = time.getFullYear() + "年" + m + "月"
                            + time.getDate() + "日 ";
                    $(".time").html(t);
                    //获取数据
                    this.getData();
                    setInterval(function(){
                        self.getData();
                    },30000);
                },
                methods: {
                    getData: function () { //获取数据
                        var self = this;
                        $.ajax({
                            type : "get",
                            url : "${ctxyh}/auth/curlist?tipStatus=0",
                            async : true,
                            success : function(res){
                                var dataArr = [];
                                dataArr = res.extra;
                                if(dataArr != null && dataArr.length > 0){
                                    $("#sound").attr("src","${ctxStatic}/global.m4a");
                                }
                                console.log("dataArr:"+dataArr + "|"+dataArr.length);
                                for(var i = 0;i<dataArr.length;i++){
                                    self.notReadList.push(dataArr[i]);
                                }
                                $.ajax({
                                    type : "get",
                                    url : "${ctxyh}/auth/todaylist",
                                    async : true,
                                    success : function(res){
                                        self.readList = res.extra;
                                    }
                                });
                            }
                        });
                    }
                }
            }
            return initObj;
        };
        var base = baseVueObj();
        Vue.filter('substring', function (input,num) {
            return input.substring(0,num);
        })
        new Vue(base);
    });
</script>
</body>
</html>
