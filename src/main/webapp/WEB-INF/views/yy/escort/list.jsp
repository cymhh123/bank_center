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
        <span class="select-box" style="width:150px">
			<select class="select" v-model="search.bankId" name="bankId" size="1">
                <option value="" selected>请选择银行</option>
				 <option v-for="it in extra" v-bind:value="it.id">
                     {{ it.name }}
                 </option>
			</select>
        </span>
        <button class="btn btn-success" @click="searchData()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="text-l mt-10">
        <c:if test="${empty sessionScope.admin.bankId}">
            <button class="btn btn-primary size-S" @click="addPage()">添加</button>
        </c:if>
        <a target="_blank" class="btn btn-primary size-S" href="${ctxyy}/escort/down">导出数据包</a>
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
                <th>银行</th>
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
                <td>{{item.bankName}}</td>
                <td>{{item.createDate}}</td>
                <td class="td-manage">
                    <c:if test="${empty sessionScope.admin.bankId}">
                        <a style="text-decoration:none" class="ml-5" @click="editPage($index)" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>编辑</a>
                        <a style="text-decoration:none" class="ml-5" title="删除" @click="deleteData($index)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="modal" class="modal hide fade" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-width="800">
        <form action="" method="post" class="form form-horizontal responsive" id="dataform">
            <div class="modal-body">
                <h4>编辑押运员</h4>
                <div class="line"></div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>押运员名称：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" name="name" placeholder="请输入押运员名称" v-model="entity.name" datatype="*1-20" nullmsg="请输入名称！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>照片：</label>
                    <div class="formControls col-xs-5">
                        <span class="btn-upload form-group">
                            <input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly="" datatype="*" nullmsg="请选择照片！" style="width:200px">
                            <a href="javascript:void();" id="filePicker" class="btn btn-primary"><i class="Hui-iconfont"></i> 浏览文件</a>
                            <input type="file" multiple="" name="file" class="input-file">
                        </span>
                    </div>
                    <div class="col-xs-5"></div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>联系电话：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" name="phone" placeholder="请输入手机号" v-model="entity.phone" datatype="m" nullmsg="请输入手机号！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>身份证号：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" name="idcard" placeholder="请输入身份证号" v-model="entity.idcard" datatype="*1-18" nullmsg="请输入身份证号！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>卡号：</label>
                    <div class="formControls col-xs-5">
                        <input type="text" class="input-text" name="cardNo" placeholder="请输入押运员卡号" v-model="entity.cardNo" datatype="*1-100" nullmsg="请输入卡号！">
                    </div>
                    <div class="col-xs-5"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-2"><span class="c-red">*</span>所属银行：</label>
                    <div class="formControls col-xs-5">
                        <span class="select-box" style="padding:2px 0px">
                            <select v-model="entity.bankId" class="select" name="bankId" datatype="*" nullmsg="请选择银行">
                                <option v-for="it in extra" v-bind:value="it.id">
                                    {{ it.name }}
                                </option>
                            </select>
                        </span>
                    </div>
                    <div class="col-xs-5"></div>
                </div>
            </div>
            <input type="hidden" name="id" value="{{entity.id}}"/>
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
                    search: {},//查询条件对象
                    extra:[],
                    oper: ""
                },
                ready: function () {
                    var self = this;
                    $.ajax({
                        type : "get",
                        url : "${ctxyy}/bank/list",
                        async : false,//同步
                        success : function(resp){
                            self.extra = resp.extra;
                        }
                    });
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
                        $.getJSON("${ctxyy}/escort/list", this.search, function (res) {
                            self.list = res.extra;
                        });
                    },
                    searchData: function () {//条件搜索
                        this.getData(1);
                    },
                    editPage: function (index) {//编辑页面
                        this.oper = "edit";
                        var self = this;
                        if (this.list != null && this.list.length > 0) {
                            //银行列表
                            $.ajax({
                                type : "get",
                                url : "${ctxyy}/bank/list",
                                async : false,//同步
                                success : function(resp){
                                    self.extra = resp.extra;
                                }
                            });
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
                        this.oper = "add";
                        //银行列表
                        var self = this;
                        $.ajax({
                            type : "get",
                            url : "${ctxyy}/bank/list",
                            async : false,//同步
                            success : function(resp){
                                self.extra = resp.extra;
                            }
                        });
                        //重置表单验证
                        this.valid.resetForm();
                        //重置后，文字消失不掉（bug）
                        $(".Validform_checktip").html("");
                        this.entity = {};//清空单个对象
                        $("#modal").modal("show");
                    },
                    commitData: function (){//表单提交
                        this.oper = "commit";
                        var self = this;
                        var flag = this.valid.check();
                        if(flag){
                            var data = new FormData($('#dataform')[0]);
                            $.ajax({
                                url: '${ctxyy}/escort/edit',
                                type: 'POST',
                                data: data,
                                dataType: 'JSON',
                                cache: false,
                                processData: false,
                                contentType: false,
                                success:function(res){
                                    if(res.code == "200"){
                                        self.getData(1);
                                    }else{
                                        alert("保存失败，服务器异常")
                                    }
                                    //清空保留的新增数据
                                    self.entity = {};
                                    $("#modal").modal("hide");
                                }
                            });
                        }
                    },
                    deleteData: function (index) {//删除元素
                        var self = this;
                        var id = this.list[index].id;
                        $.post('${ctxyy}/escort/delete', {id: id}, function (res) {
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
