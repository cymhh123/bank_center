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
        <input type="text" v-model="search.name" placeholder="网点名称" style="width:250px" class="input-text">
        <button class="btn btn-success" @click="searchData()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="text-l mt-10">
        <button class="btn btn-primary size-S" @click="addPage()">添加</button>
    </div>
    <div class="mt-5">
        <table class="table table-border table-bordered table-bg table-striped table-hover">
            <thead>
            <tr class="text-c">
                <th>网点名称</th>
                <th>负责人</th>
                <th>电话</th>
                <th>设备编号</th>
                <th>地址</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in list" class="text-c">
                <td>
                    {{item.name}}
                </td>
                <td>{{item.persion}}</td>
                <td>{{item.phone}}</td>
                <td>{{item.deviceNo}}</td>
                <td>{{item.address}}</td>
                <td>{{item.createDate}}</td>
                <td class="td-manage">
                    <a style="text-decoration:none" class="ml-5" @click="editPage($index)" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>编辑</a>
                    <a style="text-decoration:none" class="ml-5" title="删除" @click="deleteData($index)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="modal" class="modal hide fade" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-width="800">
        <form action="" method="post" class="form form-horizontal responsive" id="dataform">
            <div class="modal-body">
                <h4>编辑网点</h4>
                <div class="line"></div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>网点名称：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" placeholder="请输入网点名称" v-model="entity.name" datatype="*1-100" nullmsg="请输入名称！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>网点地址：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" placeholder="请输入网点地址" v-model="entity.address" datatype="*1-200" nullmsg="请输入地址！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>负责人：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" placeholder="请输入负责人姓名" v-model="entity.persion" datatype="*1-10" nullmsg="请输入负责人姓名！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>手机号：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" placeholder="请输入负责人电话" v-model="entity.phone" datatype="m" nullmsg="请输入手机号">
                    </div>
                    <div class="col-xs-5"></div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>设备编号：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" placeholder="请输入设备编号" v-model="entity.deviceNo" datatype="*1-100" nullmsg="请输入设备编号">
                    </div>
                    <div class="col-xs-5"></div>
                </div>
            </div>
        </form>
            <div class="modal-footer">
                <button class="btn btn-primary" @click="commitData()">提交</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
            </div>
    </div>
</div>
<script type="text/javascript">
    new Vue({
                el: '#app',
                data: {
                    list: [], //数据集合
                    entity: {},//单个数据对象
                    valid: {},//表单验证对象
                    search: {}//查询条件对象
                },
                ready: function () {
                    //获取数据
                    this.getData(1);
                    //初始化表单验证
                    this.valid = $("#dataform").Validform({
                        tiptype: 2
                    });
                },
                methods: {
                    getData: function (curr) { //获取数据
                        var self = this;
                        $.getJSON("${ctxyh}/point/list", this.search, function (res) {
                            self.list = res.extra;
                        });
                    },
                    searchData: function () {//条件搜索
                        this.getData(1);
                    },
                    editPage: function (index) {//编辑页面
                        if (this.list != null && this.list.length > 0) {
                            //复制一个对象
                            var b = {};
                            $.extend(b, this.list[index]);
                            this.entity = b;
                            console.log("entity:"+JSON.stringify(this.entity));
                            //重置表单验证
                            this.valid.resetForm();
                            //重置后，文字消失不掉（bug）
                            $(".Validform_checktip").html("");
                            $("#modal").modal("show");
                        }
                    },
                    addPage: function () {//添加页面
                        //重置表单验证
                        this.valid.resetForm();
                        //重置后，文字消失不掉（bug）
                        $(".Validform_checktip").html("");
                        this.entity = {};//清空单个对象
                        $("#modal").modal("show");
                    },
                    commitData: function () {//提交数据
                        var self = this;
                        console.log("commitData:"+JSON.stringify(this.entity));
                        //表单验证
                        var flag = this.valid.check();
                        if (flag) {
                            $.ajax({
                                type: "POST",
                                url: '${ctxyh}/point/edit',
                                data: this.entity,
                                success: function(res){
                                    if(res.code == "200"){
                                        //更新列表数据
                                        self.getData(1);
                                    }else{
                                        alert(res.msg);
                                    }
                                    //清空保留的新增数据
                                    self.entity = {};
                                    $("#modal").modal("hide");
                                },
                                error: function(XMLHttpRequest){
                                    console.log("Error: " + XMLHttpRequest.responseText)
                                }
                            });
                        }
                    },
                    deleteData: function (index) {//删除元素
                        var self = this;
                        var id = this.list[index].id;
                        $.post('${ctxyh}/point/delete', {id: id}, function (res) {
                            if (res.code == '200') {
                                //更新列表
                                self.getData(1)
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
