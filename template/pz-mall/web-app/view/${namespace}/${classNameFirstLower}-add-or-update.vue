<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

<template>
  <el-dialog
    :title="!dataForm.${table.pkColumn.columnNameFirstLower} ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <#list table.columns as column>
      <#if !column.pk>
          <el-form-item label="${column.columnAlias!}" prop="${column.columnNameLower}">
            <el-input v-model="dataForm.${column.columnNameLower}" placeholder="${column.columnAlias!}"></el-input>
          </el-form-item>
      </#if>
      </#list>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataRule: {

        }
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.${r"$"}http({
              url: this.${r"$"}http.adornUrl(`/${namespace}/${classNameLower}/${r"$"}{!this.dataForm.${table.pkColumn.columnNameFirstLower} ? 'save' : 'update'}`),
              method: 'post',
              data: this.${r"$"}http.adornData(this.dataForm)
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
