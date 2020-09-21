<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<template>
  <div class="mod-${classNameLower}">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
    <#list table.columns as column>
        <#if !column.pk>
            <el-form-item>
                <el-input v-model="dataForm.${column.columnNameLower}" placeholder="${column.columnAlias!}" clearable></el-input>
            </el-form-item>
        </#if>
    </#list>

      <el-form-item>
        <el-button @click="search()">查询</el-button>
        <el-button v-if="isAuth('${namespace}:${classNameLower}:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('${namespace}:${classNameLower}:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
    <#list table.columns as column>
      <#if !column.pk>
      <el-table-column
        prop="${column.columnNameLower}"
        header-align="center"
        align="center"
        label="${column.columnAlias!}">
      </el-table-column>
      </#if>
    </#list>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('${namespace}:${classNameLower}:edit')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('${namespace}:${classNameLower}:delete')" type="text" size="small" @click="deleteHandle(scope.row.${table.pkColumn.columnNameFirstLower})">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-if="pageshow"
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="current"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="size"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList" :dataForm="editModel"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './${classNameLower}-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
        <#list table.columns as column>
        <#if !column.pk>
          ${column.columnNameLower}: '',
        </#if>
        </#list>
        },
        editModel: {},
        dataList: [],
        pageshow: true,
        current: 1,
        size: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      search () {
        this.current = 1
        this.pageshow = false
        this.getDataList()
        this.$nextTick(() => {
          this.pageshow = true
        })
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.${r"$"}http({
          url: this.${r"$"}http.adornUrl('/${namespace}/${classNameLower}/page'),
          method: 'get',
          params: this.${r"$"}http.adornParams(this.dataForm)
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.records
            this.totalPage = data.page.total
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.size = val
        this.current = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.current = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.${r"$"}nextTick(() => {
          this.${r"$"}refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ${classNameLower}Ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.${r"$"}confirm(`确定对[id=${r"${"}${classNameLower}Ids.join(',')}]进行[${r"$"}{id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.${r"$"}http({
            url: this.${r"$"}http.adornUrl('/${namespace}/${classNameLower}/delete'),
            method: 'post',
            data: this.${r"$"}http.adornData(${classNameLower}Ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.${r"$"}message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.${r"$"}message.error(data.msg)
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
