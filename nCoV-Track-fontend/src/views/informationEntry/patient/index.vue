<template>
  <div class="app-container">
    <SearchBar
      id="search-bar"
      ref="searchBar"
      :search-options="searchOptions"
      :search-callback="search"
      :refresh-callback="refresh"
    />
    <el-card class="table">
      <el-table
        v-loading="tableLoading"
        :data="tableData"
        row-key="id"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        :height="tableHeight"
        :expand-row-keys="expandRowKeys"
      >
        <el-table-column
          v-for="item of tableHeader"
          :key="item.key"
          :prop="item.key"
          :label="item.label"
          :width="item.width"
          :min-width="item.minWidth"
        />
        <el-table-column
          fixed="right"
          label="操作"
          :width="100"
        >
          <template slot-scope="scope">
            <Edit
              v-if="!scope.row.disabled"
              :basic-info="scope.row"
              :success="queryTableData"
            />
          </template>
        </el-table-column>
        />
      </el-table>
      <div class="bottom">
        <Add :success="queryTableData" />
        <el-pagination
          class="pagination"
          :current-page.sync="pagination.currentPage"
          :page-size="pagination.pageSize"
          :page-sizes="pagination.pageSizeOption"
          layout="total, sizes, prev, pager, next"
          :total="pagination.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar/index'
import Add from './add'
import Edit from './edit'
import {
  queryPatientList
} from '@/api/informationEntry/patient'

export default {
  name: 'Dashboard',
  components: { SearchBar, Add, Edit },
  data() {
    return {
      searchOptions: [
        {
          label: '姓名',
          value: 'patientName',
          type: 'input'
        },
        {
          label: '状态',
          value: 'state',
          type: 'select',
          options: [
            {
              value: 0,
              label: '疑似'
            },
            {
              value: 1,
              label: '确诊'
            },
            {
              value: 2,
              label: '死亡'
            },
            {
              value: 3,
              label: '治愈'
            },
            {
              value: 4,
              label: '可能传播者'
            }
          ]
        },
        {
          label: '年龄',
          value: 'age',
          type: 'input'
        },
        {
          label: '性别',
          value: 'sex',
          type: 'select',
          options: [
            {
              value: 0,
              label: '女'
            },
            {
              value: 1,
              label: '男'
            }
          ]
        },
      ],
      tableHeader: [
        {
          label: '姓名',
          key: 'patientName',
          minWidth: '200'
        },
        {
          label: '状态',
          key: 'stateFormat',
          minWidth: '200'
        },
        {
          label: '年龄',
          key: 'age',
          minWidth: '200'
        },
        {
          label: '性别',
          key: 'sexFormat',
          minWidth: '200'
        }
      ],
      tableData: [],
      tableLoading: false,
      pagination: {
        currentPage: 1,
        pageSize: 10,
        pageSizeOption: [10, 20, 50],
        total: 0
      },
      nodeList: []
    }
  },
  computed: {
    // 表格高度动态设定
    tableHeight() {
      const searchHeight = document.getElementById('search-bar')
      return window.innerHeight - 305 - searchHeight
    },
    expandRowKeys() {
      if (this.tableData.length === 0) {
        return ['-1']
      } else {
        return this.tableData.map(item => {
          return item.id.toString()
        })
      }
    }
  },
  mounted() {
    this.refresh()
  },
  methods: {
    refresh() {
      this.queryTableData()
    },
    search() {
      this.queryTableData()
    },
    handleSizeChange(e) {
      this.pagination.pageSize = e
      this.queryTableData()
    },
    handleCurrentChange(e) {
      this.pagination.currentPage = e
      this.queryTableData()
    },
    queryTableData() {
      this.tableLoading = true
      const param = {
        data: JSON.stringify({
          pageNum: this.pagination.currentPage,
          pageSize: this.pagination.pageSize,
          ...this.$refs.searchBar.form
        })
      }
      queryPatientList(param)
        .then(res => {
          this.tableLoading = false
          this.tableData = this.formatTableData(res.data.list)
          this.pagination.total = res.data.total
        })
    },
    formatTableData(data) {
      const state = {
        0: '疑似',
        1: '确诊',
        2: '死亡',
        3: '治愈',
        4: '可能传播者',
      }
      const sex = {
        0: '女',
        1: '男',
      }
      if (data.length === 0) {
        return []
      }
      return data.map(item => {
        item.stateFormat = state[item.state],
          item.sexFormat = sex[item.sex]
        return item
      })
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .table {
    margin-top: 16px;
    .bottom {
      height: 32px;
      margin-top: 16px;
      .pagination {
        float: right;
      }
    }
  }

  .table-cell {
    text-align: center;
  }
</style>
