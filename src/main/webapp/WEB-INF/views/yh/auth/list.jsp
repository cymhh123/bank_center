<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.jsp" %>
</head>
<body>
<div id="app" class="page-container">
    <div class="text-l">
        <input type="text" v-model="search.persion" placeholder="认证人姓名" style="width:250px" class="input-text">
        <button class="btn btn-success" @click="searchData()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="mt-10">
        <table class="table table-border table-bordered table-bg table-striped table-hover">
            <thead>
            <tr class="text-c">
                <th>认证人姓名</th>
                <th>认证人卡号</th>
                <th>网点名称</th>
                <th>设备号</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>认证图像</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in list" class="text-c">
                <td>
                    {{item.persion}}
                </td>
                <td>{{item.cardNo}}</td>
                <td>{{item.pointName}}</td>
                <td>{{item.deviceNo}}</td>
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
                <td class="td-manage">
                    <a style="text-decoration:none" class="ml-5" title="删除" @click="deleteData($index)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="text-r mt-5">
        <div id="page"></div>
    </div>
</div>
<script type="text/javascript">
    new Vue({
                el: '#app',
                data: {
                    list: [], //数据集合
                    search: {},//查询条件对象
                },
                ready: function () {
                    //获取数据
                    this.getData(1);
                },
                methods: {
                    getData: function (curr) { //获取数据
                        var self = this;
                        this.search.page = curr;
                        $.getJSON("${ctxyh}/auth/list", this.search, function (res) {
                            self.list = res.extra;
                            //显示分页
                            laypage({
                                cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                                pages: res.pageTotal, //通过后台拿到的总页数
                                curr: curr || 1, //当前页
                                jump: function(obj, first){ //触发分页后的回调
                                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                        self.getData(obj.curr);
                                    }
                                }
                            });
                        });
                    },
                    searchData: function () {//条件搜索
                        this.getData(1);
                    },
                    deleteData: function (index) {//删除元素
                        var self = this;
                        var id = this.list[index].id;
                        $.post('${ctxyh}/auth/delete', {id: id}, function (res) {
                            if (res.code == '200') {
                                //更新列表
                                self.getData(self.search.page)
                            }else{
                                alert("删除失败，服务器异常")
                            }
                        })
                    }
                }
            }
    )
</script>
</body>
</html>
