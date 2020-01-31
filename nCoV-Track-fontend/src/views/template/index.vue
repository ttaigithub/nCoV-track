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
        :height="tableHeight"
        header-cell-class-name="table-cell"
        cell-class-name="table-cell"
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
          label="标签"
          min-width="200"
        >
          <template slot-scope="scope">
            <el-tag v-for="(item,index) in (scope.row.tag)" :key="index" style="margin-right: 8px">
              {{ item }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          :width="100"
        >
          <template slot-scope="scope">
            <Edit
              :basic-info="Object.assign({},scope.row)"
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
  queryPrivateDatasetList
} from '@/api/template'

export default {
  name: 'Dashboard',
  components: { SearchBar, Add, Edit },
  data() {
    return {
      searchOptions: [
        {
          value: 'datasetName',
          label: '名称',
          type: 'input'
        },
        {
          value: 'description',
          label: '描述',
          type: 'input'
        },
        {
          value: 'tag',
          label: '标签',
          type: 'input'
        }
      ],
      tableHeader: [
        {
          label: '名称',
          key: 'name',
          minWidth: '200'
        },
        {
          label: '描述',
          key: 'description',
          minWidth: '200'
        },
        {
          label: '修改时间',
          key: 'gmtModified',
          width: 200
        }
      ],
      tableData: [],
      tableLoading: false,
      pagination: {
        currentPage: 1,
        pageSize: 10,
        pageSizeOption: [10, 20, 50],
        total: 0
      }
    }
  },
  computed: {
    // 表格高度动态设定
    tableHeight() {
      const searchHeight = document.getElementById('search-bar')
      return window.innerHeight - 305 - searchHeight
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
        queryCondition: JSON.stringify({
          pageNumber: this.pagination.currentPage,
          pageSize: this.pagination.pageSize,
          queryObject: this.$refs.searchBar.form
        })
      }
      queryPrivateDatasetList(param)
        .then(res => {
          this.tableLoading = false
          if (res.code === 0) {
            this.pagination.total = res.data.total
            this.tableData = res.data.list.map(item => {
              try {
                item.tag = JSON.parse(item.tag)
              } catch (e) {
                item.tag = []
              }
              // item.openToDemandSide = item.openToDemandSide ? '1' : '2'
              return item
            })
          } else {
            this.$notify.warning('服务器繁忙，请刷新')
          }
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
