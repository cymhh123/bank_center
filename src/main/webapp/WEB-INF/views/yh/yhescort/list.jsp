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
        <input type="text" v-model="search.name" placeholder="押运员名称" style="width:250px" class="input-text">
        <button class="btn btn-success" @click="searchData()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="text-l mt-10">
        <form id="dataform">
            <span class="btn-upload form-group">
                <input class="input-text upload-url size-S" type="text" name="uploadfile-2" id="uploadfile-2" readonly="" datatype="*" nullmsg="请选择照片！" style="width:200px">
                <a href="javascript:void();" id="filePicker" class="btn btn-primary size-S"><i class="Hui-iconfont"></i>上传数据</a>
                <input type="file" multiple="" name="file" class="input-file size-S">
            </span>
            <span class="btn-upload form-group">
                <button class="btn btn-primary size-S" @click="uploadData()">同步数据</button>
            </span>
        </form>
    </div>
    <div class="mt-5">
        <table class="table table-border table-bordered table-bg table-striped table-hover">
            <thead>
            <tr class="text-c">
                <th>姓名</th>
                <th>照片</th>
                <th>电话</th>
                <th>身份证号码</th>
                <th>卡号</th>
                <th>押运中心生成时间</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in list" class="text-c">
                <td>
                    {{item.name}}
                </td>
                <td>
                    <img width="80" height="60" class="picture-thumb" v-bind:src="item.imgUrl">
                </td>
                <td>{{item.phone}}</td>
                <td>{{item.idcard}}</td>
                <td>{{item.cardNo}}</td>
                <td>{{item.yyDate}}</td>
                <td>{{item.createDate}}</td>
                <td class="td-manage">
                    <a style="text-decoration:none" class="ml-5" title="删除" @click="deleteData($index)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    new Vue({
                el: '#app',
                data: {
                    list: [], //数据集合
                    search: {}//查询条件对象
                },
                ready: function () {
                    //获取数据
                    this.getData(1);
                },
                methods: {
                    getData: function (curr) { //获取数据
                        var self = this;
                        $.getJSON("${ctxyh}/yhescort/list", this.search, function (res) {
                            self.list = res.extra;
                        });
                    },
                    searchData: function () {//条件搜索
                        this.getData(1);
                    },
                    deleteData: function (index) {//删除元素
                        var self = this;
                        var id = this.list[index].id;
                        $.post('${ctxyh}/yhescort/delete', {id: id}, function (res) {
                            if (res.code == '200') {
                                //更新列表
                                self.getData(1)
                            }else{
                                alert("删除失败，服务器异常")
                            }
                        })
                    },
                    uploadData:function () {
                        var data = new FormData($('#dataform')[0]);
                        var self = this;
                        $.ajax({
                            url: "${ctxyh}/yhescort/upload",
                            type: 'POST',
                            data: data,
                            dataType: 'JSON',
                            cache: false,
                            processData: false,
                            contentType: false,
                            success:function(res){
                                self.list = res.extra;
                            }
                        });
                    }
                }
            }
    )
</script>
</body>
</html>
